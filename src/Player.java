import java.util.ArrayList;
import processing.core.PApplet;

public class Player {
	private PApplet p;
	private ArrayList<Piece> takenPieces;
	private int id;

	/**
	 * Draws the pile of eliminated pieces for that player
	 * @author Ibrahim Chehab
	 * @param id player ID
	 * @param p Reference to main PApplet instance 
	 */	
	public Player(int id, PApplet p) {
		this.id = id;
		this.p = p;
		takenPieces = new ArrayList<Piece>();
	}

	public void addToPile(Piece p) {
		takenPieces.add(p);
	}

	/**
	 * Draws the pile of eliminated pieces for that player
	 * @author Ibrahim Chehab
	 * @param images
	 */
	public void drawPile(processing.core.PImage[] images) {
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
		
		if (id == 1) {
			coord = boardSize + sideSize;
		}
		
		for (Piece p : takenPieces) {
			this.p.image(images[p.id], coord, 0, squareSize, squareSize);
			this.p.translate(0,squareSize);
		}
		p.popMatrix();
	}
	
	/**
	 * Function which returns player ID
	 * @author Fardeen Kasmani
	 * @return id
	 */
	public int getPlayer() {
		return id;
	}
}
