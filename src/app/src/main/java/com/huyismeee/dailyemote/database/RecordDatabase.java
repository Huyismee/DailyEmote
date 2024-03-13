package com.huyismeee.dailyemote.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Record.class, TaskTimer.class}, version = 2)
public abstract class RecordDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "dailyEmotion.db";
    private static RecordDatabase instance;
    public static synchronized RecordDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), RecordDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract RecordDao recordDao();

    public abstract TaskTimerDao taskTimerDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users "
                    +"ADD COLUMN address TEXT");

        }
    };

}
