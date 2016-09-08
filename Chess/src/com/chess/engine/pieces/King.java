package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece.PieceType;
import com.google.common.collect.ImmutableList;

/**
 * Chess
 * @author Winnie Aug 28, 2016
 * King.java
 */
public class King extends Piece {

	private final static int[] CANDIDATE_MOVE_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};
	
	/**
	 * @param piecePosition
	 * @param pieceAlliance
	 */
	public King(final Alliance pieceAlliance, final int piecePosition) {
		super(PieceType.KING, piecePosition, pieceAlliance);
	}


	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		final List<Move> legalMoves = new ArrayList<>();
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){
			final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
			
			//Edge cases
			if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) || 
					isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)){
				continue;
			}
			
			if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
				if(!candidateDestinationTile.isTileOccupied()){
					legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate)); 			
				}else{ //if it is on an occupied tile, get the piece on that tile and alliance (B or W)
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance(); //get alliance of piece that is occupying tile
					if(this.pieceAlliance != pieceAlliance){ //if alliance is not equal to current knight's alliance
						legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
					}
				}
			}
			
		}
		
		return ImmutableList.copyOf(legalMoves);
	}
	
	
	@Override
	public String toString(){
		return PieceType.KING.toString();
	}
	
	//Edge case methods
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == -1 || candidateOffset == 7); 
	}
	
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);
	}
	

}
