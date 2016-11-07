package hackerrank;
import java.util.Scanner;

/**
 * More information about question access HackerRank
 * https://www.hackerrank.com/challenges/diagonal-difference
 * 
 *
 */
public class DiagonalDifference {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String sizeStr = scanner.nextLine();
		int size = Integer.parseInt(sizeStr);
		size += 1;
		int primary = 0;
		int secundary = 0;

		int[][] matrix = new int[size][size];

		for (int i = 0; i < size; i++) {

			String input = scanner.nextLine();
			String[] inputs = input.split(" ");

			for (int j = 0; j < size; j++) {
				if ((!input.isEmpty()) && i == j) {
					primary += Integer.parseInt(inputs[i]);
					System.out.println("primaria" + inputs[i]);
				} else if ((!input.isEmpty()) && (i + j) == (size - 1)) {
					secundary += Integer.parseInt(inputs[size - 1]);
					System.out.println(secundary);
				}

			}

		}

		System.out.println(primary + secundary);
	}
}
