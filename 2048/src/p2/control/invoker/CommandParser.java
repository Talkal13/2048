package p2.control.invoker;

import p2.control.Controller;
import p2.control.commands.*;

public class CommandParser {
	
	private static Command[] availableCommands = { new HelpCommand("help", "print this help message."), new ResetCommand("reset", "start a new game."),
			new ExitCommand("exit", "terminate the program."), new MoveCommand("move", "execute a move in one of the directions: up, down, left, right.") } ;

	
	public static Command parseCommand(String[] commandWords, Controller controller) {
		for (Command x : availableCommands) {
			if (x.parse(commandWords, controller) != null) return x;
		}
		return null;
	}
	
	public static String commandHelp() {
		String s = "";
		for (Command x : availableCommands) {
			s += (x.helpText() + "\n");
		}
		return s;
	}
}
