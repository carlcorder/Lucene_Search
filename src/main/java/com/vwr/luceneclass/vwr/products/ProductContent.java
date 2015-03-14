/**
 * 
 */
package main.java.com.vwr.luceneclass.vwr.products;

import java.io.File;

import main.java.com.vwr.luceneclass.utils.ProductUtils;
import main.java.com.vwr.luceneclass.vwr.Config;

/**
 * @author carl.corder
 * Dec 17, 2014
 * 
 */
public class ProductContent {
	
	private File contentFile;
	private static final String EXT = Config.EXTENSION;
	private static final String PATH = Config.PATH;
	
	public ProductContent(String catNum){
		String fullPath = ProductUtils.buildFullPath(PATH, catNum, EXT);
		
		// check if resource already exists locally. If not go fetch and publish to file.
		if(new File(fullPath).isFile()){
			setContentFile(new File(fullPath));
		} else {
			ProductUtils.publishContentToFile(catNum);
			//FileUtils.copyURLToFile(ProductUtils.getUrl(catNum), getFile(catNum));
		}
	}

	// getters and setters
	public File getContentFile() {
		return contentFile;
	}
	
	public void setContentFile(File content) {
		this.contentFile = content;
	}
	
}
