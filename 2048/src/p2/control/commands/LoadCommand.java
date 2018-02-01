package p2.control.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import p2.exceptions.EndException;
import p2.exceptions.ExecutionException;
import p2.exceptions.ParsingException;
import p2.logic.Game;
import p2.util.MyStringUtils;

public class LoadCommand extends Command {
	
	private String filename;
	
	private static final String commandInfo = "load <filename>";
	private static final String helpInfo = "Load the game from a filename";
	
	public LoadCommand() {
		super(commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) throws ExecutionException {
		try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
			String f_line = buffer.readLine();
			if (!f_line.equals("This file stores a saved 2048 game")) throw new ExecutionException("Load failed: The first line does not match the standard");
			buffer.readLine();
			game.load(buffer);
		} catch (IOException e) {
			throw new ExecutionException("Load failed: There was a problem reading from the disk :'(");
		} catch (Exception e) {
			throw new ExecutionException("Load failed: invalid file format");
		}
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords, Scanner in) throws ParsingException {
		if (!commandWords[0].equals(this.commandName)) {
			return null;
		} 
		else {
			try {
				if (commandWords.length > 2) {
					throw new ParsingException("Invalid filename: the filename contains spaces");
				}
				else {
					if (MyStringUtils.validFileName(commandWords[1])) {
						filename = commandWords[1];
						return this;
					}
					else
						throw new ParsingException("Invalid filename: the filename contains invalid characters");
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new ParsingException("Save must be followed by a filename");
			}
		}
	}

	
}
