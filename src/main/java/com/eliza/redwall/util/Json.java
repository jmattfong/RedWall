/*
 * Copyright (c) 2016 Matthew Fong
 */

package com.eliza.redwall.util;

import com.eliza.redwall.util.retry.RetryFailedException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T decode(String str, Class<T> clz) {
        try {
            return Util.retryWithMetrics("decode", () -> {
                return mapper.readValue(str, clz);
            });
        } catch (RetryFailedException e) {
            throw new RuntimeException("Failed to decode string", e);
        }
    }
}
