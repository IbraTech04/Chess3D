import java.util.Arrays;

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
	 * Method which checks if a player can castle
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
	 * Method which checks to see if a player is checked
	 * 
	 * @author Fardeen Kasmani
	 * @param board
	 * @param id
	 * @param kingX
	 * @param kingY
	 * @return
	 */
	public static boolean checkforCheck(Piece[][] board, int id, int kingX, int kingY) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != null) {
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
		}
		return false;
	}

	/**
	 * Given the player, this function returns the king's coordinates
	 * 
	 * @author Ibrahim Chehab
	 * @param board
	 * @param player
	 * @return
	 */
	public static int[] getKingCoords(Piece[][] board, int player) {
		for (Piece[] p : board) {
			for (Piece pa : p) {
				if (pa != null) {
					if (pa.getPiece() == Type.KING && pa.getPlayer() == player) {
						return new int[] { pa.getPosX(), pa.getPosY() };
					}
				}
			}
		}
		return null;
	}

	/**
	 * Method that checks for a checkmate, given a player
	 * 
	 * @param b     A reference to the board object
	 * @param id    The player to check
	 * @param kingX The players KingX
	 * @param kingY The players KingY
	 * @author Ibrahim Chehab
	 * @return Whether the player is checkmated
	 */
	public static boolean checkforCheckMate(Board b, int id, int kingX, int kingY) {
		Piece[][] pieces = b.getBoard();
		for (Piece[] p : pieces) {
			for (Piece d : p) {
				if (d != null) {
					if (d.getPlayer() == id) {
						int[][] movements = d.getMove(pieces);
						for (int i = 0; i < movements.length; i++) {
							for (int j = 0; j < movements[i].length; j++) {
								if (movements[j][i] != 0) {
									String[][] bs = Board.makeBoardString(b.getBoard());
									Board newBoard = new Board(bs);
									newBoard.movePiece(d.getPosX(), d.getPosY(), i, j);

									if (!checkforCheck(newBoard.getBoard(), id,
											getKingCoords(newBoard.getBoard(), id)[0],
											getKingCoords(newBoard.getBoard(), id)[1])) {
										return false;
									}
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Method that returns where a particular piece can go when checked
	 * @param pieces 
	 * @param p 
	 * @param id 
	 * @author Ibrahim Chehab
	 * 
	 * @return Array with all the places a piece can go
	 * 
	 */
	public static int[][] getCheckPlaces(Piece[][] pieces, Piece p, int id) {
		int toReturn[][] = new int[8][8];
		int[][] movements = p.getMove(pieces);
		for (int i = 0; i < movements.length; i++) {
			for (int j = 0; j < movements[i].length; j++) {
				if (movements[j][i] != 0) {
					String[][] bs = Board.makeBoardString(pieces);
					Board newBoard = new Board(bs);
					newBoard.movePiece(p.getPosX(), p.getPosY(), i, j);

					if (!checkforCheck(newBoard.getBoard(), id, getKingCoords(newBoard.getBoard(), id)[0],
							getKingCoords(newBoard.getBoard(), id)[1])) {
						toReturn[j][i] = 1;
					}
				}
			}

		}
		return toReturn;
	}

	/**
	 * Function used for debugging. Prints out an array
	 * 
	 * @author Ibrahim Chehab
	 * @param arr
	 */
	static void printArray(int[][] arr) {
		for (int[] i : arr) {
			for (int a : i) {
				System.out.print(a + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Function which returns where a player can go to get out of a check
	 * 
	 * @author Ibrahim Chehab
	 */
	int[][] getCheckMoves() {
		// TODO Implement this function
		return null;
	}
}