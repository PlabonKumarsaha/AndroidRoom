package com.example.androidroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //https://developer.android.com/jetpack/docs/guide
    //Room library - wrapper around sqlite. WIth usual sqlite we have alot of chances to make errors.
    // Room helps us to solve thuis problem.Have to write much less boiler plate code
    /*In computer programming, boilerplate code or just boilerplate refers to sections of code that
     have to be included in many places with little or no alteration.
     It is often used when referring to languages that are considered verbose,
     i.e. the programmer must write a lot of code to do minimal jobs.*/
    //Room also provides compile time varifications.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
