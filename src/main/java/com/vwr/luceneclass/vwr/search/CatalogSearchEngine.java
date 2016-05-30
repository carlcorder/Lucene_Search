/**
 * 
 */
package com.vwr.luceneclass.vwr.search;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.vwr.luceneclass.vwr.products.Product;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @author carl.corder
 * Dec 23, 2014
 * 
 * 1) Create a directory instance for the given index
 * 2) Create an index searcher and pass in the directory
 * 3) Create a query and pass in the search term
 * 4) Instantiate a TopDocs object from the index searcher search method
 * 
 */
public class CatalogSearchEngine {
	
	private IndexSearcher indexSearcher = null;
	private QueryParser parser = null;
	private String indexPath;
	
	public CatalogSearchEngine(String indexPath){
		this.indexPath = indexPath;
		try {
			// pass an index reader into the index searcher
			indexSearcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(new File(indexPath))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		parser = new QueryParser(Product.CONTENT, new StandardAnalyzer());
	}
	
	// creates the query and performs the search
	public TopDocs performSearch(String searchTerm, int n) throws ParseException, IOException{
		Query query = parser.parse(searchTerm);
		return indexSearcher.search(query, n);
	}
	
	public Document getDocument(int docId) throws IOException{
		return indexSearcher.doc(docId);
	}
	
	public void displaySearchResults(String searchTerm, int n) throws ParseException, IOException{
		TopDocs hits = performSearch(searchTerm, n);
		System.out.println("Found " + hits.totalHits + " hit(s) that matched the query: " + searchTerm);
		
		for(ScoreDoc scoreDoc : hits.scoreDocs){
			Document document = indexSearcher.doc(scoreDoc.doc);
			System.out.println(document.get(Product.CAT_NUM));
		}
		
		
		// or do I run it all through a collector
		/*// all hits aggregated in the collector
		TopScoreDocCollector collector = TopScoreDocCollector.create(n, true);
		// perform the search
		indexSearcher.search(query, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		// display results
		System.out.println("Found " + hits.length + " hits.");
		for(ScoreDoc hit : hits){
			int docId = hit.doc;
			Document document = indexSearcher.doc(docId);
			// we can get any stored fields
			System.out.println("Catalog number : " + document.get(Product.CAT_NUM));
		}*/
		
	}

}
