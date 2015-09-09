package com.minthanthtoo.wordlists;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class WikitionaryExtractor extends DefaultHandler {
	public static final String filepath = "data/mywiktionary20150901pagesarticlesmultistream.xml";
	public static final int countLimit = Integer.MAX_VALUE;
	int count = 0;
	InputStream in;
	OutputStreamWriter out;
	String matchStr = "title";
	boolean lock = false;

	public WikitionaryExtractor(InputStream in, OutputStream os) {
		this.in = in;
		this.out = new OutputStreamWriter(os);
	}

	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		if (lock) {
			System.out.println("C:" + String.valueOf(arg0.length) + ":" + arg1
					+ ":" + arg2 + ":" + String.valueOf(arg0, arg1, arg2));
			try {
				out.write(arg0, arg1, arg2);
				out.append('\n');
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		super.characters(arg0, arg1, arg2);
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("END");
		super.endDocument();
	}

	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {
		if (++count > countLimit)
			try {
				this.in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		lock = false;

//		System.out.println("eE:" + arg0 + ":" + arg1 + ":" + arg2);
		super.endElement(arg0, arg1, arg2);
	}

	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("START");
		super.startDocument();
	}

	@Override
	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {
//		System.out.println("sE:" + arg0 + ":" + arg1 + ":" + arg2);
		if (arg2.endsWith(matchStr))
			lock = true;
		super.startElement(arg0, arg1, arg2, arg3);
	}

	public static void extract() {
		try {
			extract(new FileInputStream(filepath), new FileOutputStream(
					filepath + ".out", false));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void extract(InputStream in, OutputStream out) {
		SAXParserFactory f = SAXParserFactory.newInstance();
		try {
			SAXParser parser = f.newSAXParser();
			XMLReader r = parser.getXMLReader();
			parser.parse(in, new WikitionaryExtractor(in, out));
			out.flush();
			out.close();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
