package ua.nure.korkh.SummaryTask4.functional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class matcher for analyze String
 * 
 * @author Korkh
 * 
 */
public class ValidateRegular {
	private static Pattern patern;
	private static Matcher matcher;

	public static boolean regular(String s, String pattern) {
		patern = Pattern.compile(pattern);
		matcher = patern.matcher(s);
		return matcher.matches();

	}
}
