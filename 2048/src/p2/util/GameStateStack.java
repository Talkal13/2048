package p2.util;

public class GameStateStack {
	
	private static final int CAPACITY = 20;
	private GameState[] buffer = new GameState[CAPACITY + 1];
	private int index = 0;
	
	public GameState pop() {
		if (isEmpty()) return null;
		index--;
		return buffer[index];
	}
	
	public void push(GameState state) {
		if (index < CAPACITY) {
			buffer[index] = state;
		}
		index++;
	}
	
	public boolean isEmpty() {
		return index <= 0;
	}
}
