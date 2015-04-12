package org.magkades.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * Logic for updating scores when new point is won.
 */

public class ScoreAdjuster {

    private static final int POINTS_DIFFERENCE = 2;
    private static final int GAMES_DIFFERENCE = 2;
    private static final int POINTS_THRESHOLD = 4;
    private static final int GAMES_THRESHOLD = 6;
    private static final int SETS_THRESHOLD = 2;


    private Map<String, Integer> pointsMap = new HashMap<>();
    private Map<String, Integer> gamesMap = new HashMap<>();
    private Map<String, Integer> setsMap = new HashMap<>();
    private String scorer;
    private String nonScorer;

    public MatchEntity adjustMatch(MatchEntity match, String player) {
        initialiseMaps(match);
        scorer = player;
        nonScorer = (match.getPlayer1().equals(scorer)) ? match.getPlayer2() : match.getPlayer1();

        incrementPoints();
        if (gamesUpdateNeeded())
            incrementGames();
        if (setsUpdateNeeded())
            incrementSets();
        if (statusUpdateNeeded())
            updateStatus(match);

        updateCounts(match);
        return match;
    }

    private void initialiseMaps(MatchEntity match) {
        pointsMap.put(match.getPlayer1(), match.getPointsCount1());
        pointsMap.put(match.getPlayer2(), match.getPointsCount2());

        gamesMap.put(match.getPlayer1(), match.getGamesCount1());
        gamesMap.put(match.getPlayer2(), match.getGamesCount2());

        setsMap.put(match.getPlayer1(), match.getSetsCount1());
        setsMap.put(match.getPlayer2(), match.getSetsCount2());
    }

    private void incrementPoints() {
        int value = pointsMap.get(scorer) + 1;
        pointsMap.put(scorer, value);
    }

    private void incrementGames() {
        int value = gamesMap.get(scorer) + 1;
        gamesMap.put(scorer, value);
        pointsMap.put(scorer, 0);
        pointsMap.put(nonScorer, 0);
    }

    private void incrementSets() {
        int value = setsMap.get(scorer) + 1;
        setsMap.put(scorer, value);
        gamesMap.put(scorer, 0);
        gamesMap.put(nonScorer, 0);
    }

    private void updateStatus(MatchEntity match) {
        match.setStatus(MatchStatus.COMPLETE.getValue());
    }

    private boolean gamesUpdateNeeded() {
        int scorerPoints = pointsMap.get(scorer);
        int nonScorerPoints = pointsMap.get(nonScorer);
        return  ((scorerPoints >= POINTS_THRESHOLD) && (scorerPoints - nonScorerPoints) >= POINTS_DIFFERENCE);
    }

    private boolean setsUpdateNeeded() {
        int scorerGames = gamesMap.get(scorer);
        int nonScorerGames = gamesMap.get(nonScorer);
        return ((scorerGames >= GAMES_THRESHOLD) && (scorerGames - nonScorerGames) >= GAMES_DIFFERENCE);
    }

    private boolean statusUpdateNeeded() {
        return setsMap.get(scorer)==SETS_THRESHOLD;
    }

    private void updateCounts(MatchEntity match) {
            String player1 = match.getPlayer1();
            String player2 = match.getPlayer2();
            match.setPointsCount1(pointsMap.get(player1));
            match.setGamesCount1(gamesMap.get(player1));
            match.setSetsCount1(setsMap.get(player1));
            match.setPointsCount2(pointsMap.get(player2));
            match.setGamesCount2(gamesMap.get(player2));
            match.setSetsCount2(setsMap.get(player2));
    }
}
