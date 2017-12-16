package p2.logic;

import java.util.Random;

import p2.util.GameStateStack;
import p2.util.GameState;
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
		private GameStateStack undoStack = new GameStateStack();
		private GameStateStack redoStack = new GameStateStack();
		private GameRules currentRules;


		public void insertCell(int value, Position pos) {
			board.setCell(pos, value);
		}

		/**
		 * Inserts in a random empty cell a 4 with a 10% of chances or a 2 with te other 90%
		 *
		 */

		public void insertRandCell() {
			currentRules.addNewCell(board, myRandom);
		}

		/**
		 * Constructor of the class, saves the 3 values passed as arguments
		 *
		 * @param sizeBoard size of the board of the game
		 * @param numCells number of cells displayed at the start of the game
		 * @param random value to be used for the random behaviour of the game.
		 */
		public Game(int sizeBoard, int numCells, Random random, GameRules rules){
			size = sizeBoard;
			initCells = numCells;
			myRandom = random;
			currentRules = rules;
			board = new Board(sizeBoard);
			for (int i = 0; i < numCells; i++) {
				insertRandCell();
			}
			
		}
		
		public Game(int sizeBoard, int numCells, long seed, GameRules rules) {
			size = sizeBoard;
			initCells = numCells;
			myRandom = (seed == -1) ? new Random(seed) : new Random();
			currentRules = rules;
			board = new Board(sizeBoard);
			for (int i = 0; i < numCells; i++) {
				insertRandCell();
			}
		}
		
		public void changeGame(int sizeBoard, int numCells, long seed, GameRules rules) {
			size = sizeBoard;
			initCells = numCells;
			myRandom = (seed == -1) ? new Random(seed) : new Random();
			currentRules = rules;
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
			undoStack.push(getState());
			MoveResult result = board.executeMove(dir, currentRules);
			insertRandCell();
			score += result.getScore();
		}

		//TODO: just as reminder
		@Override
		public String toString(){
			//calls the toString of the Board
			return board.toString() + "best value: " + currentRules.getWinValue(board) + "\tscore: " + this.score + "\n";

		}
		
	

	/**
	 * set both score and highestValueCell back to 0 and resets the board, finally places the inital cells
	 */

	public void reset() {
		score = 0;
		board.reset();
		for (int i = 0; i < initCells; i++) {
			currentRules.addNewCell(board, myRandom);
		}
	}
	
	/**
	 * 
	 * 	undoes the last move in the stack
	 */
	public void undo() {
		GameState s = getState();
		try {
			setState(undoStack.pop());
			redoStack.push(s);
		} catch (NullPointerException e) {
			//TODO Send error message
		}
	}
	
	public boolean isUndoStackEmpty() {
		return undoStack.isEmpty();
	}
	
	public boolean isRedoStackEmpty() {
		return redoStack.isEmpty();
	}
	
	/**
	 * Redoes the last move in the stack
	 */
	
	public void redo() {
		GameState s = getState();
		setState(redoStack.pop());
		undoStack.push(s);
	}
	
	public GameState getState() {
		return new GameState(board.getState());
	}
	
	public void setState(GameState aState) {
		board.setState(aState.getState());
		score = aState.getScore();
	}
	
}
