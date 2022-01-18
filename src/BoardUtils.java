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
									String[][] bs = b.makeBoardString();
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

	static void printArray(int[][] arr) {
		for (int[] i : arr) {
			for (int a : i) {
				System.out.print(a + " ");
			}
			System.out.println();
		}
	}

	public static Piece[][] moveObject(Piece[][] board, int x1, int y1, int x2, int y2) {
		// Checking and Moving the Piece to an Empty Spot
		if (board[y2][x2] == null) {
			board[y2][x2] = board[y1][x1];
			board[y2][x2].setPosX(x2);
			board[y2][x2].setPosY(y2);
			board[y1][x1] = null;
			board[y2][x2].setMove(true);

			if (board[y2][x2].getPiece() == Type.PAWN) {
				if (board[y2][x2].getPlayer() == 0) {
					if (board[y2 + 1][x2] != null) {
						board[y2 + 1][x2] = null;
					}
				} else {
					if (board[y2 - 1][x2] != null) {
						board[y2 - 1][x2] = null;
					}
				}
			}
		}
		// Killing an Enemy and Moving the Piece to an Empty Spot
		else {
			board[y2][x2] = board[y1][x1];
			board[y2][x2].setPosX(x2);
			board[y2][x2].setPosY(y2);
			board[y1][x1] = null;
			board[y2][x2].setMove(true);
		}
		return board;
	}

	int[][] getCheckMoves() {
		return null;
	}
}