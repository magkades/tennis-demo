package org.magkades.service;

import org.magkades.dao.MatchDao;
import org.magkades.dao.MatchEntity;
import org.magkades.dao.MatchStatus;
import org.magkades.model.NewMatchParameters;
import org.magkades.model.NewMatchResponse;
import org.magkades.model.NewPointParameters;
import org.magkades.model.NewPointResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class MatchServiceImpl implements MatchService {

    @Autowired
    MatchDao matchDao;

    @Transactional("transactionManager")
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
                    "Please verify that player 1 is properly generated/set");
        }
        if(newMatchParameters.getPlayer2() == null){
            throw new AppException(400, "Failure to create match due to insufficient data.",
                    "Please verify that player 2 is properly generated/set");
        }
    }

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
                    "Please verify that match id is properly generated/set");
        }
        if(player == null){
            throw new AppException(400, "Failure to update match due to insufficient data.",
                    "Please verify that player is properly generated/set");
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

    public void setMatchDao(MatchDao matchDao) {
        this.matchDao = matchDao;
    }
}
