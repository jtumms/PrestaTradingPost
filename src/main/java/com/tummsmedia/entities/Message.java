package com.tummsmedia.entities;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.tummsmedia.controllers.ItemController;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.*;
import java.util.UUID;

/**
 * Created by john.tumminelli on 11/26/16.
 */
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    public int messageId;
    @Column(nullable = false)
    public int ownerId;
    @Column(nullable = false)
    public int renterId;
    @Column
    public String body;
    @Column(nullable = false)
    public String subject;
    @Column
    public String authKey;


    public Message() {
    }

    public Message(int ownerId, int renterId, String body, String subject, String authKey) {
        this.ownerId = ownerId;
        this.renterId = renterId;
        this.body = body;
        this.subject = subject;
        this.authKey = authKey;

    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }


}
