class BoardUtils {
	static int boardSize = 8;

	/**
	 * This method is used to determine if the specified coordinates exist on the
	 * board
	 * 
	 * @author Ibrahim Chehab
	 * @param x The x value of a coordinate on the board
	 * @param y The y value of a coordinate on the board
	 * @return boolean isCoord
	 */
	public static boolean isCoord(int x, int y) {
		return (x >= 0 && x < boardSize && y >= 0 && y < boardSize);
	}

	public static boolean isMoved(Piece p) {
		return p.getMove();
	}
}