/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.flock.service;

import com.sg.flock.dao.DataPersistenceException;
import com.sg.flock.dao.FlockDao;
import com.sg.flock.dao.FlockDaoImpl;
import com.sg.flock.dto.Reply;
import com.sg.flock.dto.Tweet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrew
 */
@Component
public class FlockServiceLayerImpl implements FlockServiceLayer{

    @Autowired
    FlockDao dao;

    @Autowired
    public FlockServiceLayerImpl(FlockDao flockDao) {
        this.dao=flockDao;
    }

    @Override
    public void createTables(){
        dao.createTables();
    }

    @Override
    public void insertTweet(Tweet tweet) throws 
            TweetValidationException, InvalidTweetIdException, DataPersistenceException{
        dao.insertTweet(tweet);
    }

    @Override
    public void insertReply(Reply reply) throws 
             ReplyValidationException, InvalidTweetIdException, DataPersistenceException{
        dao.insertReply(reply);
    }

    @Override
    public Tweet getTweetById(int tweetId) throws 
            InvalidTweetIdException, DataPersistenceException{
        return dao.getTweetById(tweetId);
    }
    
    @Override
    public void deleteTweetById(int tweetId) throws 
            InvalidTweetIdException, DataPersistenceException{
        dao.deleteTweetById(tweetId);
    }
    
    @Override
    public void deleteReplyById(int tweetId, int replyId) throws
            InvalidTweetIdException, DataPersistenceException{
        dao.deleteReplyById(tweetId, replyId);
    }
    
    @Override
    public void editTweetById(int tweetId, Tweet tweet) throws
            InvalidTweetIdException, DataPersistenceException{
        dao.editTweetById(tweetId, tweet);
    }
    
    @Override
    public void editReplyById(int tweetId, int replyId, Reply reply) throws
            InvalidTweetIdException, DataPersistenceException{
        dao.editReplyById(tweetId, replyId, reply);
    }

    @Override
    public List<Tweet> getAllTweets() throws DataPersistenceException{
        return dao.getAllTweets();
    }
    
    @Override
    public List<Reply> getAllReplies() throws DataPersistenceException{
        return dao.getAllReplies();
    }

    @Override
    public List<Reply> getRepliesForTweetId(int tweetId) throws 
            InvalidTweetIdException, DataPersistenceException{
        return dao.getRepliesForTweetId(tweetId);
    }
    
    @Override
    public void clearReplyTable(){
        dao.clearReplyTable();
    }
    
    @Override
    public void clearTweetTable(){
        dao.clearTweetTable();
    }
}