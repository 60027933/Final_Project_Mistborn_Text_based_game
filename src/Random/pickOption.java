package src.Random;

import java.util.*;
import src.Enums.enemies;

public class pickOption {
    public static enemies fromEnemyTypes(enemies[] choices, Random r){ // application: pickOption.fromEnemyTypes
        enemies enemyChosen = enemies.SKAA_BANDIT; // default
        enemyChosen = choices[r.nextInt(choices.length)];
        return enemyChosen;
    }
    public static String fromStrings(String[] choices, Random r){
        String strChosen = "Default pickOption.fromStrings() str";
        strChosen = choices[r.nextInt(choices.length)];
        return strChosen;
    }
}
