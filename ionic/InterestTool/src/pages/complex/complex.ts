import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { InterestService } from '../../services/interest-service';

/*
  Generated class for the Complex page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-complex',
  templateUrl: 'complex.html'

})
export class ComplexPage {
  InterestService:any;

  constructor(public navCtrl: NavController, public navParams: NavParams, public interestService:InterestService) {}

  ionViewDidLoad() {
    console.log('ionViewDidLoad ComplexPage');
  }

  callInterestService(){
    this.interestService.testInterestValue();
    console.log("hi john");
  }

}
