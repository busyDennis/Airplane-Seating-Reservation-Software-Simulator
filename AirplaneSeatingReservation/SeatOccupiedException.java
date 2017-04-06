/**
 * Thrown when the application tries to reserve an occupied seat.
 * @author Denis Bezverbnyy
 * #500474359
 * CPS 209 assignment 2.
 */
public class SeatOccupiedException extends IllegalStateException
{
  public SeatOccupiedException() {}
  
  public SeatOccupiedException(String message)
  {
    super(message);
  }
}
