import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Sampling {
	static List<Integer> population = new ArrayList<Integer>(10_000_000);

	public static void main(String[] args) {
		population = new ArrayList<Integer>(10_000_000);
		int numA = (int) (10_000_000 * .52); //part B - 52% vote A
		for (int i = 0; i < 10_000_000; i++) {
			if (i < numA)
				population.add(1); // 1=A
			else
				population.add(0); // 0=B
		}

		double sample = 0;
		int timesToLoop = 20;
		for (int i = 0; i < timesToLoop; i++) {
			double result = 0;
			double n = 400;
			while (result < 0.9) { //For part b and c - what sample size is the prob A is majority is 0.9?
				result = getSamples(n);
				n += 1;
			}
			sample += n;
			System.out.println(n);

		}
		System.out.println(sample/timesToLoop);

	}

	public static double getSamples(double sampleSize) {
		Random rand = new Random();
		double majority = 0;
		for (int timesToLoop = 0; timesToLoop < 100; timesToLoop++) { //perform sample 100 times for better estimation
			double count = 0;
			for (int i = 0; i < sampleSize; i++) {
				int index = rand.nextInt(population.size());
				if (population.get(index) == 1)
					count++;
			}
			if (count > sampleSize / 2)
				majority++;

		}

		return majority / 100;
	}
}
