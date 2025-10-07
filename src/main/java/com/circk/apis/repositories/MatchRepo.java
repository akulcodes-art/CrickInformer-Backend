package com.circk.apis.repositories;

import com.circk.apis.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepo extends JpaRepository<Match, Integer > {
    //match fetch karna h
    //provide kar dde ==>team name
    Optional<Match> findByTeamHeading(String teamHeading);
}
