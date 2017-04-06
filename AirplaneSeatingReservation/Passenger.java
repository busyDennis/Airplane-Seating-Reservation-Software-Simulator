import java.io.Serializable;

/**
 * An airplane passenger.
 * @author Denis Bezverbnyy
 * #500474359
 * CPS 209 assignment 2.
 */
public class Passenger implements Serializable
{
  private String passName;
  private String seatName;
  
  /**
   * Creates a new passenger.
   * @param pers passenger name.
   * @param seat seat name.
   */
  public Passenger(String pers, String seat)
  {
    passName = pers;
    seatName = seat;
  }
  
  /**
   * Gets passenger name.
   * @return passenger name.
   */
  public String getPassName()
  {
    return passName;
  }
  
  /**
   * Gets the name of the seat reserved for the passenger.
   * @return seat name.
   */
  public String getSeatName()
  {
    return seatName;
  }
  
  /**
   * Sets the passenger name.
   * @param name passenger name.
   */
  public void setPassName(String name)
  {
    passName = name;
  }
  
  /**
   * Sets the seat name.
   * @param name seat name.
   */
  public void setSeatName(String name)
  {
    seatName = name;
  }
}
