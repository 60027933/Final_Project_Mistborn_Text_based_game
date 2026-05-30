package src;
// Import the ArrayList class
//import java.nio.channels.InterruptedByTimeoutException;
import java.util.*;

import src.Events.*;
import src.Enums.*;
import src.Time.calandar;
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
    


    static class player {
        public int health;
        public calandar time;
        public areas location;
        public int dmg;
        public int currency;
        public player(int health, calandar time, areas location, int dmg, int currency){
            this.health = health;
            this.time = time;
            this.location = location;
            this.dmg = dmg;
            this.currency = currency;
        }
        public areaEvent pickEvent(){
            areaEvent returnValue = new areaEvent(areaEvent.event.ENEMY, 
                "A skaa bandit approaches you!", this.location);
            return returnValue;
        }
        
    }
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        calandar time = new calandar(0,0,0);
        int health = 100;
        areas location = areas.TRESTING;
        int dmg = 5;
        int currency = 0;
        player player = new player(health,time,location,dmg,currency);
        
        //ArrayList<String> log = new ArrayList<String>(); // functions: add(), get(), set(), remove(),

        if(checkPlayerUnderstanding(s)){
            beginStory(s);
            player = gameTurn(player,s);
        // at the beginning of the story, you escape from tresting plantation after it is burned down by kelsier.
        // no knowledge of allomancy or anything; as you encounter it it will be explained.
        //enum list inventory dynamic array?
        //from inventory array: select weapon, store that in variable

        // location / map here?
        //each location needs a certain number of related 'filler' events

        }
         // FOR QUICKER TESTS:
        // player = gameTurn(player, s);
        s.close();
    }
    public static boolean checkPlayerUnderstanding(Scanner s){
        boolean returnValue = true;
        printS("Welcome to the MISTBORN ADVENTURE GAME.\n\n");
        boolean understanding = option(s, "Have you read mistborn?", "Yes","No");
        if(!understanding){
            boolean spoilers = option(s, "Are you okay with spoilers to Mistborn?","Yes", "No");
            if(!spoilers) printS("\nGo read Mistborn. It's a fantastic book. This game can wait fr"); returnValue = false;
            if(!understanding && spoilers) {
                printS("Okay so Mistborn is a series of epic fantasy novels by the author Brandon Sanderson and published by Tor Books.\nThe first trilogy, commonly referred to as \"Era One\", was published between 2006 and 2008 and consists of \nThe Final Empire, The Well of Ascension, and The Hero of Ages.");
                pause();pause();

                printS("\nIn Mistborn, 1000 years ago the world was conquered by an immortal leader called the Lord Ruler. The people he now ruled under were split into two classes based off whether they supported him in his conquering: The Skaa, or slaves, which did not; and the Nobility, who did.");
                printS("\nEverything else you need to know should be explained in-game as it happens");
                returnValue = true;
            }   
        }
        else printS("\nGood, because there are spoilers in this game.");
        pause();
        return returnValue;
    }
    public static void beginStory(Scanner s){
        printS("\n\n\nYou are a skaa assigned to work the fields in the Tresting Plantation.");
        pause();
        printS("\nA strange man in a strange cloak showed up yesterday who called himself Kelsier, the survivor of Hathsin. And this morning the manor is burning.");
        pause();
        printS("\nThe Survivor of Hathsin made sure that you had no choice but to flee; when the inquisitors find the burned manor and dead nobility, they will assume the skaa were responsible.");
        pause();
        printS("\nYou choose not to go which the other skaa in hiding, but rather to set out on your own in search of a better life.");
        wait(s);
    }
    public static player gameTurn(player player, Scanner s){
        player.time.addDay(1);
        printS(player.time.printDate());
        //first: is there any immediate time based events that are happening right now? if so do that
        //second: is there any time and location based "Special" events happening right now? if so do that
        //third: 'regular turn'
        regularTurn(s, player);
        //then, recursively call itself until the final event in (assume it's like year three or something)
        if(player.time.getYear() < 3) {
            wait(s);
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
            player = explore(player,s);
        }
        if(turn == turnOptions.TRAVEL) {
            player = travel(player,s);
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
    
    public static player travel(player player, Scanner s){
        //give map, get travel option, add days according to distance, and then 
        // SO FIRST WE WILL JUST ASSUME THAT ALL AREAS in AREAS enum are next to each other, and therefore travellable to
        // except the current location
        int playerLocationInAreas = 100; // default value: something so high that it will never trigger the adding 1

        ArrayList<String> locations = new ArrayList<String>();
        int i = -1; //  counter starts at -1 to offset initial adding
        for(areas a : areas.values()){
            i++;
            if(a != player.location){
                // it is travelable to
                locations.add(a.name());
            }else{
                printS("You are currently at " + areas.values()[i] + "\n");
                playerLocationInAreas = i;
            }
        }
        String[] locationsArray = locations.toArray(new String[0]);

        int optionPicked = options(s,"Travel Locations: ", locationsArray);
        // so if the optionsPicked number is greater then or equal to the player.location in areas.values()[], add 1
        if(optionPicked >= playerLocationInAreas) optionPicked++;
        printS("Your input likely corrosponds to this location: " + areas.values()[optionPicked] + "\n");
        
        if(areas.values()[optionPicked].name().toLowerCase().equals("hathsin")){
            boolean willing = option(s,"Are you sure? No one has ever come back from the pits of hathsin","Yes","No");
            if(willing){
                player.location = areas.values()[optionPicked];
                player.time.addDay(6);
                printS("\nTraveling takes 1 week.");
            }
            else{
                printS("As you debate this, you realize that your day is spent.");
            }
        }
        else{
            boolean willing = option(s,"Are you willing?","Yes","No");
            if(willing){
                player.location = areas.values()[optionPicked];
                player.time.addDay(6);
                printS("\nTraveling takes 1 week.");
            }
            else{
                printS("As you debate this, you realize that your day is spent.");
            }
        }
        return player;
    }
    public static int sleep(int health){
        // So the player sleeps, a day passes, and you gain health.
        printS("You sleep, regaining some health. Health is now 100\n");
        pause();
        return health = 100; // change this so that it can dynamically change?
    }
    public static player explore(player player, Scanner s){
        areaEvent exploreEvent = player.pickEvent(); // eventType, description, enemies
        if(exploreEvent.eventType == areaEvent.event.ENEMY) player = battle(player,exploreEvent,s);
        return player;
    }
    public static void status(player player){
        printS("Today is: ");
        printS(player.time.printDate());
        pause();
        printS("Your health is: \n" + player.health + "\n");
    }
    public static player battle(player player, areaEvent battle, Scanner s){
        printS("\n"+battle.description);
        printS("\n(There will be a proper battle system in the future)\n");
        pause();
        printS("You Fight the " + battle.enemy.name() + " And lose " + battle.healthOfEnemy(battle.enemy) + " Health.");
        player.health -= battle.healthOfEnemy(battle.enemy);
        printS("\nYou gain 5 Boxings (currency)");
        player.currency += 5;
        return player;
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


    
}


