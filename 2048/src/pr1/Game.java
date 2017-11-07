package pr1;

import pr1.util.ArrayAsList;

import java.util.Random;

/**
 * 
 * @author Pablo and Diego
 * @version 1.0
 * @since 1.0
 *
 *Class which represents the 2048 game
 */

public class Game {

		private Board board;
		private int size;
		private int initCells;
		private Random myRandom;
		private int score;
		private int highestValueCell;
		
		
		/**
		 * Inserts in a random empty cell a 4 with a 10% of chances or a 2 with te other 90%
		 * 
		 */
		
		public void insertRandCell() {
			
			ArrayAsList.shuffle(board.getFree(), myRandom);
			
			if (myRandom.nextInt(100) < 10) {
				board.setCell((Position) board.getFree().get(0), 4);
				if (this.highestValueCell < 4)
					this.highestValueCell = 4;
			}
			else {
				board.setCell((Position) board.getFree().get(0), 2);
				if (this.highestValueCell < 2)
					this.highestValueCell = 2;
			}
			board.getFree().pop(board.getFree().get(0));
		}
		
		/**
		 * Constructor of the class, saves the 3 values passed as arguments
		 * 
		 * @param sizeBoard size of the board of the game 
		 * @param numCells number of cells displayed at the start of the game
		 * @param random value to be used for the random behaviour of the game.
		 */
		public Game(int sizeBoard,int numCells, Random random){
			size = sizeBoard;
			initCells = numCells;
			myRandom = random;
			board = new Board(sizeBoard);
			for (int i = 0; i < numCells; i++) {
				insertRandCell();
			}
			/*currentCells = 0;
			
			board = new Board(sizeBoard);
			
			while(currentCells < numCells){
				randomRow = generateRandom(sizeBoard);
				randomCol = generateRandom(sizeBoard);
					
				if(board.isBoardEmpty(randomRow,randomCol)){
					//this will be more complex in order to set a 4 or a 2
					board.setCell(new Position(randomRow, randomCol), 2);
					currentCells++;
				}
			}*/
		}
	
		/**
		 * Method that performs a move in the direction dir in the board it updates the score and the value of the highest value
		 * 
		 * @param dir direction in which the move will take place.
		 */
		public void move (Direction dir) {
			MoveResult result = board.executeMove(dir);
			insertRandCell();
			score += result.getScore();
			if (highestValueCell < result.getValue()) highestValueCell = result.getValue();
		}
		
		//TODO: just as reminder
		public String toString(){
			//calls the toString of the Board
			return board.toString() + "highest: " + this.highestValueCell + "\tscore: " + this.score + "\n\n";
			
		}
		
		/**
		 * set both score and highestValueCell back to 0 and resets the board, finally places the inital cells
		 */ 
		
		public void reset() {
			score = 0;
			this.highestValueCell = 0;
			board.reset();
			for (int i = 0; i < initCells; i++) {
				if (myRandom.nextInt(100) < 10)
					board.setCell(new Position(myRandom.nextInt(size), myRandom.nextInt(size)), 4);
				else
					board.setCell(new Position(myRandom.nextInt(size), myRandom.nextInt(size)), 2);
			}
		}
}
