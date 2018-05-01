package com.example.snehal.moviepoject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by snehal on 4/30/2018.
 */

public class detailFragment extends Fragment {
    String title, director, poster;
    TextView titleText, dirText, postText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_detail, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("title");
            director = bundle.getString("director");
            poster = bundle.getString("poster");
        }

        titleText = view.findViewById(R.id.title);
        dirText = view.findViewById(R.id.director);
        postText = view.findViewById(R.id.poster);

        titleText.setText(title);
        dirText.setText(director);
        postText.setText(poster);
        return view;

    }
}
