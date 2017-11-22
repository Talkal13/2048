package p2.control.commands;

import p2.control.Controller;
import p2.control.invoker.CommandParser;
import p2.logic.multigames.Game;

public class HelpCommand extends NoParamsCommand {

	public HelpCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game, Controller controller) {
		System.out.println("The available commands are:");
		System.out.println(CommandParser.commandHelp());
		controller.setNoPrintGameState(true);
	}

}
