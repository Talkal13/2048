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
		
		//Not sure of the correctness of the third elemnt
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
		}
	
		/**
		 * method that performs a move in the direction dir on the bard
		 * it updates the scoreand the value of the highest value
		 * 
		 * @param dir direction in which the move willtake place.
		 */
		public void move (Direction dir){
			
		}
		
		//just as reminder
		public String toString(){
			//calls the toString of the Board
			return null;
		}
}
