package com.movie.movierentals.model;

/**
 * This model stores movie name and code like new, children or regular.
 */
public class Movie {
    private String title;
    private String code;

    public Movie(String title, String code) {

        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }
}
