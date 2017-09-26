package onlineshoppingwebapp.productqa;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineshoppingwebapp.productqa.beans.ProductQABean;
import onlineshoppingwebapp.models.ProductQA;
import onlineshoppingwebapp.mysqlhelpers.ProductQADBO;

/**
 * Servlet implementation class ProductQA
 */
@WebServlet("/ProductQA")
public class ProductQAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductQAServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductQABean productQABean = new ProductQABean();
		productQABean.setQuestion(request.getParameter("productQuestion"));
		productQABean.setCustomerId(request.getParameter("userId"));
		productQABean.setProductId(request.getParameter("productId"));
		
		ProductQA productQAModel = new ProductQA();
		ProductQADBO productQADBO = new ProductQADBO();
		productQABean.convertToDBO(productQABean, ProductQABean.productQAToProductQADBOBiMap, productQADBO);
		productQAModel.insertProductQA(productQADBO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String questionId = request.getParameter("questionId");
		String answer = request.getParameter("answer");
		System.out.println(questionId + " " + answer);
		ProductQA productQAModel = new ProductQA();
		productQAModel.updateAnswer(Integer.parseInt(questionId), answer);
	}

}
