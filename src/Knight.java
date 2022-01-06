class Knight extends Piece {
		public Knight(int player, int x, int y) {
			super(player, x, y);
	        super.id = 2;
		}

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