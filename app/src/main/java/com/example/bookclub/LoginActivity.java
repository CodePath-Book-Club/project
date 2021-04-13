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


public class LoginActivity extends AppCompatActivity {

    EditText emailEt, passwordEt;
    TextView notHaveAccntTv;
    Button loginbtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");

        // for the back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        emailEt = findViewById(R.id. email);
        passwordEt = findViewById(R.id.password);
        loginbtn = findViewById(R.id. login_Button);
        notHaveAccntTv = findViewById(R.id.nothave_accountTv);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input email and password
                String emailIn = emailEt.getText().toString().trim();
                String passwordIn = passwordEt.getText().toString().trim();

                //validate
                if (!Patterns.EMAIL_ADDRESS.matcher(emailIn).matches()){
                    //set error
                    emailEt.setError("Invalid Email");
                    emailEt.setFocusable(true);
                }
                else{
                    //Register user
                    loginUser(emailIn,passwordIn);
                }
            }
        });
        //not have account textview
        notHaveAccntTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationScreen.class));
            }
        });
    }

    private void loginUser(String emailIn, String passwordIn) {
        mAuth.signInWithEmailAndPassword(emailIn, passwordIn)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Sign in successful
                            FirebaseUser user = mAuth.getCurrentUser();


                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                        }
                        else{
                            //If sign in fails
                            Toast.makeText(LoginActivity.this, "Authentication failed ", Toast.LENGTH_SHORT).show();
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