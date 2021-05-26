/************************************************************
 *Name: Kay Men Yap
 *File name: AddOption.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.controller;
import java.util.*;
import ooseassignment.model.*;
import ooseassignment.view.UserIO;
public class AddOption extends UserManipulatedOption
{
	private ObserverFactory observerFactory;

	public AddOption(UserIO userIO, ObserverFactory observerFactory)
	{
		super(userIO);
		this.observerFactory = observerFactory;
	}

    //request for person info and adds person to personMap
	@Override
	protected void doOnPerson(Map<Integer, Person> personMap)
	{
		String name = userIO.inputString("Please enter name of person");
		int type = userIO.inputInteger("Please select type of person: \n1.Candidate \n2.Strategist \n3.Volunteer", 1,3);
		Map<String, String> contactDetailsMap = new HashMap<String, String>();
		String contactDetail;
		int choice;
		String contactType;
		for(int i = 0; i < Person.TYPESOFCONTACT.length; i++)
		{
			contactType = Person.TYPESOFCONTACT[i];
			choice = userIO.inputInteger("Do you want to enter " + contactType
											 + " for person? 0 for no and 1 for yes", 0, 1);
			if(choice == 1)
			{
				if(contactType.equals(Person.TYPESOFCONTACT[0]))
				{
					contactDetail = userIO.inputMobileNumber();
				}
				else
				{
					contactDetail = userIO.inputString("Enter " + contactType + ":");
				}
				contactDetailsMap.put(contactType, contactDetail);
			}
		}
		try
		{
			Person person = Person.makePerson(name, Person.TYPESOFPERSON[type - 1], contactDetailsMap);
			personMap.put(person.getIdNum(), person);
            userIO.displayMessage("Person successfully added");
		}
		catch(PersonException e)
		{
			userIO.displayMessage(e.getMessage());
		}
	}

    //request for policy area info and if policy area already exist, report back to user or else add to policyAreaMap
	@Override
	protected void doOnPolicyArea(Map<String, PolicyArea> policyAreaMap, Map<String, PolicyArea> keywordMap, Map<String, PolicyArea> talkingPointMap)
	{
		String name = userIO.inputString("Please enter name of policy area");
		if(policyAreaMap.containsKey(name))
		{
			userIO.displayMessage("ERROR: Policy area " + name + " already exist. Going back to main menu");
		}
		else
		{
			try
			{
				PolicyArea policyArea = PolicyArea.makePolicyArea(name);
				policyAreaMap.put(name, policyArea);
                userIO.displayMessage("Policy Area successfully added");
			}
			catch(PolicyAreaException e)
			{
				userIO.displayMessage(e.getMessage());
			}
		}
	}

    /*
    request for keyword and if policy area does not exist or keyword already exists, report back to user or else add to corresponding
    policy area and keywordMap
    */
	@Override
	protected void doOnKeyword(Map<String, PolicyArea> policyAreaMap, Map<String, PolicyArea> keywordMap)
	{
		String policyAreaName = userIO.inputString("Please enter name of policy area");
		if(!(policyAreaMap.containsKey(policyAreaName)))
		{
			userIO.displayMessage("ERROR: Policy area " + policyAreaName + " does not exist. Going back to main menu");
		}
		else
        {
            String keyword = userIO.inputString("Please enter keyword");
            if(keywordMap.containsKey(keyword))
    		{
    			userIO.displayMessage("ERROR: Keyword " + keyword + " already exist in policy area " +
    								  keywordMap.get(keyword).getName() + "\nGoing back to main menu");
    		}
    		else
    		{
    			PolicyArea policyArea = policyAreaMap.get(policyAreaName);
                try
                {
        			policyArea.addKeyword(keyword);
        			keywordMap.put(keyword, policyArea);
                    userIO.displayMessage("Keyword successfully added");
                }
                catch(PolicyAreaException e)
                {
                    userIO.displayMessage(e.getMessage());
                }
    		}
        }
	}

    /*
    request for talking point and if policy area does not exist or talking point already exists, report back to user or else
    add to corresponding policy area and talkingPointMap
    */
	@Override
	protected void doOnTalkingPoint(Map<String, PolicyArea> policyAreaMap, Map<String, PolicyArea> talkingPointMap)
	{
		String policyAreaName = userIO.inputString("Please enter name of policy area");
		if(!(policyAreaMap.containsKey(policyAreaName)))
		{
			userIO.displayMessage("ERROR: Policy area " + policyAreaName + " does not exist. Going back to main menu");
		}
		else
        {
            String talkingPoint = userIO.inputString("Please enter talking point");
            if(talkingPointMap.containsKey(talkingPoint))
    		{
    			userIO.displayMessage("ERROR: Talking point " + talkingPoint + " already exist in policy area " +
    								  talkingPointMap.get(talkingPoint).getName() + " Going back to main menu");
    		}
    		else
    		{
    			PolicyArea policyArea = policyAreaMap.get(policyAreaName);
                try
                {
        			policyArea.addTalkingPoint(talkingPoint);
        			talkingPointMap.put(talkingPoint, policyArea);
                    userIO.displayMessage("Talking point successfully added");
                }
                catch(PolicyAreaException e)
                {
                    userIO.displayMessage(e.getMessage());
                }
            }
		}
	}

    /*
    request for personID and if policy area does not exist in policyAreaMap or person ID does not exist in personMap,
    report back to user or else add notification to corresponding policy area and add option is still performed
    even if notification already exists between personID and policy area to reduce complexity of adding notification
    */
	@Override
	protected void doOnNotification(Map<Integer, Person> personMap, Map<String, PolicyArea> policyAreaMap)
	{
		int personID = userIO.inputInteger("Please enter id of person", 0 , Integer.MAX_VALUE);
		String policyAreaName = userIO.inputString("Please enter name of policy area");
		if(!(personMap.containsKey(Integer.valueOf(personID))))
		{
			userIO.displayMessage("ERROR: Person ID " + personID + " does not exist. Going back to main menu");
		}
		else if(!(policyAreaMap.containsKey(policyAreaName)))
		{
			userIO.displayMessage("ERROR: Policy area " + policyAreaName + " does not exist. Going back to main menu");
		}
		else
		{
			Person person = personMap.get(Integer.valueOf(personID));
			PolicyArea policyArea = policyAreaMap.get(policyAreaName);
			person.subscribeToPolicyArea(policyArea, observerFactory);
            userIO.displayMessage("Notification successfully added");
		}
	}
}
