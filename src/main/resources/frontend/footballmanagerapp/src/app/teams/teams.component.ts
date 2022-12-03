import {Component, OnInit} from '@angular/core';
import {Team} from "../team/team";
import {HttpErrorResponse} from "@angular/common/http";
import {TeamService} from "../team.service";
import { NgForm } from '@angular/forms';

@Component({ templateUrl: 'teams.component.html' })
export class TeamsComponent implements OnInit{
  public teams: Team[] = [];

  constructor(private teamService: TeamService) {  }

  ngOnInit(): void {
    this.getTeams();
  }

  public getTeams(): void {
    this.teamService.getTeams().subscribe({
      next: (response: Team[]) => {
        this.teams = response;
        console.log(this.teams);
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }
}
