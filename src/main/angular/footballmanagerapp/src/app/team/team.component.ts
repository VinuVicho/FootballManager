import {Component, OnInit} from '@angular/core';
import {Player} from "../player/player";
import {PlayerService} from "../player.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {Team} from "./team";
import {TeamService} from "../team.service";

@Component({templateUrl: 'team.component.html'})
export class TeamComponent implements OnInit {

  team: Team | undefined;

  constructor(private route: ActivatedRoute, private teamService: TeamService) { }

  ngOnInit() {
    const routeParams = this.route.snapshot.paramMap;
    const teamNameFromRoute = String(routeParams.get('teamName'));
    this.getTeam(teamNameFromRoute);
  }

  public getTeam(teamName: string): void {
    this.teamService.getTeam(teamName).subscribe(
      (response: Team) => {
        this.team = response;
        console.log(this.team);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteTeam() {
    this.teamService.deleteTeam(this.team!.id).subscribe(
      (response: void) => {
        console.log("Redirect to all teams");
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
