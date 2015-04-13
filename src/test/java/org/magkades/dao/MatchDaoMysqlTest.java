package org.magkades.dao;

import org.magkades.hibernate.Storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

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
        Assert.assertEquals(new Integer(2), matchDao.getMatchById(id).getPointsCount1());

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
