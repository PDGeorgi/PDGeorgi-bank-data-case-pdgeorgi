import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../transaction.service';
import { Transaction } from '../transaction';
import { Router } from '@angular/router';


@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrl: './transaction-list.component.css'
})
export class TransactionListComponent implements OnInit {

  transactions: Transaction[]; 

  constructor(private transactionService: TransactionService,
    private router: Router) { }

  ngOnInit(): void {
    this.getTransactions();
  }

  private getTransactions() {
    this.transactionService.getTransactionList().subscribe(data => {
      this.transactions = data;
    },
    error => console.log(error));
  }

}