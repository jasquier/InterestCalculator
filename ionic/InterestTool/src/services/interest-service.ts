import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class InterestService {

    private data: any;
    private responseData: any;
    private interestValue: any;
    private url: string = "http://127.0.0.1:8080/interestCalculator";

    constructor(private http: Http) {}

    getInterestValue(balance: number, accountType: string, interestRate:number, overDraftPenalty:number,
    requiredMinimumBalance:number, isMinimumBalanceRequired:boolean, recurringTransactions:[any], accountHistory:[any],
     interval:number, frequency:number,interestType:number, calculationRule:number, numDaysForRule:number) {
        this.data = {
            "account": {
                "balance": balance,
                "accountType": accountType,
                "interestRate": interestRate,
                "overDraftPenalty": overDraftPenalty,
                "requiredMinimumBalance": requiredMinimumBalance,
                "isMinimumBalanceRequired": isMinimumBalanceRequired,
                "recurringTransactions": null,
                "accountHistory": null
            },
            "interval": interval,
            "frequency": frequency,
            "interestType": interestRate,
            "calculationRule": calculationRule,
            "numDaysForRule": numDaysForRule
        };
        
        this.http.post(this.url, this.data).subscribe(response => {
            this.responseData = response.json();
            console.log(this.responseData);
            this.interestValue = this.responseData.interestValue;
        });
    }
}