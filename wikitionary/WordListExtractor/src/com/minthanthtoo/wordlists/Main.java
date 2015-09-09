package com.minthanthtoo.wordlists;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class Main {
	public static void main(String[] args) {
		WikitionaryExtractor.extract();
	}
}
