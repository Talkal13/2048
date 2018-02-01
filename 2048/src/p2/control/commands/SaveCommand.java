package p2.control.commands;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import p2.exceptions.EndException;
import p2.exceptions.ExecutionException;
import p2.exceptions.ParsingException;
import p2.logic.Game;
import p2.util.Direction;
import p2.util.DirectionOption;
import p2.util.GameState;
import p2.util.MyStringUtils;

public class SaveCommand extends Command {

	private boolean filename_confirmed;
	public static final String filenameInUseMsg = "The file already exists; do you want to overwrite it ? (Y/N)";
	public static final String FirstLine = "This file stores a saved 2048 game";
	private String filename;
	
	private static final String commandInfo = "save <filename>";
	private static final String helpInfo = "Save the game in a filename";
	 
	public SaveCommand() {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) throws ExecutionException, EndException {
		
		try (BufferedWriter buffer = new BufferedWriter(new FileWriter(filename))) {
			buffer.write(FirstLine);
			buffer.newLine();
			buffer.newLine();
			game.store(buffer);
			System.out.println("Game successfully saved to file; use load command to reload it.");
		} catch (IOException e) {
			throw new ExecutionException("");
		}
		
		return false;
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
					filename = confirmFileNameStringForWrite(commandWords[1], in);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new ParsingException("Save must be followed by a filename");
			}
		}
		return this;
	}

	/* This code supposes the following attribute declarations :
		∗ private boolean filename_confirmed;
		∗ public static final filenameInUseMsg
		∗ = "The file already exists ; do you want to overwrite it ? (Y/N)";
		∗ You may also need to add a throws clause to the declarations .
		*/
	private String confirmFileNameStringForWrite(String filenameString, Scanner in) throws ParsingException {
		String loadName = filenameString;
		filename_confirmed = false;
		while (!filename_confirmed) {
			if (MyStringUtils.validFileName(loadName)) {
				File file = new File(loadName);
				if (!file.exists())
					filename_confirmed = true;
				else {
					loadName = getLoadName(filenameString, in);
					filename_confirmed = true;
				}
			} else {
				throw new ParsingException("Invalid filename: the filename contains invalid characters");
			}
		}
		return loadName;
	}
	public String getLoadName(String filenameString, Scanner in) throws ParsingException {
		
		String newFilename = null;
		boolean yesOrNo = false;
		while (!yesOrNo) {
			System.out.print(filenameInUseMsg + ": ");
			String[] responseYorN = in.nextLine().toLowerCase().trim().split("\\s+");
			if (responseYorN.length == 1) {
				switch (responseYorN[0]) {
				case "y":
					yesOrNo = true;
					newFilename = filenameString;
					break;
				case "n":
					yesOrNo = true;
					System.out.print("Please enter another filename: ");
					newFilename = in.nextLine();
					if (checkIfValid(newFilename, in)) {
						break;
					}
					else {
						throw new ParsingException("Invalid filename: the filename contains invalid characters");
					}
				default:
					System.out.println("Please answer ’Y’ or ’N’");
				}
			} else {
				System.out.println("Please answer ’Y’ or ’N’");
			}
		}
		return newFilename;
	}
	
	public boolean checkIfValid(String filename, Scanner in) throws ParsingException {
		String[] parts = filename.split("\\s+");
		try {
			if (parts.length > 1) {
				throw new ParsingException("Invalid filename: the filename contains spaces");
			}
			else {
				confirmFileNameStringForWrite(parts[0], in);
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ParsingException("Save must be followed by a filename");
		}
	}

}
