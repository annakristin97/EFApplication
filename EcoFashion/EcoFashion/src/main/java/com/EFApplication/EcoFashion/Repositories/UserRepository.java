package com.EFApplication.EcoFashion.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.EFApplication.EcoFashion.Entities.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    void delete(User user);


    @Override
    void deleteAll();

    List<User> findAll();
    User findByuserName(String userName);
}