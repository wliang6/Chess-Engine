package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.pieces.Piece.PieceType;
import com.google.common.collect.ImmutableList;

/**
 * Chess
 * @author Winnie Aug 27, 2016
 * Pawn.java
 */
public class Pawn extends Piece {

	private final static int[] CANDIDATE_MOVE_COORDINATES = {8, 16, 7, 9}; //8 only works for black pawn (top portion of board) // -8 works for the white pawn (bottom portion of the board)
	//16 is the jump you make during pawn's first move
	/**
	 * @param pieceAlliance
	 * @param piecePosition
	 */
	public Pawn(final Alliance pieceAlliance, final int piecePosition) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		final List<Move> legalMoves = new ArrayList<>();
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){
			final int candidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * currentCandidateOffset); //This handles the case of black or white side of the board (-1 or 1 directions)
			if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
				continue;
			}
			if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
				//TODO more work to do here!!
				legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
				//Checks condition that this is the pawn's FIRST MOVE. In addition, the black piece belongs to the 2nd row and the white piece belongs to the 7th row.
			
			}else if(currentCandidateOffset == 16 &&  this.isFirstMove() && 
					(BoardUtils.SECOND_ROW[this.piecePosition]) && this.pieceAlliance.isBlack() ||
					(BoardUtils.SEVENTH_ROW[this.piecePosition]) && this.pieceAlliance.isWhite()){
				//Checks condition to see if there is no piece in front of the pawn's very FIRST position
				final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
				//Checks condition to see if the tile in front is occupied and the tile that is 2 ahead is occupied
				if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() ||
						!board.getTile(candidateDestinationCoordinate).isTileOccupied()){
					legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
				}
			
			}else if(currentCandidateOffset == 7 &&
					!((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
					(BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))){ 
				if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					if(this.pieceAlliance != pieceOnCandidate.pieceAlliance){
						//TODO here
						legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
					}
				}
			
			}else if(currentCandidateOffset == 9 &&
					!((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack() ||
					(BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite())))){
				if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					if(this.pieceAlliance != pieceOnCandidate.pieceAlliance){
						//TODO here
						legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
					}
					
				}

				
			}
		}
		
		return ImmutableList.copyOf(legalMoves);
	}

	
	
	@Override
	public String toString(){
		return PieceType.PAWN.toString();
	}
}
