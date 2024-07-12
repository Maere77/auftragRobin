package com.axa.ch.its.versicherungpersitance.repository;

import com.axa.ch.its.versicherungpersitance.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, String> {
}