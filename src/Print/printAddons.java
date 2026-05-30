package src.Print;

import java.util.Scanner;

public class printAddons {
    public static void printS(String text){
        //print slow
        text = wordWrap(text, 120);
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
    public static String wordWrap(String text, int maxWidth) {
        StringBuilder result = new StringBuilder();
        String[] words = text.split(" ");
        int lineLength = 0;

        for (String word : words) {
            if (lineLength + word.length() + 1 > maxWidth) {
                result.append("\n");
                lineLength = 0;
            } else if (lineLength > 0) {
                result.append(" ");
                lineLength++;
            }
            result.append(word);
            lineLength += word.length();
        }
        return result.toString();
}
}
