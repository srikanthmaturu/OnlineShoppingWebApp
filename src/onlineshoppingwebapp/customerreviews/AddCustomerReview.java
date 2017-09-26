package onlineshoppingwebapp.customerreviews;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import onlineshoppingwebapp.auth.beans.RegistrationBean;
import onlineshoppingwebapp.customerreviews.beans.CustomerReviewBean;
import onlineshoppingwebapp.models.CustomerReviews;
import onlineshoppingwebapp.mysqlhelpers.CustomerReviewDBO;

/**
 * Servlet implementation class AddCustomerReview
 */
@WebServlet("/AddCustomerReview")
public class AddCustomerReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCustomerReview() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerReviewBean customerReviewBean = new CustomerReviewBean();
		try {
			BeanUtils.populate(customerReviewBean, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("here");
		CustomerReviews customerReviews = new CustomerReviews();
		Integer newCustomerReviewId = customerReviews.createNewCustomerReview();
		customerReviewBean.setId(newCustomerReviewId.toString());
		CustomerReviewDBO customerReviewDBO = new CustomerReviewDBO();
		customerReviewBean.convertToDBO(customerReviewBean, CustomerReviewBean.customerReviewToCustomerReviewDBOBiMap, customerReviewDBO);
		customerReviews.updateCustomerReview(customerReviewDBO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
