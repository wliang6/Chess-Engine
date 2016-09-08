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
 * @author Winnie Aug 27, 2016
 * Queen.java
 */
public class Queen extends Piece{

	//This int array consists of the offset values of the UNION of Rook and Bishop moves. 
	private final static int [] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};

	/**
	 * @param pieceAlliance
	 * @param piecePosition
	 */
	public Queen(final Alliance pieceAlliance, final int piecePosition) {
		super(PieceType.QUEEN, piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		final List<Move> legalMoves = new ArrayList<>();
		for(final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES){
			int candidateDestinationCoordinate = this.piecePosition;
			
			while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
				
				if(isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
						isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)){
					break;
				}
				
				candidateDestinationCoordinate += candidateCoordinateOffset;
				if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
					final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
					if(!candidateDestinationTile.isTileOccupied()){
						legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));	
					}else{
						final Piece pieceAtDestination = candidateDestinationTile.getPiece();
						final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
						if(this.pieceAlliance != pieceAlliance){ //if this piece alliance is not the same color as you (either B or W)
							legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
						}
						break; //breaks out of the for loop if tile is occupied by friendly piece
					}
				}
				
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	
	@Override
	public String toString(){
		return PieceType.QUEEN.toString();
	}
	
	public static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == -1 || candidateOffset == 7);
	}

	public static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);
	}
}
