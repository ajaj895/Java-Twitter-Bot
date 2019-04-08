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

The twitterer class has a random tweet method, where you can create your own text files (2 of them to be exact), with Statements and responses. The statements file requires a certain format with an int as a the first character to tell the program how many inputs will be in the statement and each random response to be added to statement as in(the index of the inputs). Here is an example:

3 Here is an example, in1 , in2 , and in3 are the random responses.

Make sure you return after each statement, the program uses scanner.nextLine() 
The response file is a lot easier, just a response per line also using scanner.nextLine()

Also Javadocs don't exist yet, I am working on it ... eventually.

Huge shoutout to Mistapotta's video on the Twitter4j api in this video here with: https://youtu.be/kgj3mjclAsM ,  I used it as a reference for the twitterer class with this project.
