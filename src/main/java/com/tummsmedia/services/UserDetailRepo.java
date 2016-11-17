package com.tummsmedia.services;

import com.tummsmedia.entities.UserDetail;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by john.tumminelli on 11/16/16.
 */
public interface UserDetailRepo extends CrudRepository<UserDetail, Integer> {

}
