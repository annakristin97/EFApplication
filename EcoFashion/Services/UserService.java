package com.EFApplication.EcoFashion.Services;

import com.EFApplication.EcoFashion.Entities.User;
import java.util.List;

public interface UserService {
    User save(User user);
    void delete(User user);
    void deleteAll();
    List<User> findAll();
    User findByuserName(String userName);
    User login(User user);
}
