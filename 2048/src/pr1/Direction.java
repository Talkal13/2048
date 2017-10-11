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
	
	
	/**
	 * Stores the direction
	 */
	
	DirectionOption dir;
	
	/**
	 * Constructor 
	 * <p>
	 * Instantiates a Direction Object with dir = x;
	 *
	 * @param x 
	 *
	 */
	
	public Direction(DirectionOption x) {
		dir = x;
	}
	
	public boolean equals(DirectionOption x) {
		return (dir == x);
	}
	
	/**
	 * 
	 * @return the direction of the object
	 */
	
	public DirectionOption getDirection() {
		return dir;
	}
	
	/**
	 * Sets dir = x
	 * @param x
	 */
	
	public void setDirection(DirectionOption x) {
		dir = x;
	}
}
