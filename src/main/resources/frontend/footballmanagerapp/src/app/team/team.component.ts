import {Component, OnInit} from '@angular/core';
import {Player} from "../player/player";
import {PlayerService} from "../player.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {Team} from "./team";
import {TeamService} from "../team.service";

@Component({templateUrl: 'team.component.html'})
export class TeamComponent implements OnInit {

  team: Team | undefined;

  constructor(private route: ActivatedRoute,
              private teamService: TeamService,
              private router: Router) {
  }

  ngOnInit() {
    const routeParams = this.route.snapshot.paramMap;
    const teamNameFromRoute = String(routeParams.get('teamName'));
    this.getTeam(teamNameFromRoute);
  }

  public getTeam(teamName: string): void {
    this.teamService.getTeam(teamName).subscribe({
      next: (response: Team) => {
        this.team = response;
        console.log(this.team);
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }

  public onUpdateTeam(team: Team): void {
    this.teamService.updateTeam(team).subscribe({
      next: (response: Team) => {
        console.log(response);
        window.location.reload();
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }

  public onDeleteTeam() {
    this.teamService.deleteTeam(this.team!.id).subscribe({
      next: () => this.router.navigate(['/']),
      error: (HttpErrorResponse) => {
        alert(HttpErrorResponse.message)
      }
    });
  }
}
