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



public class Direction {
	
	
	//Stores the direction of the movement
	
	DirectionOption dir;
	
	/**
	 * Constructor, instantiates a Direction Object with a direction introduced as parameter
	 *
	 * @param x direction to achieve the movement
	 */
	
	public Direction(DirectionOption x) {
		dir = x;
	}
	
	/**
	 * checks if the direction introduced as parameter is the same as the direction of the class
	 * 
	 * @param x direction to check
	 * @return true in case is equal and false if it doesn't
	 */
	
	public boolean equals(DirectionOption x) {
		return (dir == x);
	}
	
	/**
	 * getter of the direction of the class
	 * 
	 * @return the direction of the object
	 */
	
	public DirectionOption getDirection() {
		return dir;
	}
	
	/**
	 * setter of the direction of the class
	 * 
	 * @param x new value to store in the variable x of the class
	 */
	
	public void setDirection(DirectionOption x) {
		dir = x;
	}
}
