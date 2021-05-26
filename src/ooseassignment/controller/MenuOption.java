/************************************************************
 *Name: Kay Men Yap
 *File name: MenuOption.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.controller;
import java.util.Map;
import ooseassignment.model.Person;
import ooseassignment.model.PolicyArea;
public interface MenuOption
{
	public abstract void doOption(Map<Integer, Person> personMap, Map<String, PolicyArea> policyAreaMap,
								  Map<String, PolicyArea> keywordMap, Map<String, PolicyArea> talkingPointMap);
}
