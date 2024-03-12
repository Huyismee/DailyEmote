package com.huyismeee.dailyemote.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Record.class, TaskTimer.class}, version = 1)
public abstract class RecordDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "dailyEmotion.db";
    private static RecordDatabase instance;
    public static synchronized RecordDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), RecordDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract RecordDao recordDao();

    public abstract TaskTimerDao taskTimerDao();

}
