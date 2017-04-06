/**
 * The BoardingPassCounter class is used to count boarding passes.
 * @author Denis Bezverbnyy
 * #500474359
 * CPS 209 assignment 2.
 */

public class BoardingPassCounter
{
  private int nrOfPasses;
  
  /**
   * Creates a BoardingPassCounter initialized to zero.
   */
  public BoardingPassCounter()
  {
    nrOfPasses = 0;
  }
  
  /**
   * Increments the BoardingPassCounter.
   */
  public void increment()
  {
    nrOfPasses++;
  }
  
  /**
   * Returns number of boarding passes counted.
   * @return number of boarding passes counted.
   */
  public int getNrOfPasses()
  {
    return nrOfPasses;
  }
}
