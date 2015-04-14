package org.magkades.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Forms part of the response when match is read.
 */
@XmlRootElement
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Score {
    private final int points;
    private final int games;
    private final int sets;

    public Score(Builder builder) {
        this.points = builder.points;
        this.games = builder.games;
        this.sets = builder.sets;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private int points;
        private int games;
        private int sets;

        public Builder setPoints(int points) {
            this.points = points;
            return this;
        }

        public Builder setGames(int games) {
            this.games = games;
            return this;
        }

        public Builder setSets(int sets) {
            this.sets = sets;
            return this;
        }

        public Score build() {
            return new Score(this);
        }
    }
}