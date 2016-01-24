package ProductSelectionViewBeanTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jjdevine.entity.NewsProduct;
import com.jjdevine.entity.SportsProduct;
import com.sky.productselection.view.ProductBasketBean;

public class ProductBasketBeanTest{
	
	private ProductBasketBean productBasketBean;
	
	@Before
	public void setUp() {
		productBasketBean = new ProductBasketBean();
		
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
		
		productBasketBean.setSportsProducts(sportsProducts);
		productBasketBean.setNewsProducts(newsProducts);
	}
	
	@Test
	public void testGetAllProducts() {
		assertEquals(productBasketBean.getAllProducts().size(), 12);
		assertTrue(productBasketBean.getAllProducts().contains("news product 3"));
		assertTrue(productBasketBean.getAllProducts().contains("sports product 2"));
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

}
