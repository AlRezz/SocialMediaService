package com.techart.findpairs.util;

import com.techart.findpairs.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UtilTest {



    private User initUser(String name, List<String> interests)
    {
        return  new User(name, interests);
    }

    @Test
    public void getEqualsInterests() {
        User userOne = initUser("John", Arrays.asList("movie", "cars"));
        User userTwo = initUser("Ben", Arrays.asList("movie", "cars", "books"));
        List<String> result = Util.getEqualsInterests(userOne.getInterests(), userTwo.getInterests());
        assertEquals(2, result.size());
    }

    @Test
    public void getCost() {
        User userOne = initUser("John", Arrays.asList("movie", "cars"));
        User userTwo = initUser("Ben", Arrays.asList("movie", "cars", "books"));
        assertEquals(2, Util.getCost(userOne.getInterests(), userTwo.getInterests()));
    }

    @Test
    public void getMatrix() {
    }
}