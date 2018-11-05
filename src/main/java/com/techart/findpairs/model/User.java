package com.techart.findpairs.model;

import java.util.List;

public class User {

    private String name;
    private List<String> interests;
    public User (String name, List<String> intersts){
        this.name = name;
        this.interests = intersts;
    }

    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", interests=" + interests +
                '}';
    }
}
