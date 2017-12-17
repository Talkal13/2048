package p2.control.commands;

import p2.control.Controller;
import p2.control.ErrorCode;
import p2.logic.Game;

/**
 * @author Pablo & Diego
 *
 * Abstract class which extends the abstract class Command and will be extended by the commands which doesn't require any parameter to being taken place.
 */

public abstract class NoParamsCommand extends Command {

	/**
	 * Constructor of the class implements the parent class Command with it´s parameters.
	 * 
	 * @param commandInfo string containing the command.
	 * @param helpInfo  explanation of what the command does.
	 */
	
	public NoParamsCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	/**
	 * As the classes which will extend this method will be commands of just one word, if the first position of parameter commandWords is equal to the
	 * class attribute commandName this is the result of the parse.
	 * 
	 * @return null in the case that the first position of commandWords is not the same of the name of the command itself and if it is this Command
	 * is the one that would be returned as result of the parse.
	 */
	
	public Command parse(String[] commandWords, Controller controller) {
		if (commandWords[0].equals(this.commandName)) return this;
		return null;
	}

}
