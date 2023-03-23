/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.flock.service;

import com.sg.flock.dao.DataPersistenceException;
import com.sg.flock.dao.FlockDaoImpl;
import com.sg.flock.dto.Reply;
import com.sg.flock.dto.Tweet;
import java.util.List;

/**
 *
 * @author Andrew
 */
public interface FlockServiceLayer {
    
    void createTables();
    
    void insertTweet(Tweet tweet) throws 
            TweetValidationException, InvalidTweetIdException, DataPersistenceException;
    
    void insertReply(Reply reply) throws 
            ReplyValidationException, InvalidTweetIdException, DataPersistenceException;

    void deleteTweetById(int tweetId) throws
            InvalidTweetIdException, DataPersistenceException;
            
    void deleteReplyById(int tweetId, int replyId) throws
            InvalidTweetIdException, DataPersistenceException;
    
    public Tweet getTweetById(int tweetId) throws 
            InvalidTweetIdException, DataPersistenceException;
    
    List<Tweet> getAllTweets() throws DataPersistenceException;
    
    List<Reply> getRepliesForTweetId(int tweetId) throws 
            InvalidTweetIdException, DataPersistenceException;
    
}