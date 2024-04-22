import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from './transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private transactionUrl = "http://localhost:8080/api/v1/transactions";


  createTransaction(transaction: Transaction): Observable<Object>{
    return this.httpClient.post(`${this.transactionUrl}`, transaction);
  }

  constructor(private httpClient: HttpClient) { }
  
  getTransactionList(): Observable<Transaction[]>{
    return this.httpClient.get<Transaction[]>(`${this.transactionUrl}`);
  }
}
