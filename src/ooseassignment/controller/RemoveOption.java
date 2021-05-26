/************************************************************
 *Name: Kay Men Yap
 *File name: RemoveOption.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.controller;
import java.util.Map;
import ooseassignment.model.*;
import ooseassignment.view.UserIO;
public class RemoveOption extends UserManipulatedOption
{
	public RemoveOption(UserIO userIO)
	{
		super(userIO);
	}

    /*
    performs remove option on person and if person ID entered by user is not present in personMap,
    report back to user or else remove person from personMap
    */
	@Override
	protected void doOnPerson(Map<Integer, Person> personMap)
	{
		Integer personID = Integer.valueOf(userIO.inputInteger("Please enter id of person to remove"
										   , 1, Integer.MAX_VALUE));
		if(personMap.containsKey(personID))
		{
			personMap.remove(personID);
			userIO.displayMessage("Person sucessfully removed");
		}
		else
		{
			userIO.displayMessage("ERROR: Person does not exist");
		}
	}

    /*
    performs remove option on policy area and if policy area name entered by user is not present in
    policyAreaMap, report back to user or if keywords and/or talking points are present in policy area,
    inform the user if they really want to remove then if yes, remove policy area or else go back to main
    menu
    */
	@Override
	protected void doOnPolicyArea(Map<String, PolicyArea> policyAreaMap, Map<String, PolicyArea> keywordMap, Map<String, PolicyArea> talkingPointMap)
	{
		String name = userIO.inputString("Please enter name of policy area to remove");
		if(policyAreaMap.containsKey(name))
		{
			PolicyArea policyArea = policyAreaMap.get(name);
			String keywordString = policyArea.getAllKeywordsString();
			String talkingPointString = policyArea.getAllTalkingPointsString();
			if(keywordString.equals("") && talkingPointString.equals(""))
			{
				policyAreaMap.remove(name);
				userIO.displayMessage("Policy area sucessfully removed");
			}
			else
			{
				int choice = userIO.inputInteger("Warning: Keywords and/or talking points exist in this policy area.\n"
												  + policyArea.getAllKeywordsString() +  "\n" + policyArea.getAllTalkingPointsString()
                                                  + "\nAre you sure? 0 - No, 1 - Yes", 0, 1);
				if(choice == 1)
				{
					policyAreaMap.remove(name);
                    for(String keyword :  policyArea.getKeywordSet())
                    {
                        keywordMap.remove(keyword);
                    }
                    for(String talkingPoint :  policyArea.getTalkingPointSet())
                    {
                        talkingPointMap.remove(talkingPoint);
                    }
					userIO.displayMessage("Policy area sucessfully removed");
				}
				else
				{
					userIO.displayMessage("Removal of policy area cancelled. Returning to main menu.");
				}
			}
		}
		else
		{
			userIO.displayMessage("ERROR: Policy area does not exist");
		}
	}

    /*
    performs remove option on keyword and if policy area name entered by user is not present in
    policyAreaMap, report back to user or if keyword entered is already present, report back to user
    that keyword is already present or else add keyword to policy area specified
    */
	@Override
	protected void doOnKeyword(Map<String, PolicyArea> policyAreaMap, Map<String, PolicyArea> keywordMap)
	{
		String keyword = userIO.inputString("Please enter keyword to remove");
		if(keywordMap.containsKey(keyword))
		{
			PolicyArea policyArea = keywordMap.get(keyword);
			try
			{
				policyArea.removeKeyword(keyword);
				keywordMap.remove(keyword);
				userIO.displayMessage("Keyword sucessfully removed");
			}
			catch(PolicyAreaException e)
			{
				userIO.displayMessage(e.getMessage());
			}
		}
		else
		{
			userIO.displayMessage("ERROR: Keyword does not exist");
		}
	}

    /*
    performs remove option on talking point and if policy area name entered by user is not present in
    policyAreaMap, report back to user or if talking point entered is already present, report back to user
    that talking point is already present or else add talking point to policy area specified
    */
	@Override
	protected void doOnTalkingPoint(Map<String, PolicyArea> policyAreaMap, Map<String, PolicyArea> talkingPointMap)
	{
		String talkingPoint = userIO.inputString("Please enter talking point to remove");
		if(talkingPointMap.containsKey(talkingPoint))
		{
			PolicyArea policyArea = talkingPointMap.get(talkingPoint);
			try
			{
				policyArea.removeTalkingPoint(talkingPoint);
				talkingPointMap.remove(talkingPoint);
				userIO.displayMessage("Talking point sucessfully removed");
			}
			catch(PolicyAreaException e)
			{
				userIO.displayMessage(e.getMessage());
			}
		}
		else
		{
			userIO.displayMessage("ERROR: Keyword does not exist");
		}
	}

    /*
    performs remove option on notificaiton and if policy area name entered by user is not present in
    policyAreaMap or person ID is not present in personMap, report back to user or add notification
    to that policy area through person
    */
	@Override
	protected void doOnNotification(Map<Integer, Person> personMap, Map<String, PolicyArea> policyAreaMap)
	{
		Integer personID = Integer.valueOf(userIO.inputInteger("Please enter id of person", 1, Integer.MAX_VALUE));
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
			PolicyArea policyArea = policyAreaMap.get(policyAreaName);
			try
			{
				policyArea.removeObserver(personID);
				userIO.displayMessage("Notificatiom sucessfully removed");
			}
			catch(PolicyAreaException e)
			{
				userIO.displayMessage(e.getMessage());
			}
		}
	}
}
