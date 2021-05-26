/************************************************************
 *Name: Kay Men Yap
 *File name: Volunteer.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.model;
import java.util.Map;
public class Volunteer extends Person
{
	public Volunteer(Integer idNum, String name, Map<String, String> contactDetailsMap) throws PersonException
	{
		super(idNum, name, contactDetailsMap);
	}

	@Override
	public boolean equals(Object inObj)
	{
		boolean equals = false;
		if(inObj instanceof Volunteer)
		{
			Volunteer volunteer = (Volunteer)inObj;
			if(super.equals(volunteer))
			{
				equals = true;
			}
		}
		return equals;
	}

	@Override
	public String toString()
	{
		String string = idNum.intValue() + "," + name + ",Volunteer";
		for(String contactDetail : contactDetailsMap.values())
		{
			string = string + "," + contactDetail;
		}
		return string;
	}

    /*
    imports a policy area and observerFactory and uses observerFactory to make observers that would
    notify volunteer for keywords added, keywords trending and talking points added to that policy area
    */
    @Override
    public void subscribeToPolicyArea(PolicyArea policyArea, ObserverFactory observerFactory)
    {
        KeywordAddedObserver keywordAddedObserver = observerFactory.makeKeywordAddedNotification(this);
        policyArea.addKeywordAddedObserver(idNum, keywordAddedObserver);
        KeywordTrendingObserver keywordTrendingObserver = observerFactory.makeKeywordTrendingNotification(this);
        policyArea.addKeywordTrendingObserver(idNum, keywordTrendingObserver);
        TalkingPointAddedObserver talkingPointAddedObserver = observerFactory.makeTalkingPointAddedNotification(this);
        policyArea.addTalkingPointAddedObserver(idNum, talkingPointAddedObserver);
    }
}
