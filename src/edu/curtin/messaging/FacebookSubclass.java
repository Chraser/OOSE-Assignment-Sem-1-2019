/************************************************************
 *Name: Kay Men Yap
 *File name: FacebookSubclass.java
 *Date last modified: 23/5/2019
 ************************************************************/
package edu.curtin.messaging;
import java.util.*;
import ooseassignment.model.KeywordMapCombiner;
import ooseassignment.model.KeywordOccurrence;

public class FacebookSubclass extends FacebookMessenger
{
	private static final long MILLISECONDSTOSECONDS = 1000;
	private Set<String> keywords;
	private KeywordMapCombiner combiner;
	private List<KeywordOccurrence> keywordOccurrenceList;

    public FacebookSubclass(Set<String> keywords, List<KeywordOccurrence> keywordOccurrenceList, KeywordMapCombiner combiner)
	{
        super();
        this.keywords = keywords;
		this.keywordOccurrenceList = keywordOccurrenceList;
		this.combiner = combiner;
	}

    //used by KeywordMapGenerator for generating maps
    public Set<String> getKeywords()
    {
        return keywords;
    }

    //overrides the FacebookMessenger setKeywords so that the keywords can be stored in a classfield
    @Override
    public void setKeywords(Set<String> keywords)
    {
        this.keywords = keywords;
    }


    /*
    stores the map and timestamp supplied into an Object called KeywordOccurrence and
    add that object to the list
    */
    @Override
    protected void keywordsDetected(Map<String, Integer> keywords, long timestamp)
	{
        KeywordOccurrence keywordOccurrence = new KeywordOccurrence(keywords, timestamp);
		keywordOccurrenceList.add(keywordOccurrence);
		long currentTime = System.currentTimeMillis() / MILLISECONDSTOSECONDS;
        removeOldMaps(currentTime);
		generateTotalOccurences(currentTime);
	}

    //remove any maps that are too old as determined by KeywordOccurrence
	private void removeOldMaps(long currentTime)
	{
        Set<KeywordOccurrence> toBeRemovedSet = new HashSet<KeywordOccurrence>();
		for(KeywordOccurrence keywordOccurrence : keywordOccurrenceList)
		{
			if(keywordOccurrence.isTooOld(currentTime))
			{
				toBeRemovedSet.add(keywordOccurrence);
			}
		}
        for(KeywordOccurrence keywordOccurrence : toBeRemovedSet)
		{
			keywordOccurrenceList.remove(keywordOccurrence);
		}
	}

    //compute total occurrences across all keywordOccurrence in the list and pass totalOccurrencesMap to combiner
	private void generateTotalOccurences(long currentTime)
	{
		int totalOccurences;
		int value;
		Map<String, Integer> newMap = new HashMap<String, Integer>();
        Map<String, Integer> mapToBeAdded;
		for(KeywordOccurrence keywordOccurrence: keywordOccurrenceList)
		{
            mapToBeAdded = keywordOccurrence.getKeywordMap();
			for(String keyword : mapToBeAdded.keySet())
			{
				if(newMap.containsKey(keyword))
				{
					totalOccurences = mapToBeAdded.get(keyword).intValue() + newMap.get(keyword).intValue();
					newMap.put(keyword, Integer.valueOf(totalOccurences));
				}
				else
				{
					newMap.put(keyword, mapToBeAdded.get(keyword));
				}
			}
		}
		combiner.setFacebookMap(newMap, currentTime);
	}
}
