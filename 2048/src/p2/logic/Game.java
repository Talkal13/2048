package p2.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import p2.util.GameStateStack;
import p2.util.GameType;
import p2.util.GameState;
import p2.exceptions.EndException;
import p2.util.ArrayAsList;
import p2.util.Direction;
import p2.util.MoveResult;
import p2.util.Position;

/**
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
	private GameType currentType;

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
		currentType.getRules().addNewCell(board, myRandom);
	}

	/**
	 * Constructor of the class,in case no seed is introduced.
	 *
	 * @param sizeBoard size of the board of the game.
	 * @param numCells number of cells displayed at the start of the game.
	 * @param random value to be used for the random behavior of the game.
	 */
		
	public Game(int sizeBoard, int numCells, Random random, GameType rules){
		size = sizeBoard;
		initCells = numCells;
		myRandom = random;
		currentType = rules;
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
		
	public Game(int sizeBoard, int numCells, long seed, GameType rules) {
		size = sizeBoard;
		initCells = numCells;
		myRandom = (seed == -1) ? new Random(seed) : new Random();
		currentType = rules;
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
		
	public void changeGame(int sizeBoard, int numCells, long seed, GameType rules) {
		size = sizeBoard;
		initCells = numCells;
		myRandom = new Random(seed);
		currentType = rules;
		score = 0;
		board = new Board(sizeBoard);
		for (int i = 0; i < numCells; i++) {
			insertRandCell();
		}
	}

	/**
	 * Method that performs a move in the direction dir in the board it updates the score and the value of the highest value.
	 *
	 * @param dir direction in which the move will take place.
	 * @throws EndException 
	 */
		
	public void move (Direction dir) throws EndException {
		redoStack.reset();
		undoStack.push(getState());
		MoveResult result = board.executeMove(dir, currentType.getRules());
		insertRandCell();
		score += result.getScore();
		if (currentType.getRules().win(board)) throw new EndException("Congratulations. You won the game!!");
		else if (currentType.getRules().lose(board)) throw new EndException("Sorry, more luck next time :(");
	}

	/**
	 * Calls the toString of the Board adding the best value of the current game and it's score.
	 */
		
	public String toString(){
		return board.toString() + "best value: " + currentType.getRules().getWinValue(board) + "\tscore: " + this.score + "\n";
	}

	/**
	 * Set both score and highestValueCell back to 0 and resets the board, finally places the initial cells.
	 */

	public void reset() {
		score = 0;
		board.reset();
		for (int i = 0; i < initCells; i++) {
			currentType.getRules().addNewCell(board, myRandom);
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
	 * Checks if the stack of undo movements is empty.
	 * 
	 * @return true if is empty or false if is not.
	 */
	
	public boolean isUndoStackEmpty() {
		return undoStack.isEmpty();
	}
	
	/**
	 * Checks if the stack of redo movements is empty.
	 * 
	 * @return true if is empty or false if is not.
	 */
	
	public boolean isRedoStackEmpty() {
		return redoStack.isEmpty();
	}
	
	/**
	 * Redo the last move in the stack.
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
	
	public void store(BufferedWriter buffer) throws IOException {
		board.store(buffer);
		buffer.newLine();
		buffer.write(initCells + " " + score + " " + currentType.externalise());
		buffer.newLine();
	}

	public void load(BufferedReader buffer) throws Exception {
		board.load(buffer);
		String[] parts = buffer.readLine().split("\\s+");
		initCells = Integer.parseInt(parts[0]);
		score = Integer.parseInt(parts[1]);
		currentType = GameType.parse(parts[2]);
		System.out.println("Successfull load of the game " + currentType.toString());
	}

	
}
