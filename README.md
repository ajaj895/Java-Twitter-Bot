# Java-Twitter-Bot
This is a twitter bot I am working on using the java Twitter4J library.

This program will also be using the JSoup library to get the local weather, rocket launches, and/or local/university events.
I am currently in the process of making interactive with both direct messages and mentions.
This means that you can customize what information it's pulling and posting with relative ease.

You do have to provide twitter4j.properties file in the root of your classpath with your keys/tokens in this format suggested by twitter4j:

debug=true
oauth.consumerKey= ____________
oauth.consumerSecret= ___________
oauth.accessToken= ____________
oauth.accessTokenSecret= __________

Also Javadocs don't exist yet, I am working on it ... eventually.

Huge shoutout to Mistapotta's video on the Twitter4j api in this video here with: https://youtu.be/kgj3mjclAsM ,  I used it as a reference for the twitterer class with this project.
