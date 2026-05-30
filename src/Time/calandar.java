package src.Time;

import java.util.ArrayList;

public class calandar {
    public int year, month, day;
    public calandar(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public int getYear(){
        return year;
    }
    public void addDay(int add){
        String[] months = {"January", "February","March","April","May","June","July","August","September","October","November","December"};
        
        ArrayList<String> days31 = new ArrayList<String>();
        days31.add("January"); days31.add("March");days31.add("May");days31.add("July"); days31.add("October"); days31.add("December");
        ArrayList<String> days30 = new ArrayList<String>();
        days30.add("April"); days30.add("June"); days30.add("September"); days30.add("November");
        // So keep track of days of the month, flip months accordingly
        for(int k = 0; k < add; k++){
            day += 1;
            // february first
            switch(day){
                case 28:
                    if(month == 1){
                        day = 1;
                        month++;
                        // february
                    }
                    break;
                case 30:
                    for(int i = 0; i < days30.size(); i++){
                        if(months[month].equals(days30.get(i))){
                            month++;
                            day = 1;
                        }
                    }
                    break;
                case 31:
                    for(int i = 0; i < days31.size(); i++){
                        if(months[month].equals(days31.get(i))){
                            if(month == 11) { // december, last month of year
                                month = 0;
                                day = 1;
                                year += 1;
                            }
                            else{
                                month++;
                                day = 1;
                            }
                        }
                    }
                    break;
            }
        }
    }

    public String printDate(){
        String[] months = {"January", "February","March","April","May","June","July","August","September","October","November","December"};
        return String.format("%s %d of year %d\n",months[month],day,year);
    }
}
