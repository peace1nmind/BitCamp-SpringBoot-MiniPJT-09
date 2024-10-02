package com.model2.mvc.service.domain;
// W D 

import java.sql.Date;
import java.util.Map;

import com.model2.mvc.service.TranCodeMapper;
import com.model2.mvc.service.domain.Product;


public class Purchase {

	// Field
	// �� 11��
	private int tranNo;				// TRAN_NO			PK	���Ź�ȣ 10000���� ����
	private Product purchaseProd;	// PROD_NO			NN	���� ��ǰ ����
	private User buyer;				// BUYER_ID			NN	������ ����
	private String paymentOption;	// PAYMENT_OPTION		���ҹ�� (1:���ݱ���, 2:�ſ뱸��)
	private String receiverName;	// RECEIVER_NAME		�޴»�� �̸�
	private String receiverPhone;	// RECEIVER_PHONE		�޴»�� ����ó
	private String dlvyAddr;		// DLVY_ADDR			����� �ּ�
	private String dlvyRequest;		// DLVY_REQUEST 		��� ��û����
	private String tranCode;		// TRAN_STATUS_CODE 	���� ���� �ڵ�
	//														(1:�Ǹ���, 2:���ſϷ�, 3:�����, 4:��ۿϷ�)
	private Date orderDate;			// ORDER_DATE			���� ����
	private String dlvyDate;		// DLVY_DATE			��� ��� ����
	//														(����� �Ϸ�Ǹ� ��۵� ��¥�� �����ϱ�)
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
			throw new IllegalArgumentException("tranNo�� PK ���Դϴ�.");
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
			throw new IllegalArgumentException("�ùٸ��� ���� tranCode\n1:�Ǹ���, 2:���ſϷ�, 3:�����, 4:��ۿϷ�, 5:���� �ǸſϷ�");
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
