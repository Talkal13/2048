package p2.util;

/**
 * @author Pablo & Diego
 *
 * Class which provides auxiliary methods to store the gameStates in a stack.
 */

public class GameStateStack {
	
	private static final int CAPACITY = 20;
	private GameState[] buffer = new GameState[CAPACITY + 1];
	private int index = 0;
	
	/**
	 * Pops the last game state stored in the stack, if there is any.
	 * 
	 * @return null if the stack is empty, so is impossible to pop any state, or the last state in the stack if there is any.
	 */
	
	public GameState pop() {
		index--;
		return buffer[index];
	}
	
	/**
	 * Pushes the given gameState as parameter in the stack, if the stack is full the first state stored would be lost.
	 * 
	 * @param state state of the game to store in the stack.
	 */
	
	public void push(GameState state) {
		if (index < CAPACITY) {
			buffer[index] = state;
			index++;
		}
		else {
			shift();
			buffer[index] = state;
		}	
	}
	
	/**
	 * Shifts all the elements in the stack in the way that the one in the position 0 is lost and the one in the index position is two times,
	 * in the index position and in the one before.
	 */
	
	private void shift() {
		for (int i = 0; i < index; i++) {
			buffer[i] = buffer[i + 1];
		}
	}
	
	/**
	 * Checks if the stack is empty.
	 * 
	 * @return true if the stack is empty and false is there is space left.
	 */
	
	public boolean isEmpty() {
		return index <= 0;
	}
}
