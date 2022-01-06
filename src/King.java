class King extends Piece {

	public King(int player, int x, int y) {
		super(player, x, y);
		super.id = 0;

	}

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