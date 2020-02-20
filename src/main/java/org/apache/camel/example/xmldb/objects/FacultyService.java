package org.apache.camel.example.xmldb.objects;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class FacultyService {

    public FacultyService() { super(); }
    
    private final Map<String, Faculty> faculties = new TreeMap<>();

    // TODO: Link to XML file

    public Collection<Faculty> getFacultyByID(String id) {
        faculties.put("3", new Faculty("3", "John Doe"));
        
        return faculties.values();
    }
}