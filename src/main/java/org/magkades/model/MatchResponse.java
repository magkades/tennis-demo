package org.magkades.model;

import java.util.Map;

/**
 * Response to be returned when match is read.
 */
public class MatchResponse {
    private final String player1;
    private final String player2;
    private final String matchStatus;
    private final Map<String, Score> scores;
    private final boolean success;
    private final long duration;

    private MatchResponse(Builder builder) {
        this.player1 = builder.player1;
        this.player2 = builder.player2;
        this.matchStatus = builder.matchStatus;
        this.scores = builder.scores;
        this.success = builder.success;
        this.duration = builder.duration;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public Map<String, Score> getScores() {
        return scores;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getDuration() {
        return duration;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String player1;
        private String player2;
        private String matchStatus;
        private Map<String, Score> scores;
        private boolean success;
        private long duration;

        public Builder setPlayer1(String player1) {
            this.player1 = player1;
            return this;
        }

        public Builder setPlayer2(String player2) {
            this.player2 = player2;
            return this;
        }

        public Builder setMatchStatus(String matchStatus) {
            this.matchStatus = matchStatus;
            return this;
        }

        public Builder setScores(Map<String, Score> scores) {
            this.scores = scores;
            return this;
        }

        public Builder setSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public Builder setDuration(long duration) {
            this.duration = duration;
            return this;
        }

        public MatchResponse build() {
            return new MatchResponse(this);
        }
    }
}
