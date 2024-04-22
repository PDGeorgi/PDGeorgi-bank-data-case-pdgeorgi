import { Component, OnInit } from '@angular/core';
import { User } from '../user'
import { Transaction } from '../transaction'
import { UserService } from '../user.service';
import { TransactionService } from '../transaction.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})

export class UserListComponent implements OnInit  {

  users: User[];
  transactions: Transaction[];

  constructor(private userService: UserService,
    private transactionService: TransactionService,
    private router: Router) { }

  ngOnInit(): void {
    this.getUsers();
  }

  private getUsers() {
    this.userService.getUserList().subscribe(data => {
      this.users = data;
    });
  }

  // updateUser(id: number){
  //   this.router.navigate(['update-user', id]);
  // }

  // deleteUser(id: number){
  //   this.userService.deleteUser(id).subscribe( data => {
  //     console.log(data);
  //     this.getUsers();
  //   })
  // }

}
