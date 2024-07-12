package com.axa.ch.its.versicherungpersitance.repository;

import com.axa.ch.its.versicherungpersitance.domain.Player;
import com.axa.ch.its.versicherungpersitance.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}
