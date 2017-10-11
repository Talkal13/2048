package pr1;
/**
 * 
 * @author Pablo & Diego
 *
 *	Encapsulates the result of executing a move on the board
 */

public class MoveResult {
	
	private int score;
	
	public MoveResult(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean isEqualScore(int score) {
		return (this.score == score);
	}
}
