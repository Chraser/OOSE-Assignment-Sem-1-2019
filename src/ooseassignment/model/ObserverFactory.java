/************************************************************
 *Name: Kay Men Yap
 *File name: ObserverFactory.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.model;
import edu.curtin.messaging.*;
import ooseassignment.view.*;
public class ObserverFactory
{
	private SMS sms;
	private FacebookMessenger facebookMessenger;
	private TwitterMessenger twitterMessenger;
	public ObserverFactory(SMS sms, FacebookMessenger facebookMessenger, TwitterMessenger twitterMessenger)
	{
		this.sms = sms;
		this.facebookMessenger = facebookMessenger;
		this.twitterMessenger = twitterMessenger;
	}

    //factory method to make KeywordAddedNotification object
	public KeywordAddedObserver makeKeywordAddedNotification(Person person)
	{
		KeywordAddedObserver observer =  new KeywordAddedNotification(person, sms, facebookMessenger, twitterMessenger);
		return observer;
	}

    //factory method to make KeywordTrendingNotification object
	public KeywordTrendingObserver makeKeywordTrendingNotification(Person person)
	{
		KeywordTrendingObserver observer =  new KeywordTrendingNotification(person, sms, facebookMessenger, twitterMessenger);
		return observer;
	}

    //factory method to make TalkingPointAddedNotification object
	public TalkingPointAddedObserver makeTalkingPointAddedNotification(Person person)
	{
		TalkingPointAddedObserver observer =  new TalkingPointAddedNotification(person, sms, facebookMessenger, twitterMessenger);
		return observer;
	}
}
