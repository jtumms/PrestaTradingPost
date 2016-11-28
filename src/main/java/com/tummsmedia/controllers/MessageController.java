package com.tummsmedia.controllers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.tummsmedia.entities.*;
import com.tummsmedia.services.MessageRepo;
import com.tummsmedia.services.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by john.tumminelli on 11/27/16.
 */
@Controller
public class MessageController {
    @Autowired
    MessageRepo messages;
    @Autowired
    UserRepo users;

    public static ClientResponse sendOwnerMessage(Item itemBorrowed, Transaction transaction, MessageRepo messages, UserRepo users) {
        Message message = new Message();
        String generatedKey = UUID.randomUUID().toString();
        message.authKey = generatedKey;
        final String ACCEPT_URL = String.format("http://localhost:8080/accept-or-decline?transactionId=%s&isAccepted=TRUE&authKey=%s", Integer.toString(transaction.getTransactionId()), message.authKey);
        final String DECLINE_URL = String.format("http://localhost:8080/accept-or-decline?transactionId=%s&isAccepted=FALSE&authKey=%s", Integer.toString(transaction.getTransactionId()), message.authKey);

        message.setOwnerId(transaction.getOwnerId());
        message.setRenterId(transaction.getBorrowerId());
        String subjectText = String.format("You have a rental request for your one of your items. Transaction ID: %s", transaction.getTransactionId());
        message.setSubject(subjectText);
        messages.save(message);

        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api",
                "key-e40dbe458be098bc00352bae39fd72ba"));
        WebResource webResource =
                client.resource("https://api.mailgun.net/v3/sandbox24e2ae74809546f08a2ce2168f7ba9e8.mailgun.org/" +
                        "messages");
        FormDataMultiPart form = new FormDataMultiPart();
        form.field("from", "Mailgun Sandbox <postmaster@sandbox24e2ae74809546f08a2ce2168f7ba9e8.mailgun.org>");
        User owner = users.findFirstById(message.ownerId);
        form.field("to", owner.getUsername());
        form.field("subject", subjectText);

//        form.field("html", body);
        String file_separator = System.getProperty("file.separator");

        ArrayList<Object> imageArrayList = new ArrayList<>();
        Iterator iterator = itemBorrowed.getImages().iterator();
        while (iterator.hasNext()){
            imageArrayList.add(iterator.next());
        }Image image = (Image) imageArrayList.get(0);

        File jpgFile = new File("public" + file_separator + "images" + file_separator + image.getImageFileName());
        form.bodyPart(new FileDataBodyPart("attachment",jpgFile,
                MediaType.APPLICATION_OCTET_STREAM_TYPE));
        return webResource.type(MediaType.MULTIPART_FORM_DATA_TYPE).
                post(ClientResponse.class, form);
    }




}
