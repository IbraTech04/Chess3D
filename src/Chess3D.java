// Chess 3D
// By Ibrahim Chehab and Fardeen Kasmani

import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class Chess3D extends PApplet {
	Piece lastChosenPiece;
	int squareSize = 50;
	int currentPlayer = 1;

	// Loading all the textures and files required

	Rect[][] rects;
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
		surface.setTitle("Chess3D - Alpha Release v0.5");
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
		background(255);
		pushMatrix();
		translate((width - (squareSize * 8)) / 2, 0);
		updateRectSize();
		stroke(255);
		fill(0);
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
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (rects[i][j].isPressed()) {
					Piece[][] p = board.getBoard();
					if (p[j][i] != null) {
						if (p[j][i].getPlayer() != currentPlayer && lastChosenPiece != null) {
							int[][] coords = lastChosenPiece.getMove(p);
							if (coords[j][i] == 2) {
								board.movePiece(player1, player2, lastChosenPiece.getPosX(), lastChosenPiece.getPosY(),
										i, j);
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
									updateGrid(p[j][i].getMove(board.getBoard()));
								}
							} else {
								lastChosenPiece = p[j][i];
								updateGrid(p[j][i].getMove(board.getBoard()));
							}
						}
					} else {
						if (lastChosenPiece != null) {
							int[][] pos = lastChosenPiece.getMove(board.getBoard());
							if (pos[j][i] != 0) {
								board.movePiece(player1, player2, lastChosenPiece.getPosX(), lastChosenPiece.getPosY(),
										i, j);
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
	}

	/**
	 * Resets the grid color to all white
	 * 
	 * @author Fardeen Kasmani
	 */
	public void resetGridColor() {
		for (int i = 0; i < rects.length; i++) {
			for (int j = 0; j < rects[0].length; j++) {
				rects[j][i].stroke = 125;
				rects[j][i].fill = 255;
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
					rects[j][i].fill = 125;
					rects[j][i].stroke = 0;
				} else if (pos[i][j] == 2) {
					rects[j][i].fill = 200;
					rects[j][i].stroke = 0;
				} else {
					rects[j][i].stroke = 125;
					rects[j][i].fill = 255;
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
	 * Class with utilities to aid with mouse detection and square drawing
	 * 
	 * @author Ibrahim Chehab
	 *
	 */
	class Rect {
		float rectx, recty;
		int fill = 255;
		int stroke = 0;

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
			stroke(stroke);
			fill(fill);
			rect(rectx, recty, squareSize, squareSize);
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
		GameUtils.checkGameRequriements();
		String[] appletArgs = new String[] { "Chess3D" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
}
