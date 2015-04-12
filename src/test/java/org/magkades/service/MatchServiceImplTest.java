package org.magkades.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.magkades.dao.MatchDao;
import org.magkades.dao.MatchEntity;
import org.magkades.dao.MatchStatus;
import org.magkades.model.NewMatchParameters;
import org.magkades.model.NewMatchResponse;
import org.magkades.model.NewPointParameters;
import org.magkades.model.NewPointResponse;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceImplTest {

    private static final Long MATCH_ID = Long.valueOf(1);
    private static final String PLAYER_1 = "nadal";
    private static final String PLAYER_2 = "federer";
    private static final String PLAYER_3 = "murray";

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
        when(matchDao.createMatch(PLAYER_1, PLAYER_2)).thenReturn(MATCH_ID);
        NewMatchResponse newMatchResponse = matchServiceImpl.createMatch(newMatchParameters);

        // then
        verify(matchDao, times(1)).createMatch(PLAYER_1, PLAYER_2);
        Assert.assertTrue(newMatchResponse.isSuccess());
        Assert.assertTrue(newMatchResponse.getMatchId().equals(MATCH_ID.toString()));
    }

    @Test
    public void shouldNotCreateMatchWithMissingPlayer() throws AppException {

        exception.expect(AppException.class);
        exception.expectMessage("Failure to create match due to insufficient data.");

        // when
        matchServiceImpl.createMatch(new NewMatchParameters());

        // then exception

    }

    @Test
    public void shouldUpdateMatchSuccessfully() throws AppException {
        // given
        NewPointParameters newPointParameters = new NewPointParameters();
        newPointParameters.setMatchId(MATCH_ID);
        newPointParameters.setPlayer(PLAYER_1);
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setPlayer1(PLAYER_1);
        matchEntity.setPlayer2(PLAYER_2);
        matchEntity.setStatus(MatchStatus.ONGOING.getValue());

        // when
        when(matchDao.getMatchById(MATCH_ID)).thenReturn(matchEntity);
        doNothing().when(matchDao).updateMatch(MATCH_ID, PLAYER_1);
        NewPointResponse newPointResponse = matchServiceImpl.updateMatch(newPointParameters);

        // then
        verify(matchDao, times(1)).updateMatch(MATCH_ID, PLAYER_1);
        Assert.assertTrue(newPointResponse.isSuccess());
    }

    @Test
    public void shouldNotUpdateMatchWithNonExistingMatchId() throws AppException {

        exception.expect(AppException.class);
        exception.expectMessage("Failure to update match, match id does not exist.");

        // given
        NewPointParameters newPointParameters = new NewPointParameters();
        newPointParameters.setMatchId(MATCH_ID);
        newPointParameters.setPlayer(PLAYER_1);

        // when
        when(matchDao.getMatchById(MATCH_ID)).thenReturn(null);
        matchServiceImpl.updateMatch(newPointParameters);

        // then exception
    }

    @Test
    public void shouldNotUpdateMatchWithNonExistingPlayer() throws AppException {

        exception.expect(AppException.class);
        exception.expectMessage("Failure to update match, player does not exist.");

        // given
        NewPointParameters newPointParameters = new NewPointParameters();
        newPointParameters.setMatchId(MATCH_ID);
        newPointParameters.setPlayer(PLAYER_3);
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setPlayer1(PLAYER_1);
        matchEntity.setPlayer2(PLAYER_2);
        matchEntity.setStatus(MatchStatus.ONGOING.getValue());

        // when
        when(matchDao.getMatchById(MATCH_ID)).thenReturn(matchEntity);
        matchServiceImpl.updateMatch(newPointParameters);

        // then exception
    }

    @Test
    public void shouldNotUpdateMatchWithStatusComplete() throws AppException {

        exception.expect(AppException.class);
        exception.expectMessage("Failure to update match, match is complete.");

        // given
        NewPointParameters newPointParameters = new NewPointParameters();
        newPointParameters.setMatchId(MATCH_ID);
        newPointParameters.setPlayer(PLAYER_1);
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setPlayer1(PLAYER_1);
        matchEntity.setPlayer2(PLAYER_2);
        matchEntity.setStatus(MatchStatus.COMPLETE.getValue());

        // when
        when(matchDao.getMatchById(MATCH_ID)).thenReturn(matchEntity);
        matchServiceImpl.updateMatch(newPointParameters);

        // then exception
    }
}
