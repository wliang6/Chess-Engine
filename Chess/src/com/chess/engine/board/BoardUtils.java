package com.chess.engine.board;

/**
 * Chess
 * @author Winnie Aug 26, 2016
 * BoardUtils.java
 */
public class BoardUtils {

	public static final boolean[] FIRST_COLUMN = initColumn(0);
	public static final boolean[] SECOND_COLUMN = initColumn(1);
	public static final boolean[] SEVENTH_COLUMN = initColumn(6);
	public static final boolean[] EIGHTH_COLUMN = initColumn(7);

	
	public static final boolean[] SECOND_ROW = initRow(1);
	public static final boolean[] SEVENTH_ROW = initRow(6);
	
	public static final int NUM_TILES = 64;
	public static final int NUM_TILES_PER_ROW = 8;
	
	//Due to the methods being static in this class, you DON'T need to instantiate the class object before utilizing these methods.
	private BoardUtils(){ //private BoardUtils constructor
		throw new RuntimeException("Cannot instantiate me! (BoardUtils).");
	} 
	

	/**
	 * Purpose: Sets the whole n-th column to true while leaving all the other columns false
	 * boolean []
	 */
	private static boolean[] initColumn(int columnNumber) {
		final boolean[] column = new boolean [NUM_TILES];
		do{
			column[columnNumber] = true; //set this column to be TRUE
			columnNumber += NUM_TILES_PER_ROW; //add 8 to the current tile
			//if input is 0, 0+8 = 8, 8+8 = 16, 16+8 = 24, 24+8 = 32, etc. <-- your tiles that are within your n-th column
		} while(columnNumber < NUM_TILES);
		return column;
	}

	/**
	 * Purpose: 
	 * boolean []
	 */
	private static boolean[] initRow(int i) {
		final boolean[] row = new boolean [NUM_TILES];
		
		return null;
	}
	
	
	/**
	 * Purpose: Check to see if the coordinate is valid and within the bounds of the chessboard (tiles 0-63)
	 * Useful bool method for all individual pieces
	 * boolean
	 */
	public static boolean isValidTileCoordinate(final int coordinate) {
		return coordinate >= 0 && coordinate < 64;
	}

}
