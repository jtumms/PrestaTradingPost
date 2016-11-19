package com.tummsmedia.services;

import com.tummsmedia.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by john.tumminelli on 11/17/16.
 */
public interface TransactionRepo extends CrudRepository<Transaction, Integer> {

}
