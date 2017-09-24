package io.dehasi.core;

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
import java.util.List;

import static java.util.stream.Collectors.toList;

final class PomReader {

    private PomReader() {
    }

    static List<Property> loadProperties(File pomFile) {
        Document pom = loadPOM(pomFile);
        return extractProperties(pom).stream().map(PomReader::createProperty).collect(toList());
    }

    private static Document loadPOM(File pomFile) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(pomFile);
            document.getDocumentElement().normalize();
            return document;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
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

    private static Property createProperty(Node node) {
        return new Property(node.getNodeName(), node.getNodeName());
    }
}