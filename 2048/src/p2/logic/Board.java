package p2.logic;

import p2.util.*;

/**
 * @author Pablo and Diego
 *
 * Class which instance store the current state of a 2048 board and provides the methods to manipulate that state.
 *
 */

public class Board {

	private Cell[][] board;
	private int size;
	private PositionAsList free;

	/**
	 * Construct of the class Board. creates a new board with the specified size.
	 *
	 * @param size saves the dimension of the board.
	 */

	public Board(int size){
		this.size = size;
		board = new Cell[size][size];
		free = new PositionAsList(size * size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = new Cell(i, j);
				free.insert(new Position(i, j));
			}
		}
	}
	
	/**
	 * Getter method of the attribute free.
	 * 
	 * @return the PositionAsList attribute free.
	 */
	
	public ArrayAsList getFree() {
		return free;
	}

	/**
	 * Getter method of the attribute size.
	 * 
	 * @return the  attribute size.
	 */
	
	public int getSize() {
		return size;
	}
	
	/**
	 * Calculates the value of the cell with highest value.
	 * 
	 * @return the highest value in the board.
	 */
	
	public int getMax() {
		int max = board[0][0].getValue();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (max < board[i][j].getValue()) max = board[i][j].getValue();
			}
		}
		return max;
	}
	
	/**
	 * Calculates the value of the cell with lowest value.
	 * 
	 * @return the lowest value in the board.
	 */
	
	public int getMin() {
		int min = board[0][0].getValue();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (min > board[i][j].getValue()) min = board[i][j].getValue();
			}
		}
		return min;
	}
	
	/**
	 * Checks if the board have all it's cells occupied.
	 * 
	 * @return true is the board is full and false if is not.
	 */
	
	public boolean isFull() {
		boolean x = true;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				x &= !board[i][j].isEmpty();
			}
		}
		return x;
	}
	
	/**
	 * Resets the board by emptying all it's cells.
	 */

	public void reset() {
		free = new PositionAsList(size * size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j].emptyCell();
				free.insert(new Position(i, j));
			}
		}
	}
	

	/**
	 * Check if in the board there is any movement possible by checking if there are two cells one next to the other with the same value.
	 * 
	 * @return true in the case that there is no possibility of movement in any cell or false if there is.
	 */
	
	public boolean noMoves() {
		for (int i = 1; i < size - 1; i++) {
			for (int j = 1; j < size - 1; j++) {
				
				if (board[i][j].equals(board[i + 1][j])) return false;
				else if (board[i][j].equals(board[i - 1][j])) return false;
				else if (board[i][j].equals(board[i][j + 1])) return false;
				else if (board[i][j].equals(board[i][j - 1])) return false;
				
			}
		}
		return true;
	}

	/**
	 * Sets the value of the cell at a given position on the board.
	 *
	 * @param pos position of the cell.
	 * @param value value which will be settled in the cell on the position pos.
	 */

	public void setCell(Position pos, int value){
		this.board[pos.getX()][pos.getY()].setValue(value);
		free.pop(pos);
	}

	/**
	 * Checks if the cell in the position given by the parameters is empty or not.
	 *
	 * @param row row of the cell to check.
	 * @param col column of the cell to check.
	 * @return true in case the cell is empty or false if is not empty.
	 */
	
	public boolean isBoardEmpty(int row, int col) {
		return this.board[row][col].isEmpty();
	}

	/**
	 * Draws the board in that exact moment of the game, with all the different values that each cell has.
	 */
	
	public String toString() {
		String s = "";
		for (int i = 0; i < size; i++) {
			//top separation line
			s += MyStringUtils.repeat("-", 8 * size);
			s += "\n";

			for (int j = 0; j < size; j++) {
				s += "|";
				if (board[i][j].isEmpty()) {
					s += MyStringUtils.repeat(" ", 7);
				}
				else {
					s += MyStringUtils.centre(board[i][j].toString(), 7);
				}
			}
			s += "|\n";
		}

		//bottom separation line
		s += MyStringUtils.repeat("-", 8 * size);
		s += "\n";

		return s;
	}

	/**
	 * Goes through the right side of the board and for each position,the cell in that position is swapped for it's transposed.
	 * The transpose of one position is the one which has the same value for the row and column but inverted.
	 */

	private void transpose() {
		Cell tmp;
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				tmp = board[i][j];
				board[i][j] = board[j][i];
				board[j][i] = tmp;
			}
		}
	}

	/**
	 * Goes through the top right side of the board and for each position,the cell in that position is swapped for reflected one.
	 * The reflection of one position is the one the same row and the reflected column.
	 */

	private void reflection() {
		Cell tmp;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size / 2; j++) {
				tmp = board[i][j];
				board[i][j] = board[i][size - j - 1];
				board[i][size - j - 1] = tmp;
			}
		}
	}
	
	/**
	 * Check if two cells passed as attribute can merge.
	 * 
	 * @param a first cell to check.
	 * @param b second cell to check.
	 * 
	 * @return true if the merge can happen or false if doesn't.
	 */
	
	private boolean canMerge(Cell a, Cell b) {
		if (!a.isEmpty() && !b.isEmpty() && a.getValue() == b.getValue()) {
			return true;
		}
		return false;
	}

	/**
	 * Check if one cell can move to the cell in its right.
	 * 
	 * @param a cell which must has some value to move to the right.
	 * @param b cell that is on the right and should be empty.
	 * 
	 * @return true if the movement can happen or not.
	 */
	
	private boolean canMoveFree(Cell a, Cell b) {
		if (!a.isEmpty() && b.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Moves all the tokens to the most right position going through all the empty cells.
	 */
	
	private void moveFree_Right() {
		Cell a, b;
		for (int i = 0; i < size; i++) {
			for (int j = size - 1; j > 0; j--) {
				for (int k = j; k < size; k++) {
					a = board[i][k - 1]; b = board[i][k];
					if (canMoveFree(a, b)) {
						b.setValue(a.getValue());
						a.emptyCell();
						free.insert(a.getPos());
						free.pop(b.getPos());
					}
				}
			}
		}
	}

	/**
	 * Performs a move to the right in the board of the game, making merges.
	 */

	private MoveResult move_right(GameRules currentRules) {
		MoveResult r = new MoveResult();
		moveFree_Right();
		for (int i = 0; i < size; i++) {
			for (int j = size - 2; j >= 0; j--) {
				r.addScore(board[i][j + 1].doMerge(board[i][j], currentRules));
				free.insert(board[i][j].getPos());
			}
		}
		moveFree_Right();
		return r;
	}
	
	/**
	 * removes the position indicates as parameter from the list of free positions.
	 * 
	 * @param pos position where is the free position of the array to remove. 
	 */
	public void removeFree(Position pos) {
		free.pop(pos);
	}

	/**
	 * Performs a move to the left in the board of the game.
	 *
	 * @return the object MoveResult that results of the move to the left on the game.
	 */

	private MoveResult move_left(GameRules currentRules) {
		MoveResult r = new MoveResult();
		reflection();
		r = move_right(currentRules);
		reflection();
		return r;
	}

	/**
	 * Performs a move down in the board of the game.
	 *
	 * @return the object MoveResult that results of the moving down on the game.
	 */

	private MoveResult move_down(GameRules currentRules) {
		MoveResult r = new MoveResult();
		transpose();
		r = move_right(currentRules);
		transpose();
		return r;
	}

	/**
	 * Performs a move up in the board of the game.
	 *
	 * @return the object MoveResult that results of the moving up on the game.
	 */

	private MoveResult move_up(GameRules currentRules) {
		MoveResult r = new MoveResult();
		transpose();
		r = move_left(currentRules);
		transpose();
		return r;
	}

	/**
	 * Checks the parameter which tell us the direction to perform the movement, and calls to the corresponding method.
	 *
	 * @param dir direction where we want to move.
	 * @return the object MoveResult that results of the moving to the desired direction on the game.
	 */

	public MoveResult executeMove(Direction dir, GameRules currentRules) {
		MoveResult r = new MoveResult();
		if (dir.equals(DirectionOption.RIGHT)) {
			r = move_right(currentRules);
		}
		else if (dir.equals(DirectionOption.DOWN)) {
			r = move_down(currentRules);
		}
		else if (dir.equals(DirectionOption.UP)) {
			r = move_up(currentRules);
		}
		else if (dir.equals(DirectionOption.LEFT)) {
			r = move_left(currentRules);
		}
		return r;
	}
	
	/**
	 * Generates an array of arrays containing just the values of the cells in the same corresponding position of the board.
	 * 
	 * @return the array of arrays with all the values of the board.
	 */
	
	public int[][] getState() {
		int [][] compactBoard = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				compactBoard[i][j] = board[i][j].getValue();
			}
		}
		return compactBoard;
	}
	
	/**
	 * Given an array of arrays this method sets the value of each position to the one corresponding from the board.
	 * 
	 * @param aState array of array to set its values to the ones in the board.
	 */
	
	public void setState(int [][] aState) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j].setValue(aState[i][j]);
			}
		}
	}
}
