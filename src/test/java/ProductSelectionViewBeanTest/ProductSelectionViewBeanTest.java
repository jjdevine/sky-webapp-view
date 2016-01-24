package ProductSelectionViewBeanTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jjdevine.entity.NewsProduct;
import com.jjdevine.entity.SportsProduct;
import com.sky.productselection.view.ProductBasketBean;
import com.sky.productselection.view.ProductSelectionViewBean;

public class ProductSelectionViewBeanTest {

	private ProductSelectionViewBean productSelectionViewBean;
	
	@Before
	public void init() {
		productSelectionViewBean = new ProductSelectionViewBean();
		productSelectionViewBean.setProductBasketBean(new ProductBasketBean());
		
		List<SportsProduct> sportsProducts = new ArrayList<SportsProduct>();
		sportsProducts.add(createSportsProduct(1, "sports product 1"));
		sportsProducts.add(createSportsProduct(2, "sports product 2"));
		sportsProducts.add(createSportsProduct(3, "sports product 3"));
		sportsProducts.add(createSportsProduct(4, "sports product 4"));
		sportsProducts.add(createSportsProduct(5, "sports product 5"));
		sportsProducts.add(createSportsProduct(6, "sports product 6"));
		
		List<NewsProduct> newsProducts = new ArrayList<NewsProduct>();
		newsProducts.add(createNewsProduct(1, "news product 1"));
		newsProducts.add(createNewsProduct(2, "news product 2"));
		newsProducts.add(createNewsProduct(3, "news product 3"));
		newsProducts.add(createNewsProduct(4, "news product 4"));
		newsProducts.add(createNewsProduct(5, "news product 5"));
		newsProducts.add(createNewsProduct(6, "news product 6"));
		
		productSelectionViewBean.setAvailableSportsList(sportsProducts);
		productSelectionViewBean.setAvailableNewsList(newsProducts);
		
		productSelectionViewBean.setSelectedSportsIdList(Arrays.asList(new String[]{"1", "3", "5"}));
		productSelectionViewBean.setSelectedNewsIdList(Arrays.asList(new String[]{"4", "6"}));
	}
	
	private SportsProduct createSportsProduct(long id, String name) {
		SportsProduct sp = new SportsProduct();
		sp.setId(id);
		sp.setName(name);
		return sp;
	}
	
	private NewsProduct createNewsProduct(long id, String name) {
		NewsProduct np = new NewsProduct();
		np.setId(id);
		np.setName(name);
		return np;
	}
	
	@Test
	public void testCheckout() {
	 	productSelectionViewBean.checkout();
	 	assertEquals(productSelectionViewBean.getProductBasketBean().getAllProducts().size(), 5);
	}
	
	@Test
	public void testGetAllProducts() {
		assertEquals(productSelectionViewBean.getAllProducts().size(), 5);
	}
}
