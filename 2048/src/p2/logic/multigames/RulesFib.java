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
			board.setCell(pos, 2);
		} else {
			board.setCell(pos, 1);
		}
	}

	@Override
	public int merge(Cell self, Cell other) {
		if (other.getValue() == next(self.getValue())) {
			other.setValue(self.getValue() + other.getValue());
			self.emptyCell();
			return other.getValue();
		}
		return 0;
	}
	
	private int next(int numb, int sum, int bef) {
		if (numb == 1) return 1;
		else if (sum == numb) return sum + bef;
		else {
			return next(numb, sum + bef, sum);
		}
	}
	
	private int next(int numb) {
		return next(numb, 1, 1);
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
