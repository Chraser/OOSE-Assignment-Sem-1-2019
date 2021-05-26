/************************************************************
 *Name: Kay Men Yap
 *File name: ReadFromFileOption.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.controller;
import java.util.Map;
import ooseassignment.model.*;
import ooseassignment.view.UserIO;
public class ReadFromFileOption implements MenuOption
{
	private UserIO userIO;

	public ReadFromFileOption(UserIO userIO)
	{
		this.userIO = userIO;
	}

	@Override
	public void doOption(Map<Integer, Person> personMap, Map<String, PolicyArea> policyAreaMap,
						 Map<String, PolicyArea> keywordMap, Map<String, PolicyArea> talkingPointMap)
	{
		userIO.displayMessage("Read from file not yet implemented");
	}
}
