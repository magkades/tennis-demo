package org.magkades.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.magkades.dao.MatchDao;
import org.magkades.model.NewMatchParameters;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.*;
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

        when(matchDao.createMatch(any(MatchEntity.class))).thenReturn(CREATED_MATCH_RESOURCE_ID);

        NewMatchParameters newMatchParameters = new NewMatchParameters();
        newMatchParameters.setPlayer1(PLAYER_1);
        newMatchParameters.setPlayer2(PLAYER_2);
        Long createMatch = matchServiceImpl.createMatch(newMatchParameters);

        verify(matchDao, times(1)).createMatch(any(MatchEntity.class));

        Assert.assertTrue(createMatch == CREATED_MATCH_RESOURCE_ID);
    }

    @Test
    public void shouldNotCreateMatchWithMissingPlayer() throws AppException {

        exception.expect(AppException.class);
        exception.expectMessage("Failure to create match due to missing player information.");

        NewMatchParameters newMatchParameters = new NewMatchParameters();
        matchServiceImpl.createMatch(new Match());

    }
}
