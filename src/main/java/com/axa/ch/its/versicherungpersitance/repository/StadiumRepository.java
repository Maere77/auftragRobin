package com.axa.ch.its.versicherungpersitance.repository;

import com.axa.ch.its.versicherungpersitance.domain.Stadium;
import com.axa.ch.its.versicherungpersitance.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StadiumRepository extends JpaRepository<Stadium, String> {
}
