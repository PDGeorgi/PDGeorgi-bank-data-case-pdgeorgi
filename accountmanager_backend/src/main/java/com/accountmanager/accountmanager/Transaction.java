package com.accountmanager.accountmanager;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "transactions_table")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "from_account_id")
    private int fromAccountId;

    @Column(name = "to_account_id")
    private int toAccountId;

    @Column(name = "from_name")
    private String fromName;

    @Column(name = "to_name")
    private String toName;

    @Column(name = "transfer_amount")
    private double transferAmount;

    @Column(name = "new_balance")
    private double newBalance;

    public Transaction() {

    }
    
    public Transaction(int fromAccountId, int toAccountId, String toName, String fromName, double transferAmount, double newBalance) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.toName = toName;
        this.fromName = fromName;
        this.transferAmount = transferAmount;
        this.newBalance = newBalance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(int toAccountId) {
        this.fromAccountId = toAccountId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }

}
