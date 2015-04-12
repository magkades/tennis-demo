package org.magkades.dao;

import org.magkades.hibernate.Storage;

import java.util.Date;

/**
 * Mysql implementation of MatchDAO.
 */
public class MatchDaoMysql implements MatchDao {


    public Long createMatch(MatchEntity matchEntity) {

        Storage<MatchEntity> storage = new Storage<MatchEntity>(matchEntity);
        storage.beginTransaction();
        matchEntity.setStartTs(new Date());
        matchEntity.setLatestTs(new Date());
        matchEntity.setStatus(MatchStatus.ONGOING.getValue());
        matchEntity.setSetsCount1(0);
        matchEntity.setGamesCount1(0);
        matchEntity.setPointsCount1(0);
        matchEntity.setSetsCount2(0);
        matchEntity.setGamesCount2(0);
        matchEntity.setPointsCount2(0);
        storage.insert(matchEntity);
        storage.commit();

        return matchEntity.getId();
    }

}