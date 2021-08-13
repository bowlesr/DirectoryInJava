/**
 * ---------------------------------------------------------------------------
 * File name: Person.java
 * Project name: FinalProject1260Fall2019
 * ---------------------------------------------------------------------------
 * Creator's name and email: Rodney Bowles, bowlesr@etsu.edu and John Pilson, pilson@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Nov 9, 2019
 * ---------------------------------------------------------------------------
 */

package directoryProject;



/**
 * to create a person object
 *
 * <hr>
 * Date created: Nov 9, 2019
 * <hr>
 * @author John Pilson
 */
public class Person
{
	private int 						regNum = 0;		// using mask to force numbers
	private String						lastName;		
	private String 						firstName;
	private String						middleInital;
	private String						dateOfBirth;	// using mask to force numbers
	private GenderType					genderType;		
	private String						street;
	private String 						city;
	private StateAbbrevationList 		state;			
	private String 						zipcode;		// using mask to force numbers
	private PhoneType 					phoneNumType;	// placeholder for the phone number type, converted to enum value in the set method
	private String 						phoneNum;		// placeholder for the phone number using mask to force numbers
	private EmailType					emailType;		// place holder for the email type, converted to enum value in the set method
	private String 						email;			// placeholder for the email
	private String						photo;			// will hold the string path for photo location 
	private String						dateAdded; 		// using mask to force numbers
	
	/**
	 * Constructor        
	 *
	 * <hr>
	 * Date created: Nov 9, 2019 
	 *
	 * 
	 */
	public Person ( )
	{
		//regNum = regNum +1;
	}

	
	/**
	 * Constructor        
	 *
	 * <hr>
	 * Date created: Nov 9, 2019 
	 *
	 * 
	 * @param regNum
	 * @param lastName
	 * @param firstName
	 * @param middleInital
	 * @param dateOfBirth
	 * @param genderType
	 * @param street
	 * @param city
	 * @param state
	 * @param zipcode
	 * @param phoneNumType
	 * @param phoneNum
	 * @param emailType
	 * @param email
	 * @param photo
	 * @param dateAdded
	 */
	public Person (	int regNum, String lastName, String firstName, String middleInital, String dateOfBirth,
					GenderType genderType, String street, String city, StateAbbrevationList state, String zipcode,
					PhoneType phoneNumType, String phoneNum, EmailType emailType, String email, String photo,
					String dateAdded)
	{
		this.regNum = regNum;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleInital = middleInital;
		this.dateOfBirth = dateOfBirth;
		this.genderType = genderType;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.phoneNumType = phoneNumType;
		this.phoneNum = phoneNum;
		this.emailType = emailType;
		this.email = email;
		this.photo = photo;
		this.dateAdded = dateAdded;
	}
	
	/**
	 * @return regNum
	 */
	public String getRegNum ()
	{
		 String num = Integer.toString (regNum);
		 return num;
	}
		
	public int getRegNumINT()
	 {
		 return regNum;
	 }
	
	/**
	 * @param regNum the regNum to set
	 */
	public void setRegNumINT (int num)
	{
		regNum = num;
	} 
	 
	/**
	 * @param regNum the regNum to set
	 */
	public void setRegNum (String num)
	{
		this.regNum = Integer.parseInt (num);
	}
	
	/**
	 * @return lastName
	 */
	public String getLastName ( )
	{
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName (String lastName)
	{
		this.lastName = lastName;
	}
	
	/**
	 * @return firstName
	 */
	public String getFirstName ( )
	{
		return firstName;
	}
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName (String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * @return middleInital
	 */
	public String getMiddleInital ( )
	{
		return middleInital;
	}
	
	/**
	 * @param middleInital the middleInital to set
	 */
	public void setMiddleInital (String middleInital)
	{
		this.middleInital = middleInital;
	}
	
	/**
	 * @return dateOfBirth
	 */
	public String getDateOfBirth ( )
	{
		return dateOfBirth;
	}
	
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth (String dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	
	/**
	 * @return genderType
	 */
	public GenderType getGenderType ( )
	{
		return genderType;
	}
	
	/**
	 * @param gender the genderType to set
	 * takes in a string and sets it to the proper enum value
	 */
	public void setGenderType (String gender)
	{
		gender = gender.trim ( ).toUpperCase ( );
		GenderType displayGender = GenderType.valueOf (gender);
		this.genderType = displayGender;
	}
	
	/**
	 * @return street
	 */
	public String getStreet ( )
	{
		return street;
	}
	
	/**
	 * @param street the street to set
	 */
	public void setStreet (String street)
	{
		this.street = street;
	}
	
	/**
	 * @return city
	 */
	public String getCity ( )
	{
		return city;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity (String city)
	{
		this.city = city;
	}
	
	/**
	 * @return state
	 */
	public StateAbbrevationList getState ( )
	{
		return state;
	}
	
	/**
	 * @param state the state to set
	 * takes in a string and sets it to the proper enum value
	 */
	public void setState (String s)
	{
		s = s.trim ( ).toUpperCase ( );
		StateAbbrevationList stateAB = StateAbbrevationList.valueOf (s);
		this.state = stateAB;
	}
	
	/**
	 * @return zipcode
	 */
	public String getZipcode ( )
	{
		return zipcode;
	}
	
	/**
	 * @param zipcode the zipcode to setState
	 */
	public void setZipcode (String zipcode)
	{
		this.zipcode = zipcode;
	}
	
	/**
	 * @return phoneNumType
	 */
	public PhoneType getPhoneNumType ( )
	{
		return phoneNumType;
	}
	
	/**
	 * @param phoneNumType the phoneNumType to set
	 * takes in a string and sets it to the proper enum value
	 */
	public void setPhoneNumType (String pnt)  // pnt = phone number type that will be passed to it 
	{
		pnt = pnt.trim ( ).toUpperCase ( );
		PhoneType pType = PhoneType.valueOf (pnt);
		this.phoneNumType = pType;
	}
	
	/**
	 * @return phoneNum
	 */
	public String getPhoneNum ( )
	{
		return phoneNum;
	}
	
	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum (String phoneNum)
	{
		this.phoneNum = phoneNum;
	}
	
	/**
	 * @return emailType
	 */
	public EmailType getEmailType ( )
	{
		return emailType;
	}
	
	/**
	 * @param emailType the emailType to set
	 * takes in a string and sets it to the proper enum value
	 */
	public void setEmailType (String eType)
	{
		eType = eType.trim ( ).toUpperCase ( );
		EmailType type = EmailType.valueOf (eType);
		this.emailType = type;
	}
	
	/**
	 * @return email
	 */
	public String getEmail ( )
	{
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail (String email)
	{
		this.email = email;
	}
	
	/**
	 * @return photo
	 */
	public String getPhoto ( )
	{
		return photo;
	}
	
	/**
	 * @param photo the photo to set
	 */
	public void setPhoto (String photo)
	{
		this.photo = photo;
	}
	
	/**
	 * @return dateAdded
	 */
	public String getDateAdded ( )
	{
		return dateAdded;
	}
	
	/**
	 * @param dateAdded the dateAdded to set
	 */
	public void setDateAdded (String dateAdded)
	{
		this.dateAdded = dateAdded;
	}
	
	/**
	 * Added for testing purposes and it joins the name as one          
	 *
	 * <hr>
	 * Date created: Nov 30, 2019
	 *
	 * <hr>
	 * @return name
	 */
	public String name()
	{
		String name = firstName +", " + lastName;
		return name;
	}//end name
}// end Person
