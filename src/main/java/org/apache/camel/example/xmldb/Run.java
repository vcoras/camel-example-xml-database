package org.apache.camel.example.xmldb;

import org.apache.camel.main.Main;
import org.apache.camel.example.xmldb.util.Database;

public class Run {

    private Run() { super(); }

    public static void main(String[] args) throws Exception {
        
        Main main = new Main();

        // Bind objects
        main.bind("authorService", new AuthorService());
        main.bind("articleService", new ArticleService());
        main.bind("departmentService", new DepartmentService());
        main.bind("facultyService", new FacultyService());
        main.bind("universityService", new UniversityService());

        // RouteBuilder
        main.addRouteBuilder(new TheRouteBuilder());

        // Run the app
        main.run();

        Database connection = new Database();

        main.close();
    }
}