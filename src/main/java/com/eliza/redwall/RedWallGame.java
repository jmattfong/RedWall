/*
 * Copyright (c) 2016 Matthew Fong
 */

package com.eliza.redwall;

import com.eliza.redwall.data.S3Client;
import com.eliza.redwall.twitter.TwitterClient;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.RequiredArgsConstructor;
import twitter4j.Status;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class RedWallGame implements Runnable {

    private final TwitterClient twitterClient;
    private final S3Client s3Client;

    @Override
    public void run() {
        // TODO make these asynchronous
        List<Status> latestReplies = twitterClient.getRepliesToLastTweet();
        GameState currentState = s3Client.getGameState();
        
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new RedWallModule());
        RedWallGame game = injector.getInstance(RedWallGame.class);
        game.run();
    }
}
