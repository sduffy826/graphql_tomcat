package com.corti.graphql_tomcat;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

// Purpose is to save and load links from storage
public class LinkRepository {
  private final MongoCollection<Document> links;
  private static final boolean debugIt = true; 
  
  public LinkRepository(MongoCollection<Document> links) {
    this.links = links;
  }
  
  public Link findById(String id) {
    if (debugIt) System.out.println("In LinkRepository->findById()");
    Document doc = links.find(eq("_id", new ObjectId(id))).first();
    return link(doc);
  }
  
  public List<Link> getAllLinks() {
    if (debugIt) System.out.println("In LinkRepository->getAllLinks()");
    List<Link> allLinks = new ArrayList<>();
    for (Document doc : links.find()) {
      allLinks.add(link(doc));
    }
    return allLinks;
  }
  
  public void saveLink(Link link) {
    if (debugIt) System.out.println("In LinkRepository->saveLink(link)");
    Document doc = new Document();
    doc.append("url",  link.getUrl());
    doc.append("description",  link.getDescription());
    links.insertOne(doc);
  }
  
  private Link link(Document doc) {
    return new Link(doc.getString("_id").toString(),
                    doc.getString("url"),
                    doc.getString("description"));
  }
  
}
