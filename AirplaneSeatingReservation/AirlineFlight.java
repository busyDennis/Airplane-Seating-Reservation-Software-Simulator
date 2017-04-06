import java.awt.Graphics2D;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;

/**
 * The class models an airline flight which refers to a specific plane model with distinct seat configuration, and keeps track of all passengers and seats assigned to them.
 * @author Denis Bezverbnyy
 * #500474359
 * CPS 209 assignment 2.
 * The project contains 11 classes.
 */
public class AirlineFlight implements Serializable
{
  private String number; //flight number
  private Boeing737 plane;//plane model
  private ArrayList<Passenger> passengers;
  
  /**
   * Creates a flight with specified number and plane model, all seats are empty.
   * @param nr flight number.
   * @param model plane model.
   */
  public AirlineFlight(String nr, Boeing737 model)
  {
    number = nr;
    plane = model;
    passengers = new ArrayList<Passenger>();
  }
  
  /**
   * Adds a new passenger to the flight.
   * @param passName passenger name.
   * @param seatName seat name.
   * @throws IllegalArgumentException if passenger name contains no letters or contains symbols other than letters and space.
   */
  public void addPassenger(String passName, String seatName) throws IllegalArgumentException
  {
    boolean acceptableName = true; //only letters and space
    boolean hasLetters = false;
    for(int i = 0; i < passName.length(); i++)
    {
      if (! (Character.isLetter(passName.charAt(i)) || passName.charAt(i) == ' '))
    	  acceptableName = false;
      if (Character.isLetter(passName.charAt(i)))
    	  hasLetters = true;
    }
    if(! hasLetters) //should contain letters
    	acceptableName = false;
    if (passName.equals("") || acceptableName == false)
    	throw new IllegalArgumentException ("Invalid passenger name");
    else
    {
      Passenger person = new Passenger(passName, seatName);
      passengers.add(person);
    }
  }
  
  /**
   * Assigns a seat for a passenger if x, y coordinates point to some seat.
   * @param passName passenger name.
   * @param x x coordinate.
   * @param y y coordinate.
   * @throws SeatOccupiedException if seat is already occupied.
   */
  public void assignSeat(String passName, double x, double y) throws SeatOccupiedException
  {
    Seat seat = plane.getSeat(x, y);
    if (seat != null && passName != null)
    {
      Passenger passenger = findPassenger(passName);
      if (passenger != null) //if the passenger is already there
      {
        if (seat.getOccupancy() == false)
        {
          plane.getSeat(passenger.getSeatName()).setOccupancy(false);
          seat.setOccupancy(true);
          passenger.setSeatName(seat.getName());
        }
        else if(passenger.getSeatName().equals(seat.getName()))
        {
          removePassenger(passName);
        }
        else
        	throw new SeatOccupiedException ("Seat Occupied");
      }
      else
      {
        if (seat.getOccupancy() == false)
        {
          addPassenger(passName, seat.getName());
          seat.setOccupancy(true);
        }
        else
        	throw new SeatOccupiedException ("Seat Occupied");
      }
    }
  }
  
  /**
   * Draws a seat configuration.
   * @param g2 object to draw on.
   */
  public void draw(Graphics2D g2)
  {
    plane.draw(g2);
  }
  
  /**
   * Finds a passenger by name.
   * @param passName passenger name.
   * @return found passenger or null if nothing was found.
   */
  public Passenger findPassenger(String passName)
  {
    if (passName != null)
    {
      for (int i = 0; i < passengers.size(); i++)
      {
        if (passengers.get(i).getPassName().equals(passName))
    	    return passengers.get(i);
      }
    }
    return null;
  }
  
  /**
   * Finds a passenger by seat name.
   * @param seatName seat name.
   * @return found passenger or null if nothing was found.
   */
  public Passenger findPassengerBySeatName(String seatName)
  {
    if (seatName != null)
    {
      for (int i = 0; i < passengers.size(); i++)
      {
        if (passengers.get(i).getSeatName().equals(seatName))
    	    return passengers.get(i);
      }
    }
    return null;
  }
  
  /**
   * Returns flight number.
   * @return flight number.
   */
  public String getNumber()
  {
    return number;
  }
  
  /**
   * Returns a seat if x, y - coordinates are contained inside the seat rectangle.
   * @param x x coordinate.
   * @param y y coordinate.
   * @return seat or null if no seat was found.
   */
  public Seat getSeat(double x, double y)
  {
    return plane.getSeat(x, y);
  }
 
  /**
   * Removes passenger from the flight.
   * @param passName passenger name.
   * @throws IllegalArgumentException if null passenger name was given.
   */
  public void removePassenger(String passName) throws IllegalArgumentException
  {
    if(passName == null) throw new IllegalArgumentException("Invalid passenger name");
    Passenger passenger = findPassenger(passName);
    if (passenger != null)
    {
      plane.getSeat(passenger.getSeatName()).setOccupancy(false);
      passengers.remove(passenger);
    }
  }
  
}
