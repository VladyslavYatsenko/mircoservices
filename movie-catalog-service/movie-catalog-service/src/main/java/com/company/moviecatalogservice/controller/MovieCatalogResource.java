package com.company.moviecatalogservice.controller;


import com.company.moviecatalogservice.model.CatalogItem;
import com.company.moviecatalogservice.model.Movie;
import com.company.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 5)
        );
        return ratings.stream().map(rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), "Desc", rating.getRating());
                }
        ).collect(Collectors.toList());
        //get all rated movie ID`s

        //For each movie id, call movie info service and get details

        //Put them all together
    }
}
