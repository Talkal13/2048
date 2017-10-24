package pr1;
/**
 * 
 * @author Pablo and Diego
 *
 *	Encapsulates the result of executing a move on the board
 */

public class MoveResult {
	
	private int score = 0;
	private int value = 0;
	
	/**
	 * constructor of the class
	 * 
	 * 
	 */
	public MoveResult() {
	}

	/**
	 * getter of the class
	 * 
	 * @return the score of the game
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * getter of the class
	 * 
	 * @return the value of the highest cell
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 
	 * change the value of score
	 * 
	 * @param s the score
	 */
	
	public void setScore(int s) {
		this.score = s;
	}
	
	/**
	 * 
	 * Change the value of the value of the highest cell
	 * 
	 * @param v the value
	 */
	
	public void setValue(int v) {
		this.value = v;
	}
	
	/**
	 *  
	 * Merges two MoveResult objects 
	 *  
	 * @param a the object MoveResult to merge with
	 */
	
	public void add(MoveResult a) {
		score += a.score;
		value = a.getValue() > value ? a.getValue() : value;
	}
	
	/**
	 * checks wheter a score introduced as paramether and the one in the class is the same
	 * @param score to check if is equal
	 * @return true if is equal or false if doesnt
	 */
	public boolean isEqualScore(int score) {
		return (this.score == score);
	}
}
