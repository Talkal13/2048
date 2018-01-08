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
	
	public LoadCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) throws ExecutionException {
		try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
			buffer.readLine();
			buffer.readLine();
			game.load(buffer);
		} catch (IOException | IndexOutOfBoundsException e) {
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
