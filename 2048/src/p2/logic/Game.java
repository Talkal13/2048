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
 *
 * Class which represents the 2048 game.
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

	/**
	 * Inserts a given value in the position passed as parameter.
	 * 
	 * @param value value to be introduced.
	 * @param pos position to introduce that value.
	 */
		
	public void insertCell(int value, Position pos) {
		board.setCell(pos, value);
	}

	/**
	 * Inserts in a random empty cell a 4 with a 10% of chances or a 2 with the other 90%.
	 */

	public void insertRandCell() {
		currentRules.addNewCell(board, myRandom);
	}

	/**
	 * Constructor of the class,in case no seed is introduced.
	 *
	 * @param sizeBoard size of the board of the game.
	 * @param numCells number of cells displayed at the start of the game.
	 * @param random value to be used for the random behavior of the game.
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
		
	/**
	 * Constructor of the class,in case the seed is introduced.
	 *
	 * @param sizeBoard size of the board of the game.
	 * @param numCells number of cells displayed at the start of the game.
	 * @param random value to be used for the random behavior of the game.
	 */
		
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
		
	/**
	 * Switch to the game modality.
	 * 
	 * @param sizeBoard size of the board of the new game.
	 * @param numCells number of initial cells in the new game.
	 * @param seed seed for the random behavior of the new game.
	 * @param rules rules of the new game.
	 */
		
	public void changeGame(int sizeBoard, int numCells, long seed, GameRules rules) {
		size = sizeBoard;
		initCells = numCells;
		myRandom = new Random(seed);
		currentRules = rules;
		board = new Board(sizeBoard);
		for (int i = 0; i < numCells; i++) {
			insertRandCell();
		}
	}

	/**
	 * Method that performs a move in the direction dir in the board it updates the score and the value of the highest value.
	 *
	 * @param dir direction in which the move will take place.
	 */
		
	public void move (Direction dir) {
		undoStack.push(getState());
		MoveResult result = board.executeMove(dir, currentRules);
		insertRandCell();
		score += result.getScore();
	}

	/**
	 * Calls the toString of the Board adding the best value of the current game and it's score.
	 */
		
	public String toString(){
		return board.toString() + "best value: " + currentRules.getWinValue(board) + "\tscore: " + this.score + "\n";
	}

	/**
	 * Set both score and highestValueCell back to 0 and resets the board, finally places the initial cells.
	 */

	public void reset() {
		score = 0;
		board.reset();
		for (int i = 0; i < initCells; i++) {
			currentRules.addNewCell(board, myRandom);
		}
	}
	
	/**
	 * 	Undoes the last move in the stack.
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
	
	/**
	 * checks if the stack of undo movements is empty.
	 * 
	 * @return true if is empty or false if is not.
	 */
	
	public boolean isUndoStackEmpty() {
		return undoStack.isEmpty();
	}
	
	/**
	 * checks if the stack of redo movements is empty.
	 * 
	 * @return true if is empty or false if is not.
	 */
	
	public boolean isRedoStackEmpty() {
		return redoStack.isEmpty();
	}
	
	/**
	 * Redo the last move in the stack
	 */
	
	public void redo() {
		GameState s = getState();
		setState(redoStack.pop());
		undoStack.push(s);
	}
	
	/**
	 * Returns a GameState which will be the same as the one of the class board, gotten through the method get state.
	 * 
	 * @return the state of the board of the game taking place.
	 */
	
	public GameState getState() {
		return new GameState(board.getState());
	}
	
	/**
	 * Changes the state of the board of the current game, the score will be changed as well.
	 * 
	 * @param aState state to assign to the board.
	 */
	
	public void setState(GameState aState) {
		board.setState(aState.getState());
		score = aState.getScore();
	}
	
}
