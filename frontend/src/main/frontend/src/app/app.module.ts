import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';

// http kérésekhez
import {HttpClientModule} from '@angular/common/http';
import {LoginService} from './login/login.service';
import {RouterModule, Routes} from '@angular/router';
import {CyclopaediaComponent} from './cyclopaedia/cyclopaedia.component';
import {CyclopaediaService} from './cyclopaedia/cyclopaedia.service';
import { RulesComponent } from './rules/rules.component';
import { PlayComponent } from './play/play.component';
import { HeroComponent } from './cyclopaedia/heroes/hero/hero.component';
import { MonsterComponent } from './cyclopaedia/monsters/monster/monster.component';
import { HeroesComponent } from './cyclopaedia/heroes/heroes.component';
import { MonstersComponent } from './cyclopaedia/monsters/monsters.component';
import { ItemsComponent } from './cyclopaedia/items/items.component';
import { ItemComponent } from './cyclopaedia/items/item/item.component';


const appRoutes: Routes = [
  {path: 'login', component : LoginComponent},
  {path: 'cyclopaedia', component : CyclopaediaComponent},
  {path: 'rules', component : RulesComponent},
  {path: 'play', component : PlayComponent},
  {path: 'heroes', component: HeroesComponent},
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
    ItemComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, FormsModule, HttpModule, RouterModule.forRoot(appRoutes),
  ],
  providers: [LoginService, CyclopaediaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
