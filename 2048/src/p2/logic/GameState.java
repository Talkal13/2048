package p2.logic;

public class GameState {
	private int [][] compactBoard = null;
	
	public GameState(int [][] a) {
		compactBoard = a;
	}
	
	public void setState(int [][] a) {
		compactBoard = a;
	}
	
	public int[][] getState() {
		return compactBoard;
	}
	
}
