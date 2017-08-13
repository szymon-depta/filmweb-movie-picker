package com.sdepta.filmwebmoviepicker.reader;

import com.sdepta.filmwebmoviepicker.model.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlReader {

    private static final Logger LOG = LoggerFactory.getLogger(HtmlReader.class);

    public List<Movie> getWannaSeeMovies(String username) {
        Document doc;
        ArrayList<Movie> movies;
        try {
            doc = Jsoup.connect("http://www.filmweb.pl/user/" + username + "/films/wanna-see").get();

            //table class wantToSeeSee
            Elements wannaSeeTable = doc.select("table.wantToSeeSee");
            Elements wannaSeeRows = wannaSeeTable.select("tbody > tr");
            LOG.debug("wannaSeeRows.size()=" + wannaSeeRows.size());
            movies = new ArrayList<>(wannaSeeRows.size());
            for (Element wannaSeeRow : wannaSeeRows) {
                Elements titleAndHref = wannaSeeRow.select("td > div > a:eq(0)");
                Elements howMuchWannaSee = wannaSeeRow.select("td:eq(1)");

                Movie movie = new Movie(titleAndHref.text(), titleAndHref.attr("href"), Integer.parseInt(howMuchWannaSee.attr("sorttable_customkey")));
                movies.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
            movies = new ArrayList<>();
        }
        return movies;
    }
}
