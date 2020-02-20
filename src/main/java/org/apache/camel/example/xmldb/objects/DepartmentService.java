package org.apache.camel.example.xmldb.objects;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class DepartmentService {

    public DepartmentService() { super(); }
    
    private final Map<String, Department> department = new TreeMap<>();

    // TODO: Link to XML file

    public Collection<Department> getDepartmentByID(String id) {
       department.put("3", new Department("3", "John Doe"));
        
       return department.values();
    }
}