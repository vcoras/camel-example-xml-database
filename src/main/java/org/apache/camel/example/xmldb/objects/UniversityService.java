package org.apache.camel.example.xmldb.objects;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class UniversityService {

    public UniversityService() { super(); }
    
    private final Map<String, University> universities = new TreeMap<>();

    // TODO: Link to XML file

    public Collection<University> getUniversityByID(String id) {
        universities.put("3", new University("3", "John Doe"));
        
        return universities.values();
    }
}