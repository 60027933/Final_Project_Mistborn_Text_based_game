package src.Events;
enum event {
    ENEMY,
    INFO,
    INVENTORY,
    QUEST
}
enum enemies {
    IMMATUREKOLOSS,
    CLERGYSCOUT
}

class areaEvent {
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
}
