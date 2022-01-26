import { Routes, RouterModule } from '@angular/router';

import { PlayerComponent } from './player';
import { PlayersComponent } from './players';
import { TeamComponent } from './team';
import { TeamsComponent } from './teams';

const routes: Routes = [
  { path: '', component: TeamsComponent },
  { path: 'players', component: PlayersComponent },
  // { path: 'user', component: RegisterComponent },    //TODO: make pathVariable somehow

  // if nothing of above = redirect to main page (teams)
  { path: '**', redirectTo: '' }
];

export const appRoutingModule = RouterModule.forRoot(routes);
