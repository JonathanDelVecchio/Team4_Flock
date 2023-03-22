/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.flock.dao;

import com.sg.flock.dto.Reply;
import com.sg.flock.dto.Tweet;
import java.util.List;

/**
 *
 * @author Andrew
 */
public interface FlockDao {
    
    void createTables();
    
    void insertTweet(Tweet tweet);
    
    void insertReply(Reply reply);
    //    void insertReply(int tweetId, String userName, String title, String post, String img, String date);

    public Tweet getTweetById(int tweetId);
    
    List<Tweet> getAllTweets();
    
    List<Reply> getRepliesForTweetId(int tweetId);
}