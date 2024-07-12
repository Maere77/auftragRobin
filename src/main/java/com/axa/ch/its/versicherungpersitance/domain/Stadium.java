package com.axa.ch.its.versicherungpersitance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String stadiumName;

    @OneToMany(mappedBy = "stadium")
    private Set<Matchh> matchhs = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public Set<Matchh> getMatchhs() {
        return matchhs;
    }

    public void setMatchhs(Set<Matchh> matchhs) {
        this.matchhs = matchhs;
    }
}