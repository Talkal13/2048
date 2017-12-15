package p2.control.commands;

import p2.control.Controller;
import p2.logic.Game;

public class RedoCommand extends NoParamsCommand {

	public RedoCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game, Controller controller) {
		if (game.isRedoStackEmpty()) {
			System.out.println("Nothing to redo");
			controller.setNoPrintGameState(true);
			return;
		}
		System.out.println("Redoing one move...");
		game.redo();
		
	}

}
