/**
 * The main class which all the piece types extend
 *
 * @author Ibrahim Chehab
 */
abstract class Piece {
	/**
	 * Constructor for the Piece class
	 * 
	 * @author Fardeen Kasmani
	 * @param player Signifies the player id for this piece
	 * @param x      Signifies the piece's x-coordinate on the Cartesian plane
	 * @param y      Signifies the piece's y-coordinate on the Cartesian plane
	 */
	public Piece(int player, int x, int y, int id, Type piece) {
		posX = x;
		posY = y;
		this.player = player;
		this.id = id;
		this.piece = piece;
	}

	/**
	 * Abstract function implemented by all subclasses. Returns where the piece can
	 * move
	 * 
	 * @author Ibrahim Chehab
	 * @param boardStatus Array containing all the
	 * @return int[][] Returns the places that the piece can move,
	 */
	public abstract int[][] getMove(Piece[][] boardStatus);

	private int player; // Player ID
	private int posX; // X position
	private int posY; // Y position
	public int id; // Model ID
	private boolean isMoved = false;
	private Type piece;

	/**
	 * Returns the player ID
	 * 
	 * @author Ibrahim Chehab
	 * @return int player
	 */
	public int getPlayer() {
		return player;
	}

	/**
	 * Set's the piece's player
	 * 
	 * @author Ibrahim Chehab
	 * @param player
	 */
	public void setPlayer(int player) {
		this.player = player;
	}

	/**
	 * Returns the piece's x-position
	 * 
	 * @author Ibrahim Chehab
	 * @return int posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Returns the piece's y-position
	 * 
	 * @author Ibrahim Chehab
	 * @return int posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Sets the piece's x-position
	 * 
	 * @author Fardeen Kasmani
	 * @param x
	 */
	public void setPosX(int x) {
		posX = x;
	}

	/**
	 * Sets the piece's y-position
	 * 
	 * @author Fardeen Kasmani
	 * @param y
	 */
	public void setPosY(int y) {
		posY = y;
	}
	
	/** Checks if a Piece is Moved
	 * @author Fardeen Kasmani
	 * @return isMoved
	 */
	public boolean getMove() {
		return isMoved;
	}
	
	/** Sets a Piece as Moved
	 * @author Fardeen Kasmani
	 * @param a
	 */
	public void setMove(boolean a) {
		isMoved = a;
	}
	/** Gets the Type of Piece
	 * @author Ibrahim Chehab
	 * @return piece
	 */
	public Type getPiece() {
		return piece;
	}
}