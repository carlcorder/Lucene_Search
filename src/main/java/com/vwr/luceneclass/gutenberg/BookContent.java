/**
 * 
 */
package com.vwr.luceneclass.gutenberg;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

/**
 * @author Carl.Corder
 * Dec 12, 2014
 * 
 */
public class BookContent {

	private File document;
	private static final String EXTENSION = ".txt";
	private static final String PATH = "C:\\vwrWorkspace\\LuceneClass\\src\\main\\resources\\library\\shelf\\";
	private static String baseUrl = "https://www.gutenberg.org/cache/epub/<id>/pg<id>.txt";
	private URL url;
	
	public BookContent(String id){
		try {
			// check if resource already exists locally. If not go fetch it.
			if(new File(PATH + id + EXTENSION).isFile()){
				setDocument(new File(PATH + id + EXTENSION));
			} else {
				// request the contents of the url and place it into a file on disk
				FileUtils.copyURLToFile(getUrl(id), getFile(id));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getDocument() {
		return document;
	}

	public void setDocument(File document) {
		this.document = document;
	}

	private URL getUrl(String id){
		// set the URL and return it
		try {
			url = new URL(baseUrl.replace("<id>", id));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	private File getFile(String id){
		// initialize the document and return it
		document = new File(PATH + id + EXTENSION);
		return document;
	}
	
}
