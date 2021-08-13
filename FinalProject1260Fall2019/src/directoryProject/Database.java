/**
 * ---------------------------------------------------------------------------
 * File name: Database.java
 * Project name: FinalProject1260Fall2019
 * ---------------------------------------------------------------------------
 * Creator's name and email: Rodney Bowles, bowlesr@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Dec 2, 2019
 * ---------------------------------------------------------------------------
 */

package directoryProject;


import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;

/**
 * Communicates with the database
 *
 * <hr>
 * Date created: Dec 2, 2019
 * <hr>
 * @author Rodney Bowles
 */
public class Database
{
	protected Connection con;
	protected static List<Person> person = new LinkedList<>();
	//protected static List<Person>	p = new ArrayList<Person>();
	//private String lastName;
	//private String firstName;
	
	protected ArrayList <String> thingy;
	
	
	
	  /**
	 * Connects to the database         
	 *
	 * <hr>
	 * Date created: Dec 4, 2019
	 *
	 * <hr>
	 * @throws Exception
	 */
	public void connect() throws Exception
	{
		 if(con!=null)return;
		 try
		 {
			 Class.forName ("com.mysql.cj.jdbc.Driver"); 
		 }
		 catch(ClassNotFoundException e)
		 {
			 throw new Exception("Driver not found");
		 }
		 String url = "jdbc:mysql://localhost:3306/directory";
		 con = DriverManager.getConnection (url,"root","");
		 
		System.out.print ("Connected :" + con);
		JTextArea tArea = new JTextArea ("Connected to Database");
		JOptionPane.showMessageDialog (null, tArea);
		 
		 //add();
		 //loadFromSQL ( );
		
	}
	  
	  /**
	 * Takes a person in as an object for the array         
	 *
	 * <hr>
	 * Date created: Dec 4, 2019
	 *
	 * <hr>
	 * @param p
	 */
	 public void addPerson(Person p)
	  {
		  person.add (p);
	  }	  
	  
	  
	  /**
	 * Add the person to the database         
	 *
	 * <hr>
	 * Date created: Dec 4, 2019
	 *
	 * <hr>
	 * @throws SQLException
	 */
	public void add() throws SQLException
	  {
		  String sql= "Select count(*) as count from person where person_id=?";
		  PreparedStatement check = con.prepareStatement (sql);
		  
		  String addsql = "insert into person(person_id,last_name,first_name,middle_inital,date_of_birth," +
		  				"gender_type,street,city,state,zipcode,phone_num_type,phone_number,email_type,email," +
		  				"date_added,photo)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		  
		  PreparedStatement addStatement = con.prepareStatement (addsql);
		  
		  for (Person person : person)
		{
			String person_id = person.getRegNum ( );
			String last_name = person.getLastName ( );
			String first_name = person.getFirstName ( );
			String middle_inital = person.getMiddleInital ( );
			String date_of_birth = person.getDateOfBirth ( );
			GenderType gender_type = person.getGenderType ( );
			String street = person.getStreet ( );
			String city = person.getCity ( );
			StateAbbrevationList state = person.getState ( );
			String zipcode = person.getZipcode ( );
			PhoneType phone_num_type = person.getPhoneNumType ( );
			String phone_number = person.getPhoneNum ( );
			EmailType email_type = person.getEmailType ( );
			String email = person.getEmail ( );
			String date_added = person.getDateAdded ( );
			String photo = person.getPhoto ( );
			
			check.setString (1, person_id);
			
			ResultSet checkResult = check.executeQuery ( );
			checkResult.next ( );
			
			int count = checkResult.getInt (1);
			
			if(count == 0) 
			{
				JOptionPane.showMessageDialog (null, "Added to database " + person_id);
				
				addStatement.setString (1, person_id);
				addStatement.setString (2, last_name);
				addStatement.setString (3, first_name);
				addStatement.setString (4, middle_inital);
				addStatement.setString (5, date_of_birth);
				addStatement.setString (6, gender_type.name ( ));
				addStatement.setString (7, street);
				addStatement.setString (8, city);
				addStatement.setString (9, state.name ( ));
				addStatement.setString (10, zipcode);
				addStatement.setString (11, phone_num_type.name ( ));
				addStatement.setString (12, phone_number);
				addStatement.setString (13, email_type.name ( ));
				addStatement.setString (14, email);
				addStatement.setString (15, date_added);
				addStatement.setString (16, photo);
				
				addStatement.executeUpdate ( );
				
			}
			else 
			{
				JOptionPane.showMessageDialog (null, "Added to Database " + person_id);
			}
		}
		 
		  addStatement.close ( );
		  person.clear ( );
	  }
	  	  
	  /**
	 * Loads the current database into a JOptionPane window for display      
	 *
	 * <hr>
	 * Date created: Dec 4, 2019
	 *
	 * <hr>
	 * @throws Exception
	 */
	public void loadFromSQL() throws Exception
	  
	  {
		  person.clear ( );
		  thingy = new ArrayList <String> ( );
		  String sql = "select person_id,last_name,first_name,middle_inital,date_of_birth," +
			  			"gender_type,street,city,state,zipcode,phone_num_type,phone_number,email_type,email," +
			  			"date_added,photo from person order by person_id";
		  
		  
		//Statement selectStatement = con.createStatement ( );
		java.sql.Statement selectStatement = con.createStatement ( );
		
		ResultSet result = selectStatement.executeQuery (sql);
		
		while(result.next ( )) 
		{
			String sqlresults = "Person ID:  		" + result.getString ("person_id")  	+ "\n" +
								"Last Name:		" + result.getString ("last_name") 		+ "\n" +
								"First Name:		" + result.getString ("first_name") 	+ "\n" +
								"Middle Initial:		" + result.getString ("middle_inital")  + "\n" +
								"Date of Birth:		" + result.getString ("date_of_birth")	+ "\n" +
								"Gender:			" + result.getString ("gender_type")	+ "\n" +
								"Street:			" + result.getString ("street")			+ "\n" +
								"City:			" + result.getString ("city")			+ "\n" +
								"State:			" + result.getString ("state")			+ "\n" +
								"Zipcode:		" + result.getString ("zipcode")		+ "\n" +
								"Phone Number Type:	" + result.getString ("phone_num_type")	+ "\n" +	
								"Phone Number:		" + result.getString ("phone_number") 	+ "\n" +
								"Email Type:		" + result.getString ("email_type")		+ "\n" +
								"Email:			" + result.getString ("email")			+ "\n" +
								"Date Added:		" + result.getString ("date_added")		+ "\n" +
								"Photo URL:		" + result.getString ("photo")			+ "\n\n"  ;
			thingy.add (sqlresults);
		}
		//lastName =result.getString ("last_name") ;
			
		//firstName = result.getString ("first_name");
		
		String printOut = "";
		for (int i = 0; i < thingy.size ( ); i++ )
		{
			printOut += i + 1 +". " +thingy.get (i)+"\n";
		}
		
		JTextArea tArea = new JTextArea (printOut);
		JScrollPane scrollPane = new JScrollPane (tArea);
		tArea.setLineWrap (true);
		tArea.setWrapStyleWord (true);
		scrollPane.setPreferredSize (new Dimension ( 500,500));
		JOptionPane.showMessageDialog (null, scrollPane);
		
		//System.out.print (thingy.toString ( ));
		
		result.close ( );
		selectStatement.close ( );
	  }
	  
	/**
	 * Disconnects from database        
	 *
	 * <hr>
	 * Date created: Dec 4, 2019
	 *
	 * <hr>
	 */
	public void disconnect()
	  {
		  if (con != null)		  
		  {
			  try
			  {
				  con.close ( );
				  JTextArea tArea = new JTextArea ("Disconnected from Database");
					JOptionPane.showMessageDialog (null, tArea);			  
			  }
			  catch (Exception e)
			  {
				  System.out.println ( "Cant close try again ");
			  }
		  }	  
	  }
	  
	  
	  
//--------------------------OTHER THINGS THAT ARE BEING ATTEMPED BUT NOT IN PROGRAM UNLESS ITS ABOVE THIS LINE--------------------------\\
	  
	  public void reomovePerson(int r)
	  {
		  person.remove (r);
	  }
	 
	
	  public ArrayList<Person> getPerson()
	  {
		  return (ArrayList <Person>) person;
	  }
	 
	/*
	 * public String getLastName()
	 * {
	 * return lastName;
	 * }
	 * public String getFirstName()
	 * {
	 * return firstName;
	 * }
	 */
	/*
	 * public Person findName(String personName) throws Exception
	 * {
	 * if (!personName.isEmpty ( ) && personName !=null)
	 * {
	 * Person personMatch = null;
	 * for (Person member : p)
	 * {
	 * if ((member.getLastName ( ) + ", " + member.getFirstName ( )).equalsIgnoreCase (personName))
	 * {
	 * personMatch = member;
	 * }// end if
	 * }// end for
	 * if (personMatch == null )
	 * {
	 * throw new Exception ("Could not find member!! ");
	 * }// end if
	 * else
	 * {
	 * return personMatch;
	 * }// end else
	 * }// end if
	 * else
	 * {
	 * throw new Exception ("Member name cannot be empty." );
	 * }// end else
	 * //return null;
	 * }
	 */
	  
	  /*
	  public Person findByName(String personName) throws Exception
		
		{
			if (!personName.isEmpty ( ) && personName !=null)
			{
				Person personMatch = null;
				for (Person member : p)
				{
					if ((member.getLastName ( ) + ", " + member.getFirstName ( )).equalsIgnoreCase (personName))
					{
						personMatch = member;
					}// end if
				}// end for
				
				if (personMatch == null )
				{
					throw new Exception ("Could not find member!! ");
				}// end if
				else 
				{
					return personMatch;
				}// end else
			}// end if
			else
			{
				throw new Exception ("Member name cannot be empty." );
			}// end else
		}// findByName
	  */
	  
	  
	  
	  
	  
}
