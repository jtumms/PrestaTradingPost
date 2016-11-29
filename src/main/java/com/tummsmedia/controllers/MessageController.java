package com.tummsmedia.controllers;

import com.samskivert.mustache.Mustache;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.tummsmedia.entities.*;
import com.tummsmedia.services.ItemRepo;
import com.tummsmedia.services.MessageRepo;
import com.tummsmedia.services.TransactionRepo;
import com.tummsmedia.services.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.*;

/**
 * Created by john.tumminelli on 11/27/16.
 */
@RestController
public class MessageController {

    public static ClientResponse sendOwnerMessage(Transaction transaction, UserRepo users, ItemRepo items, MessageRepo messages) {
        Message message = new Message();
        User renter = users.findFirstById(transaction.getBorrowerId());
        User owner = users.findFirstById(transaction.getOwnerId());
        Item rentedItem = items.findFirstByItemId(transaction.getItem().getItemId());
        String[] renterNameArray = renter.getUsername().split("@");
        String renterName = renterNameArray[0];
        String generatedKey = UUID.randomUUID().toString();
        message.authKey = generatedKey;
        HashMap<String, String> m = new HashMap<>();
        final String ACCEPT_URL = String.format("http://localhost:8080/accept-or-decline?transactionId=%s&isAccepted=TRUE&authKey=%s", Integer.toString(transaction.getTransactionId()), message.authKey);
        final String DECLINE_URL = String.format("http://localhost:8080/accept-or-decline?transactionId=%s&isAccepted=FALSE&authKey=%s", Integer.toString(transaction.getTransactionId()), message.authKey);
        m.put("acceptUrl", ACCEPT_URL);
        m.put("declineUrl", DECLINE_URL);
        String messageBody = String.format("Your fellow Presta partner, %s, has requested to rent your listed item %s at a price of %s US Dollars. Please accept or decline this transaction below.", renterName, rentedItem.getItemName(), rentedItem.getAskingPrice());
        m.put("messageBody", messageBody);
        message.setOwnerId(transaction.getOwnerId());
        message.setRenterId(transaction.getBorrowerId());
        String subjectText = String.format("You have a rental request for one of your items. Transaction ID: %s", transaction.getTransactionId());
        message.setSubject(subjectText);
        message.setBody(messageBody);
        messages.save(message);

        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api",
                "key-e40dbe458be098bc00352bae39fd72ba"));
        WebResource webResource =
                client.resource("https://api.mailgun.net/v3/sandbox24e2ae74809546f08a2ce2168f7ba9e8.mailgun.org/" +
                        "messages");
        FormDataMultiPart form = new FormDataMultiPart();
        form.field("from", "Mailgun Sandbox <postmaster@sandbox24e2ae74809546f08a2ce2168f7ba9e8.mailgun.org>");
        form.field("to", owner.getUsername());
        form.field("subject", subjectText);
        ArrayList<String> photoNames = new ArrayList<>();
        Set<Image> itemImageSet = rentedItem.getImages();
        for (Image img: itemImageSet){
            photoNames.add(itemImageSet.iterator().next().getImageFileName());
        }
        m.put("itemImage", photoNames.get(0));
        File jpgFile = new File("public/images/" + photoNames.get(0));
        form.bodyPart(new FileDataBodyPart("inline", jpgFile,
                MediaType.APPLICATION_OCTET_STREAM_TYPE));
        StringWriter writer = new StringWriter();
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader("public/toOwnerMailTemplate.html"));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        String content = contentBuilder.toString();
        Mustache.compiler().compile(content).execute(m, writer);
        form.field("html", String.valueOf(writer));




        return webResource.type(MediaType.MULTIPART_FORM_DATA_TYPE).
                post(ClientResponse.class, form);
    }
}


