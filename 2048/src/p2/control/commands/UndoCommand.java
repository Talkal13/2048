package p2.control.commands;

import p2.control.Controller;
import p2.logic.Game;

public class UndoCommand extends NoParamsCommand {

	public UndoCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game, Controller controller) {
		if (game.isUndoStackEmpty()) {
			System.out.println("Nothing to undo");
			controller.setNoPrintGameState(true);
			return;
		}
		System.out.println("Undoing one move...");
		game.undo();
		
	}

}
