/*
    Huge shoutout to Mistapotta's video on the Twitter4j api in this video here with: https://youtu.be/kgj3mjclAsM ,
    I used it as a reference for the twitterer class with this project.
*/
package myBot;

import java.io.IOException;
import java.io.File;//to be added later (for random generated tweet)
import java.io.FileNotFoundException;//to be added later (for random generated tweet)
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.DirectMessage;
import twitter4j.DirectMessageList;
import twitter4j.ResponseList;
import myBot.Webscrape;

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
    //gets the most recent status
    /**
     * This method returns a single status object, the most recent status at the time of method call.
     * @return Most recent status in the form of an object of type status.
     * @throws TwitterException 
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
    public ResponseList<Status> getRecents() throws TwitterException{
        return twitter.getUserTimeline();
    }
    public ResponseList<Status> getMentions() throws TwitterException{
        return twitter.getMentionsTimeline();
    }
    public ResponseList<Status> getRetweets() throws TwitterException{//get retweets of me. I might add a method to get a specific post's retweets later.
        return twitter.getRetweetsOfMe();
    }
    //Age section and overloading.
    private long getAge(Status status){//returns age of post in miliseconds (starting date Jan 1 1970
        long age = status.getCreatedAt().getTime();
        return age;
    }
    private long getAge(DirectMessage message){
        long age = message.getCreatedAt().getTime();
        return age;
    }
    public long ageToDays(DirectMessage message){
        long age = System.currentTimeMillis()-getAge(message);
        return TimeUnit.MILLISECONDS.toDays(age);
    }
    public long ageToDays(Status status){
        long age = System.currentTimeMillis()-getAge(status);
        return TimeUnit.MILLISECONDS.toDays(age);
    }
    public long ageToHours(DirectMessage message){
        long age = System.currentTimeMillis()-getAge(message);
        return TimeUnit.MILLISECONDS.toHours(age);
    }
    public long ageToHours(Status status){
        long age = System.currentTimeMillis()-getAge(status);
        return TimeUnit.MILLISECONDS.toHours(age);
    }
    public long ageToMinutes(DirectMessage message){
        long age = System.currentTimeMillis()-getAge(message);
        return TimeUnit.MILLISECONDS.toMinutes(age);
    }
    public long ageToMinutes(Status status){
        long age = System.currentTimeMillis()-getAge(status);
        return TimeUnit.MILLISECONDS.toMinutes(age);
    }
    public long ageToSeconds(DirectMessage message){
        long age = System.currentTimeMillis()-getAge(message);
        return TimeUnit.MILLISECONDS.toSeconds(age);
    }
    public long ageToSeconds(Status status){
        long age = System.currentTimeMillis()-getAge(status);
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
    public void sendMessage(String message, long target) throws TwitterException{
        twitter.sendDirectMessage(target, message);
    }
    
    //*****tweeting methods*****
    //Work in progress
    //this tweets weather conditions (for Macomb, IL. To change the location, just change that URL to your local National Weather Service location
    public void weatherTweet() throws IOException, TwitterException{
        tweetOut(Webscrape.getWeather());//tweets here
    }
    public void randomTweet() throws IOException, TwitterException{
        tweetOut(randomTweetGen());//also for testing purposes
    }
    private String randomTweetGen() throws FileNotFoundException{
        File statements = new File("C:/Users/Evan/Documents/Coding Adventures/Personal Projects/Personal Java/Experimental/WeatherTwitterBot/src/statements.txt");
        File responses = new File("C:/Users/Evan/Documents/Coding Adventures/Personal Projects/Personal Java/Experimental/WeatherTwitterBot/src/responses.txt");
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

    
    
    
}