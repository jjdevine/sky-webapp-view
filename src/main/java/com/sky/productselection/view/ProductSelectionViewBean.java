package com.sky.productselection.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import com.jjdevine.entity.NewsProduct;
import com.jjdevine.entity.SportsProduct;
import com.jjdevine.service.catalogue.CatalogueService;
import com.jjdevine.service.location.CustomerLocationService;
import com.jjdevine.service.location.LocationRetrievalException;

/**
 * Backing bean for product selection screen.
 * @author Jonathan
 */
@ManagedBean(name="productSelectionViewBean")
@ViewScoped
public class ProductSelectionViewBean {
	
	/**
	 * Customer location service
	 */
	@EJB
	private CustomerLocationService customerLocationService;

	/**
	 * Catalogue service.
	 */
	@EJB
	private CatalogueService catalogueService;
	
	/**
	 * Product basket bean for session.
	 */
	@ManagedProperty(value="#{productBasketBean}")
	private ProductBasketBean productBasketBean;
	
	/**
	 * List of all sports products available to customer.
	 */
	private List<SportsProduct> availableSportsList;
	
	/**
	 * List of ids of selected sports products.
	 */
	private List<String> selectedSportsIdList;
	
	/**
	 * List of all news products available to customer.
	 */
	private List<NewsProduct> availableNewsList;
	
	/**
	 * List of ids of selected news products.
	 */
	private List<String> selectedNewsIdList;
	
	/**
	 * Location id of customer.
	 */
	private long locationId;

	/**
	 * Initialise method for backing bean.
	 * @throws LocationRetrievalException if unable to ascertain location id.
	 */
	@PostConstruct
	public void init() throws LocationRetrievalException {
		long customerId = getCustomerId();
		locationId = customerLocationService.getLocationId(customerId);
		
		availableSportsList = catalogueService.getSportsProducts(locationId);
		availableNewsList = catalogueService.getNewsProducts();
		selectedSportsIdList = new ArrayList<String>();
		selectedNewsIdList = new ArrayList<String>();
	}
	
	/**
	 * Obtains the customer id from a cookie.
	 * @return The customer id.
	 * @throws IllegalStateException if unable to obtain the customer id.
	 */
	private long getCustomerId() throws IllegalStateException {
		Map<String, Object> cookies = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
		if(cookies != null) {
			Object customerIdCookie = cookies.get("customerID");
			if(customerIdCookie instanceof Cookie) {
				String cookieValue = null;
				try {
					cookieValue = ((Cookie) customerIdCookie).getValue();
					return Long.parseLong(cookieValue);
				} catch (NumberFormatException ex) {
					throw new IllegalStateException("Invalid customer id <" + cookieValue + ">");
				}
			}
			
		}
		throw new IllegalStateException("No customer id cookie");
	}

	/**
	 * Checkout event for product selection page.
	 * @return The next page to render.
	 */
	public String checkout() {
		
		List<SportsProduct> sportsProducts = new ArrayList<SportsProduct>();
		List<NewsProduct> newsProducts = new ArrayList<NewsProduct>();
		
		for(String selectedSportsId: selectedSportsIdList) {
			sportsProducts.add(getSportsProductById(selectedSportsId));
		}
		
		for(String selectedNewsId: selectedNewsIdList) {
			newsProducts.add(getNewsProductById(selectedNewsId));
		}
		
		productBasketBean.setSportsProducts(sportsProducts);
		productBasketBean.setNewsProducts(newsProducts);
		
		return "confirmation";
	}
	
	/**
	 * Convenience method to return names of all selected products.
	 * @return list of all selected products.
	 */
	public List<String> getAllProducts() {
		List<String> allProducts = new ArrayList<String>();
		
		for(String sportsProductId: selectedSportsIdList) {
			allProducts.add(getSportsProductById(sportsProductId).getName());
		}
		
		for(String newsProductId: selectedNewsIdList) {
			allProducts.add(getNewsProductById(newsProductId).getName());
		}

		return allProducts;
	}
	
	/**
	 * Lookup a sports product by its id (as a string)
	 * @param idStr the product id.
	 * @return The SportsProduct
	 */
	private SportsProduct getSportsProductById(String idStr) {
		
		long id = -1;
		
		try {
			id = Long.parseLong(idStr);
		} catch (NumberFormatException ex) {
			return null; //invalid id
		}
		
		for(SportsProduct sportsProduct: getAvailableSportsList()) {
			if(sportsProduct.getId() == id) {
				return sportsProduct;
			}
		}
		return null; //not found
	}
	
	/**
	 * Lookup a news product by its id (as a string)
	 * @param idStr the product id.
	 * @return The NewsProduct
	 */
	private NewsProduct getNewsProductById(String idStr) {
		
		long id = -1;
		
		try {
			id = Long.parseLong(idStr);
		} catch (NumberFormatException ex) {
			return null; //invalid id
		}
		
		for(NewsProduct newsProduct: getAvailableNewsList()) {
			if(newsProduct.getId() == id) {
				return newsProduct;
			}
		}
		return null; //not found
	}

	/**
	 * @return the customerLocationService
	 */
	public CustomerLocationService getCustomerLocationService() {
		return customerLocationService;
	}

	/**
	 * @param customerLocationService the customerLocationService to set
	 */
	public void setCustomerLocationService(CustomerLocationService customerLocationService) {
		this.customerLocationService = customerLocationService;
	}

	/**
	 * @return the catalogueService
	 */
	public CatalogueService getCatalogueService() {
		return catalogueService;
	}

	/**
	 * @param catalogueService the catalogueService to set
	 */
	public void setCatalogueService(CatalogueService catalogueService) {
		this.catalogueService = catalogueService;
	}

	/**
	 * @return the productBasketBean
	 */
	public ProductBasketBean getProductBasketBean() {
		return productBasketBean;
	}

	/**
	 * @param productBasketBean the productBasketBean to set
	 */
	public void setProductBasketBean(ProductBasketBean productBasketBean) {
		this.productBasketBean = productBasketBean;
	}

	/**
	 * @return the availableSportsList
	 */
	public List<SportsProduct> getAvailableSportsList() {
		return availableSportsList;
	}

	/**
	 * @param availableSportsList the availableSportsList to set
	 */
	public void setAvailableSportsList(List<SportsProduct> availableSportsList) {
		this.availableSportsList = availableSportsList;
	}

	/**
	 * @return the selectedSportsIdList
	 */
	public List<String> getSelectedSportsIdList() {
		return selectedSportsIdList;
	}

	/**
	 * @param selectedSportsIdList the selectedSportsIdList to set
	 */
	public void setSelectedSportsIdList(List<String> selectedSportsIdList) {
		this.selectedSportsIdList = selectedSportsIdList;
	}

	/**
	 * @return the availableNewsList
	 */
	public List<NewsProduct> getAvailableNewsList() {
		return availableNewsList;
	}

	/**
	 * @param availableNewsList the availableNewsList to set
	 */
	public void setAvailableNewsList(List<NewsProduct> availableNewsList) {
		this.availableNewsList = availableNewsList;
	}

	/**
	 * @return the selectedNewsIdList
	 */
	public List<String> getSelectedNewsIdList() {
		return selectedNewsIdList;
	}

	/**
	 * @param selectedNewsIdList the selectedNewsIdList to set
	 */
	public void setSelectedNewsIdList(List<String> selectedNewsIdList) {
		this.selectedNewsIdList = selectedNewsIdList;
	}
}
 