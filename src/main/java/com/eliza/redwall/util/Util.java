/*
 * Copyright (c) 2016 Matthew Fong
 */

package com.eliza.redwall.util;

import com.eliza.redwall.util.function.UnsafeCallable;
import com.eliza.redwall.util.function.UnsafeSupplier;
import com.eliza.redwall.util.retry.Retry;
import com.eliza.redwall.util.retry.RetryFailedException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Util {

    public static <T> T retryWithMetrics(String metricName, UnsafeSupplier<T> supplier) throws RetryFailedException {
        log.info("retryWithMetrics - TODO not implemented");
        return Retry.retry(supplier);
    }

    public static void retryWithMetrics(String metricName, UnsafeCallable callable) throws RetryFailedException {
        log.info("retryWithMetrics - TODO not implemented");
        Retry.retry(callable);
    }

    private Util() {
    }
}
