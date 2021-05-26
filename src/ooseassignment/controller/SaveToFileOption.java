/************************************************************
 *Name: Kay Men Yap
 *File name: SaveToFileOption.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.controller;
import java.util.Map;
import ooseassignment.model.*;
import ooseassignment.view.UserIO;
public class SaveToFileOption implements MenuOption
{
	private UserIO userIO;

	public SaveToFileOption(UserIO userIO)
	{
		this.userIO = userIO;
	}

	@Override
	public void doOption(Map<Integer, Person> personMap, Map<String, PolicyArea> policyAreaMap,
						 Map<String, PolicyArea> keywordMap, Map<String, PolicyArea> talkingPointMap)
	{
		userIO.displayMessage("Save to file not yet implemented");
	}
}
