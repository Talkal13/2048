package p2.control.invoker;

import p2.control.Controller;
import p2.control.commands.*;

public class CommandParser {
	
	private static Command[] availableCommands = { new HelpCommand("help", "print this help message."), new ResetCommand("reset", "start a new game."),
			new ExitCommand("exit", "terminate the program."), new MoveCommand("move <direction>", "execute a move in one of the directions: up, down, left, right."),
			new UndoCommand("undo", "restores the state of the game previous to executing the last move"), new RedoCommand("redo", " allows a previously undone command to be re-executed")} ;

	
	public static Command parseCommand(String[] commandWords, Controller controller) {
		for (Command x : availableCommands) {
			if (x.parse(commandWords, controller) != null) return x;
		}
		return null;
	}
	
	public static String commandHelp() {
		String s = "";
		for (Command x : availableCommands) {
			s += ("\t" + x.helpText() + "\n");
		}
		return s;
	}
}
