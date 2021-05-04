package com.example.bookclub;

import android.app.Application;

import com.example.bookclub.parse_models.User_Extra;
import com.example.bookclub.parse_models.User_Friend;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        ParseObject.registerSubclass(User_Extra.class);
        ParseObject.registerSubclass(User_Friend.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("588yQ1MIURTAJq21oXOixyg6FAUDHNlpuyYlolJY")
                .clientKey("9u9cAbVSyJ3tiAUBp96T9kSKgOn0VWSSKnOihxgM")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
