package com.example.tp_listcontacts.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ContactEntity.class}, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {

    private static ContactDatabase instance;
    public abstract ContactDAO contactDao();

    public static synchronized ContactDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactDatabase.class,
                    "contact_database"
            ).build();
        }
        return instance;
    }
}
