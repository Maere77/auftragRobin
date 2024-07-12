package com.axa.ch.its.versicherungpersitance.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String coach;

    @JsonIgnoreProperties("teams")
    @ManyToOne
    private Group groupp;

    @JsonIgnoreProperties("team")
    @OneToMany(mappedBy = "team")
    private Set<Player> players = new HashSet<>();

    @ManyToMany(mappedBy = "teams")
    private Set<Matchh> matchhs = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Group getGroupp() {
        return groupp;
    }

    public void setGroupp(Group groupp) {
        this.groupp = groupp;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Set<Matchh> getMatchhs() {
        return matchhs;
    }

    public void setMatchhs(Set<Matchh> matchhs) {
        this.matchhs = matchhs;
    }
}
