package com.seizetheday.lists.events;

public class APIErrorEvent {
    private String mErrorMessage;

    public APIErrorEvent(String errorMessage) {
        mErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }
}
