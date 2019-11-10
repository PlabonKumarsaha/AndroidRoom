package com.example.androidroom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//any change in database effects the version no
@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    //we make this static bcz we can't isnatnce it multiple time and use the same instance all the time.
    //this is made singleton
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    //we synchronised it bcz so that only one thread can call it at a time .
    public static synchronized NoteDatabase getInstance(Context context)
    {

        //fallbackToDestructiveMigration will destroy the database table if it has to instanciated over and if it had previosu
        //isnatnces
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        
        return instance;
    }

}
