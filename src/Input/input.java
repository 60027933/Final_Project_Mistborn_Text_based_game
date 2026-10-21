package src.Input;


import src.Text.*;

import java.util.Scanner;


public class input {
    public static String get(Scanner s){
        String stringInput = s.nextLine();
        return stringInput;
    }
    public static boolean give_option(Scanner s, String prompt, String expectedValue, String negatoryValue,boolean showOptions){ // SINGLE OPTION for true / false type questions
        print.s(String.format("%s %s %s %s %s %s\n", // look at this ridiculous thing
        prompt,
        (showOptions) ? "(" : "",
        (showOptions) ? expectedValue : "",
        (showOptions) ? "," : "",
        (showOptions) ? negatoryValue : "",
        (showOptions) ? ")" : ""));
        // clean the text for comparisons
        expectedValue = str.simple_clean(expectedValue, false);
        negatoryValue = str.simple_clean(negatoryValue, false);
        
        boolean returnValue;
        while(true) {
            System.out.print(">>> ");
            String input = str.simple_clean(get(s),false);

            if(input.equals(expectedValue)) {
                returnValue = true;
                break;
            }
            if(input.equals(negatoryValue)){
                returnValue = false;
                break;
            }
        }
        return returnValue;
    }

    public static int give_options(Scanner s, String prompt, String[] values){ // MULTIPLE OPTION 
        print.s(prompt + " (");
        for(int i = 0; i < values.length-1; i++) print.s(String.format("%s, ", (values[i].toString())));
        print.s(String.format("%s)\n",values[values.length-1].toString()));
        // clean the text for comparisons
        for(int z = 0; z < values.length; z++) values[z] = str.simple_clean(values[z],true);
        
        int returnValue = -1; // 
        boolean breakLoop = false;
        while(!breakLoop) {
            System.out.print(">>> ");
            String input = str.simple_clean(get(s),false);
            for(int i = 0; i < values.length; i++) {
                if(input.equals(values[i].toString())){
                    returnValue = i;
                    breakLoop = true;
                }
            }
        }
        return returnValue; // 
    }
}
