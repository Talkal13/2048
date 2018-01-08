package p2.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Pablo & Diego
 * 
 * Class which provides certain methods to handle the string for the game.
 */

public class MyStringUtils {
	
	/**
	 * Generates a string which will be filled with the element introduced as parameter length times.
	 * 
	 * @param element string which will be repeated and stored in the string to return.
	 * @param length number of times that the parameter element will be replicated.
	 * @return an String with the element repeated length times.
	 */
	
	public static String repeat(String elmnt, int length) {
		String result = "";
		
		for (int i = 0; i < length; i++) {
			result += elmnt;
		}
		return result;
	}
	
	/**
	 * Centers the parameter introduced in a space of length len.
	 * 
	 * @param text string which is going to be placed on the center.
	 * @param len spaces which will be left at each side.
	 * @return the string with the text centered.
	 */
	
	public static String centre(String text, int len){
		String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");
		float mid = (out.length()/2);
		float start = mid - (len/2);
		float end = start + len;
		
		return out.substring((int)start, (int)end);
	}
	
	// Used to exist method: org.eclipse.core.internal.resources.OS.isNameValid(filename).
	// This method is not completely reliable since exception could also be thrown due to:
	// incorrect permissions , no space on disk , problem accessing the device ,...
	public static boolean validFileName(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			return canWriteLocal(file);
		} else {
			try {
				file.createNewFile();
				file.delete();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
	
	public static boolean canWriteLocal(File file) {
		// works OK on Linux but not on Windows (apparently!)
		if (!file.canWrite()) {
			return false;
		}
		// works on Windows
		try {
			new FileOutputStream(file, true).close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}