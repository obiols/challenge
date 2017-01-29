package others;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Utils {
	
    public static Document loadXMLFromString(String xml) {
    	DocumentBuilder documentBuilder = null;
    	Document document = null;
    	InputSource inputSource = null;
		try {
			inputSource = new InputSource(new StringReader(xml));
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = documentBuilder.parse(inputSource);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
    	return document;
    }
}
