package com.microservices.demo.twitter.to.kafka.service.config.runner;

import twitter4j.TwitterException;

public interface StreamRunner {

    void start() throws TwitterException;
}
