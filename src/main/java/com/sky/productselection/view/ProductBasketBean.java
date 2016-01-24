package com.sky.productselection.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jjdevine.entity.NewsProduct;
import com.jjdevine.entity.SportsProduct;

/**
 * Product basket bean for session.
 * @author Jonathan
 *
 */
@ManagedBean(name="productBasketBean")
@SessionScoped
public class ProductBasketBean {

	/**
	 * List of selected sports products.
	 */
	private List<SportsProduct> sportsProducts;
	
	/**
	 * list of selected news products.
	 */
	private List<NewsProduct> newsProducts;
	
	/**
	 * @return the sportsProducts
	 */
	public List<SportsProduct> getSportsProducts() {
		return sportsProducts;
	}

	/**
	 * @param sportsProducts the sportsProducts to set
	 */
	public void setSportsProducts(List<SportsProduct> sportsProducts) {
		this.sportsProducts = sportsProducts;
	}

	/**
	 * @return the newsProducts
	 */
	public List<NewsProduct> getNewsProducts() {
		return newsProducts;
	}

	/**
	 * @param newsProducts the newsProducts to set
	 */
	public void setNewsProducts(List<NewsProduct> newsProducts) {
		this.newsProducts = newsProducts;
	}

	/**
	 * Convenience method to return names of all products selected.
	 * @return list of names of all selected products.
	 */
	public List<String> getAllProducts() {
		List<String> allProducts = new ArrayList<String>();
		
		for(SportsProduct sportsProduct: sportsProducts) {
			allProducts.add(sportsProduct.getName());
		}
		
		for(NewsProduct newsProduct: newsProducts) {
			allProducts.add(newsProduct.getName());
		}

		return allProducts;
	}
	
	
}
