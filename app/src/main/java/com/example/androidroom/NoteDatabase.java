package com.example.androidroom;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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

    public static RoomDatabase.Callback roomcallback = new RoomDatabase.Callback()
    {
        //cntrl+o
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }

}
