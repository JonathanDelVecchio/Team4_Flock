import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SearchTweetsComponent} from "./search-tweets/search-tweets.component";
import {HomeComponent} from "./home/home.component";


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'search', component: SearchTweetsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
