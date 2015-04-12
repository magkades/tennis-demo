package org.magkades.service;

import org.magkades.dao.MatchDao;
import org.magkades.dao.MatchEntity;
import org.magkades.model.NewMatchParameters;
import org.magkades.model.NewMatchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;

public class MatchServiceImpl implements MatchService {

    @Autowired
    MatchDao matchDao;

    @Transactional("transactionManager")
    public NewMatchResponse createMatch(NewMatchParameters newMatchParameters) throws AppException {
        validateInputForCreation(newMatchParameters);
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setPlayer1(newMatchParameters.getPlayer1());
        matchEntity.setPlayer2(newMatchParameters.getPlayer2());
        Long id = matchDao.createMatch(matchEntity);
        NewMatchResponse newMatchResponse = new NewMatchResponse();
        newMatchResponse.setSuccess(true);
        newMatchResponse.setMatchId(id.toString());
        return newMatchResponse;
    }

    private void validateInputForCreation(NewMatchParameters newMatchParameters) throws AppException {
        if(newMatchParameters.getPlayer1() == null){
            throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "Failure to create match due to insufficient data.",
                    "Please verify that player 1 is properly generated/set");
        }
        if(newMatchParameters.getPlayer2() == null){
            throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "Failure to create match due to insufficient data.",
                    "Please verify that player 1 is properly generated/set");
        }
    }

    public void setMatchDao(MatchDao matchDao) {
        this.matchDao = matchDao;
    }
}
