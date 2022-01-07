/**
 * The class which represents Pawns
 *
 * @author Ibrahim Chehab
 */
class Pawn extends Piece {
	/**
	 * 
	 * @param player
	 * @param x
	 * @param y
	 */
	public Pawn(int player, int x, int y) {
		super(player, x, y, 5, Type.PAWN);
	}

	// Variable which holds what side the instance of the pawn is on. This is
	// important because
	// Pawns can only move forward (until they morph into another)

	/**
	 * This method is used to determine where pawns can go
	 * 
	 * @author Ibrahim Chehab
	 * @param boardStatus This is the first paramter to addNum method
	 * @return int[][] This returns where this piece can go
	 */
	public int[][] getMove(Piece[][] boardStatus) {
		int[] twoSpaceY = { 6, 1 };
		int[][] kills = { { 1, 1 }, { -1, 1 } };
		int[][] toReturn = new int[boardStatus.length][boardStatus[0].length];
		int x = super.getPosX();
		int y = super.getPosY();

		if (super.getPlayer() == 1) {
			y += 1;
			if (BoardUtils.isCoord(x, y)) {
				if (boardStatus[y][x] == null) {
					toReturn[y][x] = 1;
				}
			}
			if (super.getPosY() == twoSpaceY[super.getPlayer()]) {
				y += 1;
				if (BoardUtils.isCoord(x, y)) {
					if (boardStatus[y][x] == null) {
						toReturn[y][x] = 1;
					}
				}
			}

			for (int i = 0; i < kills.length; i++) {
				x = super.getPosX() + kills[i][0];
				y = super.getPosY() + kills[i][1];

				if (BoardUtils.isCoord(x, y)) {
					if (boardStatus[y][x] != null) {
						if (boardStatus[y][x].getPlayer() != super.getPlayer())
							toReturn[y][x] = 2;
					}
				}
			}

			x = super.getPosX();
			y = super.getPosY();

			if (y == 3) {
				if (BoardUtils.isCoord(x + 1, y)) {
					if (boardStatus[y][x + 1] != null && boardStatus[y][x - 1].getPiece() == Type.PAWN) {
						toReturn[y + 1][x + 1] = 2;
					}
				}
				if (BoardUtils.isCoord(x - 1, y)) {
					if (boardStatus[y][x - 1] != null && boardStatus[y][x + 1].getPiece() == Type.PAWN) {
						toReturn[y + 1][x - 1] = 2;
					}
				}
			}

		}

		else {
			y -= 1;
			if (BoardUtils.isCoord(x, y)) {
				if (boardStatus[y][x] == null) {
					toReturn[y][x] = 1;
				}
			}
			if (super.getPosY() == twoSpaceY[super.getPlayer()]) {
				y -= 1;
				if (BoardUtils.isCoord(x, y)) {
					if (boardStatus[y][x] == null) {
						toReturn[y][x] = 1;
					}
				}
			}

			for (int i = 0; i < kills.length; i++) {
				x = super.getPosX() - kills[i][0];
				y = super.getPosY() - kills[i][1];

				if (BoardUtils.isCoord(x, y)) {
					if (boardStatus[y][x] != null) {
						if (boardStatus[y][x].getPlayer() != super.getPlayer()) {
							toReturn[y][x] = 2;
						}
					}
				}
			}

			x = super.getPosX();
			y = super.getPosY();

			if (y == 3) {
				if (BoardUtils.isCoord(x - 1, y)) {
					if (boardStatus[y][x - 1] != null && boardStatus[y][x - 1].getPiece() == Type.PAWN) {
						toReturn[y - 1][x - 1] = 2;
					}
				}
				if (BoardUtils.isCoord(x + 1, y)) {
					if (boardStatus[y][x + 1] != null && boardStatus[y][x + 1].getPiece() == Type.PAWN) {
						toReturn[y - 1][x + 1] = 2;
					}
				}
			}
		}

		return toReturn;
	}
}