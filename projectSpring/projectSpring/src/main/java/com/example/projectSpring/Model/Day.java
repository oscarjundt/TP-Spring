package com.example.projectSpring.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Day{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String number;

    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    protected Championship championships;

    @Nullable
    @OneToMany(mappedBy = "day",fetch = FetchType.LAZY)
    @JsonIgnore
    protected List<Game> games;

    public Day(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Nullable
    public Championship getChampionships() {
        return championships;
    }

    public void setChampionships(@Nullable Championship championships) {
        this.championships = championships;
    }

    @Nullable
    public List<Game> getGames() {
        return games;
    }

    public void setGames(@Nullable List<Game> games) {
        this.games = games;
    }
}
