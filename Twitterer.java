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
import java.util.concurrent.TimeUnit;
import twitter4j.ResponseList;

/**
 *
 * @author Evan
 */
public class Twitterer {//TODO: J A V A D O C S!
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
    
    public Status getRecent() throws TwitterException{
        ResponseList<Status> status = twitter.getUserTimeline();
        Status currentStatus = null; 
        if(status.isEmpty()){
            System.err.println("Error: There are no statuses to return.");
        }else{
            currentStatus = status.get(0);
        }
        return currentStatus;
    }
    //returns the 20 most recent statuses of the authenticating user (you), if empty, will prolly return null.
    public ResponseList<Status> getRecents() throws TwitterException{
        return twitter.getUserTimeline();
    }
    
    private long getPostAge(Status status){//returns age of post in miliseconds (starting date Jan 1 1970
        long age = status.getCreatedAt().getTime();
        return age;
    }
    public long ageToDays(Status status){
        long age = System.currentTimeMillis()-getPostAge(status);
        return TimeUnit.MILLISECONDS.toDays(age);
    }
    public long ageToHours(Status status){
        long age = System.currentTimeMillis()-getPostAge(status);
        return TimeUnit.MILLISECONDS.toHours(age);
    }
    public long ageToMinutes(Status status){
        long age = System.currentTimeMillis()-getPostAge(status);
        return TimeUnit.MILLISECONDS.toMinutes(age);
    }
    public long ageToSeconds(Status status){
        long age = System.currentTimeMillis()-getPostAge(status);
        return TimeUnit.MILLISECONDS.toSeconds(age);
    }
    public boolean dayOld(Status status){
        return (ageToDays(status)>0);
    }
    
}
