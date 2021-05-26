/************************************************************
 *Name: Kay Men Yap
 *File name: UserManipulatedOption.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.controller;
import java.util.Map;
import ooseassignment.model.*;
import ooseassignment.view.UserIO;
public abstract class UserManipulatedOption implements MenuOption
{
	protected UserIO userIO;

	public UserManipulatedOption(UserIO userIO)
	{
		this.userIO = userIO;
	}

    //requests for subject to be performed by the option and calls appropriate hook method
	public void doOption(Map<Integer, Person> personMap, Map<String, PolicyArea> policyAreaMap,
						 Map<String, PolicyArea> keywordMap, Map<String, PolicyArea> talkingPointMap)
	{
		int subjectForOption = userIO.inputInteger("Select subject to perform menu option on:\n"
												   + "1.Person \n2.Policy Area \n3.Keyword \n"
												   + "4.Talking Point \n5.Notifcation \n6.Go back",
												   1, 6);
		switch(subjectForOption)
		{
			case 1:
				doOnPerson(personMap);
				break;
			case 2:
				doOnPolicyArea(policyAreaMap, keywordMap, talkingPointMap);
				break;
			case 3:
				doOnKeyword(policyAreaMap, keywordMap);
				break;
			case 4:
				doOnTalkingPoint(policyAreaMap, talkingPointMap);
				break;
			case 5:
				doOnNotification(personMap, policyAreaMap);
				break;
		}
	}

    //perform option picked by user on person
	protected abstract void doOnPerson(Map<Integer, Person> personMap);
    //perform option picked by user on policy area
	protected abstract void doOnPolicyArea(Map<String, PolicyArea> policyAreaMap, Map<String, PolicyArea> keywordMap, Map<String, PolicyArea> talkingPointMap);
    //perform option picked by user on keyword
	protected abstract void doOnKeyword(Map<String, PolicyArea> policyAreaMap, Map<String, PolicyArea> keywordMap);
    //perform option picked by user on talking point
	protected abstract void doOnTalkingPoint(Map<String, PolicyArea> policyAreaMap, Map<String, PolicyArea> talkingPointMap);
    //perform option picked by user on notification
	protected abstract void doOnNotification(Map<Integer, Person> personMap, Map<String, PolicyArea> policyAreaMap);

}
