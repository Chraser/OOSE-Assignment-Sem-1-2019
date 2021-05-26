/************************************************************
 *Name: Kay Men Yap
 *File name: TwitterSubclass.java
 *Date last modified: 23/5/2019
 ************************************************************/
package edu.curtin.messaging;
import java.util.*;
import ooseassignment.model.KeywordMapCombiner;
import ooseassignment.model.KeywordOccurrence;

public class TwitterSubclass extends TwitterMessenger
{
	private static final long MILLISECONDSTOSECONDS = 1000;
	private Set<String> keywords;
	private KeywordMapCombiner combiner;
	private List<KeywordOccurrence> keywordOccurrenceList;

	public TwitterSubclass(Set<String> keywords, List<KeywordOccurrence> keywordOccurrenceList, KeywordMapCombiner combiner)
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

    //overrides the TwitterMessenger setKeywords so that the keywords can be stored in a classfield
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

    //remove maps that are determined too old by keywordOccurrence
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
		int totalOccurrences;
		int value;
		Map<String, Integer> totalOccurrencesMap = new HashMap<String, Integer>();
        Map<String, Integer> mapToBeAdded;
		for(KeywordOccurrence keywordOccurrence: keywordOccurrenceList)
		{
            mapToBeAdded = keywordOccurrence.getKeywordMap();
			for(String keyword : mapToBeAdded.keySet())
			{
				if(totalOccurrencesMap.containsKey(keyword))
				{
					totalOccurrences = mapToBeAdded.get(keyword).intValue() + totalOccurrencesMap.get(keyword).intValue();
					totalOccurrencesMap.put(keyword, Integer.valueOf(totalOccurrences));
				}
				else
				{
					totalOccurrencesMap.put(keyword, mapToBeAdded.get(keyword));
				}
			}
		}
        //pass the totalOccurrences
		combiner.setTwitterMap(totalOccurrencesMap, currentTime);
	}
}
