package src;
// Import the ArrayList class
//import java.nio.channels.InterruptedByTimeoutException;
import java.util.*;
import src.Events.*;
import src.Enums.*;
import src.Time.calandar;
import src.Player.*;
import src.Text.*;
import src.Input.*;

// enum inventory objs;
class textGame {


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        calandar time = new calandar(0,0,0);
        int health = 100;
        areas location = areas.TRESTING;
        int dmg = 5;
        int currency = 0;
        float defense = 0f;
        float evasion = 0.1f;
        float accuracy = 0.8f;

        player player = new player(health,time,location,dmg,defense,evasion,accuracy,currency);
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
        
        print.s("Welcome to the MISTBORN ADVENTURE GAME.\n\n");
        boolean understanding = input.give_option(s, "Have you read mistborn?", "Yes","No",true);
        if(!understanding){
            boolean spoilers = input.give_option(s, "Are you okay with spoilers to Mistborn?","Yes", "No",true);
            if(!spoilers){ print.s("\nGo read Mistborn. It's a fantastic book. This game can wait fr"); returnValue = false;}
            if(!understanding && spoilers) {
                print.s("Alright. I'm about to get you up to speed on the basic lore, so feel free to opt out now:");
                if(!input.give_option(s,"\n( Yes [opt out], No [continue the game] )","Yes","No",false)){
                    print.pause();print.pause();

                    print.s(30,"\n1000 years ago the world was conquered by an immortal leader called the Lord Ruler. The people in his kingdom were split into two classes based off whether they supported him: The Skaa, or slaves, did not, and the Nobility did.");
                    print.wait(s);
                }else{returnValue = false;}
            }   
        }
        else print.s("\nThank you for confirming. It is important you understand that there IS spoilers in this game.");
        print.pause();print.pause();print.pause();

        return returnValue;
    }
    public static void beginStory(Scanner s){
        print.s("\n\nKeep in mind that (P) denotes placeholder text");
        print.pause();
        print.s("\nThough...");
        print.pause();print.pause();
        print.s("I guess all this text is kind of placeholder...");
        print.pause();
        print.s("\nSo it really just denotes EXTREMELY placeholder.");
        print.pause();print.pause();print.pause();print.pause();
        print.s("\n\nWith that said, let's get into the story.");
        print.pause();print.pause();
        print.s("\n\nYou are a skaa assigned to work the fields in the TRESTING PLANTATION.");
        print.pause();print.pause();
        print.s("\nA strange man in a strange cloak showed up yesterday who called himself Kelsier, the survivor of Hathsin. And this morning the manor is burning.");
        print.pause();
        print.s("\nThe Survivor of Hathsin made sure that you had no choice but to flee; when the inquisitors find the burned manor and dead nobility, they will assume the skaa were responsible.");
        print.pause();print.pause();
        print.s(30,"\nYou choose not to go which the other skaa in hiding, but rather to set out on your own in search of a better life.");
        print.pause();
        if(input.give_option(s,"\nDo you need a tutorial?","Yes","No",true)){
            print.s(30,"So instead of proper tutorial, I'm just going to vaguely explain a couple of mechanics and leave the rest to you.");
            print.s(20,
                "\nIn a turn, you are given the following options:"
                +   "\n(1)  Travel, which lets you travel to the various cities and areas in the Final Empire," 
                +   "\n(2)  Rest, which heals you," 
                +   "\n(3)  Explore, which gives you one of many random encounters, and"
                +   "\n(4)  Status, which tells you your stats."
            );
            print.pause();
            print.s("Speaking of Stats..."
                +   "\nDEFENSE is measured in percentage removed from enemy (for example, a peice of armour may grant 10% damage reduction)"
                +   "\nACCURACY is how accurate your attacks are, measured in percent"
                +   "\nEVASION is your change to evade damage, measured in percent"
            );
        }else{
            print.s("\nNo tutorial. Got it.");
        }
        print.wait(s);

    }
    public static player gameTurn(player player, Scanner s){
        player.time.addDay(1);
        print.s("\n"+player.time.printDate());
        //first: is there any immediate time based events that are happening right now? if so do that
        //second: is there any time and location based "Special" events happening right now? if so do that
        //third: 'regular turn'
        regularTurn(s, player);
        //then, recursively call itself until the final event in (assume it's like year three or something)
        
        player.phase = player.time.year - 1;
        if(player.time.getYear() < 3) {
            print.wait(s);
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
        if(turn == turnOptions.REST) {
            player.health = rest(player.health);
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
        turnOptions optionPicked = turnOptions.REST;
        String[] options = {"Travel", "Rest", "Explore", "Status"};
        int optionChose = input.give_options(s,"You can: ", options);
        switch(optionChose){
            case 0:
                optionPicked = turnOptions.TRAVEL;
                break;
            case 1:
                optionPicked = turnOptions.REST;
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
        String IDidNothing = "As you debate this decision, you realize your day is spent.";

        ArrayList<String> locations = new ArrayList<String>();
        int i = -1; //  counter starts at -1 to offset initial adding
        for(areas a : areas.values()){
            i++;
            if(a != player.location){
                // it is travelable to
                locations.add(a.name());
            }else{
                print.s("You are currently at " + areas.values()[i] + "\n");
                playerLocationInAreas = i;
            }
        }
        String[] locationsArray = locations.toArray(new String[0]);


        int optionPicked = input.give_options_with_exit(s,"Travel Locations: ", locationsArray);
        if(optionPicked == -2){
            // EXIT VALUE
            print.s(IDidNothing);
            return player;
        }
        // so if the optionsPicked number is greater then or equal to the player.location in areas.values()[], add 1
        if(optionPicked >= playerLocationInAreas) optionPicked++;
        print.s("Your input likely corrosponds to this location: " + areas.values()[optionPicked] + "\n");

        if(areas.values()[optionPicked].name().toLowerCase().equals("hathsin")){
            boolean willing = input.give_option(s,"Are you sure? No one has ever come back from the pits of hathsin","Yes","No",true);
            if(willing){
                player.location = areas.values()[optionPicked];
                player.time.addDay(6);

                print.s("\nTraveling takes 1 week.");
                print.s(travelDescriptions.desc_from_area(player.location));
            }
            else{
                print.s(IDidNothing);
            }
        }
        else{
            boolean willing = input.give_option(s,"Are you willing?","Yes","No",true);
            if(willing){
                player.location = areas.values()[optionPicked];
                player.time.addDay(6);

                print.s("\nTraveling takes 1 week.");
                print.s(travelDescriptions.desc_from_area(player.location));
            }
            else{
                print.s(IDidNothing);
            }
        }
        return player;
    }
    public static int rest(int health){
        // So the player sleeps, a day passes, and you gain health.
        print.s("You sleep, regaining some health. Health is now 100\n");
        print.pause();
        return health = 100; // change this so that it can dynamically change?
    }
    public static player explore(player player, Scanner s){
        randomAreaEvents exploreEvent = player.pickEvent(); // eventType, description, enemies
        if(exploreEvent.eventType == eventTypes.ENEMY) player = battle(player,exploreEvent,s);
        else if(exploreEvent.eventType == eventTypes.INFO) player = InfoEvent(player,exploreEvent,s);
        return player;
    }
    public static void status(player player){
        print.s("\nYou are at " + player.location.name());
        print.pause();
        print.s("\nToday is: " + player.time.printDate());
        print.pause();
        print.s("Battle stats:"
            +   "\n\t"
            +       "Health: "
            +           player.health
            +   "\n\t"
            +       "Defense: "
            +           player.def
            +   "\n\t"
            +       "Evasion: "
            +           player.evasion
            +   "\n\t"
            +       "Accuracy:"
            +           player.accuracy
        );
        print.pause();
        print.s("\nThe coins you have in your purse: " + player.currency + "\n");
    }
    public static player battle(player player, randomAreaEvents event, Scanner s){
        player = battle.fight(player,event,s);
        return player;
    }
    public static player InfoEvent(player player, randomAreaEvents info, Scanner s){
        print.s(15,info.description);
        return player;
    }



}


