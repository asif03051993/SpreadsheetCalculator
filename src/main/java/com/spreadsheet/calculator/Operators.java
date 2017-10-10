package com.spreadsheet.calculator;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents different types of operators.
 * 
 * @author asif
 *
 */
public enum Operators {

	INCREMENT("++"), DECREMENT("--"), ADDITION("+"), SUBTRACTION("-"), MULTIPLICATION("*"), DIVISION("/");

	private final String operator;

	private Operators(final String operator) {
		this.operator = operator;
	}

	private static final Map<String, Operators> operatorsMap = new HashMap<String, Operators>();

	static {
		for (Operators operator : Operators.values())
			operatorsMap.put(operator.getOperator(), operator);
	}

	public static Operators get(String operator) {
		return operatorsMap.get(operator);
	}

	public static boolean isValidOperator(String operator) {
		return get(operator) != null;
	}

	public String getOperator() {
		return operator;
	}
}