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
	private ArrayAsList free;
	
	/**
	 * Construct of the class Board. creates a new board with the specified size
	 * 
	 * @param size saves the dimension of the board
	 */
	
	public Board(int size){
		this.size = size;
		board = new Cell[size][size];
		free = new ArrayAsList(size * size);
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
	
	/**
	 * resets the board by emptying all it's cells
	 * 
	 */
	
	public void reset() {
		free = new ArrayAsList(size * size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j].emptyCell();
				free.insert(new Position(i, j));
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
	
	private int canMerge(Cell a, Cell b) {
		if (!a.isEmpty() && !b.isEmpty() && b.getValue() == a.getValue()) return 0;
		else if (!a.isEmpty() && !b.isEmpty()) return 1;
		else if (a.isEmpty() && !b.isEmpty()) return 2;
		else if (!a.isEmpty() && b.isEmpty()) return 3;
		else return 4;
	}
	
	private boolean executeMerge(Cell a, Cell b) {
		return a.doMerge(b);
	}
	
	private boolean displaceCell(Cell a, Cell b) {
		return a.doMerge(b);
	}
	
	/**
	 * Goes through the right side of the board and for each position,the cell in that position is swapped for it's trasposed.
	 * The trasposie of one position is the one which has the same value for the row and column but inverted.
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
	 * Goes through the top right side of the board and for each position,the cell in that position is swapped for refected one.
	 * The refection of one position is the one the same row and the reflected column.
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
	 * Performs a move to the right in the board of the game.	
	 */
	
	
	private void execute() {
		int fini = 0, cini = size - 1,incrc = -1,incrf = 0;
		for(int i = 0; i < size; i++){
			fini = i;
			int k = fini;
			int j = size - 1;
			while ((j < size) && (j >= 0) && (k < size) && (k >= 0)){	
				if(board[k][j].getValue() == board[k+incrf][j+incrc].getValue() && board[k][j].getValue() != 0){
					if( (j != size-1 || (j == size-1 && incrc <= 0) ) && (j != 0 || (j == 0 && incrc >= 0) ) ) {
						board[k][j].setValue(board[k][j].getValue() * 2);
						board[k+incrf][j+incrc].setValue(0);
						k += incrf;
						j += incrc;
					}
					k += incrf;
					j += incrc;
				}else{
				
					fini += incrf;
					cini += incrc;
					k = fini;
					j = cini;
				}
			}
		}	
	}
	
	private void moveFree_right() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				if (!board[i][j].isEmpty() && board[i][j + 1].isEmpty()) {
					board[i][j + 1].setValue(board[i][j].getValue());
					board[i][j].emptyCell();
					free.insert(board[i][j]);
				}
			}
		}
	}
	
	
	private MoveResult move_right() {
		MoveResult r = new MoveResult();
		moveFree_right();
		System.out.print(this);
		/*for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				board[i][j + 1].setStatus(false);
				switch(canMerge(board[i][j], board[i][j + 1])) {
				case 0:
					executeMerge(board[i][j], board[i][j + 1]);
					if (r.getValue() < board[i][j + 1].getValue()) {
						r.setValue(board[i][j + 1].getValue());
					}
					r.setScore(r.getScore() + board[i][j + 1].getValue());
					free.insert(board[i][j]);
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					executeMerge(board[i][j], board[i][j + 1]);
					free.insert(board[i][j]);
					free.pop(board[i][j + 1]);
					break;
				case 4:
					j++;
					break;
				}
			}
		}*/
		execute();
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
