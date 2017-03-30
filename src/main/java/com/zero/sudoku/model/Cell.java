package com.zero.sudoku.model;

public class Cell {

	private Color cellColor;
	private int value;
	
	public void setCellColor(Color cellColor) {
		this.cellColor = cellColor;
	}
	public Color getCellColor() {
		return cellColor;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return String.format("(%s : %s)", value, cellColor);
	}
}
