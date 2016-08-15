package hackerrank;
import java.util.Scanner;

/**
 * More information about question access HackerRank
 * https://www.hackerrank.com/challenges/a-very-big-sum
 * 
 */
public class VeryBigSun {
	static int aliceScore = 0;
	static int bobScore = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long sun = 0;
		String secondValues = scanner.nextLine();
		String[] vector = secondValues.split(" ");
		for (int i = 0; i < vector.length; i++) {
			sun += Integer.valueOf(vector[i]);
		}

		System.out.println(sun);

		scanner.close();

	}
}
