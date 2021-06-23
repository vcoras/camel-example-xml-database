package org.apache.camel.example.xmldb.objects;

public class Article {

    private int id;

    public Article() {
        super();
    }

    // Getters and setters
    public int getId() {
        return id;
    }


    @Override
    public String toString() { 
        return String.format("#" + this.id); 
    }
}
