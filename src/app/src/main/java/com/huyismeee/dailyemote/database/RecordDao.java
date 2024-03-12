package com.huyismeee.dailyemote.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecordDao {
    @Insert
    void insertRecord(Record record);

    @Query("Select * from Record")
    List<Record> getListRecord();
}
