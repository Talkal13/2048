package pr1.util;

import java.util.Random;


public class ArrayAsList {
// The rest of the code for the ArrayAsList class is to be added here.
// The other methods of this class will not be static .
// This method is static in order to be similar to the "shuffle () "
// method of the standard library class "Collections ".
	private int size;
	private Object arrayAsList[];
	
	public ArrayAsList(int lenght) {
		size = lenght;
		arrayAsList = new Object[size];
	}
	
	public static void shuffle(ArrayAsList list, Random random) {
			for (int i = list.size(); i > 1; i--) {
				swap(list.arrayAsList, i - 1, random.nextInt(i));
			}
	}
	
	/**
	 * method to get the size of the array
	 * 
	 * @return the size of the array
	 */
	public int size() {
		return size;
	}
	
	/**
	 * return the position (Object) which has been indicated in the paramether index
	 * 
	 * @param index the position of the array we are interested in getting
	 * @return the object which contains the cell of the array
	 */
	public Object get(int index){
		return arrayAsList[index];
	}

	// This method is static in order to be similar to the "shuffle () " method.
	public static Object choice(ArrayAsList list, Random random) {
		return list.get(random.nextInt(list.size()));
	}
	
	/**
	 * swap to positions of a given array
	 * 
	 * @param anArray array which contains the position to be swapped
	 * @param i position which will be overwritten and then restored from the temporal value
	 * @param j position which will overwrite i
	 */
	private static void swap(Object[] anArray, int i, int j) {
		Object temp = anArray[i];
		anArray[i] = anArray[j];	
		anArray[j] = temp;
	}
}