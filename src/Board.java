class Board {
	Piece[][] board;

	public Board() {
		board = new Piece[8][8];
		Pawn[] playerOnePawns = new Pawn[] { new Pawn(1, 0, 1), new Pawn(1, 1, 1), new Pawn(1, 2, 1), new Pawn(1, 3, 1),
				new Pawn(1, 4, 1), new Pawn(1, 5, 1), new Pawn(1, 6, 1), new Pawn(1, 7, 1) };

		Rook[] playerOneRooks = new Rook[] { new Rook(1, 0, 0), new Rook(1, 7, 0) };
		Knight[] playerOneKnights = new Knight[] { new Knight(1, 1, 0), new Knight(1, 6, 0) };
		Bishop[] playerOneBishops = new Bishop[] { new Bishop(1, 2, 0), new Bishop(1, 5, 0) };
		Queen playerOneQueen = new Queen(1, 3, 0);
		King playerOneKing = new King(1, 4, 0);

		Pawn[] playerTwoPawns = new Pawn[] { new Pawn(0, 0, 6), new Pawn(0, 1, 6), new Pawn(0, 2, 6), new Pawn(0, 3, 6),
				new Pawn(0, 4, 6), new Pawn(0, 5, 6), new Pawn(0, 6, 6), new Pawn(0, 7, 6) };

		Rook[] playerTwoRooks = new Rook[] { new Rook(0, 0, 7), new Rook(0, 7, 7) };
		Knight[] playerTwoKnights = new Knight[] { new Knight(0, 1, 7), new Knight(0, 6, 7) };
		Bishop[] playerTwoBishops = new Bishop[] { new Bishop(0, 2, 7), new Bishop(0, 5, 7) };
		Queen playerTwoQueen = new Queen(0, 3, 7);

		King playerTwoKing = new King(0, 4, 7);

		for (Pawn p : playerOnePawns) {
			board[p.getPosY()][p.getPosX()] = p;
		}

		for (Pawn p : playerTwoPawns) {
			board[p.getPosY()][p.getPosX()] = p;
		}

		for (Rook p : playerOneRooks) {
			board[p.getPosY()][p.getPosX()] = p;
		}

		for (Rook p : playerTwoRooks) {
			board[p.getPosY()][p.getPosX()] = p;
		}

		for (Knight p : playerOneKnights) {
			board[p.getPosY()][p.getPosX()] = p;
		}

		for (Knight p : playerTwoKnights) {
			board[p.getPosY()][p.getPosX()] = p;
		}

		for (Bishop p : playerOneBishops) {
			board[p.getPosY()][p.getPosX()] = p;
		}

		for (Bishop p : playerTwoBishops) {
			board[p.getPosY()][p.getPosX()] = p;
		}

		board[playerOneQueen.getPosY()][playerOneQueen.getPosX()] = playerOneQueen;

		board[playerTwoQueen.getPosY()][playerTwoQueen.getPosX()] = playerTwoQueen;

		board[playerOneKing.getPosY()][playerOneKing.getPosX()] = playerOneKing;

		board[playerTwoKing.getPosY()][playerTwoKing.getPosX()] = playerTwoKing;

	}

	public Piece[][] getBoard() {
		return board;
	}


	public void movePiece(Player p1, Player p2, int x1, int y1, int x2, int y2) {
		// Checking and Moving the Piece to an Empty Spot
		if (board[y2][x2] == null) {
			board[y2][x2] = board[y1][x1];
			board[y2][x2].setPosX(x2);
			board[y2][x2].setPosY(y2);
			board[y1][x1] = null;
		}
		// Killing an Enemy and Moving the Piece to an Empty Spot
		else {
			int playerNumber = board[y2][x2].getPlayer();
			if (playerNumber == 1) {
				// p2.addToPile(pieceTwo);

			} else {
				// p1.addToPile(pieceTwo);
			}
			board[y2][x2] = board[y1][x1];
			board[y2][x2].setPosX(x2);
			board[y2][x2].setPosY(y2);
			board[y1][x1] = null;
		}
	}
}