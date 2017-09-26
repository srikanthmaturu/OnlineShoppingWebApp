package onlineshoppingwebapp.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineshoppingwebapp.models.Orders;

/**
 * Servlet implementation class UpdateOrderItems
 */
@WebServlet("/UpdateOrderItems")
public class UpdateOrderItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrderItems() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Orders ordersModel = new Orders();
		String orderItemId = request.getParameter("orderItemId");
		String shippingRefNo = request.getParameter("shippingRefNo");
		ordersModel.updateShippingStatus(Integer.parseInt(orderItemId), shippingRefNo);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Orders ordersModel = new Orders();
		String orderItemId = request.getParameter("orderItemId");
		ordersModel.updateOrderItemStatus(Integer.parseInt(orderItemId), "0");
		
	}
	

}
