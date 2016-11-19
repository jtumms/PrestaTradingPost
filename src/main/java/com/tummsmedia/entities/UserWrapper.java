package com.tummsmedia.entities;

import java.util.ArrayList;

/**
 * Created by john.tumminelli on 11/18/16.
 */
public class UserWrapper {
    public ArrayList<User> usersWrapped;

    public UserWrapper() {
    }

    public UserWrapper(ArrayList<User> usersWrapped) {
        this.usersWrapped = usersWrapped;
    }

    public ArrayList<User> getUsersWrapped() {
        return usersWrapped;
    }

    public void setUsersWrapped(ArrayList<User> usersWrapped) {
        this.usersWrapped = usersWrapped;
    }
}
