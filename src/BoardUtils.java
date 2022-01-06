class BoardUtils {
	static int boardSize = 8;

	public static boolean isCoord(int x, int y) {
		return (x >= 0 && x < boardSize && y >= 0 && y < boardSize);
	}
}