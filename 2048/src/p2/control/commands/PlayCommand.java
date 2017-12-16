package p2.control.commands;

import p2.control.Controller;
import p2.logic.Game;
import p2.logic.multigames.*;
import p2.util.GameType;

public class PlayCommand extends Command {
	
	private GameType type;
	private int size = 4, initNumb = 2;
	private long seed = -1;

	public PlayCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game, Controller controller) {
		switch(type) {
		case ORIG:
			game.changeGame(size, initNumb, seed, new Rules2048());
			return;
		case INV:
			game.changeGame(size, initNumb, seed, new RulesInverse());
			return;
		case FIB:
			game.changeGame(size, initNumb, seed, new RulesFib());
			return;
		}
		
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		if (!commandWords[0].equals(commandName)) {
			return null;
		}
		else {
			switch(commandWords[1]) {
			case "original":
				type = GameType.ORIG;
				break;
			case "inverse":
				type = GameType.INV;
				break;
			case "fib":
				type = GameType.FIB;
				break;
			default:
				System.out.println("Unknown game type for play command");
				controller.setErrorCode(false);
				controller.setNoPrintGameState(true);
				return null;
			}
			if (commandWords.length > 2) {
				try {
					size = Integer.parseInt(commandWords[2]);
				} catch (NumberFormatException e) {
					System.out.println("The third argument must be an integer");
				}
				if (commandWords.length > 3) {
					try {
						initNumb = Integer.parseInt(commandWords[3]);
					} catch (NumberFormatException e) {
						System.out.println("The forth argument must be an integer");
					}
					if (commandWords.length > 4) {
						try {
							seed = Integer.parseInt(commandWords[4]);
						} catch (NumberFormatException e) {
							System.out.println("The fith argument must be an integer");
						}
					}
				}
				
			}
			else {
				//TODO Read the values of the game;
			}
			
			return this;
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public int getInit() {
		return initNumb;
	}
	
	public long getSeed() {
		return seed;
	}
	
	public GameType getType() {
		return type;
	}

}
