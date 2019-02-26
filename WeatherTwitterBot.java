/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weathertwitterbot;

import java.io.PrintStream;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import java.io.IOException;//for connection issues
import java.io.PrintStream;
import twitter4j.TwitterException;
/**
 *
 * @author Evan
 */
public class WeatherTwitterBot {
    //private static PrintStream consolePrint;
    
    private static PrintStream consolePrint;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException,IOException {
        
        Twitterer tweety = new Twitterer(consolePrint);
        
        String message= "Hello Twitter! This is a test of the java twitter4j API.";
        tweety.tweetOut(message);

        // TODO code application logic here
    }

    
}
