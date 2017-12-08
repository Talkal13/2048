package p2.logic.multigames;

import java.util.Random;

import p2.logic.ArrayState;
import p2.logic.GameState;
import p2.util.ArrayAsList;
import p2.util.Direction;
import p2.util.MoveResult;
import p2.util.Position;

/**
 *
 * @author Pablo and Diego
 * @version 1.0
 * @since 1.0
 *
 *Class which represents the 2048 game
 */

public class Game {

		private Board board;
		private int size;
		private int initCells;
		private Random myRandom;
		private int score;
		private int highestValueCell;
		//private ArrayState states;


		public int getHigh() {
			return highestValueCell;
		}

		public void insertCell(int value, Position pos) {
			board.setCell(pos, value);
		}

		/**
		 * Inserts in a random empty cell a 4 with a 10% of chances or a 2 with te other 90%
		 *
		 */

		public void insertRandCell() {

			ArrayAsList.shuffle(board.getFree(), myRandom);

			if (myRandom.nextInt(100) < 10) {
				board.setCell((Position) board.getFree().get(0), 4);
				if (this.highestValueCell < 4)
					this.highestValueCell = 4;
			}
			else {
				board.setCell((Position) board.getFree().get(0), 2);
				if (this.highestValueCell < 2)
					this.highestValueCell = 2;
			}
		}

		/**
		 * Constructor of the class, saves the 3 values passed as arguments
		 *
		 * @param sizeBoard size of the board of the game
		 * @param numCells number of cells displayed at the start of the game
		 * @param random value to be used for the random behaviour of the game.
		 */
		public Game(int sizeBoard,int numCells, Random random){
			size = sizeBoard;
			initCells = numCells;
			myRandom = random;
			board = new Board(sizeBoard);
			for (int i = 0; i < numCells; i++) {
				insertRandCell();
			}
		}

		/**
		 * Method that performs a move in the direction dir in the board it updates the score and the value of the highest value
		 *
		 * @param dir direction in which the move will take place.
		 */
		public void move (Direction dir) {
			MoveResult result = board.executeMove(dir);
			insertRandCell();
			score += result.getScore();
			if (highestValueCell < result.getValue()) highestValueCell = result.getValue();
			//states.push(new GameState(board.getState()));
		}

		//TODO: just as reminder
		@Override
		public String toString(){
			//calls the toString of the Board
			return board.toString() + "highest: " + this.highestValueCell + "\tscore: " + this.score + "\n";

		}
		

	/**
	 * Checks if a game is over by checking if the there is a cell with the value of 2048 or if there is no moves to make.
	 * 
	 * @return true in the case that the game is over or false if it is
	
	*/

	public boolean isOver() {
		if (highestValueCell == 2048) return true;
		else if (board.getFree().isEmpty() && board.noMoves()) return true;
		else return false;
	}
	
	/**
	 * Checks if there is any cell witha value of 2048.
	 * 
	 * @return true if there is a cell with its value equals to 2048.
	 */
		
	public boolean isWon() {
		return highestValueCell == 2048;
	}
	

	/**
	 * set both score and highestValueCell back to 0 and resets the board, finally places the inital cells
	 */

	public void reset() {
		score = 0;
		this.highestValueCell = 0;
		board.reset();
		for (int i = 0; i < initCells; i++) {
			if (myRandom.nextInt(100) < 10)
				board.setCell(new Position(myRandom.nextInt(size), myRandom.nextInt(size)), 4);
			else
				board.setCell(new Position(myRandom.nextInt(size), myRandom.nextInt(size)), 2);
		}
	}
	
	/**
	 * 
	 * 	undoes the last move in the stack
	 */
	public void undo() {
		//GameState x = states.pop();
	//	board.setState(x.getState());
		//states.push(x);
	}
	
	/**
	 * Redoes the last move in the stack
	 */
	
	public void redo() {
	//	board.setState(states.pop().getState());
	}
	
	public GameState getState() {
		return new GameState(board.getState());
		
	}
	
}
