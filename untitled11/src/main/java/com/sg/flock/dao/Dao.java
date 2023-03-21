package com.sg.flock.dao;

import org.example.dto.Reply;
import org.example.dto.Tweet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Dao {
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String user = "root";
    private final String pass = "uuuu";
    private final String dbName = "mydb";
    private final String url = "jdbc:mysql://localhost:3306/mydb";
    DataSource dataSource = DataSourceFactory.createDataSource();
    private final JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);

    public void createTables() {


        String createDbSql = "CREATE DATABASE IF NOT EXISTS " + dbName;
        jdbcTemplate.execute(createDbSql);

        jdbcTemplate.execute("USE " + dbName);

        String roundTableSql = "CREATE TABLE IF NOT EXISTS `mydb`.`tweet` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `user_name` VARCHAR(300) NOT NULL,\n" +
                "  `title` TEXT NULL,\n" +
                "  `post` TEXT NULL,\n" +
                "  `img` TEXT NULL,\n" +
                "  `date` TEXT NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB;";
        jdbcTemplate.execute(roundTableSql);

        String gameTableSql = "CREATE TABLE IF NOT EXISTS `mydb`.`reply` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `tweet_id` INT NOT NULL,\n" +
                "  `user_name` VARCHAR(300) NULL,\n" +
                "  `title` TEXT NULL,\n" +
                "  `post` TEXT NULL,\n" +
                "  `img` TEXT NULL,\n" +
                "  `date` TEXT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  INDEX `key_idx` (`tweet_id` ASC),\n" +
                "  CONSTRAINT `key`\n" +
                "    FOREIGN KEY (`tweet_id`)\n" +
                "    REFERENCES `mydb`.`tweet` (`id`)\n" +
                "    ON DELETE CASCADE\n" +
                " ) ENGINE = InnoDB;";
        jdbcTemplate.execute(gameTableSql);

        System.out.println("Tables created successfully.");
    }
    public void insertTweet(Tweet tweet) {
        String insertTweetSql = "INSERT INTO `mydb`.`tweet` (`user_name`, `title`, `post`, `img`, `date`) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(insertTweetSql, tweet.getUser_name(), tweet.getTitle(), tweet.getPost(), tweet.getImage(), tweet.getDate());
    }

    public void insertReply(int tweetId, String userName, String title, String post, String img, String date) {
        String sql = "INSERT INTO reply (tweet_id, user_name, title, post, img, date) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, tweetId, userName, title, post, img, date);
    }

    public List<Tweet> getAllTweets() {
        String sql = "SELECT * FROM tweet";
        List<Tweet> tweets = jdbcTemplate.query(sql, new RowMapper<Tweet>() {
            @Override
            public Tweet mapRow(ResultSet rs, int rowNum) throws SQLException {
                Tweet tweet = new Tweet();
                tweet.setId(rs.getInt("id"));
                tweet.setUser_name(rs.getString("user_name"));
                tweet.setTitle(rs.getString("title"));
                tweet.setPost(rs.getString("post"));
                tweet.setImage(rs.getString("img"));
                tweet.setDate(rs.getString("date"));
                return tweet;
            }
        });
        return tweets;
    }
    public List<Reply> getRepliesForTweetId(int tweetId) {
        String sql = "SELECT * FROM reply WHERE tweet_id = ?";
        List<Reply> replies = jdbcTemplate.query(sql, new Object[] { tweetId }, new RowMapper<Reply>() {
            @Override
            public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
                Reply reply = new Reply();
                reply.setId(rs.getInt("id"));
                reply.setTweetId(rs.getInt("tweet_id"));
                reply.setUserName(rs.getString("user_name"));
                reply.setTitle(rs.getString("title"));
                reply.setPost(rs.getString("post"));
                reply.setImg(rs.getString("img"));
                reply.setDate(rs.getString("date"));
                return reply;
            }
        });
        return replies;
    }




}
