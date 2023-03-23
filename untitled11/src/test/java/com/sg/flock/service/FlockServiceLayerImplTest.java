/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.flock.service;

import com.sg.flock.dao.DataPersistenceException;
import com.sg.flock.dao.DataSourceFactory;
import com.sg.flock.dao.FlockDao;
import com.sg.flock.dao.FlockDaoImpl;
import com.sg.flock.dto.Reply;
import com.sg.flock.dto.Tweet;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author nicho
 */
public class FlockServiceLayerImplTest {
    
    private FlockServiceLayer service;
 
    
    @BeforeEach
    public void setUp() throws Exception{

        
        Tweet tweet = new Tweet();
        
        tweet.setUser_name("Nick");
        tweet.setTitle("Food");
        tweet.setPost("What is your food recommendations?");
        tweet.setImg("food pic");
//        tweet.setDate("1/23/2049");
        
        service.insertTweet(tweet);
        
        Reply reply = new Reply();
        reply.setTweetId(1);
        reply.setUserName("Barry");
        reply.setTitle("salty");
        reply.setPost("The fried chicken is too salty");
        reply.setImg("food pic");
        
        service.insertReply(reply);
    }
    
    public FlockServiceLayerImplTest() {
        
        FlockDao dao = new FlockDaoImpl();
        service = new FlockServiceLayerImpl(dao);
    }

    /**
     * Test of insertTweet method, of class FlockServiceLayerImpl.
     */
    @Test
    public void testInsertTweetandgetTweetById() throws Exception {
        Tweet tweet = new Tweet();
        
        tweet.setUser_name("Jonathan");
        tweet.setTitle("Games");
        tweet.setPost("What is your game recommendations?");
        tweet.setImg("Game");
//        tweet.setDate("3/15/2030");
        
        service.insertTweet(tweet);
        // Fetch the inserted tweet and check that it matches the original tweet
        int num = service.getAllTweets().size() + 1;
        
        Tweet fetchedTweet = service.getTweetById(num);


        
        assertNotNull(fetchedTweet);
        assertEquals(tweet.getUser_name(), fetchedTweet.getUser_name());
        assertEquals(tweet.getTitle(), fetchedTweet.getTitle());
        assertEquals(tweet.getPost(), fetchedTweet.getPost());
        assertEquals(tweet.getImg(), fetchedTweet.getImg());
        assertEquals(tweet.getDate(), fetchedTweet.getDate());
   }

    /**
     * Test of insertReply method, of class FlockServiceLayerImpl.
     */
    @Test
    public void testInsertReply() throws Exception {
        Reply reply = new Reply();
        reply.setTweetId(1);
        reply.setUserName("Andrew");
        reply.setTitle("Food");
        reply.setPost("Fried Chicken");
        reply.setImg("food pic");
        
        service.insertReply(reply);
        // Fetch the inserted tweet and check that it matches the original tweet
        int num = service.getRepliesForTweetId(reply.getTweet_id()).size();
        
        Reply fetchedReply = service.getRepliesForTweetId(reply.getTweet_id()).get(num - 1);
        assertNotNull(fetchedReply);
        assertEquals(reply.getUserName(), fetchedReply.getUserName());
        assertEquals(reply.getTitle(), fetchedReply.getTitle());
        assertEquals(reply.getPost(), fetchedReply.getPost());
        assertEquals(reply.getImg(), fetchedReply.getImg());
        assertEquals(reply.getDate(), fetchedReply.getDate());
    }


    @Test
    public void testDeleteTweet() throws Exception {
        
        Tweet tweet = new Tweet();
        
        tweet.setUser_name("Nick");
        tweet.setTitle("Food");
        tweet.setPost("What is your food recommendations?");
        tweet.setImg("food pic");
        
        service.insertTweet(tweet);
        
        int countOld = service.getAllTweets().size();
        service.deleteTweetById(service.getAllTweets().size());
        int countNew = service.getAllTweets().size();
        assertEquals(countOld, countNew + 1);
    }
    
    @Test
    public void testDeleteReply() throws Exception {
        Tweet tweet = new Tweet();
        tweet.setUser_name("Nick");
        tweet.setTitle("Food");
        tweet.setPost("What is your food recommendations?");
        tweet.setImg("food pic");
        
        service.insertTweet(tweet);
        int num = service.getAllTweets().size();
        Reply reply = new Reply();
        reply.setTweetId(num);
        reply.setUserName("Raul");
        reply.setTitle("Country");
        reply.setPost("Whatever");
        reply.setImg("PICTURES");
        
        service.insertReply(reply);
        
        int countOld = service.getAllReplies().size();
        service.deleteReplyById(num, 2);
        int countNew = service.getAllReplies().size();
        assertEquals(countOld, countNew + 1);
    }
    
    @Test
    public void testEditTweet() throws Exception {
        Tweet tweet = new Tweet();
        
        tweet.setTitle("Drinks");
        tweet.setPost("What is your drink recommendations?");
        tweet.setImg("drink pic");
        
        service.editTweetById(1, tweet);
        assertEquals("Drinks", service.getTweetById(1).getTitle());
        assertEquals("What is your drink recommendations?", service.getTweetById(1).getPost());
        assertEquals("drink pic", service.getTweetById(1).getImg());
        
        
    }
    
    @Test
    public void testEditReply() throws Exception {
        Reply reply = new Reply();
        
        reply.setTitle("Drinks");
        reply.setPost("What is your drink recommendations?");
        reply.setImg("drink pic");
        
        service.editReplyById(1,1, reply);
        assertEquals("Drinks", service.getRepliesForTweetId(1).get(0).getTitle());
        assertEquals("What is your drink recommendations?", service.getRepliesForTweetId(1).get(0).getPost());
        assertEquals("drink pic", service.getRepliesForTweetId(1).get(0).getImg());
        
        
    }

    /**
     * Test of getAllTweets method, of class FlockServiceLayerImpl.
     */
    @Test
    public void testGetAllTweets() throws Exception {
        List<Tweet> tweets = service.getAllTweets();
        
        assertEquals(10, tweets.size());
    }

    /**
     * Test of getRepliesForTweetId method, of class FlockServiceLayerImpl.
     */
    @Test
    public void testGetRepliesForTweetId() throws Exception {
        List<Reply> replies = service.getRepliesForTweetId(1);
        assertEquals(6, replies.size());
    }
    
    
}
