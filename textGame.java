import java.util.*;
import java.util.ArrayList; // Import the ArrayList class

// enum inventory objs;
class textGame {
    //ENUMS:
    enum turnType {
        IMMEDIATE,
        SPECIAL,
        REGULAR
    }
    enum turnOptions {
        TRAVEL,
        SLEEP,
        EXPLORE
    }
    public static void main(String[] args) {
        int year = 0;
        int month = 0;
        int day = 0;
        ArrayList<String> log = new ArrayList<String>(); // functions: add(), get(), set(), remove(),
        
        //enum list inventory dynamic array?
        //from inventory array: select weapon, store that in variable

        // location / map here?
        //each location needs a certain number of related 'filler' events
    }

    public static void gameTurn(){
        //first: is there any immediate time based events that are happening right now? if so do that
        //second: is there any time and location based "Special" events happening right now? if so do that
        //third: 'regular turn'
    }
    public static void clear(){
        // clear the screen.
    }
    public static void regularTurn(){
        // give options to player,
        // travel, sleep, explore
    
        // give options function

        //      travel: select "travel event", travel to selected location
        //      sleep: gain health, stamina
        //      explore: local "filler" events

    }
    public static turnOptions giveTurnOptions(){ // REGULAR TURN
        turnOptions optionPicked = turnOptions.SLEEP;
            // clear screen, display log, and give options,
            // get input
            // clean input
        
        return optionPicked;
    }
    



    public static String getInput(){
        Scanner scanner = new Scanner(System.in);
        String stringInput = scanner.next();
        scanner.close();
        return stringInput;
    }
    public static String cleanInput(String input, boolean oneWord) {
        // clean input: lowercase it, remove extra spaces if OneWord
        input = input.toLowerCase();
        if(oneWord) input = input.replaceAll("\\s", "");
        return input;
    }

}


