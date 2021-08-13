/**
 * ---------------------------------------------------------------------------
 * File name: Directory.java
 * Project name: FinalProject1260Fall2019
 * ---------------------------------------------------------------------------
 * Creator's name and email: Rodney Bowles, bowlesr@etsu.edu, John Pilson, pilson@etsu.edu and Wyatt Stine, stinew@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Nov 9, 2019
 * ---------------------------------------------------------------------------
 */

package directoryProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;



/**
 * Creates a directory "manager" that allows you to make a directory of "person" objects
 *
 * <hr>
 * Date created: Nov 9, 2019
 * <hr>
 * @author John Pilson and Wyatt Stine
 */
public class Directory
{

	protected ArrayList		<Person> 	person 				= new ArrayList<>();
	private String 						directoryName;
	private static String				fileName			= "";
	private boolean						saveNeeded = false;
	
	/**
	 * Constructor        
	 *
	 * <hr>
	 * Date created: Nov 9, 2019 
	 *
	 * 
	 */
	public Directory ( )
	{
		directoryName = "Name";
	}
	
	
	/**
	 * Constructor        
	 *
	 * <hr>
	 * Date created: Nov 9, 2019 
	 *
	 * 
	 * @param person
	 * @param directoryName
	 * @param saveNeeded
	 */
	public Directory (ArrayList <Person> person, String directoryName, boolean saveNeeded)
	{
		this.person = person;
		this.directoryName = directoryName;
		this.saveNeeded = saveNeeded;
	}

	/**
	 * @return person
	 */
	public ArrayList <Person> getPerson ( )
	{		
		return person;
	}
	
	/**
	 * @param person the person to set
	 */
	public void setPerson (ArrayList <Person> person)
	{
		this.person = person;
	}
	
	/**
	 * @return directoryName
	 */
	public String getDirectoryName ( )
	{
		return directoryName;
	}
	
	/**
	 * @param directoryName the directoryName to set
	 */
	public void setDirectoryName (String directoryName)
	{
		this.directoryName = directoryName;
	}
	
	/**
	 * @return fileName
	 */
	public static String getFileName ( )
	{
		return fileName;
	}
	
	/**
	 * @param fileName the fileName to set
	 */
	public static void setFileName (String fileName)
	{
		Directory.fileName = fileName;
	}
	
	/**
	 * @return saveNeeded
	 */
	public boolean isSaveNeeded ( )
	{
		return saveNeeded;
	}
	
	/**
	 * @param saveNeeded the saveNeeded to set
	 */
	public void setSaveNeeded (boolean saveNeeded)
	{
		this.saveNeeded = saveNeeded;
	}
	
	/**
	 * add a person to the array list as a Person object         
	 *
	 * <hr>
	 * Date created: Nov 30, 2019
	 *
	 * <hr>
	 * @param id
	 */
	public void addPerson(Person id)
	{
		person.add (id);
		saveNeeded = true;
	}
	
	/**
	 * Removes person form array list         
	 *
	 * <hr>
	 * Date created: Nov 30, 2019
	 *
	 * <hr>
	 * @param i
	 */
	public void removeMember(int i)
	{
		person.remove (i);
		saveNeeded =  true;
	}

	/**
	 * edits person and add "rewrites" of the old one with "new" Person object         
	 *
	 * <hr>
	 * Date created: Nov 30, 2019
	 *
	 * <hr>
	 * @param i
	 * @param object
	 */
	public void editMember(int i, Person object)
	{
		person.set (i, object);
		saveNeeded = true;
	}
	
	/**
	 * Save the file to a text file         
	 *
	 * <hr>
	 * Date created: Nov 30, 2019
	 *
	 * <hr>
	 * @param file
	 */
	public void saveFile (String file)
	{
		
		saveNeeded = false;
		try
		{
			//resort ( );
			PrintWriter pw = new PrintWriter (file);
			
			pw.println (getDirectoryName( ));
			
			for (int i = 0; i < person.size ( ); i++ )
			{
				pw.println (	  person.get (i).getRegNum ( ) 			+ "|" 
								+ person.get (i).getLastName ( ) 		+ "|" 
								+ person.get (i).getFirstName ( ) 		+ "|" 
								+ person.get (i).getMiddleInital ( ) 	+ "|" 
								+ person.get (i).getDateOfBirth ( ) 	+ "|" 
								+ person.get (i).getGenderType ( ) 		+ "|" 
								+ person.get (i).getStreet ( ) 			+ "|" 
								+ person.get (i).getCity ( ) 			+ "|" 
								+ person.get (i).getState ( ) 			+ "|" 
								+ person.get (i).getZipcode ( ) 		+ "|" 
								+ person.get (i).getPhoneNumType ( ) 	+ "|" 
								+ person.get (i).getPhoneNum ( ) 		+ "|" 
								+ person.get (i).getEmailType ( ) 		+ "|"   
								+ person.get (i).getEmail ( ) 			+ "|"
								+ person.get (i).getDateAdded ( ) 		+ "|"
								+ person.get (i).getPhoto ( ));
			}// end for
			saveNeeded = false;
			pw.close ( );// closes the pritwriter
		}// end try
		catch (Exception e)
		{
			JOptionPane.showMessageDialog (null, "Unalble to write to file.");
			//System.out.println ("Unable to write to file. ");
			saveNeeded = false;
			return;
		}// end catch
	}// end saveFile
	
	/**
	 * pulls information from a text file and fills the array list with person objects        
	 *
	 * <hr>
	 * Date created: Nov 30, 2019
	 *
	 * <hr>
	 * @param fileName
	 */
	public void fillFromFile (File fileName)
	{
		Scanner kb = null;
		try
		{
			kb = new Scanner (fileName);
		}// end try
		catch (FileNotFoundException e)
		{
			e.printStackTrace ( );
		}// end catch
		while (kb.hasNext ( ))
		{
			String str = kb.nextLine ( );
			String [ ] fields = str.split ("\\|");

			if (fields.length == 1)
			{
				directoryName = fields [0];				
				setDirectoryName (directoryName);				

			}// end if
			else if (fields.length == 16)
			{
				Person personFile = new Person ( );
				String regNum 		= fields [0];
					//int num = Integer.parseInt (regNum); // to make the regNum an int
				String lastName 	= fields [1];
				String firstName 	= fields [2];
				String middleInital = fields [3];
				String dob 			= fields [4];
				String gender 		= fields [5];
				String street 		= fields [6];
				String city 		= fields [7];
				String state 		= fields [8];
				String zip 			= fields [9];
				String phoneType 	= fields [10];
				String phoneNum 	= fields [11];
				String emailType 	= fields [12];
				String email 		= fields [13];
				String dateAdded 	= fields [14];				
				String photo 		= fields [15];
				
				personFile.setRegNum 		(regNum);				
				personFile.setLastName 		(lastName);
				personFile.setFirstName 	(firstName);
				personFile.setMiddleInital 	(middleInital);
				personFile.setDateOfBirth 	(dob);
				personFile.setGenderType 	(gender);
				personFile.setStreet 		(street);
				personFile.setCity 			(city);
				personFile.setState 		(state);
				personFile.setZipcode 		(zip);
				personFile.setPhoneNumType 	(phoneType);
				personFile.setPhoneNum 		(phoneNum);
				personFile.setEmailType 	(emailType);
				personFile.setEmail 		(email);				
				personFile.setDateAdded 	(dateAdded);
				personFile.setPhoto 		(photo);
				person.add 					(personFile);
			}// end else if
		}// end while		
		kb.close ( );// closed the scanner
		saveNeeded = false;
	}// end fillFromFile	

	
	
	/**
	 * finds person by their name that comes from the listlistener so it will update and populate with 
	 * correct information         
	 *
	 * <hr>
	 * Date created: Nov 30, 2019
	 *
	 * <hr>
	 * @param personName
//	 * @return
	 * @throws Exception
	 */
	public Person findByName(String personName) throws Exception
		
		{
			if (!personName.isEmpty ( ) && personName !=null)
			{
				Person personMatch = null;
				for (Person member : person)
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
	
	

	
	//added by John Pilson
	
	
	
	/**
	 * sorts the people array by last name
	 *
	 * @param invert determines if sort is A-Z or Z-A
	 */
	public void sortByLast(boolean invert)
	{
		int comp = 0;
		if(invert == true)
		{
			for(int i = person.size()-1; i > 0; i--)
			{
				for(int q = 0; q < i; q++)
				{
					comp = person.get(q).getLastName().compareTo(person.get(q+1).getLastName());
					if(comp < 0)
						swap(q, q+1);
				}
			}
		}
		else
		{
			for(int i = person.size()-1; i > 0; i--)
			{
				for(int q = 0; q < i; q++)
				{
					comp = person.get(q).getLastName().compareTo(person.get(q+1).getLastName());
					if(comp > 0)
						swap(q, q+1);
				}
			}
		}
	}

	/**
	 * sorts the people array by join date
	 *
	 * @param invert determines if sort is first to last or last to first
	 */
	public void sortByJoin(boolean invert) {
		int comp = 0;
		String subString1 = "";
		String subString2 = "";
		if (invert == true)
		{
			for (int i = person.size()-1; i > 0; i--)
			{
				for (int q = 0; q < i; q++)
				{
					subString1 = person.get(q).getDateAdded().substring(6, 10)
							+ person.get(q).getDateAdded().substring(0, 2)
							+ person.get(q).getDateAdded().substring(3, 5);
					subString2 = person.get(q + 1).getDateAdded().substring(6, 10)
							+ person.get(q + 1).getDateAdded().substring(0, 2)
							+ person.get(q + 1).getDateAdded().substring(3, 5);
					if (Integer.parseInt(subString1) < Integer.parseInt(subString2))
						swap(q, q + 1);
				}
			}
		}
		else
		{
			for (int i = person.size()-1; i > 0; i--)
			{
				for (int q = 0; q < i; q++)
				{
					subString1 = person.get(q).getDateAdded().substring(6, 10)
							+ person.get(q).getDateAdded().substring(0, 2)
							+ person.get(q).getDateAdded().substring(3, 5);
					subString2 = person.get(q + 1).getDateAdded().substring(6, 10)
							+ person.get(q + 1).getDateAdded().substring(0, 2)
							+ person.get(q + 1).getDateAdded().substring(3, 5);
					if (Integer.parseInt(subString1) > Integer.parseInt(subString2))
						swap(q, q + 1);
				}
			}
		}
	}

	/**
	 * reorders the ArrayList by regNum to restore it to its original state
	 */
	public void resort()	//check with John about this one
	{
		ArrayList<Person> reset = new ArrayList();
		ArrayList<Person> temp;
		
		for(int i = 0; i < person.size(); i++)
		{
			int finalI = i;												
			temp = (ArrayList<Person>) person.stream().filter(p -> finalI == p.getRegNumINT()).collect(Collectors.toList()); // created a new method that returns the regNum as an int so it would work
			temp.forEach(s -> reset.add(s));
		}
		person = reset;
	}

	/**
	 * searches the people Array List for the exact join date given
	 * and returns an Array List of elements with said join date
	 *
	 * @param date the date that is searched for
	 * @return searchedPeople
	 */
	public ArrayList<Person> searchByJoin(String date)
	{
		ArrayList<Person> searchedPeople = new ArrayList<>();
		person.forEach(p ->
			{
				if(date.equals(p.getDateAdded()))
					searchedPeople.add(p);
			});

		return searchedPeople;
	}

	/**
	 * searches the people Array List for the last name given
	 * and returns an Array List of elements with said last name
	 *
	 * @param lastName the last name that is searched for
	 * @return
	 */
	public ArrayList<Person> searchByLast(String lastName)
	{
		ArrayList<Person> searchedPeople = new ArrayList<>();
		person.forEach(p ->
		{
			if(lastName.toUpperCase().equals(p.getLastName().toUpperCase()))
				searchedPeople.add(p);
		});

		return searchedPeople;
	}

	/**
	 * swaps two elements of the people array
	 *
	 * @param i first swapped element
	 * @param q second swapped element
	 */
	private void swap(int i, int q)
	{
		Person temp = person.get(i);
		person.set(i, person.get(q));
		person.set(q, temp);
	}
	
	
	//--------------------------OTHER THINGS THAT ARE BEING ATTEMPED BUT NOT IN PROGRAM UNLESS ITS ABOVE THIS LINE--------------------------\\
	
	
	/**
	 * used to increment and set the person ID        
	 *
	 * <hr>
	 * Date created: Nov 30, 2019
	 *
	 * <hr>
	 */
	public void setRegNum()
	{
		for (int i = 0; i < person.size ( ); i++ )
		{
			
			Person p = new Person();
			//p.setRegNumINT (person.get (i));
			
		}
	}
	
}
