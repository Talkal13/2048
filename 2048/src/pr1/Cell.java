package pr1;

import java.lang.String;

/**
 * @author Pablo Vazquez and Diego Ambite
 *
 * Class Cell which compose the board, each cell has a value and a certain position.
 */
public class Cell {
	
	private Position pos;
	private int value = 0;
	private boolean empty = true;
	
	/**
	 * Constructor of the class Cell.
	 * 
	 * @param x, reperesents the X position of the cell.
	 * @param y, reperesents the Y position of the cell.
	 */
	
	public Cell(int x, int y) {
		pos = new Position(x, y);
	}
	
	/**
	 * Changes the value of the current cell to the new value introduced as paramether.
	 * 
	 * @param newValue the new value that the cell will have.
	 */
	
	public void setValue(int newValue){
		this.value = newValue;
		this.empty = false;
	}
	
	/**
	 * Adds to the current value a new value intrcued as paramether.
	 * 
	 * @param newValue value to be increased.
	 */
	public void addValue(int newValue) {
		this.value += newValue;
	}
	
	/**
	 * Getter method of the class cell for its value.
	 * 
	 * @return value the value of the cell.
	 */
	
	public int getValue() {
		return value;
	}
		
	/**
	 * Getter for the position of the cell.
	 * 
	 * @return the position of the cell.
	 */
	
	public Position getPos() {
		return pos;
	}
	
	/**
	 * Checks whether or not a cell is empty.
	 * 
	 * @return false if is empty or true if doesnt.
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
	 * Checks whether a merge is allowed between a cell and the neighbour cell provided as argument 
	 * if it result to merge, implements the merges returning a boolean value to indicate if the merge took place succesfully. 
	 * 
	 * @param neighbour another cell which the merge could take place with.
	 * @return true in the case that the merge can take place, false if not.
	 */
	
	public boolean doMerge(Cell neighbour){
		//case the neighbour is an empty cell, we copy the value to this neighbour, we empty the current cell and we retrun false
		if (neighbour.isEmpty() && !this.isEmpty()) {
			neighbour.setValue(this.value);
			emptyCell();
			return false;
		}
		//if both the neighbour and the current aren't empty and share value, we add the value of the cell to the neighbour and we empty the cell
		else if (neighbour.getValue() == this.value && !neighbour.isEmpty() && !isEmpty()) {
			neighbour.addValue(value);
			emptyCell();
			//this.setMarked(true);
			return true;
		}
		return false;
	}
	
	/**
	 * Returns a string contaning the value of the cell.
	 * 
	 * @return a String containing the resut of the cell.
	 */
	
	public String toString(){	
		return String.valueOf(value);
	}
	
}
