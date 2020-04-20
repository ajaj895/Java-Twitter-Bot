/*
    Huge shoutout to Mistapotta's video on the Twitter4j api in this video here with: https://youtu.be/kgj3mjclAsM ,
    I used it as a reference for the twitterer class with this project.
*/
package twitterer.core;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import twitterer.webscrape.Webscrape;
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
public class Twitterer {
    private Twitter twitter;
    private PrintStream consolePrint;
    private List<Status> statuses;
    /**
     * Constructor for the Twitterer object.
     * 
     * @param console the PrintStream used to show the events of the program
     */
    public Twitterer(PrintStream console){
        //makes a twitter object and connects and does that login stuff
        twitter = TwitterFactory.getSingleton();
        consolePrint = console;
        
    }
    //tweets a given message
    /**
     * 
     * @param message  A message you wish to tweet out.
     * @throws TwitterException Throws a TwitterException if no connection with twitter is made or an error had occurred when tweeting.
     * @throws IOException Throws a IOException if a connection to the internet couldn't be made.
     */
    public void  tweetOut(String message) throws TwitterException, IOException{
        Status status = twitter.updateStatus(message);
        System.out.println("Status has been updated to: "+ status.getText());
    }
    //gets the most recent status
    /**
     * This method returns a single status object, the most recent status at the time of method call.
     * @return Most recent status in the form of an object of type status.
     * @throws TwitterException Throws a Twitter Exception if connection cannot be made to twitter, or an internal error within twitter.
     */
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
    /**
     * This method returns the 20 most recent statuses of the authenticating user in a ResponseList of type Status.
     * @return ResponseList - Stores 20 of the most recent statuses, returns null if empty.
     * @throws TwitterException Throws a TwitterException if connection to twitter cannot be made.
     */
    public ResponseList<Status> getRecents() throws TwitterException{
        return twitter.getUserTimeline();
    }
    /**
     * This method returns the 20 most recent mentions of the authenticating user (you) in a ResponseList of type Status.
     * @return ResponseList - Stores 20 of the most recent statuses that mention the authenticating user, returns null if empty.
     * @throws TwitterException Throws a TwitterException if connection to twitter cannot be made.
     */
    public ResponseList<Status> getMentions() throws TwitterException{
        return twitter.getMentionsTimeline();
    }
    /**
     * This method returns the 20 most recent statuses of the authenticating user that have been retweeted in a ResponseList of type Status.
     * @return ResponseList - Stores 20 of the most recent statuses of the authenticating user that have been retweeted, returns null if empty.
     * @throws TwitterException Throws a TwitterException if connection to twitter cannot be made.
     */
    public ResponseList<Status> getRetweets() throws TwitterException{//get retweets of me. I might add a method to get a specific post's retweets later.
        return twitter.getRetweetsOfMe();
    }
    //###### Age section and overloading. ######

    private long getAge(Status status){//returns age of post in miliseconds (starting date Jan 1 1970
        long age = status.getCreatedAt().getTime();
        return age;
    }
    private long getAge(DirectMessage message){
        long age = message.getCreatedAt().getTime();
        return age;
    }
    /**
     * This method gets the age of a message in days.
     * @param message This parameter is message that will checked for it's age.
     * @return long - Returns the age of the message in days.
     */
    public long ageToDays(DirectMessage message){
        long age = System.currentTimeMillis()-getAge(message);
        return TimeUnit.MILLISECONDS.toDays(age);
    }
    /**
     * This method gets the age of a status in days.
     * @param status This parameter is the status that will be checked for it's age.
     * @return long - Returns the age of the status in days.
     */
    public long ageToDays(Status status){
        long age = System.currentTimeMillis()-getAge(status);
        return TimeUnit.MILLISECONDS.toDays(age);
    }
    /**
     * This method gets the age of a message in hours.
     * @param message This parameter is the message that will be checked for it's age.
     * @return long - Returns the age of the message in hours.
     */
    public long ageToHours(DirectMessage message){
        long age = System.currentTimeMillis()-getAge(message);
        return TimeUnit.MILLISECONDS.toHours(age);
    }
    /**
     * This method gets the age of a status in hours.
     * @param status This parameter is the status that will be checked for it's age.
     * @return long - Returns the age of the status in hours.
     */
    public long ageToHours(Status status){
        long age = System.currentTimeMillis()-getAge(status);
        return TimeUnit.MILLISECONDS.toHours(age);
    }
    /**
     * This method gets the age of a message in minutes.
     * @param message This parameter is the message that will be checked for it's age.
     * @return long - Returns the age of the message in minutes.
     */
    public long ageToMinutes(DirectMessage message){
        long age = System.currentTimeMillis()-getAge(message);
        return TimeUnit.MILLISECONDS.toMinutes(age);
    }
    /**
     * This method gets the age of a status in minutes.
     * @param status This parameter is the status that will be checked for it's age.
     * @return long - Returns the age of the status in minutes.
     */
    public long ageToMinutes(Status status){
        long age = System.currentTimeMillis()-getAge(status);
        return TimeUnit.MILLISECONDS.toMinutes(age);
    }
    /**
     * This method gets the age of a message in seconds.
     * @param message This parameter is the message that will be checked for it's age.
     * @return long - Returns the age of the message in seconds.
     */
    public long ageToSeconds(DirectMessage message){
        long age = System.currentTimeMillis()-getAge(message);
        return TimeUnit.MILLISECONDS.toSeconds(age);
    }
    /**
     * This method gets the age of a status in seconds.
     * @param status This parameter is the status that will be checked for it's age.
     * @return long - Returns the age of the status in seconds.
     */
    public long ageToSeconds(Status status){
        long age = System.currentTimeMillis()-getAge(status);
        return TimeUnit.MILLISECONDS.toSeconds(age);
    }
    
    private long myId() throws TwitterException{
        return twitter.getId();
    }
    /**
     * This method checks to see if a status is a day (or more) old.
     * @param status This parameter is the status that will be checked if it is a day or more old.
     * @return boolean - Returns a true/false value if ageToDays() is greater than 0
     */
    public boolean dayOld(Status status){
        return (ageToDays(status)>0);
    }
    /**
     * This method will check a status for an inputed keyword.
     * @param word The word that will be searched for in the status.
     * @param status The status that will be searched through for the keyword.
     * @return boolean - Returns a true/false value if the keyword is found in the given status.
     */
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
    /**
     * This method will check a message for an inputed keyword.
     * @param word The word that will be searched for in the message.
     * @param message The message that will be searched through for the keyword. 
     * @return boolean - Returns a true/false value if the keyword is found in the given message.
     */
    public boolean keywordCheck(String word, DirectMessage message){
        Scanner sc = new Scanner(message.getText());
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
    /**
     * This method will get all messages and put them in a DirectMessageList.
     * @param howMany The number of messages to return (max: 50, min: 0).
     * @return DirectMessageList - Returns the DirectMessageList that contains all message types up to 50 messages.
     * @throws TwitterException Throws a TwitterException if connection to Twitter cannot be made.
     */
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
    /**
     * This method will sort direct messages into the proper category of sent and received messages.
     * @param allMessages An unsorted DirectMessageList containing sent and received messages.
     * @param sentMessages A DirectMessageList used to store sent messages.
     * @param receivedMessages A DirectMessageList used to store received messages.
     * @throws TwitterException Throws a TwitterException if a connection to Twitter cannot be made.
     */
    public void getReceivedMessages(DirectMessageList allMessages, LinkedList<DirectMessage> sentMessages, LinkedList<DirectMessage> receivedMessages) throws TwitterException{
        for(int i = 0; i<allMessages.size();){
            if(allMessages.get(i).getRecipientId() == myId() && ageToDays(allMessages.get(i))<1){
                receivedMessages.addLast(allMessages.remove(i));
            }else if(ageToDays(allMessages.get(i))<1){
                sentMessages.addLast(allMessages.remove(i));
            }
            else{
                allMessages.remove(i);
            }
        }
        
    }
    /**
     * This method will send a message, a String, to a target, a twitter id in the form of a long.
     * @param message A String to be sent.
     * @param target A Twitter id in the form of a long.
     * @throws TwitterException Throws a TwitterException if a connection to Twitter cannot be made. Also throws a TwitterException if an error occurs when sending the message.
     */
    public void sendMessage(String message, long target) throws TwitterException{
        twitter.sendDirectMessage(target, message);
    }
    
    //todo doc
    public String getPath(){
        return System.getProperty("user.dir");
    }
    
    //*****tweeting methods*****
    //Work in progress
    //this tweets weather conditions (for Macomb, IL. To change the location, just change that URL to your local National Weather Service location
    /**
     * This method will tweet out a weather related method (to Macomb, IL), obtaining it's information from the Webscrape class.
     * @throws IOException Throws an IOException if the webscraper was not able to connect to it's target website. 
     * @throws TwitterException Throws a TwitterException if a connection to Twitter cannot be made. Also throws a TwitterException if an error occurs when tweeting out.
     * @see Webscrape
     */
    public void weatherTweet() throws IOException, TwitterException{
        tweetOut(Webscrape.getWeather());//tweets here
    }
    /**
     * This method tweets out a randomly generated string from a list of statements and responses in text files.
     * @throws IOException Throws an IOException if the files could not be open or found.
     * @throws TwitterException Throws a TwitterException if a connection to Twitter could not be made. Also throws a TwitterException if an error occurs when tweeting out.
     */
    public void randomTweet(File statements, File responses) throws IOException, TwitterException{
        tweetOut(randomTweetGen(statements, responses));
    }
    private String randomTweetGen(File statements, File responses) throws FileNotFoundException{
        Scanner statementSc = null;
        Random rand = new Random();
        try{
            statementSc = new Scanner(statements);
        }catch(FileNotFoundException e){
            System.err.println("Error: Could not find or open statements.txt. Program will now exit.");
            System.exit(1);
        }
        Scanner responseSc = null;
        try{
            responseSc = new Scanner(responses);
        }catch(FileNotFoundException e){
            System.err.println("Error: Could not find or open the responses.txt. Program will now exit.");
            System.exit(1);
        }
        //Random values -------------------------------------------------------
        int lineRand = rand.nextInt(21);//int storing what random line 21 is the number of lines in statements.txt
        int responseRand = rand.nextInt(14);//int storing the random line value, 14 is the number of lines in responses.txt
        //Line selection loop -------------------------------------------------
        for(int i = 0; i<lineRand; i++){//for getting a random statement
            statementSc.nextLine();
        }
        Scanner lineSc = new Scanner(statementSc.nextLine());
        
        String content = "";
        int responseNum = lineSc.nextInt();//this stores how many responses are needed for the given statement
        int i = 1;//for input values (in1 in2 in3 etc)
        //Statement printing and selection loop -------------------------------
        while(lineSc.hasNext()){
            String current = lineSc.next();
            String in = "in"+i;
            String resp = "cool";
            if(in.equalsIgnoreCase(current) && i<=responseNum){
                for(int f = 0; f<responseRand;f++){
                    responseSc.nextLine();
                }
                resp = responseSc.nextLine();
                content = content+" "+resp+" ";
                for(int r=responseRand; r == responseRand;){
                    responseRand=rand.nextInt(14);
                }
                i++;
            }else{
                content = content+" "+current+" ";
            }
            responseSc = new Scanner(responses);
        }
        content = content+"\n";
        return content;
    }
    public void eventsTweet() throws IOException, TwitterException, InterruptedException{
        tweetOut(eventsGet());
    }
    private String eventsGet() throws IOException, TwitterException, InterruptedException{//Tweets events if more than 280 characters.
        LinkedList<String> myList = Webscrape.getWiuEvents();
        String storeString = "Today's events:";
        while(!myList.isEmpty()){
            String current = myList.remove();//280 charater limit
            if(storeString.length() + current.length() > 280){
                tweetOut(storeString);
                Thread.sleep(5000);//waits 5 seconds before continuing.
                storeString = "Events Continued:\n"+current;
            } else {
                storeString = storeString + "\n" + current;
            }
            
        }
        return storeString;
    }
  
}