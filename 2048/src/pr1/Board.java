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
	
	private MoveResult actualMove(Position cell, Position neightbour) {
		MoveResult r = new MoveResult();
		if (board[cell.getX()][cell.getY()].doMerge(board[neightbour.getX()][neightbour.getY()])) {
			r.setScore(r.getScore() + board[cell.getX()][cell.getY()].getValue() + board[neightbour.getX()][neightbour.getY()].getValue());
			if (board[neightbour.getX()][neightbour.getY()].getValue() > r.getValue()) {
				r.setValue(board[neightbour.getX()][neightbour.getY()].getValue());
			}
		}
		return r;

	}
	
	/**
	 * executes the displacing and merging of a move in the direction dir
	 * 
	 * @param dir direction to execute the movement to
	 * @return returns the Object containing the results
	 */
	public MoveResult executeMove(Direction dir){
		MoveResult result = new MoveResult();
		MoveResult tmp = new MoveResult();
		
		if (dir.equals(DirectionOption.DOWN)) {
			for (int i = 0; i < boardSize - 1; i++) {
				for (int j = 0; j < boardSize; j++) {
					result.add(actualMove(new Position(i, j), new Position(i + 1, j)));
					if (tmp.getScore() < result.getScore()) {
						j++;
					}
					tmp = result;
				}
			}
		}
		
		else if (dir.equals(DirectionOption.RIGHT)) {
			for (int i = 0; i < boardSize; i++) {
				for (int j = 0; j < boardSize - 1; j++) {
					
					result.add(actualMove(new Position(i, j), new Position(i, j + 1)));
					if (tmp.getScore() < result.getScore()) {
						i++;
					}
					tmp = result;
				}
			}
		}
		
		else if (dir.equals(DirectionOption.LEFT)) {
			for (int i = 0; i < boardSize; i++) {
				for (int j = boardSize - 1; j > 0; j--) {
					result.add(actualMove(new Position(i, j), new Position(i, j - 1)));
					if (tmp.getScore() < result.getScore()) {
						i--;
					}
					tmp = result;
				}
			}
		}
		
		else if (dir.equals(DirectionOption.UP)) {
			for (int i = boardSize - 1; i > 0; i--) {
				for (int j = 0; j < boardSize; j++) {
					result.add(actualMove(new Position(i, j), new Position(i - 1, j)));
					if (tmp.getScore() < result.getScore()) {
						j--;
					}
					tmp = result;
				}
			}
		}
		
		return result;
	}
}
