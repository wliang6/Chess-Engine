package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

/**
 * Chess
 * @author Winnie Aug 16, 2016
 * Tile.java
 */

public abstract class Tile {
	
	final int tileCoordinate;
	private static Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
	/**
	 * Purpose: 
	 * Map<Integer,EmptyTile>
	 */
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
		for(int i = 0; i < BoardUtils.NUM_TILES; i++){ //NUM_TILES = 64
			emptyTileMap.put(i, new EmptyTile(i));
		}
		return ImmutableMap.copyOf(emptyTileMap); //Guava library - allows an unmodifiable map
	}
	
	public static Tile createTile(final int tileCoordinate, final Piece piece){
		return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
	}
	
	//Tile constructor that takes in tile coordinate as parameter
	private Tile(final int tileCoordinate){
		this.tileCoordinate = tileCoordinate;
	}
	
	public abstract boolean isTileOccupied();
	public abstract Piece getPiece();
	
	
	
	public static final class EmptyTile extends Tile{

		/**
		 * @param tileCoordinate 
		 */
		private EmptyTile(final int tileCoordinate) {
			super(tileCoordinate);
		}

		@Override
		public boolean isTileOccupied() {
			return false;
		}

		@Override
		public Piece getPiece() {
			return null;
		}
		
	}
	
	public static final class OccupiedTile extends Tile{
		private final Piece pieceOnTile;
		/**
		 * @param tileCoordinate
		 * @param piece 
		 */
		private OccupiedTile(int tileCoordinate, Piece piece) {
			super(tileCoordinate);
			this.pieceOnTile = piece; 
		}

		@Override
		public boolean isTileOccupied() {
			return true;
		}


		@Override
		public Piece getPiece() {

			return this.pieceOnTile;
		} 
	}
}

//	protected final int tileCoordinate; //can only be altered by subclasses/this class and you cannot set the value again
//	//Tile constructor that allows us to create an individual tile
//	private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();
//	
//	
//	//createAllPossibleEmptyTiles
//	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles(){
//		final Map<Integer,EmptyTile> emptyTileMap = new HashMap<>();
//		for(int i = 0; i < 64; i++){
//			emptyTileMap.put(i, new EmptyTile(i));
//		}
//		return ImmutableMap.copyOf(emptyTileMap); //ImmutableMap is a library that makes the map return immutable
//	}
//	
//	
//	
//	public static Tile createTile(final int tileCoordinate, final Piece piece){
//		return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES.get(tileCoordinate);
//	} //anyone can use this method due to "public"- to get a empty tile, you get one of the cached tiles or occupied tiles
//	
//	
//	
//	private Tile(int tileCoordinate){
//		this.tileCoordinate = tileCoordinate;
//	}
//	
//	
//	
//	public abstract boolean isTileOccupied(); 
//	//abstract means you are defining this method in a subclass rather than in this class. 
//	public abstract Piece getPiece(); //gets the piece off from the tile
//	
//	
////EmptyTile object aka subclass	
//	public static final class EmptyTile extends Tile{ //created this subclass for empty tile
//		EmptyTile(int coordinate){ //EmptyTile constructor
//			super(coordinate); //calls superclass constructor with tile coordinate
//		}
//		
//		@Override
//		public boolean isTileOccupied(){
//			return false;
//		}
//		
//		@Override
//		public Piece getPiece(){
//			return null;
//		}
//	}	
//	
//	
//
//	public static final class OccupiedTile extends Tile{
//		private final Piece pieceOnTile;
//		OccupiedTile(int tileCoordinate, Piece pieceOnTile){
//			super(tileCoordinate);
//			this.pieceOnTile = pieceOnTile;
//		}
//		
//		@Override
//		public boolean isTileOccupied(){
//			return true;
//		}
//		
//		@Override
//		public Piece getPiece(){
//			return this.pieceOnTile;
//		}
//		
//	}
//	
//}
