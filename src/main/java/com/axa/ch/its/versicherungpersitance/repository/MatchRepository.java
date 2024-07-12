package com.axa.ch.its.versicherungpersitance.repository;

import com.axa.ch.its.versicherungpersitance.domain.Matchh;
import com.axa.ch.its.versicherungpersitance.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Matchh, String> {
}
