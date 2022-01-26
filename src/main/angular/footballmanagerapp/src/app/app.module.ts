import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TeamService } from './team.service';
import { PlayerComponent } from './player';
import { TeamComponent } from './team';
import { PlayersComponent } from './players';
import { TeamsComponent } from './teams';

@NgModule({
  declarations: [
    AppComponent, 
    PlayerComponent, 
    TeamComponent, 
    TeamsComponent, 
    PlayersComponent
  ],
  imports: [
    BrowserModule, 
    HttpClientModule
  ],
  providers: [TeamService],
  bootstrap: [AppComponent]
})
export class AppModule { }
