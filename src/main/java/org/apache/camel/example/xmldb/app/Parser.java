package org.apache.camel.example.xmldb.app;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

public class Parser {

	Parser() {
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

	// CREATE AUTHOR

	private static Node createAuthorNodes(Document doc, String id, String FirstName, String LastName) {

		Element author = doc.createElement("Author");

		author.setAttribute("id", id);

		author.appendChild(getAuthorElements(doc, author, "FirstName", FirstName));

		author.appendChild(getAuthorElements(doc, author, "LastName", LastName));

		return author;
	}

	private static Node getAuthorElements(Document doc, Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

	public void createAuthor(String id, String firstname, String lastname) {

		File inputFile = new File("file.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(inputFile);

		doc.getDocumentElement().normalize();

		XPath xPath = XPathFactory.newInstance().newXPath();

		try {

			NodeList nodes = doc.getElementsByTagName("Authors");
			nodes.item(0).appendChild(getAuthor(doc, id, firstname, lastname));

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// for pretty print
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);

			// write to console or file
			StreamResult console = new StreamResult(System.out);
			StreamResult file = new StreamResult(new File("file.xml"));

			// write data
			transformer.transform(source, console);
			transformer.transform(source, file);
			System.out.println("DONE");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Delete author

	public void deleteAuthor(String authorId) {

		File inputFile = new File("file.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(inputFile);

		doc.getDocumentElement().normalize();

		XPath xPath = XPathFactory.newInstance().newXPath();

		try {

			String authorPath = "/ManagementSystem/Authors/Author[@id = '" + authorId + "']";
			Node author = (Node) xPath.compile(authorPath).evaluate(doc, XPathConstants.NODE);

			NodeList nodes = doc.getElementsByTagName("Authors");
			nodes.item(0).removeChild(author);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// for pretty print
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);

			// write to console or file
			StreamResult console = new StreamResult(System.out);
			StreamResult file = new StreamResult(new File("file.xml"));

			// write data
			transformer.transform(source, console);
			transformer.transform(source, file);
			System.out.println("DONE");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Update author
	public void updateAuthor(String id, JSONObject data) {

		try {

			String authorPath = "/ManagementSystem/Authors/Author[@id = '" + authorId + "']";
			Node author = (Node) xPath.compile(authorPath).evaluate(doc, XPathConstants.NODE);

			NodeList list = author.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {

				Node node = list.item(i);

				// get the salary element, and update the value
				if ("FirstName".equals(node.getNodeName())) {
					node.setTextContent(data.get("FirstName"));
				}

				// remove firstname
				if ("LastName".equals(node.getNodeName())) {
					node.setTextContent(data.get("LastName"));
				}

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// for pretty print
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);

			// write to console or file
			StreamResult console = new StreamResult(System.out);
			StreamResult file = new StreamResult(new File("file.xml"));

			// write data
			transformer.transform(source, console);
			transformer.transform(source, file);
			System.out.println("DONE");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}