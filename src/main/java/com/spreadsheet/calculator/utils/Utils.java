package com.spreadsheet.calculator.utils;

import java.util.regex.Pattern;

/**
 * Utility class with helper methods.
 * 
 * @author asif
 *
 */
public class Utils {

	/**
	 * Regex represents pattern with one or more spaces.
	 */
	public static final Pattern spaceDelimiterRegex = Pattern.compile("\\s+");

	public static String[] parseContent(final String string) {
		return spaceDelimiterRegex.split(string);
	}
}
