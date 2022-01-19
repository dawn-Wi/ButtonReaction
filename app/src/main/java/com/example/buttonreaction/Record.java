package com.example.buttonreaction;

public class Record{
    String record;
    String userId;

    public Record(String record, String userId)
    {
        this.record = record;
        this.userId = userId;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

