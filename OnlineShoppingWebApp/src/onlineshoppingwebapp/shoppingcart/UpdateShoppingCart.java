package onlineshoppingwebapp.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineshoppingwebapp.shoppingcart.beans.ShoppingCartBean;

/**
 * Servlet implementation class UpdateShoppingCart
 */
@WebServlet("/UpdateShoppingCart")
public class UpdateShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateShoppingCart() {
        super();
    }
    
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		System.out.println("here");
		ShoppingCartBean shoppingCart = (ShoppingCartBean) userSession.getAttribute("shoppingCart");
		if(shoppingCart == null){
			shoppingCart = new ShoppingCartBean();
		}
		
		String productId = request.getParameter("productId");
		String quantity = request.getParameter("quantity");
		shoppingCart.updateQuantity(Integer.parseInt(productId), Integer.parseInt(quantity));
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
		
		ShoppingCartBean shoppingCart = (ShoppingCartBean) userSession.getAttribute("shoppingCart");
		if(shoppingCart == null){
			shoppingCart = new ShoppingCartBean();
			userSession.setAttribute("shoppingCart", shoppingCart);
		}
		
		String quantity = request.getParameter("quantity");
		String productId = request.getParameter("productId");
		//System.out.println(quantity + " " + productId);
		shoppingCart.addItem(Integer.parseInt(productId), Integer.parseInt(quantity));	
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		
		ShoppingCartBean shoppingCart = (ShoppingCartBean) userSession.getAttribute("shoppingCart");
		if(shoppingCart == null){
			shoppingCart = new ShoppingCartBean();
		}
		
		String productId = request.getParameter("productId");
		shoppingCart.removeItem(Integer.parseInt(productId));
	}
	
	
	
}
