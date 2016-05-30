/**
 * 
 */
package com.vwr.luceneclass.vwr.products;

import java.util.List;

import com.vwr.luceneclass.utils.ProductUtils;

/**
 * @author carl.corder
 * Dec 17, 2014
 * 
 */
public class Catalog {

	private String productSource;
	private List<Product> products;
	
	// constructor
	public Catalog(String productSource) {
		this.productSource = productSource;
		this.products = ProductUtils.getProducts(productSource);
	}

	// getters and setters
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getProductSource() {
		return productSource;
	}

	public void setProductSource(String productSource) {
		this.productSource = productSource;
	}
	
}
