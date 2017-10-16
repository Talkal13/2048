package pr1;
/**
 * 
 * @author Pablo & Diego
 *
 *	Encapsulates the result of executing a move on the board
 */

public class MoveResult {
	
	private int score;
	
	/**
	 * constructor of the class
	 * 
	 * @param score current scre of the game
	 */
	public MoveResult(int score) {
		this.score = score;
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
	 * checks wheter a score introduced as paramether and the one in the class is the same
	 * @param score to check if is equal
	 * @return true if is equal or false if doesnt
	 */
	public boolean isEqualScore(int score) {
		return (this.score == score);
	}
}
