import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.NoSuchElementException;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The class displays the "Book-A-Flight" application.
 * This application allows to reserve seats for airline flights. Three airline flights
 * with three different airplane models are available. Each one has a distinct seat configuration.
 * User may enter a passenger's name and reserve one empty seat per passenger, or unreserve a seat - with a
 * mouse click.
 * User may print up to four boarding passes per session.
 * If a user wants to know who occupies a seat, he enters the seat name and presses the "Display Passenger Name"
 * button. The passenger's name will be displayed. Similarly, when a passenger's name is entered, the seat he/she
 * occupies will be displayed automatically.
 * All seat configurations can be saved with a "Save" button.
 * @author Denis Bezverbnyy
 * #500474359
 * CPS 209 assignment 2.
 */

public class FlightViewer
{
  /**
   * Displays the "Book-A-Flight" application.
   * @param args not used
   */
  public static void main(String[] args)
  {
    final JFrame frame = new JFrame();
    //The following two constants determine size of a frame
    final int FRAME_WIDTH  = 650;
    final int FRAME_HEIGHT = 550;
    //Width of all three JTextField objects
    final int FIELD_WIDTH = 8;
    //The following to constants relate to the JTextArea
    final int AREA_ROWS = 5;
    final int AREA_COLUMNS = 50;
    //BoardingPassCounter counts boarding passes, allowing at most four to be printed
    final BoardingPassCounter passCounter = new BoardingPassCounter();
    //Setting the frame properties
    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    frame.setTitle("Book-A-Flight");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.getContentPane().setBackground(Color.WHITE);
    //FlightComponent:
    final FlightComponent flightComp = new FlightComponent();
    frame.add(flightComp, BorderLayout.CENTER);
    
//*********Designing a topPanel which contains labels, buttons and text fields**********
    JPanel topPanel = new JPanel(new GridLayout(3, 1));
    topPanel.setOpaque(false);
    frame.add(topPanel, BorderLayout.NORTH);
    //panel1 is for labeled text fields
    JPanel panel1 = new JPanel(new FlowLayout((FlowLayout.LEFT)));
    panel1.setOpaque(false);
    topPanel.add(panel1);
    JLabel flightLabel = new JLabel("Flight Number:");
    panel1.add(flightLabel);
    final JTextField flightField = new JTextField(FIELD_WIDTH);
    flightField.setText("");
    panel1.add(flightField);
    JLabel passLabel = new JLabel("  Passenger:");
    panel1.add(passLabel);
    final JTextField passField = new JTextField(FIELD_WIDTH);
    passField.setText("");
    panel1.add(passField);
    JLabel seatLabel = new JLabel("        Seat:");
    panel1.add(seatLabel);
    final JTextField seatField = new JTextField(FIELD_WIDTH);
    seatField.setText("");
    panel1.add(seatField);
    //panel2 is for buttons
    JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel2.setOpaque(false);
    topPanel.add(panel2);
    JButton swtchBttn = new JButton("Switch Flight");
    panel2.add(swtchBttn);
    JButton printBrdPassBttn = new JButton("Print Boarding Pass");
    panel2.add(printBrdPassBttn);
    //By entering seat name and pressing this button, we find out who the passenger is:
    JButton displayPassNameBttn = new JButton("Display Passenger Name (Enter Seat)");
    panel2.add(displayPassNameBttn);
    //panel3 is for the mssgLabel which displays information:
    JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel3.setOpaque(false);
    topPanel.add(panel3);
    final JLabel mssgLabel = new JLabel("");
    panel3.add(mssgLabel);
        
//*********Designing a bottomPanel which contains the JTextArea object, save and quit buttons**********  
    JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
    bottomPanel.setOpaque(false);
    frame.add(bottomPanel, BorderLayout.SOUTH);
    JPanel bottomPanelNested1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel bottomPanelNested2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    bottomPanelNested1.setOpaque(false);
    bottomPanelNested2.setOpaque(false);
    bottomPanel.add(bottomPanelNested1);
    bottomPanel.add(bottomPanelNested2);
    //This is a text area for printing boarding passes:
    final JTextArea boardingPassArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
    bottomPanelNested1.add(boardingPassArea);
    //Save and Quit buttons:
    JButton saveBttn = new JButton("Save");
    bottomPanelNested2.add(saveBttn);
    JButton quitBttn = new JButton("Quit");
    bottomPanelNested2.add(quitBttn);
    
//*********Next all the listener classes are created, and all the listeners are assigned********** 
    /**
     * Sets passenger name of the FlightComponent if text in the passField is being modified
     */
    class DocListener implements DocumentListener
    {
      public void changedUpdate(DocumentEvent e)
      {
        flightComp.setPassengerName(passField.getText());
      }
      public void removeUpdate(DocumentEvent e)
      {
        flightComp.setPassengerName(passField.getText());
      }
      public void insertUpdate(DocumentEvent e)
      {
        flightComp.setPassengerName(passField.getText());
      }
    }
    DocumentListener docListener = new DocListener();
    passField.getDocument().addDocumentListener(docListener);
    
    /**
     * Switches between flights
     */
    class SwitchFlightListener implements ActionListener
    {
      public void actionPerformed(ActionEvent event)
      {       
        String flightNumber = flightField.getText();
        try
        {
          flightComp.chooseFlight(flightNumber);
          mssgLabel.setText("");
          flightComp.repaint();
        }
        catch(IllegalArgumentException exception)
        {
          mssgLabel.setText("Invalid Flight Number: Choose 1, 2 or 3");
          flightComp.repaint();
        }
      }
    }
    ActionListener switchFlightListener = new SwitchFlightListener();
    swtchBttn.addActionListener(switchFlightListener);
    
    /**
     * Prints boarding passes
     */
    class PrintBrdPassListener implements ActionListener
    {
      public void actionPerformed(ActionEvent event)
      {	
        if (passCounter.getNrOfPasses() < 4)
        {
    	  String passInfo;
          try
          {
            passInfo = flightComp.getBrdPassInfo(passField.getText());
            if(passInfo != null)
            {
              boardingPassArea.append(passInfo + "\n");
              passCounter.increment();
            }
          }
          catch (NoSuchElementException exception)
          {
            mssgLabel.setText("Passenger Not Found");
          }
        }
        else
        	mssgLabel.setText("Only Four Boarding Passes May be Printed per Session");
      }
    }
    ActionListener printBrdPassListener = new PrintBrdPassListener();
    printBrdPassBttn.addActionListener(printBrdPassListener);
    
    /**
     * Displays passenger name, according to the entered seat
     */
    class DisplayPassNameListener implements ActionListener
    {
      public void actionPerformed(ActionEvent event)
      {
        String seatName = seatField.getText();
        Passenger passenger = flightComp.findPassengerBySeatName(seatName);
        if(passenger != null)
        {
          passField.setText(passenger.getPassName());
          mssgLabel.setText("");
        }
        else
        	mssgLabel.setText("Passenger Not Found");
      }
    }
    ActionListener displayPassNameListener = new DisplayPassNameListener();
    displayPassNameBttn.addActionListener(displayPassNameListener);
    
    /**
     * Saves the state of the flightComp
     */
    class SaveListener implements ActionListener
    {
      public void actionPerformed(ActionEvent event)
      {       
        flightComp.saveToFile();
      }
    } 
    ActionListener saveListener = new SaveListener();
    saveBttn.addActionListener(saveListener);
    
    /**
     * Saves the state of the flightComp and quits the program
     */
    class QuitListener implements ActionListener
    {
      public void actionPerformed(ActionEvent event)
      {
        flightComp.saveToFile();
        System.exit(0);
      }
    } 
    ActionListener quitListener = new QuitListener();
    quitBttn.addActionListener(quitListener);
    
    /**
     * Allows to reserve, unreserve and reassign a seat with a mouse click
     */
    class MousePressedListener implements MouseListener
	{
      public void mousePressed(MouseEvent event)
  	  {  
        try
        {
          flightComp.assignSeat(event.getX(), event.getY());
          flightComp.repaint();
          mssgLabel.setText("");
        }
        catch (IllegalArgumentException e)
        {
          mssgLabel.setText("Invalid Passenger Name");
        }
        catch (SeatOccupiedException e)
        {
          mssgLabel.setText("Seat Occupied");
        }
        updateSeatField();
      }
      public void mouseReleased(MouseEvent event){}
      public void mouseClicked(MouseEvent event){}
      public void mouseEntered(MouseEvent event){}
      public void mouseExited(MouseEvent event){}
      
      /**
       * Updates the seat field according to the entered passenger name
       */
      public void updateSeatField()
      {
        Passenger passenger = flightComp.findPassenger(passField.getText());
        if (passenger != null)
          seatField.setText(passenger.getSeatName());
        else
          seatField.setText("");
      }
    }
	MouseListener mpListener = new MousePressedListener();
	flightComp.addMouseListener(mpListener);
    
    frame.setVisible(true);
  }

}
