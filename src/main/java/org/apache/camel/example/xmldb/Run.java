package org.apache.camel.example.xmldb;

import org.apache.camel.main.Main;
import org.apache.camel.example.xmldb.util.Database;

public class Run {

    private Run() {}

    public static void main(String[] args) throws Exception {
        
        Main main = new Main();

        main.bind("authorService", new AuthorService());
        main.addRouteBuilder(new TheRouteBuilder());
        main.run();

        Database connection = new Database();

        main.close();
    }
}