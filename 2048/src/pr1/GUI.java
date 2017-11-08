package pr1;


import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;

class GUI {
  JFrame frame;

  private void drawCells(Board b) {
    for (int i = 0; i < b.getSize(); i++) {
      for (int j = 0; j < b.getSize(); j++) {
          frame.getContentPane().add(new GUICell(i, j));
      }
    }
  }

  public GUI(String name, Board b) {
    frame = new JFrame(name);
    drawCells(b);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(200 * b.getSize(), 200 * b.getSize()));
    frame.pack();
    frame.setVisible(true);


  }
}

class GUICell extends JPanel {
  protected static final int RECT_X = 30;
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
