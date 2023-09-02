package project2;
/**
 * This class opens and reads file containing color names and their hexvalues
 * and displays name hex and RGB  for hex value that user inputs
 * @author Dimitri Chaber
 * @version 9/11/2017
 * 
 */

import java.io.*;
import java.util.*;

public class ColorConverter {
	/**
	 * main method, opens and reads file gathers user input
	 */
	public static void main(String[] args) throws IllegalArgumentException {
		// date fields
		File test = null;
		// create arraylist to store Color objects
		ColorList list = new ColorList();
		// create scanner to read user input
		Scanner input = new Scanner(System.in);
		String userHex = "";

		// check to see if file location is in argument and terminate program if
		// it is not
		try {
			// create file object from command line argument
			test = new File(args[0]);
		} catch (ArrayIndexOutOfBoundsException w) {
			System.err.println("No file in argument");
			System.exit(1);

		}
		// check if file exists and terminate if it does not
		if (test.exists() == false) {
			System.err.println("File does not exist");
			System.exit(1);

		}
		// create scanner to read from from file
		Scanner colorInfo = null;
		try {
			colorInfo = new Scanner(test);
		} catch (FileNotFoundException e) {

		}

		// read contents of file and add them to arraylist
		do {

			String tempName = colorInfo.nextLine();
			String[] temp = tempName.split(",");
			temp[0] = temp[0].trim();
			temp[1] = temp[1].trim();
			// System.out.println("Name:"+temp[0]);
			// System.out.println("Hex:"+ ""+temp[1]);
			Color testColor = new Color(temp[1], temp[0]);
			list.add(testColor);

		} while (colorInfo.hasNext());
		System.out.println(list);
		// close file scanner
		colorInfo.close();
		
		//mehthod tests
		Color testColor = new Color("#000070");
		list.add(testColor);
		System.out.println(list.indexOf(testColor));
		System.out.println(list.contains(testColor));

		// user input loop
		while (!userHex.equalsIgnoreCase("quit")) {
			// keep asking user to input hex values until they enter "quit"
			System.out.println("Enter a hexadecimal value or type quit to terminate the program:");
			userHex = input.next();
			// print error if user entered invalid hex
			// otherwise print color info
			try {
				Color userColor = list.getColorByHexValue(userHex);
				if (userColor == null) {
					System.out.println(new Color(userHex));
				} else
					System.out.println(userColor);
			} catch (IllegalArgumentException g) {
				System.err.println("Invalid hex value. must be in format #XXXXXX with X between 0 and F.");
			}

		}
		// close input scanner
		input.close();

	}

}
