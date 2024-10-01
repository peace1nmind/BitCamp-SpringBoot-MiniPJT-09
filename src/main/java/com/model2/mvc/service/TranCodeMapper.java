package com.model2.mvc.service;
// W D 

import java.util.HashMap;
import java.util.Map;

public class TranCodeMapper {

	// Field
	private static TranCodeMapper tranCodeMapper;
	private Map<String, String> map;
	
	
	// Constructor
	private TranCodeMapper() {
		
		System.out.println("\ncom.model2.mvc.service.purchase");
		System.out.println("TranCodeMapper");
	}

	
	// Method
	public static TranCodeMapper getInstance() {
		
		if (tranCodeMapper == null) {
			tranCodeMapper = new TranCodeMapper();
		}
		
		return tranCodeMapper;
	}

	public Map<String, String> getMap() {
		
		map = new HashMap<String, String>();
		map.put("1", "�Ǹ���");
		map.put("2", "���ſϷ�");
		map.put("3", "�����");
		map.put("4", "��ۿϷ�");
		map.put("5", "���� �ǸſϷ�");
		
		return map;
	}
	
}
// class end
