/**
 * ---------------------------------------------------------------------------
 * File name: DirectoryManagerGUI.java
 * Project name: FinalProject1260Fall2019
 * ---------------------------------------------------------------------------
 * Creator's name and email: Rodney Bowles, bowlesr@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Nov 9, 2019
 * ---------------------------------------------------------------------------
 */

package directoryProject;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

/**
 * Creates the DirectoryManagerGUI thing
 *
 * <hr>
 * Date created: Nov 9, 2019
 * <hr>
 * @author Rodney Bowles
 */
public class DirectoryManagerGUI extends JFrame 
{	
	private static final long 			serialVersionUID = 1L;	
	
		// menus
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu databaseMenu;
	private JMenu helpMenu;
	
		//panels
	private JPanel panelMain;
	private JPanel panelTop;
	private JPanel panelTopLeft;
	private JPanel panelTopRight;
	private JPanel panelBottom;
	private JPanel panelBottomCenter;
	private JPanel panelBottomSouth;
	
		// setupFileMenu
	private JMenuItem createNewDirectory;
	private JMenuItem openNewDirectory;
	private JMenuItem saveDirectory;
	private JMenuItem exitItem;	
	
		// setupEditMenu
	private JMenuItem addPerson;
	private JMenuItem modifyPerson;
	private JMenuItem removePerson;
	private JMenuItem sortByLNameAZ;
	private JMenuItem sortByAddDate;
	
		//setupDataBaseMeun
	private JMenuItem connectDataBase;
	private JMenuItem disconnectDataBase;
	private JMenuItem addPersonToDatabase;
	private JMenuItem loadDatabase;
	
		// setupHelpMenu
	private JMenuItem aboutItem;
	
		//top left panel
	private JLabel personsNameLabel;
	private JPanel scrollPanePanel;
	private DefaultListModel <String> names;
	private JList     		 <String> personsNameList;
	private ListSelectionModel listSelectionModel;
	private JScrollPane scrollPane;
	//private DefaultListModel <String> dates;
	//private JList <String> personsDateList;
	//private ListSelectionModel listSelectionModel2;
	//private JScrollPane scrollPane2;	
	
		// top right panel
	private JLabel directoryNameLabel;
	private JTextField directoryNameTextFeild;
	private JLabel lName;
	private JTextField lNameTextField;
	private JLabel stateLabel;
	private JComboBox stateComboBox;
	private JLabel fName;
	private JTextField fNameTextField;
	private JLabel middle;
	private JTextField middleTextField;
	private JLabel dob;	// date of birth
	private MaskFormatter dobMask;
	private JFormattedTextField dobTextField;
	private JLabel gender;
	private JComboBox genderComboBox;
	private JLabel street;
	private JTextField streetTextField;
	private JLabel city;
	private JTextField cityTextField;
	private JLabel zipcode;
	private JTextField zipcodeTextField;
	private MaskFormatter zipMask;
	private JFormattedTextField zipMaskField;
	private JLabel phoneType;
	private JComboBox phoneTypeComboBox;
	private JLabel phone;
	private JTextField phoneTextField;
	private MaskFormatter phoneMask;
	private JFormattedTextField phoneNumMaskField;
	private JLabel emailType;
	private JComboBox emailTypeComboBox;
	private JLabel addDate;
	private MaskFormatter addDateMask;
	private JFormattedTextField addDateTextField;
	private JLabel email;
	private JTextField emailTextField;
	private JLabel regNum;
	private JTextField regNumTextField;
	private MaskFormatter regNumMask;
	private JFormattedTextField regNumMaskTextField;
	
		// bottom Center panel
	private JLabel iconLabel;
	
		// bottom South panel 
	private Button addBut;
	private Button modBut;
	private Button removeBut;
	private Button sortBut;
	private Button sortButDate;

	
	
	
					
	
		// creates a instance of the Directory
	private static Person p;
	private static Directory dir;		
	private static Database db = new Database();
	
	/**
	 * Constructor that creates the Directory Manager window 
	 *
	 * <hr>
	 * Date created: Nov 9, 2019 
	 *
	 * 
	 */
	public DirectoryManagerGUI()
	{
		super("Directory Manager");
		setWindowsLookAndFeel();
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setDirectoryIcon ( );
		final int WINDOW_WIDTH		 = 1000;
		final int WINDOW_HEIGHT		 = 950;
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(new GridLayout(1,1));
		
		// Instantiate components
		menuBar  		= new JMenuBar();
		fileMenu		= new JMenu("File");
		editMenu 		= new JMenu("Edit");
		databaseMenu 	= new JMenu ("Database");
		helpMenu 		= new JMenu("Help");
		
		// set component properties
		menuBar.setBorder (BorderFactory.createRaisedBevelBorder ( ));
		setupFileMenu();
		setupEditMenu();
		setupDatabaseMenu ();
		setupHelpMenu();
		
		// add menu bars
		menuBar.add (fileMenu);
		menuBar.add (editMenu);
		menuBar.add (databaseMenu);
		menuBar.add (helpMenu);
		
		// create main panel that all panel attach to 
		panelMain = new JPanel();
		panelMain.setLayout (new BorderLayout ());
		panelMain.setBorder (BorderFactory.createEmptyBorder ( 10,5,10,5));
		
		// create top primary panel
		panelTop = new JPanel();
		panelTop.setLayout (new GridLayout(1,2));
		
		// create top left panel and add components
		panelTopLeft = new JPanel();
		panelTopLeft.setLayout (new BorderLayout());
		setTopLeftPanel();
		
		// created top right panel and add components
		panelTopRight = new JPanel ();
		panelTopRight.setLayout (new GridLayout (16,2));
		setTopRightPanel();
		
		// create bottom panel and add components
		panelBottom = new JPanel ( );
		panelBottom.setLayout (new GridLayout (2,1));
		
		// create bottom left panel
		panelBottomCenter = new JPanel ( );	
		panelBottomCenter.setLayout (new GridLayout ( 1,1));
		setBottomCenterPanel();
		
		//create bottom right panel
		panelBottomSouth = new JPanel ();		
		panelBottomSouth.setLayout (new GridLayout (1,5));
		setBottomSouthPanel ( );
		
		// register all listeners
		registerListeners();
		
		// add top inner panels to primary top panel
		panelTop.add (panelTopLeft);
		panelTop.add (panelTopRight);
		panelMain.add (panelTop, BorderLayout.NORTH);
		//panelBottom.add (panelBottomCenter, BorderLayout.CENTER); // make pic viewer center and buttons on bottom (south)
		//panelBottom.add (panelBottomSouth, BorderLayout.SOUTH);
		panelMain.add (panelBottom, BorderLayout.CENTER);
		panelMain.add (panelBottomSouth,BorderLayout.SOUTH);
		
		//panelBottom.add (panelBottomCenter);
		
		
		// add panels to frame and display frame
		setJMenuBar(menuBar);
		add(panelMain);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true); // display window
		
	}
	
	/**
	 * sets up the file menu       
	 *
	 * <hr>
	 * Date created: Nov 9, 2019
	 *
	 * <hr>
	 */
	private void setupFileMenu ( )
	{
		
		// file menu items 
		createNewDirectory 	= new JMenuItem("Create New Directory");
		ImageIcon newIcon = new ImageIcon ("Pictures\\icons\\new.png");
		createNewDirectory.setIcon (newIcon);
		openNewDirectory 	= new JMenuItem("Open Directory");
		ImageIcon open = new ImageIcon ("Pictures\\icons\\open.png");
		openNewDirectory.setIcon (open);
		saveDirectory		= new JMenuItem("Save and Close Directory");
		ImageIcon save = new ImageIcon ("Pictures\\icons\\save3.png");
		saveDirectory.setIcon (save);
		exitItem			= new JMenuItem("Exit Directory");
		exitItem.setForeground (Color.RED);// sets the text color to red
		ImageIcon exit = new ImageIcon ("Pictures\\icons\\exit2.png");
		exitItem.setIcon (exit);
		//adds them to the file menu
		fileMenu.add (createNewDirectory);
		fileMenu.add (openNewDirectory);
		fileMenu.add (saveDirectory);
		fileMenu.add (new JSeparator ()); // crates a line break with a line
		fileMenu.add (exitItem);
		
	}
	
	/**
	 * sets up the edit menu        
	 *
	 * <hr>
	 * Date created: Nov 9, 2019
	 *
	 * <hr>
	 */
	private void setupEditMenu ( )
	{
			// add person 
		addPerson    		= new JMenuItem("Add Person");
		ImageIcon add 		= new ImageIcon( "Pictures\\icons\\add2.png");
		addPerson.setIcon (add);
			// modify person 
		modifyPerson 		= new JMenuItem("Edit Person");
		ImageIcon mod 		= new ImageIcon("Pictures\\icons\\edit.png" );
		modifyPerson.setIcon (mod);
			// remove person
		removePerson 		= new JMenuItem("Romove Person");
		ImageIcon remove 	= new ImageIcon("Pictures\\icons\\remove.png");
		removePerson.setIcon (remove);
			// sort by the last name
		sortByLNameAZ		= new JMenuItem("Sort By Last Name (A-Z)");
		ImageIcon sortAZ 	= new ImageIcon("Pictures\\icons\\sortAZ.png");
		sortByLNameAZ.setIcon (sortAZ);
			// sort by the add date  
		sortByAddDate		= new JMenuItem("Sort by Add Date");
		ImageIcon sort 		= new ImageIcon("Pictures\\icons\\sort.png");
		sortByAddDate.setIcon (sort);		
		
		editMenu.add (addPerson);
		editMenu.add (modifyPerson);
		editMenu.add (removePerson);
		editMenu.add (sortByLNameAZ);
		editMenu.add (sortByAddDate);
		
	}
	
	/**
	 * sets up the database menu         
	 *
	 * <hr>
	 * Date created: Nov 29, 2019
	 *
	 * <hr>
	 */
	private void setupDatabaseMenu()
	{
		connectDataBase = new JMenuItem ("Connect");
		ImageIcon connect = new ImageIcon ("Pictures\\icons\\connect.png");
		connectDataBase.setIcon (connect);
		disconnectDataBase = new JMenuItem ("Disconnect");
		ImageIcon disconnect = new ImageIcon ("Pictures\\icons\\disconnect.png");
		disconnectDataBase.setIcon (disconnect);
		addPersonToDatabase = new JMenuItem ("Add Person to Database");
		ImageIcon add = new ImageIcon ("Pictures\\icons\\add2.png");
		addPersonToDatabase.setIcon (add);		
		loadDatabase = new JMenuItem ("Load Database");
		ImageIcon load = new ImageIcon ("Pictures\\icons\\load.png");
		loadDatabase.setIcon (load);
		
				
		databaseMenu.add (connectDataBase);
		databaseMenu.add (loadDatabase);
		databaseMenu.add (addPersonToDatabase);
		databaseMenu.add (disconnectDataBase);
		
	}
	
	/**
	 * sets up the help menu         
	 *
	 * <hr>
	 * Date created: Nov 9, 2019
	 *
	 * <hr>
	 */
	private void setupHelpMenu ( )
	{
		aboutItem = new JMenuItem ("About");
		ImageIcon help = new ImageIcon ("Pictures\\icons\\help2.png");
		aboutItem.setIcon (help);
		
		helpMenu.add (aboutItem);
	}
	
	/**
	 * sets up the top left panel         
	 *
	 * <hr>
	 * Date created: Nov 9, 2019
	 *
	 * <hr>
	 */
	private void setTopLeftPanel ( )
	{

		personsNameLabel = new JLabel("Names In Directory", SwingConstants.CENTER);
		personsNameLabel.setFont (new Font("SansSerif", Font.BOLD, 12));
		
		scrollPanePanel = new JPanel();
			// create the list and add the names to it
		names = new DefaultListModel<String>();
		personsNameList = new JList<String>(names);
		personsNameList.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel = personsNameList.getSelectionModel ( );
		personsNameList.setLayoutOrientation (JList.VERTICAL);
		personsNameList.setVisibleRowCount (20);
		personsNameList.setSelectedIndex (0);
		personsNameList.setFixedCellWidth (200);
		
				// was trying to find a way to display the add date for the sort function 
		//dates = new DefaultListModel <String> ( );
		//personsDateList = new JList <String> ( );
		//personsDateList.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
		//listSelectionModel2 = personsDateList.getSelectionModel ( );
		//personsDateList.setLayoutOrientation (JList.VERTICAL);
		//personsDateList.setVisibleRowCount (20);
		//personsDateList.setSelectedIndex (0);
		//personsDateList.setFixedCellWidth (200);
		
		scrollPane = new JScrollPane (personsNameList);
		//scrollPane2 = new JScrollPane ( personsDateList);
		scrollPanePanel.add (scrollPane);
		//scrollPanePanel.add (scrollPane2);
		panelTopLeft.add (personsNameLabel, BorderLayout.NORTH);
		panelTopLeft.add (scrollPanePanel, BorderLayout.CENTER); //west
		//panelTopLeft.add (scrollPanePanel, BorderLayout.EAST);
		
	}
	
	/**
	 * sets up the top right panel        
	 *
	 * <hr>
	 * Date created: Nov 9, 2019
	 *
	 * <hr>
	 */
	private void setTopRightPanel ( )
	{
			//directory name
		directoryNameLabel 			= new JLabel("Directory Name");
		directoryNameLabel.setText ("Directory Name: ");
		directoryNameTextFeild 	= new JTextField(30);
		directoryNameTextFeild.setEditable (false);
		
			//register number
		regNum = new JLabel ( );
		regNum.setText ("Person ID Number: ");
		try
		{
			regNumMask = new MaskFormatter ("####");
			regNumMask.setPlaceholderCharacter ('#');			
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog (null, e1.getMessage ( ), "Person ID does not contain valid mask characters", JOptionPane.ERROR_MESSAGE);
		}
		regNumMaskTextField = new JFormattedTextField (regNumMask);
		regNumMaskTextField.setHorizontalAlignment (SwingConstants.CENTER);
		regNumTextField = new JTextField (10);
		regNumTextField.setHorizontalAlignment (SwingConstants.CENTER);
		//regNumTextField.setEditable (false);		
		
			// last name
		lName = new JLabel();
		lName.setText ("Last Name:");
		lNameTextField = new JTextField (10);
		
			// first name
		fName = new JLabel();
		fName.setText ("First Name:");
		fNameTextField = new JTextField (10);
		
			// middle Initial
		middle = new JLabel();
		middle.setText ("Middle Initial:");
		middleTextField = new JTextField (1);
		
			// DOB
		dob = new JLabel();
		dob.setText ("Date of Birth (mm/dd/yyyy):");
		try 
		{
			dobMask = new MaskFormatter ( "##/##/####");
			dobMask.setPlaceholderCharacter ('#'); 
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog (null, e.getMessage ( ), "Date of Birth does not contain valid mask characters", JOptionPane.ERROR_MESSAGE);
		}
		dobTextField = new JFormattedTextField (dobMask); 
		dobTextField.setHorizontalAlignment (SwingConstants.CENTER);
		
			//Gender
		gender = new JLabel ();
		gender.setText ("Gender:");
		genderComboBox = new JComboBox <> (GenderType.values ( ));
		
			//Street
		street = new JLabel();
		street.setText ("Street:");
		streetTextField = new JTextField (30);	
		
			//City
		city = new JLabel();
		city.setText ("City:");
		cityTextField = new JTextField (30);
		
			// state
		stateLabel = new JLabel ();
		stateLabel.setText ("State:");
		stateComboBox = new JComboBox <> (StateAbbrevationList.values ( ) );  
		
			//Zipcode
		zipcode = new JLabel ( );
		zipcode.setText ("Zipcode:");
		zipcodeTextField = new JTextField (5);
		try
		{
				zipMask = new MaskFormatter("#####");
				zipMask.setPlaceholderCharacter ('#');				
		}
		catch (Exception e1) 
		{
			JOptionPane.showMessageDialog (null,  "Zipcode does not contain valid mask characters", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		zipMaskField = new JFormattedTextField (zipMask);
		zipMaskField.setHorizontalAlignment (SwingConstants.CENTER);
		
			//phone number Type and number
		phoneType = new JLabel ( );
		phoneType.setText ("Phone Type: ");
		phoneTypeComboBox = new JComboBox <> (PhoneType.values ( ));
		
		phone = new JLabel ( );
		phone.setText ("Phone Number: ");
		phoneTextField = new JTextField (10);
		try
		{
			phoneMask = new MaskFormatter ( "##########");
			phoneMask.setPlaceholderCharacter ('#');
		}
		catch (Exception e1)
		{
			JOptionPane.showMessageDialog (null,  "Phone Number does not contain valid mask characters", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		phoneNumMaskField = new JFormattedTextField (phoneMask);
		phoneNumMaskField.setHorizontalAlignment (SwingConstants.CENTER);
		
			//email type and email
		emailType = new JLabel ( );
		emailType.setText ("Email Type: ");
		emailTypeComboBox = new JComboBox <> (EmailType.values ( ) );
		
		email = new JLabel ( );
		email.setText ("Email: ");
		emailTextField = new JTextField (30);
		
			//photo not needed here 
		
			// Add Date
		addDate = new JLabel ( );
		addDate.setText ("Dated Added to Directory (mm/dd/yyyy): ");
		try 
		{
			addDateMask = new MaskFormatter ( "##/##/####");
			addDateMask.setPlaceholderCharacter ('#'); 
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog (null,  "Date added does not contain valid mask characters", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		addDateTextField = new JFormattedTextField (dobMask); 
		addDateTextField.setHorizontalAlignment (SwingConstants.CENTER);				
		
			// adding labels and text fields
		panelTopRight.add (directoryNameLabel);
		panelTopRight.add (directoryNameTextFeild);
		
		panelTopRight.add (regNum);
		panelTopRight.add (regNumMaskTextField);
		
		panelTopRight.add (lName);
		panelTopRight.add (lNameTextField);
		
		panelTopRight.add (fName);
		panelTopRight.add (fNameTextField);
		
		panelTopRight.add (middle);
		panelTopRight.add (middleTextField);
		
		panelTopRight.add (dob);
		panelTopRight.add (dobTextField);
		
		panelTopRight.add (gender);
		panelTopRight.add (genderComboBox);
		
		panelTopRight.add (street);
		panelTopRight.add (streetTextField);
		
		panelTopRight.add (city);
		panelTopRight.add (cityTextField);

		panelTopRight.add (stateLabel);
		panelTopRight.add (stateComboBox);		
				
		panelTopRight.add (zipcode);
		panelTopRight.add (zipMaskField);
		
		panelTopRight.add (phoneType);
		panelTopRight.add (phoneTypeComboBox);
		
		panelTopRight.add (phone);
		panelTopRight.add (phoneNumMaskField);
		
		panelTopRight.add (emailType);
		panelTopRight.add (emailTypeComboBox);
		
		panelTopRight.add (email);
		panelTopRight.add (emailTextField);
		
		panelTopRight.add (addDate);
		panelTopRight.add (addDateTextField);		
	}
	
	/**
	 * sets up the bottom center panel with the picture icon          
	 *
	 * <hr>
	 * Date created: Nov 9, 2019
	 *
	 * <hr>
	 */
	private void setBottomCenterPanel ( )   
	{
		
		iconLabel = new JLabel("Please select a person from the Directory.", SwingConstants.CENTER);
		iconLabel.setIcon (null);
		panelBottom.add (iconLabel);
	}
	
	/**
	 * sets up the bottom south panel which house the buttons         
	 *
	 * <hr>
	 * Date created: Nov 29, 2019
	 *
	 * <hr>
	 */
	private void setBottomSouthPanel()
	{		
		addBut 		= new Button("Add Person");
		modBut 		= new Button("Edit Person");
		removeBut 	= new Button("Remove Person");
		sortBut		= new Button("Sort By Last Name");
		sortButDate = new Button("Sort By Add Date");
		
		
		panelBottomSouth.add (addBut);
		panelBottomSouth.add (modBut);
		panelBottomSouth.add (removeBut);
		panelBottomSouth.add (sortBut);
		panelBottomSouth.add (sortButDate);					
	}// end setBottomSouthPanel
	
	/**
	 * resize method that will automatically resize selected photo and will save over the 
	 * existing file          
	 *
	 * <hr>
	 * Date created: Aug 1, 2019
	 *
	 * <hr>
	 * @param inputImagePath
	 * @param outputImagePath
	 * @param widthScaled
	 * @param heightScaled
	 * @throws IOException
	 */
	public static void resize(String inputImagePath, String outputImagePath, int widthScaled, int heightScaled) throws IOException
	{
		// reads in image
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read (inputFile);
		
		if(!(inputImage.getWidth ( ) == 200 && inputImage.getHeight ( ) == 200 ))
		{
			// creates image output
			BufferedImage outputImage = new BufferedImage(widthScaled, heightScaled, inputImage.getType ( ));
			
			// scales image in  to the out image
			Graphics2D g2d = outputImage.createGraphics ( );
			g2d.drawImage (inputImage, 0, 0, widthScaled, heightScaled, null);
			g2d.dispose ( );
			
			// extension of file
			String format = outputImagePath.substring(outputImagePath.lastIndexOf (".") + 1);
			
			// writes to output file
			ImageIO.write (outputImage, format, new File(outputImagePath));
		}// end if
		else
		{
			String format = outputImagePath.substring(outputImagePath.lastIndexOf (".") + 1);
			ImageIO.write (inputImage, format, new File(outputImagePath));
		}// end else
	}// end resize
		
	/**
	 * saves the file to a text file also calls the saveFile method in the Directory class      
	 *
	 * <hr>
	 * Date created: Nov 29, 2019
	 *
	 * <hr>
	 */
	public static void saveFile()
	{		
			 	JFileChooser chooser = null;
		        File fileInput = null;
		        String file = " "; //this will hold the name and path of the file
		        chooser = new JFileChooser("data");
		        //set file extensions to txt only
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
		        chooser.setFileFilter(filter);
		        //Prompt user to select a file
		        chooser.setDialogTitle("Please Select A File..");
		        chooser.setApproveButtonToolTipText("Select the file you wish to save. Then click me");
		        int button = chooser.showSaveDialog(null);		
		  if (button != JFileChooser.APPROVE_OPTION)
		  {
			  	JOptionPane.showMessageDialog (null, "File not saved", "Warning", JOptionPane.WARNING_MESSAGE);
		  }// end if 
		  else 
		  {		 
		        //set the selected file path to String file
		        file = chooser.getSelectedFile().getPath();
		        //Create a file object from file path
		        fileInput = chooser.getSelectedFile();
		        if(fileInput.exists()) 
		        {
		            try 
		            {		            	
		                dir.saveFile(file);
		                JOptionPane.showMessageDialog (null, "File was saved! ", "Saved", JOptionPane.PLAIN_MESSAGE);
		            }// end try
		            catch(Exception ex) 
		            {
		                JOptionPane.showMessageDialog (null, "Error file does not exist. Cannot Save.", 
		                	"Error", JOptionPane.ERROR_MESSAGE);
		            }// end catch
	
		        }// end if
		  }// end if
	}// end saveFile
		
	/**
	 * registers the listeners for the various buttons a menu items because it could not get lambda expressions 
	 * to work         
	 *
	 * <hr>
	 * Date created: Nov 9, 2019
	 *
	 * <hr>
	 */
	private void registerListeners ( )
	{		
		openNewDirectory.addActionListener 			(new OpenDirectoryListener ( ));
		createNewDirectory.addActionListener 		(new CreateDirectoryListener ( ));		
		addBut.addActionListener 					(new AddPersonListener ( ));
		addPerson.addActionListener 				(new AddPersonListener ( ));
		removePerson.addActionListener 				(new DropPersonListener ( ));
		removeBut.addActionListener 				(new DropPersonListener ( ));
		modifyPerson.addActionListener 				(new EditPersonListener ( ));
		modBut.addActionListener 					(new EditPersonListener ( ));
		listSelectionModel.addListSelectionListener (new ListListener());
		saveDirectory.addActionListener 			(new SaveListener ( ));
		exitItem.addActionListener 					(new ExitListener ( ));
		connectDataBase.addActionListener 			(new ConnectListener ( ));
		disconnectDataBase.addActionListener 		(new DisonnectListener ( ));
		aboutItem.addActionListener 				(new AboutListener ( ));
		sortByLNameAZ.addActionListener 			(new NameSortListener ( ));
		sortBut.addActionListener 					(new NameSortListener ( ));
		sortByAddDate.addActionListener 			(new DateSortListener ( ));
		sortButDate.addActionListener 				(new DateSortListener ( ));
		connectDataBase.addActionListener 			(new ConnectListener ( ));
		disconnectDataBase.addActionListener 		(new DisonnectListener ( ));
		addPersonToDatabase.addActionListener 		(new AddPersonDatabase ( ));
		loadDatabase.addActionListener 				(new LoadDatabase ( ));
	}
		 
	/**
	 * sets directory icon          
	 *
	 * <hr>
	 * Date created: Nov 30, 2019
	 *
	 * <hr>
	 */
	private void setDirectoryIcon ( ) 
	{
		try
		{
			File file = new File ("Pictures\\icons\\directory2.png");
			BufferedImage icon = ImageIO.read (file);
			setIconImage (icon);
		}// end try 
		catch (IOException e)
		{
			// Ignores missing icon if one is not put in 
		}// end catch
		
	}
	
	/**
	 * sets how the window looks and feels          
	 *
	 * <hr>
	 * Date created: Nov 29, 2019
	 *
	 * <hr>
	 */
	private void setWindowsLookAndFeel ( ) 
	{
		try
		{
			UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI (this);
		}// end try 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog (null, "Error setting the look and feel.");
			System.exit (0);
		}// end catch 
		
	}// end setWindowsLookAndFeel
	
	/**
	 * Listener that creates a new directory when called  
	 *
	 * <hr>
	 * Date created: Nov 13, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class CreateDirectoryListener implements ActionListener
	{

		/**
		 * action performed that creates the new directory          
		 *
		 * <hr>
		 * Date created: Nov 13, 2019 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			if (dir != null && dir.isSaveNeeded ( ) == true )
			{
				int option = JOptionPane.showConfirmDialog (null, "Would you like to save your changes to the current Directory? ", "Save", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) 
				{
					saveFile(); // need method
				}// end if/
			}// end if
			
			dir = new Directory ( );
			names.clear ( );
			
			String directoryName = JOptionPane.showInputDialog (null, "What is the Directory's name?");
			
			try
			{
				dir.setDirectoryName (directoryName);
				directoryNameTextFeild.setText (dir.getDirectoryName ( ));
			}// end try 
			catch (Exception e1)
			{
				JOptionPane.showMessageDialog (null, "Directory could not be created", "ERROR", JOptionPane.ERROR_MESSAGE);
			}// end catch 			
		}// end actionPerformed
	}// end CreateDirectoryListener
		
	/**
	 * listener that opens a existing directory when called
	 *
	 * <hr>
	 * Date created: Nov 13, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class OpenDirectoryListener implements ActionListener
	{

		/**
		 * action performed that opens the directory        
		 *
		 * <hr>
		 * Date created: Nov 13, 2019 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			if (dir != null && dir.isSaveNeeded ( ) == true )// askes if you want to save your previous club before opening new one 
			{
				int option = JOptionPane.showConfirmDialog (null, "Would you like to save your changes to the current club? ", "Save", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) 
				{
					saveFile(); // need method
				}// end if
				
			}// end if
			
			dir = new Directory ( );
			names.clear ( );
			
			JFileChooser fileChooser = new JFileChooser ("Data");
			FileNameExtensionFilter filter = new FileNameExtensionFilter ("Text Files", "txt");
			fileChooser.setFileFilter (filter);
			fileChooser.setDialogTitle ("Open Club Data File");
			
			int button = fileChooser.showOpenDialog (null);
			if (button == JFileChooser.APPROVE_OPTION)
			{
				try
				{
					File selectedFile = fileChooser.getSelectedFile ( );
					dir.fillFromFile (selectedFile);
					
					for (Person i : dir.getPerson ( ))
					{						
						names.addElement (i.getLastName ( ) + ", " + i.getFirstName ( ));
					}// end for
									
					directoryNameTextFeild.setText (dir.getDirectoryName ( ));
					
										
				}// end try				
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog (null, "File Not Found", "Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}// end catch
				
			}// end if
			
			else if (button == JFileChooser.CANCEL_OPTION || button == JFileChooser.ERROR_OPTION)
			{
				JOptionPane.showMessageDialog (null, "No file selected", "File Not Opened", JOptionPane.INFORMATION_MESSAGE);
				return;
			}// end else if		
		}//end actionPerformded		
	}//end OpenDirectoryListener
	
	/**
	 * Listener that creates/adds a person 
	 *
	 * <hr>
	 * Date created: Nov 13, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class AddPersonListener implements ActionListener
	{

		/**
		 * action performed that makes the person          
		 *
		 * <hr>
		 * Date created: Nov 13, 2019 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			Person tempPer = new Person();
			//int rNum = Integer.parseInt (regNumTextField.getText ( ));
			
			if(dir != null)
			{
				try // put all of these into their own try/catch possibly 
				{
					try 
					{
						tempPer.setRegNum 			(regNumMaskTextField.getText ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Person ID is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setLastName 		(lNameTextField.getText ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Last Name is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setFirstName 		(fNameTextField.getText ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "First Name is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setMiddleInital 	(middleTextField.getText ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Middle Inital is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setDateOfBirth 		(dobTextField.getText ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Date of Birth is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setGenderType 		(genderComboBox.getSelectedItem ( ).toString ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,   "Gender is invalid.",  "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setStreet 			(streetTextField.getText ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,   "Street is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setCity 			(cityTextField.getText ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,   "City is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setState 			(stateComboBox.getSelectedItem ( ).toString ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,   "State is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setZipcode 			(zipMaskField.getText ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Zipcode is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setPhoneNumType 	(phoneTypeComboBox.getSelectedItem ( ).toString ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Phone Number Type is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setPhoneNum 		(phoneNumMaskField.getText ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Phone Number is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setEmailType 		(emailTypeComboBox.getSelectedItem ( ).toString ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Email Type is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setEmail 			(emailTextField.getText ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,   "Email is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try 
					{
						tempPer.setDateAdded 		(addDateTextField.getText ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,   "Dated Added is invalid.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
											
				}// end try 
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog (null, e1.getMessage ( ), "Member could not be created",
						JOptionPane.ERROR_MESSAGE);
				}// end catch 
				
				JFileChooser fc = new JFileChooser ("Pictures\\persons\\");
				FileNameExtensionFilter filter = new FileNameExtensionFilter ("Image Files", ImageIO.getReaderFileSuffixes ( ));
				fc.setFileFilter (filter);
				fc.setDialogTitle ("Select A Member Photo");
				fc.setApproveButtonToolTipText ("Select the file you want to open, then click me.");
				
				boolean isPhotoValid = false;
				while(!isPhotoValid)
				{
					JOptionPane.showMessageDialog (null, "Please Select a Photo");
					int result = fc.showOpenDialog (null);
					try
					{
						if (result == JFileChooser.APPROVE_OPTION)
						{
							String photo = fc.getSelectedFile ( ).getName ( );
							String path  = fc.getSelectedFile ( ).getPath ( );
							tempPer.setPhoto (photo);
							try
							{
								resize(path, "Pictures" + tempPer.getPhoto ( ), 200, 200);
								isPhotoValid = true;
							}// end try
							catch (IOException e1)
							{
								JOptionPane.showMessageDialog (null, "Picture cannot be resized.");
								e1.printStackTrace();
							}// end catch
							isPhotoValid = true;
						} // end if
						
						else
						{
							
							int choice = JOptionPane.showConfirmDialog (null, "Would you like to continue without a member photo? ",
																		"Picture Option", JOptionPane.YES_NO_OPTION );
							if (choice == JOptionPane.YES_OPTION)
							{
								String photo = fc.getSelectedFile ( ).getPath ( );
								tempPer.setPhoto (photo);
								isPhotoValid = true;
							} // end if
							else if (choice == JOptionPane.NO_OPTION)// && choice == JOptionPane.CANCEL_OPTION)
							{
								//JOptionPane.showMessageDialog (null, "Please enter \"y/n\"", "Invalid Input",JOptionPane.ERROR_MESSAGE);
								tempPer.setPhoto ("noPic.png");
								isPhotoValid = true;
							} // end else if
						}// end else
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog (null, "No photo selected.");
						tempPer.setPhoto ("noPic.png");
						isPhotoValid = true;
					}
				}// end while
				
				try
				{
					dir.addPerson (tempPer);
					//db.addPerson (tempPer);
					JOptionPane.showMessageDialog (null, "Person successfully created");
					names.addElement (tempPer.getLastName ( ) + ", " + tempPer.getFirstName ( ));
					//dates.addElement (tempPer.getDateAdded ( ));
					dir.setSaveNeeded (true);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog (null, e1.getMessage ( ), "Person could not be created",
						JOptionPane.ERROR_MESSAGE);
				}
				
			}// end if
			else
			{
				JOptionPane.showMessageDialog (null, "Please create a Directory", "Could not create Person",
					JOptionPane.ERROR_MESSAGE);
			}// // end else
		}//end actionPerformed		
	}//end AddPersonListener
		
		/**
	 * listener that drops person when called
	 *
	 * <hr>
	 * Date created: Nov 13, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class DropPersonListener implements ActionListener
	{

		/**
		 * action performed that drops person from the directory         
		 *
		 * <hr>
		 * Date created: Nov 13, 2019 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			if (dir != null && !dir.getPerson ( ).isEmpty ( ))
			{
				try
				{
					dir.removeMember (personsNameList.getSelectedIndex ( ));
					
					names.removeElement (personsNameList.getSelectedValue ( ));
					JOptionPane.showMessageDialog (null, "Member droped!! ");
					
					dir.setSaveNeeded (true);
				}// end try
				catch (Exception e1) 
				{
					JOptionPane.showMessageDialog (null, "No member selected. Please selected a memeber to drop.", "Error", JOptionPane.WARNING_MESSAGE);
				}// end catch
			}// end if
			else
			{
				JOptionPane.showMessageDialog (null, "Please create a Directory before trying to remove a club member.", "No Directory Exists Yet!!! ", JOptionPane.ERROR_MESSAGE);
			}// end else
			
			//used for debugging 
		//System.out.println (club.toString ( ));
			
		}
		
		
	}
		
	/**
	 * listener that edits person information when called 
	 *
	 * <hr>
	 * Date created: Nov 13, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class EditPersonListener implements ActionListener
	{

		/**
		 * action performed that will change the information of a person object      
		 *
		 * <hr>
		 * Date created: Nov 13, 2019 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			if (dir != null && !dir.getPerson ( ).isEmpty ( ))
			{
				try 
				{
					Person selectedPerson = dir.findByName (personsNameList.getSelectedValue ( ));
					
					//selectedPerson.setRegNum 		(regNumMaskTextField.getText ( ));
					selectedPerson.setFirstName 	(fNameTextField.getText ( ));
					selectedPerson.setLastName 		(lNameTextField.getText ( ));
					selectedPerson.setMiddleInital 	(middleTextField.getText ( ));
					selectedPerson.setDateOfBirth 	(dobTextField.getText ( ));
					selectedPerson.setGenderType 	(genderComboBox.getSelectedItem ( ).toString ( ));
					selectedPerson.setStreet 		(streetTextField.getText ( ));
					selectedPerson.setCity 			(cityTextField.getText ( ));
					selectedPerson.setState 		(stateComboBox.getSelectedItem ( ).toString ( ));
					selectedPerson.setZipcode 		(zipcodeTextField.getText ( ));
					selectedPerson.setPhoneNumType 	(phoneTypeComboBox.getSelectedItem ( ).toString ( ));
					selectedPerson.setPhoneNum 		(phoneTextField.getText ( ));
					selectedPerson.setEmailType 	(emailTypeComboBox.getSelectedItem ( ).toString ( ));
					selectedPerson.setEmail 		(emailTextField.getText ( ));					
					
					
					
					dir.editMember (personsNameList.getSelectedIndex ( ), selectedPerson);
					JOptionPane.showMessageDialog (null, "Member edited sucessfully");
					names.set (personsNameList.getSelectedIndex ( ), selectedPerson.getLastName ( )+", " +  selectedPerson.getFirstName ( ));
					dir.setSaveNeeded (true);
				}// end try
				catch (Exception e1) 
				{
					JOptionPane.showMessageDialog (null,  "Could not edit member!! ", "ERROR",       JOptionPane.ERROR_MESSAGE);
				}// end catch				
			}// end if 
			else
			{
				JOptionPane.showMessageDialog (null, "Please create a Directory before you can edit a member", "No Directory Created", JOptionPane.ERROR_MESSAGE);
			}// end else
			
		}// end actionPerformed		
	}// end EditPersonListener
	
	/**
	 * listener that controls what the names list does when clicked 
	 *
	 * <hr>
	 * Date created: Nov 13, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class ListListener implements ListSelectionListener
	{
                   
		/**
		 * value changed that Controls person information of person clicked         
		 *
		 * <hr>
		 * Date created: Nov 13, 2019 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void valueChanged (ListSelectionEvent e)
		{
			if (personsNameList.getSelectedValue ( ) != null)
			{
				try
				{
					
					Person tempPerson = dir.findByName 	(personsNameList.getSelectedValue ( ));	
					try
					{
						regNumMaskTextField.setText 			(tempPerson.getRegNum ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get Person ID.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						fNameTextField.setText 				(tempPerson.getFirstName ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null, "Unable to get First Name.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						lNameTextField.setText 				(tempPerson.getLastName ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get Last Name.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						middleTextField.setText 			(tempPerson.getMiddleInital ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get Middle Inital.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						dobTextField.setText 				(tempPerson.getDateOfBirth ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get Date of Birth.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						genderComboBox.setSelectedItem 		(tempPerson.getGenderType ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get Gender.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						streetTextField.setText 			(tempPerson.getStreet ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get Street.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						cityTextField.setText 				(tempPerson.getCity ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get City.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						stateComboBox.setSelectedItem 		(tempPerson.getState ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get State.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						zipMaskField.setText 			(tempPerson.getZipcode ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get Zipcode.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						phoneTypeComboBox.setSelectedItem 	(tempPerson.getPhoneNumType ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get Phone Type.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						phoneNumMaskField.setText 				(tempPerson.getPhoneNum ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get Phone Number.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						emailTypeComboBox.setSelectedItem 	(tempPerson.getEmailType ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get Email Type.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						emailTextField.setText 				(tempPerson.getEmail ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get Email.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						addDateTextField.setText 			(tempPerson.getDateAdded ( ));
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog (null,  "Unable to get Date Added.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					}					
							
					try
					{
						iconLabel.setText ("");
						String personPhotoPath = "Pictures\\persons\\" + tempPerson.getPhoto ( );
						BufferedImage personImage = ImageIO.read (new File(personPhotoPath));
						iconLabel.setIcon (new ImageIcon(personImage));
						iconLabel.revalidate ( );
						iconLabel.repaint ( );
						iconLabel.update (iconLabel.getGraphics ( ));						
					}// end try
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog (null, "Something went wrong with the picture", "ERROR", JOptionPane.ERROR_MESSAGE);
					}// end catch
				}//end try 
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog (null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}// end catch
			}// end if			
		}		
	}
	
	/**
	 * listener that saves to file when called
	 *
	 * <hr>
	 * Date created: Nov 29, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class SaveListener implements ActionListener
	{

		/**
		 * action performed that calls the saveFile method to save the file         
		 *
		 * <hr>
		 * Date created: Nov 28, 2019 
		 *
		 * <hr>
		 * @param arg0
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent arg0)
		{
			if(dir == null)
			{
				JOptionPane.showMessageDialog (null, "Please open a directory before you can save!!");
			}
			else
			{
				saveFile();
				names.clear ( );
				directoryNameTextFeild.setText ("");
				regNumMaskTextField.setText ("");
				lNameTextField.setText ("");
				fNameTextField.setText ("");
				middleTextField.setText ("");
				dobTextField.setText ("");
				genderComboBox.setSelectedIndex (0);
				streetTextField.setText ("");
				cityTextField.setText ("");
				stateComboBox.setSelectedIndex (0);
				zipMaskField.setText ("");
				phoneTypeComboBox.setSelectedIndex (0);
				phoneNumMaskField.setText ("");
				emailTypeComboBox.setSelectedIndex (0);
				emailTextField.setText ("");
				addDateTextField.setText ("");
				iconLabel.setIcon (null);
				//setBottomCenterPanel ( );
				//iconLabel = new JLabel("Please select a person from the Directory.", SwingConstants.CENTER);
				
				
			}
		}
		
	}
		
	
	/**
	 * listener that exits the program when called
	 *
	 * <hr>
	 * Date created: Nov 13, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class ExitListener implements ActionListener
	{

		/**
		 * action performed that literally terminates the program unless it needs to be saved  
		 *
		 * <hr>
		 * Date created: Nov 13, 2019 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			if (dir != null && dir.isSaveNeeded ( ) == true )
			{
				int option = JOptionPane.showConfirmDialog (null, "Would you like to save your changes to the current Directory? ", "Save", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) 
				{
					saveFile(); // need method
					System.exit (0);
				}// end if 
				else
				{
					System.exit (0);
				}// end else
			}//end if
			else
			{
				System.exit (0);
			}// end else
			
		}
		
		
	}
		
	
	/**
	 * listener that opens the about item when called
	 *
	 * <hr>
	 * Date created: Nov 30, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class AboutListener implements ActionListener
	{

		/**
		 * action performed that display about window         
		 *
		 * <hr>
		 * Date created: Nov 30, 2019 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			new About(DirectoryManagerGUI.this);			
		}
		
	}
		
	/**
	 * listener that connects to database whan called
	 *
	 * <hr>
	 * Date created: Nov 30, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class ConnectListener implements ActionListener
	{

		/**
		 * action performed that connects to the database         
		 *
		 * <hr>
		 * Date created: Nov 30, 2019 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			// TODO Auto-generated method stub
			
			//db = new Database();
			try 
			{
				db.connect ( );
				//JOptionPane.showMessageDialog (null, "Connected to Database");
				
			}
			catch(Exception e1)
			{
				e1.printStackTrace ( );	
			}
			
			
			//System.out.println ( "Connected Working???");
		}
		
	}
	
	/**
	 * listener that disconnects database when called
	 *
	 * <hr>
	 * Date created: Nov 30, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class DisonnectListener implements ActionListener
	{

		/**
		 * action performed that disconnects from the database         
		 *
		 * <hr>
		 * Date created: Nov 30, 2019 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			// TODO Auto-generated method stub
			
			if(db.con == null)
			{
				JOptionPane.showMessageDialog (null, "Your not connected to a database.");
			}
			else
			{
				try 
				{
					db.disconnect ( );
					names.clear ( );
				}
				catch(Exception e1)
				{
					e1.printStackTrace ( );	
				}
			}
			//System.out.println ( "Disconnect Working???");
		}
		
	}
		
	/**
	 * Adds/inserts a person to the database
	 *
	 * <hr>
	 * Date created: Dec 3, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class AddPersonDatabase implements ActionListener
	{

		/**
		 * actionPerformed that adds/inserts a new person to the database         
		 *
		 * <hr>
		 * Date created: Dec 3, 2019 
		 *
		 * <hr>
		 * @param arg0
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent arg0)
		{
			// TODO Auto-generated method stub
			
						Person tempPer = new Person();
						
						if(db.con == null)
						{
							JOptionPane.showMessageDialog (null, "Please connect to database.");
						}
						else
						{
							try // put all of these into their own try/catch possibly 
							{
								
								if (regNumMaskTextField.getText ( ) == "")
								{
									JOptionPane.showMessageDialog (null, "Please create a Person");
								}
								
								try 
								{
									tempPer.setRegNum 			(regNumMaskTextField.getText ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,  "Person ID is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setLastName 		(lNameTextField.getText ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,  "Last Name is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setFirstName 		(fNameTextField.getText ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,  "First Name is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setMiddleInital 	(middleTextField.getText ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,  "Middle Inital is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setDateOfBirth 		(dobTextField.getText ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,  "Date of Birth is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setGenderType 		(genderComboBox.getSelectedItem ( ).toString ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,   "Gender is invalid.",  "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setStreet 			(streetTextField.getText ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,   "Street is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setCity 			(cityTextField.getText ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,   "City is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setState 			(stateComboBox.getSelectedItem ( ).toString ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,   "State is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setZipcode 			(zipMaskField.getText ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,  "Zipcode is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setPhoneNumType 	(phoneTypeComboBox.getSelectedItem ( ).toString ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,  "Phone Number Type is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setPhoneNum 		(phoneNumMaskField.getText ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,  "Phone Number is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setEmailType 		(emailTypeComboBox.getSelectedItem ( ).toString ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,  "Email Type is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setEmail 			(emailTextField.getText ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,   "Email is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
								try 
								{
									tempPer.setDateAdded 		(addDateTextField.getText ( ));
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog (null,   "Dated Added is invalid.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
													
							}// end try 
							catch (Exception e1)
							{
								JOptionPane.showMessageDialog (null, e1.getMessage ( ), "Member could not be created",
									JOptionPane.ERROR_MESSAGE);
							}// end catch 
							
							JFileChooser fc = new JFileChooser ("Pictures\\persons\\");
							FileNameExtensionFilter filter = new FileNameExtensionFilter ("Image Files", ImageIO.getReaderFileSuffixes ( ));
							fc.setFileFilter (filter);
							fc.setDialogTitle ("Select A Member Photo");
							fc.setApproveButtonToolTipText ("Select the file you want to open, then click me.");
							
							boolean isPhotoValid = false;
							while(!isPhotoValid)
							{
								JOptionPane.showMessageDialog (null, "Please Select a Photo");
								int result = fc.showOpenDialog (null);
								try
								{
									if (result == JFileChooser.APPROVE_OPTION)
									{
										String photo = fc.getSelectedFile ( ).getName ( );
										String path  = fc.getSelectedFile ( ).getPath ( );
										tempPer.setPhoto (photo);
										try
										{
											resize(path, "Pictures" + tempPer.getPhoto ( ), 200, 200);
											isPhotoValid = true;
										}// end try
										catch (IOException e1)
										{
											JOptionPane.showMessageDialog (null, "Picture cannot be resized.");
											e1.printStackTrace();
										}// end catch
										isPhotoValid = true;
									} // end if
									
									else
									{
										
										int choice = JOptionPane.showConfirmDialog (null, "Would you like to continue without a member photo? ",
																					"Picture Option", JOptionPane.YES_NO_OPTION );
										if (choice == JOptionPane.YES_OPTION)
										{
											String photo = fc.getSelectedFile ( ).getPath ( );
											tempPer.setPhoto (photo);
											isPhotoValid = true;
										} // end if
										else if (choice == JOptionPane.NO_OPTION)// && choice == JOptionPane.CANCEL_OPTION)
										{
											//JOptionPane.showMessageDialog (null, "Please enter \"y/n\"", "Invalid Input",JOptionPane.ERROR_MESSAGE);
											tempPer.setPhoto ("noPic.png");
											isPhotoValid = true;
										} // end else if
									}// end else
								}
								catch(Exception e1)
								{
									JOptionPane.showMessageDialog (null, "No photo selected.");
									tempPer.setPhoto ("noPic.png");
									isPhotoValid = true;
								}
							
							}// end while
							
							try
							{
								//dir.addPerson (tempPer);
								db.addPerson (tempPer);
								JOptionPane.showMessageDialog (null, "Person successfully created");
								names.addElement (tempPer.getLastName ( ) + ", " + tempPer.getFirstName ( ));
								//dates.addElement (tempPer.getDateAdded ( ));
								//dir.setSaveNeeded (true);
							}
							catch(Exception e1)
							{
								JOptionPane.showMessageDialog (null, e1.getMessage ( ), "Person could not be created",
									JOptionPane.ERROR_MESSAGE);
							}
							try
							{
								db.add ();
							}
							catch (SQLException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
		}
		
	}
		
	/**
	 * Loads the database information in a readable format
	 *
	 * <hr>
	 * Date created: Dec 3, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class LoadDatabase implements ActionListener
	{

		/**
		 * actionPerformed that will load the database information into a readable format       
		 *
		 * <hr>
		 * Date created: Dec 3, 2019 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			if(db.con == null)
			{
				JOptionPane.showMessageDialog (null, "Please connect to database.");
			}
			else
			{			
				try
				{					
						db.loadFromSQL ( );	
						/*
						for (Person i : db.getPerson ( ))
						{
							names.addElement (i.getLastName ( ) + ", " + i.getFirstName ( ));
						}
						*/
				}
				catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	
	/**
	 * listener that sorts by last name when called 
	 *
	 * <hr>
	 * Date created: Nov 13, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class NameSortListener implements ActionListener
	{

		/**
		 * action performed that sorts by last name         
		 *
		 * <hr>
		 * Date created: Nov 13, 2019 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			try
			{
				dir.sortByLast (false);
				names.clear ( );
				for (Person i : dir.getPerson ( ))
				{						
					names.addElement (i.getLastName ( ) + ", " + i.getFirstName ( ));
				}// end for
			}
			catch (Exception e1)
			{
				JOptionPane.showMessageDialog (null,  "Please create or open an existing directory to sort!!!!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			}			
					// for debugging
			//System.out.println ( "Name Sort Working???");
		}		
	}
	
	/**
	 * listener that sorts by add date when called
	 *
	 * <hr>
	 * Date created: Nov 13, 2019
	 * <hr>
	 * @author Rodney Bowles
	 */
	private class DateSortListener implements ActionListener
	{

		/**
		 * action performed that sorts by add date          
		 *
		 * <hr>
		 * Date created: Nov 13, 2019 
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			try
			{
				dir.sortByJoin (true);
				names.clear ( );
				for (Person i : dir.getPerson ( ))
				{						
					names.addElement (i.getLastName ( ) + ", " + i.getFirstName ( ));
				}// end for
			}
			catch (Exception e1)
			{
				JOptionPane.showMessageDialog (null,  "Please create or open an existing directory to sort!!!!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			}			
				// for debugging
			//System.out.println ( "Date Sort Working???");
		}		
	}	
}// end DirectoryManagerGUI
