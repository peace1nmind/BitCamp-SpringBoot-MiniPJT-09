package com.model2.mvc.web.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Paging;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.TranCodeMapper;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

// W 24.09.12.start 

@Controller
@RequestMapping("/product")
public class ProductController {

	// Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	// properties 파일을 application.properties에서 import해서 가져오고 있다
	@Value("${pageUnit}")
	int pageUnit;
	
	@Value("${pageSize}")
	int pageSize;
	
	@Value("${uploadDir}")
	String uploadDir;

	// Constructor
	public ProductController() {
		System.out.println(":: " +  getClass().getSimpleName() + " default Constructor call\n");
	}

	// Method
	// 상품목록
	@RequestMapping("/listProduct")
	public String listProduct(@RequestParam("menu") String menu,
							  @ModelAttribute("search") Search search,
							  @RequestParam(name = "salePage", required = false, defaultValue = "1") int salePage,
							  Model model) throws Exception {
		
		System.out.println("/listProduct");
		
		// 상품 목록 / 상품 관리 구분 로직
		// menu: search , manage
		model.addAttribute("menu" ,menu);
		model.addAttribute("title", (menu!=null && menu.equals("search"))? "상품 목록조회" : "상품관리 (판매전)");
		model.addAttribute("navi", (menu!=null && menu.equals("search"))? "getProduct" : "updateProduct");
		
		
		// 검색 조건을 다루는 로직
		search.setPageSize(pageSize);
		model.addAttribute("search", search);
		
		
		// 검색한 리스트값들을 다루는 로직 (list, count)
		/* 판매중인 상품들 (listProduct) */
		Map<String, Object> map = productService.getProductList(search);
		model.addAttribute("map" ,map);
		
		// 페이지를 다루는 로직
		Paging paging = new Paging((int) map.get("count"), search.getCurrentPage(), pageSize, pageUnit);
		model.addAttribute("paging" ,paging);
		
		
		/* 구매완료 상품들 (listSale) */
		if (menu.equals("manage")) {
			Search saleSearch = search;
			saleSearch.setCurrentPage(salePage);
			saleSearch.setPageSize(pageSize);
			
			System.out.println(String.format("\n\nsearch= %s \n\n", search));
			System.out.println(String.format("\n\nsaleSearch=  %s \n\n", saleSearch));
			
			Map<String, Object> saleMap = purchaseService.getSaleList(saleSearch);
			model.addAttribute("saleMap", saleMap);
			
			Paging salePaging = new Paging((int) saleMap.get("count"), saleSearch.getCurrentPage(), pageSize, pageUnit);
			model.addAttribute("salePaging", salePaging);
		}
		
		model.addAttribute("tranCodeMap", TranCodeMapper.getInstance().getMap());
		
		return "/product/listProduct.jsp";
	}
	
	
	// 상품정보
	@RequestMapping("/getProduct")
	public String getProduct(@RequestParam("prodNo") String prodNo,
							 @RequestParam(value="menu", required = false, defaultValue = "search") String menu,
							 HttpServletRequest request,
							 HttpServletResponse response,
							 Model model) throws NumberFormatException, Exception {
		
		System.out.println("/getProduct");
		
		// 상품정보를 가져오는 로직
		model.addAttribute("product", productService.getProduct(Integer.parseInt(prodNo)));
		model.addAttribute("menu", menu);
	
		
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

		return "forward:/product/getProduct.jsp";
	}
	
	
	// 상품정보 수정
	@GetMapping("/updateProduct")
	public String updateProduct(@RequestParam("prodNo") int prodNo,
								Model model) throws Exception {
		
		System.out.println("/updateProduct GET");
		
		model.addAttribute("product", productService.getProduct(prodNo));
		model.addAttribute("fnc", "update");
		
		return "/product/addAndUpdateProductView.jsp";
	}
	
	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute("product") Product product,
								Model model) 
								throws Exception {
		
		System.out.println("/updateProduct POST");
		
		Product beforeProduct = productService.getProduct(product.getProdNo());
		
		if (product.getFile().getSize() > 0) {
			String uuid = UUID.randomUUID().toString().split("-")[0];
			String fileExtension = product.getFileName().substring(product.getFileName().lastIndexOf("."));
			String uploadFileName = uuid + fileExtension;
			
			product.getFile().transferTo(new File(uploadDir + uploadFileName));
			
			product.setFileName(uploadFileName);
			Thread.sleep(3000);
			
		} else if (beforeProduct.getFileName() != null && !beforeProduct.getFileName().equals("")) {
			product.setFileName(beforeProduct.getFileName());
			
		} 
		
		product = productService.updateProduct(product);
		model.addAttribute("product", product);
		
		return "/product/getProduct.jsp";
	}
	
	
	// 상품등록
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		
		System.out.println("/product/addProduct GET");
		
		model.addAttribute("fnc", "add");
		
		return "/product/addAndUpdateProductView.jsp";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute("product") Product product,
							Model model) {
		
		System.out.println("/product/addProduct POST");
		
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
		model.addAttribute("product", product);
		
		return "/product/getProduct.jsp";
	}
	
	
	// 최근 본 상품
	@RequestMapping("/history")
	public String history(HttpServletRequest request) {
		
		System.out.println("/history Controller");
		
		String historys[] = null;
		List<Product> productList = new ArrayList<Product>();
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("history")) {
					historys = (cookie.getValue().trim()).split("&");
				}
			}
		}
		
		if (historys != null) {
			for (String prodNo : historys) {
			Product product = productService.getProduct(Integer.parseInt(prodNo));
			productList.add(product);
			}
		}
		
		System.out.println(productList);
		request.setAttribute("list", productList);
		request.setAttribute("tranCodeMap", TranCodeMapper.getInstance().getMap());
		
		return "/history.jsp";
	}

}
// class end