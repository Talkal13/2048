/**
 * 
 */
package pr1;

/**
 * @author Pablo Vazquez & Diego Ambite
 *
 */
public class Cell {
	
	private Position pos;
	private int value = 0;
	private boolean empty = true;
	
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
	 * 
	 * @return value the value of the cell
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Checks whether or not a cell is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		//if the value is different than 0 means that the cell is not empty
		if(value != 0){
			empty = false;
		}
		return empty;
	}
	
	public void emptyCell() {
		empty = true;
	}
	
	/**
	 * Checks whether a merge is allowed betweena cell and the neighbour cell provided as argumet 
	 * if it result to merge, implements the merges returning a boolean value to indicate if the merge took place succesfully. 
	 * 
	 * @param neighbour
	 * @return
	 */
	public boolean doMerge(Cell neighbour){

		if (neighbour.getValue() == this.value || neighbour.isEmpty() || this.isEmpty()) {
			neighbour.setValue(value);
			emptyCell();
			return true;
			
		}
		return false;
	}
}
