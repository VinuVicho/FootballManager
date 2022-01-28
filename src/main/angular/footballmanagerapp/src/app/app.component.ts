import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Team } from './team/team';
import { TeamService } from './team.service';
import { NgForm } from '@angular/forms';
import {Player} from "./player/player";
import {PlayerService} from "./player.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(private teamService: TeamService,
              private playerService: PlayerService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  public toTeamPage(id: number) {
    this.router.navigate(['/team/' + id]);
  }

  public toPlayerPage(id: number) {
    this.router.navigate(['/player/' + id]);
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

  createNewPlayer(newForm: NgForm): void {
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
