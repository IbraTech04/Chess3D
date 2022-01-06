/**
 * The main class which all the piece types extend
 *
 * @author Ibrahim Chehab
 */
abstract class Piece {
	public Piece(int player, int x, int y) {
		posX = x;
		posY = y;
		this.player = player;
	}

	public abstract int[][] getMove(Piece[][] boardStatus);

	private int player;
	private int posX;
	private int posY;
	public int id;

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosX(int x) {
		posX = x;
	}

	public void setPosY(int y) {
		posY = y;
	}
}