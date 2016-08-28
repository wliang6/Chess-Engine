package com.chess.engine.pieces;

/**
 * Chess
 * @author Winnie Aug 25, 2016
 * Alliance.java
 * Alliance gives the directionality and the color of the piece.
 */
public enum Alliance {
	WHITE 
	{
		@Override
		public int getDirection() { 
			//white pieces head towards the negative direction of the board therefore -1 (towards the top of the board)
			return -1;
		}

		@Override
		public boolean isBlack() {
			return false;
		}

		@Override
		public boolean isWhite() {
			return true;
		}
	},	
	
	BLACK
	{
		@Override
		public int getDirection() { 
			//black pieces head towards the positive direction of the board therefore 1 (towards the bottom of the board)
			return 1;
		}

		@Override
		public boolean isBlack() {
			return true;
		}

		@Override
		public boolean isWhite() {
			return false;
		}
	};
	


	
	
	//Method declared that returns directionality
	public abstract int getDirection();
	public abstract boolean isBlack();
	public abstract boolean isWhite();
	
}


