//You need to implement the logic for following functionalities:
//
//Add a movie: POST /movies/add-movie
//Pass the Movie object as request body
//Return success message wrapped in a ResponseEntity object
//Controller Name - addMovie
//
//Add a director: POST /movies/add-director
//Pass the Director object as request body
//Return success message wrapped in a ResponseEntity object
//Controller Name - addDirector
//
//Pair an existing movie and director: PUT /movies/add-movie-director-pair
//Pass movie name and director name as request parameters
//Return success message wrapped in a ResponseEntity object
//Controller Name - addMovieDirectorPair
//
//Get Movie by movie name: GET /movies/get-movie-by-name/{name}
//Pass movie name as path parameter
//Return Movie object wrapped in a ResponseEntity object
//Controller Name - getMovieByName
//
//Get Director by director name: GET /movies/get-director-by-name/{name}
//Pass director name as path parameter
//Return Director object wrapped in a ResponseEntity object
//Controller Name - getDirectorByName
//
//Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
//Pass director name as path parameter
//Return List of movies name(List()) wrapped in a ResponseEntity object
//Controller Name - getMoviesByDirectorName
//
//Get List of all movies added: GET /movies/get-all-movies
//No params or body required
//Return List of movies name(List()) wrapped in a ResponseEntity object
//Controller Name - findAllMovies
//
//Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
//Pass director’s name as request parameter
//Return success message wrapped in a ResponseEntity object
//Controller Name - deleteDirectorByName
//
//Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
//No params or body required
//Return success message wrapped in a ResponseEntity object
//Controller Name - deleteAllDirectors
//(Note that there can be some movies on your watchlist that aren’t mapped to any of the director.
// Make sure you do not remove them.)





package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("New movie added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("New director added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie, @RequestParam String director){
        movieService.createMovieDirectorPair(movie, director);
        return new ResponseEntity<>("New movie-director pair added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
        Movie movie = movieService.findMovie(name);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        Director director = movieService.findDirector(name);
        return new ResponseEntity<>(director, HttpStatus.CREATED);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director){
        List<String> movies = movieService.findMoviesFromDirector(director);
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String director){
        movieService.deleteDirector(director);
        return new ResponseEntity<>(director + " removed successfully", HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All directors deleted successfully", HttpStatus.CREATED);
    }
}
