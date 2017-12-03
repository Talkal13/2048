package p2.util;

/**
 * @author Pablo and Diego
 * 
 * Class which contains the direction of a movement.
 */

public class Direction {
	
	DirectionOption dir;
	
	/**
	 * Constructor, instantiates a Direction Object with a direction introduced as paramether.
	 *
	 * @param x direction to achieve the movement.
	 */
	
	public Direction(DirectionOption x) {
		dir = x;
	}
	
	/**
	 * Checks if the direction introduced as parameter is the same as the direction of the class.
	 * 
	 * @param x direction to check.
	 * @return true in case is equal and false if it doesn't.
	 */
	
	public boolean equals(DirectionOption x) {
		return (dir == x);
	}
	
	/**
	 * Getter of the direction of the class.
	 * 
	 * @return the direction of the object.
	 */
	
	public DirectionOption getDirection() {
		return dir;
	}
	
	/**
	 * Setter of the direction of the class.
	 * 
	 * @param x new value to store in the variable x of the class.
	 */
	
	public void setDirection(DirectionOption x) {
		dir = x;
	}
}
