package org.tvbookmarks.app.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.tvbookmarks.app.web.rest.models.Movie;
import org.tvbookmarks.app.web.rest.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class TvController {

    private String apiKey = "3166a07754e8519a061813405d20f2e7";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
        return new Movie(movieSummary.getId(), movieSummary.getTitle(), movieSummary.getOverview());
    }
}
