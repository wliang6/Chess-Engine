package com.chess.engine.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chess.engine.pieces.Alliance;
import com.chess.engine.pieces.Bishop;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Knight;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Queen;
import com.chess.engine.pieces.Rook;
import com.google.common.collect.ImmutableList;

/**
 * Chess
 * @author Winnie Aug 25, 2016
 * Board.java
 */
public class Board {
	

	private final List<Tile> gameBoard;
	//Keeps track of white and black pieces on the board
	
	private final Collection<Piece> blackPieces;
	private final Collection<Piece> whitePieces;
	

	//Constructor
	private Board(Builder builder){
		this.gameBoard = createGameBoard(builder);
		this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);
		this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
		
		final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
		final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);
				
				
	}
	
	//Determines what the board looks like when it turns into a string
	@Override
	public String toString(){
		final StringBuilder builder = new StringBuilder();
		for(int i = 0; i < BoardUtils.NUM_TILES; i++){
			final String tileText = this.gameBoard.get(i).toString();
			builder.append(String.format("%3s",  tileText));
			if((i+1) % BoardUtils.NUM_TILES_PER_ROW == 0){
				builder.append("\n");
			}
		}
		return builder.toString();
	}


	/**
	 * Purpose: Prints outs the board in ASCII text
	 * String
	 */
	private static String prettyPrint(final Tile tile) {
		return tile.toString();
	}

	/**
	 * Purpose: Calculates legal moves of white and black moves
	 * Collection<Move>
	 */
	private Collection<Move> calculateLegalMoves(Collection<Piece> pieces) {
		final List<Move> legalMoves = new ArrayList<>();
		for(final Piece piece : pieces){
			legalMoves.addAll(piece.calculateLegalMoves(this));
		}
		return ImmutableList.copyOf(legalMoves);
	}


	/**
	 * Purpose: Calculates how many pieces are on the board for the corresponding alliance.
	 * Collection<Piece>
	 */
	private static Collection<Piece> calculateActivePieces(List<Tile> gameBoard,
			Alliance alliance) {
		final List<Piece> activePieces = new ArrayList<>();
		for(final Tile tile : gameBoard){
			if(tile.isTileOccupied()){
				final Piece piece = tile.getPiece();
				if(piece.getPieceAlliance() == alliance){
					activePieces.add(piece);
				}		
			}
		}
		return ImmutableList.copyOf(activePieces);
	}


	public Tile getTile(final int tileCoordinate) {
		return gameBoard.get(tileCoordinate);
	}

	/**
	 * Purpose: Populates the list of tiles from number 0-63 to represent our chess board
	 * List<Tile>
	 */
	private static List<Tile> createGameBoard(final Builder builder){
		final Tile[] tiles = new Tile[BoardUtils.NUM_TILES]; //64 tiles
		for(int i = 0; i < BoardUtils.NUM_TILES; i++){
			tiles[i]= Tile.createTile(i, builder.boardConfig.get(i)); //whenever we set a config, we are going to map a piece onto the tile ID
			//createTile- associates the coordinate to the piece
		}
		return ImmutableList.copyOf(tiles);
	}
	
	public static Board createStandardBoard(){
		final Builder builder = new Builder();
		//Black tiles
		builder.setPiece(new Rook(Alliance.BLACK, 0));
		builder.setPiece(new Knight(Alliance.BLACK, 1));
		builder.setPiece(new Bishop(Alliance.BLACK, 2));
		builder.setPiece(new Queen(Alliance.BLACK, 3));
		builder.setPiece(new King(Alliance.BLACK, 4));
		builder.setPiece(new Bishop(Alliance.BLACK, 5));
		builder.setPiece(new Knight(Alliance.BLACK, 6));
		builder.setPiece(new Rook(Alliance.BLACK, 7));
		builder.setPiece(new Pawn(Alliance.BLACK, 8));
		builder.setPiece(new Pawn(Alliance.BLACK, 9));
		builder.setPiece(new Pawn(Alliance.BLACK, 10));
		builder.setPiece(new Pawn(Alliance.BLACK, 11));
		builder.setPiece(new Pawn(Alliance.BLACK, 12));
		builder.setPiece(new Pawn(Alliance.BLACK, 13));
		builder.setPiece(new Pawn(Alliance.BLACK, 14));
		builder.setPiece(new Pawn(Alliance.BLACK, 15));
		
		//White tiles
		builder.setPiece(new Pawn(Alliance.WHITE, 48));
		builder.setPiece(new Pawn(Alliance.WHITE, 49));
		builder.setPiece(new Pawn(Alliance.WHITE, 50));
		builder.setPiece(new Pawn(Alliance.WHITE, 51));
		builder.setPiece(new Pawn(Alliance.WHITE, 52));
		builder.setPiece(new Pawn(Alliance.WHITE, 53));
		builder.setPiece(new Pawn(Alliance.WHITE, 54));
		builder.setPiece(new Pawn(Alliance.WHITE, 55));
		builder.setPiece(new Rook(Alliance.WHITE, 56));
		builder.setPiece(new Knight(Alliance.WHITE, 57));
		builder.setPiece(new Bishop(Alliance.WHITE, 58));
		builder.setPiece(new Queen(Alliance.WHITE, 59));
		builder.setPiece(new King(Alliance.WHITE, 60));
		builder.setPiece(new Bishop(Alliance.WHITE, 61));
		builder.setPiece(new Knight(Alliance.WHITE, 62));
		builder.setPiece(new Rook(Alliance.WHITE, 63));
		//White piece moves first
		builder.setMoveMaker(Alliance.WHITE);
		return builder.build();
		
		
	}
	
	
	//Creating the board that contains the piece
	public static class Builder{
		
		//Mapping tile ID on the chessboard to the given piece on the tile ID
		Map<Integer, Piece> boardConfig;
		//Person to move
		Alliance nextMoveMaker;
		
		//Builder constructor
		public Builder(){ 
			this.boardConfig = new HashMap<>();
		}
		
		//Setting some property of current Builder and returning Builder back to where it is called from
		public Builder setPiece(final Piece piece){
			this.boardConfig.put(piece.getPiecePosition(), piece);
			return this;
		}
		
		public Builder setMoveMaker(final Alliance nextMoveMaker){
			this.nextMoveMaker = nextMoveMaker;
			return this;
		}
		
		public Board build(){
			return new Board(this); //creates immutable board based on builder
		}
	}
	
}
