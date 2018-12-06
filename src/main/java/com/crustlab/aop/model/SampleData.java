package com.crustlab.aop.model;

public class SampleData {

    private final Long timestamp;
    private final String message;

    public SampleData(Long timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("SampleData(timestamp = '%d', message = '%s')", timestamp, message);
    }

}
