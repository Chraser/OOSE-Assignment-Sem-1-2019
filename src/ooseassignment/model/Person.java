/************************************************************
 *Name: Kay Men Yap
 *File name: Person.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.model;
import java.util.Map;
public abstract class Person
{
    /*
    autoGeneratedNum is used to generate idNum of person and it is a static classfield so
    that the factory method can use it to generate unique idNum for Person
    */
	private static int autoGeneratedNum = 1;
	public static final String[] TYPESOFPERSON = {"Candidate","Strategist","Volunteer"};
	public static final String[] TYPESOFCONTACT = {"MobileNumber","FacebookID","TwitterUsername"};
	protected Integer idNum;
	protected String name;
	protected Map<String, String> contactDetailsMap;

    //factory method to construct a Person object of the correct Subclass type
	public static Person makePerson(String name, String type, Map<String, String> contactDetailsMap) throws PersonException
	{
		Person person = null;
		if(type.equals(TYPESOFPERSON[0]))
		{
			person = new Candidate(Integer.valueOf(autoGeneratedNum), name, contactDetailsMap);
		}
		else if(type.equals(TYPESOFPERSON[1]))
		{
			person = new Strategist(Integer.valueOf(autoGeneratedNum), name, contactDetailsMap);
		}
		else if(type.equals(TYPESOFPERSON[2]))
		{
			person = new Volunteer(Integer.valueOf(autoGeneratedNum), name, contactDetailsMap);
		}
        if(person != null)
        {
            autoGeneratedNum++;
        }
		return person;
	}

	public Person(Integer idNum, String name, Map<String, String> contactDetailsMap) throws PersonException
	{
		if(name.equals(""))
		{
			throw new PersonException("Name can't be empty");
		}
		if(contactDetailsMap.isEmpty())
		{
			throw new PersonException("There must be at least one contact details for a person");
		}
		this.idNum = idNum;
		this.name = name;
		this.contactDetailsMap = contactDetailsMap;
	}

	public Integer getIdNum()
	{
		return idNum;
	}

	public String getName()
	{
		return name;
	}

	public Map<String, String> getContactDetailsMap()
	{
		return contactDetailsMap;
	}

	public boolean equals(Object obj)
	{
		boolean equals = false;
		if(obj instanceof Person)
		{
			Person person = (Person)obj;
			if(idNum == person.getIdNum())
			{
				equals = true;
			}
		}
		return equals;
	}

	public abstract String toString();

    /*
    imports a policy area and observerFactory and uses observerFactory to make observers that would
    notify person of events that occurs in that policy area that the person's type would know
    */
	public abstract void subscribeToPolicyArea(PolicyArea policyArea, ObserverFactory observerFactory);
}
