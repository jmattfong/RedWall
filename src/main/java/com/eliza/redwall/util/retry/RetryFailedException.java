/*
 * Copyright (c) 2016 Matthew Fong
 */

package com.eliza.redwall.util.retry;

public class RetryFailedException extends Exception {

    public RetryFailedException(String message) {
        super(message);
    }

    public RetryFailedException(Throwable cause) {
        super(cause);
    }

    public RetryFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
