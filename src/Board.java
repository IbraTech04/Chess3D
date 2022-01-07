class Board {
	Piece[][] board; // The main board array

	/**
	 * Initializes the board array
	 * 
	 * @author Ibrahim Chehab
	 */

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

	/**
	 * This method returns the board in a pieces array
	 * 
	 * @author Fardeen Kasmani
	 * @return Pieces[][] This returns the board with the location of all the pieces
	 */
	public Piece[][] getBoard() {
		return board;
	}

	/**
	 * This method moves pieces in the board array accordingly, and adds eliminated
	 * pieces to their respective piles
	 * 
	 * @author Fardeen Kasmani
	 * @param p1 Reference to player1
	 * @param p2 Reference to player2
	 * @param x1 The x-coordinate of a piece before it moves
	 * @param y1 The y-coordinate of a piece before it moves
	 * @param x2 The destination x-coordinate
	 * @param y2 The destination y-coordinate
	 */
	public void movePiece(Player p1, Player p2, int x1, int y1, int x2, int y2) {
		// Checking and Moving the Piece to an Empty Spot
		if (board[y2][x2] == null) {
			board[y2][x2] = board[y1][x1];
			board[y2][x2].setPosX(x2);
			board[y2][x2].setPosY(y2);
			board[y1][x1] = null;
			board[y2][x2].setMove(true);
		}
		// Killing an Enemy and Moving the Piece to an Empty Spot
		else {
			int playerNumber = board[y2][x2].getPlayer();
			if (playerNumber == 1) {
				p2.addToPile(board[y2][x2]);

			} else {
				p1.addToPile(board[y2][x2]);
			}
			board[y2][x2] = board[y1][x1];
			board[y2][x2].setPosX(x2);
			board[y2][x2].setPosY(y2);
			board[y1][x1] = null;
			board[y2][x2].setMove(true);
		}
	}

	/**
	 * @author Fardeen Kasmani
	 * @param x1 King's x-pos
	 * @param y1 King's y-pos
	 * @param x2 Rook's x-pos
	 * @param y2 Rook's y-pos
	 */
	public void castle(int x1, int y1, int x2, int y2) {
		// Determining
		int deltaX = Math.abs(x2 - x1);
		// Far Side Castle
		if (deltaX == 4) {
			// Moving the King
			board[y1][x1 - 2] = board[y1][x1];
			board[y1][x1 - 2].setPosX(x1 - 2);
			board[y1][x1] = null;
			board[y1][x1 - 2].setMove(true);
			// Moving the Rook
			board[y2][x2 + 3] = board[y2][x2];
			board[y2][x2 + 3].setPosX(x2 + 3);
			board[y2][x2] = null;
			board[y2][x2 + 3].setMove(true);	
		}
		// Close Side Castle
		else {
			// Moving the King
			board[y1][x1 + 2] = board[y1][x1];
			board[y1][x1 + 2].setPosX(x1 + 2);
			board[y1][x1] = null;
			board[y1][x1 + 2].setMove(true);
			// Moving the Rook
			board[y2][x2 - 2] = board[y2][x2];
			board[y2][x2 - 2].setPosX(x2 - 2);
			board[y2][x2] = null;
			board[y2][x2 - 2].setMove(true);
		}
	}
}
