package com.tummsmedia.entities;


import org.springframework.data.jpa.repository.query.StringQueryParameterBinder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Image> images = new HashSet<Image>();

    @ManyToOne
    User user;

    @Transient
    String mapUrl;


    public Item() {
    }

    public Item(int itemId, String itemName, String itemDescription, Category category, long estValue, long askingPrice, Condition condition, Set<Image> images, User user, String mapUrl) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.category = category;
        this.estValue = estValue;
        this.askingPrice = askingPrice;
        this.condition = condition;
        this.images = images;
        this.user = user;
        this.mapUrl = mapUrl;
    }

    public Item(String itemName, String itemDescription, Category category, long estValue, long askingPrice, Condition condition, HashSet<Image> images, User user, String mapUrl) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.category = category;
        this.estValue = estValue;
        this.askingPrice = askingPrice;
        this.condition = condition;
        this.images = images;
        this.user = user;
        this.mapUrl = mapUrl;
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

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
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

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }
}
