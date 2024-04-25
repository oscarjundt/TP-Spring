package com.example.projectSpring.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Championship{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String name;
    protected Date startDate;
    public Championship(){}
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getWonPoint() {
        return wonPoint;
    }

    public void setWonPoint(int wonPoint) {
        this.wonPoint = wonPoint;
    }

    public int getLostPoint() {
        return lostPoint;
    }

    public void setLostPoint(int lostPoint) {
        this.lostPoint = lostPoint;
    }

    public int getDrawPoint() {
        return drawPoint;
    }

    public void setDrawPoint(int drawPoint) {
        this.drawPoint = drawPoint;
    }

    @Nullable
    public List<Day> getDays() {
        return days;
    }

    public void setDays(@Nullable List<Day> days) {
        this.days = days;
    }

    public List<Team> getTeam() {
        return teams;
    }

    public void setTeam(List<Team> team) {
        this.teams = team;
    }

    protected Date endDate;
    protected int wonPoint;
    protected int lostPoint;
    protected int drawPoint;

    @OneToMany(mappedBy = "championships",fetch = FetchType.LAZY)
    @JsonIgnore
    @Nullable
    protected List<Day> days;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "champ_team",
            joinColumns = { @JoinColumn(name = "champ_id") },
            inverseJoinColumns = { @JoinColumn(name = "team_id") })
    protected List<Team> teams;
}
