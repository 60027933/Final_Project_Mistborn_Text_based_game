package src.Character;

import src.Enums.*;

public class enemy {
    public int health;
    public int coins;
    public enemies enemyType;
    public int energy = 50;
    public enemyStates state = enemyStates.NORMAL;
    public enemy(int health, int coins, enemies enemyType){
        this.health = health;
        this.coins = coins;
        this.enemyType = enemyType;
    }
}
