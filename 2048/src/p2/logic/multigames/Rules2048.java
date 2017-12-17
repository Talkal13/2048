package p2.logic.multigames;

import java.util.Random;

import p2.logic.*;
import p2.util.Position;

/**
 * @author Pablo & Diego
 * 
 * Class which implements the interface GameRules for the game 2048.
 *
 */

public class Rules2048 implements GameRules {
	
	/**
	 * Overrides the addNewCellAt method from the GameRules interface, and for this game the chances are 10% for the cell to be settled
	 * the value 4 and 90% a value 2.
	 * 
	 * @param board board where the value will be settled.
	 * @param pos position of the cell to set.
	 * @param rand numeric value to determine the value of the cell.
	 */
	
	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		int random = rand.nextInt(99);
		if (random < 10) {
			board.setCell(pos, 4);
		} else {
			board.setCell(pos, 2);
		}
	}
	
	/**
	 * Overrides the merge method from the GameRules interface, and for this game both cells must have the same value and the resulting value 
	 * will be the power of two of that value and will be returned.
	 * 
	 * @param self cell where the merge will result with it´s value powered to 2.
	 * @param other cell where the merge will result with an empty cell.
	 * @return if the merge is possible the resulting number, if is not 0.
	 */

	@Override
	public int merge(Cell self, Cell other) {
		if (self.getValue() == other.getValue()) {
			self.setValue(self.getValue() * 2);
			other.emptyCell();
			return self.getValue();
		}
		else return 0;
	}

	/**
	 * Overrides the getWinValue method from the GameRules interface,and for this game the value that should be achieve in order to win the game,
	 * in this case is gotten through the method getMax from the board class.
	 * 
	 * @param board board to check the wining value from.
	 * @return the value to get in order to win the game.
	 */
	
	@Override
	public int getWinValue(Board board) {
		return board.getMax();
	}

	/**
	 * Overrides the win method from the GameRules interface,and for this game is won if the maximum value is 2048.
	 * 
	 * @param board board to check the maximum value from.
	 * @return the value to get in order to win the game.
	 */
	
	@Override
	public boolean win(Board board) {
		return (board.getMax() == 2048);
	}

}
