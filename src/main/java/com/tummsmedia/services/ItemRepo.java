package com.tummsmedia.services;

import com.tummsmedia.entities.Item;
import com.tummsmedia.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by john.tumminelli on 11/16/16.
 */
public interface ItemRepo extends CrudRepository<Item, Integer> {
    Iterable<Item> findAllByCategory(Item.Category category);
    Item findFirstByItemId(int itemId);

}
