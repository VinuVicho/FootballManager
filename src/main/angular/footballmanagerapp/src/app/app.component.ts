import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Team } from './team/team';
import { TeamService } from './team.service';
import { NgForm } from '@angular/forms';
import {Player} from "./player/player";
import {PlayerService} from "./player.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  public team: Team = {
    id: 1,
    money: 12,
    commission: 12,
    name: "string",
    logo: "string",
    city: "string",
    country: "string",
    about: "string",
    players: []
  };

  constructor(private teamService: TeamService, private playerService: PlayerService) {
  }

  ngOnInit(): void {
  }

  public toTeamPage(id: number) {   //TODO
    console.log("Here should be link to created team page " + id)
  }

  public toPlayerPage(id: number) {   //TODO
    console.log("Here should be link to created player page " + id)
  }

  public createNewTeam(newForm: NgForm): void {   //TODO: make auto close form
    this.teamService.addTeam(newForm.value).subscribe(
      (response: Team) => {
        console.log(response);
        this.toTeamPage(response.id);
        newForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        newForm.reset();
      }
    );
  }

  createNewPlayer(newForm: NgForm): void {   //TODO: make auto close form
    console.log(newForm.value)
    this.playerService.addPlayer(newForm.value).subscribe(
      (response: Player) => {
        console.log(response);
        this.toPlayerPage(response.id);
        newForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        newForm.reset();
      }
    );
  }
}
