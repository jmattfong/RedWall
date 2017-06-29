/*
 * Copyright (c) 2016 Matthew Fong
 */

package com.eliza.redwall.util.retry;

import com.eliza.redwall.util.function.UnsafeCallable;
import com.eliza.redwall.util.function.UnsafeSupplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Retry {

    public static <T> T retry(UnsafeSupplier<T> supplier) throws RetryFailedException {
        // TODO
        log.info("retrying supplier - TODO NOT IMPLEMENTED");
        try {
            return supplier.get();
        } catch (Exception e) {
            throw new RetryFailedException("Retries failed", e);
        }
    }

    public static void retry(UnsafeCallable callable) throws RetryFailedException {
        // TODO
        log.info("retrying callable - TODO NOT IMPLEMENTED");
        try {
            callable.call();
        } catch (Exception e) {
            throw new RetryFailedException("Retries failed", e);
        }
    }
}
