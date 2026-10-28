package src.Player;
import src.Events.*;
import src.Enums.*;
import src.Time.calandar;
import src.randomAddons.*;
import java.util.Random;

public class player {
        public int health;
        public calandar time;
        public areas location;
        public int dmg;
        public float def;
        public float evasion;
        public float accuracy;

        public int currency;
        public int phase = 0;
        public boolean[] areasVisited = new boolean[areas.values().length];
        public String lastEventDescription = "Null and Void Starter Description";

        public player(int health, calandar time, areas location, int dmg, float def, float evasion , float accuracy, int currency){
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
                eventTypes eventType = choose.fromEventTypes(new eventTypes[]{eventTypes.ENEMY,eventTypes.INFO},r);
                returnValue = new randomAreaEvents(eventType, this.location);
                if(!returnValue.description.equals(lastEventDescription)){
                    lastEventDescription = returnValue.description;
                    break;
                }
            }
            return returnValue; 
        }
        
    }
    
