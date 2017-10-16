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
		
		private int currentCells;
		private int randomRow;
		private int randomCol;
		
		/**
		 * Constructor of the class, saves the 3 values passed as arguments
		 * 
		 * @param sizeBoard size of the board of the game 
		 * @param numCells number of cells displayed at the start of the game
		 * @param seed value to be used for the random behaviour of the game.
		 */
		public Game(int sizeBoard,int numCells,Random seed){
			size = sizeBoard;
			initCells = numCells;
			myRandom = seed;
			currentCells = 0;
			
			board = new Board(sizeBoard);
			
			while(currentCells < numCells){
				randomRow = generateRandom(sizeBoard);
				randomCol = generateRandom(sizeBoard);
					
				if(board.isBoardEmpty(randomRow,randomCol)){
					//this will be more complex in order to set a 4 or a 2
					board.setCell(new Position(randomRow, randomCol), 2);
					currentCells++;
				}
			}
		}
			
		
		/**
		 * generates a randm number smaller than the size of the board
		 * 
		 * @param max size of the bord, the random number can't be greater han this number
		 * @return a random number
		 */
		public int generateRandom(int max){
			return (int) Math.floor(Math.random()*max);
		}
	
		/**
		 * method that performs a move in the direction dir on the board
		 * it updates the score and the value of the highest value
		 * 
		 * @param dir direction in which the move willtake place.
		 */
		public void move (Direction dir){
			
		}
		
		//just as reminder
		public void toPrint(){
			//calls the toString of the Board
			System.out.print(board.toString());
		}
}
