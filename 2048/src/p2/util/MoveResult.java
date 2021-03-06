package p2.util;

/**
 * @author Pablo and Diego
 *
 *	Encapsulates the result of executing a move on the board.
 */

public class MoveResult {
	
	private int score = 0;
	
	/**
	 * Constructor of the class
	 */
	
	public MoveResult() {
	}

	/**
	 * Getter of the class for the score.
	 * 
	 * @return the score of the game.
	 */
	
	public int getScore() {
		return score;
	}
	
	/**
	 * Setter for the value of score.
	 * 
	 * @param s the score.
	 */
	
	public void setScore(int s) {
		this.score = s;
	}
	
	/** 
	 * Adds the score of two MoveResult objects. 
	 *  
	 * @param a the object MoveResult to merge with.
	 */
	
	public void add(MoveResult a) {
		score += a.score;
	}
	
	/** 
	 * Adds to the score an integer passed as parameter. 
	 *  
	 * @param a integer to add to the score.
	 */
	
	public void addScore(int a) {
		score += a;
	}
	
	/**
	 * Checks whether a score introduced as parameter and the one in the class is the same.
	 * 
	 * @param score to check if is equal.
	 * @return true if is equal or false if doesn't.
	 */
	
	public boolean isEqualScore(int score) {
		return (this.score == score);
	}
}
