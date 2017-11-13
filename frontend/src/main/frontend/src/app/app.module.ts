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
import { RulesComponent } from './rules/rules.component';
import { PlayComponent } from './play/play.component';

const appRoutes: Routes = [
  {path: 'login', component : LoginComponent},
  {path: 'cyclopaedia', component : CyclopaediaComponent},
  {path: 'rules', component : RulesComponent},
  {path: 'play', component : PlayComponent}


];


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    CyclopaediaComponent,
    RulesComponent,
    PlayComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, FormsModule, HttpModule, RouterModule.forRoot(appRoutes),
  ],
  providers: [LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
