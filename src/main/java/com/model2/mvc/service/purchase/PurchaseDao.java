package com.model2.mvc.service.purchase;
// W D 

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {

	// �������� ��ȸ
	public Purchase selectPurchase(int tranNo);
	
	// prodNo�� �������� ��ȸ
	public Purchase selectPurchaseByProd(int prodNo);
	
	// ����
	public int insertPurchase(Purchase purchase);
	
	// ���� �����̷� ��ȸ (���ſϷ�~�����)
	public List<Purchase> selectPurchaseList(Search search, String buyerId);
	
	// �����̷� (���ſϷ�~�����) count
	public int countPurchaseList(String buyerId);
	
	// ���� �����̷� ��ȸ (��ۿϷ�~)
	public List<Purchase> selectPurchaseHistoryList(Search search, String buyerId);
	
	// �����̷� (��ۿϷ�~) count
	public int countPurchaseHistoryList(String buyerId);
	
	// �ǸſϷ� ��ǰ����Ʈ ��ȸ (������)
	// RowBounds ����ϱ�
	public List<Purchase> selectSaleList(Search search);
	
	// �ǸſϷ� ��ǰ����Ʈ count
	public int countSaleList(Search search);
	
	// �������� ����
	public void updatePurchase(Purchase purchase);
	
	// tranCode ����
	public void updateTranStatusCode(Purchase purchase);
	
}
// class end