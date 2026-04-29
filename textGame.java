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
        EXPLORE,
        STATUS
    }
    enum areas {
        // CENTRAL DOMINANCE:
        LUTHADEL, // culteral and political center of final empire. Major nobles have keeps there. "City of a thousand spires"
        FELLIS, // prosperous, clean suburban city near luthadel, for nobles who do not wish to live in luthadel
        TRESTING, // skaa plantation. burned to the ground at the start of the series.
        HATHSIN // pits of hathsin, skaa labour camp where only one person has survived (kelsier).
        //'skaa climb down suffocatingly narrow gorges and reach into crystal-lines niches to find geodes that contain atium.'
    }
    static class calandar {
        int year, month, day;
        public calandar(int year, int month, int day){
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }
    static class player {
        int health;
        calandar time;
        areas location;
        public player(int health, calandar time, areas location){
            this.health = health;
            this.time = time;
            this.location = location;
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        calandar time = new calandar(0,0,0);
        int health = 100;
        areas location = areas.TRESTING;
        
        player player = new player(health,time,location);
        
        ArrayList<String> log = new ArrayList<String>(); // functions: add(), get(), set(), remove(),

        /*if(checkPlayerUnderstanding(s)){
            beginStory(s);
            gameTurn(time,s,health);
        // at the beginning of the story, you escape from tresting plantation after it is burned down by kelsier.
        // no knowledge of allomancy or anything; as you encounter it it will be explained.

        //enum list inventory dynamic array?
        //from inventory array: select weapon, store that in variable

        // location / map here?
        //each location needs a certain number of related 'filler' events

        }
        */ // FOR QUICKER TESTS:
        player = gameTurn(player, s);
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
                printS("Okay so Mistborn is a series of epic fantasy novels by the author Brandon Sanderson and published by Tor Books.\nThe first trilogy, commonly referred to as \"Era One\", was published between 2006 and 2008 and consists of \nThe Final Empire, The Well of Ascension, and The Hero of Ages.");
                System.out.println("");
            
            }   
        }
        else printS("\nGood, because there are spoilers in this game.");
        pause();
        return returnValue;
    }
    public static void beginStory(Scanner s){
        printS("\nYou are a skaa working the fields in the Tresting Plantation. You decide to escape after the plantation is burned.");
        pause();
        printS("\nYou choose not to go which the other skaa, but rather to set out on your own in search of a better life. You have a sickly build.");
        wait(s);
    }
    public static player gameTurn(player player, Scanner s){
        player.time = addDay(player.time, 1);
        printDate(player.time);
        //first: is there any immediate time based events that are happening right now? if so do that
        //second: is there any time and location based "Special" events happening right now? if so do that
        //third: 'regular turn'
        regularTurn(s, player);
        //then, recursively call itself until the final event in (assume it's like year three or something)
        if(player.time.year < 3) {
            player = gameTurn(player, s);
        }
        return player;
    }
    public static void clear(){
        // clear the screen.
    }
    public static player regularTurn(Scanner s, player player){
        // give options to player,
        // travel, sleep, explore
        turnOptions turn = giveTurnOptions(s);
        if(turn == turnOptions.SLEEP) {
            player.health = sleep(player.health);
        }
        if(turn == turnOptions.EXPLORE){
            player.health = explore(player.health);
        }
        if(turn == turnOptions.TRAVEL) {
            player.location = travel(player,s);
        }
        if(turn == turnOptions.STATUS) {
            status(player);
            player = regularTurn(s,player);
        }
        return player;
    }

    public static turnOptions giveTurnOptions(Scanner s){ // REGULAR TURN
        turnOptions optionPicked = turnOptions.SLEEP;
        String[] options = {"Travel", "Sleep", "Explore", "Status"};
        int optionChose = options(s,"You can: ", options);
        switch(optionChose){
            case 0:
                optionPicked = turnOptions.TRAVEL;
                break;
            case 1:
                optionPicked = turnOptions.SLEEP;
                break;
            case 2:
                optionPicked = turnOptions.EXPLORE;
                break;
            case 3:
                optionPicked = turnOptions.STATUS;
                break;
        }
        return optionPicked;
    }
    
    public static areas travel(player player, Scanner s){
        //give map, get travel option, add days according to distance, and then 
        // SO FIRST WE WILL JUST ASSUME THAT ALL AREAS in AREAS enum are next to each other, and therefore travellable to
        // except the current location
        ArrayList<String> locations = new ArrayList<String>();
        for(areas a : areas.values()){
            if(a != player.location){
                // it is travelable to
                locations.add(a.name());
            }
        }
        String[] locationsArray = locations.toArray(new String[0]);

        int optionPicked = options(s,"Locations: ", locationsArray);

        // interpret this value now
        //Or print it for now I guess
        System.out.println("You chose " + optionPicked + ". This probably corrosponds to this location: " + areas.values()[optionPicked]);
        return player.location;
    }
    public static int sleep(int health){
        // So the player sleeps, a day passes, and you gain health.
        printS("You sleep, regaining some health. Health is now 100\n");
        return health = 100; // change so that it can dynamically change?
    }
    public static int explore(int health){
        
        return health;
    }
    public static void status(player player){
        printS("Today is: ");
        printDate(player.time);
        printS("Your health is: \n" + player.health + "\n");
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

    public static int options(Scanner s, String prompt, String[] values){ // MULTIPLE OPTION 
        printS(prompt + " (");
        for(int i = 0; i < values.length-1; i++) printS(String.format("%s, ", (values[i].toString())));
        printS(String.format("%s)\n",values[values.length-1].toString()));
        // clean the text for comparisons
        for(int z = 0; z < values.length; z++) values[z] = cleanText(values[z],true);
        
        int returnValue = -1; // 
        boolean breakLoop = false;
        while(!breakLoop) {
            System.out.print(">>> ");
            String input = cleanText(getInput(s),false);
            for(int i = 0; i < values.length; i++) {
                if(input.equals(values[i].toString())){
                    returnValue = i;
                    breakLoop = true;
                }
            }
        }
        return returnValue; // 
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
    public static void printDate(calandar time){
        String[] months = {"January", "February","March","April","May","June","July","August","September","October","November","December"};
        printS(String.format("\n%s %d of year %d\n",months[time.month],time.day,time.year));
    }
    public static calandar addDay(calandar time, int add){
        String[] months = {"January", "February","March","April","May","June","July","August","September","October","November","December"};
        
        ArrayList<String> days31 = new ArrayList<String>();
        days31.add("January"); days31.add("March");days31.add("May");days31.add("July"); days31.add("October"); days31.add("December");
        ArrayList<String> days30 = new ArrayList<String>();
        days30.add("April"); days30.add("June"); days30.add("September"); days30.add("November");
        // So keep track of days of the month, flip months accordingly
        time.day += 1;
        // february first
        switch(time.day){
            case 28:
                if(time.month == 1){
                    time.day = 1;
                    time.month++;
                     // february
                }
                break;
            case 30:
                for(int i = 0; i < days30.size(); i++){
                    if(months[time.day].equals(days30.get(i))){
                        time.month++;
                        time.day = 1;
                    }
                }
                break;
            case 31:
                for(int i = 0; i < days31.size(); i++){
                    if(months[time.day].equals(days31.get(i))){
                        if(time.month == 11) { // december, last month of year
                            time.month = 0;
                            time.day = 1;
                            time.year += 1;
                        }
                        else{
                            time.month++;
                            time.day = 1;
                        }
                    }
                }
        }
        return time;
    }
}


