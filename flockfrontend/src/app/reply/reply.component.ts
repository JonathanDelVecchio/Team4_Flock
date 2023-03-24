import { Component, Input, OnInit } from '@angular/core';
import { Reply } from '../models/reply';
import { TweetService } from '../services/tweet.service';

@Component({
  selector: 'app-reply',
  templateUrl: './reply.component.html',
  styleUrls: ['./reply.component.scss'],
})
export class ReplyComponent implements OnInit {
  @Input() tweetId!: number;
  replies: Reply[] = [];
  reply: Reply = new Reply();
  selectedFile: File | null = null;

  constructor(private tweetService: TweetService) {}


    ngOnInit(): void {
      this.loadReplies();
    }

    //Dont remove
    loadReplies() {
      this.tweetService.getRepliesForTweet(this.tweetId).subscribe((replies) => {
        this.replies = replies;
        //added
        console.log('Received replies:', replies);
      });
    }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  async createReply() {
    if (this.selectedFile) {
      const imageUrl = await this.tweetService.uploadImage(this.selectedFile).toPromise();
      this.reply.img = imageUrl;
    }
    this.reply.tweet_id = this.tweetId;
    this.tweetService.createReply(this.reply).subscribe((createdReply) => {
      this.replies.push(createdReply);
      this.reply = new Reply();
      this.selectedFile = null;
    });
  }
}
