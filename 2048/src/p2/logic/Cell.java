/**
 * 
 */
package p2.logic;

import java.lang.String;

import p2.util.Position;

/**
 * @author Pablo & Diego.
 *
 * Class which represents each of the cells that compose a board.
 */
public class Cell {
	
	private Position pos;
	private int value = 0;
	private boolean empty = true;
	
	/**
	 * Constructor of the class Cell.
	 * 
	 * @param x, represents the X position of the cell.
	 * @param y, represents the Y position of the cell.
	 */
	
	public Cell(int x, int y) {
		pos = new Position(x, y);
	}
	
	public Cell(int i, int j, int k) {
		pos = new Position(i, j);
		value = k;
		if (k != 0) {
			empty = false;
		}
	}

	/**
	 * Changes the value of the current cell to the new value introduced as parameter.
	 * 
	 * @param newValue the new value that the cell will have.
	 */
	
	public void setValue(int newValue){
		this.value = newValue;
		if (newValue == 0) {
			empty = true;
		}
		else 
			empty = false;
	}
	
	/**
	 * Adds to the current value a new value introduced as parameter.
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
	 * @return false if is empty or true if doesn't.
	 */
	
	public boolean isEmpty() {
		//if the value is different than 0 means that the cell is not empty
		if(value != 0){
			empty = false;
		}
		return empty;
	}
	
	/**
	 * Set the value of the cell to 0 and set the boolean empty to true.
	 */
	
	public void emptyCell() {
		value = 0;
		empty = true;
	}
	
	/**
	 * Checks whether a merge is allowed between a cell and the neighbor cell provided as argument 
	 * if it result to merge, implements the merges returning a boolean value to indicate if the merge took place successfully. 
	 * 
	 * @param neighbour another cell which the merge could take place with.
	 * @return true in the case that the merge can take place, false if not.
	 */
	
	public int doMerge(Cell neighbour, GameRules currentRules){
		return currentRules.merge(this, neighbour);
	}
	
	/**
	 * Returns a string containing the value of the cell.
	 * 
	 * @return a String containing the result of the cell.
	 */
	
	public String toString(){	
		return String.valueOf(value);
	}
	
	/**
	 * Checks if the cell and one passed as parameter are equal or not by checking if have the same value and none of them is empty.
	 * 
	 * @param a cell to compare with.
	 * @return true if are not empty and have the same values.
	 */
	
	public boolean equals(Cell a) {
		return a.getValue() == getValue() && !a.isEmpty() && !isEmpty();
	}
	
}

