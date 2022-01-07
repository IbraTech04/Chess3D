class Queen extends Piece {

	/**
	 * @author Fardeen Kasmani
	 * @param player
	 * @param x
	 * @param y
	 */
	public Queen(int player, int x, int y) {
		super(player, x, y, 1, Type.QUEEN);
	}

	/**
	 * This method returns all the possible movements for the queen class
	 * 
	 * @author Fardeen Kasmani
	 * @param boardStatus 2D piece array which contains all the current status of
	 *                    the board
	 * @return toReturn A 2D integer array containing 0s, 1s, and 2s, signifying
	 *         where the king can go
	 */
	public int[][] getMove(Piece[][] boardStatus) {
		int toReturn[][] = new int[boardStatus.length][boardStatus[0].length];
		int coords[][] = { { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 5, 0 }, { 6, 0 }, { 7, 0 } };

		for (int i = 0; i < coords.length; i++) {
			int y = super.getPosX() + coords[i][0];
			int x = super.getPosY() + coords[i][1];

			if (BoardUtils.isCoord(x, y)) {
				// If spot is empty
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				} else {
					// If Kill is Possible
					if (boardStatus[x][y] != null && (boardStatus[x][y].getPlayer() != super.getPlayer())) {
						toReturn[x][y] = 2;
						break;
					} else {
						break;
					}
				}
			}
		}

		coords = new int[][] { { -1, 0 }, { -2, 0 }, { -3, 0 }, { -4, 0 }, { -5, 0 }, { -6, 0 }, { -7, 0 } };
		for (int i = 0; i < coords.length; i++) {
			int y = super.getPosX() + coords[i][0];
			int x = super.getPosY() + coords[i][1];

			if (BoardUtils.isCoord(x, y)) {
				// If spot is empty
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				} else {
					// If Kill is Possible
					if (boardStatus[x][y] != null && (boardStatus[x][y].getPlayer() != super.getPlayer())) {
						toReturn[x][y] = 2;
						break;

					} else {
						break;
					}
				}
			}
		}

		coords = new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 } };
		for (int i = 0; i < coords.length; i++) {
			int y = super.getPosX() + coords[i][0];
			int x = super.getPosY() + coords[i][1];

			if (BoardUtils.isCoord(x, y)) {
				// If spot is empty
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				} else {
					// If Kill is Possible
					if (boardStatus[x][y] != null && (boardStatus[x][y].getPlayer() != super.getPlayer())) {
						toReturn[x][y] = 2;
						break;

					} else {
						break;
					}
				}
			}
		}

		coords = new int[][] { { 0, -1 }, { 0, -2 }, { 0, -3 }, { 0, -4 }, { 0, -5 }, { 0, -6 }, { 0, -7 } };
		for (int i = 0; i < coords.length; i++) {
			int y = super.getPosX() + coords[i][0];
			int x = super.getPosY() + coords[i][1];

			if (BoardUtils.isCoord(x, y)) {
				// If spot is empty
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				} else {
					// If Kill is Possible
					if (boardStatus[x][y] != null && (boardStatus[x][y].getPlayer() != super.getPlayer())) {
						toReturn[x][y] = 2;
						break;

					} else {
						break;
					}
				}
			}
		}

		coords = new int[][] { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 4 }, { 5, 5 }, { 6, 6 }, { 7, 7 } };
		for (int i = 0; i < coords.length; i++) {
			int y = super.getPosX() + coords[i][0];
			int x = super.getPosY() + coords[i][1];

			if (BoardUtils.isCoord(x, y)) {
				// If spot is empty
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				} else {
					// If Kill is Possible
					if (boardStatus[x][y] != null && (boardStatus[x][y].getPlayer() != super.getPlayer())) {
						toReturn[x][y] = 2;
						break;
					} else {
						break;
					}
				}
			}
		}

		coords = new int[][] { { -1, 1 }, { -2, 2 }, { -3, 3 }, { -4, 4 }, { -5, 5 }, { -6, 6 }, { -7, 7 } };
		for (int i = 0; i < coords.length; i++) {
			int y = super.getPosX() + coords[i][0];
			int x = super.getPosY() + coords[i][1];

			if (BoardUtils.isCoord(x, y)) {
				// If spot is empty
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				} else {
					// If Kill is Possible
					if (boardStatus[x][y] != null && (boardStatus[x][y].getPlayer() != super.getPlayer())) {
						toReturn[x][y] = 2;
						break;
					} else {
						break;
					}
				}
			}
		}

		coords = new int[][] { { 1, -1 }, { 2, -2 }, { 3, -3 }, { 4, -4 }, { 5, -5 }, { 6, -6 }, { 7, -7 } };
		for (int i = 0; i < coords.length; i++) {
			int y = super.getPosX() + coords[i][0];
			int x = super.getPosY() + coords[i][1];

			if (BoardUtils.isCoord(x, y)) {
				// If spot is empty
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				} else {
					// If Kill is Possible
					if (boardStatus[x][y] != null && (boardStatus[x][y].getPlayer() != super.getPlayer())) {
						toReturn[x][y] = 2;
						break;
					} else {
						break;
					}
				}
			}
		}

		coords = new int[][] { { -1, -1 }, { -2, -2 }, { -3, -3 }, { -4, -4 }, { -5, -5 }, { -6, -6 }, { -7, -7 } };
		for (int i = 0; i < coords.length; i++) {
			int y = super.getPosX() + coords[i][0];
			int x = super.getPosY() + coords[i][1];

			if (BoardUtils.isCoord(x, y)) {
				// If spot is empty
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				} else {
					// If Kill is Possible
					if (boardStatus[x][y] != null && (boardStatus[x][y].getPlayer() != super.getPlayer())) {
						toReturn[x][y] = 2;
						break;

					} else {
						break;
					}
				}
			}
		}

		return toReturn;
	}
}
