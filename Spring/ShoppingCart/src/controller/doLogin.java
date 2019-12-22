package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerService;

@WebServlet("/doLogin")
public class doLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public doLogin() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String customerId = request.getParameter("customerId");//request객체를 통해 customerId를 읽어 들여 String 로컬변수 생성 후 할당
		String customerPw = request.getParameter("customerPw");//request객체를 통해 customerPw를 읽어 들여 String 로컬변수 생성 후 할당
		CustomerService cs = new CustomerService();//CustomerService 객체 생성

		Customer customer = cs.findCustomer(customerId);/*CustomerService객체를 통해 저장된 customer객체를 로컬변수 customer
		변수에 할당*/

		String page;
		if (customer != null) { //로그인이 맞는 조건검사하여 일치시 page 스트링변수에 view/form.jsp 또는 view/error.jsp 할당 
				page = "view/form.jsp";
		} else {
			page = "view/error.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(page); //인자에 할당된page를 전달하여 RequestDispatcher객체 생성 
		rd.forward(request, response);//저장된 page로 forwarding
	}
}
