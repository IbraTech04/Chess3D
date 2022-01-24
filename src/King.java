class King extends Piece {

	/**
	 * @author Fardeen Kasmani
	 * @param player Player ID
	 * @param x      X position
	 * @param y      Y position
	 */
	public King(int player, int x, int y) {
		super(player, x, y, 0, Type.KING);
	}

	/**
	 * This method returns all the possible movements for the king class
	 * 
	 * @author Fardeen Kasmani
	 * @param boardStatus 2D piece array which contains all the current status of
	 *                    the board
	 * @return toReturn A 2D integer array containing 0s, 1s, and 2s, signifying
	 *         where the king can go
	 */
	public int[][] getMove(Piece[][] boardStatus) {
		int toReturn[][] = new int[boardStatus.length][boardStatus[0].length];
		int coords[][] = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

		for (int i = 0; i < coords.length; i++) {
			int y = super.getPosX() + coords[i][0];
			int x = super.getPosY() + coords[i][1];

			if (BoardUtils.isCoord(x, y)) {
				// If spot is empty
				if (boardStatus[x][y] == null) {
					toReturn[x][y] = 1;
				}

				// If Kill is Possible
				if (boardStatus[x][y] != null && (boardStatus[x][y].getPlayer() != super.getPlayer())) {
					toReturn[x][y] = 2;
				}
			}
		}
		return toReturn;
	}
}