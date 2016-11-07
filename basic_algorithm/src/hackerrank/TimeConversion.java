package hackerrank;
import java.util.Scanner;

/**
 * More information about question access HackerRank
 * https://www.hackerrank.com/challenges/time-conversion
 * 
 *
 */
public class TimeConversion {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		String time = in.next();
		String hour = time.substring(0, 2);
		String minute = time.substring(2, 5);
		String second = time.substring(5, 8);
		String formatTime = time.substring(8, 10);

		if (formatTime.equals("PM")) {
			if (Integer.parseInt(hour) == 12)
				System.out.println(Integer.parseInt(hour) + minute + second);
			else
				System.out.println(Integer.parseInt(hour) + 12 + minute
						+ second);
		} else {
			if (formatTime.equals("AM") && Integer.parseInt(hour) == 12)
				hour = "00";

			System.out.println(hour + minute + second);
		}
	}
}
