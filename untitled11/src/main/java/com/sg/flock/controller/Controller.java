/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.flock.controller;

import com.sg.flock.dao.FlockDao;
import com.sg.flock.dto.Reply;
import com.sg.flock.dto.Tweet;
import com.sg.flock.service.FlockServiceLayer;
import java.util.List;

import com.sg.flock.service.FlockServiceLayerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody Tweet tweet) {
        sl.insertTweet(tweet);
    }
    
    @PostMapping("/reply")
    public void createReply(@RequestBody Reply reply) {
        sl.insertReply(reply);
    }
    
    @GetMapping("/posts")
    public List<Tweet> getAllPosts() {
        return sl.getAllTweets();
    }
    
    @GetMapping("/reply/{tweetId}")
    public List<Reply> getAllReplies(@PathVariable("tweetId") int tweetId) {
        return sl.getRepliesForTweetId(tweetId);
    }
    
    @GetMapping("/post/{tweetId}")
    public Tweet getTweetById(@PathVariable("tweetId") int tweetId) {
        return sl.getTweetById(tweetId);
    }
}