package p2.control.commands;

import p2.control.Controller;
import p2.logic.multigames.Game;

public class ExitCommand extends NoParamsCommand {

	public ExitCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game, Controller controller) {
		controller.setOver(true);
		controller.setNoPrintGameState(true);
		System.out.println("Game Over");
	}
	
}
