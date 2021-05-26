/************************************************************
 *Name: Kay Men Yap
 *File name: KeywordAddedNotification.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.view;
import java.util.Map;
import edu.curtin.messaging.*;
import ooseassignment.model.Person;
import ooseassignment.model.KeywordAddedObserver;
public class KeywordAddedNotification implements KeywordAddedObserver
{
	private Person person;
	private SMS sms;
	private FacebookMessenger facebookMessenger;
	private TwitterMessenger twitterMessenger;

	public KeywordAddedNotification(Person person, SMS sms, FacebookMessenger facebookMessenger, TwitterMessenger twitterMessenger)
	{
		this.person = person;
		this.sms = sms;
		this.facebookMessenger = facebookMessenger;
		this.twitterMessenger = twitterMessenger;
	}

    /*
    notifies the person about keyword added through all contact details that are present in person
    */
	@Override
	public void notify(String message)
	{
		Map<String, String> contactDetails = person.getContactDetailsMap();
        if(contactDetails.containsKey(Person.TYPESOFCONTACT[0]))
		{
			sms.sendSMS(Long.parseLong(contactDetails.get(Person.TYPESOFCONTACT[0])), message);
		}
		if(contactDetails.containsKey(Person.TYPESOFCONTACT[1]))
		{
			facebookMessenger.sendPrivateMessage(contactDetails.get(Person.TYPESOFCONTACT[1]), message);
		}
		if(contactDetails.containsKey(Person.TYPESOFCONTACT[2]))
		{
			twitterMessenger.sendPrivateMessage(contactDetails.get(Person.TYPESOFCONTACT[2]),message);
		}
	}
}
