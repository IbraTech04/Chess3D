import java.util.ArrayList;

public class Player {
	private ArrayList<Piece> takenPieces; 
	
	public Player(int id) {
		takenPieces = new ArrayList<Piece>();
	}
	
	public void addToPile(Piece p) {
		takenPieces.add(p);
	}
}
