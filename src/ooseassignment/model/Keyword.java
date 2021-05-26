/************************************************************
 *Name: Kay Men Yap
 *File name: Keyword.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.model;
public class Keyword
{
	private static final int COUNTFORTRENDING = 50;
	public static final long TIMEINTERVALFORCOUNT = 60;
    private static final long WITHHOLDNOTIFCATIONTIMERATIO = 24;
	private String name;
	private long timestampSinceTrending;
	private boolean trending;

	public static Keyword makeKeyword(String name)
	{
		return new Keyword(name);
	}

	public Keyword(String name)
	{
		this.name = name;
		this.timestampSinceTrending = 0;
		this.trending = false;
	}

	public String getName()
	{
		return name;
	}

	/*
	determines if a notification for keyword trending needs to be sent and
	update the keyword to be trending or not.
    this method returns a boolean that indicate whether a notification needs
    to be sent
	*/
	public boolean updateTrending(int occurrence, long occurrenceTimestamp)
	{
		boolean notify = false;
        //check if number of occurrence is enough for keyword to be trending
		if(occurrence >= COUNTFORTRENDING)
		{
            //if keyword is not trending, set it to be trending and notify to true
			if(!(trending))
			{
				trending = true;
				timestampSinceTrending = occurrenceTimestamp;
				notify = true;
			}
			else
			{
                /*if keyword is trending and it is past the time limit to withhold notification,
                  notify becomes true and timestampSinceTrending is updated with occurence timestamp*/
				if((occurrenceTimestamp - timestampSinceTrending) >= (TIMEINTERVALFORCOUNT * WITHHOLDNOTIFCATIONTIMERATIO))
				{
					timestampSinceTrending = occurrenceTimestamp;
					notify = true;
				}
			}
		}
		else
		{
            //set trending to false since occurrence is less than COUNTFORTRENDING
			trending = false;
		}
		return notify;
	}
}
