// Chess 3D
// By Ibrahim Chehab and Fardeen Kasmani

import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;
import processing.core.PConstants;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class Chess2D extends PApplet {
	int screenNumber = 3;
	Piece lastChosenPiece;
	static int squareSize = 50;
	int currentPlayer = 1;

	// Loading all the textures and files required

	static Rect[][] rects;
	PImage[] images;
	String[] names = { "king.png", "queen.png", "knight.png", "bishop.png", "rook.png", "pawn.png" };
	Board board;
	Player player1, player2;

	/**
	 * Setup function required for PApplet
	 * 
	 * @author Fardeen Kasmani
	 */
	public void setup() {
		player1 = new Player(0, this);
		player2 = new Player(1, this);
		board = new Board(this);
		surface.setTitle("Chess2D - Alpha Release v0.5.1");
		surface.setResizable(true);
		initBoard2D();
		images = new PImage[6];
		for (int i = 0; i < 6; i++) {
			images[i] = loadImage("data/" + names[i]);
		}
	}

	/**
	 * Draw function required for PApplet
	 * 
	 * @author Fardeen Kasmani
	 */
	public void draw() {
		if (screenNumber == 0) {
			background(255);
			mainScreen();
		}

		else if (screenNumber == 1) {
			background(255);
			updateRectSize();
			stroke(255);
			fill(0);
			pushMatrix();
			translate((width - (squareSize * 8)) / 2, 0);
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					rects[i][j].drawRect();
				}
			}
			drawPieces(board.getBoard());
			popMatrix();
			player1.drawPile(images);
			player2.drawPile(images);
		}

		else if (screenNumber == 2) {
			background(0);
			gameOverScreen();
		}

		else if (screenNumber == 3) {
			background(0);
			settingsScreen();
		}
	}

	/**
	 * Draws the main screen for Chess 2D
	 * 
	 * @author Ibrahim Chehab
	 * 
	 */
	public void mainScreen() {
		pushStyle();
		textAlign(LEFT, TOP);
		textSize(25);
		fill(0);
		text("V1.0.0", 0, 0);
		textAlign(CENTER);
		textSize(100);
		text("jChess", width / 2, height / 5.1f);
		textSize(75);
		text("2D Edition", width / 2, height / 5.1f + 125);

		textSize(125);

		Button playButton = new Button(width / 2, height / 2, 300, 150, "Play", this, new int[] { 0, 0, 0 },
				new int[] { 255, 255, 255 });
		playButton.drawButton();
		textSize(75);
		Button settingsButton = new Button(width / 2, height / 2 + 150, 310, 100, "Settings", this,
				new int[] { 0, 0, 0 }, new int[] { 255, 255, 255 });
		settingsButton.drawButton();
		fill(0);
		textAlign(RIGHT, BOTTOM);
		textSize(25);
		text("CopyLeft iFlySoft 2022. No Rights Reserved", width, height);

		if (playButton.isPressed()) {
			screenNumber = 1;
		}
		popStyle();

	}

	/**
	 * Method which draws the game over screen
	 * 
	 * @author Fardeen Kasmani
	 */
	public void gameOverScreen() {
		pushStyle();
		textSize(100);
		textAlign(CENTER);
		fill(255);
		text("Game Over", width / 2, height / 2 - 100);
		textSize(50);
		Button playAgainButton = new Button(width / 2, height / 2, 300, 120, "Play Again", this,
				new int[] { 255, 255, 255 }, new int[] { 0, 0, 0 });

		playAgainButton.drawButton();

		if (playAgainButton.isPressed()) {
			screenNumber = 0;
			setup();
			delay(100);
		}

		popStyle();
	}

	public void settingsScreen() {
		pushStyle();
		textSize(25);
		textAlign(CENTER);
		fill(255);
		text("Board Colour: ", width / 2 - 100, height / 2 - 100);
		popStyle();
	}

	/**
	 * Method which resizes squares on screen depending on window size
	 * 
	 * @author Ibrahim Chehab
	 */

	public void updateRectSize() {

		if (width > height) {
			squareSize = height / 8;
		} else {
			squareSize = width / 8;
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				rects[i][j].update(i * squareSize, j * squareSize);
			}
		}
	}

	/**
	 * Method which initializes the rect objects on screen
	 * 
	 * @author Fardeen Kasmani
	 */
	public void initBoard2D() {
		rects = new Rect[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				rects[i][j] = new Rect(i * squareSize, j * squareSize);

				if (i % 2 == 0 && j % 2 != 0) {
					rects[i][j].setFillR(54);
					rects[i][j].setFillG(207);
					rects[i][j].setFillB(224);
				} else if (i % 2 != 0 && j % 2 == 0) {
					rects[i][j].setFillR(54);
					rects[i][j].setFillG(207);
					rects[i][j].setFillB(224);
				}

			}
		}
	}

	/**
	 * Mouse pressed function required for PApplet Depending on screen, method will
	 * either interact with GUI or move pieces
	 * 
	 * @author Ibrahim Chehab
	 */
	public void mousePressed() {
		if (screenNumber == 1) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (rects[i][j].isPressed()) {
						Piece[][] p = board.getBoard();
						if (p[j][i] != null) {
							if (p[j][i].getPlayer() != currentPlayer && lastChosenPiece != null) {
								int[][] coords = lastChosenPiece.getMove(p);
								coords = updateArray(coords,
										BoardUtils.getCheckPlaces(board.getBoard(), lastChosenPiece, currentPlayer));

								if (coords[j][i] == 2) {
									board.movePiece(player1, player2, lastChosenPiece.getPosX(),
											lastChosenPiece.getPosY(), i, j);
									resetGridColor();
									lastChosenPiece = null;
									if (currentPlayer == 1) {
										currentPlayer = 0;
									} else {
										currentPlayer = 1;
									}
								}
							} else if (p[j][i].getPlayer() == currentPlayer) {
								if (lastChosenPiece != null) {
									if (lastChosenPiece.getPiece() == Type.KING && p[j][i].getPiece() == Type.ROOK) {
										if (BoardUtils.isCastlePossible(p, lastChosenPiece.getPosX(),
												lastChosenPiece.getPosY(), i, j)) {
											board.castle(lastChosenPiece.getPosX(), lastChosenPiece.getPosY(), i, j);
											resetGridColor();
											lastChosenPiece = null;
											if (currentPlayer == 1) {
												currentPlayer = 0;
											} else {
												currentPlayer = 1;
											}
										}
									} else {
										lastChosenPiece = p[j][i];

										updateGrid(updateArray(p[j][i].getMove(board.getBoard()),
												BoardUtils.getCheckPlaces(board.getBoard(), p[j][i], currentPlayer)));
									}
								} else {
									lastChosenPiece = p[j][i];
									updateGrid(updateArray(p[j][i].getMove(board.getBoard()),
											BoardUtils.getCheckPlaces(board.getBoard(), p[j][i], currentPlayer)));
								}
							}
						} else {
							if (lastChosenPiece != null) {
								int[][] pos = lastChosenPiece.getMove(board.getBoard());

								pos = updateArray(pos,
										BoardUtils.getCheckPlaces(board.getBoard(), lastChosenPiece, currentPlayer));

								if (pos[j][i] != 0) {
									board.movePiece(player1, player2, lastChosenPiece.getPosX(),
											lastChosenPiece.getPosY(), i, j);
									resetGridColor();
									lastChosenPiece = null;
									if (currentPlayer == 1) {
										currentPlayer = 0;
									} else {
										currentPlayer = 1;
									}
								}
							}
						}
					}
				}
			}

			if (BoardUtils.checkforCheck(board.getBoard(), currentPlayer,
					BoardUtils.getKingCoords(board.getBoard(), currentPlayer)[0],
					BoardUtils.getKingCoords(board.getBoard(), currentPlayer)[1])) {

				int kingX = BoardUtils.getKingCoords(board.getBoard(), currentPlayer)[0];
				int kingY = BoardUtils.getKingCoords(board.getBoard(), currentPlayer)[1];

				rects[kingX][kingY].setFillR(255);
				rects[kingX][kingY].setFillG(120);
				rects[kingX][kingY].setFillB(120);

				if (BoardUtils.checkforCheckMate(board, currentPlayer,
						BoardUtils.getKingCoords(board.getBoard(), currentPlayer)[0],
						BoardUtils.getKingCoords(board.getBoard(), currentPlayer)[1])) {
					System.out.println("Here1");
					delay(5000);
					screenNumber = 2;
				}

			}

		}
	}

	/**
	 * Resets the grid color to all white
	 * 
	 * @author Fardeen Kasmani
	 */
	public void resetGridColor() {
		for (int i = 0; i < rects.length; i++) {
			for (int j = 0; j < rects[0].length; j++) {
				rects[i][j].setStrokeR(125);
				rects[i][j].setStrokeG(125);
				rects[i][j].setStrokeB(125);
				rects[i][j].setFillR(255);
				rects[i][j].setFillG(255);
				rects[i][j].setFillB(255);

				if (i % 2 == 0 && j % 2 != 0) {
					rects[i][j].setFillR(79);
					rects[i][j].setFillG(197);
					rects[i][j].setFillB(247);
				} else if (i % 2 != 0 && j % 2 == 0) {
					rects[i][j].setFillR(79);
					rects[i][j].setFillG(197);
					rects[i][j].setFillB(247);
				}
			}
		}
	}

	/**
	 * Updates the Board's colours based on it's movement capabilities when a Piece
	 * is selected
	 * 
	 * @author Fardeen Kasmani
	 * @param pos
	 */
	public void updateGrid(int[][] pos) {

		for (int i = 0; i < pos.length; i++) {
			for (int j = 0; j < pos[0].length; j++) {
				if (pos[i][j] == 1) {
					rects[j][i].setFillR(125);
					rects[j][i].setFillG(125);
					rects[j][i].setFillB(125);
					rects[j][i].setStrokeR(0);
					rects[j][i].setStrokeG(0);
					rects[j][i].setStrokeB(0);
				} else if (pos[i][j] == 2) {
					rects[j][i].setFillR(255);
					rects[j][i].setFillG(120);
					rects[j][i].setFillB(120);

					rects[j][i].setStrokeR(0);
					rects[j][i].setStrokeG(0);
					rects[j][i].setStrokeB(0);

				} else {
					rects[j][i].setStrokeR(125);
					rects[j][i].setStrokeG(125);
					rects[j][i].setStrokeB(125);

					rects[j][i].setFillR(255);
					rects[j][i].setFillG(255);
					rects[j][i].setFillB(255);

					if (j % 2 == 0 && i % 2 != 0) {
						rects[j][i].setFillR(79);
						rects[j][i].setFillG(197);
						rects[j][i].setFillB(247);
					} else if (j % 2 != 0 && i % 2 == 0) {
						rects[j][i].setFillR(79);
						rects[j][i].setFillG(197);
						rects[j][i].setFillB(247);
					}

				}
			}
		}
	}

	/**
	 * Draws piece images to screen
	 * 
	 * @author Fardeen Kasmani
	 * @param pieces The board array
	 */
	public void drawPieces(Piece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces.length; j++) {
				if (pieces[i][j] != null) {
					int x = pieces[i][j].getPosX();
					int y = pieces[i][j].getPosY();

					imageMode(CENTER);

					int xDisplay = (x) * squareSize + squareSize / 2;
					int yDisplay = (y) * squareSize + squareSize / 2;

					image(images[pieces[i][j].id], xDisplay, yDisplay, squareSize, squareSize);
				}
			}
		}
	}

	/**
	 * Updates array based on a given map
	 * 
	 * @param source
	 * @param matte
	 * @return
	 */
	int[][] updateArray(int[][] source, int[][] matte) {
		for (int i = 0; i < matte.length; i++) {
			for (int j = 0; j < matte[i].length; j++) {
				if (matte[i][j] == 0) {
					source[i][j] = 0;
				}
			}
		}

		return source;
	}

	/**
	 * Class with utilities to aid with mouse detection and square drawing
	 * 
	 * @author Ibrahim Chehab
	 *
	 */
	class Rect {
		public float rectx, recty;
		private int[] fill = { 255, 255, 255 };
		private int[] stroke = { 0, 0, 0 };

		/**
		 * @author Fardeen Kasmani
		 * @param x
		 * @param y
		 */
		public Rect(int x, int y) {
			rectx = x;
			recty = y;
		}

		/**
		 * Updates rect size
		 * 
		 * @author Fardeen Kasmani
		 * @param x
		 * @param y
		 */
		public void update(int x, int y) {
			rectx = x;
			recty = y;
		}

		/**
		 * Draws rectangle to screen
		 * 
		 * @author Ibrahim Chehab
		 */
		public void drawRect() {
			pushStyle();
			stroke(stroke[0], stroke[1], stroke[2]);
			fill(fill[0], fill[1], fill[2]);
			rect(rectx, recty, squareSize, squareSize);
			popStyle();
		}

		/**
		 * Returns whether the rect is pressed
		 * 
		 * @author Ibrahim Chehab
		 * @return isPressed
		 */
		public boolean isPressed() {
			if (mousePressed && mouseX >= rectx + (width - (squareSize * 8)) / 2
					&& mouseX <= rectx + squareSize + (width - (squareSize * 8)) / 2 && mouseY >= recty
					&& mouseY <= recty + squareSize) {
				return true;
			}
			return false;
		}

		public void setFillR(int R) {
			fill[0] = R;
		}

		public void setFillG(int G) {
			fill[1] = G;
		}

		public void setFillB(int B) {
			fill[2] = B;
		}

		public void setStrokeR(int R) {
			stroke[0] = R;
		}

		public void setStrokeG(int G) {
			stroke[1] = G;
		}

		public void setStrokeB(int B) {
			stroke[2] = B;
		}

	}

	/**
	 * Sets screen size Required for PApplet
	 * 
	 * @author Fardeen Kasmani
	 */
	public void settings() {
		size(1280, 720);
	}

	/**
	 * Function which starts the PApplet class
	 * 
	 * @author Ibrahim Chehab
	 * @param passedArgs
	 */
	public static void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "Chess2D" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
}
