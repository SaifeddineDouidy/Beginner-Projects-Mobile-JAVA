package com.example.stars.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.stars.R;
import com.example.stars.beans.Movie;
import com.example.stars.service.MovieService;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> implements Filterable {
    private static final String TAG = "MovieAdapter";

    private List<Movie> movies;
    private List<Movie> moviesFilter;
    private Context context;
    private NewFilter mfilter;

    public MovieAdapter(Activity context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
        this.moviesFilter = new ArrayList<>(movies);
        this.mfilter = new NewFilter(this);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        moviesFilter.add(movie);
        notifyItemInserted(moviesFilter.size() - 1);
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(this.context).inflate(R.layout.movie_item,
                viewGroup, false);
        final MovieViewHolder holder = new MovieViewHolder(v);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popup = LayoutInflater.from(context).inflate(R.layout.movie_edit_item, null,
                        false);
                final ImageView img = popup.findViewById(R.id.img);
                final RatingBar bar = popup.findViewById(R.id.ratingBar);
                final TextView idss = popup.findViewById(R.id.idss);
                Bitmap bitmap = ((BitmapDrawable)((ImageView)v.findViewById(R.id.img)).getDrawable()).getBitmap();
                img.setImageBitmap(bitmap);
                bar.setRating(((RatingBar)v.findViewById(R.id.rating)).getRating());
                idss.setText(((TextView)v.findViewById(R.id.ids)).getText().toString());

                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Movie Options")
                        .setView(popup)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                float s = bar.getRating();
                                int ids = Integer.parseInt(idss.getText().toString());
                                Movie movie = MovieService.getInstance().findById(ids);
                                movie.setRating(s);
                                MovieService.getInstance().update(movie);
                                notifyItemChanged(holder.getAdapterPosition());
                            }
                        })
                        .setNeutralButton("Partager", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Movie movie = moviesFilter.get(holder.getAdapterPosition());
                                String shareText = "Check out this movie!\n" +
                                        "Title: " + movie.getName() + "\n" +
                                        "Rating: " + movie.getRating() + "/5 stars";

                                ShareCompat.IntentBuilder
                                        .from((Activity) context)
                                        .setType("text/plain")
                                        .setChooserTitle("Share Movie")
                                        .setText(shareText)
                                        .startChooser();
                            }
                        })
                        .setNegativeButton("Annuler", null)
                        .create();
                dialog.show();
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Log.d(TAG, "onBindView call ! "+ i);

        Glide.with(context)
                .asBitmap()
                .load(moviesFilter.get(i).getImg())
                .apply(new RequestOptions().override(100, 100))
                .into(movieViewHolder.img);
        movieViewHolder.name.setText(moviesFilter.get(i).getName().toUpperCase());
        movieViewHolder.rating.setRating(moviesFilter.get(i).getRating());
        movieViewHolder.ids.setText(moviesFilter.get(i).getId()+"");
    }
    @Override
    public int getItemCount() {
        return moviesFilter.size();
    }

    @Override
    public NewFilter getFilter() {
        return mfilter;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView ids;
        ImageView img;
        TextView name;
        RatingBar rating;
        RelativeLayout parent;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ids = itemView.findViewById(R.id.ids);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            parent = itemView.findViewById(R.id.parent);
        }
    }
    public class NewFilter extends Filter {
        public RecyclerView.Adapter mAdapter;
        public NewFilter(RecyclerView.Adapter mAdapter) {
            super();
            this.mAdapter = mAdapter;
        }
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            moviesFilter.clear();
            final FilterResults results = new FilterResults();
            if (charSequence.length() == 0) {
                moviesFilter.addAll(movies);
            } else {
                final String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Movie p : movies) {
                    if (p.getName().toLowerCase().startsWith(filterPattern)) {
                        moviesFilter.add(p);
                    }
                }
            }
            results.values = moviesFilter;
            results.count = moviesFilter.size();
            return results;
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            moviesFilter = (List<Movie>) filterResults.values;
            this.mAdapter.notifyDataSetChanged();
        }
    }

}

