package pr1.util;

public class MyStringUtils {
	
	/**
	 * generates a string wich will be fulled with the element introduced as paramether length times.
	 * 
	 * @param elmnt string which will be repited and stored in the string to return.
	 * @param length number of times that the paramether elmnt will be replicated.
	 * @return an String with the elmnt repited length times.
	 */
	
	public static String repeat(String elmnt, int length) {
		String result = "";
		
		for (int i = 0; i < length; i++) {
			result += elmnt;
		}
		return result;
	}
	
	/**
	 * Centers the paramether introduced in a space of length len.
	 * 
	 * @param text string which is going to be placed on the center.
	 * @param len spaces which will be left at eatch side.
	 * @return the string with the text centered.
	 */
	
	public static String centre(String text, int len){
		String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");
		float mid = (out.length()/2);
		float start = mid - (len/2);
		float end = start + len;
		
		return out.substring((int)start, (int)end);
	}
}