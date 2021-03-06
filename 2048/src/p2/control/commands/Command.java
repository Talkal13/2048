 package p2.control.commands;

import java.util.Scanner;

import p2.control.Controller;
import p2.exceptions.EndException;
import p2.exceptions.ExecutionException;
import p2.exceptions.ParsingException;
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
	 
	 /**
	  * Constructor of the abstract class.
	  * Once it�s called it will assign the parameters commandInfo and helpInfo to the attributes commandText and helpText respectively.
	  * In addition commandText will be slip and the first word set as the attribute commandName. In that way once its called, all it�s 
	  * attributes would be defined.
	  * 
	  * @param commandInfo string containing the command to perform.
	  * @param helpInfo explanation of what that command does.
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
	 * @throws ExecutionException 
	 * @throws WinException 
	 * @throws EndException 
	  */
	 
	 public abstract boolean execute(Game game) throws ExecutionException, EndException;
	 
	 /**
	  * Abstract parser of the class which parses the command introduced.
	  * 
	  * @param commandWords string which contains in each position each word introduced as command.
	  * @param controller which rules the games and performs the corresponding actions.
	  * @return the corresponding command to perform. 
	  * @throws ParsingException 
	  */
	 
	 public abstract Command parse(String[] commandWords, Scanner in) throws ParsingException;
	 
	 /**
	  * Method which builds the returned string composed by the attribute commandText a space and the other attribute helpText.
	  * 
	  * @return the string as described above.
	  */
	 
	 public String helpText() {
		 return commandText + ": " + helpText;
	 }
	 
 }