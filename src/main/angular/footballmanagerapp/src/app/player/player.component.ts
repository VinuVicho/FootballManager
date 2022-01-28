import {Component, OnInit} from '@angular/core';
import {Player} from "./player";
import {ActivatedRoute} from "@angular/router";
import {PlayerService} from "../player.service";
import {Team} from "../team/team";
import {HttpErrorResponse} from "@angular/common/http";

@Component({ templateUrl: 'player.component.html' })
export class PlayerComponent implements OnInit {

  player: Player | undefined;

  constructor(private route: ActivatedRoute, private playerService: PlayerService) { }

  ngOnInit() {
    const routeParams = this.route.snapshot.paramMap;
    const playerIdFromRoute = Number(routeParams.get('playerId'));
    this.getPlayer(playerIdFromRoute);
  }

  public getPlayer(playerId: number): void {
    this.playerService.getPlayer(playerId).subscribe(
      (response: Player) => {
        this.player = response;
        console.log(this.player);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeletePlayer() {
    this.playerService.deletePlayer(this.player!.id).subscribe(
      (response: void) => {
        console.log("Redirect to all players");
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
