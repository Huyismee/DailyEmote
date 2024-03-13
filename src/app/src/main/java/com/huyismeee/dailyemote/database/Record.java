package com.huyismeee.dailyemote.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "Record")
@TypeConverters(DateConverter.class)
public class Record {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int emotion;
    private int weather;
    private String note;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;
    private Date noteDate;

    public Record() {
    }

    public Record(int id, int emotion, int weather, String note, byte[] image, Date noteDate) {
        this.id = id;
        this.emotion = emotion;
        this.weather = weather;
        this.note = note;
        this.image = image;
        this.noteDate = noteDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmotion() {
        return emotion;
    }

    public void setEmotion(int emotion) {
        this.emotion = emotion;
    }

    public int getWeather() {
        return weather;
    }

    public void setWeather(int weather) {
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

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }
}
