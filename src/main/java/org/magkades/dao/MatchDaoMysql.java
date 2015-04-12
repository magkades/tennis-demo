package org.magkades.dao;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Mysql implementation of MatchDAO.
 */
public class MatchDaoMysql implements MatchDao {

    @PersistenceContext(unitName="demoRestPersistence")
    private EntityManager entityManager;

    public Long createMatch(MatchEntity matchEntity) {

        matchEntity.setStartTs(new Date());
        matchEntity.setLatestTs(new Date());
        matchEntity.setStatus(MatchStatus.ONGOING.getValue());
        matchEntity.setSetsCount1(0);
        matchEntity.setGamesCount1(0);
        matchEntity.setPointsCount1(0);
        matchEntity.setSetsCount2(0);
        matchEntity.setGamesCount2(0);
        matchEntity.setPointsCount2(0);
        entityManager.merge(matchEntity);
        entityManager.flush();

        return matchEntity.getId();
    }

}
