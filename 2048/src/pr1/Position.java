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
	
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets x
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets y
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets x and y
	 * @param x
	 * @param y
	 */
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
