package com.model2.mvc.service.product;
// W D 

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductDao {
	
	// �Ǹ��ϴ� ��ǰ����Ʈ (tranCode=1)
	// Search�� orderBy, desc ���� (���Ŀ��ϴ� �÷��� ������ ���)
	public List<Product> selectProductList(Search search);
	
	// selectProductList�� totalCount
	public int selectTotalCount(Search search);
	
	// ��ǰ���� ��ȸ
	public Product selectProduct(int prodNo);
	
	//  ���� �ֱ� ��� ��ǰ���� ��ȸ
	public Product selectCurrentProduct();
	
	// ��ǰ���
	public int insertProduct(Product product);
	
	// ��ǰ���� ����
	public int updateProduct(Product product);
	
	// tranCode ����
	// service���� tranCode �����Ͽ� product �ְԲ�
	public int updateProTranCode(Product product);
	
}
// class end