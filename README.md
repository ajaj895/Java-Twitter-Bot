# Java-Twitter-Bot
An automated twitter bot that reads two files to make randomly generated jokes from the sets of jokes and answers in the two files.

## AUTHOR
Evan Colwell
ec-colwell@wiu.edu
eccolwell99@gmail.com

## USAGE

to run the program, use the following command:

java -jar path/to/jar/[todo build a jar]

This will then ask for the location of the questions .txt file and then the answers .txt files, input the location and then the bot will then tweet out the current events for the day at western illinois university, and then the random jokes.

For more information, consider looking at the Twitter4j API here: twitter4j.org/javadoc/index.html  and this is a tutorial I used to learn Twitter4j to Mistapotta's video on the Twitter4j api in this video here with: https://youtu.be/kgj3mjclAsM , I used it as a reference for the twitterer class with this project. 

## ABOUT

this is a twitter bot i am working on using the java twitter4j library.

this program will also be using the jsoup library to get the local weather, rocket launches, and/or local/university events.
i am currently in the process of making interactive with both direct messages and mentions.
this means that you can customize what information it's pulling and posting with relative ease.

you do have to provide twitter4j.properties file in the root of your classpath with your keys/tokens in this format suggested by twitter4j:

debug=true
oauth.consumerkey= ____________
oauth.consumersecret= ___________
oauth.accesstoken= ____________
oauth.accesstokensecret= __________

the twitterer class has a random tweet method, where you can create your own text files (2 of them to be exact), with statements and responses. the statements file requires a certain format with an int as a the first character to tell the program how many inputs will be in the statement and each random response to be added to statement as in(the index of the inputs). here is an example:

3 here is an example, in1 , in2 , and in3 are the random responses.

make sure you return after each statement, the program uses scanner.nextline() 
the response file is a lot easier, just a response per line also using scanner.nextline()

each public method *except for main* have documentation.

huge shoutout to Mistapotta's video on the Twitter4j api in this video here with: https://youtu.be/kgj3mjclAsM ,  I used it as a reference for the twitterer class with this project.