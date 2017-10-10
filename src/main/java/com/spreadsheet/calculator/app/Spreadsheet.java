package com.spreadsheet.calculator.app;

import java.util.Scanner;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.spreadsheet.calculator.SpreadsheetCalculator;
import com.spreadsheet.calculator.configuration.ConfigurationManager;

import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Main application class to run spreadsheet coding test.
 * 
 * @author asif
 *
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @_(@Inject))
public class Spreadsheet {

	private final SpreadsheetCalculator spreadsheetCalculator;

	public void proccessSheetTest() {
		try {
			@Cleanup
			Scanner inputScanner = null;
			inputScanner = new Scanner(System.in);

			this.spreadsheetCalculator.populateSheet(inputScanner);
			this.spreadsheetCalculator.evaluate();
			this.spreadsheetCalculator.printSheet();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new ConfigurationManager());
		Spreadsheet app = injector.getInstance(Spreadsheet.class);
		app.proccessSheetTest();
	}
}
