/**
 * 
 */
package com.vwr.luceneclass.vwr.demo;

import com.vwr.luceneclass.vwr.Config;
import com.vwr.luceneclass.vwr.index_writer.CatalogIndexer;
import com.vwr.luceneclass.vwr.products.Catalog;
import com.vwr.luceneclass.vwr.search.CatalogSearchEngine;

/**
 * @author Carl.Corder
 * Dec 17, 2014
 * 
 */
public class Demo {

	/**
	 * Carl.Corder
	 * Dec 17, 2014
	 * @param args
	 * 
	 * Main method for all of our testing
	 * 
	 * Once the index has been created, you can browse it with Luke
	 * Luke 4.10 works with Lucence 4.10
	 * You can download Luke 4.10 from https://github.com/DmitryKey/luke/releases
	 * It can be run via command line: java -XX:MaxPermSize=512m -jar luke-with-deps.jar
	 * 
	 */
	public static void main(String[] args) {

		String query = "latex";
		int limit = 10;
		// instantiating the catalog calls on
		Catalog productCatalog = new Catalog(Config.PRODUCT_SOURCE);
		CatalogIndexer catalogIndexer = new CatalogIndexer(Config.INDEX_DIR);
		
		try {
			// index the catalog
			catalogIndexer.indexCatalog(productCatalog);
			// instantiate the search
			CatalogSearchEngine catalogSearcher = new CatalogSearchEngine(Config.INDEX_DIR);
			// search for "graphite" and display the results
			// a search for "micro" will display 0 results..maybe I need to use a fuzzy search to achieve this?
			catalogSearcher.displaySearchResults(query, limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
