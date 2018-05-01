package com.example.snehal.moviepoject;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snehal on 4/28/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CustomHolder> {
    List<DataModel> list = new ArrayList<>();
    boolean flag = false;
    MovieFragment movieFragment = new MovieFragment();
    FragmentActivity context;

    public MovieAdapter(ArrayList<DataModel> list, FragmentActivity context, MovieFragment movieFragment) {
        this.list = list;
        this.context = context;
        this.movieFragment = movieFragment;
    }

    @Override
    public MovieAdapter.CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);
        return new CustomHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.CustomHolder holder, int position) {
        DataModel model = list.get(position);
        holder.titText.setText(model.getName());
        holder.dirText.setText(model.getDirector());
        holder.postText.setText(model.getPoster());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder {
        TextView titText, dirText, postText;

        public CustomHolder(final View itemView) {
            super(itemView);
            titText = itemView.findViewById(R.id.title);
            dirText = itemView.findViewById(R.id.director);
            postText = itemView.findViewById(R.id.poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "hello", Toast.LENGTH_LONG).show();
                    movieFragment.setFragment(true, titText, dirText, postText);

                }
            });
        }
    }
}
