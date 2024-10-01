package com.model2.mvc.service.product;
// W D 

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductService {
	
	// ��ǰ���
	public Product addProduct(Product product);
	
	// ��ǰ�� ��ȸ
	public Product getProduct(int prodNo);
	
	// �Ǹ� ��ǰ ����Ʈ
	public Map<String, Object> getProductList(Search search);
	
	// ��ǰ���� ����
	public Product updateProduct(Product product);
	
	// TranCode ���� (1: �Ǹ���, 2: ���ſϷ�, 3: �����, 4: ��ۿϷ�)
	public void updateTranCode(int prodNo, String proTranCode);
	
}
// class end