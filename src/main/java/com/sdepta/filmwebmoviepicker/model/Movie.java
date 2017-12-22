package com.sdepta.filmwebmoviepicker.model;

public class Movie {
    private String title;
    private String href;
    private int howMuchWannaSee;

    public String getTitle() {
        return title;
    }

    public int getHowMuchWannaSee() {
        return howMuchWannaSee;
    }

    public Movie(String title, String href, int howMuchWannaSee) {
        this.title = title;
        this.href = href;
        this.howMuchWannaSee = howMuchWannaSee;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", howMuchWannaSee=" + howMuchWannaSee +
                '}';
    }
}
