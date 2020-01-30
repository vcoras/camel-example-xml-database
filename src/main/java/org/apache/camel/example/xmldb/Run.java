package org.apache.camel.example.xmldb;

import org.apache.camel.main.Main;

public class Run {

    private Run() {
        // to comply with checkstyle rule
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.bind("userService", new UserService());
        main.addRouteBuilder(new UserRouteBuilder());
        main.run();
    }
}