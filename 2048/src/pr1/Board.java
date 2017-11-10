package pr1;

import pr1.util.ArrayAsList;
import pr1.util.MyStringUtils;

/**
 *
 * @author Pablo and Diego
 *
 * Class which instance store the current state of a 2048 board and provides the methods to manipulate that state
 *
 */




public class Board {

	private Cell[][] board;
	private int size;
	private PositionAsList free;

	/**
	 * Construct of the class Board. creates a new board with the specified size
	 *
	 * @param size saves the dimension of the board
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

	public ArrayAsList getFree() {
		return free;
	}

	public int getSize() {
		return size;
	}
	
	public Cell getCell(int i, int j) {
		return board[i][j];
	}

	/**
	 * resets the board by emptying all it's cells
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
	 * Sets the value of the cell at a given position on the board
	 *
	 * @param pos position of the cell
	 * @param value value which will be settled in the cell on the position pos
	 */

	public void setCell(Position pos, int value){
		this.board[pos.getX()][pos.getY()].setValue(value);
		free.pop(pos);
	}

	/**
	 * checks if the cell in the position given by the paramethers is empty or not
	 *
	 * @param row row of the cell to check
	 * @param col column of the cell to check
	 * @return true in case the cell is empty or false if is not empty
	 */
	public boolean isBoardEmpty(int row, int col) {
		return this.board[row][col].isEmpty();
	}



	//TODO toString method
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

	private boolean canMerge(Cell a, Cell b) {
		if (!a.isEmpty() && !b.isEmpty() && a.getValue() == b.getValue()) {
			return true;
		}
		return false;
	}

	private boolean canMoveFree(Cell a, Cell b) {
		if (!a.isEmpty() && b.isEmpty()) {
			return true;
		}
		return false;
	}

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
	 * Performs a move to the right in the board of the game.
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
	 * Checks the paramether which tell us the direction to perform the movement, and calls to the corresponding method
	 *
	 * @param dir direction where we want to move
	 * @return the object MoveResult that results of the moving to the desired direction on the game
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
