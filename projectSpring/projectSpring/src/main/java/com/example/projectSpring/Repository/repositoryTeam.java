package com.example.projectSpring.Repository;


import com.example.projectSpring.Model.Game;
import com.example.projectSpring.Model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface repositoryTeam extends CrudRepository<Team,Integer> {
    ArrayList<Team> findAll();
    Team findById(int id);
    List<Team> findTeamByChampionshipId(int id);
}
