package com.tummsmedia.entities;

import javax.persistence.*;

/**
 * Created by john.tumminelli on 11/16/16.
 */
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue()
    int transactionId;

    @Column(nullable = false)
    int borrowerId;
    @Column(nullable = false)
    int ownerId;
    @ManyToOne
    public Item item;

    @Transient
    boolean isAccepted;
    @Transient
    boolean isBorrowerNotified;
    @Transient
    boolean isOwnerNotifed;

    public Transaction() {
    }

    public Transaction(String owner) {

    }

    public Transaction(int borrowerId, int ownerId, Item item) {
        this.borrowerId = borrowerId;
        this.ownerId = ownerId;
        this.item = item;

    }

    public Transaction(int borrowerId, int ownerId, Item item, boolean isAccepted, boolean isBorrowerNotified, boolean isOwnerNotifed) {
        this.borrowerId = borrowerId;
        this.ownerId = ownerId;
        this.item = item;
        this.isAccepted = isAccepted;
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

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
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
