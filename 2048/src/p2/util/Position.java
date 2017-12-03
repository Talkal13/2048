package p2.util;

import p2.util.ArrayAsList;

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
