package com.spreadsheet.calculator.configuration;

import com.google.inject.AbstractModule;
import com.spreadsheet.calculator.SpreadsheetCalculator;
import com.spreadsheet.calculator.SpreadsheetCell;
import com.spreadsheet.calculator.impl.SpreadsheetCalculatorImpl;
import com.spreadsheet.calculator.impl.SpreadsheetCellImpl;

/**
 * Guice bindings.
 * 
 * @author asif
 *
 */
public class ConfigurationManager extends AbstractModule {

	@Override
	protected void configure() {
		bind(SpreadsheetCell.class).to(SpreadsheetCellImpl.class);
		bind(SpreadsheetCalculator.class).to(SpreadsheetCalculatorImpl.class);
	}

}
