/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.flock.dao;

import com.sg.flock.dto.Reply;
import com.sg.flock.dto.Tweet;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrew
 */
@Component
public interface FlockDao {
    
    void createTables();
    
    void insertTweet(Tweet tweet) throws DataPersistenceException;
    
    void insertReply(Reply reply) throws DataPersistenceException;
    //    void insertReply(int tweetId, String userName, String title, String post, String img, String date);
    
    public Tweet getTweetById(int tweetId) throws DataPersistenceException;
    
    List<Tweet> getAllTweets() throws DataPersistenceException;
    
    List<Reply> getRepliesForTweetId(int tweetId) throws DataPersistenceException;
}