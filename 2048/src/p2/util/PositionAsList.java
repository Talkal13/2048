package p2.util;

/**
 * Class which extends the class ArrayAsList to insert certain positions to the List of free positions.
 */

public class PositionAsList extends ArrayAsList {
	/*
	public Position get(int index) {
		return (Position) super.get()[index];
	}
	
	public void set(int index, Position x) {
		super.set(index, x);
	}
	*/
	
	/**
	 * Constructor of the class
	 * 
	 * @param lenght of the List to be created.
	 */
	
	public PositionAsList(int lenght) {
		super(lenght);
		this.set(new Position[lenght]);
	}
	
	/**
	 * Inserts a position introduced as paramether in the List. If the list isnt full ot the position is already on the list.
	 * 
	 * @param o Position to include in the List.
	 * @return true if it was possible and false if it wasnt.
	 */
	
	public boolean insert(Position o) {
		if (!isFull() && getIndex(o) == -1) {
			set(size, o);
			size++;
			return true;
		}
		return false;
	}
	
	/**
	 * Pops the Element in the position introduced as paramether, by moving all the elements on the list one position to the left.
	 * 
	 * @param o Position to pop.
	 * @return tru if the pop took place correcctly and false if it doesnt.
	 */
	
	public boolean pop(Position o) {
		int index = getIndex(o);
		
		if (index == -1) return false;
		for (int i = index; i < size - 1; i++) {
			set(i, get(i + 1));
		}
		size--;
		return true;
	}
	
	/**
	 * Given a certain Position passed as paramether returns its index in the list if it is on this list.
	 * 
	 * @param o Position which index we are looking for.
	 * @return the index of this position on the list or -1 in the case that is noton the list.
	 */
	
	private int getIndex(Position o) {
		for (int i = 0; i < size; i++) {
			if (o.equals((Position) get(i))) return i;
		}
		return -1;
	}
	
}
