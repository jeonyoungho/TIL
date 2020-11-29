package controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;
import service.OrderService;
@WebServlet("/doOrder")
public class doOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
     Order order;
    public doOrder() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String cardNum = request.getParameter("cardNum"); //26~35행 : request객체를 통해 order클래스의 필드를 얻는 부분
		String cardType = request.getParameter("cardType");
		String price = request.getParameter("price");
		String initial = request.getParameter("initial");
		String itemNum = request.getParameter("itemNum");
		String address = request.getParameter("address");
		String firstName = request.getParameter("firstName");
		String description = request.getParameter("description");
		String lastName = request.getParameter("lastName");
		String repeatCardNum = request.getParameter("repeatCardNum");
		
		OrderService orderservice = new OrderService(cardNum, cardType, price, initial, itemNum, address,
				firstName, description, lastName, repeatCardNum);//orderservice객체 생성
		Order order = orderservice.findOrder(); //로컬변수에 order객체 할당
		request.setAttribute("order",order); //request객체의 속성 설정
		
		String page;
		if(cardNum.equals(repeatCardNum)) { //cardNum과 repeatCardNum일치시 order.jsp, 불일치시 form.jsp로 page로컬변수에할당
			page = "/view/order.jsp";
		}else {
			page="/view/form.jsp";
		}
			
		RequestDispatcher rd = request.getRequestDispatcher(page); //page변수를 인자로한 RequestDispatche객체 생성
		rd.forward(request, response); //할당된 page로 forwarding
	}
}
