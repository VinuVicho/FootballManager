import {Component, OnInit} from '@angular/core';
import {Player} from "./player";
import {ActivatedRoute, Router} from "@angular/router";
import {PlayerService} from "../player.service";
import {Team} from "../team/team";
import {HttpErrorResponse} from "@angular/common/http";

@Component({templateUrl: 'player.component.html'})
export class PlayerComponent implements OnInit {

  player: Player | undefined;

  constructor(private route: ActivatedRoute,
              private playerService: PlayerService,
              private router: Router) {
  }

  ngOnInit() {
    const routeParams = this.route.snapshot.paramMap;
    const playerIdFromRoute = Number(routeParams.get('playerId'));
    this.getPlayer(playerIdFromRoute);
  }

  public getPlayer(playerId: number): void {
    this.playerService.getPlayer(playerId).subscribe({
      next: (response: Player) => {
        this.player = response;
        console.log(this.player);
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }

  public onUpdatePlayer(player: Player): void {
    this.playerService.updatePlayer(player).subscribe({
      next: (response: Player) => {
        console.log(response);
        window.location.reload();
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }

  public onDeletePlayer() {
    this.playerService.deletePlayer(this.player!.id).subscribe({
      next: (response: void) => {
        console.log("Redirect to all players");
        this.router.navigate(['/players']);
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }
}
