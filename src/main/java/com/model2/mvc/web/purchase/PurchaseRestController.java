package com.model2.mvc.web.purchase;
// W 24... 

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Paging;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.TranCodeMapper;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@RestController
@RequestMapping("/purchase/json/*")
public class PurchaseRestController {

	// Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Value("${pageSize}")
	int pageSize;
	
	@Value("${pageUnit}")
	int pageUnit;
	
	
	// Constructor
	public PurchaseRestController() {
		System.out.println(":: " + getClass().getSimpleName() + " default Constructor call\n");
	}

	
	// Method
	// 구매이력 목록
	@RequestMapping("/listPurchase")
	public Map<String, Object> listPurchase(@RequestParam(required = false, defaultValue = "1") int page,
									 		@RequestParam(required = false, defaultValue = "1") int historyPage,
									 		@SessionAttribute("user") User buyer,
									 		HttpSession session) {
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
			
		/* 구매이력에 관한 로직 */
		Search search = new Search(page, pageSize);
		Map<String, Object> map = purchaseService.getPurchaseList(search, buyer.getUserId());
		Paging paging = new Paging((int) map.get("count"), search.getCurrentPage(), pageSize, pageUnit);
		
		responseMap.put("map", map);
		responseMap.put("paging", paging);
		responseMap.put("tranCodeMap", TranCodeMapper.getInstance().getMap());
		
		
		/* listPurchaseHistory 로직 */
		Search historySearch =  new Search(historyPage, pageSize);
		Map<String, Object> historyMap = purchaseService.getPurchaseHistoryList(historySearch, buyer.getUserId());
		Paging historyPaging = new Paging((int) historyMap.get("count"), historySearch.getCurrentPage(), pageSize, pageUnit);
		
		responseMap.put("historyMap", historyMap);
		responseMap.put("historyPaging", historyPaging);
		
		return responseMap;
	}
	
	
	// 구매
	@GetMapping(value="/addPurchase", params = "prodNo")
	public Map<String, Object> addPurchase(@RequestParam int prodNo) 
										   throws Exception {
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		Product product = productService.getProduct(prodNo);
		responseMap.put("product", product);
		
		return responseMap;
	}
	
	
	/* Header에 Content-Type : application/json; charset=UTF-8 필요 */
	@PostMapping(value="/addPurchase")
	public Map<String, Object> addPurcahse(@RequestBody Purchase purchase) throws Exception {
		
		System.out.println("/addPurchase POST");
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		purchase = purchaseService.addPurchase(purchase);
		
		System.out.println(purchase);
		
		responseMap.put("purchase", purchase);
		
		return responseMap;
	}
	
	
	// 구매정보
	@GetMapping(value="/getPurchase", params = "tranNo")
	public Map<String, Object> getPurchase(@RequestParam int tranNo) {
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		responseMap.put("purchase", purchaseService.getPurchase(tranNo));
		
		return responseMap;
	}
	
	
	// 구매정보 변경
	@GetMapping(value="/updatePurchase", params = "tranNo")
	public Map<String, Object> updatePurchase(@RequestParam int tranNo) {
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		responseMap.put("purchase", purchaseService.getPurchase(tranNo));
		
		return responseMap;
	}
	
	@PostMapping("/updatePurchase")
	public Map<String, Object> updatePurchase(@RequestBody Purchase purchase) {
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		responseMap.put("purchase", purchaseService.updatePurchase(purchase));
		
		return responseMap;
	}
	
	
	// 배송하기, 물건도착
	// listSale (관리자)에서 배송하기 요청
	@GetMapping("updateTranCode")
	public Map<String, Object> updateTranCode(@RequestParam int tranNo,
									   		  @RequestParam String tranCode) {
		
		System.out.println("/updateTranCode?tranNo="+tranNo);
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		if (tranCode.equals("3")) {	// 배송하기
			responseMap.put("link" ,"redirect:/product/listProduct?menu=manage");
			
			Purchase purchase = purchaseService.getPurchase(tranNo);
			
			purchaseService.updateTranCode(purchase, tranCode);
			productService.updateTranCode(purchase.getPurchaseProd().getProdNo(), tranCode);
			
		} else if (tranCode.equals("4") || tranCode.equals("5")) {	// 물건도착, 구매확정
			responseMap.put("link" ,"redirect:/purchase/listPurchase");
			
			Purchase purchase = purchaseService.getPurchase(tranNo);
			
			purchaseService.updateTranCode(tranNo, tranCode);
			productService.updateTranCode(purchase.getPurchaseProd().getProdNo(), tranCode);
		}
		
		return responseMap;
	}

}
// class end