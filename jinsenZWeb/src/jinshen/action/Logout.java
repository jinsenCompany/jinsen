package jinshen.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//用户注销
@WebServlet("/logout")
public class Logout extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取session域对象
		HttpSession session = request.getSession();
		//将对象从session域中移除
		session.removeAttribute("staff_id");
		session.removeAttribute("staff_name");
//		System.out.println("注销成功！");
		response.sendRedirect("login.jsp");
	}
}
