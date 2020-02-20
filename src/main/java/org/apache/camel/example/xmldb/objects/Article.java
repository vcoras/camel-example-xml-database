package org.apache.camel.example.xmldb.objects;

public class Article {

    private int id;
    private String firstName;

    public Article() {
        super();
    }

    // Getters and setters
    public int getId() {
        return id;
    }


    @Override
    public String toString() { 
        return String.format("#" + this.id + ", " + this.firstName); 
    }
}
