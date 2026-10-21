package src.Events;
import src.Player.*;
import src.Text.*;
public class battle {
    public static player fight(player player, randomAreaEvents event){
        print.s("\n"+event.description);

        //Start: Give battle information. Give Battle options at the top.
        print.s("You can: Flee (50/50 chance), Fight (" + player.dmg + " dmg)");
        while(player.health > 0 && enemiesAreStillAlive(event)){
            //Get player input.
            
            //Player Attacks or attempts to flee.
            //Enemy Attacks.
        }
        return player;
    }
    public static boolean enemiesAreStillAlive(randomAreaEvents event){
        boolean returnVal = true;
        for(int i = 0; i < event.enemyCount; i++){

        }
        return returnVal;
    }
    public static player placeholder(player player, randomAreaEvents battle){
        print.s("\n"+battle.description);
        print.s("\n(There will be a proper battle system in the future)\n");
        print.pause();
        print.s("You Fight " + battle.enemyCount + " " + battle.enemy.name() + " And lose " + battle.healthOfEnemy(battle.enemy)*battle.enemyCount + " Health.");
        player.health -= battle.healthOfEnemy(battle.enemy)*battle.enemyCount;
        print.s("\nYou gain "+battle.currencyHeldOnEnemy(battle.enemy)*battle.enemyCount+" Boxings (currency)");
        player.currency += battle.currencyHeldOnEnemy(battle.enemy)*battle.enemyCount;
        return player;
    }
}
