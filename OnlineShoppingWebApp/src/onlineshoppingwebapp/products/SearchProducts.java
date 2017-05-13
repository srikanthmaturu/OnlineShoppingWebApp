package onlineshoppingwebapp.products;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineshoppingwebapp.models.Products;
import onlineshoppingwebapp.models.Users;
import onlineshoppingwebapp.products.beans.ProductBean;

/**
 * Servlet implementation class SearchProducts
 */
@WebServlet("/SearchProducts")
public class SearchProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProducts() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchQuery = request.getParameter("searchquery");
		String[] searchQueryWords = searchQuery.split("\\s+");
		Products productsModel = new Products();
		ArrayList<ProductBean> resultProducts = productsModel.getProductsBySearchQuery(searchQueryWords);
		request.getSession().setAttribute("resultProducts", resultProducts);
		Users usersModel = new Users();
		request.setAttribute("usersModel", usersModel);
		System.out.println(request.getContextPath() + "/CustomerPages/ProductSearchResults.jsp");
		request.getRequestDispatcher("SharedPages/ProductSearchResults.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
