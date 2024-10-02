package com.model2.mvc.web.product;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Paging;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.TranCodeMapper;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

// W 24.09.12.start 

@RestController
@RequestMapping("/product/json")
public class ProductRestController {

	// Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Value("${pageUnit}")
	int pageUnit;
	
	@Value("${pageSize}")
	int pageSize;
	
	@Value("${uploadDir}")
	String uploadDir;

	// Constructor
	public ProductRestController() {
		System.out.println(":: " +  getClass().getSimpleName() + " default Constructor call\n");
	}

	// Method
	// 상품목록
	@RequestMapping("/listProduct")
	public Map listProduct(@RequestParam("menu") String menu,
							@ModelAttribute("search") Search search,
							@RequestParam(name = "salePage", required = false, defaultValue = "1") int salePage) 
							  throws Exception {
		
		System.out.println("Rest /listProduct");
		
		// 상품 목록 / 상품 관리 구분 로직
		// menu: search , manage
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		responseMap.put("menu", menu);
		responseMap.put("title", (menu!=null && menu.equals("search"))? "상품 목록조회" : "상품관리 (판매전)");
		responseMap.put("navi", (menu!=null && menu.equals("search"))? "getProduct" : "updateProduct");
		
		
		// 검색 조건을 다루는 로직
		search.setPageSize(pageSize);
		responseMap.put("search", search);
		
		
		// 검색한 리스트값들을 다루는 로직 (list, count)
		/* 판매중인 상품들 (listProduct) */
		Map<String, Object> map = productService.getProductList(search);
		responseMap.put("map", map);
		
		// 페이지를 다루는 로직
		Paging paging = new Paging((int) map.get("count"), search.getCurrentPage(), pageSize, pageUnit);
		responseMap.put("paging", paging);
		
		
		/* 구매완료 상품들 (listSale) */
		if (menu.equals("manage")) {
			Search saleSearch = search;
			saleSearch.setCurrentPage(salePage);
			saleSearch.setPageSize(pageSize);
			
			System.out.println(String.format("\n\nsearch= %s \n\n", search));
			System.out.println(String.format("\n\nsaleSearch=  %s \n\n", saleSearch));
			
			Map<String, Object> saleMap = purchaseService.getSaleList(saleSearch);
			responseMap.put("saleMap", saleMap);
			
			Paging salePaging = new Paging((int) saleMap.get("count"), saleSearch.getCurrentPage(), pageSize, pageUnit);
			responseMap.put("salePaging", salePaging);
		}
		
		responseMap.put("tranCodeMap", TranCodeMapper.getInstance().getMap());
		
		System.out.println("\n\n여기까지?\n\n");
		
		return responseMap;
	}
	
	
	// 상품정보
	@RequestMapping("/getProduct")
	public Map getProduct(@RequestParam("prodNo") String prodNo,
							 @RequestParam(value="menu", required = false, defaultValue = "search") String menu,
							 HttpServletRequest request,
							 HttpServletResponse response)
							 throws NumberFormatException, Exception {
		
		System.out.println("Rest /getProduct");
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		// 상품정보를 가져오는 로직
		responseMap.put("product", productService.getProduct(Integer.parseInt(prodNo)));
		responseMap.put("menu", menu);
		
		
		// 최근 본 상품 리스트 로직
		if (menu != null && menu.equals("search")) {
			Cookie[] cookies = request.getCookies();
			Cookie history = new Cookie("history", null);
			
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					history = (cookie.getName().equals("history")) ? cookie : history;
				}
			}
			
	
			String historyValue = history.getValue();
			String value = "";
			
			if (historyValue == null) {
				value = prodNo;
				
			} else {
				
				if (!historyValue.contains(prodNo)) {
					value = prodNo + "&" + historyValue;
					
				} else {
					value = historyValue.replace(prodNo, "");
					
					String[] splittedValue = value.split("&");
					value = "";
					
					for (int i = 0; i < splittedValue.length; i++) {
						
						if (!(splittedValue[i]==null || splittedValue[i].equals(""))) {
							value += splittedValue[i] + ((i < splittedValue.length -1)? "&" : "");
							
						}
					}
					
					value = prodNo + "&" + value;
					
				}
		
			}
			
			history.setValue(value);
			response.addCookie(history);
		}

		return responseMap; 
	}
	
	
	// 상품정보 수정
	@GetMapping("/updateProduct")
	public Map<String, Object> updateProduct(@RequestParam("prodNo") int prodNo)
							 throws Exception {
		
		System.out.println("Rest /updateProduct GET");
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		responseMap.put("product", productService.getProduct(prodNo));
		responseMap.put("fnc", "update");
		
		return responseMap;
	}
	
	@PostMapping("/updateProduct")
	public Map<String, Object> updateProduct(@ModelAttribute("product") Product product) 
								throws Exception {
		
		System.out.println("Rest /updateProduct POST");
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		Product beforeProduct = productService.getProduct(product.getProdNo());
		
		if (product.getFile().getSize() > 0) {
			String uuid = UUID.randomUUID().toString().split("-")[0];
			String fileExtension = product.getFileName().substring(product.getFileName().lastIndexOf("."));
			String uploadFileName = uuid + fileExtension;
			
			product.getFile().transferTo(new File(uploadDir + uploadFileName));
			Thread.sleep(2000);
			
			product.setFileName(uploadFileName);
			
		} else if (beforeProduct.getFileName() != null && !beforeProduct.getFileName().equals("")) {
			product.setFileName(beforeProduct.getFileName());
			
		} 
		
		product = productService.updateProduct(product);
		responseMap.put("product", product);
		
		return responseMap;
	}
	
	
	// 상품등록
	@GetMapping("/addProduct")
	public Map<String, Object> addProduct() {
		
		System.out.println("Rest /product/addProduct GET");
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		responseMap.put("fnc", "add");
		
		return responseMap;
	}
	
	@PostMapping("/addProduct")
	public Map<String, Object> addProduct(@ModelAttribute Product product) {
		
		System.out.println("Rest /product/addProduct POST");
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		if (product.getFile().getSize() > 0) {
			// 파일이름이 겹칠수도 있고 보안의 문제로 랜덤된 파일 이름을 사용
			String uuid = UUID.randomUUID().toString().split("-")[0];
			String fileExtension = product.getFileName().substring(product.getFileName().lastIndexOf("."));
			String uploadFileName = uuid + fileExtension;
			
			System.out.println("업로드할 디렉토리 : "+uploadDir);
			System.out.println("\n");
			File uploadFile = new File(uploadDir+uploadFileName);
			
			try {
				product.getFile().transferTo(uploadFile);
				Thread.sleep(3000);
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			product.setFileName(uploadFileName);
		}
		
		product = productService.addProduct(product);
		responseMap.put("product", product);
		
		return responseMap;
	}

}
// class end