package com.model2.mvc.service.domain;
// W D 

import java.sql.Date;
import java.util.Map;

import com.model2.mvc.service.TranCodeMapper;
import com.model2.mvc.service.domain.Product;


public class Purchase {

	// Field
	// 총 11개
	private int tranNo;				// TRAN_NO			PK	구매번호 10000부터 시작
	private Product purchaseProd;	// PROD_NO			NN	구매 물품 정보
	private User buyer;				// BUYER_ID			NN	구매자 정보
	private String paymentOption;	// PAYMENT_OPTION		지불방식 (1:현금구매, 2:신용구매)
	private String receiverName;	// RECEIVER_NAME		받는사람 이름
	private String receiverPhone;	// RECEIVER_PHONE		받는사람 연락처
	private String dlvyAddr;		// DLVY_ADDR			배송지 주소
	private String dlvyRequest;		// DLVY_REQUEST 		배송 요청사항
	private String tranCode;		// TRAN_STATUS_CODE 	구매 상태 코드
	//														(1:판매중, 2:구매완료, 3:배송중, 4:배송완료)
	private Date orderDate;			// ORDER_DATE			구매 일자
	private String dlvyDate;		// DLVY_DATE			배송 희망 일자
	//														(배송이 완료되면 배송된 날짜로 변경하기)
	private int prodNo;
	private String buyerId;
	
	
	// Constructor
	public Purchase() {
	}
	
	public Purchase(User buyer, Product product) {
		this.buyer = buyer;
		purchaseProd = product;
	}

	// Method
	public int getProdNo() {
		return prodNo;
	}

	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
		purchaseProd = new Product(prodNo);
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
		buyer = new User(buyerId);
	}

	public int getTranNo() {
		return tranNo;
	}

	public void setTranNo(int tranNo) {
		if (tranNo == 0) {
			throw new IllegalArgumentException("tranNo는 PK 값입니다.");
		}
		
		this.tranNo = tranNo;
	}

	public Product getPurchaseProd() {
		return purchaseProd;
	}

	public void setPurchaseProd(Product purchaseProd) {
		this.purchaseProd = purchaseProd;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		paymentOption = (paymentOption==null)? "1" : paymentOption.trim(); 
		this.paymentOption = paymentOption;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		
		if (receiverPhone==null) {
			receiverPhone = "";
			
		} else if (receiverPhone.contains("-")) {
			receiverPhone = receiverPhone.replace("-", "");
			
		}
		
		this.receiverPhone = receiverPhone;
	}

	public String getDlvyAddr() {
		return dlvyAddr;
	}

	public void setDlvyAddr(String dlvyAddr) {
		this.dlvyAddr = dlvyAddr;
	}

	public String getDlvyRequest() {
		return dlvyRequest;
	}

	public void setDlvyRequest(String dlvyRequest) {
		this.dlvyRequest = dlvyRequest;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		
		tranCode = tranCode.trim();
		Map<String, String> tranCodeMap = TranCodeMapper.getInstance().getMap();
		
		if (!tranCodeMap.containsKey(tranCode)) {
			throw new IllegalArgumentException("올바르지 않은 tranCode\n1:판매중, 2:구매완료, 3:배송중, 4:배송완료, 5:최종 판매완료");
		}
		
		this.tranCode = tranCode;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getDlvyDate() {
		return dlvyDate;
	}

	public void setDlvyDate(String dlvyDate) {
		this.dlvyDate = (dlvyDate==null)? dlvyDate : dlvyDate.split(" ")[0];
	}

	@Override
	public String toString() {

		return String.format("Purchase : [tranNo] %d "
				+ "\n\t[puchaseProd] %s "
				+ "\n\t[buyer] %s "
				+ "\n\t[paymentOption] %s "
				+ "\n\t[receiverName] %s [receiverPhone] %s "
				+ "\n\t[dlvyAddr] %s [dlvyRequest] %s "
				+ "\n\t[tranCode] %s "
				+ "\n\t[orderDate] %s [dlvyDate] %s ", 
						tranNo, 
						purchaseProd, 
						buyer, 
						paymentOption, 
						receiverName, receiverPhone, 
						dlvyAddr, dlvyRequest, 
						tranCode, 
						orderDate, dlvyDate);
	}

}
// class end
