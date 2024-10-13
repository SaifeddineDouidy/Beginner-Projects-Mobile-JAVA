package com.example.stars;


import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.stars.adapter.MovieAdapter;
import com.example.stars.beans.Movie;
import com.example.stars.service.MovieService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private MovieService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("ListActivity", "ListActivity started");

        // Initialiser le service et la liste
        service = MovieService.getInstance();

        init();

        movies = service.findAll();

        // Créer et configurer l'adaptateur
        movieAdapter = new MovieAdapter(this, movies);

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (movieAdapter != null){
                    movieAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.app_bar_add) {
            // Inflate the custom layout for adding a movie
            View popup = LayoutInflater.from(this).inflate(R.layout.movie_add_item, null, false);

            // Get references to the EditText fields in the custom layout
            EditText movieName = popup.findViewById(R.id.movieNameValue);
            EditText movieURL = popup.findViewById(R.id.movieURLValue);

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Ajouter un Film")
                    .setView(popup)
                    .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String movieNameValue = movieName.getText().toString();
                            String movieURLValue = movieURL.getText().toString();

                            if (!movieNameValue.isEmpty() && !movieURLValue.isEmpty()) {
                                Movie newMovie = new Movie(movieNameValue, movieURLValue, 4f);
                                movieAdapter.addMovie(newMovie);

                                Log.d("Movie Name:", movieNameValue);
                                Log.d("Movie URL:", movieURLValue);
                            }

                        }
                    })
                    .setNegativeButton("Annuler", null)
                    .create();
            dialog.show();
        }

        if (item.getItemId() == R.id.app_bar_share) {
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Movies")
                    .setText(txt)
                    .startChooser();
        }

        return super.onOptionsItemSelected(item);
    }


    public void init() {
        service.create(new Movie("Interstellar",
                "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg", 4.8f));
        service.create(new Movie("Inception",
                "https://image.tmdb.org/t/p/w500/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg", 4.7f));
        service.create(new Movie("The Dark Knight",
                "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg", 4.9f));
        service.create(new Movie("Dune",
                "https://image.tmdb.org/t/p/w500/d5NXSklXo0qyIYkgV94XAgMIckC.jpg", 4.5f));
        service.create(new Movie("Oppenheimer",
                "https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg", 4.6f));
        service.create(new Movie("Avatar: The Way of Water",
                "https://image.tmdb.org/t/p/w500/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", 4.4f));
        service.create(new Movie("The Matrix Resurrections",
                "https://image.tmdb.org/t/p/w500/eNI7PtK6DEYgZmHWP9gQNuff8pv.jpg", 4.2f));
        service.create(new Movie("Spider-Man: No Way Home",
                "https://image.tmdb.org/t/p/w500/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg", 4.6f));
        service.create(new Movie("Shang-Chi and the Legend of the Ten Rings",
                "https://image.tmdb.org/t/p/w500/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg", 4.3f));
        service.create(new Movie("The Batman",
                "https://image.tmdb.org/t/p/w500/74xTEgt7R36Fpooo50r9T25onhq.jpg", 4.4f));
        service.create(new Movie("Top Gun: Maverick",
                "https://image.tmdb.org/t/p/w500/62HCnUTziyWcpDaBO2i1DX17ljH.jpg", 4.7f));
        service.create(new Movie("Doctor Strange in the Multiverse of Madness",
                "https://image.tmdb.org/t/p/w500/9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg", 4.2f));
        service.create(new Movie("Thor: Love and Thunder",
                "https://image.tmdb.org/t/p/w500/pIkRyD18kl4FhoCNQuWxWu5cBLM.jpg", 4.1f));
        service.create(new Movie("No Time to Die",
                "https://image.tmdb.org/t/p/w500/iUgygt3fscRoKWCV1d0C7FbM9TP.jpg", 4.3f));

    }
}