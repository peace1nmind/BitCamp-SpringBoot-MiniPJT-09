package com.model2.mvc.service.purchase;
// W D 

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	
	// ����
	public Purchase addPurchase(Purchase purchase);
	
	// �������� ��ȸ
	public Purchase getPurchase(int tranNo);
	public Purchase getPurchaseByProd(int prodNo);
	
	// �����̷� ��ȸ (���ſϷ�~�����)
	public Map<String, Object> getPurchaseList(Search search, String buyerId);
	
	// �����̷� ��ȸ (��ۿϷ�~)
	public Map<String, Object> getPurchaseHistoryList(Search search, String buyerId);
	
	// �ǸſϷ� ��ǰ����Ʈ ��ȸ
	public Map<String, Object> getSaleList(Search search);
	
	// �������� ����
	public Purchase updatePurchase(Purchase purchase);
	
	// tranCode ����
	public void updateTranCode(Purchase purchase, String tranCode);
	
	public void updateTranCode(int tranNo, String tranCode);
	
}
// class end