package hackerrank;
import java.util.Scanner;

/**
 * More information about question access HackerRank
 * https://www.hackerrank.com/challenges/compare-the-triplets
 * 
 *
 */
public class CompareTheTriplets {
	static int aliceScore = 0;
	static int bobScore = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String input = scanner.nextLine();
		String[] aliceProblem = input.split(" ");

		input = scanner.nextLine();
		String[] bobProblem = input.split(" ");

		scanner.close();

		checkToWinner(aliceProblem, bobProblem);
	}

	private static void checkToWinner(String[] aliceProblem, String[] bobProblem) {

		for (int i = 0; i < aliceProblem.length; i++) {

			if (Integer.parseInt(aliceProblem[i]) > Integer
					.parseInt(bobProblem[i])) {
				aliceScore++;
			} else if (Integer.parseInt(aliceProblem[i]) < Integer
					.parseInt(bobProblem[i])) {
				bobScore++;
			} else if (Integer.parseInt(aliceProblem[i]) == Integer
					.parseInt(bobProblem[i])) {
			}

		}
		System.out.println(aliceScore + " " + bobScore);
	}
}
