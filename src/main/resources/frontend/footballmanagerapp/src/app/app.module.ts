import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { appRoutingModule } from './app.routing';
import { AppComponent } from './app.component';
import { TeamService } from './team.service';
import { PlayerComponent } from './player';
import { TeamComponent } from './team';
import { PlayersComponent } from './players';
import { TeamsComponent } from './teams';
import { FormsModule } from "@angular/forms";

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
    appRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [TeamService],
  bootstrap: [AppComponent]
})
export class AppModule { }
