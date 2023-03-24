import {Component, OnInit, ViewChild} from '@angular/core';
import {Tweet} from '../models/tweet';
import {TweetService} from '../services/tweet.service';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import {ReplyComponent} from '../reply/reply.component';


@Component({
  selector: 'app-tweet-history',
  templateUrl: './tweet-history.component.html',
  styleUrls: ['./tweet-history.component.scss'],
})
export class TweetHistoryComponent implements OnInit {
  tweets: Tweet[] = [];
  editTweetForm!: FormGroup;
  selectedFile: File | null = null;

  @ViewChild(ReplyComponent) replyComponent!: ReplyComponent;

  constructor(private tweetService: TweetService, private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.loadTweets()
    this.initEditTweetForm();
  }

  deleteTweet(tweetId: number): void {
    this.tweetService.deleteTweetById(tweetId).subscribe(() => {
      this.tweets = this.tweets.filter((tweet) => tweet.id !== tweetId);
    });
  }

  loadTweets() {
    this.tweetService.getAllTweets().subscribe((tweets) => {
      this.tweets = tweets;

      //added
      console.log('Received tweets:', tweets);

    });
  }

  initEditTweetForm(): void {
    this.editTweetForm = this.fb.group({
      title: ['', Validators.required],
      tweet: ['', Validators.required],
    });
  }

  toggleEditMode(tweet: Tweet): void {
    tweet.editMode = !tweet.editMode;
  }

  saveChanges(tweet: Tweet): void {
    this.tweetService.editTweetById(tweet.id!, tweet).subscribe(() => {
      // Refresh the tweets list after editing
      this.loadTweets();
    });
    tweet.editMode = false;
  }

  toggleReplyForm() {
    this.replyComponent.showReplyForm = !this.replyComponent.showReplyForm;
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }
}
