package com.spreadsheet.calculator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a spreadsheet cell.
 * 
 * @author asif
 *
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class SpreadsheetCell {

	protected int row;
	protected int col;
	protected String content;
	protected boolean currentlyEvaluating;
	protected boolean evaluated;
	protected double value;

	public SpreadsheetCell(final int row, final int col, final String content) {
		this.row = row;
		this.col = col;
		this.content = content;
	}

}
