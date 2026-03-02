// Import the ArrayList class
import java.nio.channels.InterruptedByTimeoutException;
import java.util.*;

// enum inventory objs;
class textGame {
    //ENUMS:
    enum turnType {
        IMMEDIATE, // non area specific, time-based events
        SPECIAL, // area specific, time-based events
        REGULAR // area specific, non-time based events
    }
    enum turnOptions {
        TRAVEL,
        SLEEP,
        EXPLORE
    }
    enum areas {
        // CENTRAL DOMINANCE:
        LUTHADEL, // culteral and political center of final empire. Major nobles have keeps there. "City of a thousand spires"
        FELLIS, // prosperous, clean suburban city near luthadel, for nobles who do not wish to live in luthadel
        TRESTING, // skaa plantation. burned to the ground at the start of the series.
        HATHSIN // pits of hathsin, skaa labour camp where only one person has survived (kelsier).
        //'skaa climb down suffocatingly narrow gorges and reach into crystal-lines niches to find geodes that contain atium.'
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int year = 0;
        int month = 0;
        int day = 1;
        ArrayList<String> log = new ArrayList<String>(); // functions: add(), get(), set(), remove(),
        areas location = areas.TRESTING;
        if(checkPlayerUnderstanding(s)){
            beginStory(s);
            gameTurn(year,month,day);
        // at the beginning of the story, you escape from tresting plantation after it is burned down by kelsier.
        // no knowledge of allomancy or anything; as you encounter it it will be explained.

        //enum list inventory dynamic array?
        //from inventory array: select weapon, store that in variable

        // location / map here?
        //each location needs a certain number of related 'filler' events



        
    
        }
        s.close();
    }
    public static boolean checkPlayerUnderstanding(Scanner s){
        boolean returnValue = true;
        printS("Welcome to the MISTBORN ADVENTURE GAME.\n\n");
        boolean understanding = option(s, "Have you read mistborn?", "Yes","No");
        if(!understanding){
            boolean spoilers = option(s, "Are you okay with spoilers to Mistborn?","Yes", "No");
            if(!spoilers) System.out.println("Go read Mistborn it good. This game can wait fr"); returnValue = false;
            if(!understanding && spoilers) {
                printS("Okay so Mistborn is a series of epic fantasy novels by the American author Brandon Sanderson and published by Tor Books.\nThe first trilogy, commonly referred to as \"Era One\", was published between 2006 and 2008 and consists of \nThe Final Empire, The Well of Ascension, and The Hero of Ages.");
                System.out.println("");
            
            }   
        }
        else printS("\nGood, because there is spoilers in this game.");
        pause();
        return returnValue;
    }
    public static void beginStory(Scanner s){
        printS("\nYou are a skaa working the fields in the Tresting Plantation. It is burned down by kelsier, and then you escape.");
        pause();
        printS("\nYou choose not to go which the other skaa, but rather to set out on your own in search of a better life. You have a sickly build.");
        wait(s);
    }
    public static void gameTurn(int year, int month, int day){
        printDate(year,month,day);
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
    



    public static String getInput(Scanner s){
        String stringInput = s.nextLine();
        return stringInput;
    }

    public static String cleanText(String input, boolean oneWord) {
        // clean input: lowercase it, remove extra spaces if OneWord
        input = input.toLowerCase();
        if(oneWord) input = input.replaceAll("\\s", "");
        return input;
    }
    public static boolean option(Scanner s, String prompt, String expectedValue, String negatoryValue){ // SINGLE OPTION for true / false type questions
        printS(String.format("%s (%s, %s)\n",prompt,expectedValue,negatoryValue));
        // clean the text for comparisons
        expectedValue = cleanText(expectedValue, false);
        negatoryValue = cleanText(negatoryValue, false);
        
        boolean returnValue;
        while(true) {
            System.out.print(">>> ");
            String input = cleanText(getInput(s),false);

            if(input.equals(expectedValue)) {
                returnValue = true;
                break;
            }
            if(input.equals(negatoryValue)){
                returnValue = false;
                break;
            }
        }
        return returnValue;
    }
    public static void printS(String text){
        //print slow
        for(int i = 0; i < text.length(); i++){
            try{
                System.out.print(text.charAt(i));
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
                // best practice somehow?
                Thread.currentThread().interrupt();
            }
        }
    }
    public static void pause(){
        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted.");
            Thread.currentThread().interrupt();
        }
    }
    public static void wait(Scanner s){
        // wait for player to hit enter
        System.out.print("\n(enter)");
        s.nextLine();
    }
    public static void printDate(int year, int month, int day){
        String[] months = {"January", "February","March","April","May","June","July","August","September","October","November","December"};
        System.out.print(String.format("%s %d of year %d",months[month],day,year));
    }
    public static void addDay(int year, int month, int day){

    }
}


