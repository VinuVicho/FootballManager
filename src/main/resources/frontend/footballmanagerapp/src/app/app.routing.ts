import { Routes, RouterModule } from '@angular/router';

import { PlayerComponent } from './player';
import { PlayersComponent } from './players';
import { TeamComponent } from './team';
import { TeamsComponent } from './teams';

const routes: Routes = [
  { path: '', component: TeamsComponent },
  { path: 'players', component: PlayersComponent },
  { path: 'player/:playerId', component: PlayerComponent },
  { path: 'team/:teamName', component: TeamComponent },

  // if nothing of above = redirect to main page (teams)
  { path: '**', redirectTo: '' }
];

export const appRoutingModule = RouterModule.forRoot(routes);
