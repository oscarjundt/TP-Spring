package com.example.projectSpring.Repository;

import com.example.projectSpring.Model.Day;
import com.example.projectSpring.Model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;

@Repository
public interface repositoryDay extends CrudRepository<Day,Integer> {
    List<Day> findAll();
    Day findById(int id);
}
