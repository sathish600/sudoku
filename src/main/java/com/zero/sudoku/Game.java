package com.zero.sudoku;

import com.zero.sudoku.model.Cell;
import com.zero.sudoku.model.Color;

public class Game {

	private static final int NUMBER_OF_ROWS = 9;

	private static final int NUMBER_OF_COLUMNS = 9;

	private static final int[][] DEFAULT_BOARD_START = new int[][] {
			{ 0, 2, 0, 6, 0, 8, 0, 0, 0 }, { 5, 8, 0, 0, 0, 9, 7, 0, 0 },
			{ 0, 0, 0, 0, 4, 0, 0, 0, 0 }, { 3, 7, 0, 0, 0, 0, 5, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 0, 4 }, { 0, 0, 8, 0, 0, 0, 0, 1, 3 },
			{ 0, 0, 0, 0, 2, 0, 0, 0, 0 }, { 0, 0, 9, 8, 0, 0, 0, 3, 6 },
			{ 0, 0, 0, 3, 0, 6, 0, 9, 0 } };

	private Cell[][] board = new Cell[NUMBER_OF_ROWS][NUMBER_OF_ROWS];

	/**
	 * Initialize the game with all cell set to black
	 */
	public Game() {
		for (int row = 0; row < NUMBER_OF_ROWS; row++) {
			for (int col = 0; col < NUMBER_OF_COLUMNS; col++) {
				board[row][col] = new Cell();
				board[row][col].setCellColor(Color.BLACK);
			}
		}
	}

	/**
	 * Initialize the board with default inputs
	 */
	public void initialize() {
		populateValues(DEFAULT_BOARD_START);
	}

	/**
	 * initialize the board with custom values after validation
	 * 
	 * @param startingValues
	 * @throws IllegalArgumentException
	 */
	public void initialize(int[][] startingValues)
			throws IllegalArgumentException {
		validateInput(startingValues);
		populateValues(startingValues);
	}

	/**
	 * Try to inser the value at the given position and mark with the color provided.
	 * If the value is not allowed at the given position, its inserted with Color.RED
	 * @param row
	 * @param col
	 * @param value
	 * @param color
	 */
	public void tryAndInsert(int row, int col, int value, Color color) {
		int result = verifyInput(row, col, value);
		
		if (result == 1) {
			board[row][col].setCellColor(color);
			board[row][col].setValue(value);
		} else if(result == -1){
			board[row][col].setCellColor(Color.RED);
			board[row][col].setValue(value);
		}
	}

	/**
	 * Verifies the input value is not present in the same row/column and also
	 * not present in its 3/3 matrix
	 * 
	 * @param row
	 * @param col
	 * @param value
	 * @return
	 */
	public int verifyInput(int row, int col, int value) {

		for (int r = 0; r < NUMBER_OF_ROWS; r++) {
			if (board[r][col].getValue() == value) {
				return -1;
			}
		}

		for (int c = 0; c < NUMBER_OF_COLUMNS; c++) {
			if (board[row][c].getValue() == value) {
				return -1;
			}
		}

		int rowStart = row / 3 * 3;
		int colStart = col / 3 * 3;
		for (int i = rowStart; i < rowStart + 3; i++) {
			for (int j = colStart; j < colStart + 3; j++) {
				if (board[i][j].getValue() == value) {
					return -1;
				}
			}
		}

		return 1;
	}
	
	public Cell getCell(int row, int col) {
		return board[row][col];
	}

	/**
	 * method to print matrix with/without color
	 * @param withColor
	 */
	public void printMatrix(boolean withColor) {
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
		for (int row = 0; row < NUMBER_OF_ROWS; row++) {
			for (int col = 0; col < NUMBER_OF_COLUMNS; col++) {
				if (withColor) {
					System.out.printf(" %s %s", "| ",
							board[row][col]);
				} else {
					System.out.printf(" %s %s", "| ",
							board[row][col].getValue());
				}
			}
			System.out.println("  |");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
		}
	}

	private void populateValues(int[][] startingValues) {
		for (int row = 0; row < NUMBER_OF_ROWS; row++) {
			for (int col = 0; col < NUMBER_OF_COLUMNS; col++) {
				board[row][col] = new Cell();
				if (verifyInput(row, col, startingValues[row][col]) == 1) {
					tryAndInsert(row, col, startingValues[row][col], Color.GREEN);
				} else {
					board[row][col].setCellColor(Color.BLACK);
				}
			}
		}
	}

	private void validateInput(int[][] input) throws IllegalArgumentException {
		if (input == null) {
			throw new IllegalArgumentException("input array cannot be null");
		}

		if (input.length != NUMBER_OF_ROWS) {
			throw new IllegalArgumentException("the stating input should have "
					+ NUMBER_OF_ROWS + " rows");
		}

		if (input[0].length != NUMBER_OF_COLUMNS) {
			throw new IllegalArgumentException("the stating input should have "
					+ NUMBER_OF_COLUMNS + " columns for each row");
		}

	}
}
