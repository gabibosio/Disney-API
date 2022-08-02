package com.example.Alkemy.Disney.Services.Implement;

import com.example.Alkemy.Disney.Services.MovieService;
import com.example.Alkemy.Disney.dtos.MovieDTO;
import com.example.Alkemy.Disney.models.Movie;
import com.example.Alkemy.Disney.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Override
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public Movie movieById(long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

    @Override
    public List<MovieDTO> getMovies() {
        return movieRepository.findAll().stream().map(movies -> new MovieDTO(movies)).collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> getMovieByName(String name) {
        return movieRepository.findAll().stream().filter(movie -> movie.getTitle().equals(name)).map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> getMovieByGender(long genre) {
        return movieRepository.findAll().stream().filter(movies -> movies.getGender().getId() == genre).map(movies -> new MovieDTO(movies)).collect(Collectors.toList());
    }
}
