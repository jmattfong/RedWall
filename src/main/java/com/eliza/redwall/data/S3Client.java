/*
 * Copyright (c) 2016 Matthew Fong
 */

package com.eliza.redwall.data;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.eliza.redwall.GameState;
import com.eliza.redwall.util.Json;
import com.eliza.redwall.util.Util;
import com.eliza.redwall.util.retry.RetryFailedException;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class S3Client {

    private static final String BUCKET_NAME = "red-wall-state";
    private static final String GAME_STATE_KEY = "game-state";

    private final AmazonS3 s3Client;

    public GameState getGameState() {
        S3Object gameStateObject = downloadObject(GAME_STATE_KEY);
        try {
            return Util.retryWithMetrics("getGameState", () -> {
                try (InputStream objectData = gameStateObject.getObjectContent()) {
                    String data = IOUtils.toString(objectData);
                    return Json.decode(data, GameState.class);
                }
            });
        } catch (RetryFailedException e) {
            throw new RuntimeException("Failed to get game state", e);
        }
    }

    private S3Object downloadObject(String key) {
        return s3Client.getObject(new GetObjectRequest(BUCKET_NAME, key));
    }
}
