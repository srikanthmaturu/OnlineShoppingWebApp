package onlineshoppingwebapp.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineshoppingwebapp.models.Orders;
import onlineshoppingwebapp.models.Products;
import onlineshoppingwebapp.models.Users;
import onlineshoppingwebapp.shoppingcart.beans.ShoppingCartBean;

/**
 * Servlet implementation class CustomerTransactionConfirmation
 */
@WebServlet("/CustomerTransactionConfirmation")
public class CustomerTransactionConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerTransactionConfirmation() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		String orderStatus = request.getParameter("status");
		
		if(orderStatus.contains("success")){
			request.setAttribute("orderStatus", true);
			request.setAttribute("orderId", orderId);
			Orders ordersModel = new Orders();
			request.setAttribute("ordersModel", ordersModel);
			request.setAttribute("order", ordersModel.getOrderById(Integer.parseInt(orderId)));
			request.setAttribute("orderItems", ordersModel.getOrderItemsByOrderId(Integer.parseInt(orderId)));
			request.setAttribute("productsModel", new Products());
			request.setAttribute("usersModel", new Users());
			HttpSession session = request.getSession();
			session.setAttribute("shoppingCart", new ShoppingCartBean());
		}
		else if(orderStatus.contains("failure")) {
			request.setAttribute("orderStatus", false);
		}
		else {
			return;
		}
		
		request.getRequestDispatcher("/CustomerPages/CustomerTransactionConfirmation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
