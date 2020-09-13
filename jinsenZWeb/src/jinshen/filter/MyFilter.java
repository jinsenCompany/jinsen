package jinshen.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//判断用户是否登录
@WebFilter({"/superManage.jsp","/managerP.jsp","/manageCutnumseeDelate.jsp","/manageCutnum.jsp",
	"/manageCutnumsee.jsp","/manageCutnumseeUse.jsp","/Surveyor.jsp","/passworkpage.jsp",
	"/treeoutCover.jsp","/forestP.jsp","/salaryper.jsp","/salaryContract.jsp","/salaryContractList.jsp",
	"/productowner.jsp","/productownerSee.jsp","/saleCalloutOrder.jsp","/saleCalloutOrdersee.jsp",
	"/saleCalloutOrderShenheModer.jsp","/outyardCostS.jsp","/treeoutPrice.jsp","/treeoutPriceTable.jsp",
	"/treeoutCodepage.jsp","/producetreeTableSalaryper.jsp","/treeoutTableSalayper.jsp",
	"/managesdatecard.jsp","/managersdatecardSee.jsp","/CutnumProjectpackage.jsp","/cutareaAllot.jsp",
	"/cutnumProjectpackageShenhe.jsp","/CutnumProjectpackageTable.jsp","/productPrice.jsp","/productPrice2.jsp","/productTreePrice.jsp",
	"/productPrice23.jsp","/productTreePriceTable.jsp","/manageCutnumProduced.jsp"})
public class MyFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	//根据session域中是否存在对象判断是否登录
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//获取session对象
		HttpSession session = request.getSession();
		String staff_id = (String)session.getAttribute("staff_id");
		if( staff_id != null) {
			arg2.doFilter(request, response);
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('请先登录！');");
			out.write("location.href='./login.jsp';");
			out.write("</script>");
			out.close();
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
