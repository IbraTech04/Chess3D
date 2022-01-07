class Knight extends Piece {
	/**
	 * @author Fardeen Kasmani
	 * @param player
	 * @param x
	 * @param y
	 */
	public Knight(int player, int x, int y) {
		super(player, x, y, 2, Type.KNIGHT);
	}

	/**
	 * This method returns all the possible movements for the knight class
	 * 
	 * @author Fardeen Kasmani
	 * @param boardStatus 2D piece array which contains all the current status of
	 *                    the board
	 * @return A 2D integer array containing 0s, 1s, and 2s, signifying where the
	 *         king can go
	 */
	public int[][] getMove(Piece[][] boardStatus) {
		int[][] toReturn = new int[boardStatus.length][boardStatus[0].length];
		int[][] coords = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { -1, 2 }, { 1, -2 }, { -1, -2 } };

		for (int[] i : coords) {
			int y = super.getPosX() + i[0];
			int x = super.getPosY() + i[1];
			if (BoardUtils.isCoord(x, y)) {
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				} else if (boardStatus[x][y].getPlayer() != super.getPlayer()) {
					toReturn[x][y] = 2;
				}
			}
		}
		return toReturn;
	}
}