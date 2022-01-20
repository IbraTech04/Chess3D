import processing.core.PApplet;

import java.awt.Color;

import javax.swing.*;

class Board {

	Piece[][] board; // The main board array

	/**
	 * Initializes the board array from scratch
	 * 
	 * @author Ibrahim Chehab
	 */
	PApplet pa;

	public Board(PApplet pa) {
		board = new Piece[8][8];
		//Creating all the piece objects to be added to board
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

		//Using foreach loops to add them to the baord at their respective positions
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

		this.pa = pa;
	}

	/**
	 * Creates a board class given a string array representation of a board Used
	 * when checking for checkmates
	 * 
	 * @param board
	 */
	public Board(String[][] board) {
		this.board = new Piece[8][8];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != null) {
					String[] workWith = board[i][j].split(":");
					if (workWith[0].toUpperCase().equals("PAWN")) {
						this.board[i][j] = new Pawn(Integer.parseInt(workWith[1]), j, i);
						this.board[i][j].setMove(getStringBoolean(workWith[2]));
					}
					if (workWith[0].toUpperCase().equals("ROOK")) {
						this.board[i][j] = new Rook(Integer.parseInt(workWith[1]), j, i);
						this.board[i][j].setMove(getStringBoolean(workWith[2]));
					}
					if (workWith[0].toUpperCase().equals("BISHOP")) {
						this.board[i][j] = new Bishop(Integer.parseInt(workWith[1]), j, i);
						this.board[i][j].setMove(getStringBoolean(workWith[2]));
					}
					if (workWith[0].toUpperCase().equals("KING")) {
						this.board[i][j] = new King(Integer.parseInt(workWith[1]), j, i);
						this.board[i][j].setMove(getStringBoolean(workWith[2]));
					}
					if (workWith[0].toUpperCase().equals("QUEEN")) {
						this.board[i][j] = new Queen(Integer.parseInt(workWith[1]), j, i);
						this.board[i][j].setMove(getStringBoolean(workWith[2]));
					}
					if (workWith[0].toUpperCase().equals("KNIGHT")) {
						this.board[i][j] = new Knight(Integer.parseInt(workWith[1]), j, i);
						this.board[i][j].setMove(getStringBoolean(workWith[2]));
					}
				}
			}
		}
	}

	/**
	 * Returns whether a string is "true"
	 * Used when recreating boards from String arrays
	 * 
	 * @author Ibrahim Chehab
	 * @param a
	 * @return True
	 */
	public boolean getStringBoolean(String a) {
		return a.toUpperCase().equals("TRUE");
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

			if (board[y2][x2].getPiece() == Type.PAWN) {
				if (board[y2][x2].getPlayer() == 0) {
					if (board[y2 + 1][x2] != null) {
						p2.addToPile(board[y2 + 1][x2]);
						board[y2 + 1][x2] = null;
					}
				} else {
					if (board[y2 - 1][x2] != null) {
						p2.addToPile(board[y2 - 1][x2]);
						board[y2 - 1][x2] = null;
					}
				}
			}
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

		if (y2 == 0 || y2 == 7) {
			if (board[y2][x2].getPiece() == Type.PAWN) {
				pawnPromotion(x2, y2);
			}
		}
	}

	/**
	 * Moves piece without adding to player pile. Used in CheckMate
	 * 
	 * @author Fardeen Kasmani
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void movePiece(int x1, int y1, int x2, int y2) {
		// Checking and Moving the Piece to an Empty Spot
		if (board[y2][x2] == null) {
			board[y2][x2] = board[y1][x1];
			board[y2][x2].setPosX(x2);
			board[y2][x2].setPosY(y2);
			board[y1][x1] = null;
			board[y2][x2].setMove(true);

			if (board[y2][x2].getPiece() == Type.PAWN) {
				if (board[y2][x2].getPlayer() == 0) {
					if (board[y2 + 1][x2] != null) {
						board[y2 + 1][x2] = null;
					}
				} else {
					if (board[y2 - 1][x2] != null) {
						board[y2 - 1][x2] = null;
					}
				}
			}
		}
		// Killing an Enemy and Moving the Piece to an Empty Spot
		else {
			board[y2][x2] = board[y1][x1];
			board[y2][x2].setPosX(x2);
			board[y2][x2].setPosY(y2);
			board[y1][x1] = null;
			board[y2][x2].setMove(true);
		}
	}

	/**
	 * Castles player pieces
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

	/**
	 * Promotes Pawns when they reach the end of the board
	 * 
	 * @param x2 X- coordinate to change
	 * @param y2 Y- coordinate to change
	 * @author Fardeen Kasmani
	 */
	public void pawnPromotion(int x2, int y2) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Sets Windows UI
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		String[] options = { "Queen", "Rook", "Bishop", "Knight" };

		String entry;

		do {
			entry = (String) JOptionPane.showInputDialog(null, "What would you like to promote this pawn to?",
					"Pawn Promotion", JOptionPane.QUESTION_MESSAGE, new ImageIcon("data\\pawnR.png"), options,
					options[0]);
		} while (entry == null);
		int player = board[y2][x2].getPlayer();

		if (entry.equals("Queen")) {
			board[y2][x2] = null;
			board[y2][x2] = new Queen(player, x2, y2);
		}

		if (entry.equals("Rook")) {
			board[y2][x2] = null;
			board[y2][x2] = new Rook(player, x2, y2);
			board[y2][x2].setMove(true); // Disables Castling with the New Rook
		}

		if (entry.equals("Bishop")) {
			board[y2][x2] = null;
			board[y2][x2] = new Bishop(player, x2, y2);
		}

		if (entry.equals("Knight")) {
			board[y2][x2] = null;
			board[y2][x2] = new Knight(player, x2, y2);
		}
		System.gc();
	}

	/**
	 * Returns a string array representation of the current board state Used in
	 * checkmate detection
	 * 
	 * @author Ibrahim Chehab
	 * @return
	 */
	public static String[][] makeBoardString(Piece[][] board) {
		String[][] toReturn = new String[8][8];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != null) {
					toReturn[i][j] = String.valueOf(board[i][j].getPiece()) + ":"
							+ String.valueOf(board[i][j].getPlayer()) + ":" + String.valueOf(board[i][j].getMove());
				}
			}
		}
		return toReturn;
	}
}
