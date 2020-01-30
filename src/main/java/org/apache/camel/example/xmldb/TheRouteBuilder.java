package org.apache.camel.example.xmldb;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

/**
 * Define REST services using the Camel REST DSL
 */
public class TheRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        
        // Configure we want to use spark-rest on port 8080 as the component for the rest DSL
        restConfiguration().component("spark-rest").apiContextPath("api-doc").port(8080)
             // and we enable json binding mode
             .bindingMode(RestBindingMode.json);

        // REST API routes
        rest("/rest").consumes("application/json").produces("application/json")
            
            // Authors
            .get("/author/{id}}").outType(Author.class)
                .to("bean:authorService?method=getAuthorByID(${id})")

            .get("/author").route().transform().constant("Hello World");


        // .get("/user/{id}}").outType(User.class)
        //     .to("bean:userService?method=getUser(${header.id})")
    }
}