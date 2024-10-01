package com.model2.mvc.service.product.impl;
// W D 

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	// Field
	@Autowired
	@Qualifier("productDao")
	private ProductDao productDao;

	// Constructor
	public ProductServiceImpl() {
		System.out.println(":: " +  getClass().getSimpleName() + " default Constructor call\n");
	}
	
	private RowBounds getRowBounds(Search search) {
		return new RowBounds(search.getStartRowNum(), search.getPageSize());
	}

	@Override
	public Product addProduct(Product product) {
		
		productDao.insertProduct(product);
		
		return productDao.selectCurrentProduct();
	}

	@Override
	public Product getProduct(int prodNo) {
		
		return productDao.selectProduct(prodNo);
	}

	@Override
	public Map<String, Object> getProductList(Search search) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", productDao.selectProductList(search, getRowBounds(search)));
		map.put("count", productDao.selectTotalCount(search));
		
		return map;
	}

	@Override
	public Product updateProduct(Product product) {
		
		productDao.updateProduct(product);
		
		return productDao.selectProduct(product.getProdNo());
	}

	@Override
	public void updateTranCode(int prodNo, String proTranCode) {
		
		Product product = productDao.selectProduct(prodNo);
		product.setProTranCode(proTranCode);
		
		productDao.updateProTranCode(product);
	}

}
// class end