import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { CreateTweetComponent } from '../app/create-tweet/create-tweet.component';
import { TweetHistoryComponent } from '../app/tweet-history/tweet-history.component';
import { ReplyComponent } from '../app/reply/reply.component';
import { TweetService } from './services/tweet.service';

@NgModule({
  declarations: [
    AppComponent,
    CreateTweetComponent,
    TweetHistoryComponent,
    ReplyComponent,
  ],
  imports: [BrowserModule, FormsModule, HttpClientModule],
  providers: [TweetService],
  bootstrap: [AppComponent],
})
export class AppModule {}
