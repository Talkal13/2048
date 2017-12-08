package p2.control.commands;

import p2.control.Controller;
import p2.control.ErrorCode;
import p2.logic.multigames.Game;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		if (commandWords[0].equals(this.commandName)) return this;
		return null;
	}

}
