package com.corti.graphql_tomcat;

import com.coxautodev.graphql.tools.SchemaParser;
import javax.servlet.annotation.WebServlet;
import graphql.servlet.SimpleGraphQLServlet;

import graphql.schema.GraphQLSchema;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {
  private static final boolean debugIt = true;
  
  public GraphQLEndpoint() {
    super(buildSchema());
    if (debugIt) System.out.println("In GraphQLEndpoint constructor");
  }
    
  private static GraphQLSchema buildSchema() {
    if (debugIt) System.out.println("In GraphQLSchema->buildSchema()");
    LinkRepository linkRepository = new LinkRepository();
    return SchemaParser.newParser()
             .file("schema.graphqls")
             .resolvers(new Query(linkRepository))
             .build()
             .makeExecutableSchema();
  }

}
