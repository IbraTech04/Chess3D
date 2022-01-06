/*public class Main {
	public static void main(String[] args) {
		Board board = new Board();

		printArray(board.getBoard());

		board.movePiece(null, null, 3, 1, 3, 2);

		printArray(board.playerOnePawns[3].getMove(board.getBoard()));

		board.movePiece(null, null, 3, 0, 3, 1);
		
		printArray(board.playerOneQueen.getMove(board.getBoard()));

		printArray(board.getBoard());

	}

	static void printArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

	}

	static void printArray(Piece[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i][j] != null) {
					System.out.print(array[i][j].getClass().getSimpleName() + " ");
				} else {
					System.out.print("Null ");
				}
			}
			System.out.println();
		}
		System.out.println();

	}
}*/

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
	int currentPlayer = 0;
//Chess 3D
//By Ibrahim Chehab and Fardeen Kasmani

//Loading all the textures and files required

	Rect[][] rects;
	PImage[] images;
	String[] names = { "king.png", "queen.png", "knight.png", "bishop.png", "rook.png", "pawn.png" };
	Board board;

	public void setup() {
		board = new Board();
		surface.setTitle("Chess3D - Alpha Release v0.5");
		surface.setResizable(true);
		initBoard2D();
		images = new PImage[6];
		for (int i = 0; i < 6; i++) {
			images[i] = loadImage("data/" + names[i]);
		}
	}

//width-(squareSize*8)

	public void draw() {
		background(0);
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
	}

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

	public void initBoard2D() {
		rects = new Rect[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				rects[i][j] = new Rect(i * squareSize, j * squareSize);
			}
		}
	}

	public void mousePressed() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (rects[i][j].isPressed()) {
					Piece[][] p = board.getBoard();
					if (p[j][i] != null) {
						if (p[j][i].getPlayer() != currentPlayer && lastChosenPiece != null) {
							int[][] coords = lastChosenPiece.getMove(p);
							if (coords[j][i] == 2) {
								board.movePiece(null, null, lastChosenPiece.getPosX(), lastChosenPiece.getPosY(), i, j);
								resetGridColor();
								lastChosenPiece = null;
								if (currentPlayer == 1) {
									currentPlayer = 0;
								} else {
									currentPlayer = 1;
								}
							}
						} else if (p[j][i].getPlayer() == currentPlayer) {
							lastChosenPiece = p[j][i];
							updateGrid(p[j][i].getMove(board.getBoard()));
						}
					} else {
						if (lastChosenPiece != null) {
							int[][] pos = lastChosenPiece.getMove(board.getBoard());
							if (pos[j][i] != 0) {
								board.movePiece(null, null, lastChosenPiece.getPosX(), lastChosenPiece.getPosY(), i, j);
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

	public void resetGridColor() {
		for (int i = 0; i < rects.length; i++) {
			for (int j = 0; j < rects[0].length; j++) {
				rects[j][i].stroke = 125;
				rects[j][i].fill = 255;
			}
		}
	}

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

	class Rect {
		float rectx, recty;
		int fill = 255;
		int stroke = 0;

		public Rect(int x, int y) {
			rectx = x;
			recty = y;
		}

		public void update(int x, int y) {
			rectx = x;
			recty = y;
		}

		public void drawRect() {
			stroke(stroke);
			fill(fill);
			rect(rectx, recty, squareSize, squareSize);
		}

		public boolean isPressed() {
			if (mousePressed && mouseX >= rectx + (width - (squareSize * 8)) / 2
					&& mouseX <= rectx + squareSize + (width - (squareSize * 8)) / 2 && mouseY >= recty
					&& mouseY <= recty + squareSize) {
				return true;
			}
			return false;
		}
	}

	public void settings() {
		size(1280, 720);
	}

	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "Chess3D" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
}
