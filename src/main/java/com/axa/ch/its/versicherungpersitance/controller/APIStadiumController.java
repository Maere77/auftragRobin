package com.axa.ch.its.versicherungpersitance.controller;

import com.axa.ch.its.versicherungpersitance.domain.Stadium;
import com.axa.ch.its.versicherungpersitance.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stadiums")
public class APIStadiumController {

    @Autowired
    private StadiumRepository stadiumRepository;

    // Get all stadiums
    @GetMapping
    public List<Stadium> getStadiums() {
        return stadiumRepository.findAll();
    }

    // Get stadium by ID
    @GetMapping("/{id}")
    public ResponseEntity<Stadium> getStadiumById(@PathVariable String id) {
        Optional<Stadium> stadium = stadiumRepository.findById(id);
        return stadium.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new stadium
    @PostMapping
    public ResponseEntity<Stadium> createStadium(@RequestBody Stadium stadium) {
        Stadium savedStadium = stadiumRepository.save(stadium);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStadium);
    }

    // Update existing stadium
    @PutMapping("/{id}")
    public ResponseEntity<Stadium> updateStadium(@PathVariable String id, @RequestBody Stadium updatedStadium) {
        Optional<Stadium> existingStadium = stadiumRepository.findById(id);
        if (existingStadium.isPresent()) {
            Stadium stadium = existingStadium.get();
            stadium.setPlace(updatedStadium.getPlace());
            stadium.setStadiumName(updatedStadium.getStadiumName());
            stadiumRepository.save(stadium);
            return ResponseEntity.ok(stadium);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete stadium by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStadium(@PathVariable String id) {
        if (stadiumRepository.existsById(id)) {
            stadiumRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
