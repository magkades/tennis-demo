package org.magkades.service;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.magkades.dao.MatchDao;
import org.magkades.dao.MatchEntity;
import org.magkades.dao.MatchStatus;
import org.magkades.model.MatchResponse;
import org.magkades.model.MatchParameters;
import org.magkades.model.NewMatchResponse;
import org.magkades.model.NewMatchParameters;
import org.magkades.model.NewPointParameters;
import org.magkades.model.NewPointResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

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

    MatchResponseMapper mapper;

    @Before
    public void setUp() throws Exception {
        matchServiceImpl = new MatchServiceImpl();
        matchServiceImpl.setMatchDao(matchDao);
        mapper = new MatchResponseMapper();
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
        exception.expectMessage("Failure to create match, please verify that player 1 is properly set.");

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
        exception.expectMessage("Failure to update match, please verify that match id is properly set.");

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
        exception.expectMessage("Failure to update match, please verify correct player.");

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
        exception.expectMessage("Failure to update match, please verify that match is ongoing.");

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

    @Test
    public void shouldReadMatchSuccessfully() throws AppException {
        // given
        MatchParameters matchParameters = new MatchParameters();
        matchParameters.setMatchId(MATCH_ID);
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.initialise();
        matchEntity.setPlayer1(PLAYER_1);
        matchEntity.setPlayer2(PLAYER_2);

        // when
        when(matchDao.getMatchById(MATCH_ID)).thenReturn(matchEntity);
        MatchResponse matchResponse = matchServiceImpl.readMatch(matchParameters);

        // then
        verify(matchDao, times(1)).getMatchById(MATCH_ID);
        Assert.assertTrue(matchResponse.isSuccess());
        Assert.assertTrue(matchResponse.getPlayer1().equals(PLAYER_1));
    }

    @Test
    public void shouldNotReadMatchWithBadId() throws AppException {

        exception.expect(AppException.class);
        exception.expectMessage("Failure to fetch match, please verify that match id is properly set.");

        // given
        MatchParameters matchParameters = new MatchParameters();
        matchParameters.setMatchId(MATCH_ID);

        // when
        when(matchDao.getMatchById(MATCH_ID)).thenReturn(null);
        MatchResponse matchResponse = matchServiceImpl.readMatch(matchParameters);

        // then exception
    }

    @Test
    public void shouldCreateValidJsonMatchResponse() throws AppException, IOException {
        // given
        MatchParameters matchParameters = new MatchParameters();
        matchParameters.setMatchId(MATCH_ID);
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.initialise();
        matchEntity.setPlayer1(PLAYER_1);
        matchEntity.setPlayer2(PLAYER_2);

        // when
        when(matchDao.getMatchById(MATCH_ID)).thenReturn(matchEntity);
        MatchResponse matchResponse = matchServiceImpl.readMatch(matchParameters);
        ObjectMapper mapper = new ObjectMapper();
        String responseJson = mapper.writeValueAsString(matchResponse);

        // then
        Assert.assertNotNull(responseJson.contains("player1\":\"nadal\""));
    }
}
