package com.spreadsheet.calculator.exceptions;

/**
 * Exception in case of a cycle found while evaluating cells.
 * 
 * @author asif
 *
 */
public class CyclicDependencyException extends Exception {
	/**
	 * Used in serialization/deserialization to validate classes.
	 */
	private static final long serialVersionUID = -973300931327155228L;

	public CyclicDependencyException() {
	}

	public CyclicDependencyException(String message) {
		super(message);
	}
}
