package com.techart.findpairs.model;

import java.util.List;

public class UserPair
{
    User firstUser, secondUser;
    List<String> interests;
    public UserPair(){}

    public UserPair(User firstUser, User secondUser, List<String> interests)
    {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.interests = interests;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    @Override
    public String toString() {
        return firstUser.getName() + " - " + secondUser.getName() + " " + interests;
    }
}
