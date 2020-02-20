package org.apache.camel.example.xmldb.objects;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class ArticleService {

    public ArticleService() { super(); }
    
    private final Map<String, Article> articles = new TreeMap<>();

    // TODO: Link to XML file

    public Collection<Article> getArticleByID(String id) {
       articles.put("3", new Article("3", "John Doe"));
        
       return articles.values();
    }
}