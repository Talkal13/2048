package pr1;

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
		game = currentGame;
		in = new Scanner(System.in);
	}
	

	public void run(){
		//executes the game
		boolean on = true;
		String input;
		while(on){
			System.out.print("Command > ");
			input = in.nextLine().toLowerCase();
			String[] parts = input.split(" ");
			
			switch (parts[0]) {
				case "reset": 
					game.reset();
					System.out.print(game);
				//the program returns to the initial state
					break;
				case "help": showHelp();
				//displays the help text
					break;
				case "exit": System.out.print("Game over");
					on = false;	
				//terminates the program after printing the message "Game over"
					break;
				case "move": 
					if(parts.length == 2){//check if the second term is an accepted command(up,down,right,left)
						//performs the movement in the parts[2] direction
						switch(parts[1]) {
						case "up":
							game.move(new Direction(DirectionOption.UP));
							break;
						case "down": 
							game.move(new Direction(DirectionOption.DOWN));
							break;
						case "right": 
							game.move(new Direction(DirectionOption.RIGHT));
							break;
						case "left": 
							game.move(new Direction(DirectionOption.LEFT));
							break;
						default: System.out.println("choose a valid direction");
						}
						System.out.print(game);
						break;
						
					}
				//default case that the command is invalid, we ask for a new command
				default: System.out.println("choose another option");	
			}
		}	
	}

	
	public void showHelp(){
		System.out.println("Move <direction>: execute a move in one of the fur directions, up, own,left, right");
		System.out.println("Reset: start a new game");
		System.out.println("Help: print this help message");
		System.out.println("Exit: terminate the program");
	}
	
	
}
