import { Component, OnInit} from '@angular/core';
import { Transaction } from '../transaction';
import { TransactionService } from '../transaction.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrl: './transaction.component.css'
})

export class TransactionComponent {

  transaction: Transaction = new Transaction();
  constructor(private transactionService: TransactionService,
    private router: Router) { }

  ngOnInit(): void {
  }

  saveTransaction(){
    this.transactionService.createTransaction(this.transaction).subscribe( data =>{
      console.log(data);
      this.goToTransactionList();
    },
    error => console.log(error));
  }

  goToTransactionList(){
    this.router.navigate(['/transactions-log']);
  }

  onSubmit(){
    console.log(this.transaction);
    this.saveTransaction();
  }
  
}