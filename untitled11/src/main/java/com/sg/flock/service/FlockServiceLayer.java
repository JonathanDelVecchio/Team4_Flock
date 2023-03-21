/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.flock.service;

import com.sg.flock.dto.Reply;
import com.sg.flock.dto.Tweet;
import java.util.List;

/**
 *
 * @author Andrew
 */
public interface FlockServiceLayer {
    void createTables();
    
    void insertTweet(Tweet tweet);
    
    void insertReply(Reply reply);

    public Tweet getTweetById(int tweetId);
    
    List<Tweet> getAllTweets();
    
    List<Reply> getRepliesForTweetId(int tweetId);
    
}
