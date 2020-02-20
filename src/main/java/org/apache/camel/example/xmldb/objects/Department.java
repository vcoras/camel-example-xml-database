package org.apache.camel.example.xmldb.objects;

public class Department {

    private int id;

    public Department() {
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
