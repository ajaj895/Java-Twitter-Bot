package com.ajaj895.twitterbot;

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
*/

import twitter4j.Status;
import twitter4j.TwitterException;
import com.ajaj895.twitterbot.twitterer.core.Twitterer;

public class twitterbot {
    //look into resources or file resources for making the statements and the responses form.
    private static PrintStream consolePrint;//a printstream prints all of what java does. Idk much else though

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException, IOException, InterruptedException {
        //##### initializing phase #####
        Twitterer tweety = new Twitterer(consolePrint);
        try {
            tweety.tweetOut("This is a test numero 2");
        } catch(IllegalStateException e) { // If keys aren't present
            System.err.println("Error! Authentication keys need to be placed in the root folder of this program. " +
                    "The file needs to be named twitter4j.properties and should include the following: \n" +
                    "debug=true\n" +
                    "oauth.consumerkey=[your key here]\n" +
                    "oauth.consumersecret=[your secret here]\n" +
                    "oauth.accesstoken=[your access token here]\n" +
                    "oauth.accesstokensecret=[your secret token here]\n" +
                    "With all of the [] replaced with your tokens/keys/secrets that can be applied for at " +
                    "https://developer.twitter.com/en");
        }



    }
}
