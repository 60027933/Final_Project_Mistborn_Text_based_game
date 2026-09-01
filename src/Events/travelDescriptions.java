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
                options.add("\nA week's weary travel leads you to Fellis. Your first impression of it is cleanliness - in Luthadel's shadow, this quiet suburb of whitewashed granite buildings looks almost perfect. You see the lavish mansions and feel out of place, like you're peeking into a world that shouldn't exist.");
                break;
            case HATHSIN:
                options.add("\nYou approach what you can only assume is the infamous Pits of Hathsin. Massive cracks line the ground and you hear Skaa crying out in pain and anguish. \nWhat are you doing here, at the Lord Ruler's own kill camps? Still -  no one would expect a Skaa coming into the pits - you can probably still leave.");
                break;
            case LUTHADEL:
                options.add("\nYou arrive at the Capitol city of Luthadel, marveling at the massive walls. How will you get in? You don't have to wonder long - A skaa woman sees you gawking and Gestures at you to follow her. She leads you into the City, and as you turn to thank her, you realize she has already left, blending in with the crowds of Skaa.");
                break;
            case TRESTING:
                options.add("\nYou return to the place of your birth - the Tresting Plantation. In the absence of it's owners and the skaa, the fields have built up a large amount of ash. You walk through the old Skaa housings, reminiscing.");
                break;
        }
        if(!options.isEmpty()) {desc = pickOption.fromStrings(options.toArray(new String[0]),r);}
        return desc;
    }
}
