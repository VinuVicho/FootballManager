import {Component, OnInit} from '@angular/core';
import {Team} from "../team/team";
import {TeamService} from "../team.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Player} from "../player/player";
import {PlayerService} from "../player.service";

@Component({templateUrl: 'players.component.html'})
export class PlayersComponent implements OnInit {
  public players: Player[] = [];

  constructor(private playerService: PlayerService) {
  }

  ngOnInit(): void {
    this.getPlayers();
  }

  public getPlayers(): void {
    this.playerService.getPlayers().subscribe({
      next: (response: Player[]) => {
        this.players = response;
        console.log(this.players);
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }
}
