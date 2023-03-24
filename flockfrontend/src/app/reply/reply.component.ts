import {Component, Input, OnInit,} from '@angular/core';
import {Reply} from '../models/reply';
import {TweetService} from '../services/tweet.service';
import {MatDialog} from '@angular/material/dialog';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';

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
  editReplyForm!: FormGroup;
  showReplyForm = false;


  constructor(private tweetService: TweetService, private dialog: MatDialog, private fb: FormBuilder) {
  }


  ngOnInit(): void {
    this.loadReplies();
    this.initEditReplyForm();
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
    console.log('Reply object before sending to back-end:', this.reply);
    this.tweetService.createReply(this.reply).subscribe((createdReply) => {
      this.replies.push(createdReply);
      this.reply = new Reply();
      this.selectedFile = null;
    });
  }

  deleteReply(replyId: number): void {
    this.tweetService.deleteReplyById(this.tweetId, replyId).subscribe(() => {
      this.replies = this.replies.filter((reply) => reply.id !== replyId);
    });
  }

  initEditReplyForm(): void {
    this.editReplyForm = this.fb.group({
      title: ['', Validators.required],
      reply: ['', Validators.required],
    });
  }

  toggleEditMode(reply: Reply): void {
    reply.editMode = !reply.editMode;
  }

  saveChanges(reply: Reply): void {
    this.tweetService.editReplyById(this.tweetId, reply.id, reply).subscribe(() => {
      // Refresh the replies list after editing
      this.loadReplies();
    });
    reply.editMode = false;
  }
}
