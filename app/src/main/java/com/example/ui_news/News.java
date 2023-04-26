package com.example.ui_news;

public class News {
    public String title;
    public String content;
    public int id;

    // Constructor for creating a new News object with given title, content, and ID
    public News(String title, String content, int id) {
        this.title = title;
        this.content = content;
        this.id = id;
    }

    // Getter method for the title of the news item
    public String getTitle() {
        return title;
    }

    // Setter method for the title of the news item
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter method for the content of the news item
    public String getContent() {
        return content;
    }

    // Setter method for the content of the news item
    public void setContent(String content) {
        this.content = content;
    }

    // Getter method for the ID of the news item
    public int getId() {
        return id;
    }

    // Setter method for the ID of the news item
    public void setId(int id) {
        this.id = id;
    }
}
