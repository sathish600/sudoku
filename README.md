# Sudoku

This project implements a 9X9 Sudoku game with the following features\

	1. Initialise the Sudoku game with default/custom values
	2. Insert value at a given position (row/col)
	3. Search value at a given position (row/col)

## Tools/Technologies
	Java
	Maven
	
## Build/Run

  To run the sample applicaiton (GameApp), run the following command
	
	mvnw exec:java -Dexec.mainClass="com.example.Main"

	Three options will be provided
		1 - Insert
		2 - Search
		3 - Quit
	
  To Insert, input the values in the format given (row:col:value) with row/col starting from 0
	
  To Search, input the row/column (row:col) and the value for the row/column will be shown along with color.
	
  To run all the Unit tests, run the following command
		
	mvnw test
