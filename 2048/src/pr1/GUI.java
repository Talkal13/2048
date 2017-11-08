package pr1;


import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

class GUI {
  JFrame frame;

  public GUI(String name, Board b) {
    frame = new JFrame(name);
    frame.getContentPane().add(new GUIBoard(b));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   // frame.setPreferredSize(new Dimension(400, 400));
    frame.pack();
    frame.setVisible(true);


  }
}

class GUIBoard extends JPanel {
	
	protected static final int RECT_X = 400;
	protected static final int RECT_Y = RECT_X;
	protected static final int RECT_WIDTH = 400;
	protected static final int RECT_HEIGHT = RECT_WIDTH;
	private Board b;
	
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    // draw the rectangle here
	    
	    Graphics2D g2 = (Graphics2D) g;
	    for (int i = 0; i < b.getSize(); i++) {
	        for (int j = 0; j < b.getSize(); j++) {
	        	g2.drawRoundRect(RECT_X / b.getSize() * i, RECT_Y / b.getSize() * j, RECT_WIDTH / b.getSize(), RECT_HEIGHT  / b.getSize(), 20, 20);
	        	
	        	g2.scale(3.0, 3.0);
	        	g2.drawString(b.getCell(i, j).toString(), RECT_X / (b.getSize())  * i + RECT_X / b.getSize() / 2, RECT_Y / (b.getSize()) * j + RECT_Y / b.getSize() / 2);
	        	g2.scale(1/3, 1/3);
	        }
	      }
	    
	  }

	   @Override
	   public Dimension getPreferredSize() {
	      // so that our GUI is big enough
	      return new Dimension(RECT_WIDTH, RECT_HEIGHT);
	   }
	   
	   public GUIBoard(Board b) {
		   super();
		   this.b = b;
	   }
}

class GUICell extends JPanel {
  protected static final int RECT_X = 20;
  protected static final int RECT_Y = RECT_X;
  protected static final int RECT_WIDTH = 100;
  protected static final int RECT_HEIGHT = RECT_WIDTH;
  private int x, y;

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // draw the rectangle here
    g.drawRect(RECT_X * x + RECT_X, RECT_Y * y + RECT_Y, RECT_WIDTH, RECT_HEIGHT);
  }

   @Override
   public Dimension getPreferredSize() {
      // so that our GUI is big enough
      return new Dimension(RECT_WIDTH + 2 * RECT_X, RECT_HEIGHT + 2 * RECT_Y);
   }

   public GUICell(int x, int y) {
     super();
     this.x = x;
     this.y = y;
   }


}
