package p2.logic.multigames;

import java.util.Random;

import p2.logic.*;
import p2.util.Position;

public class Rules2048 implements GameRules {
	
	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		int random = rand.nextInt(99);
		if (random < 10) {
			board.setCell(pos, 4);
		} else {
			board.setCell(pos, 2);
		}
	}

	@Override
	public int merge(Cell self, Cell other) {
		if (self.getValue() == other.getValue()) {
			self.setValue(self.getValue() * 2);
			other.emptyCell();
			return self.getValue();
		}
		else return 0;
	}

	@Override
	public int getWinValue(Board board) {
		return board.getMax();
	}

	@Override
	public boolean win(Board board) {
		return (board.getMax() == 2048);
	}

}
