package pr1;

import pr1.util.ArrayAsList;
import pr1.util.MyStringUtils;

/**
 * @author Pablo and Diego
 *
 * Class which instance store the current state of a 2048 board and provides the methods to manipulate that state.
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
	 * Getter to recive the Arraylist which contains the list of cells that currently has a score equals to 0.
	 * 
	 * @return the list with the free positions.
	 */

	public ArrayAsList getFree() {
		return free;
	}
	
	/**
	 * Getter of the paramether size.
	 * 
	 * @return the size of the board.
	 */

	public int getSize() {
		return size;
	}

	/**
	 * Resets the board by emptying all it's cells.
	 *
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
	 * Sets the value of the cell at a given position on the board.
	 *
	 * @param pos position of the cell.
	 * @param value value which will be settled in the cell on the position pos.
	 */

	public void setCell(Position pos, int value){
		this.board[pos.getX()][pos.getY()].setValue(value);
		free.pop(pos);
	}
	
	public boolean noMoves() {
		for (int i = 1; i < size - 1; i++) {
			for (int j = 1; i < size - 1; j++) {
				if (board[i][j].equals(board[i + 1][j])) return false;
				else if (board[i][j].equals(board[i - 1][j])) return false;
				else if (board[i][j].equals(board[i][j + 1])) return false;
				else if (board[i][j].equals(board[i][j - 1])) return false;
				
			}
		}
		return true;
	}

	/**
	 * Checks if the cell in the position given by the paramethers is empty or not.
	 *
	 * @param row row of the cell to check.
	 * @param col column of the cell to check.
	 * @return true in case the cell is empty or false if is not empty.
	 */
	
	public boolean isBoardEmpty(int row, int col) {
		return this.board[row][col].isEmpty();
	}

	/**
	 * Draws the board in that exact moment of the game, with all the differet values that each cell has.
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
	 * Goes through the right side of the board and for each position,the cell in that position is swapped for it's trasposed.
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
	 * Checks if to given cells will be abe to merge, this will happen if they are both non empty nad have the same value.
	 * 
	 * @param a First cell to check.
	 * @param b Second cell, the one which will be the neighbour.
	 * @return true in case that the merge of this cells is possible and false if is not.
	 */
	
	private boolean canMerge(Cell a, Cell b) {
		if (!a.isEmpty() && !b.isEmpty() && a.getValue() == b.getValue()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the movement between two cells can take place.
	 * 
	 * @param a First cell to check, this will take the place of the cell b, so cant be empty.
	 * @param b Second cell, this will have to be empty.
	 * @return true if the move can take place and false if doesnt.
	 */

	private boolean canMoveFree(Cell a, Cell b) {
		if (!a.isEmpty() && b.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Makes the first step of a right movement, by moving all the non empty cells to the right most side
	 * leaving the board ready to perform the move_right methd.
	 */
	
	private void moveFree_Right() {
		Cell a, b;
		
		for (int i = 0; i < size; i++) {
			for (int j = size - 1; j > 0; j--) {
				
				for (int k = j; k < size; k++) {
					a = board[i][k - 1]; b = board[i][k];
					if (canMoveFree(a, b)) {
						a.doMerge(b);
						free.insert(a.getPos());
						free.pop(b.getPos());
					}
				}
			}
		}
	}

	/**
	 * Performs the second step pfa move to the right in the board of the game.
	 * 
	 * @return the object MoveResult that results of the moving right on the game.
	 */

	private MoveResult move_right() {
		MoveResult r = new MoveResult();
		moveFree_Right();
		for (int i = 0; i < size; i++) {
			for (int j = size - 1; j > 0; j--) {
				
				for (int k = j; k < size; k++) {
					if (canMerge(board[i][k - 1], board[i][k]) ) {
						if (board[i][k - 1].doMerge(board[i][k])) {
							free.insert(board[i][k - 1].getPos());
							if (r.getValue() < board[i][k].getValue()) r.setValue(board[i][k].getValue());
							r.setScore(r.getScore() + board[i][k].getValue());
							k++;
							j--;
						}
					}
				}
			}
		}
		moveFree_Right();
		return r;
	}

	/**
	 * Performs a move to the left in the board of the game.
	 *
	 * @return the object MoveResult that results of the move to the left on the game
	 */

	private MoveResult move_left() {
		MoveResult r = new MoveResult();
		reflection();
		r = move_right();
		reflection();
		return r;
	}

	/**
	 * Performs a move down in the board of the game.
	 *
	 * @return the object MoveResult that results of the moving down on the game
	 */

	private MoveResult move_down() {
		MoveResult r = new MoveResult();
		transpose();
		r = move_right();
		transpose();
		return r;
	}

	/**
	 * Performs a move up in the board of the game.
	 *
	 * @return the object MoveResult that results of the moving up on the game
	 */

	private MoveResult move_up() {
		MoveResult r = new MoveResult();
		transpose();
		r = move_left();
		transpose();
		return r;
	}

	/**
	 * Checks the paramether which tell us the direction to perform the movement, and calls to the corresponding method.
	 *
	 * @param dir direction where we want to move.
	 * @return the object MoveResult that results of the moving to the desired direction on the game.
	 */

	public MoveResult executeMove(Direction dir) {
		MoveResult r = new MoveResult();
		if (dir.equals(DirectionOption.RIGHT)) {
			r = move_right();
		}
		else if (dir.equals(DirectionOption.DOWN)) {
			r = move_down();
		}
		else if (dir.equals(DirectionOption.UP)) {
			r = move_up();
		}
		else if (dir.equals(DirectionOption.LEFT)) {
			r = move_left();
		}
		return r;
	}
}
