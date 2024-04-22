import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { TransactionComponent } from './transaction/transaction.component';
import { FrontpageComponent } from './frontpage/frontpage.component';
import { TransactionListComponent } from './transaction-list/transaction-list.component';

const routes: Routes = [
  {path: 'frontpage', component: FrontpageComponent},
  {path: 'accounts', component: UserListComponent},
  {path: 'create-user', component: CreateUserComponent},
  {path: '', redirectTo: 'frontpage', pathMatch: 'full'},
  {path: 'transactions', component: TransactionComponent},
  {path: 'transactions-log', component: TransactionListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
