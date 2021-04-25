package com.example.bookclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/*import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;*/


public class RegistrationActivity extends AppCompatActivity
{
    EditText email, password;
    Button register;
    TextView haveAccount;

    //Declare FirebaseAuth
    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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

        //mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //input email and password
                String emailIn = email.getText().toString().trim();
                String passwordIn = password.getText().toString().trim();

                //validate
                /*if (!Patterns.EMAIL_ADDRESS.matcher(emailIn).matches())
                {
                    //set error
                    email.setError("Invalid Email");
                    email.setFocusable(true);
                }
                else if(passwordIn.length()< 7)
                {
                    password.setError("Too Short, password length must be at least 7 characters");
                    password.setFocusable(true);
                }
                else
                    {
                    //Register user
                    registerUser(emailIn,passwordIn);
                }*/

                registerUser(emailIn,passwordIn);
            }
        });

        //handle login textview click listener
        haveAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }

    private void registerUser(String u, String p)
    {
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(u);
        user.setPassword(p);
        user.setEmail(u + "@gmail.com");
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback()
        {
            public void done(ParseException e)
            {
                if(e != null)
                {
                    Toast.makeText(RegistrationActivity.this, "Unable to sign up due to: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    Toast.makeText(RegistrationActivity.this, "Sign up successful.", Toast.LENGTH_SHORT).show();
                    loginUser(u, p);
                }
            }
        });
    }

    private void loginUser(String username, String password)
    {
        ParseUser.logInInBackground(username, password, new LogInCallback()
        {
            @Override
            public void done(ParseUser user, ParseException e)
            {
                if(e != null)
                {
                    Toast.makeText(RegistrationActivity.this, "Login failed due to: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    Toast.makeText(RegistrationActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();
                    goTo_dashboardActivity();
                }
            }
        });
    }

    private void goTo_dashboardActivity()
    {
        Intent i = new Intent(RegistrationActivity.this, DashboardActivity.class);
        startActivity(i);
        finish();
    }

    /*private void registerUser(String emailIn, String passwordIn) {

        mAuth.createUserWithEmailAndPassword(emailIn, passwordIn)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Sign in successful
                            FirebaseUser user = mAuth.getCurrentUser();



                            Toast.makeText(RegistrationActivity.this, "Registered \n" + user.getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this, DashboardActivity.class));
                            finish();
                        }
                        else{
                            //If sign in fails
                            Toast.makeText(RegistrationActivity.this, "Authentication failed ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }*/

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}