package jinshen.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jinshen.bean.IdentifyCode;

/**
 *
 */
public class CheckCodeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("creat img...");
		HttpSession session = request.getSession();
		IdentifyCode identifyCode = new IdentifyCode();
		identifyCode.write(response.getOutputStream());
		String code = identifyCode.getCode();
		session.setAttribute("v_code", code);
	}

	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
