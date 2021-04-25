package com.example.bookclub;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("588yQ1MIURTAJq21oXOixyg6FAUDHNlpuyYlolJY")
                .clientKey("9u9cAbVSyJ3tiAUBp96T9kSKgOn0VWSSKnOihxgM")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
