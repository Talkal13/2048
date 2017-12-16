package p2.util;

public class GameStateStack {
	
	private static final int CAPACITY = 20;
	private GameState[] buffer = new GameState[CAPACITY + 1];
	private int index = 0;
	
	public GameState pop() {
		if (isEmpty()) return null;
		return buffer[index];
	}
	
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
	
	private void shift() {
		for (int i = 0; i < index; i++) {
			buffer[i] = buffer[i + 1];
		}
	}
	
	public boolean isEmpty() {
		return index <= 0;
	}
}
