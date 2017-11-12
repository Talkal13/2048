package pr1;

import pr1.util.ArrayAsList;

/**
 * @author Pablo and Diego
 * 
 * Class which represents the position, each cell will have a position.
 */

public class Position {

	private int x;
	private int y;
	
	/**
	 * Constructor of the class, sets the x and y values to the ones introduced.
	 * 
	 * @param x sets the value of the row.
	 * @param y sets the value of the column.
	 */
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter method of the row of the cell.
	 * 
	 * @return x return the row of the cell.
	 */
	
	public int getX() {
		return x;
	}
	
	/**
	 * Getter method of the column of the cell.
	 * 
	 * @return y return the column of the cell.
	 */
	
	public int getY() {
		return y;
	}
	
	/**
	 * Setter method for the row of the cell.
	 * 
	 * @param x new value to set on the row cell.
	 */
	
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Setter method for the column of the cell.
	 * 
	 * @param y new value to set on the column of the cell.
	 */
	
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets x and y in the same function.
	 * 
	 * @param x sets the new value of the row.
	 * @param y sets the new value of the column.
	 */
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Equalizer method, checks if the positition of the paramether and the one of the class are equals.
	 * 
	 * @param position2 position to compare with the one of the class.
	 * @return true if the values x and y are equals.
	 */
	
	public boolean equals(Position position2) {
		return this.x == position2.getX() && this.y == position2.getY();
	}
}
/**
 * Class which extends the class ArrayAsList to insert certain positions to the List of free positions.
 */

class PositionAsList extends ArrayAsList {
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
	
	PositionAsList(int lenght) {
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
