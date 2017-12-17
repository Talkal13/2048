package p2.util;

/**
 * @author Pablo & Diego
 *
 * Class which provides auxiliary methods to store the state of a game in a certain moment.
 */

public class GameState {
	private int [][] compactBoard = null;
	private int score;
	
	/**
	 * Stores the the value of each position of the board in the array of arrays passed as parameter.
	 * 
	 * @param a array of arrays where the state of the board with all it's current values will be store.
	 */
	
	public GameState(int [][] a) {
		compactBoard = a;
	}
	
	/**
	 * Changes the state of a board stored in the class attribute compactBoard, setting it as the array of arrays introduced as parameter.
	 * 
	 * @param a new state to store in the attribute compactBoard.
	 */
	
	public void setState(int [][] a) {
		compactBoard = a;
	}
	
	/**
	 * Setter of the attribute score.
	 * 
	 * @param s provides the new value for the attribute score.
	 */
	
	public void setScore(int s) {
		score = s;
	}
	
	/**
	 * Getter of the attribute compactBoard.
	 * 
	 * @return the state of the board with all the values of the positions the of board stored.
	 */
	
	public int[][] getState() {
		return compactBoard;
	}
	
	/**
	 * Getter of the attribute score.
	 * 
	 * @return the score of the board stored.
	 */
	
	public int getScore() {
		return score;
	}
	
}
