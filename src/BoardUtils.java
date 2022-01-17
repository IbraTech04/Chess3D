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

	/**
	 * 
	 * @param board
	 * @param x1    King X-Coord
	 * @param y1    King Y-Coord
	 * @param x2    Rook X-Coord
	 * @param y2    Rook Y-Coord
	 * @author Ibrahim Chehab
	 * @return
	 */
	public static boolean isCastlePossible(Piece[][] board, int x1, int y1, int x2, int y2) {
		if (board[y1][x1].getMove() || board[y2][x2].getMove()) {
			return false;
		}

		if (x1 > x2) {
			for (int i = x1 - 1; i > x2; i--) {
				if (board[y1][i] != null) {
					return false;
				}
			}
		}

		else {
			for (int i = x1 + 1; i < x2; i++) {
				if (board[y1][i] != null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @author Fardeen Kasmani
	 * @param board
	 * @param kingX
	 * @param kingY
	 */
	public boolean checkforCheck(Piece[][] board, int id, int kingX, int kingY) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getPlayer() != id) {
					// Check if piece is on enemy team
					int[][] move = board[i][j].getMove(board);

					// if the array at the kings x and y is 2, then return true
					if (move[kingY][kingX] == 2) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void checkforCheckmate(Piece[][] board, int id, int kingX, int kingY) {
		for (Piece[] p : board) {
			for (Piece d : p) {
				if (d.getPlayer() != id) {
					int[][] movements = d.getMove(board);
				}
				
			}
		}
	}
}