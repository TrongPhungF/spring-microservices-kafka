package com.microservices.demo.twitter.to.kafka.service.runner.impl;

import com.microservices.demo.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.listener.TwitterKafkaStatusListener;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;
import java.util.Arrays;

/**
 * <p>
 * TwitterKafkaStreamRunner class
 * </p>
 *
 * @author Phung Huynh
 */
@Component
@ConditionalOnProperty(name = "twitter-to-kafka-service.enable-mock-tweets", havingValue = "false", matchIfMissing = true)
public class TwitterKafkaStreamRunner implements StreamRunner {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStreamRunner.class);

    /**
     * TwitterToKafkaServiceConfigData
     */
    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;

    /**
     * TwitterKafkaStatusListener
     */
    private final TwitterKafkaStatusListener twitterKafkaStatusListener;

    /**
     * TwitterStream
     */
    private TwitterStream twitterStream;

    /**
     * <p>
     * Constructor
     * </p>
     * @param configData TwitterToKafkaServiceConfigData
     * @param statusListener TwitterKafkaStatusListener
     */
    public TwitterKafkaStreamRunner(TwitterToKafkaServiceConfigData configData,
                                    TwitterKafkaStatusListener statusListener) {
        this.twitterToKafkaServiceConfigData = configData;
        this.twitterKafkaStatusListener = statusListener;
    }

    /**
     * <p>
     * Start the twitter stream
     * </p>
     *
     * @author Phung Huynh
     */
    @Override
    public void start() throws TwitterException {
        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(twitterKafkaStatusListener);
        addFilter();
    }

    /**
     * <p>
     * shutdown
     * </p>
     *
     * @author Phung Huynh
     */
    @PreDestroy
    public void shutdown() {
        if (twitterStream != null) {
            LOG.info("Closing twitter stream!");
            twitterStream.shutdown();
        }
    }

    /**
     * <p>
     * addFilter
     * </p>
     *
     * @author Phung Huynh
     */
    private void addFilter() {
        String[] keywords = twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
        LOG.info("Started filtering twitter stream for keywords {}", Arrays.toString(keywords));
    }
}
