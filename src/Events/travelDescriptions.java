package src.Events;

import java.util.*;
import src.Enums.*;
import src.randomAddons.*;
public class travelDescriptions {
    public static String desc_from_area(areas area){
        Random r = new Random();
        String desc = "Default description";
        ArrayList<String> options = new ArrayList<>();
        switch(area){
            case FELLIS:
                options.add("\nDescription of Fellis");
                break;
            case HATHSIN:
                options.add("\nDescription of Hathsin");
                break;
            case LUTHADEL:
                options.add("\nDescription of Luthadel");
                break;
            case TRESTING:
                options.add("\nDescription of Tresting");
                break;
        }
        if(!options.isEmpty()) {desc = pickOption.fromStrings(options.toArray(new String[0]),r);}
        return desc;
    }
}
