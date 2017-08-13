package com.sdepta.filmwebmoviepicker;

import com.sdepta.filmwebmoviepicker.algorithm.WeightedRandom;
import com.sdepta.filmwebmoviepicker.model.Movie;
import com.sdepta.filmwebmoviepicker.reader.HtmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
public class FilmwebMoviePickerApplication {

	private static final Logger LOG = LoggerFactory.getLogger(FilmwebMoviePickerApplication.class);
	HtmlReader reader = new HtmlReader();

	@RequestMapping("/user/{user}")
	String home(@PathVariable String user) {
		StringBuilder result = new StringBuilder();
		List<Movie> movies = reader.getWannaSeeMovies(user);

		for (Movie movie : movies) {
			result.append(movie.toString()).append("<br/>");
		}

		WeightedRandom.getWeightedRandom(movies, m -> m.getHowMuchWannaSee());

		return result.toString();
	}

	public static void main(String[] args) {
		SpringApplication.run(FilmwebMoviePickerApplication.class, args);
	}
}
