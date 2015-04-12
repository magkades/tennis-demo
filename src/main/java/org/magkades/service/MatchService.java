package org.magkades.service;

import org.magkades.model.NewMatchParameters;
import org.magkades.model.NewMatchResponse;

/**
 * These are the functions to be used by the restful API.
 */
public interface MatchService {

    public NewMatchResponse createMatch(NewMatchParameters newMatchParameters) throws AppException;

}
