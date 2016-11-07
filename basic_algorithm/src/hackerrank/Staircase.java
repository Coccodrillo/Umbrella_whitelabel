package hackerrank;
import java.util.Scanner;

/**
 * More information about question access HackerRank
 * https://www.hackerrank.com/challenges/staircase
 * 
 *
 */
public class Staircase {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		in.close();
		String staircase = "";
		for (int i = 1; i <= n; i++) {
			staircase += addSteps(i, n);

		}
		System.out.println(staircase);
	}

	public static String addSteps(int howSteps, int n) {
		String step = "";
		String newLine = "";
		for (int i = 0; i < howSteps; i++) {
			step += "#";
			newLine = addSpace(n - howSteps) + step;
		}
		return newLine + "\n";
	}

	public static String addSpace(int qtdSpace) {
		String aux = "";

		for (int i = 0; i < qtdSpace; i++) {
			aux += " ";
		}

		return aux;
	}
}
