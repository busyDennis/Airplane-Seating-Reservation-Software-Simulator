import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 * Airplane model Boeing777 that contains 28 seats of the economy class and 28 seats of the de luxe class.
 * @author Denis Bezverbnyy
 * #500474359
 * CPS 209 assignment 2.
 */
public class Boeing777 extends Boeing737 implements Serializable
{
  final int FIRST_CLASS_ROWS = 4;
  final int FIRST_CLASS_COLUMNS = 7;
  public final int FIRST_CL_WIDTH = 230;
  
  /**
   * Creates a Boeing777 model with specified coordinates of the upper left corner in 2D plane.
   * @param x x coordinate.
   * @param y y coordinate.
   */
  public Boeing777(int x, int y)
  {
    super(x, y);
    final int DIST_LEFT = 245;
    final int DIST_TOP = 35;
    final int FIRST_CL_1_ROW_INDEX = 8; //1st row index of the economy class
    createSeats(super.getX() + DIST_LEFT, super.getY() + DIST_TOP, FIRST_CLASS_ROWS, FIRST_CLASS_COLUMNS,
    		FIRST_CL_1_ROW_INDEX, true);
  }
  
  /**
   * Draws the Boeing777 model.
   * @param g2 object to draw on.
   */
  public void draw(Graphics2D g2)
  {
    final int R_DIST_LEFT = 230;
    final int STRING_DIST_LEFT = 310;
    final int STRING_DIST_TOP = 15;
    
    g2.drawString("FIRST CLASS", super.getX() + STRING_DIST_LEFT, super.getY() + STRING_DIST_TOP);
    super.draw(g2);
    
    Rectangle r = new Rectangle(super.getX() + R_DIST_LEFT, super.getY() + UPPER_INDENT, FIRST_CL_WIDTH, HEIGHT);
    g2.draw(r);
  }
}

