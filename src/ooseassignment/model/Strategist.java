/************************************************************
 *Name: Kay Men Yap
 *File name: Strategist.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.model;
import java.util.Map;
public class Strategist extends Person
{
	public Strategist(Integer idNum, String name, Map<String, String> contactDetailsMap) throws PersonException
	{
		super(idNum, name, contactDetailsMap);
	}

	@Override
	public boolean equals(Object inObj)
	{
		boolean equals = false;
		if(inObj instanceof Strategist)
		{
			Strategist strategist = (Strategist)inObj;
			if(super.equals(strategist))
			{
				equals = true;
			}
		}
		return equals;
	}

	@Override
	public String toString()
	{
		String string = idNum.intValue() + "," + name+ ",Strategist";
		for(String contactDetail : contactDetailsMap.values())
		{
			string = string + "," + contactDetail;
		}
		return string;
	}

    /*
    imports a policy area and observerFactory and uses observerFactory to make observers that would
    notify volunteer for keywords trending and talking points added to that policy area
    */
    @Override
	public void subscribeToPolicyArea(PolicyArea policyArea, ObserverFactory observerFactory)
    {
        KeywordTrendingObserver keywordTrendingObserver = observerFactory.makeKeywordTrendingNotification(this);
        policyArea.addKeywordTrendingObserver(idNum, keywordTrendingObserver);
        TalkingPointAddedObserver talkingPointAddedObserver = observerFactory.makeTalkingPointAddedNotification(this);
        policyArea.addTalkingPointAddedObserver(idNum, talkingPointAddedObserver);
    }
}
