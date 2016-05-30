/**
 * 
 */
package com.vwr.luceneclass.vwr.products;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author carl.corder
 * Dec 17, 2014
 * 
 */
public class Product {
	
	private String catalogNum;
	private String description;
	private String supplierNum;
	private String casNum;
	private ProductContent productContent;
	
	// attributes of a product
	public static final String CAT_NUM = "Catalog Number";
	public static final String DESC = "Description";
	public static final String SUPPLIER_NUM = "Supplier Catalog Number";
	public static final String CAS_NUM = "CAS Number";
	public static final String CONTENT = "Content";
	
	
	// constructor
	public Product(String catalogNum, String description, String supplierNum, String casNum) {
		
		this.catalogNum = catalogNum;
		this.description = description;
		this.supplierNum = supplierNum;
		this.casNum = casNum;
		this.productContent = new ProductContent(catalogNum);
		
	}
	
	@Override
	public String toString() {
		String html = "";
		try {
			BufferedReader input = new BufferedReader(new FileReader(this.productContent.getContentFile()));
			String line = "";
			while((line = input.readLine()) != null){
				// construct the html source line by line
				html += line + "\n";
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return html;
	}
	
	// getters and setters
	public String getCatalogNum() {
		return catalogNum;
	}

	public void setCatalogNum(String catalogNum) {
		this.catalogNum = catalogNum;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getSupplierNum() {
		return supplierNum;
	}

	public void setSupplierNum(String supplierNum) {
		this.supplierNum = supplierNum;
	}

	public String getCasNum() {
		return casNum;
	}

	public void setCasNum(String casNum) {
		this.casNum = casNum;
	}

	public ProductContent getProductContent() {
		return productContent;
	}

	public void setProductContent(ProductContent productContent) {
		this.productContent = productContent;
	}

}
