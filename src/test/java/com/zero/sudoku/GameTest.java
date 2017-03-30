package com.zero.sudoku;

import org.junit.Assert;
import org.junit.Test;

import com.zero.sudoku.model.Color;

public class GameTest {

	private static final int[][] CUSTOM_INPUT = new int[][] {
		{ 0, 2, 0, 6, 0, 8, 0, 0, 0 }, { 5, 8, 0, 0, 0, 9, 7, 0, 0 },
		{ 0, 0, 0, 0, 4, 0, 0, 0, 0 }, { 3, 7, 0, 0, 0, 0, 5, 0, 0 },
		{ 6, 0, 0, 0, 0, 0, 0, 0, 4 }, { 0, 0, 8, 0, 0, 0, 0, 1, 3 },
		{ 0, 0, 0, 0, 2, 0, 0, 0, 0 }, { 0, 0, 9, 8, 0, 0, 0, 3, 6 },
		{ 0, 0, 0, 3, 0, 4, 0, 9, 0 } };
	@Test
	public void testGameInit() throws Exception {
		Game game = new Game();
		game.initialize();

		Assert.assertEquals(9, game.getCell(8, 7).getValue());
	}

	@Test
	public void testGameCustomInit() throws Exception {
		Game game = new Game();
		game.initialize(CUSTOM_INPUT);

		Assert.assertEquals(4, game.getCell(8, 5).getValue());
	}
	
	@Test
	public void testVerifyInputSuccess() throws Exception {
		Game game = new Game();
		game.initialize();
		game.printMatrix(true);
		Assert.assertEquals(1, game.verifyInput(0, 2, 3));
	}

	@Test
	public void testVerifyInputFailureRowMatch() throws Exception {
		Game game = new Game();
		game.initialize();
		game.tryAndInsert(1, 2, 5, Color.WHITE);
		
		Assert.assertEquals(game.getCell(1, 2).getCellColor(), Color.RED);
	}

	@Test
	public void testVerifyInputFailureColumnMatch() throws Exception {
		Game game = new Game();
		game.initialize();

		Assert.assertEquals(-1, game.verifyInput(6, 1, 7));
	}

	@Test
	public void testVerifyInputFailureSubMatrixMatch() throws Exception {
		Game game = new Game();
		game.initialize();

		Assert.assertEquals(-1, game.verifyInput(6, 6, 9));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testVerifyNullInputFailure() {
		Game game = new Game();
		game.initialize(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testVerifyInvalidInputRowFailure() {
		Game game = new Game();
		game.initialize(new int[4][9]);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testVerifyInvalidInputColumnFailure() {
		Game game = new Game();
		game.initialize(new int[9][4]);
	}
}
