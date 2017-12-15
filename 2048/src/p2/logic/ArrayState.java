package p2.logic;

import p2.util.GameState;

// trial delete later this line


public class ArrayState {
	private static final int CAPACITY = 20;

	private GameState[] array;
	int size;
	
	public ArrayState() {
		array = new GameState[CAPACITY];
		
		
		size = 0;
	}
	
	public void push(GameState state) {
		if (size == CAPACITY) {
			shift();
		}
		array[size] = state;
	}
	
	private void shift() {
		for (int i = 0; i < size; i++) {
			array[i] = array[i + 1];
		}
	}
	
	public GameState pop() {
		size--;
		return array[size + 1];
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}
