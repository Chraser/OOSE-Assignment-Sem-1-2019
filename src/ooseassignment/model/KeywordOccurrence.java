/************************************************************
 *Name: Kay Men Yap
 *File name: KeywordOccurrence.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.model;
import java.util.Map;

public class KeywordOccurrence
{
	private Map<String, Integer> keywordMap;
	private long timestamp;

	public KeywordOccurrence(Map<String, Integer> keywordMap, long timestamp)
	{
		this.keywordMap = keywordMap;
		this.timestamp = timestamp;
	}

	public Map<String, Integer> getKeywordMap()
	{
		return keywordMap;
	}

    public long getTimestamp()
    {
        return timestamp;
    }

    /*
    checks if timestamp is greater  than currentTime by a difference of greater than Keyword.TIMEINTERVALFORCOUNT
    and if it is true, this object is too old and returns true or else returns false
    */
	public boolean isTooOld(long currentTime)
	{
		return ((currentTime - timestamp) > Keyword.TIMEINTERVALFORCOUNT);
	}

    public boolean equals(Object obj)
    {
        boolean equals = false;
        if(obj instanceof KeywordOccurrence)
        {
            KeywordOccurrence keywordOccurrence = (KeywordOccurrence)obj;
            if(keywordOccurrence.getKeywordMap().equals(keywordMap))
            {
                if(keywordOccurrence.getTimestamp() == timestamp)
                {
                    equals = true;
                }
            }
        }
        return equals;
    }
}
