package com.spreadsheet.calculator;

import java.util.Scanner;

import lombok.Getter;

/**
 * Represents spread sheet and has functionalities for calculation of each cell.
 * 
 * @author asif
 *
 */
public abstract class SpreadsheetCalculator {

	protected SpreadsheetCell[][] spreadsheetCells;

	@Getter
	protected int sizeX;

	@Getter
	protected int sizeY;

	/**
	 * Populates the sheet.
	 * 
	 * @param inputScanner
	 */
	public abstract void populateSheet(Scanner inputScanner);
	
	/**
	 * Evaluates all cells of sheet.
	 */
	public abstract void evaluate();
	
	/**
	 * Prints value of each cell in the sheet.
	 */
	public abstract void printSheet();

}