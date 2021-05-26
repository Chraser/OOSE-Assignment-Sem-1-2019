/************************************************************
 *Name: Kay Men Yap
 *File name: UserIO.java
 *Date last modified: 23/5/2019
 ************************************************************/
package ooseassignment.view;
import java.util.Scanner;
import java.util.InputMismatchException;
public class UserIO
{
	private Scanner scanner;
	private static long VALIDMOBILENUMBER = 400000000;

	public UserIO()
	{
		scanner = new Scanner(System.in);
	}

    /*
    requests for a mobile number and reprompts if a non-long value is entered or
    less than VALIDMOBILENUMBER
    */
	public String inputMobileNumber()
	{
		String promptMessage = "Please enter a mobile phone number";
		String errorMessage = "";
		long mobileNumber = 0;
		while(mobileNumber < VALIDMOBILENUMBER)
		{
			System.out.println(errorMessage + promptMessage);
			while(!(scanner.hasNextLong()))
			{
				scanner.nextLine();
				System.out.println("Only long values are valid");
			}
			mobileNumber = scanner.nextLong();
			errorMessage = "Mobile number must be greater than " + VALIDMOBILENUMBER + "\n";
		}
		return Long.toString(mobileNumber);
	}

    /*
    display promptMessage and request for a valid string which is anything but an empty string
    and reprompts user if empty string entered
    */
	public String inputString(String promptMessage)
	{
		String string = "";
		String errorMessage = "";
        do
        {
            System.out.println(errorMessage + promptMessage);
			string = scanner.nextLine();
			errorMessage = "String can't be empty\n";
        } while (string.equals(""));
		return string;
	}

    /*
    display promptMessage and request for integer input and keeps looping until
    a valid integer within the range of min and max
    */
    public int inputInteger(String promptMessage, int min, int max)
	{
		String errorMessage = "";
		int number = -1;
        do
        {
            try
            {
                System.out.println(errorMessage + promptMessage);
                number = scanner.nextInt();
    			errorMessage = "Integer must be in the range of " + min + " and " + max + "\n";
			}
            catch(InputMismatchException e)
            {
                //flush the scanner after invalid input
                scanner.nextLine();
    			errorMessage = "Only int values are valid\n";
            }
        }while((number < min) || (number > max));

        /*
        flush the scanner so that inputString won't print an error message by not waiting
        for input for its first iteration of loop
        */
        scanner.nextLine();
		return number;
	}

    //displays any message to user by any class that calls it
	public void displayMessage(String message)
	{
		System.out.println(message);
	}
}
