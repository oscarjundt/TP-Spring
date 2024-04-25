package com.example.projectSpring.Repository;


import com.example.projectSpring.Model.Game;
import com.example.projectSpring.Model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface repositoryGame extends CrudRepository<Game,Integer> {
    List<Game> findAll();
    Game findById(int id);
}
