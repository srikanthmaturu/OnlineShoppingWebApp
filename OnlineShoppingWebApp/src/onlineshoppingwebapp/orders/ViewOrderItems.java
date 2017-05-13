package onlineshoppingwebapp.orders;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineshoppingwebapp.auth.beans.RegistrationBean;
import onlineshoppingwebapp.models.Orders;
import onlineshoppingwebapp.models.Products;
import onlineshoppingwebapp.models.Users;
import onlineshoppingwebapp.orders.beans.OrderItemBean;

/**
 * Servlet implementation class ViewOrderItems
 */
@WebServlet("/ViewOrderItems")
public class ViewOrderItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOrderItems() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		Orders ordersModel = new Orders();
		ArrayList<OrderItemBean> orderItems;
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("user_type");
		RegistrationBean userBean = (RegistrationBean) session.getAttribute("loggedUserBean");
		
		if(userType.contains("customer")){
			orderItems = ordersModel.getOrderItemsByOrderId(Integer.parseInt(orderId));
			request.setAttribute("orderId", orderId);
			request.setAttribute("orderItems", orderItems);
			request.setAttribute("usersModel", new Users());
			request.setAttribute("productsModel", new Products());
			request.getRequestDispatcher("/CustomerPages/ViewOrderItems.jsp").forward(request, response);
		}
		else if(userType.contains("seller")){
			orderItems = ordersModel.getSellerOrderItems(Integer.parseInt(userBean.getId()));
			request.setAttribute("orderItems", orderItems);
			request.setAttribute("productsModel", new Products());
			request.setAttribute("ordersModel", ordersModel);
			request.setAttribute("usersModel", new Users());
			request.getRequestDispatcher("/SellerPages/ViewCurrentOrders.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
