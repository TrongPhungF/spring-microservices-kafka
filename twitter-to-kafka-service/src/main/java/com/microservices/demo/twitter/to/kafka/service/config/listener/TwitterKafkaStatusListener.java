package com.microservices.demo.twitter.to.kafka.service.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

/**
 * <p>
 * Twitter Kafka Status Listener
 * </p>
 *
 * @author Phung Huynh
 */
@Component
public class TwitterKafkaStatusListener extends StatusAdapter {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStatusListener.class);

    /**
     * <p>
     *    This method is called when a tweet is received
     * </p>
     * @param status Status
     *
     * @author Phung Huynh
     */
    @Override
    public void onStatus(Status status) {
        LOG.info("Twitter status with text {}", status.getText());
    }

}

