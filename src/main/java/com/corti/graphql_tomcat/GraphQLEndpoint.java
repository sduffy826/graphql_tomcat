package com.corti.graphql_tomcat;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import javax.servlet.annotation.WebServlet;
import graphql.servlet.SimpleGraphQLServlet;

import graphql.schema.GraphQLSchema;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {
  private static final boolean debugIt = true;
  private static final LinkRepository linkRepository;
  
  static {
    MongoDatabase mongoDb = new MongoClient().getDatabase("hackernews");
    linkRepository = new LinkRepository(mongoDb.getCollection("links"));
  }
  
  public GraphQLEndpoint() {
    super(buildSchema());
    if (debugIt) System.out.println("In GraphQLEndpoint constructor.");
  }
    
  private static GraphQLSchema buildSchema() {
    if (debugIt) System.out.println("In GraphQLSchema->buildSchema()");
    return SchemaParser.newParser()
             .file("schema.graphqls")
             .resolvers(new Query(linkRepository), new Mutation(linkRepository))
             .build()
             .makeExecutableSchema();
  }

}
