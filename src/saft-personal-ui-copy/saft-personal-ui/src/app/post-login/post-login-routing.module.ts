import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BuyPageComponent } from './buy-page/buy-page.component';
import { HistoryPageComponent } from './history-page/history-page.component';
import { PostLoginPageComponent } from './post-login-page/post-login-page.component';

const routes: Routes = [
  {path: "", component: PostLoginPageComponent, children: [
    {path:"", redirectTo: "buy", pathMatch: "full"},
    {path: "buy", component: BuyPageComponent},
    {path: "history", component: HistoryPageComponent}
  ]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostLoginRoutingModule { }
