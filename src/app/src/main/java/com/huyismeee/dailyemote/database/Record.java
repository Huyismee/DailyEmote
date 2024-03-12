package com.huyismeee.dailyemote.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.InputStream;
import java.sql.Blob;

@Entity(tableName = "Record")
public class Record {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String emotion;
    private String weather;
    private String note;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public Record() {
    }

    public Record(int id, String emotion, String weather, String note, byte[] image) {
        this.id = id;
        this.emotion = emotion;
        this.weather = weather;
        this.note = note;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
