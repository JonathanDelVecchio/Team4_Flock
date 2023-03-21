/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.flock.controller;

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
    public int createPost() {
        return dao.insertTweet();
    }
    
    @PostMapping("/reply")
    public int createReply() {
        return dao.insertReply();
    }
    
    @GetMapping("/posts")
    public List<Tweet> getAllPosts() {
        return dao.getAllTweets();
    }
    
    @GetMapping("/post/{tweetId}")
    public Reply<Reply> getAllReplies(@PathVariable("tweetId") int tweetId) {
        return dao.getRepliesForTweetId(tweetId);
    }
}
