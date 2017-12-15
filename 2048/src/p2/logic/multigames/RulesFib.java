package p2.logic.multigames;

import java.util.Random;

import p2.logic.Board;
import p2.logic.Cell;
import p2.logic.GameRules;
import p2.util.Position;

public class RulesFib implements GameRules {
		
	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		int random = rand.nextInt(99);
		if (random < 10) {
			board.setCell(pos, 1);
		} else {
			board.setCell(pos, 2);
		}
	}

	@Override
	public int merge(Cell self, Cell other) {
		if (self.doMerge(other)) return self.getValue() + other.getValue();
		else return 0;
	}

	@Override
	public int getWinValue(Board board) {
		return board.getMax(); 
	}

	@Override
	public boolean win(Board board) {
		return (board.getMax() == 144);
	}
}
