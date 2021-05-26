/************************************************************
 *Name: Kay Men Yap
 *File name: Candidate.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.model;
import java.util.Map;
public class Candidate extends Person
{
	public Candidate(int idNum, String name, Map<String, String> contactDetailsMap) throws PersonException
	{
		super(idNum, name, contactDetailsMap);
	}

	@Override
	public boolean equals(Object inObj)
	{
		boolean equals = false;
		if(inObj instanceof Strategist)
		{
			Candidate candidate = (Candidate)inObj;
			if(super.equals(candidate))
			{
				equals = true;
			}
		}
		return equals;
	}

	@Override
	public String toString()
	{
		String string = idNum.intValue() + "," + name + ",Candidate";
		for(String contactDetail : contactDetailsMap.values())
		{
			string = string + "," + contactDetail;
		}
		return string;
	}

    /*
    imports a policy area and observerFactory and uses observerFactory to make observers that would
    notify candidate for talking points added to that policy area
    */
    @Override
    public void subscribeToPolicyArea(PolicyArea policyArea, ObserverFactory observerFactory)
    {
        TalkingPointAddedObserver talkingPointAddedObserver = observerFactory.makeTalkingPointAddedNotification(this);
        policyArea.addTalkingPointAddedObserver(idNum, talkingPointAddedObserver);
    }
}
