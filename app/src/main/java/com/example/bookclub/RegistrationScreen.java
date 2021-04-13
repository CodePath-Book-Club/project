package com.example.bookclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;


public class RegistrationScreen extends AppCompatActivity {

    EditText email, password;
    Button register;
    TextView haveAccount;

    //Declare FirebaseAuth
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Create Account");

        // for the back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        email = findViewById(R.id. email);
        password = findViewById(R.id.password);
        register = findViewById(R.id. registerbtn);
        haveAccount = findViewById(R.id.have_accountTv);

        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input email and password
                String emailIn = email.getText().toString().trim();
                String passwordIn = password.getText().toString().trim();

                //validate
                if (!Patterns.EMAIL_ADDRESS.matcher(emailIn).matches()){
                    //set error
                    email.setError("Invalid Email");
                    email.setFocusable(true);
                }
                else if(passwordIn.length()< 7){
                    password.setError("Too Short, password length must be at least 7 characters");
                    password.setFocusable(true);
                }
                else{
                    //Register user
                    registerUser(emailIn,passwordIn);
                }
            }
        });
        //handle login textview click listener
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationScreen.this, LoginActivity.class));
            }
        });
    }




    private void registerUser(String emailIn, String passwordIn) {

        mAuth.createUserWithEmailAndPassword(emailIn, passwordIn)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Sign in successful
                            FirebaseUser user = mAuth.getCurrentUser();



                            Toast.makeText(RegistrationScreen.this, "Registered \n" + user.getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationScreen.this, DashboardActivity.class));
                            finish();
                        }
                        else{
                            //If sign in fails
                            Toast.makeText(RegistrationScreen.this, "Authentication failed ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}