package org.magkades.service;

import org.magkades.dao.MatchDao;
import org.magkades.dao.MatchEntity;
import org.magkades.dao.MatchStatus;
import org.magkades.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class MatchServiceImpl implements MatchService {

    @Autowired
    MatchDao matchDao;

    Mapper<MatchEntity, MatchResponse> mapper = new MatchResponseMapper();

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
            throw new AppException(400, "Failure to create match due to insufficient data.",
                    "Please verify that player 1 is properly set");
        }
        if(newMatchParameters.getPlayer2() == null){
            throw new AppException(400, "Failure to create match due to insufficient data.",
                    "Please verify that player 2 is properly set");
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
            throw new AppException(400, "Failure to update match due to insufficient data.",
                    "Please verify that match id is properly set");
        }
        if(player == null){
            throw new AppException(400, "Failure to update match due to insufficient data.",
                    "Please verify that player is properly set");
        }
        MatchEntity matchEntity = matchDao.getMatchById(newPointParameters.getMatchId());
        if(matchEntity == null){
            throw new AppException(400, "Failure to update match, match id does not exist.",
                    "Please verify that match id has been previously generated.");
        }
        if((matchEntity.getPlayer1() != player) && (matchEntity.getPlayer2() != player)){
            throw new AppException(400, "Failure to update match, player does not exist.",
                    "Please verify that correct player is provided.");
        }
        if(matchEntity.getStatus().equals(MatchStatus.COMPLETE.getValue())){
            throw new AppException(400, "Failure to update match, match is complete.",
                    "Please verify that match is ongoing.");
        }
    }

    @Override
    public MatchResponse readMatch(MatchParameters matchParameters) throws AppException {
        MatchEntity matchEntity = matchDao.getMatchById(matchParameters.getMatchId());
        if(matchEntity == null){
            throw new AppException(400, "Failure to fetch match.",
                    "Please verify that match id is properly set");
        }
        return mapper.map(matchEntity);
    }
    
    public void setMatchDao(MatchDao matchDao) {
        this.matchDao = matchDao;
    }
}
