package com.example.cinemasystem.controller;

import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.repository.FakeDataStore;
import com.example.cinemasystem.repository.Genre;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {

    private FakeDataStore fakeDataStore;

    @Autowired
    public MovieController(FakeDataStore fakeDataStore)
    {
        this.fakeDataStore = fakeDataStore;
    }


    @GetMapping("hello")
    @ResponseBody
    public String SayHello()
    {
      String msg ="Hello, your source works";
        return msg;

    }

    @GetMapping
    public ResponseEntity<List<Movie>> GetAllMovies()
    {
        return ResponseEntity.ok().body(fakeDataStore.GetMovies());
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> GetMovieById(@PathVariable(value = "id") int id)
    {
        Movie movie = fakeDataStore.GetMovieById(id);
        if (movie == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
          return ResponseEntity.ok().body(movie);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity DeleteMovie(@PathVariable int id) {
        fakeDataStore.DeleteMovie(id);
        return ResponseEntity.ok().build();

    }

    @PostMapping()
    public ResponseEntity<Movie> CreateMovie(@RequestBody Movie movie) {
        if (!fakeDataStore.AddMovie(movie)){
            String entity =  "Movie " + movie.getTitle() + " already exists.";
            return new ResponseEntity(entity,HttpStatus.CONFLICT);
        } else {
            String url = "movie" + movie.getTitle(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Movie> UpdateMovie(@PathVariable("id") int id, @RequestParam String title, @RequestParam Double length, @RequestParam Genre genre,@RequestParam Double rating, @RequestParam String director)
    {
        Movie movie = new Movie(id,title,length,genre,rating,director);
        if (fakeDataStore.UpdateMovie(movie))
        {
            return  ResponseEntity.noContent().build();
        }
        else
        {
            return new ResponseEntity("Please provide a id", HttpStatus.NOT_FOUND);
        }
    }
}
