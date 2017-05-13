package onlineshoppingwebapp.banking;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import onlineshoppingwebapp.banking.beans.CreditCardBean;
import onlineshoppingwebapp.banking.models.CreditCards;
import onlineshoppingwebapp.logger.LogPrinter;
import onlineshoppingwebapp.mysqlhelpers.CreditCardDBO;

/**
 * Servlet implementation class BankServlet
 */
@WebServlet("/BankServlet")
public class BankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankServlet() {
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
		CreditCardDBO creditCardDBO = new CreditCardDBO();
		creditCardBean.convertToDBO(creditCardBean, CreditCardBean.creditCardToCreditCardDBOBiMap, creditCardDBO);
		creditCardDBO.resetField("Id");
		creditCardDBO.resetField("Balance");
		CreditCards creditCardsModel = new CreditCards();
		Integer creditCardId = creditCardsModel.getCreditCardId(creditCardDBO);
		
		if(creditCardId == -1){
			response.getWriter().println("false");
			return;
		}
		String transactionType = request.getParameter("transactionType");
		String transactionAmount = request.getParameter("transactionAmount");
		
		Double creditCardBalance = creditCardsModel.getBalance(creditCardId); 
		Double transactionAmountD = Double.parseDouble(transactionAmount);
		
		String transactionOperator;
		if(transactionType.contains("deduct")){
			if(creditCardBalance < transactionAmountD){
				response.getWriter().println("InSufficient Funds");
				return;
			}
			transactionOperator = "-";
		}
		else if(transactionType.contains("credit")){
			transactionOperator = "+";
		}
		else {
			response.getWriter().println("Invalid Transaction");
			return;
		}
		
		creditCardsModel.modifyBalance(creditCardId.toString(), transactionAmountD, transactionOperator);
		response.getWriter().println("Success!");
	}

}
