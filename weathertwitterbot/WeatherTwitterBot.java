/*
    Huge shoutout to Mistapotta's video on the Twitter4j api in this video here with: https://youtu.be/kgj3mjclAsM ,
    I used it as a reference for the twitterer class with this project.
*/
package weathertwitterbot;

//import myBot.Twitterer;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/* The scanner implementation of the statements and responses will be added later.
import twitter4j.DirectMessage;
import twitter4j.DirectMessageList;
import twitter4j.ResponseList;
*/

import twitter4j.Status;
import twitter4j.TwitterException;
import myBot.Twitterer;

/**
 * This is a multi-role twitter bot that currently tweets out different tweets in randomized statements, or webscraped information.
 * @author Evan
 * 
 */
public class WeatherTwitterBot{//TODO: Javadocs
    
    private static PrintStream consolePrint;//a printstream prints all of what java does. Idk much else though

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException, IOException, InterruptedException {
        //##### initializing phase #####
        Twitterer tweety = new Twitterer(consolePrint);
        boolean run = true;
        Scanner filenameSc = new Scanner(System.in);
        //##### File finding phase #####
        System.out.println("Enter the path of the statements file: ");
        File statements = new File(filenameSc.nextLine());
        System.out.println("Enter the path of the responses file: ");
        File responses = new File(filenameSc.nextLine());//TODO: The rest of the file phase.
        //C:/Users/Evan/Documents/Coding Adventures/Personal Projects/Personal Java/Experimental/WeatherTwitterBot/src/statements.txt
        
        
        //System.out.println(allMessages.size()+" "+sentMessages.size()+" "+receivedMessages.size());//oldest messages are last in the list, newest messages are first in the list
        //##### Tweeting phase #####
        Status recentStatus = tweety.getRecent();//TODO: Put this in a try catch
        int i = 0;
        tweety.eventsTweet();
        while(run == true){
            try{
                recentStatus = tweety.getRecent();
                if(tweety.ageToHours(recentStatus)>1){
                    try{
                        tweety.randomTweet(statements, responses);
                        i++;
                    }catch(IOException | TwitterException e){//java multi-catch statement for catching different types of exceptions
                        System.err.println("Error connecting to twitter. Will try again later. Error: "+e);
                    }
                        
                }
            }catch(TwitterException e){
                System.err.println("Error connecting to twitter. Will try again later. Error: "+e);
            }
            if(i>12){//will tweet every hour and 30 minutes, runs for 18 hours
                run = false;
            }
            Thread.sleep(TimeUnit.MINUTES.toMillis(30));//checks every 30 minutes
        }


    }
}
