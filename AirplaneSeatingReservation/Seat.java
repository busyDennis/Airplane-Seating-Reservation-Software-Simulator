import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 * An airplane seat of the economy class (painted in pink).
 * @author Denis Bezverbnyy
 * #500474359
 * CPS 209 assignment 2.
 */
public class Seat implements Serializable
{
  private String name;
  private boolean occupied; //true if occupied
  private int xLeft;
  private int yTop;
  public static final int WIDTH = 23;
  public static final int HEIGHT = 23;
  Rectangle r;
  
  /**
   * Creates an empty seat.
   * @param aName seat name.
   * @param x x coordinate.
   * @param y y coordinate.
   */
  public Seat(String aName, int x, int y)
  {
    name = aName;
    occupied = false;
    xLeft = x;
    yTop = y;
    r = new Rectangle(xLeft, yTop, WIDTH, HEIGHT);
  }
  
  /**
   * Draws the seat.
   * @param g2 object to draw on.
   */
  public void draw(Graphics2D g2)
  {
    if (occupied == false)
      g2.setColor(Color.PINK);
    else
      g2.setColor(Color.RED);
    g2.fill(r);
    g2.setColor(Color.GRAY);
    g2.draw(r);
    g2.setColor(Color.BLACK);
    g2.drawString(name, xLeft + 2, yTop + HEIGHT - 4);
  }
  
  /**
   * Get the seat name.
   * @return the seat name.
   */
  public String getName()
  {
    return name;
  }
  
  /**
   * Get the seat occupancy.
   * @return true if occupied.
   */
  public boolean getOccupancy()
  {
    return occupied;
  }
  
  /**
   * Get the seat x-coordinate.
   * @return the seat x-coordinate.
   */
  public int getX()
  {
    return xLeft;
  }
  
  /**
   * Get the seat y-coordinate.
   * @return the seat y-coordinate.
   */
  public int getY()
  {
    return yTop;
  }
  
  /**
   * Checks if the seat is selected with an (x, y) dot.
   * @param x x coordinate.
   * @param y y coordinate.
   * @return true if the seat is selected.
   */
  public boolean isSelected(double x, double y)
  {
    if (r.contains(x, y)) return true;
    else return false;
  }
  
  /**
   * Set the seat occupancy.
   * @param occ occupancy state, occupied if true.
   */
  public void setOccupancy(boolean occ)
  {
    occupied = occ;
  }
  
  /**
   * Set the seat size.
   * @param w the seat width.
   * @param h the seat height.
   */
  public void setSize(int w, int h)
  {
    r.setSize(w, h);
  }
  
}
