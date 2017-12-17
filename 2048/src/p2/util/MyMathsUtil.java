package p2.util;

/**
 * @author Pablo & Diego
 *
 * Class which provides auxiliary mathematical methods to help the development of the games.
 */

public class MyMathsUtil {

	/**
	 * Calculates the next number in the Fibonacci series after one introduced as parameter.
	 * 
	 * @param previous number which next number in the fibonacci series we want to calculate.
	 * @return the next number in the fibonacci series after the one introduced as parameter.
	 */
	
	public static int nextFib(int previous) {
		double phi = (1 + Math.sqrt(5)) / 2;
		return (int) Math.round(phi * previous);	// convert from long to int since we will not need to use large numbers
	}
	
	/**
	 * Calculates the result of the logarithmic of one number in base to other, both passed as parameter.
	 * 
	 * @param base base of the logarithmic.
	 * @param numb number which logarithmic we want to calculate.
	 * @return the result of the logarithmic, if the base is greater than the numb, the result will be -1 because there wont be a logical answer.
	 */
	
	public static int log(int base, int numb) {
		if (numb == base) return 1;
		else if (numb == 1) return 0;
		else if (numb < base) return -1;
		else {
			return 1 + log(base, numb/base);
		}
	}
	
}
