class Bishop extends Piece {

	/**
	 * @author Ibrahim Chehab
	 * @param player
	 * @param x
	 * @param y
	 */
	public Bishop(int player, int x, int y) {
		super(player, x, y, 3, Type.BISHOP);
	}

	/**
	 * Method which returns where the Bishop can go
	 * 
	 * @author Ibrahim Chehab
	 * @param boardStatus Piece array containing the board status
	 * @return toReturn int[][] array containing where the object can go
	 */
	public int[][] getMove(Piece[][] boardStatus) {
		int[][] toReturn = new int[boardStatus.length][boardStatus[0].length];
		int[][] coords = { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 4 }, { 5, 5 }, { 6, 6 }, { 7, 7 } };

		for (int[] i : coords) {
			int y = super.getPosX() + i[0];
			int x = super.getPosY() + i[1];
			if (BoardUtils.isCoord(x, y)) {
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				} else if (boardStatus[x][y].getPlayer() != super.getPlayer()) {
					toReturn[x][y] = 2;
					break;
				} else {
					break;
				}
			}
		}

		coords = new int[][] { { -1, -1 }, { -2, -2 }, { -3, -3 }, { -4, -4 }, { -5, -5 }, { -6, -6 }, { -7, -7 } };
		for (int[] i : coords) {
			int y = super.getPosX() + i[0];
			int x = super.getPosY() + i[1];
			if (BoardUtils.isCoord(x, y)) {
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				} else if (boardStatus[x][y].getPlayer() != super.getPlayer()) {
					toReturn[x][y] = 2;
					break;
				} else {
					break;
				}
			}
		}

		coords = new int[][] { { 1, -1 }, { 2, -2 }, { 3, -3 }, { 4, -4 }, { 5, -5 }, { 6, -6 }, { 7, -7 } };
		for (int[] i : coords) {
			int y = super.getPosX() + i[0];
			int x = super.getPosY() + i[1];
			if (BoardUtils.isCoord(x, y)) {
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				} else if (boardStatus[x][y].getPlayer() != super.getPlayer()) {
					toReturn[x][y] = 2;
					break;
				} else {
					break;
				}
			}
		}

		coords = new int[][] { { -1, 1 }, { -2, 2 }, { -3, 3 }, { -4, 4 }, { -5, 5 }, { -6, 6 }, { -7, 7 } };
		for (int[] i : coords) {
			int y = super.getPosX() + i[0];
			int x = super.getPosY() + i[1];
			if (BoardUtils.isCoord(x, y)) {
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				} else if (boardStatus[x][y].getPlayer() != super.getPlayer()) {
					toReturn[x][y] = 2;
					break;
				} else {
					break;
				}
			}
		}

		return toReturn;
	}
}
