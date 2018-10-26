package com.corti.graphql_tomcat;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import java.util.List;

public class Query implements GraphQLRootResolver {

  private final LinkRepository linkRepository;
  private static final boolean debugIt = true;
  
  public Query(LinkRepository linkRepository) {
    if (debugIt) System.out.println("In Query constructor");
    this.linkRepository = linkRepository;
  }
  
  public List<Link> allLinks() {
    if (debugIt) System.out.println("In Query->allLinks()");
    return linkRepository.getAllLinks();
  }
}
