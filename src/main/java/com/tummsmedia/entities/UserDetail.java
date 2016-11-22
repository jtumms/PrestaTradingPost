package com.tummsmedia.entities;

import javax.persistence.*;

/**
 * Created by john.tumminelli on 11/16/16.
 */
@Entity
@Table(name = "user_details")
public class UserDetail {
    @Id
    @GeneratedValue
    @Column(name = "uDetail_PK")
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String street;
    @Column
    private String address2;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private int zipcode;
    @Column(nullable = false)
    private int accountNum;
    @Column(nullable = false)
    private int rating;


    public UserDetail() {
    }

    public UserDetail(String firstName, String lastName, String street, String city, String state, int zipcode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public UserDetail(String firstName, String lastName, String street, String address2, String city, String state, int zipcode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public UserDetail(String firstName, String lastName, String street, String address2, String city, String state, int zipcode, int accountNum, int rating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.accountNum = accountNum;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
