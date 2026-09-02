package src.Events;

import src.Enums.*;
import src.randomAddons.*;

import java.util.*;

public class randomAreaEvents {
    //so the event needs to hold this info
    //introductory text options in an array ("book text")

    //event types:
    // enemy / inventory / information / quest (enum?)
    // hold of on quest and inventory for now
    
    public eventTypes eventType;
    public String description;
    public enemies enemy;
    public int enemyCount = 1;
    public randomAreaEvents(eventTypes eventType, areas area){
        this.eventType = eventType;
        this.enemy = pickEnemyFromLocation(area);
        this.description = pickDescriptionFromOtherFactors(area, enemy);
    }
    public String pickDescriptionFromOtherFactors(areas area,enemies enemy){
        String desc = "Default event decription";

        if(eventType == eventTypes.ENEMY){
            WeightedRandomCollection<fightDescription> options = new WeightedRandomCollection<>();

            switch(area){ // AREA SPECIFIC DESCRIPTION, 
                case LUTHADEL:
                    switch(enemy){
                        case SKAA_BANDIT:
                            options.add(50.0, new fightDescription(1,"(p)You approached by a skaa bandit fr"));
                            options.add(70.0, new fightDescription(1,"You walk through the grimy, ashen streets of cobblestone Skaa slums. The streets are compact, lined with multi-story hovels and tenaments with only the occasional boarded-up window. The streets are filled with beggars and the sick, and you hear coughing ring out as you pass an alley. You decide to head back, but before you do, someone taps on your shoulder. You swivel around, and come face to face with a Skaa Bandit!"));
                            options.add(20.0, new fightDescription(4,"Your footsteps sound through the ash-covered cobbled streets of the Skaa Slums. You see a lack of people, but don't register what it means until too late - you find yourself surrounded by skaa bandits!"));
                            break;
                        case TINEYE_CLERGY:
                            options.add(50.0, new fightDescription(1,"You walk along the main River running through Luthadel, only to realize you've wandered into the Canton of Inquisition. You promtly turn aroun, knowing you may attract attention, but it's too late; a Clergy Tineye perched on one of the many spires of Luthadel has spotted you, and is now curiously approaching you."));
                            break;
                        case CLERGY_SCOUT:
                            options.add(50.0, new fightDescription(1,"You are passed by the occasional horse and wagon as you walk through the clean stone streets of Luthadel's Commercial District. You pass bakeries filling the air with delicious smells, and hear the workers at the woodshop cutting, hammering, and working on the various woodworks to be put up for sale. As you pass yet another carriage, you realize that you are being followed. Parhaps being a ash-covered skaa has attracted the attention of this Clergy Scout, who was watching the main street in an alley. You turn to face him."));
                            options.add(50.0, new fightDescription(1,"(p)For sum reeson you atrakt a clerjy scout"));
                            break;
                    };
                    break;
                case FELLIS:
                    switch(enemy){
                        case NOBLE_GUARD:
                            options.add(50.0, new fightDescription(1,"You wander down the quiet streets of Fellis, and pass a carriage. A noble woman in a frilly dress saw you pass, gasps, and says 'It's a SKAA! Get rid of it!' to the guard in the backseat. The guard begrudginly complies. After opening the carriage door, he steps outside gingerly before calling out 'Stop!'"));
                            break;
                        case TINEYE_CLERGY:
                            options.add(50.0, new fightDescription(1,"As you pass through the quiet streets and quaint shops of Fellis, looking for a bite to eat, you see an obligator walking slowly towards you with a hateful expression on his face. You shout in surprise, causing him to put his hands over his ears - a tineye's reaction."));
                            break;
                        case CLERGY_SCOUT:
                            options.add(50.0, new fightDescription(1,"(p)Spotted by clergy scout"));
                            break;
                    }
                    break;
                case TRESTING:
                    switch(enemy){
                        case SKAA_BANDIT:
                            options.add(50.0, new fightDescription(1,"As you wander the ash-covered fields of the Tresting plantation, you see a Skaa bandit, probably a former worker, approach you with a knife. He demands for everything that you have."));
                            break;
                        case CLERGY_SCOUT:
                            break;
                    }
                    break;
                case HATHSIN:
                    switch(enemy){
                    case STEEL_INQUISITOR:
                        options.add(50.0, new fightDescription(1,"(p)You find youself being watched by a Steel Inquisitor."));
                        break;
                    case HATHSIN_GUARD:
                        options.add(50.0, new fightDescription(1,"(P)Fight with 1 hathsin guard"));
                        options.add(20.0, new fightDescription(3,"(P)Fight with 3 hathsin guards"));
                        options.add(5.0, new fightDescription(12,"(P)Fight with 12 hathsin guards"));
                        break;
                    }
                    break;
                    
            }
            fightDescription chosen_option = options.next();
            if(!options.isEmpty() && options != null) {
                desc = chosen_option.desc; 
                enemyCount = chosen_option.enemyAmount;
            }
        }
        else if(eventType == eventTypes.INFO){
            WeightedRandomCollection<String> options = new WeightedRandomCollection<>();

            switch(area){
                case LUTHADEL:
                    options.add(50.0,"\n(P)Luthadel Observations");
                    options.add(50.0,"\n(P)Walk by the manors");
                    options.add(10.0,"\n(P)Watched by an inquisitor");
                    options.add(20.0,"\n(P)You see a Skaa crew at work");
                    break;
                case TRESTING:
                    options.add(50.0,"\n(P)Tresting Observations");
                    options.add(50.0,"\n(P)Walk through the wrecked manor");
                    break;
                case FELLIS:
                    options.add(50.0,"\n(P)Walk through the pleasant, clean streets");
                    options.add(50.0,"\n(p)Small talk with a noble lord");
                    break;
                case HATHSIN:
                    options.add(50.0,"\n(P)Stomach turning Pits descriptions");
                    options.add(30.0,"\n(P)You witness something disturbing, a mistwraith maybe");
                    break;
            }
            String chosen_option = options.next();
            if(!options.isEmpty() && options != null) {
                desc = chosen_option; 
            }
        }
        return desc;
    }
    public enemies pickEnemyFromLocation(areas area){
        //AREAS: LUTHADEL, 

        enemies chosenEnemy = enemies.SKAA_BANDIT;
        WeightedRandomCollection<enemies> options = new WeightedRandomCollection<>();
        switch(area){
            case LUTHADEL: // SKAA_BANDIT, TINEYE_CLERGY, CLERGY_SCOUT
                options.add(60.0, enemies.SKAA_BANDIT);
                options.add(20.0, enemies.TINEYE_CLERGY);
                options.add(20.0, enemies.CLERGY_SCOUT);
                chosenEnemy = options.next();
                break;
            case FELLIS: // NOBLE_GUARD, TINEYE_CLERGY, CLERGY_SCOUT
                options.add(75.0, enemies.NOBLE_GUARD);
                options.add(15.0, enemies.CLERGY_SCOUT);
                options.add(15.0, enemies.TINEYE_CLERGY);
                chosenEnemy = options.next();
                break;
            case TRESTING: // SKAA_BANDIT, CLERGY_SCOUT
                options.add(75.0, enemies.SKAA_BANDIT);
                options.add(15.0, enemies.CLERGY_SCOUT);
                chosenEnemy = options.next();
                break;
            case HATHSIN: // STEEL_INQUISITOR, MATURE_KOLOSS, IMMATURE_KOLOSS,
                options.add(5.0, enemies.STEEL_INQUISITOR);
                options.add(70.0, enemies.HATHSIN_GUARD);
                chosenEnemy = options.next();
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
            case HATHSIN_GUARD:
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
            case HATHSIN_GUARD:
                boxings = 30;
                break;
        }
        return boxings;
    }
}
