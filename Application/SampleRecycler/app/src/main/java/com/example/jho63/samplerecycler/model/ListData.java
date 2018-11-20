package com.example.jho63.samplerecycler.model;

/**
 * Created by Jyoung on 2018. 8. 30..
 */

public class ListData extends ListItem {
    private String title, message;

    public ListData(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
