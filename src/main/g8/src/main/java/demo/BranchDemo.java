package demo;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.Predicate;
import utils.ConfigReader;

import java.util.Properties;

class BranchDemo {

    private ConfigReader configReader = new ConfigReader();

    void filterTweets() {

        Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "filter-tweets");
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, configReader.getKafkaServers());

        final Serde<String> stringSerde = Serdes.String();

        Predicate<String, String> christmasPredicate = (dummy, value) -> value.contains("#Christmas") || value.contains("#Y100JingleBall");

        Predicate<String, String> photoPredicate = (dummy, value) -> value.contains("#Photography");

        KStreamBuilder builder = new KStreamBuilder();


        KStream<String, String> feeds = builder.stream(stringSerde, stringSerde, configReader.getKafkaTopic());
        KStream<String, String>[] branches = feeds.branch(christmasPredicate, photoPredicate);


        branches[0].to("christmas-tweet");
        branches[1].to("photography-tweet");

        KafkaStreams streams = new KafkaStreams(builder, streamsConfiguration);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

}
