import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 * An airplane seat of the de luxe class (painted in blue).
 * @author Denis Bezverbnyy
 * #500474359
 * CPS 209 assignment 2.
 */
public class DeLuxeSeat extends Seat implements Serializable
{
  /**
   * Creates an empty seat.
   * @param aName seat name.
   * @param x x coordinate.
   * @param y y coordinate.
   */
  public DeLuxeSeat(String aName, int x, int y)
  {
    super(aName, x, y);
  }
  
  /**
   * Draws the seat.
   * @param g2 object to draw on.
   */
  public void draw(Graphics2D g2)
  {
    if (super.getOccupancy() == false)
      g2.setColor(Color.CYAN);
    else
      g2.setColor(Color.BLUE);
    g2.fill(r);
    g2.setColor(Color.GRAY);
    g2.draw(r);
    g2.setColor(Color.BLACK);
    g2.drawString(super.getName(), super.getX() + 2, super.getY() + HEIGHT - 4);
  }
}
