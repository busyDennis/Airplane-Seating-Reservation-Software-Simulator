import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Basic airplane model Boeing737 that contains 28 seats of the economy class.
 * @author Denis Bezverbnyy
 * #500474359
 * CPS 209 assignment 2.
 */
public class Boeing737 implements Serializable
{
  public final int DIST = 7; //distance between seats
  public final int ROWS = 4;//number of rows
  public final int COLUMNS = 7;//number of columns
  public final int UPPER_INDENT = 20; //upper indent for the block of seats in a bevel, the space is used to 
                                      //label all sections, such as the "economy class"
  final int ECONOMY_WIDTH = 230; //width of the economy class section in 2D representation
  final int HEIGHT = 160; //height of the section in 2D representation
  
  private int xLeft;//x position
  private int yTop;//y position
  private int width;//width of the whole model in 2D representation
  private int height;//height of the whole model in 2D representation

  ArrayList<Seat> seatList; //all seats of the model
  
  /**
   * Creates a Boeing737 model with specified coordinates of the upper left corner in 2D plane.
   * @param x x coordinate.
   * @param y y coordinate.
   */
  public Boeing737(int x, int y)
  {
    xLeft = x;
    yTop = y;
    seatList = new ArrayList<Seat>();
    width = ECONOMY_WIDTH;
    height = HEIGHT + UPPER_INDENT;
    final int DIST_LEFT = 15;//distance from leftmost seats to the left side
    final int DIST_TOP = 35;//distance from the uppermost seats to the top
    final int EC_CL_1_ROW_INDEX = 1; //1-st row index of the economy class
    createSeats(xLeft + DIST_LEFT, yTop + DIST_TOP, ROWS, COLUMNS, EC_CL_1_ROW_INDEX, false);
  }
  

  
  /**
   * A method that creates seats.
   * @param xLoc x position.
   * @param yLoc y position.
   * @param rws number of rows.
   * @param clmns number of columns.
   * @param startCol number marking the first column.
   * @param lux if true creates de luxe seats, otherwise creates economy class seats.
   */
  public void createSeats(int xLoc, int yLoc, int rws, int clmns, int startCol, boolean lux)
  {
    int xpointer = xLoc;//location of a seat
    int ypointer = yLoc;
    final char START_ROW = 'A';
    char currRow = START_ROW;//used to create seat names
    int currCol = startCol;
    for (int i = 0; i < rws; i++)
    {
      xpointer = xLoc;
      currCol = startCol;
      if (rws % 2 == 0)
      {
    	  if(i == rws / 2) // there is a larger distance in the center
            ypointer += 2*Seat.HEIGHT;
    	  else if (i > 0)
            ypointer += DIST + Seat.HEIGHT;
      }
      else if (i > 0)
        ypointer += DIST + Seat.HEIGHT;
      if (i > 0)
        currRow = (char) ((int) currRow + 1);
      for (int j=0; j < clmns; j++)
      {
        if(j > 0)
        {
    	  xpointer += DIST + Seat.WIDTH;
    	  currCol += 1;
        }
        String name = "" + currCol + currRow; // name for a seat
        Seat s;
        if(lux == false)
          s = new Seat(name, xpointer, ypointer);
        else
          s = new DeLuxeSeat(name, xpointer, ypointer);
        seatList.add(s);
      }
    }
  }
  
  /**
   * Draws the Boeing737 model.
   * @param g2 object to draw on.
   */
  public void draw(Graphics2D g2)
  {
    final int DIST_LEFT = 70;
    final int DIST_TOP = 15;
    g2.drawString("ECONOMY CLASS", xLeft + DIST_LEFT, yTop + DIST_TOP);
    Rectangle r = new Rectangle(xLeft, yTop + UPPER_INDENT, ECONOMY_WIDTH, HEIGHT);
    g2.draw(r);
    for (int i = 0; i < seatList.size(); i++)
    {
      seatList.get(i).draw(g2);
    }
  }
  
  /**
   * Get height of the model.
   * @return height of the model.
   */
  public int getHeight()
  {
    return height;
  }
  
  /**
   * Returns a seat if point (x, y) is contained inside.
   * @param x x coordinate.
   * @param y y coordinate.
   * @return seat or null if seat was not found.
   */
  public Seat getSeat(double x, double y)
  {
    for (int i = 0; i < seatList.size(); i++)
    {
      if (seatList.get(i).isSelected(x, y))
    	  return seatList.get(i);
    }
    return null;
  }
  
  /**
   * Being given a seat name, gets index of a seat in the ArrayList.
   * @param seatName seat name.
   * @return index in the ArrayList if a seat is found, -1 otherwise.
   */
  public Seat getSeat(String seatName)
  {
    for (int i = 0; i < seatList.size(); i++)
    {
      if (seatList.get(i).getName().equals(seatName))
    	  return seatList.get(i);
    }
    return null;
  }
  
  /**
   * Get width of the model.
   * @return width of the model.
   */
  public int getWidth()
  {
    return width;
  }
  
  /**
   * Get x-coordinate of the model.
   * @return x-coordinate of the model.
   */
  public int getX()
  {
    return xLeft;
  }
  
  /**
   * Get y-coordinate of the model.
   * @return y-coordinate of the model.
   */
  public int getY()
  {
    return yTop;
  }
  
  /**
   * Set height of the model.
   * @param h height of the model.
   */
  public void setHeight(int h)
  {
    height = h;
  }
  
  /**
   * Set width of the model.
   * @param w width of the model.
   */
  public void setWidth(int w)
  {
    width = w;
  }
  
}
