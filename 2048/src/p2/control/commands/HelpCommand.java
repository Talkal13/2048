package p2.control.commands;

import p2.control.Controller;
import p2.control.invoker.CommandParser;
import p2.logic.Game;

/**
 * @author Pablo & Diego
 * 
 * Class which extends the abstract class NoParamsCommand and represents the Help command.
 *
 */

public class HelpCommand extends NoParamsCommand {

	/**
	 * Constructor of the class implements the NoParamsCommand parent class with it´s parameters.
	 * 
	 * @param commandInfo string containing the help command.
	 * @param helpInfo explanation of what the help command does.
	 */
	
	public HelpCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	/**
	 * Executes the help command, this one just call the commandHelp method of the class CommandParser from the package p2.control.invoker which reads 
	 * all the commands and what are they for. Also sets the attribute setNoPrintGameState to true, because the help instructions will be displayed but 
	 * not the game board.
	 * 
	 * @param game current game which is taking place, in this extension of the class this game wont be use.
	 * @param controller controller of the current game, it´s attributes will be modified as described below.
	 */
	
	public void execute(Game game, Controller controller) {
		System.out.println("The available commands are:");
		System.out.println(CommandParser.commandHelp());
		controller.setNoPrintGameState(true);
	}

}
