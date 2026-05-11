package src.Events;


public class areaEvent {
    public enum event {
        ENEMY,
        INFO,
        INVENTORY,
        QUEST
    }
    public enum enemies {
        IMMATURE_KOLOSS,
        CLERGY_SCOUT,
        MATURE_KOLOSS,
        TINEYE_CLERGY,
        STEEL_INQUISITOR,
        SKAA_BANDIT,
    }
    //so the event needs to hold this info
    //introductory text options in an array ("book text")

    //event types:
    // enemy / inventory / information / quest (enum?)
    // hold of on quest and inventory for now
    event eventType;
    String description;
    enemies enemies;
    public areaEvent(event eventType, String description, enemies enemies){
        this.eventType = eventType;
        this.description = description;
        this.enemies = enemies;
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
}
