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
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MatchDaoMysqlTest {
    private static final Long CREATED_MATCH_RESOURCE_ID = Long.valueOf(1);
    private static final String PLAYER_1 = "nadal";
    private static final String PLAYER_2 = "federer";

    MatchDao matchDao;

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

    private void deleteMatch(MatchEntity matchEntity) {
        Storage<MatchEntity> storage = new Storage<MatchEntity>(matchEntity);
        storage.beginTransaction();
        storage.delete(matchEntity);
        storage.commit();
    }

}
