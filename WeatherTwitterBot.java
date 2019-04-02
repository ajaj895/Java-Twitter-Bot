/*
    Huge shoutout to Mistapotta's video on the Twitter4j api in this video here with: https://youtu.be/kgj3mjclAsM ,
    I used it as a reference for the twitterer class with this project.
*/
package weathertwitterbot;

//import myBot.Twitterer;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import twitter4j.DirectMessage;
import twitter4j.DirectMessageList;
import twitter4j.ResponseList;
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
        Random rand = new Random();
        boolean run = true;
        Twitterer tweety = new Twitterer(consolePrint);
        DirectMessageList allMessages = tweety.getAllMessages(20);
        LinkedList<DirectMessage> sentMessages = new LinkedList<>();
        LinkedList<DirectMessage> receivedMessages = new LinkedList<>();
        //gets messages the most recent 
        tweety.getReceivedMessages(allMessages, sentMessages, receivedMessages);
        Status recentStatus = tweety.getRecent();
        //System.out.println(allMessages.size()+" "+sentMessages.size()+" "+receivedMessages.size());//oldest messages are last in the list, newest messages are first in the list
        int i = 0;
        int randomInt = rand.nextInt(6);
        while(run == true){
            randomInt = rand.nextInt(6);
            try{
                recentStatus = tweety.getRecent();
                if(tweety.ageToHours(recentStatus)>1){
                    try{
                        switch (randomInt) {
                            case 0:
                                tweety.tweetOut("Cool beans are infact, cool. "+i);
                                break;
                            case 1:
                                tweety.tweetOut("What if I told you that doors are just giant windows. "+i);
                                break;
                            case 2:
                                tweety.tweetOut("Bagels are just formal donuts, change my mind. "+i);
                                break;
                            case 3:
                                tweety.tweetOut("Zero-gravity is actually just falling forever but not hitting anything. "+i);
                                break;
                            case 4:
                                tweety.tweetOut("The python coding language was named after Monty Python. "+i);
                                break;
                            default:
                                tweety.tweetOut("The Sun has a light switch, we just haven't found it yet. "+i);
                                break;
                        }
                    i++;
                    }catch(IOException | TwitterException e){//java multi-catch statement for catching different types of exceptions
                        System.err.println("Error connecting to twitter. Will try again later. Error: "+e);
                    }
                        
                }
            }catch(TwitterException e){
                System.err.println("Error connecting to twitter. Will try again later. Error: "+e);
            }
            if(i>24){
                run = false;
            }
            Thread.sleep(TimeUnit.MINUTES.toMillis(30));
        }
    }
}
