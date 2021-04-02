package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Product;
import com.example.service.ProductService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		Product product = new Product();
		product.setName("맥북프로");
		product.setCategory("컴퓨터");
		productService.addProduct(product);
		System.out.println("Invoke create: " + product);

		model.addAttribute("msg", "create success");
		return "home";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(Model model) {
		Product product = productService.getProductById(1);
		model.addAttribute("product", product);
		System.out.println("Invoke read: " + product);
		
		model.addAttribute("msg", "read success");
		return "home";
	}
	
	@RequestMapping(value = "/reads", method = RequestMethod.GET)
	public String reads(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		System.out.println("Invoke reads: " + products);
		
		model.addAttribute("msg", "reads success");
		return "home";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model) {
		Product product = productService.getProductById(1);
		product.setName("맥북프로이름수정");
		productService.updateProduct(product);
		System.out.println("Invoke update: " + product);
		
		model.addAttribute("msg", "update success");
		return "home";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model) {
		Product product = productService.getProductById(1);
		productService.deleteProduct(product);
		System.out.println("Invoke delete: " + product);
		
		model.addAttribute("msg", "delete success");
		return "home";
	}
	
}
