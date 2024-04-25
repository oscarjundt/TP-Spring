package com.example.projectSpring.Repository;


import com.example.projectSpring.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface repositoryUser extends CrudRepository<User,Integer> {
    ArrayList<User> findAll();
    User findById(int id);
    User findByPasswrodAndEmail(String Password,String Email);
}
