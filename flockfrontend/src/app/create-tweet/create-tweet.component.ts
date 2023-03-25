import {Component, EventEmitter, Output, Input} from '@angular/core';
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
  @Output() tweetCreated = new EventEmitter<void>();
  @Input() showTweetForm = false;


  constructor(private tweetService: TweetService) {
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  async createTweet() {
    if (this.selectedFile) {
      try {
        this.tweet.img = await this.readFileAsDataURL(this.selectedFile);
      } catch (error) {
        console.error('Error reading the file:', error);
      }
    }
    console.log('Sending tweet data:', this.tweet);
    this.tweetService.createTweet(this.tweet).subscribe((createdTweet) => {
      this.tweet = new Tweet();
      this.selectedFile = null;
      this.tweetCreated.emit();
    });
  }


  private async readFileAsDataURL(file: File): Promise<string> {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();

      reader.onload = () => {
        resolve(reader.result as string);
      };

      reader.onerror = (error) => {
        reject(error);
      };

      reader.readAsDataURL(file);
    });
  }
}

