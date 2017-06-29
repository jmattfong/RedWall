/*
 * Copyright (c) 2016 Matthew Fong
 */

package com.eliza.redwall.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.eliza.redwall.twitter.TwitterClient;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import twitter4j.Twitter;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ScheduledRedWall implements RequestHandler {

    private final TwitterClient twitterClient;

    public Object handleRequest(Object o, Context context) {
        return null;
    }
}
