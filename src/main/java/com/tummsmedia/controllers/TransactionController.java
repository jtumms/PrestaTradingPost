package com.tummsmedia.controllers;

import com.tummsmedia.entities.Transaction;
import com.tummsmedia.services.MessageRepo;
import com.tummsmedia.services.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by john.tumminelli on 11/27/16.
 */
@RestController
public class TransactionController {
    @Autowired
    MessageRepo messages;
    @Autowired
    TransactionRepo transactions;

    @RequestMapping(path = "/accept-or-decline", method = RequestMethod.POST )
    public void acceptOrDecline(@RequestParam("transactionId") int transactionId,
                                       @RequestParam("authKey") String authKey,
                                       @RequestParam("isAccepted") boolean isAccepted) throws Exception {
        if (messages.findByAuthKey(authKey) == null) {
            throw new Exception("Invalid message authentication key. Request is declined!!");
        }
        if (isAccepted){
            Transaction transaction = transactions.findOne(transactionId);
            transaction.setAccepted(true);
            System.out.println("Owner has sent an accept response via email");
//            MessageController.sendRenterMessageAccept(transaction);
        }

    }


}
