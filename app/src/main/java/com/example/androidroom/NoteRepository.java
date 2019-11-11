package com.example.androidroom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>>allNotes;

    //Application is a subclass of context
    public NoteRepository(Application application)
    {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();

    }

    public void insert(Note note)
    {

        new InsertIntoAsyntask(noteDao).execute(note);
    }

    public void update(Note note)
    {

        new UpdateIntoAsyntask((noteDao)).execute(note);
    }
    public void delte(Note note)
    {

        new DeleteIntoAsyntask(noteDao).execute(note);
    }

    public void deleteAllNotes()
    {

        new DeleteAllNotesIntoAsyntask(noteDao).execute();
    }

    public LiveData<List<Note>>getAllNotes()
    {
        return allNotes;
    }

    //static so there is no rsfernce to repository .having repos may cause in data leackage
    private static class InsertIntoAsyntask extends AsyncTask<Note,Void,Void>
    {
        private NoteDao noteDao;

        private InsertIntoAsyntask(NoteDao noteDao)
        {

            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.insert(notes[0]);
            return null;
        }
    }




    private static class UpdateIntoAsyntask extends AsyncTask<Note,Void,Void>
    {
        private NoteDao noteDao;

        private UpdateIntoAsyntask(NoteDao noteDao)
        {

            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.update(notes[0]);
            return null;
        }
    }


    private static class DeleteIntoAsyntask extends AsyncTask<Note,Void,Void>
    {
        private NoteDao noteDao;

        private DeleteIntoAsyntask(NoteDao noteDao)
        {

            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.delete(notes[0]);
            return null;
        }
    }


    private static class DeleteAllNotesIntoAsyntask extends AsyncTask<Void,Void,Void>
    {
        private NoteDao noteDao;

        private DeleteAllNotesIntoAsyntask(NoteDao noteDao)
        {

            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.deleteALlNotes();
            return null;
        }
    }
}
