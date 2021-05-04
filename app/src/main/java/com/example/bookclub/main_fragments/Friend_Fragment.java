package com.example.bookclub.main_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookclub.Base_Fragment;
import com.example.bookclub.MainActivity;
import com.example.bookclub.R;
import com.example.bookclub.parse_models.User_Extra;
import com.example.bookclub.parse_models.User_Friend;
import com.example.bookclub.rv_adapters.FriendAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class Friend_Fragment extends Base_Fragment
{
    private TextInputEditText et_add_friend;
    private Button button_add_friend;
    private RecyclerView rv_friends;
    protected FriendAdapter rv_friends_adapter;
    private List<User_Friend> friends;

    @Override
    protected View inflate_view(LayoutInflater i, ViewGroup c)
    {
        return i.inflate(R.layout.fragment_friend, c, false);
    }

    @Override
    protected void init_components(View v)
    {
        et_add_friend = v.findViewById(R.id.et_add_friend);
        button_add_friend = v.findViewById(R.id.button_add_friend);
        rv_friends = v.findViewById(R.id.rv_friends);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        init_components(view);
        friends = new ArrayList<User_Friend>();
        rv_friends_adapter = new FriendAdapter(getContext(), friends);

        rv_friends.setAdapter(rv_friends_adapter);
        rv_friends.setLayoutManager(new LinearLayoutManager(getContext()));

        getFriends();
    }

    public void goTo_mainActivity()
    {
        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    protected void clear_friends()
    {
        friends.clear();
        rv_friends_adapter.notifyDataSetChanged();
    }

    protected void populate_friends(List<User_Friend> f)
    {
        friends.addAll(f);
        rv_friends_adapter.notifyDataSetChanged();
    }

    protected void set_query_filter(ParseQuery<User_Friend> q)
    {
        q.addDescendingOrder(User_Friend.KEY_CREATED_AT);
        q.whereEqualTo(User_Friend.KEY_USER, ParseUser.getCurrentUser());
    }

    protected void getFriends()
    {
        ParseQuery<User_Friend> query = ParseQuery.getQuery(User_Friend.class);
        query.include(User_Friend.KEY_USER);
        query.include(User_Friend.KEY_FRIEND);
        set_query_filter(query);

        query.findInBackground(new FindCallback<User_Friend>()
        {
            @Override
            public void done(List<User_Friend> objects, ParseException e)
            {
                if(e != null)
                {
                    Toast.makeText(getContext(), "Unable to load friends due to : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Friend loading successful.", Toast.LENGTH_SHORT).show();

                    clear_friends();
                    populate_friends(objects);
                }
            }
        });
    }
}
