package pr1;


import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

class GUI {
  private GUIBoard frame;
  
  public GUI(String name, Board b, Game c) {
    frame = new GUIBoard(name, b, c);
  }
  
  public void update() {
	  frame.repaint();
  }
  
}


class GUIBoard extends JFrame implements KeyListener {
	
	/**
	 * 
	 */
	protected static final int RECT_WIDTH = 100;
	protected static final int RECT_HEIGHT = RECT_WIDTH;
	private GUICell board[][];
	private int size;
	private Game control;
	

   @Override
   public Dimension getPreferredSize() {
      // so that our GUI is big enough
      return new Dimension(RECT_WIDTH * size, RECT_HEIGHT * size);
   }
   
   public GUIBoard (String name, Board b, Game c) {
	   super();
	   setResizable( false );
	   size = b.getSize();
	   control = c;
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
	   addKeyListener(this);
	   // frame.setPreferredSize(new Dimension(400, 400));
	   pack();
	   setVisible(true);
	   
	// Accessing components via index.
   }

@Override
public void keyTyped(KeyEvent e) {
	
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_UP:
			control.move(new Direction(DirectionOption.UP));
			break;
		case KeyEvent.VK_DOWN:
			control.move(new Direction(DirectionOption.DOWN));
			break;
		case KeyEvent.VK_RIGHT:
			control.move(new Direction(DirectionOption.RIGHT));
			break;
		case KeyEvent.VK_LEFT:
			control.move(new Direction(DirectionOption.LEFT));
			break;
		case KeyEvent.VK_R:
			control.reset();
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
		}
		if (control.isOver()) {
			System.exit(0);
		}
		repaint();
		
}

@Override
public void keyReleased(KeyEvent e) {
	
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
    //g.drawRect(RECT_X * pos.getX(), RECT_Y * pos.getY() , RECT_X, RECT_Y);
    this.setBackground(getColorOfCell());
    g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
    g.drawString(Integer.toString(cell.getValue()), (RECT_X - 20) / 2, (RECT_Y + 10) / 2);
  }
  
  private Color getColorOfCell() {
	  Color color = null;
	  switch (cell.getValue()) {
	  case 2:
		  color = Color.GRAY;
		  break;
	  case 4:
		  color = Color.DARK_GRAY;
		  break;
	  case 8:
		  color = Color.CYAN;
		  break;
	  case 16:
		  color = Color.BLUE;
		  break;
	  case 32:
		  color = Color.MAGENTA;
		  break;
	  case 64:
		  color = Color.BLACK;
		  break;
	  case 128:
		  color = Color.CYAN;
		  break;
	  case 256:
		  color = Color.YELLOW;
		  break;
	  case 512:
		  color = Color.RED;
		  break;
	  case 1024:
		  color = Color.GREEN;
		  break;
	  case 2048:
		  color = Color.ORANGE;
		  break;
	  default:
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




