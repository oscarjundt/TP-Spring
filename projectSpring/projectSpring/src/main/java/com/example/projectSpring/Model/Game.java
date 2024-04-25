package com.example.projectSpring.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    protected Team team;

    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    protected Day day;

    public Game(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public Team getTeam() {
        return team;
    }

    public void setTeam(@Nullable Team team) {
        this.team = team;
    }

    @Nullable
    public Day getDay() {
        return day;
    }

    public void setDay(@Nullable Day day) {
        this.day = day;
    }
}
