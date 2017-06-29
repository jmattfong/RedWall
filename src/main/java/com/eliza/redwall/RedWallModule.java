/*
 * Copyright (c) 2016 Matthew Fong
 */

package com.eliza.redwall;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

class RedWallModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Singleton
    @Provides
    private Twitter getTwitter() {
        Twitter twitter = TwitterFactory.getSingleton();
        AccessToken accessToken = new AccessToken("879602215228383232-vmLw3XDJTiwckVfAMaGrEoK6H4FLK69",
                "gU2ij6uB3PUVNfiCTl4AeYoSBx0JNcDif5PUOWH8qOvAw");
        twitter.setOAuthAccessToken(accessToken);
        twitter.setOAuthConsumer("uc2faW8nVKRG2uhe3QZJSHqF7", "JhLgx8KOdlVOqmsLC0MVMCpgaOxy8jC7hnwb08huB7Bkqt7i2m");
        return twitter;
    }

    @Singleton
    @Provides
    private AmazonS3 getS3Client() {
        return new AmazonS3Client(new ProfileCredentialsProvider());
    }
}
