package p2.util;

import java.util.EmptyStackException;

/**
 * @author Pablo & Diego
 *
 * Class which provides auxiliary methods to store the gameStates in a stack.
 */

public class GameStateStack {
	
	private static final int CAPACITY = 1;
	private GameState[] buffer;
	private int initial, index;
	
	public GameStateStack() {
		buffer  = new GameState[CAPACITY + 1];
		initial = 0;
		index = 0;
	}
	
	/**
	 * Pops the last game state stored in the stack, if there is any.
	 * 
	 * @return null if the stack is empty, so is impossible to pop any state, or the last state in the stack if there is any.
	 */
	
	public GameState pop() {
		if (isEmpty()) throw new EmptyStackException();
		if (index == 0) index = CAPACITY;
		else index--;
		return buffer[index];
	}
	
	/**
	 * Pushes the given gameState as parameter in the stack, if the stack is full the first state stored would be lost.
	 * 
	 * @param state state of the game to store in the stack.
	 */
	
	public void push(GameState state) {
		if (isFull()) {
			buffer[index] = state;
			index = initial;
			initial++;
		}
		else {
			
			buffer[index] = state;
			index++;
		}
		
	}
	
	
	/**
	 * Checks if the stack is empty.
	 * 
	 * @return true if the stack is empty and false is there is space left.
	 */
	
	public boolean isEmpty() {
		return index == initial;
	}
	
	/**
	 * 
	 * Checks if the stack is full
	 * 
	 * @return if the board is full or not
	 */
	
	private boolean isFull() {
		if (initial == 0)
			return index == CAPACITY;
		else
			return index == initial - 1;
	}

	public void reset() {
		// TODO Auto-generated method stub
		initial = 0;
		index = initial;
	}
	
}
