
package myBot;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Evan
 * 
 * This class is for webscraping utilities, all of the methods are tailored for specific websites with formating specific to that website.
 */
public class Webscrape {
    
    private static String weather() throws IOException{//gets the weather
        final Document document = Jsoup.connect("https://forecast.weather.gov/MapClick.php?x=68&y=83&site=ilx&zmx=&zmy=&map_x=67&map_y=83#.XFOy5KpKhPY").get();
        String currentCond = document.select("p.myforecast-current").text();
        String temp = document.select("p.myforecast-current-lrg").text();//.text gets the value from the HTML
        String tempC = document.select("p.myforecast-current-sm").text();//temp in celcius
        String storeString = "Current Conditions "+currentCond+"\n"+"Temperature "+temp+" ("+tempC+")";
        for(Element row : document.select("div#current_conditions_detail.pull-left tr")){//collect current conditions, and last time updated.
            storeString=storeString+"\n"+row.text();
        }
        storeString = "Here are the current weather conditions in Macomb, IL: \n"+storeString;//change this string for localization.
        return storeString;
    }
    /**
     * This method gets data from the National Weather Service and returns information, in a String, for local weather data for Macomb, IL.
     * @return String - Returns formatted weather data in a String.
     * @throws IOException Throws an IOException if connection to the NWS website cannot be made.
     */
    public static String getWeather() throws IOException{//gets the weather() which gets the weather, totally not confusing
        return weather();
    }
    
    private static LinkedList<String> LaunchCal() throws IOException{//creates a LinkedList storing all of the scheduled rocket launches/descriptions from spaceflightnow.com
        Document spaceflightNow = Jsoup.connect("https://spaceflightnow.com/launch-schedule/").get();
        int node = 1;//this int helps with formatting issues
        LinkedList<String> schedule = new LinkedList<>();//this is a double-y linked list
        //for searching for more than one HTML tag, use a comma separating them. Example: div.dataname, div.
        for(Element launch : spaceflightNow.select("div.entry-content.clearfix div.datename, div.missiondata, div.missdescrip")){
            String launchdate,mission,missiondata,missiondescrip;
            if(node == 1){//Date, rocket/mission
                launchdate = launch.select("span.launchdate").text();
                mission = launch.select("span.mission").text();
                schedule.addLast(launchdate+"\n"+mission+"\n");
                
            }
            if(node == 2){
                missiondata = launch.select("div.missiondata").text();
                schedule.addLast(missiondata+"\n");
            }
            if (node == 3){
                missiondescrip = launch.select("div.missdescrip").text();
                schedule.addLast(missiondescrip +"\n\n");
            }
            node++;//use for the for loop to work without weird formating and spacing issues
            if(node>3) node=1;//mode 1: date and rocket/mission, mode 2: launch window and location mode 3 flight description
        }
        return schedule;
    }
    /**
     * This method gets information from https://spaceflightnow.com/launch-schedule/ about upcoming space launches, times, dates, missions, and returns that information in a LinkedList.
     * @return LinkedList - Returns information about upcoming spaces launches including: time, date, mission, and launch vehicle.
     * @throws IOException Throws an IOException if connection to https://spaceflightnow.com/launch-schedule/ cannot be made.
     */
    public static LinkedList<String> getLaunchCal() throws IOException{
        return LaunchCal();
    }
    private static LinkedList<String> wiuEvents() throws IOException{
        final Document wiu = Jsoup.connect("http://www.wiu.edu/wiucalendar/").get();
        LinkedList<String> eventList = new LinkedList<>();
        for(Element event : wiu.select("div#main h3.date, tr")){
            String curEvent = event.text();
            eventList.add(curEvent);
        }
        return eventList;
    }
    /**
     * This method gets information from http://www.wiu.edu/wiucalendar/ about events happen around campus. The first node is always a day, potentially followed by an event.
     * @return LinkedList - Returns information about events in the form of a LinkedList that holds strings.
     * @throws IOException Throws an IOException if connection to http://www.wiu.edu/wiucalendar/ cannot be made.
     */
    public static LinkedList<String> getWiuEvents() throws IOException{
        LinkedList<String> allEvents = wiuEvents();
        Scanner sc;
        LinkedList<String> curEvents = new LinkedList<>();
        while(!allEvents.isEmpty()){
            curEvents.add(allEvents.remove());
            sc = new Scanner(allEvents.getFirst());
            String temp = sc.next();
            //this if loop makes sure only a day of events is returned
            if(temp.equalsIgnoreCase("Monday,")||temp.equalsIgnoreCase("Tuesday,")||temp.equalsIgnoreCase("Wednesday,")||temp.equalsIgnoreCase("Thursday,")||temp.equalsIgnoreCase("Friday,")||temp.equalsIgnoreCase("Saturday,")||temp.equalsIgnoreCase("Sunday,")){
                break;
            }
        }
        return curEvents;
    }
    
    
    
}
