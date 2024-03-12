package com.huyismeee.dailyemote.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskTimerDao {
    @Insert
    void insertTimer(TaskTimer taskTimer);

    @Query("Select * from TaskTimer")
    List<TaskTimer> getListTaskTimer();
}
