package org.magkades.service;

import org.magkades.dao.MatchDao;
import org.magkades.dao.MatchEntity;
import org.magkades.dao.MatchStatus;
import org.magkades.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MatchServiceImpl implements MatchService {

    @Autowired
    MatchDao matchDao;

    Mapper<MatchEntity, MatchResponse> mapper = new MatchResponseMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(MatchServiceImpl.class);

    @Override
    public NewMatchResponse createMatch(NewMatchParameters newMatchParameters) throws AppException {
        validateInputForCreation(newMatchParameters);
        Long id = matchDao.createMatch(newMatchParameters.getPlayer1(), newMatchParameters.getPlayer2());
        NewMatchResponse newMatchResponse = new NewMatchResponse();
        newMatchResponse.setSuccess(true);
        newMatchResponse.setMatchId(id.toString());
        return newMatchResponse;
    }

    private void validateInputForCreation(NewMatchParameters newMatchParameters) throws AppException {
        if(newMatchParameters.getPlayer1() == null){
            String message = "Failure to create match, please verify that player 1 is properly set.";
            LOGGER.debug(message);
            throw new AppException(400, message);
        }
        if(newMatchParameters.getPlayer2() == null){
            String message = "Failure to create match, please verify that player 2 is properly set.";
            LOGGER.debug(message);
            throw new AppException(400, message);
        }
    }

    @Override
    public NewPointResponse updateMatch(NewPointParameters newPointParameters) throws AppException {
        validateInputForUpdate(newPointParameters);
        NewPointResponse newPointResponse = new NewPointResponse();
        matchDao.updateMatch(newPointParameters.getMatchId(), newPointParameters.getPlayer());
        newPointResponse.setSuccess(true);
        return newPointResponse;
    }

    private void validateInputForUpdate(NewPointParameters newPointParameters) throws AppException {
        Long matchId = newPointParameters.getMatchId();
        String player = newPointParameters.getPlayer();
        if(matchId == null){
            String message = "Failure to update match, please verify that match id is properly set.";
            LOGGER.debug(message);
            throw new AppException(400, message);
        }
        if(player == null){
            String message = "Failure to update match, please verify that player is properly set.";
            LOGGER.debug(message);
            throw new AppException(400, message);
        }
        MatchEntity matchEntity = matchDao.getMatchById(newPointParameters.getMatchId());
        if(matchEntity == null){
            String message = "Failure to update match, please verify that match id is properly set.";
            LOGGER.debug(message);
            throw new AppException(400, message);
        }
        if((matchEntity.getPlayer1() != player) && (matchEntity.getPlayer2() != player)){
            String message = "Failure to update match, please verify correct player.";
            LOGGER.debug(message);
            throw new AppException(400, message);
        }
        if(matchEntity.getStatus().equals(MatchStatus.COMPLETE.getValue())){
            String message = "Failure to update match, please verify that match is ongoing.";
            LOGGER.debug(message);
            throw new AppException(400, message);
        }
    }

    @Override
    public MatchResponse readMatch(MatchParameters matchParameters) throws AppException {
        MatchEntity matchEntity = matchDao.getMatchById(matchParameters.getMatchId());
        if(matchEntity == null){
            String message = "Failure to fetch match, please verify that match id is properly set.";
            LOGGER.debug(message);
            throw new AppException(400, message);
        }
        return mapper.map(matchEntity);
    }
    
    public void setMatchDao(MatchDao matchDao) {
        this.matchDao = matchDao;
    }
}
