package com.example.snehal.moviepoject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by snehal on 4/28/2018.
 */


public class MovieFragment extends Fragment {
    RecyclerView recyclerView;
    MovieAdapter adapter;
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> director = new ArrayList<>();
    ArrayList<String> poster = new ArrayList<>();
    ArrayList<DataModel> list = new ArrayList<>();
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment fragment;
    boolean flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getStringArrayList("Tit");
            director = bundle.getStringArrayList("Direct");
            poster = bundle.getStringArrayList("Post");
        }

        DataModel model = new DataModel();

        for (int i = 0; i < title.size(); i++) {
            model.setName(title.get(i));
            for (int j = 0; j < director.size(); j++) {
                model.setDirector(director.get(j));
                for (int k = 0; k < poster.size(); k++) {
                    model.setPoster(poster.get(k));
                }
            }
            list.add(model);
        }
        Log.e("Ctitle", "" + title);
        Log.e("Ctitle", "" + director);
        Log.e("Ctitle", "" + poster);
        recyclerView.setVisibility(View.VISIBLE);
        adapter = new MovieAdapter(list, getActivity(), MovieFragment.this);
        recyclerView.setAdapter(adapter);
        Log.e("check flag", "" + flag);


        return view;
    }

    public void setFragment(boolean b, TextView titText, TextView dirText, TextView postText) {
        Log.e("check", "b" + b);
        if (b == true) {
            fragment = new detailFragment();
            fragmentManager = getActivity().getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("title", titText.getText().toString());
            bundle.putString("director", dirText.getText().toString());
            bundle.putString("poster", postText.getText().toString());
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.detail_frame, fragment);
            fragmentTransaction.commit();
            recyclerView.setVisibility(View.GONE);
        }
    }
}
