/*
 * Copyright (c) 2016 Matthew Fong
 */

package com.eliza.redwall.util.retry;

public class NotRetryableException extends RetryFailedException {

    public NotRetryableException(String message) {
        super(message);
    }

    public NotRetryableException(Throwable cause) {
        super(cause);
    }

    public NotRetryableException(String message, Throwable cause) {
        super(message, cause);
    }
}
