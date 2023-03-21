package org.example.dao;

import org.example.dto.Tweet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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

        jdbcTemplate.update(insertTweetSql, tweet.getUserName(), tweet.getTitle(), tweet.getPost(), tweet.getImage(), tweet.getDate());
    }

    public void insertReply(int tweetId, String userName, String title, String post, String img, String date) {
        String sql = "INSERT INTO reply (tweet_id, user_name, title, post, img, date) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, tweetId, userName, title, post, img, date);
    }


}
