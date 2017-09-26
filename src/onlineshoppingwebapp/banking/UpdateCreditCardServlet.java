package onlineshoppingwebapp.banking;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import onlineshoppingwebapp.auth.beans.RegistrationBean;
import onlineshoppingwebapp.banking.beans.CreditCardBean;
import onlineshoppingwebapp.banking.models.CreditCards;
import onlineshoppingwebapp.mysqlhelpers.CreditCardDBO;

/**
 * Servlet implementation class UpdateCreditCardServlet
 */
@WebServlet("/UpdateCreditCardServlet")
public class UpdateCreditCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCreditCardServlet() {
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
		CreditCardBean creditCardBean = new CreditCardBean();
		try {
			BeanUtils.populate(creditCardBean, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CreditCards creditCards = new CreditCards();
		creditCardBean.setId(creditCards.createCreditCard().toString());
		HttpSession session = request.getSession();
		RegistrationBean userBean = (RegistrationBean) session.getAttribute("loggedUserBean");
		creditCardBean.setUserId(userBean.getId());
		CreditCardDBO creditCardDBO = new CreditCardDBO();
		creditCardBean.convertToDBO(creditCardBean, CreditCardBean.creditCardToCreditCardDBOBiMap, creditCardDBO);
		creditCards.updateCreditCard(creditCardDBO);
		response.sendRedirect(request.getContextPath() + "/SharedPages/ViewCreditCard.jsp");
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		CreditCardBean creditCardBean = new CreditCardBean();
		try {
			BeanUtils.populate(creditCardBean, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		CreditCardDBO creditCardDBO = new CreditCardDBO();
		creditCardBean.convertToDBO(creditCardBean, CreditCardBean.creditCardToCreditCardDBOBiMap, creditCardDBO);
		**/
		String credit = request.getParameter("credit"), creditCardNumber = request.getParameter("creditCardNumber");
		
		CreditCardDBO creditCardDBO = new CreditCardDBO();
		creditCardDBO.setFieldValue("CreditCardNumber", creditCardNumber);
		CreditCards creditCards = new CreditCards();
		
		Integer creditCardId = creditCards.getCreditCardId(creditCardDBO);
		
		if(creditCardId == -1){
			return;
		}
		creditCards.modifyBalance(creditCardId.toString(), Double.parseDouble(credit), "+");
		response.getWriter().append("Success");
		//response.sendRedirect(request.getContextPath() + "/SharedPages/ViewCreditCard.jsp");
	}
}
