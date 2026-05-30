package src.Events;

import src.Enums.*;
import src.Random.*;
import java.util.*;
public class areaSpecificEvent {
    //so the event needs to hold this info
    //introductory text options in an array ("book text")

    //event types:
    // enemy / inventory / information / quest (enum?)
    // hold of on quest and inventory for now
    public turnEventTypes eventType;
    public String description;
    public enemies enemy;
    public areaSpecificEvent(turnEventTypes eventType, areas area){
        this.eventType = eventType;
        this.enemy = pickEnemyFromLocation(area);
        this.description = pickDescriptionFromOtherFactors(area, enemy);
    }
    public String pickDescriptionFromOtherFactors(areas area,enemies enemy){
        String desc = "Default event decription";
        Random r = new Random();
        ArrayList<String> options = new ArrayList<>();

        switch(area){ // AREA SPECIFIC DESCRIPTION, 
            case LUTHADEL:
                switch(enemy){
                    case SKAA_BANDIT:
                        options.add("You walk through the grimy, ashen streets of cobblestone Skaa slums. The streets are compact, lined with multi-story hovels and tenaments with only the occasional boarded-up window. The streets are filled with beggars and the sick, and you hear coughing ring out as you pass an alley. You decide to head back, but before you do, someone taps on your shoulder. You swivel around, and come face to face with a Skaa Bandit!");
                        break;
                    case TINEYE_CLERGY:
                        options.add("You walk along the main River running through Luthadel, only to realize you've wandered into the Canton of Inquisition. You promtly turn aroun, knowing you may attract attention, but it's too late; a Clergy Tineye perched on one of the many spires of Luthadel has spotted you, and is now curiously approaching you.");
                        break;
                    case CLERGY_SCOUT:
                        options.add("You are passed by the occasional horse and wagon as you walk through the clean stone streets of Luthadel's Commercial District. You pass bakeries filling the air with delicious smells, and hear the workers at the woodshop cutting, hammering, and working on the various woodworks to be put up for sale. As you pass yet another carriage, you realize that you are being followed. Parhaps being a ash-covered skaa has attracted the attention of this Clergy Scout, who was watching the main street in an alley. You turn to face him.");
                        break;
                };
                break;
            case FELLIS:
                switch(enemy){
                    case NOBLE_GUARD:
                        break;
                    case TINEYE_CLERGY:
                        break;
                    case CLERGY_SCOUT:
                        break;
                }
                break;
            case TRESTING:
                break;
            case HATHSIN:
                break;
        }

        if(!options.isEmpty()) {desc = pickOption.fromStrings(options.toArray(new String[0]),r);}
        return desc;
    }
    public enemies pickEnemyFromLocation(areas area){
        //AREAS: LUTHADEL, 
        Random r = new Random();

        enemies chosenEnemy = enemies.SKAA_BANDIT;
        enemies[] options;
        switch(area){
            case LUTHADEL: // SKAA_BANDIT, TINEYE_CLERGY, CLERGY_SCOUT
                options = new enemies[] {enemies.SKAA_BANDIT, enemies.CLERGY_SCOUT, enemies.TINEYE_CLERGY};
                chosenEnemy = pickOption.fromEnemyTypes(options,r);
                break;
            case FELLIS: // NOBLE_GUARD, TINEYE_CLERGY, CLERGY_SCOUT
                options = new enemies[] {enemies.NOBLE_GUARD, enemies.CLERGY_SCOUT, enemies.TINEYE_CLERGY};
                chosenEnemy = pickOption.fromEnemyTypes(options,r);
                break;
            case TRESTING: // SKAA_BANDIT, CLERGY_SCOUT
                options = new enemies[] {enemies.SKAA_BANDIT, enemies.CLERGY_SCOUT};
                chosenEnemy = pickOption.fromEnemyTypes(options,r);
                break;
            case HATHSIN: // STEEL_INQUISITOR, MATURE_KOLOSS, IMMATURE_KOLOSS,
                options = new enemies[] {enemies.STEEL_INQUISITOR, enemies.MATURE_KOLOSS, enemies.IMMATURE_KOLOSS};
                chosenEnemy = pickOption.fromEnemyTypes(options,r);
                break;
        }
        return chosenEnemy;
    }
    public int healthOfEnemy(enemies enemy){
        int health = 0;
        switch(enemy){
            case IMMATURE_KOLOSS:
                health = 40;
                break;
            case MATURE_KOLOSS:
                health = 150;
                break;
            case CLERGY_SCOUT:
                health = 20;
                break;
            case TINEYE_CLERGY:
                health = 50;
                break;
            case STEEL_INQUISITOR:
                health = 250;
                break;
            case SKAA_BANDIT:
                health = 20;
                break;
            case NOBLE_GUARD:
                health = 50;
                break;
        }
        return health;
    }
    public int currencyHeldOnEnemy(enemies enemy){
        int boxings = 0;
        switch(enemy){
            case IMMATURE_KOLOSS:
                boxings = 20;
                break;
            case MATURE_KOLOSS:
                boxings = 150;
                break;
            case CLERGY_SCOUT:
                boxings = 20;
                break;
            case TINEYE_CLERGY:
                boxings = 50;
                break;
            case STEEL_INQUISITOR:
                boxings = 250;
                break;
            case SKAA_BANDIT:
                boxings = 5;
                break; 
            case NOBLE_GUARD:
                boxings = 40;
                break;
        }
        return boxings;
    }
}
