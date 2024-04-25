package com.example.projectSpring.Repository;


import com.example.projectSpring.Model.Championship;
import com.example.projectSpring.Model.Day;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface repositoryChampionship extends CrudRepository<Championship,Integer> {
    List<Championship> findAll();
    Championship findById(int id);
}
