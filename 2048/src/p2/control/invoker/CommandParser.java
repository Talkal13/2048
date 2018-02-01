package p2.control.invoker;

import java.util.Scanner;

import p2.control.Controller;
import p2.control.commands.*;
import p2.exceptions.ParsingException;

/**
 * @author Pablo & Diego
 *
 * Class which contains all the possible commands to perform as well as two auxiliary classes for the use of the command classes.
 */

public class CommandParser {
	
	//Array of commands containing all the commands available.
	private static Command[] availableCommands = { 
			new HelpCommand(), 
			new ResetCommand(),
			new ExitCommand(), 
			new MoveCommand(),
			new UndoCommand(), 
			new RedoCommand(), 
			new PlayCommand(),
			new SaveCommand(),
			new LoadCommand()
	} ;

	/**
	 * Parse method which goes through all the possible commands invoking its parse method, the one which doesn't return null will be the returned one.
	 * 
	 * @param commandWords array of string which contain the command.
	 * @param controller of the current game.
	 * @return the corresponding command, this is the one which during the loop won�t return null, in case any fixes, a null would be returned.
	 * @throws ParsingException 
	 */
	
	public static Command parseCommand(String[] commandWords, Scanner in) throws ParsingException {
		for (Command x : availableCommands) {
			if (x.parse(commandWords, in) != null) return x;
		}
		throw new ParsingException("Unknown command. Use ’help’ to see the available commands");
	}
	
	/**
	 * Method which creates and returns a string containing the help text of the corresponding command, which is the one that wont return null during the loop.
	 * 
	 * @return the string containing the help text of the corresponding command.
	 */
	
	public static String commandHelp() {
		String s = "";
		for (Command x : availableCommands) {
			s += ("\t" + x.helpText() + "\n");
		}
		return s;
	}
}
