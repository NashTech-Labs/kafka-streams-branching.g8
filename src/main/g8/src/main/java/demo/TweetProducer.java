package demo;

import twitter.TwitterFeeds;
import twitter4j.TwitterException;

import java.io.IOException;

public class TweetProducer {

    public static void main(String[] args) throws TwitterException, IOException {
        new TwitterFeeds().sendTweetsToKafka();
    }

}
