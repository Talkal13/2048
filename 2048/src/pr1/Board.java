package pr1;

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
	private int boardSize;
	
	
	/**
	 * Construct of the class Board. creates a new board with the specified size
	 * 
	 * @param size saves the dimension of the board
	 */
	
	public Board(int size){
		boardSize = size;
		board = new Cell[boardSize][boardSize];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = new Cell(i, j);
			}
		}
	}
	
	public void reset() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				board[i][j].emptyCell();
			}
		}
	}
	
	/**
	 * Sets the value of the cell at a given position on the board
	 * 
	 * @param pos position of the cell 
	 * @param value value which will be settled in the cell on the position pos
	 */
	public void setCell(Position pos, int value){
		this.board[pos.getX()][pos.getY()].setValue(value);
	}
	
	public boolean isBoardEmpty(int row, int col) {
		return this.board[row][col].isEmpty();
	}
	
	//TODO toString method
	public String toString() {
		String s = "";
		for (int i = 0; i < boardSize; i++) {
			//top separation line
			s += MyStringUtils.repeat("-", 8 * boardSize);
			s += "\n";
			
			for (int j = 0; j < boardSize; j++) {
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
		s += MyStringUtils.repeat("-", 8 * boardSize);
		s += "\n";
		
		return s;
	}
	
	private void transpose() {
		Cell tmp;
		for (int i = 0; i < boardSize; i++) {
			for (int j = i + 1; j < boardSize; j++) {
				tmp = board[i][j];
				board[i][j] = board[j][i];
				board[j][i] = tmp;
			}
		}
	}
	
	private void reflection() {
		Cell tmp;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize / 2; j++) {
				tmp = board[i][j];
				board[i][j] = board[i][boardSize - j - 1];
				board[i][boardSize - j - 1] = tmp;
			}
		}
	}
	
	
	private MoveResult move_right() {
		MoveResult r = new MoveResult();
		for (int i = 0; i < boardSize; i++) {
			for (int j = boardSize - 1; j > 0; j--) {
				if (board[i][j - 1].doMerge(board[i][j])) {
					if (r.getValue() < board[i][j].getValue()) {
						r.setValue(board[i][j].getValue());
					}
					r.setScore(r.getScore() + board[i][j].getValue());
				}
			}
		}
		return r;
	}
	
	private MoveResult move_left() {
		MoveResult r = new MoveResult();
		reflection();
		r = move_right();
		reflection();
		return r;
	}
	
	
	private MoveResult move_down() {
		MoveResult r = new MoveResult();
		transpose();
		r = move_right();
		transpose();
		return r;
	}
	
	private MoveResult move_up() {
		MoveResult r = new MoveResult();
		transpose();
		r = move_left();
		transpose();
		return r;
	}
	
	
	
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
