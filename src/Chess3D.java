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

public class Chess3D extends PApplet {
	Piece lastChosenPiece;
	static int squareSize = 50;
	int currentPlayer = 0;

	// Loading all the textures and files required

	static Rect[][] rects;
	PShape[] models;
	PShape chessBoard;
	String[] names = { "king.obj", "queen.obj", "knight.obj", "bishop.obj", "rook.obj", "pawn.obj" };
	Board board;
	Player player1, player2;

	/**
	 * Setup function required for PApplet
	 * 
	 * @author Fardeen Kasmani
	 */
	public void setup() {
		textAlign(CENTER);
		text("Rendering... Please Wait", width / 2, height / 2);
		player1 = new Player(0, this);
		player2 = new Player(1, this);
		board = new Board(this);
		surface.setTitle("Chess3D - Alpha Release v0.5.1");
		surface.setResizable(true);
		initBoard2D();
		models = new PShape[6];
		for (int i = 0; i < 6; i++) {
			models[i] = loadShape("data/" + names[i]);
		}
	}

	/**
	 * Draw function required for PApplet
	 * 
	 * @author Ibrahim Chehab
	 */
	public void draw() {
		background(255);
		lights();
		pushMatrix();
		translate((width - (squareSize * 8)) / 2, (height - (squareSize * 8)) / 1.5f);
		rotateX(PI / 3);
		// pushMatrix();
		// translate((width - (squareSize * 8)) / 2, 0);
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
		player1.drawPile(models);
		player2.drawPile(models);

	}

	/**
	 * Method which resizes squares on screen depending on window size
	 * 
	 * @author Ibrahim Chehab
	 */
	/*
	 * public void updateRectSize() {
	 * 
	 * if (width > height) { squareSize = height / 8; } else { squareSize = width /
	 * 8; }
	 * 
	 * for (int i = 0; i < 8; i++) { for (int j = 0; j < 8; j++) {
	 * rects[i][j].update(i * squareSize, j * squareSize); } } }
	 */

	public void updateRectSize() {

		if (width > height) {
			squareSize = height / 14;
		} else {
			squareSize = width / 14;
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
				rects[i][j] = new Rect(i * squareSize, j * squareSize, i, j);
			}
		}
	}

	/**
	 * Method which checks whether the users computer is capable of running our
	 * game. If not, it will alert the user
	 * 
	 * @author Fardeen Kasmani
	 * @return
	 */
	public int checkGameRequriements(PApplet pa) {
		System.getProperties().list(System.out);
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			GameUtils.getDXDiag();
			XML xml = pa.loadXML("dxdiag.xml");

			String CPU = (xml.getChild("SystemInformation").getChild("Processor").getContent());
			int memory = Integer.parseInt(xml.getChild("SystemInformation").getChild("Memory").getContent());
			String graphicsCard = (xml.getChild("DisplayDevices").getChild("DisplayDevice").getChild("CardName")
					.getContent());

		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {

		}
		return 10;
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
	 * @author Ibrahim Chehab
	 * @param pieces The board array
	 */
	public void drawPieces(Piece[][] pieces) {
		shininess((float) 1.0);
		translate(squareSize / 2, squareSize / 2);
		// scale(squareSize, squareSize);

		for (int i = 0; i < pieces.length; i++) {
			pushMatrix();
			rotateX(PI / 2);

			for (int j = 0; j < pieces.length; j++) {

				if (pieces[i][j] != null) {
					/*
					 * int x = pieces[i][j].getPosX(); int y = pieces[i][j].getPosY(); pushMatrix();
					 * rotateX(-PI/2); shapeMode(CENTER); ellipseMode(CENTER); int xDisplay = (x) *
					 * squareSize + squareSize / 2; int yDisplay = (y) * squareSize + squareSize /
					 * 2; ellipse(xDisplay, yDisplay, 50, 50); shape(models[pieces[i][j].id],
					 * xDisplay, yDisplay); popMatrix();
					 */
					// ellipse(0, 0, 50, 50);
					float rotate = PI / 2;

					if (pieces[i][j].getPlayer() == 1) {
						rotate *= -1;
					}

					pushMatrix();
					scale(2, 2, 2);
					rotateY(rotate);
					shape(models[pieces[i][j].id], 0, 0);
					popMatrix();
				}
				translate(squareSize, 0, 0);
			}
			popMatrix();
			translate(0, squareSize, 0);

		}
	}

	private void scale(double d, double e, double f) {
		// TODO Auto-generated method stub

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
		int idX;
		int idY;
		float x1;
		float y1;
		float x2;
		float y2;

		/**
		 * @author Fardeen Kasmani
		 * @param x
		 * @param y
		 * @param idX
		 * @param idY
		 */
		public Rect(int x, int y, int idX, int idY) {
			rectx = x;
			recty = y;
			this.idX = idX;
			this.idY = idY;
			this.x1 = idX * squareSize;
			this.y1 = idY * squareSize;
			this.x2 = x1 + squareSize;
			this.y2 = y1 + squareSize;
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
			this.x1 = idX * squareSize;
			this.y1 = idY * squareSize;
			this.x2 = x1 + squareSize;
			this.y2 = y1 + squareSize;

			float x1T = screenX(x1, y1, 0);
			float y1T = screenY(x1, y1, 0);
			float x2T = screenX(x2, y2, 0);
			float y2T = screenY(x2, y2, 0);

			x1 = x1T;
			x2 = x2T;

			y1 = y1T;
			y2 = y2T;

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
			if (mouseX >= x1 && mouseX < x2 && mouseY >= y1 && mouseY < y2) {
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
		size(1280, 720, P3D);
	}

	/**
	 * Function which starts the PApplet class
	 * 
	 * @author Ibrahim Chehab
	 * @param passedArgs
	 */
	public static void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "Chess3D" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
}
