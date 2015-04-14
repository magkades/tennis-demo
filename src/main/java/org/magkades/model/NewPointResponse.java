package org.magkades.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Response to be returned when match is updated with new point.
 */
@XmlRootElement
public class NewPointResponse {
    private boolean success;

    public NewPointResponse(){}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
