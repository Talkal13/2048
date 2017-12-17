package p2.logic.multigames;

import java.util.Random;
import p2.util.MyMathsUtil;

import p2.logic.Board;
import p2.logic.Cell;
import p2.logic.GameRules;
import p2.util.Position;

public class RulesInverse implements GameRules {
	
	final static int MAX = 11;

	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		int random = rand.nextInt(99);
		if (random < 10) {
			board.setCell(pos, 1024);
		} else {
			board.setCell(pos, 2048);
		}
		
	}

	@Override
	public int merge(Cell self, Cell other) {
		if (self.getValue() == other.getValue()) {
			self.setValue(self.getValue() / 2);
			other.emptyCell();
			int pow = MyMathsUtil.log(2, self.getValue());
			return (int) Math.pow(2, MAX - pow);
		}
		return 0;
	}

	@Override
	public int getWinValue(Board board) {
		return board.getMin();
	}

	@Override
	public boolean win(Board board) {
		return board.getMin() == 1;
	}

}
