package HW6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class relaxAndRound {

	public static int N = 500;
	public static int M = 500;
	public static int D = 25;

	public static void main(String[] args) {
		Question1();
		// Question2();
	}

	private static void printSetCover(ArrayList<HashSet<Integer>> list) {
		// for all skills
		String min = "minimize z: ";

		ArrayList<HashSet<String>> skills = new ArrayList<HashSet<String>>(M);
		for (int i = 0; i < list.size(); i++) {
			HashSet<String> set = new HashSet<String>();
			skills.add(set);
			System.out.println("var x" + (i + 1) + " >= 0;");
			if (i < list.size() - 1)
				min += "x" + (i + 1) + " + ";
			else
				min += "x" + (i + 1) + ";";

		}
		System.out.println(min);
		int c = 1;

		for (int i = 0; i < list.size(); i++) {
			String output = "subject to c" + c + ": ";
			System.out.println(output + "x" + (i + 1) + " <= 1;" );
			c++;
		}

		for (int i = 0; i < list.size(); i++) {
			HashSet<Integer> set = list.get(i);
			for (Integer item : set) {
				skills.get(i).add("x" + (item + 1));
			}
		}

		for (HashSet<String> set : skills) {
			String output = "subject to c" + c + ": ";
			c++;
			int i = 0;
			for (String var : set) {
				if (i < set.size() - 1)
					output += var + "+";
				else
					output += var + " >= 1;";
				i++;
			}
			System.out.println(output);

		}
		System.out.println("end;");
	}

	private static void Question1() {
		int n = N; // # people
		int m = M; // # skills
		int d = D; // size of subset

		ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>(n);
		Random rand = new Random();

		for (int i = 0; i < n; i++) { // for each person
			HashSet<Integer> set = new HashSet<Integer>();
			while (set.size() < d) { // until set has d skills
				set.add(rand.nextInt(m));
			}
			list.add(set);
		}

		printSetCover(list);
	}

	private static void Question2() {
		int N = 10_000_000;
		int[] servers = new int[N];

		Random rand = new Random();
		for (int r = 0; r < N; r++) {
			int index = rand.nextInt(N);
			int index2 = rand.nextInt(N);

			if (servers[index] < servers[index2]) {
				servers[index] = servers[index] + 1;
			} else {
				servers[index2] = servers[index2] + 1;

			}
		}
		int total = 0;

		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int j = 0; j < N; j++) {
				if (servers[j] == i) { // count total occurences of current freq
					count++;
					total++;
				}
			}
			System.out.println(i + ": " + count);
			if (total >= N)
				break;
		}
	}
}
