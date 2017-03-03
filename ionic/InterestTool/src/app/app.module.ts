import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import {TabsPage} from '../pages/tabs/tabs';
import { ComplexPage } from '../pages/complex/complex';
import {SimplePage } from '../pages/simple/simple';
import { InterestService } from '../services/interest-service';


@NgModule({
  declarations: [
    MyApp,
    HomePage,
    TabsPage,
    ComplexPage,
    SimplePage
  ],
  imports: [
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    TabsPage,
    ComplexPage,
    SimplePage
  ],
  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler}, InterestService]
})
export class AppModule {}
