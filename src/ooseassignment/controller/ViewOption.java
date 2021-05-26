/************************************************************
 *Name: Kay Men Yap
 *File name: ViewOption.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.controller;
import java.util.Map;
import ooseassignment.model.*;
import ooseassignment.view.UserIO;
public class ViewOption extends UserManipulatedOption
{

	public ViewOption(UserIO userIO)
	{
		super(userIO);
	}

    /*
    display all values in personMap if any mapping is present in personMap
    or else report to user that nothing has been entered for person
    */
	@Override
	protected void doOnPerson(Map<Integer, Person> personMap)
	{
		String viewMessage = "";
		int count = 1;
		if(personMap.isEmpty())
		{
			viewMessage = "ERROR: No person has been added yet. Going back to main menu";
		}
		else
		{
			for(Person person : personMap.values())
			{
				viewMessage = viewMessage + person.toString();
				if(count != personMap.values().size())
				{
					viewMessage = viewMessage + "\n";
				}
				count++;
			}
		}
		userIO.displayMessage(viewMessage);
	}

    /*
    display all policy area in policyAreaMap if any mapping is present in policyAreaMap
    or else report to user that nothing has been entered for policy areas
    */
	@Override
	protected void doOnPolicyArea(Map<String, PolicyArea> policyAreaMap, Map<String, PolicyArea> keywordMap, Map<String, PolicyArea> talkingPointMap)
	{
		String viewMessage = "";
		int count = 1;
		if(policyAreaMap.isEmpty())
		{
			viewMessage = "ERROR: No policy areas has been added yet. Going back to main menu";
		}
		else
		{
			for(PolicyArea policyArea : policyAreaMap.values())
			{
				viewMessage = viewMessage + policyArea.toString();
				if(count != policyAreaMap.values().size())
				{
					viewMessage = viewMessage + "\n";
				}
				count++;
			}
		}
		userIO.displayMessage(viewMessage);
	}

    /*
    display all keywords and policy area name in keywordMap if any mapping is present in keywordMap
    or else report to user that nothing has been entered for keywords
    */
	@Override
	protected void doOnKeyword(Map<String, PolicyArea> policyAreaMap, Map<String, PolicyArea> keywordMap)
	{
		String viewMessage = "";
		int count = 1;
		if(keywordMap.isEmpty())
		{
			viewMessage = "ERROR: 0 keywords has been added yet. Going back to main menu";
		}
		else
		{
			for(String keyword : keywordMap.keySet())
			{
				viewMessage = viewMessage + keyword + " - " + keywordMap.get(keyword).getName();
				if(count != keywordMap.keySet().size())
				{
					viewMessage = viewMessage + "\n";
				}
				count++;
			}
		}
		userIO.displayMessage(viewMessage);
	}

    /*
    display all talking point and policy area name in talkinPointMap if any mapping is present in
    talkingPointMap or else report to user that nothing has been entered for talking points
    */
	@Override
	protected void doOnTalkingPoint(Map<String, PolicyArea> policyAreaMap, Map<String, PolicyArea> talkingPointMap)
	{
		String viewMessage = "";
		int count = 1;
		if(talkingPointMap.isEmpty())
		{
			viewMessage = "ERROR: 0 talking points has been added yet. Going back to main menu";
		}
		else
		{
			for(String talkingPoint: talkingPointMap.keySet())
			{
				viewMessage = viewMessage + talkingPoint + " - " + talkingPointMap.get(talkingPoint).getName();
				if(count != talkingPointMap.keySet().size())
				{
					viewMessage = viewMessage + "\n";
				}
				count++;
			}
		}
		userIO.displayMessage(viewMessage);
	}

    /*
    display all notifications by showing all person to policy area mapping if personMap and policyAreaMap are not empty
    and there are notifications present in the program or else report to user that nothing has been entered for person or policy area
    */
	@Override
	protected void doOnNotification(Map<Integer, Person> personMap, Map<String, PolicyArea> policyAreaMap)
	{
		String viewMessage = "";
		int count = 1;
		boolean empty = true;
		if(personMap.isEmpty())
		{
			viewMessage = "ERROR: 0 person has been added yet. Going back to main menu";
		}
		else if(policyAreaMap.isEmpty())
		{
			viewMessage = "ERROR: 0 policy areas has been added yet. Going back to main menu";
		}
		else
		{
			for(PolicyArea policyArea : policyAreaMap.values())
			{
				viewMessage = viewMessage + policyArea.getAllNotificationString();
			}
		}
		if(viewMessage.equals(""))
		{
			viewMessage = "ERROR: No notification has been added yet. Going back to main menu";
		}
		userIO.displayMessage(viewMessage);
	}
}
