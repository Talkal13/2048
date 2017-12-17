package p2.logic;

import p2.util.GameState;

/**
 * @author Pablo & Diego
 *
 * Class which represents the stack to redo and undo moves.
 */

public class ArrayState {
	private static final int CAPACITY = 20;

	private GameState[] array;
	int size;
	
	/**
	 * Constructor method of the class, declares the stack of states and initializes the size attribute.
	 */
	
	public ArrayState() {
		array = new GameState[CAPACITY];
		size = 0;
	}
	/**
	 * Pushes the game state introduced as parameter in the stack if is not full.
	 * 
	 * @param state gameState to push in the stack.
	 */
	
	public void push(GameState state) {
		if (size == CAPACITY) {
			shift();
		}
		array[size] = state;
	}
	
	/**
	 * Shifts all the positions of the stack to the one on its bottom.
	 */
	
	private void shift() {
		for (int i = 0; i < size; i++) {
			array[i] = array[i + 1];
		}
	}
	
	/**
	 * Pops the last gameState from the stack and decreases its size.
	 * 
	 * @return the last element pushed in the stack.
	 */
	
	public GameState pop() {
		size--;
		return array[size + 1];
	}
	
	/**
	 * Checks if the stack is empty or if is not.
	 * 
	 * @return true if the stack is empty and false if is not.
	 */
	
	public boolean isEmpty() {
		return size == 0;
	}
}
