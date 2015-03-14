/**
 * 
 */
package main.java.com.vwr.luceneclass.gutenberg;

import java.util.List;

import main.java.com.vwr.luceneclass.utils.BookUtils;

/**
 * @author Carl.Corder
 * Dec 12, 2014
 * 
 * A collection of books
 * 
 */
public class Library {
	
	private static final String BOOK_SOURCE = "C:\\vwrWorkspace\\LuceneClass\\src\\main\\resources\\library\\books.csv";
	private List<Book> books;
	
	public void fillLibrary(String bookSource){
		this.books = BookUtils.getBooks(bookSource);
	}
	
	public static void main(String[] args){
		
		Library myCollection = new Library();
		myCollection.fillLibrary(BOOK_SOURCE);
		System.out.println(myCollection.books.get(0).toString());
	}

}
