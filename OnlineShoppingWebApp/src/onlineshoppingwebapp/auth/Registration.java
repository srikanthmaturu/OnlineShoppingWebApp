package onlineshoppingwebapp.auth;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import onlineshoppingwebapp.auth.beans.*;
import onlineshoppingwebapp.logger.LogPrinter;
import onlineshoppingwebapp.models.Users;
import onlineshoppingwebapp.mysqlhelpers.UserDBO;
import onlineshoppingwebapp.translators.BeanDBOConvertor;
/**
 * Servlet implementation class Registration
 */
@WebServlet(description = "User registration", urlPatterns = { "/Registration" })
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RegistrationBean registrationBean = new RegistrationBean();
		try {
			BeanUtils.populate(registrationBean, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		Enumeration params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = (String)params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}
		*/
		Users usersModel = new Users();
		registrationBean.setId(usersModel.createNewUser().toString());
		UserDBO user = new UserDBO();
		BeanDBOConvertor.convert(registrationBean, registrationBean.getClass(), user, user.getClass(), RegistrationBean.regToUserDBOBiMap);
		
		Boolean result = usersModel.registerUser(user);
		LogPrinter.printLog("User registered");
		response.sendRedirect("LoginPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
