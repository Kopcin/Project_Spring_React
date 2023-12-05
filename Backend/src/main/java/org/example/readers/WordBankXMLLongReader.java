package org.example.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WordBankXMLLongReader implements StatisticLongDataReader{
    private String inputFile;
    private DocumentBuilder documentBuilder;
    private Document document;
    NodeList contentList;

    //public Class type;

    public WordBankXMLLongReader(String filePath){
        this.inputFile = filePath;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try{
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            documentBuilder = dbf.newDocumentBuilder();
            document = documentBuilder.parse(new File(inputFile));

            document.getDocumentElement().normalize();

        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data read sukcesful");
        this.contentList = document.getElementsByTagName("record");
        this.removeNonPolandData();

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            // Specify the file path of the original XML file
            StreamResult result = new StreamResult(new File(filePath));

            // Overwrite the XML file with the updated data
            transformer.transform(source, result);

            System.out.println("XML file overwritten successfully.");

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void removeNonPolandData(){
        for (int i = contentList.getLength() - 1; i >= 0; i--) {
            Node node = contentList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String key = null;
                NodeList fieldList = element.getElementsByTagName("field");
                for (int j = 0; j < fieldList.getLength(); j++) {
                    Element fieldElement = (Element) fieldList.item(j);
                    String name = fieldElement.getAttribute("name");
                    String value = fieldElement.getTextContent();
                    if (name.equals("Country or Area")) {
                        key = value;
                        break;
                    }
                }
                if (key == null || !key.equals("Poland")) {
                    System.out.println("exess data removed");
                    node.getParentNode().removeChild(node);
                }
            }
        }
    }

    private String getFieldValueByName(NodeList fieldList,String fieldName){
        for(int i = 0; i<fieldList.getLength(); i++) {
            Element fieldElement = (Element) fieldList.item(i);
            String name = fieldElement.getAttribute("name");
            String value = fieldElement.getTextContent().trim();
            if (name.equals(fieldName)) {
                return value;
            }
        }
        return null;
    }

    public StatisticLongData toStatisticData() {
        ArrayList<SingleLongData> data = new ArrayList<SingleLongData>();

        for (Integer i = 1960; i <= 2022; i++){
            data.add(new SingleLongData(i,this.getValueByYear(i)));
        }
        return  new StatisticLongData(data);
    }

    private Integer getYear(NodeList fieldList){
        for(int i = 0; i<fieldList.getLength(); i++) {
            Element fieldElement = (Element) fieldList.item(i);
            String name = fieldElement.getAttribute("name");
            int value = Integer.valueOf(fieldElement.getTextContent().trim());
            if (name.equals("year")) {
                return value;
            }
        }
        return null;
    }

    public Long getValueByYear(Integer year){
        for (int i = contentList.getLength() - 1; i >= 0; i--) {
            Node node = contentList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                NodeList fieldList = element.getElementsByTagName("field");
                if(Integer.valueOf(getFieldValueByName(fieldList, "Year")).equals(year)){
                    String foundValue = getFieldValueByName(fieldList,"Value");
                    try{
                        return Long.valueOf(foundValue);
                    }
                    catch (NumberFormatException e){
                        return StatisticDataReader.LONG_VALUE_NOT_FOUND;
                    }
                }
            }
        }
        return 0L;
    }
}
