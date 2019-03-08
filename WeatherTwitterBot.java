/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weathertwitterbot;

//import java.io.PrintStream;
//import twitter4j.Status;
//import twitter4j.Twitter;
//import twitter4j.TwitterFactory;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;//for connection issues
import org.jsoup.nodes.Element;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 *
 * @author Evan
 */
public class WeatherTwitterBot{//TODO: Javadocs and major comment cleanup like seriously, this is a MESS
    
    private static PrintStream consolePrint;//a printstream prints all of what java does. Idk much else though
    private static boolean autoContinue = true;//a boolean for continuing automatic posting.


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException,IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        int runTime = 24;//in hours
        Twitterer tweety = new Twitterer(consolePrint);

        
    }
}
