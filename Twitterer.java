/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weathertwitterbot;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;

/**
 *
 * @author Evan
 */
public class Twitterer {
    private Twitter twitter;
    private PrintStream consolePrint;//TODO: figure out what this is
    private List<Status> statuses;
    
    public Twitterer(PrintStream console){
        //makes a twitter object and connects and does that login stuff
        twitter = TwitterFactory.getSingleton();
        consolePrint = console;
        statuses = new ArrayList<Status>();
    }
    //tweets a given message
    /**
     * 
     * @param message  A message you wish to tweet out.
     * @throws TwitterException
     * @throws IOException 
     */
    public void  tweetOut(String message) throws TwitterException, IOException{
        Status status = twitter.updateStatus(message);
        System.out.println("Status has been updated to: "+ status.getText());
    }
    
}
