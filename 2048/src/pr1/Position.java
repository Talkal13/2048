/**
 * 
 */
package pr1;

/**
 * @author Pablo and Diego
 * @version 1.0
 * @since 1.0
 *
 */
public class Position {
	/**
	 * Defines the x position
	 */
	private int x;
	/**
	 * Defines the y position
	 */
	private int y;
	
	/**
	 * Constructr of the class, sets the x and y values to the ones introduced
	 * 
	 * @param x sets the value of the row
	 * @param y sets the value of the column
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * getter method of the row of the cell
	 * 
	 * @return x return the row of the cell
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * getter method of the column of the cell
	 * 
	 * @return y return the column of the cell
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * setter method for the row of the cell
	 * 
	 * @param x new value to set on the row cell
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * setter method for the column of the cell
	 * 
	 * @param y new value to set on the column of the cell
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets x and y in the same function
	 * 
	 * @param x sets the new value of the row
	 * @param y sets the new value of the column
	 */
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
