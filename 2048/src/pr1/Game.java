package pr1;

import java.util.Random;

/**
 * 
 * @author Pablo & Diego
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
		
		//Not sure of the correctness of the third element
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
				if (random.nextInt(100) < 10)
					board.setCell(new Position(random.nextInt(sizeBoard), random.nextInt(sizeBoard)), 4);
				else
					board.setCell(new Position(random.nextInt(sizeBoard), random.nextInt(sizeBoard)), 2);
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
		 * method that performs a move in the direction dir on the bard
		 * it updates the score and the value of the highest value
		 * 
		 * @param dir direction in which the move will take place.
		 */
		public void move (Direction dir){
			MoveResult result = board.executeMove(dir);
			score = result.getScore();
			highestValueCell = result.getValue();
		}
		
		//TODO: just as reminder
		public String toString(){
			//calls the toString of the Board
			return board.toString();
			
		}
}
