package Twitter;


import kstreams.KafkaProducer;
import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import utils.ConfigReader;

import java.io.IOException;

public class TwitterFeeds {
    private ConfigReader configReader = new ConfigReader();

    private TwitterStream getTwitterConfig() {

        Configuration twitterConf = new ConfigurationBuilder()
                .setOAuthConsumerKey(configReader.getTwitterConsumerKey())
                .setOAuthConsumerSecret(configReader.getTwitterConsumerSecretKey())
                .setOAuthAccessToken(configReader.getTwitterAccessToken())
                .setOAuthAccessTokenSecret(configReader.getTwitterAccessSecretToken())
                .build();
        return new TwitterStreamFactory(twitterConf).getInstance();
    }

    public void sendTweetsToKafka() throws TwitterException, IOException {

        StatusListener listener = new StatusListener() {
            public void onStatus(Status status) {
                String feed = status.getText();
                new KafkaProducer().send(feed);
                System.out.println("Sent: " + feed);
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                //Do Nothing
            }

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                //Do Nothing
            }

            public void onScrubGeo(long l, long l1) {
                //Do Nothing
            }

            public void onStallWarning(StallWarning stallWarning) {
                //Do Nothing
            }

            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        TwitterStream twitterStream = getTwitterConfig();
        twitterStream.addListener(listener);
        twitterStream.sample("en");

    }
}
