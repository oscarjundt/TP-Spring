package com.example.projectSpring.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String name;
    protected Date creationDate;
    @Nullable
    @OneToMany(mappedBy = "team",fetch = FetchType.LAZY)
    @JsonIgnore
    protected List<Game> games;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "teams")
    @JsonIgnore
    protected List<Championship> championship;

    public Team(){}

    public List<Championship> getChampionship() {
        return championship;
    }

    public void setChampionship(List<Championship> championship) {
        this.championship = championship;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Nullable
    public List<Game> getGames() {
        return games;
    }

    public void setGames(@Nullable List<Game> games) {
        this.games = games;
    }
}
