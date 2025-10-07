package com.circk.apis.services;

import com.circk.apis.entities.Match;

import java.util.List;
import java.util.Map;

public interface MatchService {

    //allmatches
    List<Match> getAllMatches();

    //getlivematches
    List<Match> getLiveMatches();
    //pointtable
    List<List<String>> getPointTable ();

}
