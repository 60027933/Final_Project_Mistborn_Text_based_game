package src.Character;
import src.Events.*;
import src.Enums.*;
import src.Time.calandar;
import src.randomAddons.*;
import java.util.Random;
import src.Text.*;

public class player {
        public int health;
        public calandar time;
        public areas location;
        public int dmg;
        public double def;
        public double evasion;
        public double accuracy;

        public int currency;
        public int phase = 0;
        public boolean[] areasVisited = new boolean[areas.values().length];
        public String lastEventDescription = "Null and Void Starter Description";

        public player(int health, calandar time, areas location, int dmg, double def, double evasion , double accuracy, int currency){
            this.health = health;
            this.time = time;
            this.location = location;
            this.dmg = dmg;
            this.def = def;
            this.evasion = evasion;
            this.accuracy = accuracy;

            this.currency = currency;
        }
        public randomAreaEvents pickEvent(){
            Random r = new Random();
            randomAreaEvents returnValue = new randomAreaEvents(eventTypes.ENEMY, this.location); // default value
            for(int count = 0; count < 100; count++){ // number of iterations trying to get a unique previous event 
                eventTypes eventType = choose.fromEventTypes(new eventTypes[]{eventTypes.ENEMY,eventTypes.ENEMY,eventTypes.ENEMY,eventTypes.INFO},r); // hoping for more enemies than infos
                returnValue = new randomAreaEvents(eventType, this.location);
                if(!returnValue.description.equals(lastEventDescription)){
                    lastEventDescription = returnValue.description;
                    break;
                }
            }
            return returnValue; 
        }
        public void status(){
            print.s("\nYou are at " + location.name());
            print.pause();
            print.s("\nToday is: " + time.timeFormat());
            print.pause();
            print.s("Battle stats:"
                +   "\n\t"
                +       "Health: "
                +           health
                +   "\n\t"
                +       "Defense: "
                +           def
                +   "\n\t"
                +       "Evasion: "
                +           evasion
                +   "\n\t"
                +       "Accuracy:"
                +           accuracy
            );
            print.pause();
            print.s("\nThe coins you have in your purse: " + currency + "\n");
        }
        public void end_status(){
            print.s("\nYou died at " + location.name());
            print.pause();
            print.s("\n Date of Death: " + time.timeFormat());
            print.pause();
            print.s("\nThe coins you had in your purse: " + currency + "\n");
        }
    }
    
