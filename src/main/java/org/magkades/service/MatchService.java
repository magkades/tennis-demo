package org.magkades.service;

import org.magkades.model.*;

/**
 * Functions to be used by the restful API.
 */
public interface MatchService {

    public NewMatchResponse createMatch(NewMatchParameters newMatchParameters) throws AppException;

    public NewPointResponse updateMatch(NewPointParameters newPointParameters) throws AppException;

    public MatchResponse readMatch(MatchParameters matchParameters) throws AppException;
}
