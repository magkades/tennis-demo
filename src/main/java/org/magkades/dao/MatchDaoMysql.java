package org.magkades.dao;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.magkades.hibernate.Storage;

import java.util.Date;

/**
 * Mysql implementation of MatchDAO.
 */
public class MatchDaoMysql implements MatchDao {

    @Override
    public Long createMatch(String player1, String player2) {
        MatchEntity matchEntity = new MatchEntity();
        initialise(matchEntity);
        matchEntity.setPlayer1(player1);
        matchEntity.setPlayer2(player2);

        // insert to the database
        Storage<MatchEntity> storage = new Storage<MatchEntity>(matchEntity);
        storage.beginTransaction();
        storage.insert(matchEntity);
        storage.commit();

        return matchEntity.getId();
    }

    @Override
    public MatchEntity getMatchById(Long id) {
        MatchEntity matchEntity = new MatchEntity();
        Storage<MatchEntity> storage = new Storage<MatchEntity>(matchEntity);
        storage.beginTransaction();
        matchEntity = storage.getById(id);
        storage.commit();
        return matchEntity;
    }

    @Override
    public void updateMatch(Long id, String player) {
//        ScoreAdjuster scoreAdjuster = new ScoreAdjuster(getMatchById(id), player);
//        MatchEntity matchEntity = scoreAdjuster.adjustMatch();
//        Storage<MatchEntity> storage = new Storage<MatchEntity>(matchEntity);
//        storage.beginTransaction();
//        storage.update(matchEntity);
//        storage.commit();
    }

    private void initialise(MatchEntity matchEntity) {
        matchEntity.setStartTs(new Date());
        matchEntity.setLatestTs(new Date());
        matchEntity.setStatus(MatchStatus.ONGOING.getValue());
        matchEntity.setSetsCount1(0);
        matchEntity.setGamesCount1(0);
        matchEntity.setPointsCount1(0);
        matchEntity.setSetsCount2(0);
        matchEntity.setGamesCount2(0);
        matchEntity.setPointsCount2(0);
    }

}