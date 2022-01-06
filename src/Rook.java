class Rook extends Piece {
	
	/**
	 * @author Ibrahim Chehab
	 * @param player
	 * @param x
	 * @param y
	 */
	
	public Rook(int player, int x, int y) {
		super(player, x, y, 4);

	}

	/**
	 * Method which returns where the Rook can go
	 * @author Ibrahim Chehab
	 * @param boardStatus Piece array containing the board status 
	 * @return toReturn int[][] array containing where the object can go
	 */
	
	public int[][] getMove(Piece[][] boardStatus) {
		int[][] coords = { { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 5, 0 }, { 6, 0 }, { 7, 0 } };
		int[][] toReturn = new int[boardStatus.length][boardStatus[0].length];
		for (int[] i : coords) {
			int y = super.getPosX() + i[0];
			int x = super.getPosY() + i[1];
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
		for (int[] i : coords) {
			int y = super.getPosX() + i[0];
			int x = super.getPosY() + i[1];
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
		for (int[] i : coords) {
			int y = super.getPosX() + i[0];
			int x = super.getPosY() + i[1];
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
		for (int[] i : coords) {
			int y = super.getPosX() + i[0];
			int x = super.getPosY() + i[1];
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