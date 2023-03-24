import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {CreateTweetComponent} from '../app/create-tweet/create-tweet.component';
import {TweetHistoryComponent} from '../app/tweet-history/tweet-history.component';
import {ReplyComponent} from '../app/reply/reply.component';
import {TweetService} from './services/tweet.service';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {ReactiveFormsModule} from '@angular/forms';

import {OrderPipe} from './pipes/order-pipe';


@NgModule({
  declarations: [
    AppComponent,
    CreateTweetComponent,
    TweetHistoryComponent,
    ReplyComponent,
    OrderPipe,
  ],
  imports: [BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,],
  providers: [TweetService],
  bootstrap: [AppComponent],

})
export class AppModule {
}
