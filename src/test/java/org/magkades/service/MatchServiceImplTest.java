package org.magkades.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.magkades.dao.MatchDao;
import org.magkades.dao.MatchEntity;
import org.magkades.model.NewMatchParameters;
import org.magkades.model.NewMatchResponse;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceImplTest {

    private static final Long CREATED_MATCH_RESOURCE_ID = Long.valueOf(1);
    private static final String PLAYER_1 = "nadal";
    private static final String PLAYER_2 = "federer";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    MatchServiceImpl matchServiceImpl;

    @Mock
    MatchDao matchDao;

    @Before
    public void setUp() throws Exception {
        matchServiceImpl = new MatchServiceImpl();
        matchServiceImpl.setMatchDao(matchDao);
    }

    @Test
    public void shouldCreateMatchSuccessfully() throws AppException {
        // given
        NewMatchParameters newMatchParameters = new NewMatchParameters();
        newMatchParameters.setPlayer1(PLAYER_1);
        newMatchParameters.setPlayer2(PLAYER_2);

        // when
        when(matchDao.createMatch(any(MatchEntity.class))).thenReturn(CREATED_MATCH_RESOURCE_ID);
        NewMatchResponse newMatchResponse = matchServiceImpl.createMatch(newMatchParameters);

        // then
        verify(matchDao, times(1)).createMatch(any(MatchEntity.class));
        Assert.assertTrue(newMatchResponse.isSuccess());
        Assert.assertTrue(newMatchResponse.getMatchId().equals(CREATED_MATCH_RESOURCE_ID.toString()));
    }

    @Test
    public void shouldNotCreateMatchWithMissingPlayer() throws AppException {

        exception.expect(AppException.class);
        exception.expectMessage("Failure to create match due to insufficient data.");

        // when
        matchServiceImpl.createMatch(new NewMatchParameters());

        // then exception

    }
}
