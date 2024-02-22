package com.shoollessons.school.lessons.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>(List.of(
            User.builder().id(1L).email("kartfka123@.ua").firstName("Artem").lastName("Ponomarov").build(),
            User.builder().id(2L).email("ka123@.ua").firstName("Karlos").lastName("Benik").build()));


    public User createUser(User user) {
        user.setId(users.size() + 1L);
        users.add(user);
        return user;
    }


    public List<User> findAllUsers() {
        return users;
    }


    public User findUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User deleteUserById(Long id) {
        users.remove(findUserById(id));
        return null;
    }

    public User updateUserById(Long id, User newUser) {
        User user = findUserById(id);
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        return user;
    }
}
