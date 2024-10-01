package com.model2.mvc.service.purchase;
// W D 

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	
	// 구매
	public Purchase addPurchase(Purchase purchase);
	
	// 구매정보 조회
	public Purchase getPurchase(int tranNo);
	public Purchase getPurchaseByProd(int prodNo);
	
	// 구매이력 조회 (구매완료~배송중)
	public Map<String, Object> getPurchaseList(Search search, String buyerId);
	
	// 구매이력 조회 (배송완료~)
	public Map<String, Object> getPurchaseHistoryList(Search search, String buyerId);
	
	// 판매완료 상품리스트 조회
	public Map<String, Object> getSaleList(Search search);
	
	// 구매정보 수정
	public Purchase updatePurchase(Purchase purchase);
	
	// tranCode 수정
	public void updateTranCode(Purchase purchase, String tranCode);
	
	public void updateTranCode(int tranNo, String tranCode);
	
}
// class end