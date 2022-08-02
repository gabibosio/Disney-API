package com.example.Alkemy.Disney.Services;

import com.example.Alkemy.Disney.dtos.MovieDTO;
import com.example.Alkemy.Disney.models.Movie;

import java.util.List;

public interface MovieService {

    void saveMovie(Movie movie);

    Movie movieById(long id);

    void deleteMovie(Movie movie);

    List<MovieDTO> getMovies();

    List<MovieDTO> getMovieByName(String name);

    List<MovieDTO> getMovieByGender(long genre);
}
