package utilities;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LocatorUtil {
    private Map<String, Map<String, String>> locatorMap;

    // Constructor to load locators from an XML file
    public LocatorUtil(String xmlFilePath) {
        locatorMap = new HashMap<>();
        loadLocators(xmlFilePath);
    }

    // Method to load locators from the XML file
    private void loadLocators(String xmlFilePath) {
        try {
            File file = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            // Iterate through each screen node
            NodeList screenList = document.getElementsByTagName("screen");
            for (int i = 0; i < screenList.getLength(); i++) {
                Node screenNode = screenList.item(i);
                if (screenNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element screenElement = (Element) screenNode;
                    String screenId = screenElement.getAttribute("screenId");

                    // Create a map to hold object locators for the screen
                    Map<String, String> objectLocators = new HashMap<>();

                    // Iterate through object nodes under the screen
                    NodeList objectList = screenElement.getElementsByTagName("object");
                    for (int j = 0; j < objectList.getLength(); j++) {
                        Node objectNode = objectList.item(j);
                        if (objectNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element objectElement = (Element) objectNode;
                            String objectName = objectElement.getAttribute("objectName");
                            String xpath = objectElement.getElementsByTagName("xpath").item(0).getTextContent();
                            objectLocators.put(objectName, xpath);
                        }
                    }
                    locatorMap.put(screenId, objectLocators);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve a locator by screen and object name
    public String getLocator(String objectName, String screenId) {
        if (locatorMap.containsKey(screenId)) {
            Map<String, String> objectLocators = locatorMap.get(screenId);
            return objectLocators.getOrDefault(objectName, null);
        }
        return null;
    }

    // Method to print all loaded locators (for debugging purposes)
    public void printLocators() {
        locatorMap.forEach((screenId, objectLocators) -> {
            System.out.println("Screen: " + screenId);
            objectLocators.forEach((objectName, xpath) -> {
                System.out.println("  Object: " + objectName + " -> " + xpath);
            });
        });
    }
}
