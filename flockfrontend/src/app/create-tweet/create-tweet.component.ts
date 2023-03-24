import {Component} from '@angular/core';
import {Tweet} from '../models/tweet';
import {TweetService} from '../services/tweet.service';

@Component({
  selector: 'app-create-tweet',
  templateUrl: './create-tweet.component.html',
  styleUrls: ['./create-tweet.component.scss'],
})
export class CreateTweetComponent {
  tweet: Tweet = new Tweet();
  selectedFile: File | null = null;
  showTweetForm = false;


  constructor(private tweetService: TweetService) {
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  async createTweet() {
    if (this.selectedFile) {
      const imageUrl = await this.tweetService.uploadImage(this.selectedFile).toPromise();
      this.tweet.img = imageUrl;
    }
    this.tweetService.createTweet(this.tweet).subscribe((createdTweet) => {
      this.tweet = new Tweet();
      this.selectedFile = null;
    });
  }

  toggleTweetForm(): void {
    this.showTweetForm = !this.showTweetForm;
  }

}

