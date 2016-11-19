package com.tummsmedia.entities;

import javax.persistence.*;

/**
 * Created by john.tumminelli on 11/16/16.
 */
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue
    int transactionId;

    @Column(nullable = false)
    int borrowerId;
    @Column(nullable = false)
    int ownerId;
    @ManyToOne
    public Item item;

    @Transient
    boolean isFinal;
    @Transient
    boolean isBorrowerNotified;
    @Transient
    boolean isOwnerNotifed;

    public Transaction() {
    }

    public Transaction(int borrowerId, int ownerId, Item item) {
        this.borrowerId = borrowerId;
        this.ownerId = ownerId;
        this.item = item;
    }

    public Transaction(int borrowerId, int ownerId, Item item, boolean isFinal, boolean isBorrowerNotified, boolean isOwnerNotifed) {
        this.borrowerId = borrowerId;
        this.ownerId = ownerId;
        this.item = item;
        this.isFinal = isFinal;
        this.isBorrowerNotified = isBorrowerNotified;
        this.isOwnerNotifed = isOwnerNotifed;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId() {
        this.borrowerId = borrowerId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId() {
        this.ownerId = ownerId;
    }

    public Item item() {
        return item;
    }

    public void item(Item item) {
        this.item = item;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public boolean isBorrowerNotified() {
        return isBorrowerNotified;
    }

    public void setBorrowerNotified(boolean borrowerNotified) {
        isBorrowerNotified = borrowerNotified;
    }

    public boolean isOwnerNotifed() {
        return isOwnerNotifed;
    }

    public void setOwnerNotifed(boolean ownerNotifed) {
        isOwnerNotifed = ownerNotifed;
    }
}
