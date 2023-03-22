/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.flock.service;

import com.sg.flock.dao.FlockDao;
import com.sg.flock.dao.FlockDaoImpl;
import com.sg.flock.dto.Reply;
import com.sg.flock.dto.Tweet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Andrew
 */
public class FlockServiceLayerImpl implements FlockServiceLayer{

    @Autowired
    FlockDao dao;

    public FlockServiceLayerImpl(FlockDaoImpl flockDao) {
        this.dao=flockDao;
    }

    @Override
    public void createTables() {
        dao.createTables();
    }

    @Override
    public void insertTweet(Tweet tweet) {
        dao.insertTweet(tweet);
    }

    @Override
    public void insertReply(Reply reply) {
        dao.insertReply(reply);
    }

    @Override
    public Tweet getTweetById(int tweetId) {
        return dao.getTweetById(tweetId);
    }

    @Override
    public List<Tweet> getAllTweets() {
        return dao.getAllTweets();
    }

    @Override
    public List<Reply> getRepliesForTweetId(int tweetId) {
        return dao.getRepliesForTweetId(tweetId);
    }
    
}
