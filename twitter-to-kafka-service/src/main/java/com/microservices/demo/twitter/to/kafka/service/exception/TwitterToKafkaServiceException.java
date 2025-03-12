package com.microservices.demo.twitter.to.kafka.service.exception;

/**
 * <p>
 * Custom exception class for TwitterToKafkaService
 * </p>
 *
 *
 * @author Phung Huynh
 */
public class TwitterToKafkaServiceException extends RuntimeException {

    /**
     * Constructor
     */
    public TwitterToKafkaServiceException() {
        super();
    }

    /**
     * <p>
     * Constructor
     * </p>
     * @param message String
     * @author Phung Huynh
     */
    public TwitterToKafkaServiceException(String message) {
        super(message);
    }

    /**
     * <p>
     * TwitterToKafkaServiceException
     * </p>
     *
     * @param message String
     * @param cause Throwable
     *
     * @author Phung Huynh
     */
    public TwitterToKafkaServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
