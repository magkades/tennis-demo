package org.magkades.model;

/**
 * Contains the response to the request for creating new match.
 */
public class NewMatchResponse {
    private boolean success;
    private String matchId;

    public NewMatchResponse(boolean success, String matchId) {
        this.success = success;
        this.matchId = matchId;
    }

    public NewMatchResponse(){}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
