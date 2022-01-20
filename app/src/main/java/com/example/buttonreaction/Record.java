package com.example.buttonreaction;

public class Record{
    int record;
    String userId;

    public Record(int record, String userId)
    {
        this.record = record;
        this.userId = userId;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

