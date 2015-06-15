package com.nprogramming;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class DomXpathParserSpec {

    private XPath xPath;
    private Document document;

    @Before
    public void setUp() throws Exception {

        try (InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("newordersingle.xml")) {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(systemResourceAsStream);
            document.getDocumentElement().normalize();

            XPathFactory xPathFactory = XPathFactory.newInstance();
            xPath = xPathFactory.newXPath();
        }

    }

    @Test
    public void finds_attribute_value_of_given_tag_name() throws Exception {

        XPathExpression expression = xPath.compile("//Instrmt/@Sym");
        String symbol = expression.evaluate(document);

        assertThat(symbol).isEqualTo("IBM");
    }
}
