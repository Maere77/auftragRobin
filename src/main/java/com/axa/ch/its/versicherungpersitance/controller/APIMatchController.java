package com.axa.ch.its.versicherungpersitance.controller;

import com.axa.ch.its.versicherungpersitance.domain.Matchh;
import com.axa.ch.its.versicherungpersitance.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
public class APIMatchController {

    @Autowired
    private MatchRepository matchRepository;

    // Get all matches
    @GetMapping
    public List<Matchh> getMatches() {
        return matchRepository.findAll();
    }

    // Get match by ID
    @GetMapping("/{id}")
    public ResponseEntity<Matchh> getMatchById(@PathVariable String id) {
        Optional<Matchh> match = matchRepository.findById(id);
        return match.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new match
    @PostMapping
    public ResponseEntity<Matchh> createMatch(@RequestBody Matchh match) {
        Matchh savedMatch = matchRepository.save(match);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMatch);
    }

    // Update existing match
    @PutMapping("/{id}")
    public ResponseEntity<Matchh> updateMatch(@PathVariable String id, @RequestBody Matchh updatedMatch) {
        Optional<Matchh> existingMatch = matchRepository.findById(id);
        if (existingMatch.isPresent()) {
            Matchh match = existingMatch.get();
            match.setWinner(updatedMatch.getWinner());
            match.setStadium(updatedMatch.getStadium());
            match.getTeams().clear();
            match.getTeams().addAll(updatedMatch.getTeams());
            matchRepository.save(match);
            return ResponseEntity.ok(match);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete match by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable String id) {
        if (matchRepository.existsById(id)) {
            matchRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
