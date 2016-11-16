package com.tummsmedia.entities;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by john.tumminelli on 11/16/16.
 */
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String itemName;

    @Column(nullable = false)
    String category;

    @Column(nullable = false)
    long estValue;

    @Column(nullable = false)
    long askingPrice;

    public enum Condition {
        EXCELLENT,
        VERYGOOD,
        GOOD,
        FAIR,
        POOR
    }

    @Column(nullable = false)
    private Condition condition;

    @Column(nullable = false)
    private ArrayList<String> images;

    public Item() {
    }

    public Item(String itemName, String category, long estValue, long askingPrice, Condition condition, ArrayList<String> images) {
        this.itemName = itemName;
        this.category = category;
        this.estValue = estValue;
        this.askingPrice = askingPrice;
        this.condition = condition;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getEstValue() {
        return estValue;
    }

    public void setEstValue(long estValue) {
        this.estValue = estValue;
    }

    public long getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(long askingPrice) {
        this.askingPrice = askingPrice;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
