package p2.control.commands;

import p2.control.Controller;
import p2.logic.multigames.Game;
import pr1.Direction;
import pr1.DirectionOption;

public class MoveCommand extends Command {

	private Direction dir;
	
	public MoveCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game, Controller controller) {
		game.move(dir);
		controller.setNoPrintGameState(false);
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		if (!commandWords[0].equals(this.commandName)) {
			return null;
		} 
		else {
			switch (commandWords[1]) {
			case "up":
				dir = new Direction(DirectionOption.UP);
				break;
			case "down":
				dir = new Direction(DirectionOption.DOWN);
				break;
			case "right":
				dir = new Direction(DirectionOption.RIGHT);
				break;
			case "left":
				dir = new Direction(DirectionOption.LEFT);
				break;
			default:
				return null;
			}
		}
		return this;
	}

}
