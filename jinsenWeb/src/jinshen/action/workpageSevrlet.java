package jinshen.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinshen.bean.Laowu;
import jinshen.bean.codejson;
import jinshen.bean.compareTree;
import jinshen.bean.compareVolume;
import jinshen.bean.cutnumStatus;
import jinshen.bean.cutnumfeedback;
import jinshen.bean.inyard;
import jinshen.bean.managesdatecard;
import jinshen.bean.tree;
import jinshen.bean.volume;
import jinshen.bean.workpage;
import jinshen.bean.workpageStatus;
import jinshen.bean.yardManage;
import jinshen.dao.cutnumDao;
import jinshen.dao.treeDao;
import jinshen.dao.volumeDao;
import jinshen.dao.workpageDao;
import jinshen.daoimpl.cutnumDaoImpl;
import jinshen.daoimpl.treeDaoImpl;
import jinshen.daoimpl.volumeDaoImpl;
import jinshen.daoimpl.workpageDaoImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class workpageSevrlet
 */
@WebServlet("/workpageSevrlet")
public class workpageSevrlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public workpageSevrlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
        HttpSession session=request.getSession();
        String sql="";
        treeDao trd=new treeDaoImpl();
        cutnumDao cnd=new cutnumDaoImpl();
        workpageDao wpd=new workpageDaoImpl();
        ObjectMapper mapper = new ObjectMapper();
        session.setAttribute("staff_id", "123");  //登录得员工id
        if("addWorkpage".equals(action)) {
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String cutNum = request.getParameter("cutnum");
        	String cutdate=request.getParameter("cutdate");
        	String cutSite=request.getParameter("cutsite");
        	String checkSite=request.getParameter("checksite");
        	String carNumber=request.getParameter("carnumber");
        	//String gpsinfo=request.getParameter("gpsinfo");
        	String forester=request.getParameter("forester");
        	//String loadphoto=request.getParameter("loadphoto");
        	//double batchNum=Double.parseDouble(request.getParameter("batchNum"));
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d = null;
			try {
				d = format.parse(cutdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date cuttime = new java.sql.Date(d.getTime());
			sql="SELECT SUM(inyard.tolstere) AS tolstere1 FROM inyard WHERE cutNum='"+cutNum+"'";//从inyard数据库中选取材积
			compareVolume compareVolume=trd.findVolume(sql);//inyard输入的材积库存材积
			double volumet=(float)compareVolume.getTolstere();//相同采伐证累加的材积
			double tolstereal=volumet;//进入inyard中的材积
			sql="SELECT statusVolume from cutnum_status JOIN cutnum ON cutnum.cutnumid=cutnum_status.cutnumid WHERE cutnum='"+cutNum+"'";
			compareVolume compareVolumecut=trd.findVolume(sql);//
			double volumetcut=(float)compareVolumecut.getTolstere();//从采伐证才选择的材积
			if(tolstereal<(0.95*volumetcut))
			{
			workpage cp=new workpage();
			cp.setWorkid(workid);
			cp.setCutNum(cutNum);
			cp.setCutdate(cuttime);
			cp.setCutSite(cutSite);
			cp.setCheckSite(checkSite);
			cp.setCarNumber(carNumber);
			//cp.setGpsinfo(gpsinfo);
			//cp.setLoadphoto(loadphoto);
			cp.setForester(forester);
			cp.setBatchNum(1);
		    sql = "select count(*) from workpage where workid="+cp.getWorkid()+"";
		    int flag=0;
		    double f=wpd.findcount(sql);
		    if(f==1) {
		    	flag=wpd.updateWorkPage(cp);
		    }
		    else {
		    	flag=wpd.addWorkPage(cp);
		    	if(flag>0) {
		    		String rejectreason="";
		    		workpageStatus ws=new workpageStatus();
		    		ws.setWorkid(workid);
		    		ws.setWorkidstatus(0);//工单状态，0：工单已申请未审核，1：信息中心第一次审核工单，2：进场之后信息中心签收照片第二次审核，3：审核成功，4：工单被退回，5：工单修改，6：工单成功
		    	    ws.setRejectreason(rejectreason);
		    	    int flagws=wpd.addWorkpageStatus(ws);
		    	    if(flagws>0) {
		    	    	out.print("插入工单状况成功");
		    	    }
		    	    else {
		    	    	out.print("插入工单状况失败");
		    	    }
		    	}
		    }
		    if(flag==1) {
	       		sql="select * from workpage where workid="+workid+"";
	       		workpage workpage=wpd.findCodeSingle(sql);
        		request.setAttribute("workpage", workpage);
    		    request.getRequestDispatcher("workpageUpdate.jsp").forward(request, response);
		    }
		    else
        	{
        		request.getRequestDispatcher("workpageAdd.jsp").forward(request, response);
        	}
			}
			else if((tolstereal>(0.95*volumetcut)) && (tolstereal<volumetcut)) {
				workpage cp=new workpage();
				cp.setWorkid(workid);
				cp.setCutNum(cutNum);
				cp.setCutdate(cuttime);
				cp.setCutSite(cutSite);
				cp.setCheckSite(checkSite);
				cp.setCarNumber(carNumber);
				//cp.setGpsinfo(gpsinfo);
				//cp.setLoadphoto(loadphoto);
				cp.setForester(forester);
				cp.setBatchNum(1);
			    sql = "select count(*) from workpage where workid="+cp.getWorkid()+"";
			    int flag=0;
			    double f=wpd.findcount(sql);
			    if(f==1) {
			    	flag=wpd.updateWorkPage(cp);
			    }
			    else {
			    	flag=wpd.addWorkPage(cp);
			    	if(flag>0) {
			    		String rejectreason="";
			    		workpageStatus ws=new workpageStatus();
			    		ws.setWorkid(workid);
			    		ws.setWorkidstatus(0);//工单状态，0：工单已申请未审核，1：信息中心第一次审核工单，2：进场之后信息中心签收照片第二次审核，3：审核成功，4：工单被退回，5：工单修改，6：工单成功
			    	    ws.setRejectreason(rejectreason);
			    	    int flagws=wpd.addWorkpageStatus(ws);
			    	    if(flagws>0) {
			    	    	out.print("插入工单状况成功");
			    	    }
			    	    else {
			    	    	out.print("插入工单状况失败");
			    	    }
			    	}
			    }
			    if(flag==1) {
		       		sql="select * from workpage where workid="+workid+"";
		       		workpage workpage=wpd.findCodeSingle(sql);
	        		request.setAttribute("workpage", workpage);
	    		    request.getRequestDispatcher("workpageUpdate.jsp").forward(request, response);
	    		    out.print("输入材积已经超过本砍伐证的蓄留材积95%");
			    }
			    else
	        	{
	        		request.getRequestDispatcher("workpageAdd.jsp").forward(request, response);
	        	}
			}
			else {
				sql="select cutnumid from cutnum WHERE cutnum='"+cutNum+"'";
				cutnumStatus cd=cnd.findCutnumStatus(sql);
				double cutnumid=cd.getCutnumid();
				cutnumStatus cs=new cutnumStatus();
				cs.setStatus(10);
				int flagS=cnd.updateCutnumStatus(cs,cutnumid);
				if(flagS>0) {
				out.write("采伐证已锁定，输入材积已经超过本砍伐证的蓄留材积");
				}
				else
					out.write("输入材积已经超过本砍伐证的蓄留材积");
			}
        }
        else if("updateWorkpage".equals(action))
        {
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	//System.out.println("...." +workid + "...");
        	String cutNum = request.getParameter("cutnum");
        	String cutdate=request.getParameter("cutdate");
        	String cutSite=request.getParameter("cutsite");
        	String checkSite=request.getParameter("checksite");
        	String carNumber=request.getParameter("carnumber");
        	String gpsinfo=request.getParameter("gpsinfo");
        	String forester=request.getParameter("forester");
        	String loadphoto=request.getParameter("loadphoto");
        	//double batchNum=Double.parseDouble(request.getParameter("batchNum"));
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d = null;
			try {
				d = format.parse(cutdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date cuttime = new java.sql.Date(d.getTime()); 
			workpage cp=new workpage();
			cp.setWorkid(workid);
			cp.setCutNum(cutNum);
			cp.setCutdate(cuttime);
			cp.setCutSite(cutSite);
			cp.setCheckSite(checkSite);
			cp.setCarNumber(carNumber);
			cp.setGpsinfo(gpsinfo);
			cp.setLoadphoto(loadphoto);
			cp.setForester(forester);
			cp.setBatchNum(1);
		    sql = "select count(*) from workpage where workid="+cp.getWorkid()+"";
		    int flag=0;
		    double f=wpd.findcount(sql);
        	if(f==1) {
    		flag=wpd.updateWorkPage(cp);
    		/*sql="select count(*) from codestatus where workid="+workid+" and status='back'";
    		double ff=wpd.findcount(sql);
    		if(ff>0)
    		{
    		sql="update codestatus set status='again' where workid="+workid+"";
    		flag=wpd.updateWork(sql);
    		}*/
    	    }
    	   else
    	   {
    	      flag=wpd.addWorkPage(cp);
    	   }
    	   if(flag==1)
    	   {
    		  sql="select * from workpage where workid="+workid+"";
    		  workpage workpage=wpd.findCodeSingle(sql);
    		  request.setAttribute("workpage", workpage);
		      request.getRequestDispatcher("workpageUpdate.jsp").forward(request, response);
    	   }
    	   else {
    		   sql = "select * from workpage where workid="+workid+"";
    		   workpage workpage = wpd.findCodeSingle(sql);
    		   request.setAttribute("workpage",workpage);
    		   request.getRequestDispatcher("workpageUpdate.jsp").forward(request, response);
    	   }
        }
        else if("mypass".equals(action))
        {
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	double surveyor=Double.parseDouble((String)session.getAttribute("surveyor"));
        	//看这里，此处缺少用户权限得审核
        	sql="delete from codestatus where workid="+workid+"";
        	int i=wpd.delWorkPage(sql);
        	sql="update workpage set surveyor="+surveyor+" where workid="+workid+"";
        	i=wpd.updateWork(sql);
        	if(i==1)
        	{
        		out.print("审核成功！");
        	}
        	else
        	{
        		out.print("失败！");
        	}
        }
        else if("allpass".equals(action))
        {
        	double mygroup=Double.parseDouble(request.getParameter("workid"));
        	JSONArray json=JSONArray.fromObject(mygroup); 
        	double each=0;
        	for(int i=0;i<json.length();i++)
        	{
        		 each=Double.parseDouble(json.getString(i));
        		 double surveyor=Double.parseDouble((String)session.getAttribute("surveyor"));
        		 sql="update workpage set surveyor="+surveyor+" where workid="+each+"";
        		 int j=wpd.updateWork(sql);
        		 sql="delete from codestatus where workid="+each+"";
             	int k=wpd.delWorkPage(sql);
        		 if(j==0)
        		 {
        			 out.print("更新失败！");
        			 break;
        		 }
        	}
        	out.print("操作成功");
        }
        else if("allback".equals(action))
        {
        	double mygroup=Double.parseDouble(request.getParameter("workid"));
        	JSONArray json=JSONArray.fromObject(mygroup); 
        	double each=0;
        	int j=-1;
        	for(int i=0;i<json.length();i++)
        	{
        		 each=json.getDouble(i);
        		 sql="select count(*) from codestatus where workid="+each+" and status='again'";
         		double ff=wpd.findcount(sql);
         		if(ff>0)
         		{
         		sql="update codestatus set status='back' where workid="+each+"";
         		int flag=wpd.updateWork(sql);
         		}
         		else {
        		 sql="insert into codestatus values("+each+",'back')";
        		 j=wpd.addCodeStatus(sql);
         		}
        		if(j==0)
        		 {
        			 out.print("该码单已经被退回");
        			 break;
        		 }
        	}
        	out.print("退回成功");
        }
       /*else if("alldelete".equals(action))
        {
        	double mygroup=Double.parseDouble(request.getParameter("workid"));
        	JSONArray json=JSONArray.fromObject(mygroup); 
        	double each=0;
        	for(int i=0;i<json.length();i++)
        	{
        		 each=json.getDouble(i);
        		 sql="delete from workpage where workid="+each+"";
        		 int j=wpd.updateWork(sql);
        		 if(j==0)
        		 {
        			 out.print("删除失败！");
        			 break;
        		 }
        	}
        }*/
        else if("alldelete".equals(action))
    	{
    		sql="select workid,cutNum,cutSite,surveyor from workpage where surveyor like '0' or workid in (select workid from codestatus)";
        	List<codejson> work=wpd.findcodeJson(sql);
        	mapper.writeValue(response.getWriter(), work);
    	}
        else if("alldelete1".equals(action))
        {
        	String mygroup = request.getParameter("workid");
        	//workpage ac=new workpage();
        	sql="delete from workpage where workid="+mygroup+"";
        	int i=wpd.delWorkPage(sql);
        	//System.out.println("...." +i + "...");
        	ObjectMapper mapper1=new ObjectMapper();
    		mapper1.writeValue(response.getWriter(),i);
    		int flag=0;
    		if(i<0)
     		{
    		sql="update codestatus set status='delete' where workid="+mygroup+"";
        	flag=wpd.updateWork(sql);
     		}
     		else {
    		 sql="insert into codestatus values("+mygroup+",'delete')";
    		 flag=wpd.addCodeStatus(sql);
     		}
        }
        /*工单状态，0：工单已申请未审核，1：信息中心第一次审核工单通过(进入货场管理信息中心第二次审核)，
         *2：信息中心一次未通过，3：一次审核未通过伐区监管员补交材料，4：检尺员填写木材进场信息
         *5：进入货场之后信息中心签收照片第二次审核通过，6：进场未通过审核
          *7：二次未通过货场检尺员补交材料，8：货场管理员提交拍装车照片，9：信息中心审核通过，10：信息中心审核不通过
        */
        else if("writepage".equals(action))
        {
        	String mytype = request.getParameter("type");
        	if(mytype.equals("all"))
        	{
        		sql="select workid,cutNum,cutdate,cutSite,forester from workpage";
            	List<codejson> work=wpd.findcodeJson(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//未通过
        	else if(mytype.equals("notpass"))
        	{
        		sql="select w.workid,w.cutNum,w.cutdate,w.cutSite,w.forester from workpage as w JOIN workpage_status on w.workid=workpage_status.workid WHERE workpage_status.workid_status=2";
            	List<codejson> work=wpd.findcodeJson(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//通过
        	else if(mytype.equals("pass"))
        	{
        		sql="select w.workid,w.cutNum,w.cutdate,w.cutSite,w.forester from workpage as w JOIN workpage_status on w.workid=workpage_status.workid WHERE workpage_status.workid_status=1 or workpage_status.workid_status>=4";
            	List<codejson> work=wpd.findcodeJson(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//未审核
        	else if(mytype.equals("notshenhe"))
        	{
        		sql="select w.workid,w.cutNum,w.cutdate,w.cutSite,w.forester from workpage as w JOIN workpage_status on w.workid=workpage_status.workid WHERE workpage_status.workid_status=0";
            	List<codejson> work=wpd.findcodeJson(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//审核后补充材料
        	else if(mytype.equals("buchong"))
        	{
        		sql="select w.workid,w.cutNum,w.cutdate,w.cutSite,w.forester from workpage as w JOIN workpage_status on w.workid=workpage_status.workid WHERE workpage_status.workid_status=3";
            	List<codejson> work=wpd.findcodeJson(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//通过信息中心第一次审核和第二次审核的工单
        	else if(mytype.equals("tongguo")) {
        		sql="select w.workid,w.cutNum,w.cutdate,w.cutSite,w.forester from workpage as w JOIN workpage_status on w.workid=workpage_status.workid WHERE workpage_status.workid_status=1 OR workpage_status.workid_status>4";
            	List<codejson> work=wpd.findcodeJson(sql);
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
            		 sql="delete from workpage where workid="+each+"";
            		 int j=wpd.updateWork(sql);
            		 if(j==0)
            		 {
            			 out.print("删除失败！");
            			 break;
            		 }
            	}
        	}
        }
        //信息中心审核采伐工单
    	else if(action.equals("single"))
    	{
    		String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select * from workpage where workid="+workid+"";
    		workpage workpage=wpd.findCodeSingle(sql);
    		request.setAttribute("workpage", workpage);
		    request.getRequestDispatcher("workpageUpdateinfo1.jsp").forward(request, response);
    	}
      //信息中心审核工单
    	else if(action.equals("singlepass"))
    	{
    		String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select * from workpage where workid="+workid+"";
    		workpage workpage=wpd.findCodeSingle(sql);
    		sql="SELECT reject_reason FROM workpage_status join workpage on workpage_status.workid=workpage.workid WHERE workpage_status.workid="+workid+"";
    		workpageStatus workpageStatus=wpd.findWapStatus(sql);
    		request.setAttribute("workpage", workpage);
    		request.setAttribute("workpageStatus", workpageStatus);
		    request.getRequestDispatcher("workpageUpdateInfopass.jsp").forward(request, response);
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
        //信息中心对采伐工单进行第一次审核，审核通过或者不通过
    	else if("workpageyesorno".equals(action)) {
        	String mytype = request.getParameter("type");
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String rejectreason = request.getParameter("rejectreason");
        	//System.out.println("...."+rejectreason + "...");
        	if(mytype.equals("yes")) {
        		workpageStatus ws=new workpageStatus();
        		//ws.setWorkid(workid);
        		ws.setWorkidstatus(1);
        		//ws.setRejectreason(rejectreason);
        		int flag=wpd.updateWorkpagestatus(workid,ws);
        		if(flag>0){
        			out.print(flag);
        			}
        	}
        	else if(mytype.equals("not")) {
        		workpageStatus ws=new workpageStatus();
        		//ws.setWorkid(workid);
        		ws.setWorkidstatus(2);
        		ws.setRejectreason(rejectreason);
        		System.out.println("...."+rejectreason+ "...");
        		int flag=wpd.updateWorkpagestatusN(workid,ws);
        		if(flag>0){
        			out.print(flag);
        			}
        	}
        }
        //伐区监管员查看工单信息
    	else if(action.equals("faquworkpage"))
    	{
    		String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select * from workpage where workid="+workid+"";
    		workpage workpage=wpd.findCodeSingle(sql);
    		sql="SELECT reject_reason FROM workpage_status join workpage on workpage_status.workid=workpage.workid WHERE workpage_status.workid="+workid+"";
    		workpageStatus workpageStatus=wpd.findWapStatus(sql);
    		request.setAttribute("workpage", workpage);
    		request.setAttribute("workpageStatus", workpageStatus);
		    request.getRequestDispatcher("workpageUpdateFaqu.jsp").forward(request, response);
    	}
        //伐区监管员查看已通过工单信息
    	else if(action.equals("faquworkpagePass"))
    	{
    		String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select * from workpage where workid="+workid+"";
    		workpage workpage=wpd.findCodeSingle(sql);
    		sql="SELECT reject_reason FROM workpage_status join workpage on workpage_status.workid=workpage.workid WHERE workpage_status.workid="+workid+"";
    		workpageStatus workpageStatus=wpd.findWapStatus(sql);
    		request.setAttribute("workpage", workpage);
    		request.setAttribute("workpageStatus", workpageStatus);
		    request.getRequestDispatcher("workpageUpdateFaqupass.jsp").forward(request, response);
    	}
        //伐区监管员修改被信息中心退回的工单
    	else if("updateWorkpageFaqu".equals(action))
        {
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	System.out.println("...." +workid + "...");
        	String cutNum = request.getParameter("cutnum");
        	String cutdate=request.getParameter("cutdate");
        	String cutSite=request.getParameter("cutsite");
        	String checkSite=request.getParameter("checksite");
        	String carNumber=request.getParameter("carnumber");
        	String gpsinfo=request.getParameter("gpsinfo");
        	String forester=request.getParameter("forester");
        	String loadphoto=request.getParameter("loadphoto");
        	//double batchNum=Double.parseDouble(request.getParameter("batchNum"));
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d = null;
			try {
				d = format.parse(cutdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date cuttime = new java.sql.Date(d.getTime()); 
			workpage cp=new workpage();
			//cp.setWorkid(workid);
			cp.setCutNum(cutNum);
			cp.setCutdate(cuttime);
			cp.setCutSite(cutSite);
			cp.setCheckSite(checkSite);
			cp.setCarNumber(carNumber);
			cp.setGpsinfo(gpsinfo);
			cp.setLoadphoto(loadphoto);
			cp.setForester(forester);
			cp.setBatchNum(1);
		    sql = "select count(*) from workpage where workid="+cp.getWorkid()+"";
		    int flag=0;
		    double f=wpd.findcount(sql);
        	if(f>0) {
    		     flag=wpd.updateWorkPageFa(cp,workid);
    		if (flag>0)
    		{
    			workpageStatus ws=new workpageStatus();
    			ws.setWorkidstatus(3);
    			int flag1=wpd.updateWorkpagestatus1(workid, ws);
    			if(flag1>0) {
    				out.print("更新成功");
    			}
    		}
        	}
    	   else
    	   {
    	      flag=wpd.addWorkPage(cp);
    	   }
        }
        //信息中心审核伐区监管员修改工单信息
    	else if(action.equals("twosingle"))
    	{
    		String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select * from workpage where workid="+workid+"";
    		workpage workpage=wpd.findCodeSingle(sql);
    		sql="SELECT reject_reason FROM workpage_status join workpage on workpage_status.workid=workpage.workid WHERE workpage_status.workid="+workid+"";
    		workpageStatus workpageStatus=wpd.findWapStatus(sql);
    		request.setAttribute("workpage", workpage);
    		request.setAttribute("workpageStatus", workpageStatus);
		    request.getRequestDispatcher("workpageUpdateinfo2.jsp").forward(request, response);
    	}
        //信息中心第二次审核伐区管理员重新提交的工单
    	else if("workpageyesorno2".equals(action)) {
        	String mytype = request.getParameter("type");
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	//String rejectreason = request.getParameter("rejectreason");
        	//System.out.println("...."+rejectreason + "...");
        	if(mytype.equals("yes")) {
        		workpageStatus ws=new workpageStatus();
        		//ws.setWorkid(workid);
        		ws.setWorkidstatus(1);
        		//ws.setRejectreason(rejectreason);
        		int flag=wpd.updateWorkpagestatus1(workid,ws);
        		if(flag>0){
        			out.print(flag);
        			}
        	}
        	else if(mytype.equals("not")) {
        		workpageStatus ws=new workpageStatus();
        		//ws.setWorkid(workid);
        		ws.setWorkidstatus(2);
        		//ws.setRejectreason(rejectreason);
        		int flag=wpd.updateWorkpagestatus1(workid,ws);//只更新工单状态
        		if(flag>0){
        			out.print(flag);
        			}
        	}
        }
      //信息中心第三次审核货场管理员提交的照片
    	else if("workpageyesorno3".equals(action)) {
        	String mytype = request.getParameter("type");
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	if(mytype.equals("yes")) {
        		workpageStatus ws=new workpageStatus();
        		ws.setWorkidstatus(9);
        		int flag=wpd.updateWorkpagestatus1(workid,ws);
        		if(flag>0){
        			out.print(flag);
        			}
        	}
        	else if(mytype.equals("not")) {
        		workpageStatus ws=new workpageStatus();       		//ws.setWorkid(workid);
        		ws.setWorkidstatus(10);
        		int flag=wpd.updateWorkpagestatus1(workid,ws);//只更新工单状态
        		if(flag>0){
        			out.print(flag);
        			}
        	}
        }
        //检尺员，货场管理员查看审核通过的工单passworkpage.jsp
    	else if(action.equals("seetongguo"))
    	{
    		String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select * from workpage where workid="+workid+"";
    		workpage workpage=wpd.findCodeSingle(sql);
    		request.setAttribute("workpage", workpage);
		    request.getRequestDispatcher("workpageUpdate2.jsp").forward(request, response);
    	}
        //信息中心审核采伐工单
    	else if(action.equals("singlecomparetree"))
    	{
    		String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select * from comparetree where workid="+workid+"";
    		compareTree compareTree=wpd.findcompares(sql);
    		request.setAttribute("compareTree", compareTree);
		    request.getRequestDispatcher("compareTreeUpdate.jsp").forward(request, response);
    	}
    	else if(action.equals("yardAdd")) {
    		String yardname = request.getParameter("yardname");
        	String yardmanager=request.getParameter("yardmanager");
        	double yardvolume=Double.parseDouble(request.getParameter("yardvolume"));
        	String managephone = request.getParameter("managephone");
        	String remarks=request.getParameter("remarks");
        	yardManage cp=new yardManage();
        	cp.setYardname(yardname);
        	cp.setYardvolume(yardvolume);
        	cp.setYardmanager(yardmanager);
        	cp.setManagephone(managephone);
        	cp.setRemarks(remarks);
        	int flag=wpd.addyardmanage(cp);
        	if(flag>0) {
        		out.print("添加成功");
        	}
        	else {
        		out.print("添加失败");
        	}
    	}
        //在货场管理页面显示已添加货场管理信息
    	else if(action.equals("yardinfo")) {
    		sql="SELECT yardid,yardname,yardvolume,yardmanager,managephone from yardmanage";
        	List<yardManage> work=wpd.findyardmanage(sql);
        	mapper.writeValue(response.getWriter(), work);
    	}
        //储存施工方资料
    	else if(action.equals("managesdateCard")) {
    		String ownername = request.getParameter("ownername");
    		String sex = request.getParameter("sex");
    		String borndate = request.getParameter("borndate");
    		String idnumber = request.getParameter("idnumber");
    		String bornplace = request.getParameter("bornplace");
    		String enterprisename = request.getParameter("enterprisename");
    		String businesslicense = request.getParameter("businesslicense");
    		String address = request.getParameter("address");
    		String contact = request.getParameter("contact");
    		String engineeringbag = request.getParameter("engineeringbag");
    		String cutnum = request.getParameter("cutnum");
    		String quartel = request.getParameter("quartel");
    		String largeblock = request.getParameter("largeblock");
    		String smallblock = request.getParameter("smallblock");
    		double allarea = Double.parseDouble(request.getParameter("allarea"));
    		double totaloutwood = Double.parseDouble(request.getParameter("totaloutwood"));
    		int areapiece = Integer.parseInt(request.getParameter("areapiece"));
    		String signingtime = request.getParameter("signingtime");
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d = null;
			try {
				d = format.parse(signingtime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date signingdate = new java.sql.Date(d.getTime());
			String cooperationyear = request.getParameter("cooperationyear");
			String team = request.getParameter("team");
			String yeji = request.getParameter("yeji");
        	managesdatecard cp=new managesdatecard();
        	cp.setOwnername(ownername);
			cp.setSex(sex);
			cp.setBorndate(borndate);
			cp.setIdnumber(idnumber);
			cp.setBornplace(bornplace);
			cp.setEnterprisename(enterprisename);
			cp.setBusinesslicense(businesslicense);
			cp.setAddress(address);
			cp.setContact(contact);
			cp.setEngineeringbag(engineeringbag);
			cp.setCutnum(cutnum);
			cp.setQuartel(quartel);
			cp.setLargeblock(largeblock);
			cp.setSmallblock(smallblock);
			cp.setAllarea(allarea);
			cp.setTotaloutwood(totaloutwood);
			cp.setAreapiece(areapiece);
			cp.setSigningtime(signingdate);
			cp.setCooperationyear(cooperationyear);
			cp.setTeam(team);
			cp.setYeji(yeji);
        	int flag=wpd.addmanagesdatecard(cp);
        	if(flag>0) {
        		out.print("添加成功");
        	}
        	else {
        		out.print("添加失败");
        	}
    	}
        //在资料卡页面显示已添加货场管理信息
    	else if(action.equals("managesdateCardinfo")) {
    		sql="SELECT * from managesdatecard";
        	List<managesdatecard> mc=wpd.findmanagesdatecard(sql);
        	mapper.writeValue(response.getWriter(), mc);
    	}
        //显示已完成工单
    	else if(action.equals("finishWorkpage")) {
    		String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select * from workpage where workid="+workid+"";
       		workpage workpage=wpd.findCodeSingle(sql);
        	sql="select workid,cutNum,yarddate,cutSite,carNumber,yard,surveyor,toltree,tolstere,section from inyard where workid="+workid+"";
			inyard inyard=trd.findInSingle(sql);
			sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid="+workid+"";
			List<tree> tree=trd.findTree(sql);
			sql="select * from comparetree where workid="+workid+"";
    		compareTree compareTree=wpd.findcompares(sql);
    		request.setAttribute("workpage", workpage);
			request.setAttribute("inyard", inyard);
			request.setAttribute("tree", tree);
			request.setAttribute("compareTree", compareTree);
			request.getRequestDispatcher("finishWorkpage.jsp").forward(request, response);
    	}
      //通过工单号查询工单信息
        else if(action.equals("printworkpage")){
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	if (workid==0)
        	{
        		return;
        	}
        	sql="SELECT workid,cutNum,cutdate,cutSite from workpage where workid ='"+workid+"'";
        	List<workpage> cw=wpd.findWorkpageC(sql);
        	mapper.writeValue(response.getWriter(), cw);
        }
       //结束
        
       //工单跳转页面
        else if(action.equals("treebuyTable")){
        	String str=request.getParameter("workid");
        	str=str.replace("'", "");
        	double workid=Double.parseDouble(str);
        	//System.out.println("...."+number + "...");
        	//sql="SELECT m.workid,m.cutNum,m.cutdate,m.cutSite,n.treetype,n.tlong,n.tradius,n.num,n.tvolume from workpage as m JOIN tree as n ON m.workid=n.workid where n.workid='"+number+"'";
        	//sql="SELECT cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum where cutnum='"+str+"'";
        	//treebuy cw = wpd.findtreebuyTable(sql);
        	
        	sql="select workid,cutNum,cutdate,cutSite from workpage where workid="+workid+"";
    		workpage workpage=wpd.findCodeSingle1(sql);
    		
    		sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid="+workid+"";
			List<tree> tree=wpd.findTree(sql);
        	request.setAttribute("workid", workpage);
			request.setAttribute("tree", tree);

        	request.getRequestDispatcher("workpageTreeBuyTable.jsp").forward(request, response);	
        }
        
	}

}
