package org.magkades.model;

/**
 * Contains the response to the request for creating new match.
 */
public class NewPointResponse {
    private boolean success;

    public NewPointResponse(boolean success, String matchId) {
        this.success = success;
    }

    public NewPointResponse(){}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
