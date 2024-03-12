package com.huyismeee.dailyemote.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TaskTimer")
public class TaskTimer {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nametag;
    private int loopTime;

    public TaskTimer(){

    }

    public TaskTimer(int id, String nametag, int loopTime) {
        this.id = id;
        this.nametag = nametag;
        this.loopTime = loopTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNametag() {
        return nametag;
    }

    public void setNametag(String nametag) {
        this.nametag = nametag;
    }

    public int getLoopTime() {
        return loopTime;
    }

    public void setLoopTime(int loopTime) {
        this.loopTime = loopTime;
    }
}
