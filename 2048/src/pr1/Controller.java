package pr1;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Pablo & Diego
 * 
 * Class which instance controls the execution of the game 
 *
 */

public class Controller {

	private Game game;
	private Scanner in;
	
	//variable to store the conditions of the game to be developed
	public int sizeBoard;
	public int numDigit;
	
	
	public Controller(Game  currentGame) {
		currentGame = game;
		in = new Scanner(System.in);
	}
	
	//SHOULD BE MODIFIED IN THE FUTURE IN ORDER TO ACCEPT THE 3RD PARAMETER, THE ONE CONCERNING TO THE SEED
	public void firstStep(){
		sizeBoard = in.nextInt();
		numDigit = in.nextInt();
			
	}
	
	
	
	
	
	
	public void run(){
		//read the first input
		firstStep();
		//creates a new game with the values preiously introduced
		game = new Game(sizeBoard, numDigit, null);
		
	}
}
