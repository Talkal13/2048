package p2.control.commands;

import p2.control.Controller;
import p2.logic.Game;

public class ResetCommand extends NoParamsCommand {
	
	public ResetCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game, Controller controller) {
		game.reset();
	}

}
