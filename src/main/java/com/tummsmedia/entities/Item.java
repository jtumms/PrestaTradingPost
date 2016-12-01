package com.tummsmedia.entities;


import javax.persistence.*;
import java.util.*;

/**
 * Created by john.tumminelli on 11/16/16.
 */
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue
    public int itemId;

    @Column(nullable = false)
    String itemName;

    @Column (nullable = false)
    String itemDescription;

    public enum Category {
        SPORTINGGOODS,
        GENERAL,
        ELECTRONICS,
        TOOLS,
        OUTDOOR
    }

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
    private Category category;

    @Column(nullable = false)
    private Condition condition;

    @ElementCollection
    @CollectionTable(name = "item_images", joinColumns = @JoinColumn(name = "itemId"))
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    User user;

    @Transient
    HashMap<String, Double> latLng;


    public Item() {
    }

    public Item(int itemId, String itemName, String itemDescription, Category category, long estValue, long askingPrice, Condition condition, List<Image> images, User user, HashMap<String, Double> latLng) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.category = category;
        this.estValue = estValue;
        this.askingPrice = askingPrice;
        this.condition = condition;
        this.images = images;
        this.user = user;
        this.latLng = latLng;
    }

    public Item(String itemName, String itemDescription, Category category, long estValue, long askingPrice, Condition condition, List<Image> images, User user, HashMap<String, Double> latLng) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.category = category;
        this.estValue = estValue;
        this.askingPrice = askingPrice;
        this.condition = condition;
        this.images = images;
        this.user = user;
        this.latLng = latLng;
    }

    public Item(String itemName, String itemDescription, Category category, long estValue, long askingPrice, Condition condition, List<Image> images, User user) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.category = category;
        this.estValue = estValue;
        this.askingPrice = askingPrice;
        this.condition = condition;
        this.images = images;
        this.user = user;
        this.latLng = latLng;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HashMap<String, Double> getLatLng() {
        return latLng;
    }

    public void setLatLng(HashMap<String, Double> latLng) {
        this.latLng = latLng;
    }
}
