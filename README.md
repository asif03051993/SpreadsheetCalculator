# SpreadsheetCalculator
Spreadsheet Calculator which solves RPN with column references in spreadsheet

This was a part of coding assignment with some company Y.
The spreadsheet input is defined as follows:
• Line 1: two integers, defining the width and height of the spreadsheet  n, m)
• n*m lines each containing an expression which is the value of the corresponding cell 
 cells enumerated in the order A1, A2, A<n>, B1, ...)
 Your program must output its data in the same format, but each cell should be reduced to a 
 single floating8point value.
 For example, we would expect the following expect to produce the indicated output: 
 Input              
 3 2
 A2
 4 5 *
 A1
 A1 B2 / 2 +
 3
 39 B1 B2 * /
 
 Expected Output
 3 2
 20.00000
 20.00000
 20.00000
 8.66667
 3.00000
 1.50000

To set up in eclipse or any other ide, please import this project

Assuming you are running these commands from the root directory of repository.
### To Run Jar:
cat spreadsheet.txt | java -jar target/Calculator-0.0.1-SNAPSHOT-jar-with-dependencies.jar

### To Create Jar (This will create jar file inside target folder):
mvn package
