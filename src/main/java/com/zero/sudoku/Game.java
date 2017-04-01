package com.zero.sudoku;

import com.zero.sudoku.model.Cell;

public class Game {

	private static final int NUMBER_OF_ROWS = 9;

	private static final int NUMBER_OF_COLUMNS = 9;

	public static enum Color {
		BLACK, RED, GREEN, WHITE;
	};
	
	public static final int[][] DEFAULT_BOARD_START = new int[][] {
			{ 0, 2, 0, 6, 0, 8, 0, 0, 0 }, { 5, 8, 0, 0, 0, 9, 7, 0, 0 },
			{ 0, 0, 0, 0, 4, 0, 0, 0, 0 }, { 3, 7, 0, 0, 0, 0, 5, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 0, 4 }, { 0, 0, 8, 0, 0, 0, 0, 1, 3 },
			{ 0, 0, 0, 0, 2, 0, 0, 0, 0 }, { 0, 0, 9, 8, 0, 0, 0, 3, 6 },
			{ 0, 0, 0, 3, 0, 6, 0, 9, 0 } };

	private Cell[][] board = new Cell[NUMBER_OF_ROWS][NUMBER_OF_ROWS];

	/**
	 * Initialize the game with all cell set to black
	 */
	public Game(int[][] startingValues) {
		
		for (int row = 0; row < NUMBER_OF_ROWS; row++) {
			for (int col = 0; col < NUMBER_OF_COLUMNS; col++) {
				board[row][col] = new Cell();
				if (isValidPlacement(row, col, startingValues[row][col])) {
					placeItem(row, col, startingValues[row][col], Color.GREEN);
				} else {
					board[row][col].setCellColor(Color.BLACK);
				}
			}
		}
	}

	/**
	 * Try to insert the value at the given position and mark with the color provided.
	 * If the value is not allowed at the given position, its inserted with Color.RED
	 */
	public void placeItem(int row, int col, int value, Color color) {
		
		if (isValidPlacement(row, col, value)) {
			board[row][col].setCellColor(color);
			board[row][col].setValue(value);
		} else {
			board[row][col].setCellColor(Color.RED);
			board[row][col].setValue(value);
		}
	}

	/**
	 * Verifies the input value is not present in the same row/column and also
	 * not present in its 3/3 matrix for the corresponding position
	 */
	public boolean isValidPlacement(int row, int col, int value) {

		for (int r = 0; r < NUMBER_OF_ROWS; r++) {
			if (board[r][col] != null && board[r][col].getValue() == value) {
				return false;
			}
		}

		for (int c = 0; c < NUMBER_OF_COLUMNS; c++) {
			if (board[row][c] != null && board[row][c].getValue() == value) {
				return false;
			}
		}

		int rowStart = row / 3 * 3;
		int colStart = col / 3 * 3;
		for (int i = rowStart; i < rowStart + 3; i++) {
			for (int j = colStart; j < colStart + 3; j++) {
				if (board[i][j] != null && board[i][j].getValue() == value) {
					return false;
				}
			}
		}

		return true;
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
}
