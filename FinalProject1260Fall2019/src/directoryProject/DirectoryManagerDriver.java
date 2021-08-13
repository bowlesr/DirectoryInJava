/**
 * ---------------------------------------------------------------------------
 * File name: DirectoryManagerDriver.java
 * Project name: FinalProject1260Fall2019
 * ---------------------------------------------------------------------------
 * Creator's name and email: Rodney Bowles, bowlesr@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Nov 9, 2019
 * ---------------------------------------------------------------------------
 */

package directoryProject;

import javax.swing.SwingUtilities;

/**
 * runs the GUI application 
 *
 * <hr>
 * Date created: Nov 9, 2019
 * <hr>
 * @author Rodney Bowles
 */
public class DirectoryManagerDriver
{

	/**
	 * Main method to start and run the GUI application          
	 *
	 * <hr>
	 * Date created: Nov 9, 2019
	 *
	 * <hr>
	 * @param args
	 */
	public static void main (String [ ] args)
	{
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater 
		(
			new Runnable()			
			{	
				/**
				 * 
				 * runs and instantiates the DirectoryManagerGUI         
				 *
				 * <hr>
				 * Date created: Nov 9, 2019
				 *
				 * <hr>
				 * @see java.lang.Runnable#run()
				 */
				@Override
				public void run()
				{
					new DirectoryManagerGUI();
				}// end run
				
			}// end Runnable
			
		);// end SwingUtilities.invokeLater	

	}// end main

}// end DirectoryManagerDriver
