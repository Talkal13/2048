package p2.util;

import java.util.Random;

/**
 * @author Pablo & Diego
 *
 * Class which provides the method to create a list from an array and manipulate it.
 */

public class ArrayAsList {
// The rest of the code for the ArrayAsList class is to be added here.
// The other methods of this class will not be static .
// This method is static in order to be similar to the "shuffle () "
// method of the standard library class "Collections ".
	protected int size;
	private int maxSize;
	private Object arrayAsList[];
	
	/**
	 * Constructor of the class, creates the list with a size of 0.
	 * 
	 * @param lenght length which will have the Arraylist of the class.
	 */
	
	public ArrayAsList(int lenght) {
		maxSize = lenght;
		set(new Object[maxSize]);
		size = 0;
	}
	
	/**
	 * Shuffles the Arraylist by swapping the positions of the arrayListthe length of the array times.
	 * 
	 * @param list the list to be shuffled.
	 * @param random seed used to get the random value to swap.
	 */
	
	public static void shuffle(ArrayAsList list, Random random) {
			for (int i = list.size(); i > 1; i--) {
				swap(list.get(), i - 1, random.nextInt(i));
			}
	}
	
	/**
	 * Checks if the object is full.
	 * 
	 * @return true if is full, false otherwise.
	 */
	
	public boolean isFull() {
		return size == maxSize;
	}
	
	/**
	 * Checks if the array is empty.
	 * 
	 * @return true if is empty, false otherwise.
	 */
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Method to get the size of the array.
	 * 
	 * @return the size of the array.
	 */
	
	public int size() {
		return size;
	}
	
	/**
	 * Insert in the index position of the list the object introduced as parameter.
	 * 
	 * @param index index where the object will be placed.
	 * @param o object position to introduce in the list.
	 * @return true if the setting was possible, false if it wasn't.
	 */
	
	protected boolean set(int index, Object o) throws IndexOutOfBoundsException {
		if (index > size) throw new IndexOutOfBoundsException();
		arrayAsList[index] = o;
		return true;
	}
	
	/**
	 * Return the position (Object) which has been indicated in the parameter index.
	 * 
	 * @param index the position of the array we are interested in getting.
	 * @return the object which contains the cell of the array.
	 */
	
	public Object get(int index) throws IndexOutOfBoundsException {
		if (index > size) throw new IndexOutOfBoundsException();
		return arrayAsList[index];
	}

	/**
	 * Choose a random position (Object)of the Array.
	 * This method is static in order to be similar to the "shuffle () " method.
	 * 
	 * @param list which the random position position will be chosen from.
	 * @param random seed which will be used to select the random position.
	 * @return the random position encapsulated in an object.
	 */
	
	public static Object choice(ArrayAsList list, Random random) {
		return list.get(random.nextInt(list.size()));
	}
	
	/**
	 * Swap to positions of a given array.
	 * 
	 * @param anArray array which contains the position to be swapped.
	 * @param i position which will be overwritten and then restored from the temporal value.
	 * @param j position which will overwrite i.
	 */
	
	private static void swap(Object[] anArray, int i, int j) {
		Object temp = anArray[i];
		anArray[i] = anArray[j];	
		anArray[j] = temp;
	}
	
	/**
	 * Inserts the object introduced as parameter at the end of the list.
	 * 
	 * @param o object Position to include in the list
	 * @return true if the object was introduced correctly or false if it wasn't.
	 */
	
	public boolean insert(Object o) {
		if (!isFull() && getIndex(o) == -1) {
			get()[size] = o;
			size++;
			return true;
		}
		return false;
	}
	
	/**
	 * Pops one Object introduced as parameter, by moving all the elements in the list that are on its right one position to its left.
	 * 
	 * @param o object to pop from the list
	 * @return true if it was possible of false if it wasn't.
	 */
	
	public boolean pop(Object o) {
		int index = getIndex(o);
		if (index == -1) return false;
		for (int i = index; i < size - 1; i++) {
			get()[i] = get()[i + 1];
		}
		size--;
		return true;
	}
	
	/**
	 * Gets the index of a certain object introduced as parameter.
	 * 
	 * @param o object position which index we want to know.
	 * @return the index of the object in the list, or -1 if is not on the list.
	 */
	
	private int getIndex(Object o) {
		for (int i = 0; i < size; i++) {
			if (get()[i].equals(o)) return i;
		}
		return -1;
	}
	
	/**
	 * Stores in one string the all the objects stored in the ArrayList.
	 * 
	 * @return the string containing all the positions.
	 */
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < size; i++) {
			s += get()[i] + " ";
		}
		s += "\n";
		return s;
	}

	/**
	 * Getter of the class for its ArrayList.
	 * 
	 * @return the arrayAsList
	 */
	public Object[] get() {
		return arrayAsList;
	}

	/**
	 * Setter f the class for its ArrayList.
	 * 
	 * @param arrayAsList the arrayAsList to set.
	 */
	
	public void set(Object arrayAsList[]) {
		this.arrayAsList = arrayAsList;
	}
	
}

