package pr1;


import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;

class GUI {
  GUIBoard frame;

  public GUI(String name, Board b) {
    frame = new GUIBoard(name, b);
  }
}

class GUIBoard extends JFrame {
	
	protected static final int RECT_WIDTH = 100;
	protected static final int RECT_HEIGHT = RECT_WIDTH;
	private GUICell board[][];
	private int size;
	

   @Override
   public Dimension getPreferredSize() {
      // so that our GUI is big enough
      return new Dimension(RECT_WIDTH * size, RECT_HEIGHT * size);
   }
   
   public GUIBoard(String name, Board b) {
	   super();
	   setResizable( false );
	   size = b.getSize();
       setLayout( new GridLayout(size,size ) );
	   setSize(b.getSize() * RECT_WIDTH, b.getSize() * RECT_HEIGHT);
	   board = new GUICell[size][size];
	   Container container = getContentPane();
	   ArrayList < JPanel > components = new ArrayList < JPanel >();
	   
	  
	   for (int i = 0; i < b.getSize(); i++) {
		   for (int j = 0; j < b.getSize(); j++) {
			   GUICell temp = new GUICell(b.getCell(i, j));
			   container.add(temp);
			   components.add(temp);
		   }
	   }
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   // frame.setPreferredSize(new Dimension(400, 400));
	   pack();
	   setVisible(true);
	   
	// Accessing components via index.
       components.get( 5 ).setBackground( Color.BLUE );
       components.get( 8 ).setBackground( Color.GREEN );
   }
}

class GUICell extends JPanel {
  protected static final int RECT_X = 100;
  protected static final int RECT_Y = RECT_X;
  private Cell cell;

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Position pos = cell.getPos();
    g.drawRect(RECT_X * pos.getX(), RECT_Y * pos.getY() , RECT_X, RECT_Y);
    g.setColor(getColorOfCell());
  }
  
  private Color getColorOfCell() {
	  Color color = null;
	  switch (cell.getValue()) {
	  case 0:
		  color = null;
	  }
	  return color;
  }
   public GUICell(Cell a) {
     super();
     cell = a;
     setSize(RECT_X, RECT_Y);
     setBorder(BorderFactory.createLineBorder(Color.black));
   }


}

