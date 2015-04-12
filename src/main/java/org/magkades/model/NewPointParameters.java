package org.magkades.model;

/**
 * Contains the parameters to update match with new point.
 */
public class NewPointParameters {

    private Long matchId;
    private String player;

    public NewPointParameters(Long matchId, String player) {
        this.matchId = matchId;
        this.player = player;
    }

    public NewPointParameters(){}

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
