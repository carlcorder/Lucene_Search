/**
 * 
 */
package com.vwr.luceneclass.gutenberg;

import java.io.BufferedReader;
import java.io.FileReader;


/**
 * @author Carl.Corder
 * Dec 12, 2014
 * 
 */
public class Book {
	
	private String id;
	private String title;
	private String author;
	private BookContent content;

	
	/**
	 * @param id
	 * @param title
	 * @param author
	 * //@param present
	 * 
	 * constructor used when the book content needs to be downloaded
	 * the present flag tells us if we already have the book content
	 * on the local drive or if we need to got fetch it from the url.
	 * 
	 */
	public Book(String id, String title, String author){
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.content = new BookContent(id);
	}
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public BookContent getContent() {
		return content;
	}
	
	public int getWordCount(){
		//TODO
		return 0;
	}
	
	@Override
	public String toString() {
		String story = "";
		try {
			BufferedReader input = new BufferedReader(new FileReader(this.content.getDocument()));
			String line = "";
			while((line = input.readLine()) != null){
				// construct the story line by line
				story += line + "\n";
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return story;
	}

}
