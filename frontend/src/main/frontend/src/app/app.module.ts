import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';

import {HttpClientModule} from '@angular/common/http';
import {LoginService} from './_services/login.service';
import {RouterModule, Routes} from '@angular/router';
import {CyclopaediaComponent} from './cyclopaedia/cyclopaedia.component';
import {CyclopaediaService} from './_services/cyclopaedia.service';
import { RulesComponent } from './rules/rules.component';
import { PlayComponent } from './play/play.component';
import { HeroComponent } from './cyclopaedia/heroes/hero/hero.component';
import { MonsterComponent } from './cyclopaedia/monsters/monster/monster.component';
import { HeroesComponent } from './cyclopaedia/heroes/heroes.component';
import { MonstersComponent } from './cyclopaedia/monsters/monsters.component';
import { ItemsComponent } from './cyclopaedia/items/items.component';
import { ItemComponent } from './cyclopaedia/items/item/item.component';
import { GamesComponent } from './games/games.component';
import { SearchComponent } from './games/search/search.component';
import { CreateComponent } from './games/create/create.component';
import {GamesService} from './_services/games.service';
import {AuthGuardService} from './_services/authGuard.service';
import { MyGamesComponent } from './play/my-games/my-games.component';
import { PlayBoardComponent } from './play/play-board/play-board.component';
import { ClassesComponent } from './games/classes/classes.component';
import {TurnService} from './_services/turn.service';


const appRoutes: Routes = [
  {path: '', component : LoginComponent},
  {path: 'login', component : LoginComponent},
  {path: 'cyclopaedia', component : CyclopaediaComponent,
    children: [{path: '', component: HeroesComponent},
      {path: 'heroes', component: HeroesComponent},
    {path: 'monsters', component: MonstersComponent},
    {path: 'items', component: ItemsComponent}]},
  {path: 'rules', component : RulesComponent},
  {path: 'play', component : PlayComponent, canActivate: [AuthGuardService],
  children: [{path: '', component: MyGamesComponent },
    {path: 'mygames', component: MyGamesComponent },
    {path: 'playboard', component: PlayBoardComponent}]},
  {path: 'games', component: GamesComponent, canActivate: [AuthGuardService],
  children: [{path: '', component: SearchComponent },
    {path: 'search', component: SearchComponent},
    {path: 'classes', component: ClassesComponent},
    {path: 'create', component: CreateComponent}]}
];


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    CyclopaediaComponent,
    RulesComponent,
    PlayComponent,
    HeroComponent,
    MonsterComponent,
    HeroesComponent,
    MonstersComponent,
    ItemsComponent,
    ItemComponent,
    GamesComponent,
    SearchComponent,
    CreateComponent,
    MyGamesComponent,
    PlayBoardComponent,
    ClassesComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, FormsModule, HttpModule, RouterModule.forRoot(appRoutes),
  ],
  providers: [LoginService, CyclopaediaService, GamesService, AuthGuardService, TurnService],
  bootstrap: [AppComponent]
})
export class AppModule { }
