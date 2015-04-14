package org.magkades.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Response to be returned when match is created.
 */
@XmlRootElement
public class NewMatchResponse {
    private boolean success;
    private String matchId;

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
