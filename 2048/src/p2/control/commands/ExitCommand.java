package p2.control.commands;

import p2.control.Controller;
import p2.exceptions.EndException;
import p2.logic.Game;

/**
 * @author Pablo & Diego
 * 
 * Class which extends the abstract class Command and represents the Exit command.
 * 
 */

public class ExitCommand extends NoParamsCommand {

	/**
	 * Constructor of the class implements the parent class Command with it�s parameters.
	 * 
	 * @param commandInfo string containing the exit command.
	 * @param helpInfo explanation of what the exit command does.
	 */
	
	public ExitCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}
	
	/**
	 * execute of the class ExitCommand, setting the setOver boolean attribute of the controller to false, because exiting the game it comes to an end,
	 * and also setting the other boolean attribute of the controller, setNoPrintGameState to true, because once you exit the game there is no need of printing it.
	 * Finally a message of "Game Over." will be displayed.
	 * 
	 * @param game current game which is taking place, in this extension of the class this game wont be use.
	 * @param controller controller of the current game, it�s attributes will be modified as described below.
	 * @throws EndException 
	 */
	
	public boolean execute(Game game) throws EndException {
		throw new EndException("Game over......");
	}
	
}
