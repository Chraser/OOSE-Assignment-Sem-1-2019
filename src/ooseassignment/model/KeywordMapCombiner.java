/************************************************************
 *Name: Kay Men Yap
 *File name: KeywordMapCombiner.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.model;
import java.util.Map;
import java.util.HashMap;
import ooseassignment.controller.Menu;
public class KeywordMapCombiner
{
    private static final long MILLISECONDSTOSECONDS = 1000;
	private Menu menu;
	private Map<String, Integer> facebookMap;
	private Map<String, Integer> twitterMap;

	public KeywordMapCombiner(Map<String, Integer> facebookMap, Map<String, Integer> twitterMap)
	{
        this.menu = null;
		this.facebookMap = facebookMap;
		this.twitterMap = twitterMap;
	}

    /*
    due to a circular dependency between Menu, FacebookMessenger/TwitterMessenger,
    and KeywordMapCombiner, a setter is need and menu classfield is set after constructing
    KeywordMapCombiner object
    */
    public void setMenu(Menu menu)
    {
        this.menu = menu;
    }

    //receives updated map from FacebookMessenger and generates updated combined map
	public void setFacebookMap(Map<String, Integer> facebookMap, long timestamp)
	{
		this.facebookMap = facebookMap;
		generateCombinedMap(timestamp);
	}

    //receives updated map from TwitterMessenger and generates updated combined map
	public void setTwitterMap(Map<String, Integer> twitterMap, long timestamp)
	{
		this.twitterMap = twitterMap;
		generateCombinedMap(timestamp);
	}

    /*
    makes a new map and combines the facebookMap and twitterMap into the new map
    to update keywords to be trending or not and passed the new combined map
    to menu for updating keywords along with timestamp of map
    */
	private void generateCombinedMap(long timestamp)
	{
		Map<String, Integer> combinedMap = new HashMap<String, Integer>();
        combinedMap.putAll(facebookMap);
		combineMap(combinedMap, twitterMap);
		menu.updateKeywords(combinedMap, timestamp);
	}

    /*
    combines the Integer of second map with the Integer in the first map and
    saves the sum in first map
    */
	private void combineMap(Map<String, Integer> firstMap, Map<String, Integer> secondMap)
	{
		int value = 0;
		for(String key : secondMap.keySet())
		{
			if(firstMap.containsKey(key))
			{
				value = firstMap.get(key).intValue() + secondMap.get(key).intValue();
				firstMap.put(key, Integer.valueOf(value));
			}
			else
			{
				firstMap.put(key, Integer.valueOf(value));
			}
		}

	}
}
