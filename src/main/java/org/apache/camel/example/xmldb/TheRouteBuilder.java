package org.apache.camel.example.xmldb;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.example.xmldb.objects.*;
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
        rest("/api").consumes("application/json").produces("application/json")
            
            // Author
            .get("/authors").outType(Author.class).to("bean:authorService?method=get()")
            .get("/author/{id}").outType(Author.class).to("bean:authorService?method=getByID(${id})")

            .post("/author").outType(Author.class).to("bean:authorService?method=create()")
            .put("/author/{id}").outType(Author.class).to("bean:authorService?method=update(${id})")
            .delete("/author/{id}").outType(Author.class).to("bean:authorService?method=delete(${id})")

            // Article
            .get("/articles").outType(Author.class).to("bean:articleService?method=get()")
            .get("/article/{id}").outType(Author.class).to("bean:articleService?method=getByID(${id})")

            .post("/article").outType(Author.class).to("bean:articleService?method=create()")
            .put("/article/{id}").outType(Author.class).to("bean:articleService?method=update(${id})")
            .delete("/article/{id}").outType(Author.class).to("bean:articleService?method=delete(${id})")

            // Department
            .get("/departments").outType(Author.class).to("bean:departmentService?method=get()")
            .get("/department/{id}").outType(Author.class).to("bean:departmentService?method=getByID(${id})")

            .post("/department").outType(Author.class).to("bean:departmentService?method=create()")
            .put("/department/{id}").outType(Author.class).to("bean:departmentService?method=update(${id})")
            .delete("/department/{id}").outType(Author.class).to("bean:departmentService?method=delete(${id})")
            
            // Faculty
            .get("/faculties").outType(Author.class).to("bean:facultyService?method=get()")
            .get("/faculty/{id}").outType(Author.class).to("bean:facultyService?method=getByID(${id})")

            .post("/faculty").outType(Author.class).to("bean:facultyService?method=create()")
            .put("/faculty/{id}").outType(Author.class).to("bean:facultyService?method=update(${id})")
            .delete("/faculty/{id}").outType(Author.class).to("bean:facultyService?method=delete(${id})")
            
            // University
            .get("/universities").outType(Author.class).to("bean:universityService?method=get()")
            .get("/university/{id}").outType(Author.class).to("bean:universityService?method=getByID(${id})")

            .post("/university").outType(Author.class).to("bean:universityService?method=create()")
            .put("/university/{id}").outType(Author.class).to("bean:universityService?method=update(${id})")
            .delete("/university/{id}").outType(Author.class).to("bean:universityService?method=delete(${id})");
    }
}