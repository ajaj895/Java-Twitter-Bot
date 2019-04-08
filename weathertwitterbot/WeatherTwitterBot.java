/*
    Huge shoutout to Mistapotta's video on the Twitter4j api in this video here with: https://youtu.be/kgj3mjclAsM ,
    I used it as a reference for the twitterer class with this project.
*/
package weathertwitterbot;

//import myBot.Twitterer;
import java.io.IOException;
import java.io.PrintStream;
/* The scanner implementation of the statements and responses will be added later.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
*/
import java.util.concurrent.TimeUnit;
/*
import twitter4j.DirectMessage;
import twitter4j.DirectMessageList;
import twitter4j.ResponseList;
*/
import twitter4j.Status;
import twitter4j.TwitterException;
import myBot.Twitterer;

/**
 *
 * @author Evan
 * 
 */
public class WeatherTwitterBot{//TODO: Javadocs and major comment cleanup like seriously, this is a MESS
    
    private static PrintStream consolePrint;//a printstream prints all of what java does. Idk much else though

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException, IOException, InterruptedException {
        //initializing phase
        Twitterer tweety = new Twitterer(consolePrint);
        boolean run = true;
        Status recentStatus = tweety.getRecent();
        //System.out.println(allMessages.size()+" "+sentMessages.size()+" "+receivedMessages.size());//oldest messages are last in the list, newest messages are first in the list
        int i = 0;

        while(run == true){
            try{
                recentStatus = tweety.getRecent();
                if(tweety.ageToHours(recentStatus)>1){
                    try{
                        tweety.randomTweet();
                        i++;
                    }catch(IOException | TwitterException e){//java multi-catch statement for catching different types of exceptions
                        System.err.println("Error connecting to twitter. Will try again later. Error: "+e);
                    }
                        
                }
            }catch(TwitterException e){
                System.err.println("Error connecting to twitter. Will try again later. Error: "+e);
            }
            if(i>12){//will tweet every hour and 30 minutes
                run = false;
            }
            Thread.sleep(TimeUnit.MINUTES.toMillis(30));//checks every 30 minutes
        }
        
        //oldest messages are last in the list, newest messages are first in the list

    }
}
