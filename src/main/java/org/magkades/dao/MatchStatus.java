package org.magkades.dao;

/**
 * Enum representing valid status of a match.
 *
 */
public enum MatchStatus {
    ONGOING("ONGOING"),
    COMPLETE("COMPLETE");

    private String value;

    MatchStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}