package com.zero.sudoku;

import org.junit.Assert;
import org.junit.Test;

import com.zero.sudoku.Game.Color;

public class GameTest {

	private static final int[][] CUSTOM_INPUT = new int[][] {
		{ 0, 2, 0, 6, 0, 8, 0, 0, 0 }, { 5, 8, 0, 0, 0, 9, 7, 0, 0 },
		{ 0, 0, 0, 0, 4, 0, 0, 0, 0 }, { 3, 7, 0, 0, 0, 0, 5, 0, 0 },
		{ 6, 0, 0, 0, 0, 0, 0, 0, 4 }, { 0, 0, 8, 0, 0, 0, 0, 1, 3 },
		{ 0, 0, 0, 0, 2, 0, 0, 0, 0 }, { 0, 0, 9, 8, 0, 0, 0, 3, 6 },
		{ 0, 0, 0, 3, 0, 4, 0, 9, 0 } };
	
	@Test
	public void testGameInit() throws Exception {
		Game game = new Game(Game.DEFAULT_BOARD_START);

		Assert.assertEquals(9, game.getCell(8, 7).getValue());
	}

	@Test
	public void testGameCustomInit() throws Exception {
		Game game = new Game(CUSTOM_INPUT);

		Assert.assertEquals(4, game.getCell(8, 5).getValue());
	}
	
	@Test
	public void testVerifyInputSuccess() throws Exception {
		Game game = new Game(Game.DEFAULT_BOARD_START);
		
		game.printMatrix(true);
		Assert.assertTrue(game.isValidPlacement(0, 2, 3));
	}

	@Test
	public void testVerifyInputFailureRowMatch() throws Exception {
		Game game = new Game(Game.DEFAULT_BOARD_START);
		game.placeItem(1, 2, 5, Color.WHITE);
		
		Assert.assertEquals(game.getCell(1, 2).getCellColor(), Color.RED);
	}

	@Test
	public void testVerifyInputFailureColumnMatch() throws Exception {
		Game game = new Game(Game.DEFAULT_BOARD_START);
		
		Assert.assertFalse(game.isValidPlacement(6, 1, 7));
	}

	@Test
	public void testVerifyInputFailureSubMatrixMatch() throws Exception {
		Game game = new Game(Game.DEFAULT_BOARD_START);
		
		Assert.assertFalse(game.isValidPlacement(6, 6, 9));
	}

}
