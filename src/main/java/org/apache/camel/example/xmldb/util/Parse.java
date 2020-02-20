
package org.apache.camel.example.xmldb.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.io.IOException;
import java.io.File;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parse {

    Parse() {
		super();
	}

    public JSONObject getAuthor(String authorId) {

		JSONObject authorJson = new JSONObject();

		try {
			File inputFile = new File("file.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(inputFile);

			doc.getDocumentElement().normalize();

			XPath xPath = XPathFactory.newInstance().newXPath();

			String author = "/ManagementSystem/Authors/Author[@id = '" + authorId + "']";
			Node authorList = (Node) xPath.compile(author).evaluate(doc, XPathConstants.NODE);

			if (authorList.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) authorList;

				System.out
						.println("First Name : " + eElement.getElementsByTagName("FirstName").item(0).getTextContent());
				System.out.println("Last Name : " + eElement.getElementsByTagName("LastName").item(0).getTextContent());

				authorJson.put("firstName", eElement.getElementsByTagName("FirstName").item(0).getTextContent())
						.put("lastName", eElement.getElementsByTagName("LastName").item(0).getTextContent());

			} else {
				return new JSONObject().put("error", "author with id: " + authorId + " not found");
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return authorJson;

	}

	public JSONObject getArticle(String id, String column) {

		JSONObject articleJson = new JSONObject();

		try {
			File inputFile = new File("file.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(inputFile);

			doc.getDocumentElement().normalize();

			XPath xPath = XPathFactory.newInstance().newXPath();

			String article = "/ManagementSystem/Articles/Article[@id = '" + id + "']";
			Node articlesList = (Node) xPath.compile(article).evaluate(doc, XPathConstants.NODE);

			if (articlesList.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) articlesList;

				if (column == "all") {

					String articleAuthorId = eElement.getElementsByTagName("authorId").item(0).getTextContent();

					articleJson.put("name", eElement.getElementsByTagName("name").item(0).getTextContent())
							.put("year", eElement.getElementsByTagName("year").item(0).getTextContent())
							.put("rating", eElement.getElementsByTagName("rating").item(0).getTextContent())
							.put("DOI", eElement.getElementsByTagName("DOI").item(0).getTextContent())
							.put("title", eElement.getElementsByTagName("title").item(0).getTextContent());

					String author = "/ManagementSystem/Authors/Author[@id = '" + articleAuthorId + "']";
					Node authorList = (Node) xPath.compile(author).evaluate(doc, XPathConstants.NODE);

					if (authorList.getNodeType() == Node.ELEMENT_NODE) {
						Element eElementAuthor = (Element) authorList;

						articleJson.put("author", eElement.getElementsByTagName("FirstName").item(0).getTextContent()
								+ " " + eElement.getElementsByTagName("LastName").item(0).getTextContent());
					}
					
					

				} else {
					articleJson.put(column, eElement.getElementsByTagName(column).item(0).getTextContent());
				}

			} else {
				return new JSONObject().put("error", "article with id: " + id + " not found");
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return articleJson;
	}
    // Read all XML file

    // Apply xpath / XSLT

    // Example
    // getArticle(Int id, String column)
    // return JSON
    
    // if (column == "all") return all columns
    

    // getArticles()
    // return all articles

}