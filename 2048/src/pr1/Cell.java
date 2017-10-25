/**
 * 
 */
package pr1;

import java.lang.String;

/**
 * @author Pablo Vazquez and Diego Ambite
 *
 */
public class Cell {
	
	private Position pos;
	private int value = 0;
	private boolean empty = true;
	private boolean marked = false;
	
	/**
	 * Constructor of the class Cell
	 * 
	 * @param x, reperesents the X position of the cell.
	 * @param y, reperesents the Y position of the cell.
	 */
	public Cell(int x, int y) {
		pos = new Position(x, y);
	}
	
	/**
	 * changes the value of the current cell to the new value introduced as parameter
	 * 
	 * @param newValue the new value that the cell will have
	 */
	public void setValue(int newValue){
		this.value = newValue;
		empty = false;
	}
	
	/**
	 * Adds to the current value a new value intrcued as parameter
	 * 
	 * @param newValue value to be increased
	 */
	public void addValue(int newValue) {
		this.value += newValue;
	}
	
	/**
	 * getter method of the class caell for its value
	 * 
	 * @return value the value of the cell
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 
	 * @param x
	 */
	
	public void setMarked(boolean x) {
		marked = x;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getMarked() {
		return marked;
	}
	
	/**
	 * Checks whether or not a cell is empty
	 * 
	 * @return false if is empty or true if doesnt
	 */
	public boolean isEmpty() {
		//if the value is different than 0 means that the cell is not empty
		if(value != 0){
			empty = false;
		}
		return empty;
	}
	
	/**
	 * Set the value of the cell to 0 and set the boolean empty to true
	 */
	public void emptyCell() {
		value = 0;
		empty = true;
	}
	
	/**
	 * Checks whether a merge is allowed between a cell and the neighbour cell provided as argumet 
	 * if it result to merge, implements the merges returning a boolean value to indicate if the merge took place succesfully. 
	 * 
	 * @param neighbour another cell which the merge could take place with
	 * @return true in the case that the merge can take place, false if not
	 */
	public boolean doMerge(Cell neighbour){
		if (neighbour.isEmpty() && !this.isEmpty()) {
			neighbour.setValue(this.value);
			emptyCell();
			return false;
		}
		else if (neighbour.getValue() == this.value && !neighbour.isEmpty() && !isEmpty()) {
			neighbour.addValue(value);
			emptyCell();
			//this.setMarked(true);
			return true;
			
		}
		return false;
	}
	
	/**
	 * returns a string contaning the value of the cell
	 * 
	 * @return a String containing the resut of the cell
	 */
	public String toString(){	
		return String.valueOf(value);
	}
	
}
