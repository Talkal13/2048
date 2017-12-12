package p2.logic;

import java.util.Random;

import p2.logic.Board;
import p2.util.ArrayAsList;
import p2.util.Position;

public interface GameRules {
	void addNewCellAt(Board board, Position pos, Random rand);
	int merge(Cell self, Cell other);
	int getWinValue(Board board);
	boolean win(Board board);
	
	default boolean lose(Board board) {
		return board.getFree().isEmpty() && board.noMoves();
	}
	
	default Board createBoard(int size) {
		return new Board(size);
	}
	
	default void addNewCell(Board board, Random rand) {
		ArrayAsList.shuffle(board.getFree(), rand);
		Position pos = (Position) board.getFree().get(0);
		addNewCellAt(board, pos, rand);
	}
	
	default void initBoard(Board board, int numCells, Random rand) {
		for (int i = 0; i < numCells; i++) {
			addNewCell(board, rand);
		}
	}
}
