package pr1;

/**
 * @author Pablo and Diego
 *
 *	Encapsulates the result of executing a move on the board.
 */

public class MoveResult {
	
	private int score = 0;
	private int value = 0;
	
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
	 * Getter of the class for the value.
	 * 
	 * @return the value of the highest cell.
	 */
	
	public int getValue() {
		return value;
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
	 * Setter for the value of the highest cell.
	 * 
	 * @param v the value
	 */
	
	public void setValue(int v) {
		this.value = v;
	}
	
	/** 
	 * Adds the score of two MoveResult objects. 
	 *  
	 * @param a the object MoveResult to merge with.
	 */
	
	public void add(MoveResult a) {
		score += a.score;
		value = a.getValue() > value ? a.getValue() : value;
	}
	
	/**
	 * Checks wheter a score introduced as paramether and the one in the class is the same.
	 * 
	 * @param score to check if is equal.
	 * @return true if is equal or false if doesnt.
	 */
	
	public boolean isEqualScore(int score) {
		return (this.score == score);
	}
}
