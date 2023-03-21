/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.flock.controller;

import com.sg.flock.dao.FlockDao;
import com.sg.flock.dto.Reply;
import com.sg.flock.dto.Tweet;
import java.util.List;
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
    @Autowired
    FlockDao dao;
    
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody Tweet tweet) {
        dao.insertTweet(tweet);
    }
    
    @PostMapping("/reply")
    public void createReply(@RequestBody Reply reply) {
        dao.insertReply(reply);
    }
    
    @GetMapping("/posts")
    public List<Tweet> getAllPosts() {
        return dao.getAllTweets();
    }
    
//    @GetMapping("/post/{tweetId}")
//    public Reply<Reply> getAllReplies(@PathVariable("tweetId") int tweetId) {
//        return dao.getRepliesForTweetId(tweetId);
//    }
}