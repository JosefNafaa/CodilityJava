package clockDigitCounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

	public static void main(String[] args) {

		System.out.println(solution(1, 8, 3, 2));
		System.out.println(solution(2, 3, 3, 2));
		System.out.println(solution(6, 2, 4, 7));
	}

	public static int solution(int A, int B, int C, int D) {
		if (!validDigits(A, B, C, D))
			return 0;
		else {
			String number = String.valueOf(A) + String.valueOf(B) + String.valueOf(C) + String.valueOf(D);
			Set<String> permutations = permutations(0, number);

			return countValidCLock(permutations);
		}
	}

	public static int countValidCLock(Set<String> clocks) {

		int count = 0;
		for (String string : clocks) {
			StringBuffer str = new StringBuffer(string);
			str.insert(2, ":");

			if (isValidTime(str.toString())) {
				count++;
			}
		}
		return count;
	}

	public static boolean isValidTime(String time) {

		// Regex to check valid time in 24-hour format.
		String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the time is empty
		// return false
		if (time == null) {
			return false;
		}

		// Pattern class contains matcher() method
		// to find matching between given time
		// and regular expression.
		Matcher m = p.matcher(time);

		// Return if the time
		// matched the ReGex
		return m.matches();
	}

	static Set<String> permutations(int fixed, String s) {
		Set<String> strings = new TreeSet<>();
		char[] chr = s.toCharArray();
		if (fixed == s.length())
			;
		for (int i = fixed; i < s.length(); i++) {
			strings.add(s);
			char c = chr[i];
			chr[i] = chr[fixed];
			chr[fixed] = c;
			strings.addAll(permutations(fixed + 1, new String(chr)));
		}
		return strings;
	}

	public static boolean validDigits(int A, int B, int C, int D) {
		List<Integer> digits = new ArrayList<>();
		digits.add(A);
		digits.add(B);
		digits.add(C);
		digits.add(D);
		Collections.sort(digits);
		int a = digits.get(0);
		int b = digits.get(1);
		int c = digits.get(2);
		if (a == 0 || a == 1) {
			if (b >= 6)
				return false;
		} else if (a == 2) {
			if (b >= 4)
				return false;
			else if (c >= 6)
				return false;

		}
		if (a > 2) {
			return false;
		}

		return true;
	}
}
