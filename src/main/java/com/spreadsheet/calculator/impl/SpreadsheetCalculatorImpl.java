package com.spreadsheet.calculator.impl;

import java.util.Scanner;
import java.util.Stack;

import org.apache.commons.lang3.math.NumberUtils;

import com.spreadsheet.calculator.Operators;
import com.spreadsheet.calculator.SpreadsheetCalculator;
import com.spreadsheet.calculator.SpreadsheetCell;
import com.spreadsheet.calculator.exceptions.CyclicDependencyException;
import com.spreadsheet.calculator.utils.Utils;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Extends and implements {@link SpreadsheetCalculator}
 * 
 * @author asif
 *
 */
@Slf4j
@NoArgsConstructor
public class SpreadsheetCalculatorImpl extends SpreadsheetCalculator {

	private static final String NOT_EVALUATED = "NE";

	private static final String CYCLIC_DEPENDENCY = "CD";

	@Override
	public void populateSheet(Scanner inputScanner) {
		this.sizeY = inputScanner.nextInt();
		this.sizeX = inputScanner.nextInt();
		inputScanner.nextLine();
		this.spreadsheetCells = new SpreadsheetCell[this.sizeX][this.sizeY];

		for (int row = 0; row < this.sizeX; row++) {
			for (int col = 0; col < this.sizeY; col++) {
				String data = inputScanner.nextLine().trim().toUpperCase();
				SpreadsheetCell spreadsheetCell = new SpreadsheetCellImpl(row, col, data);
				spreadsheetCells[row][col] = spreadsheetCell;
			}
		}
	}

	@Override
	public void evaluate() {
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				try {
					this.evaluateCell(this.spreadsheetCells[i][j]);
				} catch (CyclicDependencyException e) {
					log.error(e.getMessage());
				}
			}
		}
	}

	private SpreadsheetCell getCell(final String string) {
		try {
			int row = (int) string.charAt(0) % 65;
			int col = Integer.parseInt(string.substring(1, string.length())) - 1;
			return spreadsheetCells[row][col];
		} catch (NumberFormatException e) {
			log.error("Format Error occured for cell content -> " + string);
			System.exit(1);
		}
		return null;

	}

	/**
	 * Evaluates value of cell using postfix operators stack.
	 * 
	 * @param sheetCell
	 * @return value of cell
	 * @throws CyclicDependencyException
	 */
	private Double evaluateCell(final SpreadsheetCell sheetCell) throws CyclicDependencyException {
		if (sheetCell.isEvaluated()) {
			return sheetCell.getValue();
		} else if (!sheetCell.isCurrentlyEvaluating()) {
			sheetCell.setCurrentlyEvaluating(true);
			
			String[] elements = Utils.parseContent(sheetCell.getContent());
			// postfix expression evaluation using stack
			Stack<Double> operands = new Stack<Double>();
			for (int i = 0; i < elements.length; i++) {
				if (Operators.isValidOperator(elements[i])) {
					double op1, op2;
					switch (Operators.get(elements[i])) {
					case INCREMENT:
						op1 = operands.pop();
						operands.push(++op1);
						break;
					case DECREMENT:
						op1 = operands.pop();
						operands.push(--op1);
						break;
					case ADDITION:
						op1 = operands.pop();
						op2 = operands.pop();
						operands.push(op2 + op1);
						break;
					case SUBTRACTION:
						op1 = operands.pop();
						op2 = operands.pop();
						operands.push(op2 - op1);
						break;
					case MULTIPLICATION:
						op1 = operands.pop();
						op2 = operands.pop();
						operands.push(op2 * op1);
						break;
					case DIVISION:
						op1 = operands.pop();
						op2 = operands.pop();
						operands.push(op2 / op1);
						break;
					}
				} else if (NumberUtils.isParsable(elements[i])) {
					operands.push(Double.parseDouble(elements[i]));
				} else {
					SpreadsheetCell dependentCell = getCell(elements[i]);
					operands.push(evaluateCell(dependentCell));
				}
			}
			sheetCell.setValue(operands.pop());
			sheetCell.setEvaluated(true);
		} else {
			throw new CyclicDependencyException(
					"Cyclic dependency occured at " + sheetCell.getRow() + "," + sheetCell.getCol());
		}

		return sheetCell.getValue();
	}

	@Override
	public void printSheet() {
		System.out.println("Tags -> NE : Not evaluated; CD: Circular Dependency");
		System.out.println("=============================================");
		System.out.println("Result:");
		boolean isCyclicDependencyFound = false;
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if (!this.spreadsheetCells[i][j].isEvaluated()) {
					String out = NOT_EVALUATED;
					if (this.spreadsheetCells[i][j].isCurrentlyEvaluating()) {
						out += ", " + CYCLIC_DEPENDENCY;
						isCyclicDependencyFound = true;
					}
					System.out.printf("%s%n", out);
				} else {
					System.out.printf("%.5f%n", this.spreadsheetCells[i][j].getValue());
				}
			}
		}
		if (isCyclicDependencyFound) {
			System.exit(-1);
		}
	}
}
