import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PConstants;

public class Player {
	private PApplet p;
	private ArrayList<Piece> takenPieces;
	private int id;

	/**
	 * Draws the pile of eliminated pieces for that player
	 * 
	 * @author Ibrahim Chehab
	 * @param id player ID
	 * @param p  Reference to main PApplet instance
	 */
	public Player(int id, PApplet p) {
		this.id = id;
		this.p = p;
		takenPieces = new ArrayList<Piece>();
	}

	/**
	 * Adds Eliminated Pieces to the Eliminated Pile
	 * 
	 * @author Fardeen Kasmani
	 * @param p
	 */
	public void addToPile(Piece p) {
		takenPieces.add(p);
	}

	/**
	 * Draws the pile of eliminated pieces for that player
	 * 
	 * @author Ibrahim Chehab
	 * @param models
	 */
	public void drawPile(PShape[] models) {
		int squareSize;
		if (p.width > p.height) {
			squareSize = p.height / 8;
		} else {
			squareSize = p.width / 8;
		}
		int boardSize = squareSize * 8;

		int sideSize = (p.width - boardSize) / 2;

		p.pushMatrix();

		p.imageMode(0);

		int coord = 0;
		int startingCoord = 0;
		int size = squareSize;
		if (id == 1) {
			coord = boardSize + sideSize;
			squareSize *= -1;
			startingCoord = this.p.height - size / 1;
		}

		for (Piece p : takenPieces) {
			// this.p.scale(0.5f, 0.5f);
			this.p.rotateZ(PConstants.PI);
			this.p.shape(models[p.id], coord, startingCoord, size / 2, size / 2);
			this.p.translate(0, squareSize / 2);
		}
		p.popMatrix();
	}

	public void drawPile(PImage[] models) {
		int squareSize;
		if (p.width > p.height) {
			squareSize = p.height / 8;
		} else {
			squareSize = p.width / 8;
		}
		int boardSize = squareSize * 8;

		int sideSize = (p.width - boardSize) / 2;

		p.pushMatrix();

		p.imageMode(0);

		int coord = 0;
		int startingCoord = 0;
		int size = squareSize;
		if (id == 1) {
			coord = boardSize + sideSize;
			squareSize *= -1;
			startingCoord = this.p.height - size / 1;
		}

		for (Piece p : takenPieces) {
			this.p.image(models[p.id], coord, startingCoord, size / 2, size / 2);
			this.p.translate(0, squareSize / 2);
		}
		p.popMatrix();
	}

	/**
	 * Function which returns player ID
	 * 
	 * @author Fardeen Kasmani
	 * @return id
	 */
	public int getPlayer() {
		return id;
	}

}
