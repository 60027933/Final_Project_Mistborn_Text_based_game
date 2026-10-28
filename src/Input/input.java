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
            else if(input.equals(negatoryValue)){
                returnValue = false;
                break;
            }
            else{
                if(input.contains(expectedValue) && !input.contains(negatoryValue)){returnValue = true; print.s(30,"(BE AWARE:");print.pause(); print.s(30, " we interpreted your answer as "+expectedValue+")\n");break;}
                if(input.contains(negatoryValue) && !input.contains(expectedValue)){returnValue = false;print.s(30,"(BE AWARE:");print.pause(); print.s(30, " we interpreted your answer as "+negatoryValue+")\n");break;}
                
                //if answer is "CLOSE ENOUGH -- containing most of the characters"

            }
        }
        return returnValue;
    }

    public static int give_options(Scanner s, String prompt, String[] values){ // MULTIPLE OPTION 
        print.s(prompt + " ( ");
        for(int i = 0; i < values.length; i++)
        {
            print.s(String.format("%s", (values[i].toString())));
            if(i < values.length-1)print.s(", ");
        }
        print.s(")\n");
        
        
        // clean the text for comparisons
        for(int z = 0; z < values.length; z++) values[z] = str.simple_clean(values[z],true);
        int returnValue = -1; // default, no option chosen

        boolean inputRecognized = false;
        while(!inputRecognized) {
            System.out.print(">>> ");
            String input = str.simple_clean(get(s),false);
            for(int i = 0; i < values.length; i++) {
                if(input.equals(values[i].toString())){
                    returnValue = i;
                    inputRecognized = true;
                }
            }
        }
        return returnValue; // 
    }

    public static int give_options_with_exit(Scanner s, String prompt, String[] values){
        print.s(prompt + " (");
        for(int i = 0; i < values.length-1; i++) print.s(String.format("%s, ", (values[i].toString())));
        print.s(String.format("%s)\n",values[values.length-1].toString()));
        print.s("\nAlso, type 'Exit' to exit\n");
        // clean the text for comparisons
        for(int z = 0; z < values.length; z++) values[z] = str.simple_clean(values[z],true);
        
        int returnValue = -1; // default;

        // loop input repeatedly until we get an input we can recognize
        boolean inputRecognized = false; 
        while(!inputRecognized) {
            System.out.print(">>> ");
            String input = str.simple_clean(get(s),false);
            if(input.equals("exit")){ returnValue = -2;inputRecognized=true;} // exit
            for(int i = 0; i < values.length; i++) {
                if(input.equals(values[i].toString())){
                    returnValue = i;
                    inputRecognized = true;
                }
            }
        }
        return returnValue; // 
    }
}
