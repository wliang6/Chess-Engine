package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

/**
 * Chess
 * @author Winnie Aug 25, 2016
 * Move.java
 */
public abstract class Move {


	final Board board;
	final Piece movedPiece;
	final int destinationCoordinate;
	
	//Constructors
	private Move(final Board board, final Piece movedPiece, final int destinationCoordinate){
		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationCoordinate = destinationCoordinate;
	}
	
	/**
	 * Purpose: Returns the destination coordinate
	 * int
	 */
	public int getDestinationCoordinate() {
		return this.destinationCoordinate;
	}
	
	//declaring some subclasses
	public static final class MajorMove extends Move{
		public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate){
			super(board, movedPiece, destinationCoordinate);
		}
	}
	
	public static final class AttackMove extends Move{
		final Piece attackedPiece;
		public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
			super(board, movedPiece, destinationCoordinate);
			this.attackedPiece = attackedPiece;
		}
		
	}

	/**
	 * Purpose: 
	 * Board
	 */
	public Board execute() {
		return null;
	}

	
}
