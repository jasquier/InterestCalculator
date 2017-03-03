import { Component } from '@angular/core';
import { HomePage } from '../home/home';
import { SimplePage } from '../simple/simple';
import { ComplexPage } from '../complex/complex';

@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root: any = HomePage;
  tab2Root: any = SimplePage;
  tab3Root: any = ComplexPage;

  constructor() {

  }
}
