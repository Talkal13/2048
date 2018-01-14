package p2.logic;

import java.util.Random;

import p2.logic.Board;
import p2.util.ArrayAsList;
import p2.util.Position;

/**
 * @author Pablo & Diego
 * 
 * Interface of the rules of the different games.
 *
 */

public interface GameRules {
	
	/**
	 * Adds a value from the initial values possible using the random rand in the position and board passed as parameter.
	 * 
	 * @param board board where the cell to add is.
	 * @param pos position of the cell to initialize.
	 * @param rand provides the random seed to determine the value of the cell.
	 */

	void addNewCellAt(Board board, Position pos, Random rand);
	
	/**
	 * Merges two cells provided as parameter and returns the value of that merge.
	 * 
	 * @param self first cell to merge.
	 * @param other the second cell to merge with.
	 * @return the value resulting of that merge
	 */
	
	int merge(Cell self, Cell other);
	
	/**
	 * Gets the value to obtain during the game in order to win it.
	 * 
	 * @param board contains the board from where the wining value will be obtained from.
	 * @return an integer containing the wining value.
	 */
	
	int getWinValue(Board board);
	
	/**
	 * Checks if the board provided as parameter has a wining situation or not. 
	 * 
	 * @param board board where the check of a wining position will be done.
	 * @return true if is a wining display or false if not.
	 */
	
	boolean win(Board board);
	
	/**
	 * Method that will check if the board is in a situation where the game is lost.
	 * 
	 * @param board which will be checked in order to see if there is a loser situation.
	 * @return true in case the board is in a loser situation or false if is not.
	 */
	
	default boolean lose(Board board) {
		return board.getFree().isEmpty() && board.noMoves(this);
	}
	
	/**
	 * Method that given a certain size as parameter creates a new board of that size.
	 * 
	 * @param size indicates the size of the board to be created.
	 * @return an object board with the specific size.
	 */
	
	default Board createBoard(int size) {
		return new Board(size);
	}
	
	/**
	 * Method that inserts a new cell in the board passed as parameter, through the list of free positions
	 * using the method addNewCellAt of this class implemented by each gameRule.
	 * 
	 * @param board board where the cell will be included.
	 * @param rand pseudorandom number to decide the value of the cell.
	 */
	
	default void addNewCell(Board board, Random rand) {
		ArrayAsList.shuffle(board.getFree(), rand);
		Position pos = (Position) board.getFree().get(0);
		addNewCellAt(board, pos, rand);
		board.removeFree(pos);
	}
	
	default boolean canMergeNeighbours(Cell cell1, Cell cell2) {
		return cell1.getValue() == cell2.getValue();
	}
	
	/**
	 * Method that initializes the board passed as parameter setting the initial number of cells on it. 
	 * 
	 * @param board board where the cells will be allocated.
	 * @param numCells number of cells to set in the board.
	 * @param rand pseudorandom number to decide the value of the cell.
	 */
	
	default void initBoard(Board board, int numCells, Random rand) {
		for (int i = 0; i < numCells; i++) {
			addNewCell(board, rand);
		}
	}
	
}
