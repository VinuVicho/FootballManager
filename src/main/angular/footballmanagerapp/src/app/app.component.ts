import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Team } from './team/team';
import { TeamService } from './team.service';
import { NgForm } from '@angular/forms';
import {Player} from "./player/player";


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

  constructor(private teamService: TeamService) {
  }

  ngOnInit(): void {
  }

  public toTeamPage(id: number) {   //TODO
    console.log("Here should be link to created team page")
  }

  public createNewTeam(newForm: NgForm): void {
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
}
