package com.model2.mvc.service.product;
// W D 

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductService {
	
	// 상품등록
	public Product addProduct(Product product);
	
	// 상품상세 조회
	public Product getProduct(int prodNo);
	
	// 판매 상품 리스트
	public Map<String, Object> getProductList(Search search);
	
	// 상품정보 수정
	public Product updateProduct(Product product);
	
	// TranCode 변경 (1: 판매중, 2: 구매완료, 3: 배송중, 4: 배송완료)
	public void updateTranCode(int prodNo, String proTranCode);
	
}
// class end