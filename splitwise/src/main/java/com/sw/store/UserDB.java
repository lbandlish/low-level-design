package com.sw.store;

import com.sw.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lakshay
 * @since 26/12/21
 */
public class UserDB {
    Map<String, User> userMap;

    public UserDB(List<User> users) {
        userMap = new HashMap<>();
        users.forEach(this::addUser);
    }

    public User getUser(String userId) {
        return userMap.get(userId);
    }

    public void addUser(User user) {
        userMap.put(user.getUserId(), user);
    }

    public List<String> getUserIds() {
        List<String> userIds = new ArrayList<>();
        userMap.forEach((key, value) -> userIds.add(key));
        return userIds;
    }
}
