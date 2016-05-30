/**
 * 
 */
package com.vwr.luceneclass.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vwr.luceneclass.gutenberg.Book;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


/**
 * @author Carl.Corder
 * Dec 12, 2014
 * 
 * utility methods for parsing csv book list
 * 
 */
public class BookUtils {
	
	// attributes of a book
	private static final String ID = "Id";
	private static final String TITLE = "Title";
	private static final String AUTHOR = "Author";
	private static final char DELIMITER = '|';
	
	// read in the list from books.csv and create a list of Book objects
	public static List<Book> getBooks(String booksCsv) {
		List<Book> books = new ArrayList<Book>();
		
		try {
			CSVParser parser = new CSVParser(new FileReader(booksCsv), CSVFormat.newFormat(DELIMITER).withHeader(ID, TITLE, AUTHOR));
			for(CSVRecord csvRecord : parser){
				System.out.println("getting book " + csvRecord.get(ID));
				books.add(new Book(csvRecord.get(ID).trim(), csvRecord.get(TITLE).trim(), csvRecord.get(AUTHOR).trim()));
			}
			parser.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return books;
	}
	
}
