// Zhang, Zijian, 15345608, Assignment 2, 159.234
/* To create a salary specifier that reads in name-id-salary combination and
 display them in certain range of salary given by user */

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.text.SimpleDateFormat;

public class Main {

    public static List<LibraryItem> Read_In(String fileName) {
        List<LibraryItem> libraryItems = new ArrayList<>();
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader(fileName));
            try {
                String line;
                while ((line = inputStream.readLine()) != null) {
                    LibraryItem temp;
                    String[] part = line.split(",");
                    if (part[0].equals("Book")) { temp = new Book(line); }
                    else if (part[0].equals("Movie")) { temp = new Movie(line); }
                    else if (part[0].equals("Journal")) { temp = new Journal(line); }
                    else {temp = new LibraryItem();}
                    //LibraryItem temp = ItemSpecifier(line);
                    //temp.Book(line);
                    libraryItems.add(temp);
                }
            } finally {
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libraryItems;
    }

    public static void main(String[] args) {
        List<LibraryItem> dataSet = new ArrayList<>();
        dataSet = Read_In("library.txt");
        String userInput = "";
        int caseNumber = 1;
        String firstInput = "not q"; // this is to indicate when to terminate the entire big loop
        Scanner scan = new Scanner(System.in);
        LibraryItem selectedItem = new LibraryItem();
        int previousCase = 0;
        int error = 1;
        int enteredID = 0;
        for (LibraryItem i : dataSet) { i.PrintRecord(); }

        while(!firstInput.equals("q")){
            switch(caseNumber){

                case 1:
                    System.out.println("\nEnter 'q' to quit, enter 'i' to search by by ID, or enter any other key to search by phrase in title");
                    userInput = scan.nextLine();
                    if (userInput.equals("i")) { caseNumber = 2; }
                    else if (userInput.equals("q")) { firstInput = "q"; }
                    else { caseNumber = 3; }
                    break;

                case 2: // ID search
                    System.out.println("\nEnter ID to start search, or enter 'b' to go back to choose search method");
                    userInput = scan.nextLine();
                    if (userInput.equals("b")) { caseNumber = 1; } // go back to previous level
                    else {
                        try { enteredID = Integer.parseInt(userInput); }
                        catch (NumberFormatException e){ System.out.println("\nPlease enter a number!\n"); break; }
                        for (LibraryItem i : dataSet) { if (i.GetId() == enteredID) { selectedItem = i; error = 0; break; } }
                        if (error == 0) { caseNumber = 4; }
                        else { System.out.println("\nItem not found!\n"); }
                        // jump to ID searched item section
                        // always find the first match item if there are any mistakes
                    }
                    break;

                case 3: // title search
                    for (LibraryItem i : dataSet) { i.SetItemNumber(-2); } // reset item number
                    System.out.println("\nEnter phrase in title to start search, or enter 'b' to go back to chose search method");
                    userInput = scan.nextLine();
                    if (userInput.equals("b")) { caseNumber = 1; break;}
                    int itemNumber = 0;
                    for (LibraryItem i : dataSet) {
                        String lowerTitle = i.GetTitle().toLowerCase();
                        String lowerInput = userInput.toLowerCase();
                        int ndx = lowerTitle.indexOf(lowerInput);
                        if (ndx != -1){
                            itemNumber++;
                            i.SetItemNumber(itemNumber);
                            System.out.println("* " + itemNumber + " -------------------------------------");
                            i.PrintRecord();
                            error = 0;
                        }
                    }
                    if (error == 0) { caseNumber = 9; }
                    else { System.out.println("\nItem not found!\n"); } // print error message
                    break;

                case 4: // display ID searched item
                    System.out.println("\n-------------------------------------");
                    selectedItem.PrintRecord();
                    System.out.println("\nEnter 'i' to search other item by ID, or enter any other key to select this item");
                    userInput = scan.nextLine();
                    if (userInput.equals("i")) { caseNumber = 2; }
                    else {
                        if (selectedItem.GetStatus().equals("available")) { caseNumber = 5; }
                        else if (selectedItem.GetStatus().equals("on loan")) { caseNumber = 7; }
                    }// jump to select item
                    break;

                case 5: // confirm selection
                    System.out.println("\nSelected item is");
                    System.out.println("\n-------------------------------------");
                    selectedItem.PrintRecord();
                    if (selectedItem.GetStatus().equals("available")) { caseNumber = 6; }
                    else { caseNumber = 7; }
                    break;

                case 6: // borrow item
                    System.out.println("\nEnter 'b' to borrow the item, enter 'a' to rate the item, or enter any other key to restart");
                    userInput = scan.nextLine();
                    if (userInput.equals("a")) { caseNumber = 8; previousCase = 5;}
                    else if (userInput.equals("b")) {
                        Calendar date = Calendar.getInstance();
                        date.add(Calendar.DAY_OF_MONTH, selectedItem.GetMaxDay());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                        String strDate = formatter.format(date.getTime());
                        selectedItem.SetDueDate(strDate);
                        System.out.println("\nThe item's due date is " + selectedItem.GetDueDate());
                        selectedItem.SetStatus("on loan");
                        caseNumber = 5;
                    }
                    else { caseNumber = 1; }
                    break;

                case 7: // return item
                    System.out.println("\nEnter 'r' to return the item, enter 'a' to rate the item, or enter any other key to restart");
                    userInput = scan.nextLine();
                    if (userInput.equals("r")) {
                        System.out.println("The item is returned");
                        selectedItem.SetStatus("available");
                        caseNumber = 5;
                    } else if (userInput.equals("a")) { caseNumber = 8; previousCase = 5; } // call rate item function
                    else { caseNumber = 1; }
                    break;

                case 8: // rate item
                    System.out.println("Please enter your rating (0 - 10)");
                    userInput = scan.nextLine();
                    double enteredRate;
                    try { enteredRate = Double.parseDouble(userInput);}
                    catch (NumberFormatException e){ System.out.println("\nPlease enter a number!\n"); break;}
                    if (enteredRate < 0 || enteredRate > 10) { System.out.println("The rate must between 0 and 10"); break; }
                    // check if the score in range
                    selectedItem.AddNumberOfViewer();
                    selectedItem.SetRating(enteredRate);
                    DecimalFormat df = new DecimalFormat("#.##"); // make it to display 2 dp maximum
                    System.out.println("The item's new average rating is " + df.format(selectedItem.GetAverageRating()));
                    caseNumber = previousCase;
                    break;

                case 9:
                    System.out.println("\nEnter item number to select item, or enter any other key to continue searching");
                    userInput = scan.nextLine();
                    try {
                        int selectNumber = Integer.parseInt(userInput);
                        for (LibraryItem i : dataSet) {if (i.GetItemNumber()==selectNumber){selectedItem = i;}}
                        caseNumber = 5;
                    }
                    catch (NumberFormatException e){ caseNumber = 3;}
                    break;

                default:
                    System.out.println("There's an error occurred in the program");
                    firstInput = "q";
                    break;
            }
        }
    }
}
