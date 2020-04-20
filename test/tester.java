/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Evan
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import twitter4j.DirectMessage;
import twitter4j.DirectMessageList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitterer.webscrape.Webscrape;
import twitterer.core.Twitterer;
/*
    This is where I test new features 
*/
public class tester {
    
    private static PrintStream consolePrint;
    public static void main(String[] args) throws InterruptedException, TwitterException, IOException{
        
        
        long time = System.currentTimeMillis();
        System.out.println(TimeUnit.MILLISECONDS.toDays(time)/365);
        if(TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis())-time > 0 ) System.out.print("Here is more than a day");
        else System.out.println("Here is not a day");
        System.out.println(System.getProperty("user.dir"));
        Twitterer tweety = new Twitterer(consolePrint);
        System.out.println(tweety.getPath());
        System.out.println(tweety.getPath()+"\\test.txt");
        try{
            File test = new File(tweety.getPath()+"\\test.txt");//file path logic
            Scanner sc = new Scanner(test);
            String msg = sc.next();
            System.out.println(msg);
            tweety.tweetOut(msg);
        } catch(FileNotFoundException e){
            System.err.println("ERROR");
        }
        
        //tweety.eventsTweet();
        //tweety.randomTweet();
        /*
        String recentTweet = tweety.getRecent().getText();//use .getText() for the status it self, toString will give you more hidden information about the tweet.
        
        System.out.println("\n\n\n"+recentTweet+"\nThis status is "+tweety.ageToDays(tweety.getRecent())+" days and "+(tweety.ageToHours(tweety.getRecent())-(tweety.ageToDays(tweety.getRecent())*24))+" hours old.");
        System.out.println("Is this a day or more old?: "+tweety.dayOld(tweety.getRecent()));
        DirectMessageList allMessages = tweety.getAllMessages(20);
        LinkedList<DirectMessage> sentMessages = new LinkedList<>();
        LinkedList<DirectMessage> receivedMessages = new LinkedList<>();
        
        for(int i = 0; i<allMessages.size();i++){
            System.err.println("Receiver: " + allMessages.get(i).getRecipientId() + " Sender: "+allMessages.get(i).getSenderId());
        }
        tweety.getReceivedMessages(allMessages, sentMessages, receivedMessages);
        System.out.println(allMessages.size()+ " " + sentMessages.size()+ " " + receivedMessages.size());
        for(int i = 0; i<sentMessages.size();i++){
            System.out.println(sentMessages.size());
        }
        System.out.println(tweety.keywordCheck("java", tweety.getRecent()));
        if(tweety.ageToMinutes(tweety.getRecent())>15){
            tweety.weatherTweet();
        }else{
            System.err.println("Error: Not enough time has passed since last tweet.");
        }
        long sender = receivedMessages.getFirst().getSenderId();
        if(tweety.keywordCheck("and", receivedMessages.getFirst())){
            tweety.sendMessage("I know what you mean.", sender);
        }else{
            tweety.sendMessage("I don't know what you mean.", sender);
        }
        */
        
    }
    
}
