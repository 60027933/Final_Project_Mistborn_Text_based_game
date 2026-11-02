package src.Events;
import src.Text.*;
import src.Character.*;
import src.Input.*;
import java.util.Scanner;
import java.util.Random;
import src.randomAddons.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import src.Enums.*;

public class battle {
    public static player fight(player player, randomAreaEvents event, Scanner s){
        
        // Create and initialize list of enemies
        ArrayList<enemy> enemies = new ArrayList<enemy>();
        for(int i = 0; i < event.enemyCount; i++) {
            enemies.add(new enemy(
                    (int)event.healthOfEnemy(event.enemyType),
                    (int)event.currencyHeldOnEnemy(event.enemyType),
                    event.enemyType)
            );
        }
        boolean thereIsMoreThanOneEnemy = enemies.size() > 1 ? true : false;
        String enemyName = str.toTitleCase(event.enemyType.toString()).replace('_',' ');

        print.s(35,"\n"+event.description+"\n\n");print.pause();
        print.s("YOU ARE IN A FIGHT.");
        Random r = new Random();
        //Start: Give battle information. Give Battle options at the top.
        while(player.health > 0 && enemiesAreStillAlive(enemies)){
            print.s(50,"\n\nYou have " + player.health + " health.\n");
            //Display health of enemy/enemies 
            String healthInfo = new String();
            for(int i = 0; i < enemies.size(); i++){ // True size starting from 0
                healthInfo = healthInfo + String.format(
                    "%s #%d  has %d health%s",
                        enemyName,
                        i+1,
                        enemies.get(i).health,
                        i<enemies.size()-1 ? ";\n" : "." // either end the sentence or properly format it for the next enemy
                    );
            }
            print.s(40,healthInfo);
            
            
            //Get player input.
            int player_input = -1; //default
            while(player_input == -1 || player_input == 3){
                player_input = input.give_options(s,"\nWhat decision do you make?",new String[]{"Flee","Fight","Defend","Status"});
                if(player_input == 3) player.status();    
            } 
            //Player Attacks or attempts to flee.
            if(player_input == 0){
                int coinflip = r.nextInt(2);
                if(coinflip == 0){
                    print.s(choose.fromStrings(new String[]{"You manage to get away.","You successfully flee!"}, r));
                    break;
                }else{
                    print.s(choose.fromStrings(new String[]{"You try to flee, but done got yanked by the collar backwards.","You fail to flee."}, r));
                }
            }else if(player_input == 1){
                // attack
                if(enemies.size() == 1){
                    // ACCURACY % RANDOM CHECK
                    // based off of the ENEMIES EVASION and YOUR ACCURACY
                    print.s(30, String.format("\nYou attack for %d Damage!",player.dmg));
                    print.pause();
                    enemies.get(0).health -= player.dmg;
                    if(enemies.get(0).health <= 0) print.s(30,"\nYou defeated the "+enemyName+"!");
                    print.pause();
                }
                else{
                    print.s("Which enemy do you want to attack?");
                    String[] atkOptions = new String[enemies.size()];
                    for(int i = 0; i < enemies.size(); i++){
                        atkOptions[i] = String.valueOf(i+1); // from 1 instead of 0
                    }
                    while(true){
                        int pInput = input.give_options(s,"",atkOptions);
                        if(enemies.get(pInput).health <= 0){
                            print.s("\nThey're already dead. You could kick their corpse, I guess.");
                            boolean IWantToKickTheCorpse = input.give_option(s,"\n( Yes, No )", "yes","no",false);
                            if(IWantToKickTheCorpse){
                                print.s(30, choose.fromStrings(new String[]{
                                    String.format("\nYou kick %s #%d's corpse for %d Damage!",                                        enemyName,
                                        enemyName,pInput+1,player.dmg),
                                    String.format("\nYou beat %s #%d's corpse for %d Damage!",
                                        enemyName,pInput+1,player.dmg)
                                },r));
                                enemies.get(pInput).health-=player.dmg;
                                break;
                            }
                        }
                        else{
                            print.s(30, String.format("\nYou attack for %d Damage!",player.dmg));
                            print.pause();
                            enemies.get(pInput).health -= player.dmg;
                            if(enemies.get(pInput).health <= 0) print.s(30,"\nYou defeated "+enemyName+" #"+String.valueOf(pInput+1)+"!");
                            print.pause();
                            break;
                        }
                    }

                }
            }else if(player_input == 2){ // PLAYER DEFENDS
                print.s("\nYou defend, gaining +0.2 defense this turn!");
                player.def += 0.2;
            }
            print.pause();
            if(enemiesAreStillAlive(enemies)){
                for(int i = 0; i < enemies.size(); i++){
                    if(enemies.get(i).health > 0){
                        //calculate damage based on player defense
                        double dmg = event.dmg-(event.dmg*player.def);
                        //clamp dmg to 0
                        dmg = dmg < 0 ? 0 : dmg;
                        int energyCost = 5;

                        if(enemies.get(i).state == enemyStates.STUNNED){
                            print.s(30, String.format("\n%s #%d does nothing (Stunned).",enemyName,i+1));
                        }
                        else if(enemies.get(i).state == enemyStates.RECOVERING){
                            print.s(30, String.format("\n%s #%d recovers some energy.",enemyName,i+1));
                            enemies.get(i).energy = 50;
                        }
                        else if(enemies.get(i).state == enemyStates.NORMAL && ThreadLocalRandom.current().nextDouble() > 0.5 && enemies.get(i).energy > 20){
                            // if the enemy has enough energy for a big attack and also a coinflip lands correctly
                            print.s(30, String.format("\n%s #%d is preparing a big attack!",enemyName,i+1));
                            print.pause();
                            enemies.get(i).state = enemyStates.PREPARING;
                        }
                        else{
                            if(enemies.get(i).state == enemyStates.PREPARING){ 
                                dmg*=3;
                                energyCost = 40;
                            }

                            if(ThreadLocalRandom.current().nextDouble() > player.evasion){ // PLAYER EVASION CHECK
                                print.s(30, String.format("\n%s #%d %s for %d Damage!",enemyName,i+1,enemies.get(i).state == enemyStates.PREPARING ? "makes a LARGE ATTACK" : "attacks", (int)dmg));
                                print.pause();
                                player.health-= dmg;
                            }else{
                                print.s(30,"\nYou dodge an attack for " + dmg + " Damage!\n");
                                print.pause();
                            }
                            if(enemies.get(i).state == enemyStates.PREPARING) enemies.get(i).state = enemyStates.NORMAL;
                            enemies.get(i).energy-=energyCost;
                            if(enemies.get(i).energy < 0) enemies.get(i).state = enemyStates.RECOVERING;
                        }
                        if(enemies.get(i).state == enemyStates.RECOVERING && enemies.get(i).energy > 0){
                            enemies.get(i).state = enemyStates.NORMAL;
                        }
                    }
                }
            }else{ // ENEMIES ARE NOT STILL ALIVE
                print.s(choose.fromStrings(new String[]{String.format("\nYou survived the encounter!\nYou gained %d coins.", (int)event.currencyHeldOnEnemy(event.enemyType)*event.enemyCount)}, new Random()));
                player.currency += event.currencyHeldOnEnemy(event.enemyType)*event.enemyCount;
            }

            if(player_input == 2){player.def-=0.2;} // reset players defense
        }// END OF WHILE LOOP FOR ATTACKING
        return player;
    }
    public static boolean enemiesAreStillAlive(ArrayList<enemy> enemies){
        boolean returnVal = false;
        for(int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).health>0)returnVal=true;
        }
        return returnVal;
    }
    public static int numberOfLiveEnemies(ArrayList<enemy> enemies)
    {
        int liveEnemies = 0;
        for(int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).health>0) liveEnemies++;
        }
        return liveEnemies; 
    }



    public static player placeholder(player player, randomAreaEvents battle){
        print.s("\n"+battle.description);
        print.s("\n(There will be a proper battle system in the future)\n");
        print.pause();
        print.s("You Fight " + battle.enemyCount + " " + battle.enemyType.name() + " And lose " + battle.healthOfEnemy(battle.enemyType)*battle.enemyCount + " Health.");
        player.health -= battle.healthOfEnemy(battle.enemyType)*battle.enemyCount;
        print.s("\nYou gain "+battle.currencyHeldOnEnemy(battle.enemyType)*battle.enemyCount+" Boxings (currency)");
        player.currency += battle.currencyHeldOnEnemy(battle.enemyType)*battle.enemyCount;
        return player;
    }
}
