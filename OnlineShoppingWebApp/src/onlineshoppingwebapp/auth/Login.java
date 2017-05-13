package onlineshoppingwebapp.auth;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.beanutils.BeanUtils;

import onlineshoppingwebapp.auth.beans.LoginBean;
import onlineshoppingwebapp.auth.beans.RegistrationBean;
import onlineshoppingwebapp.logger.LogPrinter;
import onlineshoppingwebapp.models.Database;
import onlineshoppingwebapp.models.Users;
import onlineshoppingwebapp.mysqlhelpers.UserDBO;
import onlineshoppingwebapp.translators.BeanDBOConvertor;

import java.sql.Connection;
import java.util.Enumeration;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
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
		LoginBean loginBean = new LoginBean();
		
		try {
			BeanUtils.populate(loginBean, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Enumeration params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = (String)params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}
		
		UserDBO user = new UserDBO();

		BeanDBOConvertor.convert(loginBean, loginBean.getClass(), user, user.getClass(), LoginBean.loginToUserDBOBiMap);
		Users usersModel = new Users();
		Boolean result = usersModel.validateUser(user);
		if(result){
			HttpSession session = request.getSession();
			user = usersModel.getUser(user);
			//System.out.println(user);
			session.setAttribute("loggedUserDBO", user);
			session.setAttribute("loggedUserBean", user.getBean());
			session.setAttribute("loggedin", true);
			if((Boolean)user.getFieldValue("Type")){
				session.setAttribute("user_type", "seller");
				response.sendRedirect("SellerPages/SellerHomePage.jsp");
			}
			else{
				session.setAttribute("user_type", "customer");
				response.sendRedirect("CustomerPages/CustomerHomePage.jsp");
			}
		}
		else{
			response.sendRedirect("RegistrationPage.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
