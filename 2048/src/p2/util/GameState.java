package p2.util;

public class GameState {
	private int [][] compactBoard = null;
	private int score;
	
	public GameState(int [][] a) {
		compactBoard = a;
	}
	
	public void setState(int [][] a) {
		compactBoard = a;
	}
	
	public void setScore(int s) {
		score = s;
	}
	
	public int[][] getState() {
		return compactBoard;
	}
	
	public int getScore() {
		return score;
	}
	
}
