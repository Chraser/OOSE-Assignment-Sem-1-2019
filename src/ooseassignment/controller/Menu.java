/************************************************************
 *Name: Kay Men Yap
 *File name: Menu.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.controller;
import edu.curtin.messaging.*;
import java.util.*;
import ooseassignment.model.*;
import ooseassignment.view.UserIO;
public class Menu
{
	private UserIO userIO;
    private Map<Integer, MenuOption> menuOptionMap;
	private Map<Integer, Person> personMap;
	private Map<String, PolicyArea> policyAreaMap;
    private Map<String, PolicyArea> keywordMap;
    private Map<String, PolicyArea> talkingPointMap;
	private FacebookMessenger facebookMessenger;
	private TwitterMessenger twitterMessenger;

    public Menu(UserIO userIO, Map<Integer, MenuOption> menuOptionMap, Map<Integer, Person> personMap, Map<String,
				PolicyArea> policyAreaMap, Map<String, PolicyArea> keywordMap,
				Map<String, PolicyArea> talkingPointMap, FacebookMessenger facebookMessenger,
				TwitterMessenger twitterMessenger)
    {
        this.userIO = userIO;
        this.menuOptionMap = menuOptionMap;
		this.personMap = personMap;
		this.policyAreaMap = policyAreaMap;
		this.keywordMap = keywordMap;
		this.talkingPointMap = talkingPointMap;
		this.facebookMessenger = facebookMessenger;
		this.twitterMessenger = twitterMessenger;
    }

    //acts as the main menu and request user to pick an option to do then call corresponding MenuOption object
	public void run()
	{
		int option = 0;
		MenuOption menuOption;
		while(option != 6)
		{
			option = userIO.inputInteger("Select option: \n1.Add \n2.View \n3.Remove"
										 + "\n4.Read from file \n5.Save to file \n6.Exit", 1,6);
			if(option != 6)
			{
				menuOption = menuOptionMap.get(Integer.valueOf(option));
				menuOption.doOption(personMap, policyAreaMap, keywordMap, talkingPointMap);
                /*
                updates the keyword set to be monitored and does it regardless of option performed
                since menu doesn't know what option is performed on what subject
                */
				facebookMessenger.setKeywords(keywordMap.keySet());
				twitterMessenger.setKeywords(keywordMap.keySet());
			}
		}
	}

    /*
    receives keywordOccurenceMap and timestamp and passes information to the corresponding keyword
    in its policy area to update keyword's trending status
    */
    public void updateKeywords(Map<String, Integer> keywordOccurenceMap, long timestamp)
    {
		PolicyArea policyArea;
        for(String keyword : keywordMap.keySet())
        {
            policyArea = keywordMap.get(keyword);
            policyArea.updateKeyword(keyword, keywordOccurenceMap.get(keyword).intValue(), timestamp);
        }
    }
}
