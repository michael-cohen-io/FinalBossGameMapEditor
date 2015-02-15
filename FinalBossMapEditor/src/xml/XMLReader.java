package xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/*
 * General purpose utility that provides XML Reading. Can be reused wherever 
 * XML needs to be read.  Provides can either return parsed document or
 * all elements containing specified tag.
 * 
 * @author: Hanif
 */
public class XMLReader {

    // very sensitive code! if you make a call, check that it 
    // does not return null!
    public static Document parseDocument(InputStream is) {
        //Get the DOM Builder Factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        try {
            //Get the DOM Builder
            builder = factory.newDocumentBuilder();
            /*Load and Parse the XML document
            document contains the complete XML as a Tree.*/
            document = builder.parse(new InputSource(is));
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            System.out.println("Parser Coniguration Exception");
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            System.out.println("SAXException");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("IO Exception");
            e.printStackTrace();
        }
        return document;
    }

    //returns ArrayList of all children of element 'e' with tag 'tag'
    public static List<Element> getElements(String tag, Element e) {

        ArrayList<Element> elements = new ArrayList<Element>();
        NodeList nodes = e.getElementsByTagName(tag);

        for (int i = 0; i < nodes.getLength(); ++i) {
            elements.add((Element) (nodes.item(i)));
        }

        return elements;

    }
}
