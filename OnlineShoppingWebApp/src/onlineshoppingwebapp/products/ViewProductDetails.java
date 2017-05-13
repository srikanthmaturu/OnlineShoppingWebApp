package onlineshoppingwebapp.products;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import onlineshoppingwebapp.auth.beans.RegistrationBean;
import onlineshoppingwebapp.customerreviews.beans.CustomerReviewBean;
import onlineshoppingwebapp.models.CustomerReviews;
import onlineshoppingwebapp.models.ProductQA;
import onlineshoppingwebapp.models.Products;
import onlineshoppingwebapp.models.Users;
import onlineshoppingwebapp.productqa.beans.ProductQABean;
import onlineshoppingwebapp.products.beans.ProductBean;
import onlineshoppingwebapp.products.beans.ProductCategoryBean;

/**
 * Servlet implementation class ViewProductDetails
 */
@WebServlet("/ViewProductDetails")
public class ViewProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProductDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String productId = request.getParameter("productId");
		
		if(productId == null){
			response.sendRedirect(request.getContextPath() + "/SellerPages/ManageProducts.jsp");
		}
		
		Products productsModel = new Products();
		CustomerReviews customerReviewsModel = new CustomerReviews();
		ProductQA productQAModel = new ProductQA();
		Users usersModel = new Users();
		
		int productIdInteger = Integer.parseInt(productId);
		
		ProductBean productBean = productsModel.getNewProduct(productIdInteger);
		ArrayList<CustomerReviewBean> customerReviewsBeans = customerReviewsModel.getCustomerReviews(productIdInteger);
		ArrayList<ProductQABean> productQABeans = productQAModel.getProductQA(productIdInteger);
		RegistrationBean sellerBean = usersModel.getUser(Integer.parseInt(productBean.sellerId));
		
		HttpSession session = request.getSession();
		session.setAttribute("productBean", productBean);
		session.setAttribute("customerReviewBeans", customerReviewsBeans);
		session.setAttribute("productQABeans", productQABeans);
		String productDirectory = request.getServletContext().getContextPath()+ "/data/products/" +  productBean.id;
		session.setAttribute("productDirectory", productDirectory);
		session.setAttribute("sellerBean", sellerBean);
		session.setAttribute("usersModel", usersModel);
		JSONArray productPhotos = (JSONArray)(new JSONArray(productBean.productPhotosLinks)).get(0);
		JSONArray productVideos = (JSONArray)(new JSONArray(productBean.productVideosLinks)).get(0);
		System.out.println(productPhotos.toString());
			
		ArrayList<String> productPhotosLinks = new ArrayList<String>();
		for(Object productPhoto: productPhotos.toList()){
			//System.out.println(productPhoto);
			productPhotosLinks.add(productDirectory+"/"+(String)productPhoto);
		}
		ArrayList<String> productVideosLinks  = new ArrayList<String>();
		for(Object productVideo: productVideos.toList()){
			//System.out.println(productVideo.toString());
			productVideosLinks.add(productDirectory+"/"+(String)productVideo);
		}
		session.setAttribute("productPhotosLinks", productPhotosLinks);
		session.setAttribute("productVideosLinks", productVideosLinks);
		request.getRequestDispatcher("SharedPages/ViewProductDetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
