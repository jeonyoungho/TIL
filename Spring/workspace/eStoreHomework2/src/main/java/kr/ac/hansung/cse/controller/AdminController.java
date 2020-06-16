package kr.ac.hansung.cse.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private ProductService productService;

	@RequestMapping
	public String adminPage() {

		return "admin";
	}

	@RequestMapping(value = "/productInventory", method = RequestMethod.GET)
	public String getProducts(Model model) {

		List<Product> products = productService.getProducts();

		model.addAttribute("products", products);

		return "productInventory";// view's logical name
	}

	@RequestMapping("/productInventory/addProduct")
	public String addProduct(Model model) {

		Product product = new Product();
		product.setCategory("컴퓨터");
		model.addAttribute("product", product);

		return "addProduct";
	}

	// web form data -> object(filled with form data)
	// 객체를 하나 만들고
	// 객체의 폼빈에 request parameter를 binding시키고
	// form bean이 모델에 들어간다

	// client web form -> controller -> service -> dato -> db
	// filter(utf-8) 강제적으로 utf-8로 인코딩해주는 필터를 하나 둔다

	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.POST)
	public String addProductPost(@Valid Product product, BindingResult result, HttpServletRequest request) {// Controller
																											// ->
																											// service
																											// -> dao

		if (result.hasErrors()) {
			System.out.println("=== Web Form data has some errors ===");
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "addProduct";
		}

		/*
		 * if (!productService.addProduct(product)) { // error
		 * System.out.println("Adding Product cannot be done"); }
		 */
		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "/resources/images/" + productImage.getOriginalFilename());

		if (productImage.isEmpty() == false) {
			System.out.println("----- file start -----");
			System.out.println("name : " + productImage.getName());
			System.out.println("filename : " + productImage.getOriginalFilename());
			System.out.println("size : " + productImage.getSize());
			System.out.println("savePath : " + savePath);
			System.out.println("----- file end -----");
		}

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		product.setImageFilename(productImage.getOriginalFilename());

		productService.addProduct(product);

		return "redirect:/admin/productInventory";
	}

	@RequestMapping(value = "/productInventory/deleteProduct/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable int id, HttpServletRequest request) {
		/*
		 * if (!productService.deleteProduct(id)) {
		 * System.out.println("Deleting Product cannot be done"); }
		 */
		Product product = productService.getProductById(id);

		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "/resources/images/" + product.getImageFilename());
		
		if(Files.exists(savePath)) {
			try {
				Files.delete(savePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		productService.deleteProduct(product);

		return "redirect:/admin/productInventory";
	}

	@RequestMapping(value = "/productInventory/updateProduct/{id}", method = RequestMethod.GET)
	public String updateProduct(@PathVariable int id, Model model) {

		// 아이디에 해당하는 product를 가져와서 보여줘야함
		Product product = productService.getProductById(id);

		model.addAttribute("product", product);

		return "updateProduct";
	}

	@RequestMapping(value = "/productInventory/updateProduct", method = RequestMethod.POST)
	public String updateProductPost(@Valid Product product, BindingResult result, HttpServletRequest request) {

		if (result.hasErrors()) {
			System.out.println("=== Web Form data has some errors ===");
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}

			// Model에 Product와 BindingResult가 담겨 넘어가서 View에서 출력이 가능함
			return "updateProduct";
		}

		/*
		 * if (!productService.updateProduct(product)) { // error
		 * System.out.println("Updating Product cannot be done"); }
		 */

		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "/resources/images/" + productImage.getOriginalFilename());

		if (productImage.isEmpty() == false) {
			System.out.println("----- file start -----");
			System.out.println("name : " + productImage.getName());
			System.out.println("filename : " + productImage.getOriginalFilename());
			System.out.println("size : " + productImage.getSize());
			System.out.println("savePath : " + savePath);
			System.out.println("----- file end -----");
		}

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		product.setImageFilename(productImage.getOriginalFilename());

		productService.updateProduct(product);

		return "redirect:/admin/productInventory";
	}

}
