/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.flock.controller;

import com.sg.flock.dao.DataPersistenceException;
import com.sg.flock.dao.FlockDao;
import com.sg.flock.dto.Reply;
import com.sg.flock.dto.Tweet;
import com.sg.flock.service.FlockServiceLayer;
import java.util.List;

import com.sg.flock.service.FlockServiceLayerImpl;
import com.sg.flock.service.InvalidTweetIdException;
import com.sg.flock.service.ReplyValidationException;
import com.sg.flock.service.TweetValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nicho
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class Controller {
    /*
      @Autowired
      FlockDao dao;
      */
    FlockServiceLayer sl;

    @Autowired
    public Controller(FlockServiceLayer flockServiceLayer) {
        this.sl=flockServiceLayer;
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody Tweet tweet) throws DataPersistenceException, TweetValidationException {
        // Perform validation checks on the tweet object
//        if (tweet.getPost() == null || tweet.getPost().isEmpty()) {
//            throw new TweetValidationException("Tweet message cannot be empty");
//        }
//        if (tweet.getPost().length() > 280) {
//            throw new TweetValidationException("Tweet message length cannot exceed 280 characters");
//        }

        try {
            // Insert the tweet into the database
            sl.insertTweet(tweet);
        } catch (InvalidTweetIdException ex) {
            // Handle the exception
            // For example, you could log the error and re-throw it as a DataPersistenceException
            System.out.println(ex.getMessage());
            throw new DataPersistenceException("Error inserting tweet into database", ex);
        }
    }

    @PostMapping("/replies")
    public void createReply(@RequestBody Reply reply) throws DataPersistenceException, ReplyValidationException {
        // Perform validation checks on the tweet object
//        if (reply.getPost() == null || reply.getPost().isEmpty()) {
//            throw new ReplyValidationException("reply message cannot be empty");
//        }
//        if (reply.getPost().length() > 280) {
//            throw new ReplyValidationException("reply message length cannot exceed 280 characters");
//        }

        try {
            // Insert the tweet into the database
            sl.insertReply(reply);
        } catch (InvalidTweetIdException ex) {
            // Handle the exception
            // For example, you could log the error and re-throw it as a DataPersistenceException
            System.out.println(ex.getMessage());
            throw new DataPersistenceException("Error inserting reply into database", ex);
        }
    }

    @GetMapping("/posts")
    public List<Tweet> getAllPosts() throws DataPersistenceException{
        return sl.getAllTweets();
    }

    @GetMapping("/replies/{tweetId}")
    public List<Reply> getAllReplies(@PathVariable("tweetId") int tweetId) throws DataPersistenceException{
        try{
            return sl.getRepliesForTweetId(tweetId);
        } catch (InvalidTweetIdException ex){
            System.out.println(ex.getMessage());
            throw new DataPersistenceException("Error getting replies from database.");
        }
    }

    @GetMapping("/posts/{tweetId}")
    public Tweet getTweetById(@PathVariable("tweetId") int tweetId) throws DataPersistenceException {
        
        try{
            return sl.getTweetById(tweetId);
        } catch (InvalidTweetIdException ex) {
            System.out.println(ex.getMessage());
            throw new DataPersistenceException("Error getting post from database.");
        }
    }
    
    @DeleteMapping("/posts/{tweetId}")
    public void deleteTweetById(@PathVariable("tweetId") int tweetId) throws DataPersistenceException {
        try{
            sl.deleteTweetById(tweetId);
        } catch (InvalidTweetIdException ex) {
            System.out.println(ex.getMessage());
            throw new DataPersistenceException("Error getting post from database.");
        }
    }
    
    @DeleteMapping("/replies/{tweetId}/{replyId}")
    public void deleteReplyById(@PathVariable("tweetId") int tweetId, @PathVariable("replyId") int replyId) throws DataPersistenceException {
        try{
            sl.deleteReplyById(tweetId, replyId);
        } catch (InvalidTweetIdException ex) {
            System.out.print(ex.getMessage());
            throw new DataPersistenceException("Error getting post from database.");
        }
    } 
}