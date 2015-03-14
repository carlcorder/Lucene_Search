/**
 * 
 */
package main.java.com.vwr.luceneclass.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import main.java.com.vwr.luceneclass.vwr.Config;
import main.java.com.vwr.luceneclass.vwr.products.Product;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;

/**
 * @author carl.corder
 * Dec 17, 2014
 * 
 */
public class ProductUtils {

	// read in list from the products csv and create a list of Product.java classes
	public static List<Product> getProducts(String productsCsv) {
		List<Product> products = new ArrayList<Product>();
		try {
			// DEFAULT format will remove the quotes surrounding each field
			CSVParser parser = new CSVParser(new FileReader(productsCsv), CSVFormat.DEFAULT.withHeader(Product.CAT_NUM,
																									   Product.DESC,
																									   Product.SUPPLIER_NUM,
																									   Product.CAS_NUM));
			for(CSVRecord csvRecord : parser){
				/*System.out.println("getting product " + csvRecord.get(Product.CAT_NUM));*/
				products.add(new Product(csvRecord.get(Product.CAT_NUM).trim(),
										 csvRecord.get(Product.DESC).trim(),
										 csvRecord.get(Product.SUPPLIER_NUM).trim(),
										 csvRecord.get(Product.CAS_NUM).trim()));
			}
			parser.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return products;
	}
	
	// write the product content to file
	public static void publishContentToFile(String catNum){
		File content = new File(buildFullPath(Config.PATH, catNum, Config.EXTENSION));
		try {
			FileWriter fileWriter = new FileWriter(content);
			/*System.out.println(Jsoup.connect(getUrl(catNum).toString()).timeout(0).get().getElementsByClass(Config.DIV).text());*/
			fileWriter.write(Jsoup.connect(getUrl(catNum).toString()).timeout(0).get().getElementsByClass(Config.DIV).text());
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// wrap the apache common IO method
	public static String getTextFromFile(File content){
		String textContent = "";
		try {
			textContent = FileUtils.readFileToString(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return textContent;
	}
	
	// build the full path from parameters
	public static String buildFullPath(String basePath, String fileName, String extension){
		StringBuilder fullPath = new StringBuilder();
		fullPath.append(basePath);
		fullPath.append(fileName);
		fullPath.append(extension);
		return fullPath.toString();
	}
	
	public static URL getUrl(String catNum){
		// set the URL and return it
		URL url = null;
		try {
			url = new URL(Config.baseUrl.replace("<cat_num>", catNum));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
}
