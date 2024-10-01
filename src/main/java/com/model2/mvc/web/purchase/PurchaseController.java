package com.model2.mvc.web.purchase;
// W 24... 

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {

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
	public PurchaseController() {
		System.out.println(":: " + getClass().getSimpleName() + " default Constructor call\n");
	}

	
	// Method
	// 구매이력 목록
	@RequestMapping("/listPurchase")
	public ModelAndView listPurchase(@RequestParam(value="page", required = false, defaultValue = "1") int page,
									 @RequestParam(value="historyPage", required = false, defaultValue = "1") int historyPage,
									 @SessionAttribute("user") User buyer,
									 HttpSession session) {
		
		System.out.println("/listPurchase");
		
		ModelAndView modelAndView = new ModelAndView();
		// Model 에 실을 map
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		/* Interceptor로 변경 필요 */
		if (buyer == null) {
			modelAndView.setViewName("redirect:/user/login");
		}
		
		
		/* 구매이력에 관한 로직 */
		Search search = new Search(page, pageSize);
		Map<String, Object> map = purchaseService.getPurchaseList(search, buyer.getUserId());
		Paging paging = new Paging((int) map.get("count"), search.getCurrentPage(), pageSize, pageUnit);
		
		modelMap.put("map", map);
		modelMap.put("paging", paging);
		modelMap.put("tranCodeMap", TranCodeMapper.getInstance().getMap());
		
		
		/* listPurchaseHistory 로직 */
		Search historySearch =  new Search(historyPage, pageSize);
		Map<String, Object> historyMap = purchaseService.getPurchaseHistoryList(historySearch, buyer.getUserId());
		Paging historyPaging = new Paging((int) historyMap.get("count"), historySearch.getCurrentPage(), pageSize, pageUnit);
		
		modelMap.put("historyMap", historyMap);
		modelMap.put("historyPaging", historyPaging);
		
		modelAndView.setViewName("forward:/purchase/listPurchase.jsp");
		modelAndView.addAllObjects(modelMap);
		
		return modelAndView;
	}
	
	
	// 구매
	@GetMapping(value="/addPurchase", params = "prodNo")
	public ModelAndView addPurchase(@RequestParam("prodNo") int prodNo) 
									throws Exception {
		
		System.out.println("/addPurchase GET");
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("forward:/purchase/addPurchaseView.jsp");
		
		Product product = productService.getProduct(prodNo);
		modelAndView.addObject("product", product);
		
		return modelAndView;
		
		
	}
	
	@PostMapping("/addPurchase")
	public ModelAndView addPurcahse(@RequestParam("prodNo") int prodNo,
									@RequestParam("buyerId") String buyerId,
									@ModelAttribute("purchase") Purchase purchase) throws Exception {
		
		System.out.println("/addPurchase POST");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/purchase/addPurchase.jsp");
		
		purchase.setPurchaseProd(new Product(prodNo));
		purchase.setBuyer(new User(buyerId));
		
		purchase = purchaseService.addPurchase(purchase);
		
		modelAndView.addObject("purchase", purchase);
		
		return modelAndView;
	}
	
	
	// 구매정보
	@RequestMapping(value="/getPurchase", params = "tranNo")
	public ModelAndView getPurchase(@RequestParam("tranNo") int tranNo) {
		
		// TODO interceptor 필요
		
		System.out.println("/getPurchase");
		
		ModelAndView modelAndView = new ModelAndView("forward:/purchase/getPurchase.jsp");
		modelAndView.addObject("purchase", purchaseService.getPurchase(tranNo));
		
		return modelAndView;
	}
	
	
	// 구매정보 변경
	@GetMapping(value="/updatePurchase", params = "tranNo")
	public ModelAndView updatePurchase(@RequestParam("tranNo") int tranNo) {
		
		System.out.println("/updatePurchase GET");
		
		ModelAndView modelAndView = new ModelAndView("forward:/purchase/updatePurchaseView.jsp");
		modelAndView.addObject("purchase", purchaseService.getPurchase(tranNo));
		
		return modelAndView;
	}
	
	@PostMapping("/updatePurchase")
	public ModelAndView updatePurchase(@ModelAttribute("purchase") Purchase purchase) {
		
		System.out.println("/updatePurchase POST");
		
		ModelAndView modelAndView = new ModelAndView("forward:/purchase/updatePurchase.jsp");
		
		modelAndView.addObject("purchase", purchaseService.updatePurchase(purchase));
		
		return modelAndView;
	}
	
	
	// 배송하기, 물건도착
	// listSale (관리자)에서 배송하기 요청
	@RequestMapping()
	public ModelAndView updateTranCode(@RequestParam(value = "prodNo", required = false, defaultValue = "0") int prodNo,
									   @RequestParam(value = "tranNo", required = false, defaultValue = "0") int tranNo,
									   @RequestParam(value = "tranCode") String tranCode) {
		
		System.out.println("/updateTranCode?"+((prodNo != 0)? "prodNo" : "tranNo"));
		
		System.out.println(tranCode);
		
		ModelAndView modelAndView = new ModelAndView();
		
		if (tranNo == 0 && prodNo != 0) {	// 배송하기
			modelAndView.setViewName("redirect:/product/listProduct?menu=manage");
			
			Purchase purchase = purchaseService.getPurchaseByProd(prodNo);
			
			purchaseService.updateTranCode(purchase, tranCode);
			productService.updateTranCode(prodNo, tranCode);
			
		} else if (tranNo != 0 && prodNo == 0) {	// 물건도착, 구매확정
			modelAndView.setViewName("redirect:/purchase/listPurchase");
			
			Purchase purchase = purchaseService.getPurchase(tranNo);
			
			purchaseService.updateTranCode(tranNo, tranCode);
			productService.updateTranCode(purchase.getPurchaseProd().getProdNo(), tranCode);
		}
		
		return modelAndView;
	}

}
// class end