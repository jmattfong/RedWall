/*
 * Copyright (c) 2016 Matthew Fong
 */

package com.eliza.redwall.twitter;

import com.eliza.redwall.util.Util;
import com.eliza.redwall.util.retry.RetryFailedException;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import twitter4j.*;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class TwitterClient {

    private final Twitter twitter;

    public void postTweet(String text) throws RetryFailedException {
        Util.retryWithMetrics("postTweet", () -> {
            Status status = twitter.updateStatus(text);
            log.info("Successfully tweeted [{}].", status.getText());
        });
    }

    public Status postTweet(StatusUpdate update) throws RetryFailedException {
        return Util.retryWithMetrics("postTweet", () -> {
            Status status = twitter.updateStatus(update);
            log.info("Successfully tweeted [{}].", status.getText());
            return status;
        });
    }

    public List<Status> getRepliesToLastTweet() {
        try {
            Status lastStatus = getLastTweet();
            if (lastStatus == null) {
                return Collections.emptyList();
            }
            return getRepliesToTweet(lastStatus);
        } catch (RetryFailedException e) {
            throw new RuntimeException("Failed to get last tweet replies", e);
        }
    }

    @Nullable
    private Status getLastTweet() throws RetryFailedException {
        return Util.retryWithMetrics("getLastTweet", () -> {
            Paging paging = new Paging(1, 1);
            List<Status> tweets = twitter.getUserTimeline(paging);
            if (tweets.isEmpty()) {
                return null;
            } else {
                return tweets.get(0);
            }
        });
    }

    private List<Status> getRepliesToTweet(Status status) throws RetryFailedException {
        return Util.retryWithMetrics("getRepliesToTweet", () -> {
            List<Status> replies = new ArrayList<>();

            long tweetID = status.getId();
            Query query = new Query("to:" + status.getUser().getScreenName() + " since_id:" + tweetID);
            QueryResult results;

            do {
                results = twitter.search(query);
                System.out.println("Results: " + results.getTweets().size());
                List<Status> tweets = results.getTweets();

                for (Status tweet : tweets) {
                    if (tweet.getInReplyToStatusId() == tweetID) {
                        replies.add(tweet);
                    }
                }
            } while ((query = results.nextQuery()) != null);
            return replies;
        });
    }

}
