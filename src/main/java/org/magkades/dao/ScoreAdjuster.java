package org.magkades.dao;

/**
 * Logic for updating scores when new point is won.
 */

public class ScoreAdjuster {
    public MatchEntity adjustMatch(MatchEntity match, String player) {
        match.setPointsCount1(2);
        return match;
    }
}
