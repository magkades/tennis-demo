package org.magkades.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.magkades.hibernate.Storage;
import org.magkades.model.NewMatchParameters;
import org.magkades.model.NewMatchResponse;
import org.magkades.service.AppException;
import org.magkades.service.MatchServiceImpl;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MatchDaoMysqlTest {
    private static final String PLAYER_1 = "nadal";
    private static final String PLAYER_2 = "federer";

    MatchDaoMysql matchDao;

    @Before
    public void setUp() throws Exception {
        matchDao = new MatchDaoMysql();
    }

    @Test
    public void shouldCreateMatchSuccessfully(){

        // given

        // when
        Long matchId = matchDao.createMatch(PLAYER_1, PLAYER_2);

        // then
        Assert.assertNotNull(matchId);

        // cleanup
        deleteMatch(matchDao.getMatchById(matchId));
    }

    @Test
    public void shouldUpdateMatchSuccessfully(){

        // given
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.initialise();
        Storage<MatchEntity> storage = new Storage<MatchEntity>(matchEntity);
        storage.beginTransaction();
        matchEntity.setPlayer1(PLAYER_1);
        matchEntity.setPlayer2(PLAYER_2);
        matchEntity.setPointsCount1(1);
        storage.insert(matchEntity);
        Long id = matchEntity.getId();
        storage.commit();

        // when
        matchDao.updateMatch(id, PLAYER_1);

        // then
        Assert.assertEquals(matchDao.getMatchById(id).getPointsCount1(), new Integer(2));

        // cleanup
        deleteMatch(matchEntity);
    }

    private void deleteMatch(MatchEntity matchEntity) {
        Storage<MatchEntity> storage = new Storage<MatchEntity>(matchEntity);
        storage.beginTransaction();
        storage.delete(matchEntity);
        storage.commit();
    }

}
