package com.tp;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== DÉBUT DU TP : SOA & Services Web (LSI3) ===");
        String xmlPath = "ecommerce.xml";
        String xsdPath = "ecommerce.xsd";

        boolean isValid = validerXmlXsd(xmlPath, xsdPath);

        if (isValid) {
            System.out.println("\n[SUCCÈS] Le document XML est valide par rapport au schéma XSD.");

            interrogerXPath(xmlPath);
        } else {
            System.out.println("\n[ERREUR] Le document XML n'est pas valide.");
        }
    }
    public static boolean validerXmlXsd(String xmlFileName, String xsdFileName) {
        try {
            ClassLoader classLoader = Main.class.getClassLoader();
            URL xmlUrl = classLoader.getResource(xmlFileName);
            URL xsdUrl = classLoader.getResource(xsdFileName);

            if (xmlUrl == null || xsdUrl == null) {
                System.err.println("Fichiers introuvables dans le dossier resources !");
                return false;
            }

            File xmlFile = new File(xmlUrl.getFile());
            File xsdFile = new File(xsdUrl.getFile());

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();

            validator.validate(new javax.xml.transform.stream.StreamSource(xmlFile));
            return true;

        } catch (IOException | SAXException e) {
            System.err.println("Erreur de validation : " + e.getMessage());
            return false;
        }
    }

    public static void interrogerXPath(String xmlFileName) {
        try {
            System.out.println("\n--- INTERROGATION XPATH ---");

            ClassLoader classLoader = Main.class.getClassLoader();
            File xmlFile = new File(classLoader.getResource(xmlFileName).getFile());

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();

            System.out.println("\n1. Liste de tous les noms de produits :");
            XPathExpression expr1 = xpath.compile("//Produit/Nom");
            NodeList nodeList1 = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList1.getLength(); i++) {
                System.out.println(" - " + nodeList1.item(i).getTextContent());
            }

            System.out.println("\n2. Produits actuellement en promotion :");
            XPathExpression expr2 = xpath.compile("//Produit[EnPromotion='true']/Nom");
            NodeList nodeList2 = (NodeList) expr2.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList2.getLength(); i++) {
                System.out.println(" - " + nodeList2.item(i).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}