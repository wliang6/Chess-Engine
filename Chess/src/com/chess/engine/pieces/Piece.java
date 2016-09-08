package com.chess.engine.pieces;

import java.util.Collection;

import com.chess.engine.pieces.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

/**
 * Chess
 * @author Winnie Aug 16, 2016
 * Piece.java
 */
public abstract class Piece {

	protected final PieceType pieceType;
	//Every piece has a piece position. There is a tile coordinate occupied on.
	protected final int piecePosition;
	//Piece can be white or black.
	protected final Alliance pieceAlliance; // you can create a new Alliance class but we will use an enum instead
	protected final boolean isFirstMove;
	
	Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance){
		this.pieceType = pieceType;
		this.pieceAlliance = pieceAlliance;
		this.piecePosition = piecePosition;
		this.isFirstMove = false; //TODO more work
	}
	
	public int getPiecePosition(){
		return this.piecePosition;
	}
	
	public Alliance getPieceAlliance(){
		return this.pieceAlliance;
	}
	
	public boolean isFirstMove(){
		return this.isFirstMove;
	}
	
	public PieceType getPieceType(){
		return this.pieceType;
	}
	
	//All the pieces created would override this and defines its own moves
	//You can use a list (ordered and get values at particular value), set(cannot have duplicate entries; unordered), or Collection 
	public abstract Collection<Move> calculateLegalMoves(final Board board);
	

	public enum PieceType{
		PAWN("P") {
			@Override
			public boolean isKing() {
				return false;
			}
		}, 
		KNIGHT("N") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		BISHOP("B") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		ROOK("R") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		QUEEN("Q") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		KING("K") {
			@Override
			public boolean isKing() {
				return true;
			}
		};
		
		private String pieceName;
		PieceType(final String pieceName){
			this.pieceName = pieceName;
		}
		
		@Override
		public String toString(){
			return this.pieceName;
		}

		/**
		 * Purpose: Checks if piece is King. Returns true!
		 * boolean
		 */
		public abstract boolean isKing();
		
	}
	
}
