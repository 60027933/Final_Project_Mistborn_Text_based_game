package src.Print;

import java.util.Scanner;

public class printAddons {
    public static void printS(String text){
        //print slow
        for(int i = 0; i < text.length(); i++){
            try{
                System.out.print(text.charAt(i));
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
                // best practice somehow?
                Thread.currentThread().interrupt();
            }
        }
    }
    public static void pause(){
        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted.");
            Thread.currentThread().interrupt();
        }
    }
    public static void wait(Scanner s){
        // wait for player to hit enter
        System.out.print("\n(hit enter to continue)");
        s.nextLine();
    }
}
