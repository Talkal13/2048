package p2.logic.multigames;

import java.util.Random;

import p2.logic.*;
import p2.util.Position;

public class Rules2048 implements GameRules {
	
	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		int random = rand.nextInt(99);
		if (random < 10) {
			board.setCell(pos, 2);
		} else {
			board.setCell(pos, 4);
		}
	}

	@Override
	public int merge(Cell self, Cell other) {
		if (self.doMerge(other)) return self.getValue() + other.getValue();
		else return 0;
	}

	@Override
	public int getWinValue(Board board) {
		return 0; //TODO
	}

	@Override
	public boolean win(Board board) {
		return false;
	}

	@Override
	public boolean lose(Board board) {
		// TODO Auto-generated method stub
		return false;
	}

}
