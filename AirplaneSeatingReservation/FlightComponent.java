import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.IllegalArgumentException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class creates a component which can display seat configurations of different airline flights.
 * @author Denis Bezverbnyy
 * #500474359
 * CPS 209 assignment 2.
 */
public class FlightComponent extends JComponent
{
  //Left and upper indents for a seat configuration
  final int LEFT_INDENT = 30;
  final int UPPER_INDENT = 0;
  //This ArrayList stores all flights used
  ArrayList<AirlineFlight> flights;
  //chosenFlight is a flight being currently displayed
  AirlineFlight chosenFlight;
  //passengerName is a passenger name currently entered by user
  String passengerName;
  
  /**
   * Creates a FlightComponent; in case when data is already stored in a file flights.dat, reads it,
   * otherwise, creates empty seat configurations.
   */
  public FlightComponent()
  {
    File f = new File("flights.dat");
    if (f.exists())
    {
      ObjectInputStream in = null;
      try
      {
        try
        {
          in = new ObjectInputStream(new FileInputStream(f));
          flights = (ArrayList<AirlineFlight>) in.readObject();
        }
        catch (IOException e)
        {	
		  e.printStackTrace();
		  JOptionPane.showMessageDialog(null, "Input File Not Found");
		}
        catch (ClassNotFoundException e)
        {
          e.printStackTrace();
		}
      }
      finally
      {
        try
        {
          if(in != null)
        	in.close();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
    else
    {
      flights = new ArrayList<AirlineFlight>();
      AirlineFlight flight1 = new AirlineFlight("1", new Boeing737(LEFT_INDENT, UPPER_INDENT));
      flights.add(flight1);
      AirlineFlight flight2 = new AirlineFlight("2", new Boeing747(LEFT_INDENT, UPPER_INDENT));
      flights.add(flight2);
      AirlineFlight flight3 = new AirlineFlight("3", new Boeing777(LEFT_INDENT, UPPER_INDENT));
      flights.add(flight3);
    }
    chosenFlight = null;
    passengerName = null;
  }
  
  /**
   * Assigns a seat to a passenger chosen by user if the given coordinates are contained inside a seat rectangle.
   * @param x coordinate x.
   * @param y coordinate y.
   */
  public void assignSeat (double x, double y)
  {
    if(chosenFlight != null)
	  chosenFlight.assignSeat(passengerName, x, y);
  }
  
  /**
   * Chooses a flight according to its number.
   * @param nr flight number.
   * @throws IllegalArgumentException in case when a flight with a given number does not exist.
   */
  public void chooseFlight(String nr) throws IllegalArgumentException
  {
    boolean found = false;
    for(int i = 0; i < flights.size(); i++)
    {
      if (nr.equals(flights.get(i).getNumber()))
      {
        chosenFlight = flights.get(i);
        found = true;
      }
    }
    if (found == false)
    {
      chosenFlight = null;
      throw new IllegalArgumentException("Invalid flight number input");
    }
  }
  
  /**
   * Finds a passenger in a chosen airline flight according to his/her name.
   * @param passName passenger name.
   * @return found passenger or null if nothing was found.
   */
  public Passenger findPassenger(String passName)
  {
    if (chosenFlight != null)
	  return chosenFlight.findPassenger(passName);
    return null;
  }
  
  /**
   * Finds a passenger in a chosen airline flight according to a seat he/she occupies.
   * @param seatName seat name.
   * @return found passenger or null if nothing was found.
   */
  public Passenger findPassengerBySeatName(String seatName)
  {
    if (chosenFlight != null)
	    return chosenFlight.findPassengerBySeatName(seatName);
    return null;
  }
  
  /**
   * Returns boarding pass info: flight number, passenger name and seat name.
   * @param passengerName passenger name.
   * @return boarding pass info or null if no flight is chosen.
   * @throws NoSuchElementException if passenger was not found.
   */
  public String getBrdPassInfo(String passengerName) throws NoSuchElementException
  {
    if (chosenFlight == null)
    	return null;
    Passenger passenger = chosenFlight.findPassenger(passengerName);
    if(passenger == null)
    	throw new NoSuchElementException("No such passenger");
    String result = "Flight #" + chosenFlight.getNumber();
    result += ", passenger " + passenger.getPassName();
    result += ", seat " + passenger.getSeatName() + ".";
    return result;
  }
  
  /**
   * Draws the component.
   * @param g object to draw on.
   */
  public void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    if (chosenFlight != null)
    {
      chosenFlight.draw(g2);
    }
  }
  
  /**
   * Saves seat configurations of three flights in a file flights.dat.
   */
  public void saveToFile()
  {
    File f = new File("flights.dat");
    try
    {
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
      out.writeObject(flights);
      out.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  /**
   * Sets the passenger name currently entered.
   * @param aName passenger name.
   */
  public void setPassengerName(String aName)
  {
    passengerName = aName;
  }
  
}