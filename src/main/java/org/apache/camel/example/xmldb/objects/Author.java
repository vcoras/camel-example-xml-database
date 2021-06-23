package org.apache.camel.example.xmldb.objects;

public class Author {

    private int id;
    private String firstName;

    public Author() {
        super();
    }

    public Author(int id, String firstName) {
        super(); 

        this.id = id;
        this.firstName = firstName;
    }

    public Author(String id, String firstName) {
        super();
        
        this.id = Integer.parseInt(id);
        this.firstName = firstName;
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() { 
        return String.format(this.id + ", " + this.firstName); 
    }
}
