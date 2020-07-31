package jinshen.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinshen.bean.codejson;
import jinshen.bean.compareTree;
import jinshen.bean.workpage;
import jinshen.bean.workpageStatus;
import jinshen.dao.workpageDao;
import jinshen.daoimpl.workpageDaoImpl;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class pictureServlet
 */
@WebServlet("/pictureServlet")
public class pictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private workpageDao d;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pictureServlet() {
    	d = new workpageDaoImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		workpageDao wpd=new workpageDaoImpl();
		String sql="";
		ObjectMapper mapper = new ObjectMapper();
		if(action.equals("treecompare")) {
			double workid=Double.parseDouble(request.getParameter("workid"));
			String pic = request.getParameter("pic");
			String da = request.getParameter("da");
			
			
			compareTree t = new compareTree();
			//System.out.println(workid);
			t.setWorkid(workid);
			t.setFile(da);
			t.setPic(pic);
			
			int i = d.insertcompareTree(t);
			if(i>0) {
				   workpageStatus ws=new workpageStatus();
				   ws.setWorkidstatus(8);//货场管理员装车对比拍照状态为8
				   int wpp=wpd.updateWorkpagestatus1(workid,ws);
					System.out.println(wpp);
					if(wpp>0) {
						out.write("保存状态成功");
					}
			    else {
			    	out.print("保存状态失败");		
			    }
				out.print("保存图片成功");
			}
			response.sendRedirect("compareTree.jsp");
		}
		else if("writepage".equals(action))
        {
        	String mytype = request.getParameter("type");
        	if(mytype.equals("all"))
        	{
        		sql="select c.workid,c.pic,c.file from comparetree as c JOIN workpage_status on c.workid=workpage_status.workid";
        		List<compareTree> work=d.findcomparet(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//未通过
        	else if(mytype.equals("notpass"))
        	{
        		sql="select comparetree.workid,comparetree.pic,comparetree.file from comparetree JOIN workpage_status on comparetree.workid=workpage_status.workid WHERE workpage_status.workid_status=10";
        		List<compareTree> work=d.findcomparet(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//通过
        	else if(mytype.equals("pass"))
        	{
        		sql="select comparetree.workid,comparetree.pic,comparetree.file from comparetree JOIN workpage_status on comparetree.workid=workpage_status.workid WHERE workpage_status.workid_status=9";
        		List<compareTree> work=d.findcomparet(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//未审核
        	else if(mytype.equals("notshenhe"))
        	{
        		sql="select comparetree.workid,comparetree.pic,comparetree.file from comparetree JOIN workpage_status on comparetree.workid=workpage_status.workid WHERE workpage_status.workid_status=8";
        		List<compareTree> work=d.findcomparet(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//删除
        	else if(mytype.equals("alldelete"))
        	{
        		String mygroup = request.getParameter("workid");
            	JSONArray json=JSONArray.fromObject(mygroup); 
            	double each=0;
            	for(int i=0;i<json.length();i++)
            	{
            		each=Double.parseDouble(json.getString(i));
            		 sql="delete from comparetree where workid="+each+"";
            		 int j=wpd.updateWork(sql);
            		 if(j==0)
            		 {
            			 out.print("删除失败！");
            			 break;
            		 }
            	}
        	}
        }
        //暂时没用
    	else if("searchstatus".equals(action))
        {
        	sql="select workid,cutNum,cutSite,surveyor from workpage where surveyor like '0'";
        	List<codejson> work=wpd.findcodeJson(sql);
        	mapper.writeValue(response.getWriter(), work);
        }
    	else if(action.equals("deletework"))
		 {
    		double workid=Double.parseDouble(request.getParameter("workid"));
				workpageDaoImpl ac=new workpageDaoImpl();
       		   boolean b=ac.delete_work(workid);
	    		mapper.writeValue(response.getWriter(),b);
		 }
	}

}
