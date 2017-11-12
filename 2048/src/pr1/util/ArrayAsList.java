package pr1.util;


import java.util.Random;


public class ArrayAsList {
// The rest of the code for the ArrayAsList class is to be added here.
// The other methods of this class will not be static .
// This method is static in order to be similar to the "shuffle () "
// method of the standard library class "Collections ".
	protected int size;
	private int maxSize;
	private Object arrayAsList[];
	
	/**
	 * Constructor of the class
	 * 
	 * @param lenght lenght which will have the Arraylist of the class
	 */
	public ArrayAsList(int lenght) {
		maxSize = lenght;
		set(new Object[maxSize]);
		size = 0;
	}
	
	/**
	 * shuffles the Arraylist by swaping the positions of the arrayListthe length of the array times
	 * 
	 * @param list the list to be shuffled
	 * @param random seed used to get the random value to sawp
	 */
	public static void shuffle(ArrayAsList list, Random random) {
			for (int i = list.size(); i > 1; i--) {
				swap(list.get(), i - 1, random.nextInt(i));
			}
	}
	
	/**
	 * 
	 * Checks if the object is full
	 * 
	 * @return true if is full, false otherwise
	 */
	
	public boolean isFull() {
		return size == maxSize;
	}
	
	/**
	 * 
	 * Checks if the array is empty
	 * 
	 * @return true if is empty, false otherwise
	 */
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * method to get the size of the array
	 * 
	 * @return the size of the array
	 */
	public int size() {
		return size;
	}
	
	protected boolean set(int index, Object o) {
		if (index > size) return false;
		arrayAsList[index] = o;
		return true;
	}
	
	/**
	 * return the position (Object) which has been indicated in the paramether index
	 * 
	 * @param index the position of the array we are interested in getting
	 * @return the object which contains the cell of the array
	 */
	public Object get(int index){
		if (index > size) return null;
		return arrayAsList[index];
	}

	/**
	 * choose a random position (Object)of the Array
	 * This method is static in order to be similar to the "shuffle () " method.
	 * 
	 * @param list which the random position position will be chosen from
	 * @param random seed which will be ussed to select the random position 
	 * @return the radom position encapsulated in an object
	 */
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
	
	public boolean insert(Object o) {
		if (!isFull() && getIndex(o) == -1) {
			get()[size] = o;
			size++;
			return true;
		}
		return false;
	}
	
	public boolean pop(Object o) {
		int index = getIndex(o);
		if (index == -1) return false;
		for (int i = index; i < size - 1; i++) {
			get()[i] = get()[i + 1];
		}
		size--;
		return true;
	}
	
	private int getIndex(Object o) {
		for (int i = 0; i < size; i++) {
			if (get()[i].equals(o)) return i;
		}
		return -1;
	}
	
	
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
	 * @return the arrayAsList
	 */
	public Object[] get() {
		return arrayAsList;
	}

	/**
	 * @param arrayAsList the arrayAsList to set
	 */
	public void set(Object arrayAsList[]) {
		this.arrayAsList = arrayAsList;
	}
	
}

