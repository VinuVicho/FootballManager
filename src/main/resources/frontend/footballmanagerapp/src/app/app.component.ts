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

  public createNewTeam(newForm: NgForm): void {
    this.teamService.addTeam(newForm.value).subscribe(
      (response: Team) => {
        console.log(response);
        window.location.reload();
        // this.router.navigate(['/team/' + response.id]);
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
        // this.router.navigate(['/player/' + response.id]);
        window.location.reload();
        newForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        newForm.reset();
      }
    );
  }
}
