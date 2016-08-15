package hackerrank;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * More information about question access HackerRank
 * https://www.hackerrank.com/challenges/plus-minus
 * 
 *
 */
public class PlusMinus {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int size;
		double positiveNumber = 0;
		double negativeNumber = 0;
		double zero = 0;
		size = Integer.parseInt(in.nextLine());
		String[] line = in.nextLine().split(" ");

		for (int i = 0; i < size; i++) {
			if (Integer.parseInt(line[i]) < 0) {
				negativeNumber++;
			} else if (Integer.parseInt(line[i]) > 0) {
				positiveNumber++;
			} else {
				zero++;
			}
		}
		System.out.println(new DecimalFormat("#.000000").format(positiveNumber/ size));
		System.out.println(new DecimalFormat("#.000000").format(negativeNumber/ size));
		System.out.println(new DecimalFormat("#.000000").format(zero / size));
	}
}
