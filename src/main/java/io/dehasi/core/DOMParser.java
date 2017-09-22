package io.dehasi.core;

import com.google.common.collect.Ordering;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class DOMParser {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        Document pom = getPOM();
        List<Node> nodes = extractProperties(pom);
        Comparator<Node> comparator = comparing(Node::getNodeName);
        boolean sorted = Ordering.from(comparator).isOrdered(nodes);
        System.out.println(sorted);
        if (!sorted) {
            nodes.stream().sorted(comparator).forEach(System.out::println);
        }

    }

    private static String createXmlLine(Node node) {
        return "<" + node.getNodeName() + ">" + node.getTextContent() + "</" + node.getNodeName() + ">";
    }

    private static List<Node> extractProperties(Document pom) {
        List<Node> nodes = new ArrayList<>();
        NodeList nList = pom.getElementsByTagName("properties");
        NodeList properties = nList.item(0).getChildNodes();

        for (int i = 0; i < properties.getLength(); i++) {
            Node item = properties.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                nodes.add(item);
            }
        }
        return nodes;
    }

    private static Document getPOM() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("pom.xml"));
        document.getDocumentElement().normalize();
        return document;
    }
}