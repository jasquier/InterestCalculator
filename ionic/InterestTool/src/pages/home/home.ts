import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';

import { SimplePage } from '../simple/simple';

import { ComplexPage } from '../complex/complex';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  form = {};

  constructor(public navCtrl: NavController) {

  }

  logForm(){
        console.log(this.form);
  }

}


