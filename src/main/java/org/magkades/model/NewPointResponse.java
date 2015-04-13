package org.magkades.model;

/**
 * Response to be returned when match is updated with new point.
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
