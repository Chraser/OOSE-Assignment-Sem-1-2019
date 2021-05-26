package edu.curtin.messaging;
public class SMS
{
	public SMS() {}

	/** Sends an SMS to a given phone number. */
	public void sendSMS(long mobileNumber, String message)
	{
		System.out.println("SMS sent to " + mobileNumber + " : "+ message);
	}
}
