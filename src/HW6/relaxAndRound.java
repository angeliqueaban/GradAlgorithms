package HW6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class relaxAndRound {

	public static int N = 500;
	public static int M = 500;
	public static int D = 25;

	public static void main(String[] args) {
		// Question1();
		// Question2();
		RandomRound();
	}

	/**
	 * Assign the people for each skill based on model from part b
	 * @param skillsList
	 */
	private static void InitializeList(ArrayList<HashSet<String>> skillsList) {
		try {
			Scanner s = new Scanner(new File("constraints.txt"));
			while (s.hasNext()) {
				HashSet<String> set = new HashSet<String>();
				String[] tokens = s.nextLine().split(":");
				tokens = tokens[1].split("\\+");
				for (int i = 0; i < tokens.length; i++) {
					set.add(tokens[i].trim());
				}	
				skillsList.add(set);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void RandomRound() {
		ArrayList<HashSet<String>> skillList = new ArrayList<HashSet<String>>(M);
		InitializeList(skillList);

		ArrayList<Integer> people = new ArrayList<Integer>();
		ArrayList<String> peopleNames = new ArrayList<String>();

		double t = 8.0;

		try {
			Random rand = new Random();
			Scanner scan = new Scanner(new File("LP.txt"));
			scan.nextLine();
			for (int i = 0; i < 500; i++) {
				scan.next();
				double r = rand.nextDouble();
				double x = scan.nextDouble();
				if (r <= x*t) {//prob of rounding up
					people.add(1);
					peopleNames.add("x"+(i+1));
				}
				else
					people.add(0);
			}
			//for each person picked
			for(String name: peopleNames) {
				//System.out.println(name);
				Iterator<HashSet<String>> iter = skillList.iterator();
				while(iter.hasNext()) {
					if(iter.next().contains(name)) {
						iter.remove();
					}
				}
			}
			System.out.println("T: " + t);
			System.out.println("Total people hired: " + peopleNames.size());
			System.out.println("Total skills not covered: " + skillList.size());

			/*
			 * while(scan.hasNext()) { System.out.println(scan.next()); }
			 */
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Used for printing input for a LP solver
	 * 
	 * @param list
	 */
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
			System.out.println(output + "x" + (i + 1) + " <= 1;");
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
		rand.setSeed(0);
		for (int i = 0; i < n; i++) { // for each person
			HashSet<Integer> set = new HashSet<Integer>();
			while (set.size() < d) { // until set has d skills
				set.add(rand.nextInt(m));
			}
			list.add(set);
		}

		//RandomRound(list);
		// printSetCover(list);
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
