package com.example.bookclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

/*import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;*/

public class DashboardActivity extends AppCompatActivity
{
    private ImageView profilePic;
    private TextView profileName;
    private TextView clubs_num;
    private TextInputEditText description_text;
    private Button action_logout;
    private BottomNavigationView navigation;
    //FirebaseAuth firebaseAuth;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if(ParseUser.getCurrentUser() == null)
        {
            Toast.makeText(DashboardActivity.this, "No user currently logged in", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(DashboardActivity.this, "Current user is: " + ParseUser.getCurrentUser().getUsername(), Toast.LENGTH_SHORT).show();
        }

        actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");
        profilePic = findViewById(R.id.profilePic);
        profileName = findViewById(R.id.profileName);
        clubs_num = findViewById(R.id.clubs_num);
        description_text = findViewById(R.id.description_text);
        action_logout = findViewById(R.id.action_logout);
        navigation = findViewById(R.id.navigation);
        //actionBar= getSupportActionBar();
        //actionBar.setTitle("Profile");

        action_logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ParseUser.logOut();
                goTo_mainActivity();
            }
        });

        //firebaseAuth = FirebaseAuth.getInstance();

        //bottom navigation
        //BottomNavigationView navigationView = findViewById(R.id.navigation);
    //    navigationView.setOnNavigationItemSelectedListener(selectedListener);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_group:
                        //fragment = fragment1;
                        break;
                    case R.id.action_profile:
                        //fragment = fragment2;
                        break;
                    case R.id.action_friends:
                    default:
                        //fragment = fragment3;
                        break;
                }
                return true;
            }
        });
    }

   // private BottomNavigationView. OnNavigationItemSelectedListener selectedListener =
     //       new BottomNavigationView.OnNavigationItemSelectedListener() {
    //            @Override
   //             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //handle item clicks
                  //  switch(item.getItemId()){
                        //case R.id.:
                         //   return true;
                 //   }
                   // return false;
             //   }
     //       };


    private void checkUserStatus(){
        //FirebaseUser user = firebaseAuth.getCurrentUser();
        /*if(user !=null){
            //user is signed in
           // profileTv.setText(user.getEmail());
        }
        else {
            //user not signed in, go to main activity
            startActivity(new Intent(DashboardActivity.this, MainActivity.class));
            finish();
        }*/
    }

    @Override
    protected void onStart() {
        //check on start of app
        checkUserStatus();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //get item id
        int id = item.getItemId();
        if(id == R.id.action_logout){
            //firebaseAuth.signOut();
            ParseUser.logOut();
            //checkUserStatus();
            goTo_mainActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    public void goTo_mainActivity()
    {
        Intent i = new Intent(DashboardActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}