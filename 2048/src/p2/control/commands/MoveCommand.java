package p2.control.commands;

import p2.control.Controller;
import p2.logic.multigames.Game;

public class MoveCommand extends Command {

	public MoveCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game, Controller controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		if (commandWords[0] == this.commandName) return this;
		return null;
	}

}
