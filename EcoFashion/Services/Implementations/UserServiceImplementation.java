package com.EFApplication.EcoFashion.Services.Implementations;

import com.EFApplication.EcoFashion.Entities.User;
import com.EFApplication.EcoFashion.Repositories.UserRepository;
import com.EFApplication.EcoFashion.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    UserRepository repository;

    @Autowired
    public UserServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public void deleteAll(){
        repository.deleteAll();
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findByuserName(String userName){
        return repository.findByuserName(userName);
    }

    @Override
    public User login(User user) {
        User exists = findByuserName(user.getUserName());
        if(exists != null){
            if(exists.getUserPassword().equals(user.getUserPassword())){
                System.out.println("Password match");
                return exists;
            }
        }
        return null;
    }
}
