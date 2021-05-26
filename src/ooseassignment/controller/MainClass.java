/************************************************************
 *Name: Kay Men Yap
 *File name: MainClass.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.controller;
import java.util.*;
import edu.curtin.messaging.*;
import ooseassignment.model.*;
import ooseassignment.view.UserIO;
public class MainClass
{
	public static void main(String[] args)
	{
        Set<String> keywords = new HashSet<String>();
		List<KeywordOccurrence> list = new ArrayList<KeywordOccurrence>();
        Timer timer = new Timer();
		SMS sms = new SMS();

		Map<Integer, Person> personMap = new HashMap<Integer, Person>();
		Map<String, PolicyArea> policyAreaMap = new HashMap<String, PolicyArea>();
		Map<String, PolicyArea> keywordMap = new HashMap<String, PolicyArea>();
		Map<String, PolicyArea> talkingPointMap = new HashMap<String, PolicyArea>();

		UserIO userIO = new UserIO();
        KeywordMapCombiner combiner = new KeywordMapCombiner(new HashMap<String, Integer>(), new HashMap<String, Integer>());
        TwitterSubclass twitterSubclass = new TwitterSubclass(keywords, list, combiner);
        FacebookSubclass facebookSubclass = new FacebookSubclass(keywords, list, combiner);
		ObserverFactory observerFactory = new ObserverFactory(sms, facebookSubclass, twitterSubclass);
        Map<Integer, MenuOption> menuOptionMap = initialiseMenuOptions(userIO, observerFactory);
		Menu menu = new Menu(userIO, menuOptionMap, personMap, policyAreaMap, keywordMap, talkingPointMap, facebookSubclass, twitterSubclass);
        combiner.setMenu(menu);

        //setups monitoring thread that generates maps of keyword occurrences
        KeywordMapGenerator KeywordMapGenerator = new KeywordMapGenerator(timer, facebookSubclass, twitterSubclass);

        //starts the main menu
        menu.run();
        timer.cancel();
	}

    /*
    initialises the MenuOptionMap to be used by Menu and it is initialised here so that a factory class
    is not needed for MenuOption
    */
    public static Map<Integer, MenuOption> initialiseMenuOptions(UserIO userIO, ObserverFactory observerFactory)
    {
        Map<Integer, MenuOption> menuOptionMap = new HashMap<Integer, MenuOption>();
        menuOptionMap.put(Integer.valueOf(1), new AddOption(userIO, observerFactory));
        menuOptionMap.put(Integer.valueOf(2), new ViewOption(userIO));
        menuOptionMap.put(Integer.valueOf(3), new RemoveOption(userIO));
        menuOptionMap.put(Integer.valueOf(4), new ReadFromFileOption(userIO));
        menuOptionMap.put(Integer.valueOf(5), new SaveToFileOption(userIO));
        return menuOptionMap;
    }

}
