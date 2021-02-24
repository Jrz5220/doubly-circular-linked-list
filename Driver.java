package lab5Edits;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		DoublyCircularLinkedList<String> list = new DoublyCircularLinkedList<>();
		
		File myFile = new File("C:\\Users\\felix\\eclipse-workspace\\INT2220\\src\\lab5Edits\\cities.txt");
		
		try {
			Scanner scan = new Scanner(myFile);
			System.out.println("Original Cities: ");
		    while(scan.hasNext()) {
		    	String city = scan.nextLine();
		    	System.out.println(city);
		    	list.addToBack(city);
		    }
		    
		    //Add "Canberra" to end of list
		    System.out.println("\nAdd Canberra");
		    list.addToBack("Canberra");
		    displayListItems(list);
		    
		    //Remove "Perth" from list
		    System.out.println("\nRemove Perth");
		    list.removeEntry(4);
		    displayListItems(list);
		    
		    //Replace "Darwin" with "New London"
		    System.out.println("\nReplace Darwin with New London");
		    list.replace(3, "New London");
		    displayListItems(list);
		    
		    //Add "Wagga Wagga" between "Wollongong" and "Newcastle"
		    System.out.println("\nAdd 'Wagga Wagga' between 'Wollongong' and 'Newcastle'");
		    list.addEntry(5, "Wagga Wagga");
		    displayListItems(list);

			// Write the contents of the list into a new file called new_cities.txt
			Path file = Paths.get("new_cities.txt");
			Files.write(file, list, StandardCharsets.UTF_8);
			// Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			System.out.println("\nnew_cities.txt file created");
		    
		    scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}

	} // end main
	
	public static void displayListItems(DoublyCircularLinkedList<String> theList) {
		int index = 0;
		for(String item : theList) {
			index++;
			System.out.println(index + " - " + item);
		}
	}

}
