package org.apache.camel.example.xmldb.objects;

public class Faculty {

    private int id;
    private String firstName;

    public Faculty() {
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
