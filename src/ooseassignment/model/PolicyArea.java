/************************************************************
 *Name: Kay Men Yap
 *File name: PolicyArea.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.model;
import java.util.*;

public class PolicyArea
{
	private String name;
	private Map<String, Keyword> keywordMap;
	private Set<String> talkingPointSet;
	private Map<Integer, KeywordAddedObserver> keywordAddedObserverMap;
	private Map<Integer, KeywordTrendingObserver> keywordTrendingObserverMap;
	private Map<Integer, TalkingPointAddedObserver> talkingPointAddedObserverMap;

    //factory method to construct a PolicyArea object
	public static PolicyArea makePolicyArea(String name) throws PolicyAreaException
	{
		Map<String, Keyword> keywordMap = new HashMap<>();
		Set<String> talkingPointSet = new HashSet<>();
		Map<Integer, KeywordAddedObserver> keywordAddedObserverMap = new HashMap<>();
		Map<Integer, KeywordTrendingObserver> keywordTrendingObserverMap = new HashMap<>();
		Map<Integer, TalkingPointAddedObserver> talkingPointAddedObserverMap = new HashMap<>();
		PolicyArea policyArea = new PolicyArea(name, keywordMap, talkingPointSet, keywordAddedObserverMap,
										       keywordTrendingObserverMap, talkingPointAddedObserverMap);
		return policyArea;
	}

	public PolicyArea(String name, Map<String, Keyword> keywordMap, Set<String> talkingPointSet,
					  Map<Integer, KeywordAddedObserver> keywordAddedObserverMap,
					  Map<Integer, KeywordTrendingObserver> keywordTrendingObserverMap,
					  Map<Integer, TalkingPointAddedObserver> talkingPointAddedObserverMap) throws PolicyAreaException
	{
		if(name.equals(""))
		{
			throw new PolicyAreaException("Name can't be empty");
		}
		this.name = name;
		this.keywordMap = keywordMap;
		this.talkingPointSet = talkingPointSet;
		this.keywordAddedObserverMap = keywordAddedObserverMap;
		this.keywordTrendingObserverMap = keywordTrendingObserverMap;
		this.talkingPointAddedObserverMap = talkingPointAddedObserverMap;
	}

	public String getName()
	{
		return name;
	}

    public Set<String> getKeywordSet()
    {
        return keywordMap.keySet();
    }

    //returns a string representation of all keywords
	public String getAllKeywordsString()
	{
		String string = "";
        int count = 1;
		if(!(keywordMap.isEmpty()))
		{
			string = string + "Keywords: ";
			for(String keyword : keywordMap.keySet())
			{
				string = string + keyword;
				if(count != keywordMap.keySet().size())
				{
					string = string + ", ";
				}
                count++;
			}
		}
		return string;
	}

    public Set<String> getTalkingPointSet()
    {
        return talkingPointSet;
    }

    //returns a string representation of all talking points
	public String getAllTalkingPointsString()
	{
		String string = "";
        int count = 1;
		if(!(talkingPointSet.isEmpty()))
		{
			string = string + "Talking Points: ";
			for(String talkingPoint : talkingPointSet)
			{
				string = string + talkingPoint;
				if(count != talkingPointSet.size())
				{
					string = string + ", ";
				}
				count++;
			}
		}
		return string;
	}

    //returns a string representation of all notifications
	public String getAllNotificationString()
	{
		String string = "";
		int count = 1;
        if(!(talkingPointAddedObserverMap.isEmpty()))
        {
            string = string + "Notifications of " + name + " are sent to: ";
    		for(Integer personId : talkingPointAddedObserverMap.keySet())
    		{
    			string = string + personId.intValue() + " - "  + name;
    			if(count != talkingPointAddedObserverMap.keySet().size())
    			{
    				string = string + ",";
    			}
    			count++;
    		}
        }
		return string;
	}

    /*
    adds the keywordName if the keywordName does not already exist in policy area
    by making a new Keyword object and add it to the keywordMap or
    if keywordName already exist, PolicyAreaException will be thrown
    */
	public void addKeyword(String keywordName) throws PolicyAreaException
	{
		if(keywordMap.containsKey(keywordName))
		{
			throw new PolicyAreaException("Keyword already exist");
		}
		Keyword keyword = Keyword.makeKeyword(keywordName);
		keywordMap.put(keywordName, keyword);
		notifyKeywordAddedObserver(keywordName);
	}

    //removes keyword if exists in policy area or else fails
	public void removeKeyword(String keywordName) throws PolicyAreaException
	{
		if(!(keywordMap.containsKey(keywordName)))
		{
			throw new PolicyAreaException("Keyword does not exist");
		}
		keywordMap.remove(keywordName);
	}

    /*
    updates the keyword's status of trending or not with occurrence and timestamp
    keyword trending observers are notified if keyword.updateTrending returns true
    */
    public void updateKeyword(String keywordName, int occurrence, long timestamp)
    {
        Keyword keyword = keywordMap.get(keywordName);
        boolean sendNotification = keyword.updateTrending(occurrence, timestamp);
        if(sendNotification)
        {
            notifyKeywordTrendingObserver(keywordName + " is trending");
        }
    }

    //adds talking point if not already in policy area or else fails
	public void addTalkingPoint(String talkingPoint) throws PolicyAreaException
	{
		if(talkingPointSet.contains(talkingPoint))
		{
			throw new PolicyAreaException("Talking point already exist");
		}
		talkingPointSet.add(talkingPoint);
		notifyTalkingPointAddedObserver(talkingPoint);
	}

    //removes the talking point if it exists in policy area or else fails
	public void removeTalkingPoint(String talkingPoint) throws PolicyAreaException
	{
		if(!(talkingPointSet.contains(talkingPoint)))
		{
			throw new PolicyAreaException("Talking point does not exist");
		}
		talkingPointSet.remove(talkingPoint);
	}

    /*
    returns a string representation of name and all keywords, talking points
    and notification in policy area.
    all notifications is found using talkingPointAddedObserverMap since
    this map would have mapping to all personID that are subscribed to this policy
    area
    */
    public String toString()
    {
        String string = name;
        Set<String> keywordSet = keywordMap.keySet();
        int count = 1;
        if(!(keywordSet.isEmpty()))
        {
            string = string + "\n" + getAllKeywordsString();
        }
        if(!(talkingPointSet.isEmpty()))
        {
            string = string + "\n" + getAllTalkingPointsString();
        }
        if(!(talkingPointAddedObserverMap.isEmpty()))
        {
            string = string + "\n" + getAllNotificationString();
        }
        return string;
    }

    /*
    adds KeywordAddedObserver that is mapped to a personID and if personID already exists,
    the mapping is replaced with new value which is the same value
    */
	public void addKeywordAddedObserver(Integer personID, KeywordAddedObserver observer)
	{
		keywordAddedObserverMap.put(personID, observer);
	}

    /*
    adds KeywordTrendingObserver that is mapped to a personID and if personID already exists,
    the mapping is replaced with new value which is the same value
    */
	public void addKeywordTrendingObserver(Integer personID, KeywordTrendingObserver observer)
	{
		keywordTrendingObserverMap.put(personID, observer);
	}

    /*
    adds TalkingPointAddedObserver that is mapped to a personID and if personID already exists,
    the mapping is replaced with new value which is the same value
    */
	public void addTalkingPointAddedObserver(Integer personID, TalkingPointAddedObserver observer)
	{
		talkingPointAddedObserverMap.put(personID, observer);
	}

    /*
    removes observer if a mapping with personID is present and remove action is performed on
    all observer map and remove on a mapping that does not exist just does nothing so
    no need to check for present mapping
    */
	public void removeObserver(Integer personID) throws PolicyAreaException
	{
		//check if person has observer in this policy area
		if(!(keywordAddedObserverMap.containsKey(personID)) && !(keywordTrendingObserverMap.containsKey(personID))
		   && !(talkingPointAddedObserverMap.containsKey(personID)))
	    {
			throw new PolicyAreaException("Person has no observer in policy area " + name);
		}
		else
		{
			keywordAddedObserverMap.remove(personID);
			keywordTrendingObserverMap.remove(personID);
			talkingPointAddedObserverMap.remove(personID);
		}
	}

    //notifies observer with message passed to observer
    private void notifyKeywordAddedObserver(String keywordName)
    {
        for(KeywordAddedObserver observer : keywordAddedObserverMap.values())
        {
            observer.notify("Keyword '" + keywordName + "' is added to " + name);
        }
    }

    //notifies observer with message passed to observer
    private void notifyKeywordTrendingObserver(String keywordName)
    {
        for(KeywordTrendingObserver observer : keywordTrendingObserverMap.values())
        {
            observer.notify("Keyword '" + keywordName + "' is trending ");
        }
    }

    //notifies observer with message passed to observer
    private void notifyTalkingPointAddedObserver(String talkingPoint)
    {
        for(TalkingPointAddedObserver observer : talkingPointAddedObserverMap.values())
        {
            observer.notify("Talking point '" + talkingPoint + "' is added to " + name);
        }
    }
}
