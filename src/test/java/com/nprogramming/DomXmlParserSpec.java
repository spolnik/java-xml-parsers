package com.nprogramming;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class DomXmlParserSpec {

    private Document document;

    @Before
    public void setUp() throws Exception {
        try (InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("newordersingle.xml")) {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(systemResourceAsStream);
            document.getDocumentElement().normalize();
        }
    }

    @Test
    public void returns_properly_root_of_xml() throws Exception {

        Element documentElement = document.getDocumentElement();
        String rootNode = documentElement.getNodeName();

        assertThat(rootNode).isEqualTo("FIXML");
    }

    @Test
    public void finds_attribute_value_of_given_tag_name() throws Exception {
        org.w3c.dom.Node instrument = document.getElementsByTagName("Instrmt").item(0);

        assertThat(instrument instanceof Element).isTrue();
        assertThat(((Element)instrument).getAttribute("Sym")).isEqualTo("IBM");
    }
}
