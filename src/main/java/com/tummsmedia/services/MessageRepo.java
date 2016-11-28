package com.tummsmedia.services;

import com.tummsmedia.entities.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by john.tumminelli on 11/27/16.
 */
public interface MessageRepo extends CrudRepository<Message, Integer>{
    Message findByAuthKey(String authKey);
}
