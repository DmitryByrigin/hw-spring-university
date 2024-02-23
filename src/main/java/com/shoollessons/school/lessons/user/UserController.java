package com.shoollessons.school.lessons.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createCustomer(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> findAllCustomers() {
        return userService.findAllUsers();
    }

    @GetMapping("{id}")
    public User findCustomerById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("{id}")
    public User deleteCustomerById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

    @PutMapping("{id}")
    public User updateCustomerById(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }
}
