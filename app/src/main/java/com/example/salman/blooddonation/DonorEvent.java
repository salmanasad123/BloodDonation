package com.example.salman.blooddonation;

/**
 * Created by Salman on 12/23/2017.
 */

public class DonorEvent {
    private String message;

    public DonorEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
