package com.tummsmedia.controllers;

import com.tummsmedia.entities.Transaction;
import com.tummsmedia.services.MessageRepo;
import com.tummsmedia.services.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by john.tumminelli on 11/27/16.
 */
@Controller
public class TransactionController {
    @Autowired
    MessageRepo messages;
    @Autowired
    TransactionRepo transactions;

    @RequestMapping(path = "/accept-or-decline", method = RequestMethod.GET)
    public String acceptOrDecline(@RequestParam("transactionId") int transactionId,
                                       @RequestParam("authKey") String authKey,
                                       @RequestParam("isAccepted") boolean isAccepted) throws Exception {
        if (messages.findByAuthKey(authKey) == null) {
            return "wrongAuthKey";
        }
        if (isAccepted == true){
            Transaction transaction = transactions.findOne(transactionId);
            transaction.setAccepted(true);
            transactions.save(transaction);
            return "toOwnerResponseAccept";
        }
        else if (isAccepted == false){
            Transaction transaction = transactions.findOne(transactionId);
            transaction.setAccepted(false);
            transactions.save(transaction);
            return "toOwnerResponseDecline";
        }
        return "";
    }
}
