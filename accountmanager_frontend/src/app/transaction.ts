export class Transaction {
    id: number;
    fromAccountId: number;
    toAccountId: number;
    fromName: string;
    toName: string;
    transferAmount: number;
    newBalance: number;
}
