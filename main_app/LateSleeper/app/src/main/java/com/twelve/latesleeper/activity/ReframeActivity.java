package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.twelve.latesleeper.R;

public class ReframeActivity extends AppCompatActivity {

    Context context = getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reframe);
    }

    //OnClick for the save button will trigger the next screen in the workflow
    public void saveReframe(View view){
        Intent intent = new Intent(ReframeActivity.this, RefocusActivity.class);
        startActivity(intent);

        Toast toast = Toast.makeText(context,"Reframe Save Button Clicked",Toast.LENGTH_LONG);
        toast.show();
        //TODO
        //We need to save all the information the user enters on this screen into the database on save
    }

    //OnClick for the cancel button will not save any information
    public void backReframe(View view){
        Intent intent = new Intent(ReframeActivity.this, RelabelActivity.class);
        startActivity(intent);

        Toast toast = Toast.makeText(context,"Reframe Back Button Clicked",Toast.LENGTH_LONG);
        toast.show();
    }
}