/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weathertwitterbot;

import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.DirectMessage;
import twitter4j.DirectMessageList;
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
    public ResponseList<Status> getMentions() throws TwitterException{
        return twitter.getMentionsTimeline();
    }
    public ResponseList<Status> getRetweets() throws TwitterException{//get retweets of me. I might add a method to get a specific post's retweets later.
        return twitter.getRetweetsOfMe();
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
    private long myId() throws TwitterException{
        return twitter.getId();
    }
    public boolean dayOld(Status status){
        
        return (ageToDays(status)>0);
    }
    public boolean keywordCheck(String word, Status status){
        Scanner sc = new Scanner(status.getText());
        boolean key = false;
        while(sc.hasNext()){
            if(sc.next().equalsIgnoreCase(word)){
                key = true;
                return key;
            }
        } 
        return key;
    }
    //might return null if DirectMessageList is empty.
    public DirectMessageList getAllMessages(int howMany) throws TwitterException{
        if(howMany > 50){
            howMany = 50;
            System.err.println("Too many messages to return, returning the maximum of 50.");
        }else if(howMany < 0){
            howMany = 1;
            System.err.println("Message number can't be less than zero. Returning 1 message.");
        }
        return twitter.getDirectMessages(howMany);
    }
    public void getReceivedMessages(DirectMessageList allMessages, LinkedList<DirectMessage> sentMessages, LinkedList<DirectMessage> receivedMessages) throws TwitterException{
        for(int i = 0; i<allMessages.size();){
            if(allMessages.get(i).getRecipientId() == myId()){
                receivedMessages.addLast(allMessages.remove(i));
            }else{
                sentMessages.addLast(allMessages.remove(i));
            }
        }
        
    }

    
    
    
}