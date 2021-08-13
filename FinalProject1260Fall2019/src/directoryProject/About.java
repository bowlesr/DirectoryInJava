/**
 * ---------------------------------------------------------------------------
 * File name: About.java
 * Project name: FinalProject1260Fall2019
 * ---------------------------------------------------------------------------
 * Creator's name and email: Rodney Bowles, bowlesr@etsu.edu and Wyatt Stine, stinew@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Nov 30, 2019
 * ---------------------------------------------------------------------------
 */

package directoryProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * creates the about window
 *
 * <hr>
 * Date created: Nov 30, 2019
 * <hr>
 * @author Wyatt Stine
 */
public class About extends JDialog
{
	private static final long serialVersionUID = 1L;
	private JDialog about;
	private ImageIcon image;
	private JLabel icon;
	private JLabel title;
	private JLabel subtitle;
	private JLabel copyright;
	private JPanel aboutPanel;
	private JPanel aboutPanel2;	
	
	/**
	 * Constructor that creates the About window      
	 *
	 * <hr>
	 * Date created: Nov 10, 2019 
	 *
	 * 
	 * @param parent
	 */
	public About (JFrame parent)
	{
		final int DIALOG_WIDTH = 600;
		final int DIALOG_HEIGHT = 250;
		about = new JDialog (parent, "About The Directory Manager ... ", true);
		about.setDefaultCloseOperation (DISPOSE_ON_CLOSE);
		setAboutDialogIcon ( );
		about.setSize (DIALOG_WIDTH, DIALOG_HEIGHT);
		about.setLayout (new GridLayout (1, 2));
		
		// Setup left panel
		aboutPanel = new JPanel ( );
		aboutPanel.setLayout (new GridLayout (1, 1));
		aboutPanel.setBorder (BorderFactory.createEmptyBorder (10, 0, 10, 0));
		setLeftPanel ( );
		
		// Setup right panel
		aboutPanel2 = new JPanel ( );
		aboutPanel2.setLayout (new GridLayout (3, 1));
		aboutPanel2.setBorder (BorderFactory.createEmptyBorder (50, 5, 50, 5));
		setRightPanel ( );
		
		// Add panels to JDialog box
		about.add (aboutPanel);
		about.add (aboutPanel2);
		about.pack ( );
		about.setResizable (false);
		about.setLocationRelativeTo (parent);
		about.setVisible (true);
	}// end AboutBox
	
	/**
	 * sets up and creates the left panel          
	 *
	 * <hr>
	 * Date created: Nov 10, 2019
	 *
	 * <hr>
	 */
	private void setLeftPanel ( )
	{
		image = new ImageIcon ("Pictures\\icons\\aboutus.png");
		icon = new JLabel (image);
		aboutPanel.add (icon);
	}// end setLeftPanel
	
	/**
	 * Sets up and creates the right panel        
	 *
	 * <hr>
	 * Date created: Nov 10, 2019
	 *
	 * <hr>
	 */
	private void setRightPanel ( )
	{
		title = new JLabel ("Directory Manager");
		title.setFont (new Font ("SansSerif", Font.BOLD, 18));
		title.setForeground (Color.BLUE);
		subtitle = new JLabel ("Created for CSCI1260, Final Project");
		subtitle.setFont (new Font ("SansSerif", Font.ITALIC, 11));
		copyright = new JLabel ("Copyright: Rodney, John, Wyatt  Dec 1, 2019");
		copyright.setFont (new Font ("SansSerif", Font.ITALIC, 9));
		aboutPanel2.add (title);
		aboutPanel2.add (subtitle);
		aboutPanel2.add (copyright);
	}// end setRightPanel

	/**
	 * Sets the about windows icon         
	 *
	 * <hr>
	 * Date created: Nov 12, 2019
	 *
	 * <hr>
	 */
	private void setAboutDialogIcon ( )
	{
		try
		{
			File file = new File ("Pictures\\icons\\help.png"); //fixed to show icon 08/07/2019
			BufferedImage icon = ImageIO.read (file);
			about.setIconImage (icon);
		}// end try
		catch (IOException e)
		{
			// Ignores missing icon
		}// end catch 
	}// end setAboutDialogIcon

}