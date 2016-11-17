package com.tummsmedia.services;

import com.tummsmedia.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by john.tumminelli on 11/16/16.
 */
public interface UserRepo extends CrudRepository<User, Integer> {
    User findFirstByUsername(String username);
}
