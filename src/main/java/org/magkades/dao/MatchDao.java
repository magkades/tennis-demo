package org.magkades.dao;

import java.util.List;

/**
 * Database operations over matches table.
 */
public interface MatchDao {

    /**
     * Creates a match given the names of the two players.
     *
     * @param player1
     * @param player2
     * @return
     */
    public Long createMatch(String player1, String player2);

    /**
     * Returns a match given its id.
     *
     * @param id
     * @return
     */
    public MatchEntity getMatchById(Long id);

    /**
     * Updates a match with the latest point given the player who scored it.
     *
     * @param id
     * @param player
     * @return
     */
    public void updateMatch(Long id, String player);

}
