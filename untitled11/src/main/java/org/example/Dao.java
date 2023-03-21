package org.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.LinkedList;
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

        jdbcTemplate.update(insertTweetSql, tweet.getUserName(), tweet.getTitle(), tweet.getPost(), tweet.getImg(), tweet.getDate());
    }

    public void insertReply(Reply reply) {
        String sql = "INSERT INTO reply (tweet_id, user_name, title, post, img, date) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, reply.tweet_id, reply.user_name, reply.title, reply.post, reply.img, reply.date);
    }
    public LinkedList<Tweet> getTweets() {
        String sql = "SELECT * FROM tweet";
        List<Tweet> tweets = new LinkedList<>();
        jdbcTemplate.query(sql, (rs, rowNum) -> {
            Tweet tweet = new Tweet();
            tweet.setId(rs.getInt("id"));
            tweet.setUserName(rs.getString("user_name"));
            tweet.setTitle(rs.getString("title"));
            tweet.setPost(rs.getString("post"));
            tweet.setImg(rs.getString("img"));
            tweet.setDate(rs.getString("date"));
            tweets.add(tweet);
            return tweet;
        });
        return (LinkedList<Tweet>) tweets;
    }
    public List<Reply> getReplies() {
        String sql = "SELECT * FROM reply";
        List<Reply> replies = new LinkedList<>();
        jdbcTemplate.query(sql, (rs, rowNum) -> {
            Reply reply = new Reply();
            reply.setId(rs.getInt("id"));
            reply.setTweetId(rs.getInt("tweet_id"));
            reply.setUserName(rs.getString("user_name"));
            reply.setTitle(rs.getString("title"));
            reply.setPost(rs.getString("post"));
            reply.setImg(rs.getString("img"));
            reply.setDate(rs.getString("date"));
            replies.add(reply);
            return reply;
        });
        return replies;
    }

}
