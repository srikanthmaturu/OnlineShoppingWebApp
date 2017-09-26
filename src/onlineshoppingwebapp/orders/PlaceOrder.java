package onlineshoppingwebapp.orders;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import onlineshoppingwebapp.banking.beans.CreditCardBean;
import onlineshoppingwebapp.banking.models.CreditCards;
import onlineshoppingwebapp.logger.LogPrinter;
import onlineshoppingwebapp.models.Orders;
import onlineshoppingwebapp.mysqlhelpers.CreditCardDBO;
import onlineshoppingwebapp.shoppingcart.beans.PurchaseBean;
import onlineshoppingwebapp.shoppingcart.beans.ShoppingCartBean;

/**
 * Servlet implementation class PlaceOrder
 */
@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrder() {
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
		HttpSession userSession = request.getSession();
		ShoppingCartBean shoppingCartBean = (ShoppingCartBean) userSession.getAttribute("shoppingCart");
		
		String purchaseDataString = request.getParameter("purchaseData");

		JSONParser jsonParser = new JSONParser();
		JSONObject purchaseJSONData = new JSONObject();
		try {
			purchaseJSONData = (JSONObject)jsonParser.parse(purchaseDataString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		final JSONObject purchaseData = purchaseJSONData;
		Map<String, String[]> purchaseDataMap = new HashMap<String, String[]>();
		purchaseJSONData.keySet().forEach((key)-> {
			String[] values = new String[1];
			values[0] = (String) purchaseData.get(key);
			purchaseDataMap.put((String)key, values);
		});
		
		PurchaseBean purchaseBean = new PurchaseBean();
		try {
			BeanUtils.populate(purchaseBean, purchaseDataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(String key:purchaseDataMap.keySet()){
			System.out.println("key - " + key + " , value - " + purchaseDataMap.get(key)[0]);
		}
		
		purchaseBean.computeAddresses();
		
		Orders ordersModel = new Orders();
		Integer orderId = ordersModel.placeOrder(shoppingCartBean, purchaseBean);
		JSONObject status = new JSONObject();
		status.put("status", "success");
		status.put("orderId", orderId);
		response.getWriter().println(status.toJSONString());
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderItemId = request.getParameter("orderItemId");
		
		Orders ordersModel = new Orders();
		ordersModel.updateOrderItemStatus(Integer.parseInt(orderItemId), "0");
	}
	
	protected void doPUT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderItemId = request.getParameter("orderItemId");
		String shippingRefno = request.getParameter("shippingRefno");
		Orders ordersModel = new Orders();
		ordersModel.updateShippingStatus(Integer.parseInt(orderItemId), shippingRefno);
	}

}
