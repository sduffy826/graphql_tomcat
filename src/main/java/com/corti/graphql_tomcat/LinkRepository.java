package com.corti.graphql_tomcat;

import java.util.ArrayList;
import java.util.List;

// Purpose is to save and load links from storage
public class LinkRepository {
  private final List<Link> links;
  private static final boolean debugIt = true; 
  
  public LinkRepository() {
    if (debugIt) System.out.println("In constructor for LinkRepository");
    links = new ArrayList<>();
    links.add(new Link("http://www.google.com", "your favorite search engine"));
    links.add(new Link("http://www.tvguide.com", "your entertainment"));
  }
  
  public List<Link> getAllLinks() {
    if (debugIt) System.out.println("In LinkRepository->getAllLinks()");
    return links;
  }
  
  public void saveLink(Link link) {
    if (debugIt) System.out.println("In LinkRepository->saveLink(link)");
    links.add(link);
  }
}
