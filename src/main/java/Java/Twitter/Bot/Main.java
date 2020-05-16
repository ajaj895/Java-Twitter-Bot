/*
    Huge shoutout to Mistapotta's video on the Twitter4j api in this video here with: https://youtu.be/kgj3mjclAsM ,
    I used it as a reference for the twitterer class with this project.
*/

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
import twitterer.core.Twitterer;

/**
 * This is a multi-role twitter bot that currently tweets out different tweets in randomized statements, or webscraped information.
 * @author Evan
 * 
 */
public class Main{//TODO: Javadocs
     //look into resources or file resources for making the statements and the responses form.
    private static PrintStream consolePrint;//a printstream prints all of what java does. Idk much else though

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException, IOException, InterruptedException {
        //##### initializing phase #####
        Twitterer tweety = new Twitterer(consolePrint);

        tweety.tweetOut("This is a test numero 2");


    }
}
