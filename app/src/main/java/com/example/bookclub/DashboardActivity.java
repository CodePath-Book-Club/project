package com.example.bookclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {
    private ImageView profilePic;
    private TextView profileName;
    private TextView clubs_num;
    private TextInputEditText description_text;
    private Button action_logout;
    private BottomNavigationView navigation;

    FirebaseAuth firebaseAuth;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        profilePic = findViewById(R.id.profilePic);
        profileName = findViewById(R.id.profileName);
        clubs_num = findViewById(R.id.clubs_num);
        description_text = findViewById(R.id.description_text);
        action_logout = findViewById(R.id.action_logout);
        navigation = findViewById(R.id.navigation);
        //actionBar= getSupportActionBar();
        //actionBar.setTitle("Profile");

        firebaseAuth = FirebaseAuth.getInstance();

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
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user !=null){
            //user is signed in
           // profileTv.setText(user.getEmail());
        }
        else {
            //user not signed in, go to main activity
            startActivity(new Intent(DashboardActivity.this, MainActivity.class));
            finish();
        }
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
            firebaseAuth.signOut();
            checkUserStatus();
        }
        return super.onOptionsItemSelected(item);
    }

}