 package p2.control.commands;

import p2.control.Controller;
import p2.logic.Game;

/**
 * @author Pablo & Diego
 * 
 * Abstract class which will be extended by all the other classes of this package, each represents a different Command.
 * 
 */

public abstract class Command {
	
	 private String helpText;
	 private String commandText;
	 protected final String commandName;
	 
	 //TO-DO
	 /**
	  * Constructor of the abstract class.
	  * 
	  * @param commandInfo
	  * @param helpInfo
	  */
	 
	 public Command(String commandInfo, String helpInfo) {
		 commandText = commandInfo;
		 helpText = helpInfo;
		 String[] commandInfoWords = commandText.split("\\s+");
		 commandName = commandInfoWords[0];
	 }
	 
	 /**
	  * Abstract class execute, each command will bring a different execution in the game, carried out through the controller.
	  * 
	  * @param game current game which is taking place.
	  * @param controller which rules the games and performs the corresponding actions.
	  */
	 
	 public abstract void execute(Game game, Controller controller);
	 
	 /**
	  * Abstract parser of the class which parses the command introduced.
	  * 
	  * @param commandWords string which contains in each position each word introduced as command.
	  * @param controller which rules the games and performs the corresponding actions.
	  * @return the corresponding command to perform. 
	  */
	 
	 public abstract Command parse(String[] commandWords, Controller controller);
	 
	 /**
	  * Method which builds the returned string composed by the attribute commandText a space and the other attribute helpText.
	  * 
	  * @return the string as described above.
	  */
	 
	 public String helpText() {
		 return " " + commandText + ": " + helpText;
	 }
	 
 }