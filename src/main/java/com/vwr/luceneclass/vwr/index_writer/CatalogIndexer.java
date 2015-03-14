/**
 * 
 */
package main.java.com.vwr.luceneclass.vwr.index_writer;

import java.io.File;
import java.io.IOException;

import main.java.com.vwr.luceneclass.utils.ProductUtils;
import main.java.com.vwr.luceneclass.vwr.products.Catalog;
import main.java.com.vwr.luceneclass.vwr.products.Product;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @author carl.corder
 * Dec 17, 2014
 * 
 * How to build an index:
 * 
 * 1) Create instance of Directory
 * 2) Create instance of IndexWriterConfig
 * 3) Instantiate IndexWriter using the directory and configuration
 * 4) Create instances of Document for each document
 * 5) Add documents to the IndexWriter
 * 6) Commit changes to the IndexWriter
 * 7) Close the IndexWriter
 * 
 */
public class CatalogIndexer {
	
	private static final Logger LOG = Logger.getLogger(CatalogIndexer.class);
	private IndexWriter indexWriter = null;
	private String indexPath;
	
	public CatalogIndexer(String indexPath){
		this.indexPath = indexPath;
	}
		
	// create the index writer and add new documents to it
	public IndexWriter getIndexWriter(String indexPath) throws IOException {
		this.indexPath = indexPath;
		if(indexWriter == null){
			Directory indexDir = FSDirectory.open(new File(indexPath));
			/*
			 * use lucence version 4.10.2 and pass in the standard analyzer
			 * the analyzer parses each field of the data into tokens
			 */
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_2, new StandardAnalyzer());
			// creates a new index or overwrites the existing one in the given directory
			config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
			indexWriter = new IndexWriter(indexDir, config);
		}
		return indexWriter;
	}
		
	public void indexCatalog(Catalog catalog) throws IOException {
		IndexWriter writer = getIndexWriter(this.indexPath);
		// loop through all of the products in a catalog and add it to the index
		for(Product product : catalog.getProducts()){
			Document doc = new Document();
			// add the product attributes to the document
			// a text field is a sequence of terms that can be tokenized while a string field is a single term
			doc.add(new TextField(Product.CAT_NUM, product.getCatalogNum(), Field.Store.YES));
			doc.add(new TextField(Product.DESC, product.getDescription(), Field.Store.YES));
			doc.add(new TextField(Product.SUPPLIER_NUM, product.getSupplierNum(), Field.Store.YES));
			doc.add(new TextField(Product.CAS_NUM, product.getCasNum(), Field.Store.NO));
			// add the textual content of the product page as a text field
			doc.add(new TextField(Product.CONTENT, 
								ProductUtils.getTextFromFile(product.getProductContent().getContentFile()),
								Field.Store.NO));
			// add the document to the index writer
			writer.addDocument(doc);
			LOG.info("indexing catalog number " + product.getCatalogNum());
		}
		writer.commit();
		writer.close();
	}
	
}
