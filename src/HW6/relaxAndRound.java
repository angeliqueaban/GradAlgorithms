package HW6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class relaxAndRound {

	public static void main(String[] args) {
		Question1();
		//Question2();
	}
	private static void printSetCover(ArrayList<HashSet<Integer>> list) {
		for(HashSet<Integer> set: list) {
			System.out.println(set);
		}
	}
	private static void Question1() {
		int n = 500; // # people
		int m = 500; // # skills
		int d = 25; // size of subset

		ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>(500);
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
		for(int r =0; r<N; r++) {
			int index = rand.nextInt(N);
			int index2 = rand.nextInt(N);

			if(servers[index]<servers[index2]) {
				servers[index] = servers[index]+1;
			}
			else {
				servers[index2] = servers[index2]+1;

			}
		}
		int total =0;

		for(int i=0; i<N; i++) {
			int count =0;
			for(int j=0; j<N; j++) {
				if(servers[j]==i) { //count total occurences of current freq
					count++;
					total++;
				}
			}
			System.out.println(i + ": " + count);
			if(total >= N)
				break;
		}
	}
}
