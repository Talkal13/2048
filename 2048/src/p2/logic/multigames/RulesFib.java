package p2.logic.multigames;

import java.util.Random;

import p2.logic.Board;
import p2.logic.Cell;
import p2.logic.GameRules;
import p2.util.Position;

/**
 * @author Pablo & Diego.
 * 
 * Class which implements the interface GameRules for the Fibonacci game.
 *
 */

public class RulesFib implements GameRules {
		
	/**
	 * Overrides the addNewCellAt method from the GameRules interface, and for this game the chances are 10% for the cell to be settled
	 * the value 2 and 90% a value 1.
	 * 
	 * @param board board where the value will be settled.
	 * @param pos position of the cell to set.
	 * @param rand numeric value to determine the value of the cell.
	 */
	
	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		int random = rand.nextInt(99);
		if (random < 10) {
			board.setCell(pos, 2);
		} else {
			board.setCell(pos, 1);
		}
	}

	/**
	 * Overrides the merge method from the GameRules interface, and for this game both cells must have the one value and the next in the fibonacci series,
	 * the resulting value will the addition of both and will be returned.
	 * 
	 * @param self cell where the merge will result with the addition of both cells.
	 * @param other cell where the merge will result with an empty cell.
	 * @return if the merge is possible the resulting number, if is not 0.
	 */
	
	@Override
	public int merge(Cell self, Cell other) {
		if (self.isEmpty() || other.isEmpty()) return 0;
		if (other.getValue() == next(self.getValue()) || self.getValue() == next(other.getValue()) || (self.getValue() == 1 && other.getValue() == 1)) {
			self.setValue(other.getValue() + self.getValue());
			other.emptyCell();
			return self.getValue();
		}
		return 0;
	}
	
	/**
	 * Method to calculate which is going to be the next number in the Fibonacci series to get, using three parameters.
	 * 
	 * @param numb number to calculate which is going to be the next digit in the series.
	 * @param sum 
	 * @param bef number that goes after the digit numb in the Fibonacci series.
	 * @return the next number in the Fibonacci series.
	 */
	
	private int next(int numb, int sum, int bef) {
		if (sum == numb) return sum + bef;
		else {
			return next(numb, sum + bef, sum);
		}
	}
	
	/**
	 * Method to calculate which is going to be the next number in the Fibonacci series to get, only using that number as parameter.
	 * 
	 * @param numb number to obtain the next value in the fibonacci series.
	 * @return the next number in the Fibonacci series.
	 */
	
	private int next(int numb) {
		return next(numb, 1, 1);
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
	 * Overrides the win method from the GameRules interface,and for this game is won if the maximum value is 144.
	 * 
	 * @param board board to check the maximum value from.
	 * @return the value to get in order to win the game.
	 */
	
	@Override
	public boolean win(Board board) {
		return (board.getMax() == 144);
	}
}
