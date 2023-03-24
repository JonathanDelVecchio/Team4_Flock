import { Component, OnInit } from '@angular/core';
import { Tweet } from '../models/tweet';
import { TweetService } from '../services/tweet.service';

@Component({
  selector: 'app-tweet-history',
  templateUrl: './tweet-history.component.html',
  styleUrls: ['./tweet-history.component.scss'],
})
export class TweetHistoryComponent implements OnInit {
  tweets: Tweet[] = [];

  constructor(private tweetService: TweetService) {}

  ngOnInit(): void {
    this.loadTweets();
  }

  loadTweets() {
    this.tweetService.getAllTweets().subscribe((tweets) => {
      this.tweets = tweets;

      //added
      console.log('Received tweets:', tweets);

    });
  }

}
