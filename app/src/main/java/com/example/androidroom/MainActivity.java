package com.example.androidroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    //https://developer.android.com/jetpack/docs/guide
    //Room library - wrapper around sqlite. WIth usual sqlite we have alot of chances to make errors.
    // Room helps us to solve thuis problem.Have to write much less boiler plate code
    /*In computer programming, boilerplate code or just boilerplate refers to sections of code that
     have to be included in many places with little or no alteration.
     It is often used when referring to languages that are considered verbose,
     i.e. the programmer must write a lot of code to do minimal jobs.*/
    //Room also provides compile time varifications.

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // directly don't create a instance bcz we don't need this new instance every time.so instead we specify
        //what class model we need.
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        //observe is a live data model.
        //observer will only update activity when it is in the fork ground.it will automaticaly destroy the meory
        //so it will avoid memeory leak
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                //update RecyclerView
                //method is called each time data is changed!
                //if we rotate devce this will not hold the activity and change the data
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
