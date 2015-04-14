package org.magkades.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Parameters to read match.
 */
@XmlRootElement
public class MatchParameters {
    private Long matchId;

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
}
