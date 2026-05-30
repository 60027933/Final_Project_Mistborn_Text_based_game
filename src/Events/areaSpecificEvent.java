package src.Events;

import src.Enums.*;
import src.Random.*;

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
        this.description = pickDescriptionFromOtherFactors();
    }
    public String pickDescriptionFromOtherFactors(){
        String desc = "Default event decription";
        return desc;
    }
    public enemies pickEnemyFromLocation(areas area){
        //AREAS: LUTHADEL, 
        switch(area){
            case LUTHADEL:
                
        }
        return enemies.SKAA_BANDIT;
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
        }
        return boxings;
    }
}
