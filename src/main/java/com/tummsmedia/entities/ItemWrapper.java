package com.tummsmedia.entities;

import java.util.ArrayList;

/**
 * Created by john.tumminelli on 11/16/16.
 */
public class ItemWrapper {
    public ArrayList<Item> items;

    public ItemWrapper() {
    }

    public ItemWrapper(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
