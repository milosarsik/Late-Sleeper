package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twelve.latesleeper.R;

public class LogInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;
    private String email, pass;
    ProgressBar loadingBar;
    ConstraintLayout dimLayout;
    private static final String TAG = "LOGIN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);

        emailEditText = (EditText) findViewById(R.id.txtEmail);
        passwordEditText = (EditText) findViewById(R.id.txtPass);
        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        dimLayout = (ConstraintLayout) findViewById(R.id.dimLayout);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    // On click function to log the user into their account
    public void logIn(View view) {
        email = emailEditText.getText().toString();
        pass = passwordEditText.getText().toString();
        if (!email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$") || pass.matches("")) {
            Toast.makeText(LogInActivity.this, "Some fields are not entered correctly.",
                    Toast.LENGTH_SHORT).show();
        } else {
            dimLayout.bringToFront();
            loadingBar.bringToFront();
            loadingBar.setVisibility(View.VISIBLE);
            dimLayout.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                loadingBar.setVisibility(View.INVISIBLE);
                                dimLayout.setVisibility(View.INVISIBLE);
                                Toast.makeText(LogInActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        }
    }

    // On click function to allow a new user to create an account
    public void createAccount(View view) {
        Intent intent = new Intent(LogInActivity.this, CreateAccountActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser == null) {

        } else {
            Intent intent = new Intent(LogInActivity.this, UserHomeActivity.class);
            loadingBar.setVisibility(View.INVISIBLE);
            dimLayout.setVisibility(View.INVISIBLE);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}
//
//    // On click test function to go to journal activity
//    public void toJournal(View view){
//        Intent intent = new Intent(LogInActivity.this, JournalActivity.class);
//        startActivity(intent);
//    }

