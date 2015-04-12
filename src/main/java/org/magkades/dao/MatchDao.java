package org.magkades.dao;

import java.util.List;

/**
 * Database operations over matches table.
 */
public interface MatchDao {

    public Long createMatch(String player1, String player2);

    public MatchEntity getMatchById(Long id);

    public void updateMatch(Long id, String player);

}
