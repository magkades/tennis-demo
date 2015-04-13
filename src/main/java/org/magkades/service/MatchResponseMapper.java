package org.magkades.service;

import org.magkades.dao.MatchEntity;
import org.magkades.model.MatchResponse;
import org.magkades.model.Score;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 */
public class MatchResponseMapper implements Mapper<MatchEntity, MatchResponse>{

    @Override
    public MatchResponse map(MatchEntity from) {
        String player1 = from.getPlayer1();
        String player2 = from.getPlayer2();
        Score score1 = Score.newBuilder()
                .setPoints(from.getPointsCount1())
                .setGames(from.getGamesCount1())
                .setSets(from.getSetsCount1()).build();
        Score score2 = Score.newBuilder()
                .setPoints(from.getPointsCount2())
                .setGames(from.getGamesCount2())
                .setSets(from.getSetsCount2()).build();
        Map<String, Score> scores = new HashMap<>();
        scores.put(player1, score1);
        scores.put(player2, score2);
        long duration = getDateDiff(from.getLatestTs(), from.getStartTs(), TimeUnit.MINUTES);
        return MatchResponse.newBuilder()
                .setPlayer1(player1).setPlayer2(player2)
                .setMatchStatus(from.getStatus())
                .setScores(scores)
                .setSuccess(true)
                .setDuration(duration).build();
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
