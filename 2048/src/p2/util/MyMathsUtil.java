package p2.util;

public class MyMathsUtil {

	// convert from long to int since we will not need to use large numbers
	public static int nextFib(int previous) {
	double phi = (1 + Math.sqrt(5)) / 2;
	return (int) Math.round(phi * previous);
	}
	
	public static int log(int base, int numb) {
		if (numb == base) return 1;
		else if (numb == 1) return 0;
		else if (numb < base) return -1;
		else {
			return 1 + log(base, numb/base);
		}
	}
	
}
