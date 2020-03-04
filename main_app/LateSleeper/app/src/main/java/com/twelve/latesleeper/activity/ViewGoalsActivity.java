package com.twelve.latesleeper.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;

public class ViewGoalsActivity  extends AppCompatActivity
{
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_viewgoals);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Database.retrieveJournalCollection(currentUser.getUid());
        //updateUI(currentUser);
    }

}