package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DataProvider {

	private static String[] nameDetails;

	private DataProvider() {
		// Prevent instantiation
	}
	// Made the method static to call easily anywhere in the package

	public static String[] generateEmployeeDetails() {
		String[] firstNames = {"Virat", "Rohit", "Shivam", "Shubhaman", "Suryakumar", "Pavan", "Ashwin", "Ishaan", "Shardul", "Yuzvendra"};
		String[] middleNames = {"A.", "B.", "C.", "D.", "E.", "F.", "G.", "H.", "I.", "J."};
		String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};

		Random rand = new Random();
		int randomIndex1 = rand.nextInt(firstNames.length);
		int randomIndex2 = rand.nextInt(middleNames.length);
		int randomIndex3 = rand.nextInt(lastNames.length);

		// Create a single combination of first, middle, and last name
		String firstName = firstNames[randomIndex1];
		String middleName = middleNames[randomIndex2];
		String lastName = lastNames[randomIndex3];

		int min = 111;  // Minimum value of the range
		int max = 999;  // Maximum value of the range

		// Generate a random integer between min (inclusive) and max (inclusive)
		int randomNumber = rand.nextInt((max - min) + 1) + min;

		String employeeID = "ORN" + randomNumber;

		return new String[]{firstName, middleName, lastName, employeeID};
	}

	
	public static String generateSearchKeyword() {
		String[] keywordList = {"Table", "Bed", "Chair", "Mirror", "Sculpture", "Light", "Lamp"};
		
		Random rand = new Random();
		int randomIndex1 = rand.nextInt(keywordList.length);	

		// Create a single combination of first, middle, and last name
		String dailySearchKeyword = keywordList[randomIndex1];
		
		return dailySearchKeyword;		
	}
	
	

	// Method to get the nameDetails
	public static String[] getNameDetails() {
		if (nameDetails == null) {
			nameDetails = generateEmployeeDetails(); // Generate name only once
		}
		return nameDetails;
	}

	// Optional: Method to reset the nameDetails if needed
	public static void resetNameDetails() {
		nameDetails = null;
	}

	public static String getCurrentTimeStamp() {
		// Get the current date and time for Dynamic File Name 
		LocalDateTime currentDateTime = LocalDateTime.now();

		// Define the date-time format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
		String currentTimeStamp = currentDateTime.format(formatter);
		return currentTimeStamp;
	}

	public static String getCurrentDate() {
		// Get the current date and time for Dynamic File Name 
		LocalDateTime currentDateTime = LocalDateTime.now();

		// Define the date-time format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMuuuu");
		String currentTimeStamp = currentDateTime.format(formatter);
		return currentTimeStamp;
	}

	public static int getIntValue(int min, int max) {
		
		// Generate random number between min and max (inclusive)
		int randomNumber = (int)(Math.random() * (max - min + 1)) + min;

		return randomNumber;
	}
	
	public static int extractValue(String priceString) {
		// Remove all non-numeric characters except for the decimal point and commas
        String numericString = priceString.replaceAll("[^\\d.,]", "");

        // Remove the comma
        numericString = numericString.replace(",", "");

        // Check if there is a decimal and only take the part before the decimal
        if (numericString.contains(".")) {
            numericString = numericString.substring(0, numericString.indexOf("."));
        }

        int numericInt = Integer.parseInt(numericString);
        
        return numericInt;
    }

}