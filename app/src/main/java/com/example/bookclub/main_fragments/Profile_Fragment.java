package com.example.bookclub.main_fragments;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookclub.Base_Fragment;
import com.example.bookclub.MainActivity;
import com.example.bookclub.R;
import com.parse.ParseUser;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Profile_Fragment extends Base_Fragment
{
    private ImageView profilePic;
    private TextView profileName;
    private TextView clubs_num;
    private TextInputEditText description_text;
    private Button action_logout;

    @Override
    protected View inflate_view(LayoutInflater i, ViewGroup c)
    {
        return i.inflate(R.layout.fragment_profile, c, false);
    }

    @Override
    protected void init_components(View v)
    {
        profilePic = v.findViewById(R.id.profilePic);
        profileName = v.findViewById(R.id.profileName);
        clubs_num = v.findViewById(R.id.clubs_num);
        description_text = v.findViewById(R.id.description_text);
        action_logout = v.findViewById(R.id.action_logout);

        action_logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ParseUser.logOut();
                goTo_mainActivity();
            }
        });
    }

    public void goTo_mainActivity()
    {
        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);
        getActivity().finish();
    }
}
