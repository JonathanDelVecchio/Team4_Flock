/*

import { Component, OnInit } from '@angular/core';
import { TweetService } from '../services/tweet.service';
import { Tweet } from '../models/tweet';

@Component({
 selector: 'app-search-tweets',
 templateUrl: './search-tweets.component.html',
 styleUrls: ['./search-tweets.component.scss']
})
export class SearchTweetsComponent implements OnInit {
 searchQuery = '';
 tweets: Tweet[] = [];

 constructor(private tweetService: TweetService) {
 }

 ngOnInit(): void {
 }
}
 /*
 searchTweets(): void {
   this.tweetService.searchTweetsByUsername(this.searchQuery).subscribe((tweets) => {
     this.tweets = tweets;
   });
 }
}
*/
