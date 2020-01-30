package org.apache.camel.example.xmldb;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class AuthorService {

    public AuthorService() { }
    
    private final Map<String, Author> authors = new TreeMap<>();

    public Collection<Author> getAuthorByID(String id) {
       authors.put("3", new Author("3", "John Doe"));
        
       return authors.values();
    }
}