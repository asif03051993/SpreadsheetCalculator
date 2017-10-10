package com.spreadsheet.calculator.impl;

import com.spreadsheet.calculator.SpreadsheetCalculator;
import com.spreadsheet.calculator.SpreadsheetCell;

import lombok.NoArgsConstructor;

/**
 * Extends and implements {@link SpreadsheetCell}
 * 
 * @author asif
 *
 */
@NoArgsConstructor()
public class SpreadsheetCellImpl extends SpreadsheetCell {

	public SpreadsheetCellImpl(final int row, final int col, final String content) throws RuntimeException {
		super(row, col, content);
	}

}
