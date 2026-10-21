package src.Events;
import src.Player.*;
import src.Text.*;
import src.Input.*;
import java.util.Scanner;
import java.util.Random;
import src.randomAddons.*;
public class battle {
    public static player fight(player player, randomAreaEvents event, Scanner s){
        
        print.s("\n"+event.description);
        Random r = new Random();
        //Start: Give battle information. Give Battle options at the top.
        print.s("\nYou can: Flee (50/50 chance), Fight (" + player.dmg + " dmg)");
        while(player.health > 0 && enemiesAreStillAlive(event)){
            //Get player input.
            boolean player_wants_to_flee = input.give_option(s,"","Flee","Fight",false);
            //Player Attacks or attempts to flee.
            if(player_wants_to_flee){
                int coinflip = r.nextInt(2);
                if(coinflip == 0){
                    print.s("You successfully flee the encounter.");
                    break;
                }else{
                    print.s(pickOption.fromStrings(new String[]{"You try to flee, but done got yanked by the collar backwards.","You fail to flee."}, r));
                }
            }else{
                // attack

            }
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
