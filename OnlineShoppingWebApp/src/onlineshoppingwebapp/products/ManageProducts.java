package onlineshoppingwebapp.products;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineshoppingwebapp.auth.AuthorizationStatus;
import onlineshoppingwebapp.logger.LogPrinter;
import onlineshoppingwebapp.models.CustomerReviews;
import onlineshoppingwebapp.models.ProductQA;
import onlineshoppingwebapp.models.Products;
import onlineshoppingwebapp.mysqlhelpers.CustomerReviewDBO;
import onlineshoppingwebapp.mysqlhelpers.UserDBO;

/**
 * Servlet implementation class ManageProducts
 */
@WebServlet("/ManageProducts")
public class ManageProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageProducts() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!(AuthorizationStatus.isLoggedIn(request) && AuthorizationStatus.getTypeOfUser(request) == "Seller")){
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
		
		Products productsModel = new Products();
		UserDBO sellerDBO = (UserDBO)request.getAttribute("loggedUserDBO");
		int sellerId = (Integer)(sellerDBO.getFieldValue("Id"));
		request.setAttribute("SellerProducts", productsModel.getProductsLists(sellerId));
		request.getRequestDispatcher(request.getContextPath() + "/SellerPages/ManageProducts.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Products productsModel = new Products();
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		System.out.println(" "+ productId);
		productsModel.deleteProduct(productId);
		
		ServletContext servletContext = this.getServletConfig().getServletContext();
		String productDirectory = servletContext.getInitParameter("productFilesLocation") + productId;
		LogPrinter.printLog(productDirectory);
		Path productDirectoryPath = Paths.get(productDirectory);
		System.out.println("Deleting files:\n");
		File f = new File(productDirectory);
		if(f.exists()){
			Files.walk(productDirectoryPath).sorted(Comparator.reverseOrder()).map(Path::toFile).peek(System.out::println).forEach(File::delete);;
		}
			
		ProductQA productQAModel = new ProductQA();
		productQAModel.deleteProductQA(productId);
		CustomerReviews customerReviewsModel = new CustomerReviews();
		customerReviewsModel.deleteProductReviews(productId);
		
	}
}
