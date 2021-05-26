/************************************************************
 *Name: Kay Men Yap
 *File name: KeywordMapGenerator.java
 *Date last modified: 23/5/2019
 ************************************************************/
package edu.curtin.messaging;
import java.util.*;
/*
This file contains external code to generate a random number.
Obtained from https://dzone.com/articles/random-number-generation-in-java.
Accessed on 19/5/2019
*/
public class KeywordMapGenerator extends TimerTask
{
    private static final long TIMEINTERVAL = 5000;
	private static final long MILLISECONDSTOSECONDS = 1000;
    private static final int MIN = 0;
    private static final int MAX = 50;
    private Random random;
    private FacebookSubclass facebook;
    private TwitterSubclass twitter;

    public KeywordMapGenerator(Timer timer, FacebookSubclass facebook, TwitterSubclass twitter)
    {
        this.facebook = facebook;
        this.twitter = twitter;
        this.random = new Random();
        timer.scheduleAtFixedRate(this, 0, TIMEINTERVAL);
    }

    //is called by thread after KeywordMapGenerator is scheduled at fixed intervals
    @Override
    public void run()
	{
		long timestamp = System.currentTimeMillis() / MILLISECONDSTOSECONDS;
        Map<String, Integer> facebookMap = generateMap(facebook.getKeywords());
        Map<String, Integer> twitterMap = generateMap(twitter.getKeywords());
		facebook.keywordsDetected(facebookMap, timestamp);
		twitter.keywordsDetected(twitterMap, timestamp);
	}

    //generats a map of keywords as the key and a randomly generated integer as the value
    private Map<String, Integer> generateMap(Set<String> keywords)
    {
        int value;
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(String str : keywords)
		{
			//Obtained from https://dzone.com/articles/random-number-generation-in-java.
			value = random.nextInt(MAX - MIN + 1) + MIN;
			//End of code obtained from https://dzone.com/articles/random-number-generation-in-java.
			map.put(str, Integer.valueOf(value));
		}
        return map;
    }
}
