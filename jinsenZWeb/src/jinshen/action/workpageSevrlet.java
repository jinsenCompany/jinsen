package jinshen.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.common.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import jinshen.bean.CancellingStockTable;
import jinshen.bean.Laowu;
import jinshen.bean.codejson;
import jinshen.bean.compareTree;
import jinshen.bean.compareVolume;
import jinshen.bean.cutnum;
import jinshen.bean.cutnumStatus;
import jinshen.bean.cutnumfeedback;
import jinshen.bean.goodsYardCost;
import jinshen.bean.inyard;
import jinshen.bean.managesdatecard;
import jinshen.bean.producetree;
import jinshen.bean.projectpackage;
import jinshen.bean.singleworkid;
import jinshen.bean.surveyor;
import jinshen.bean.tree;
import jinshen.bean.treefile;
import jinshen.bean.volume;
import jinshen.bean.workpage;
import jinshen.bean.workpageStatus;
import jinshen.bean.worktree;
import jinshen.bean.yardManage;
import jinshen.bean.yezhangPrint;
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
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date cuttime=null;
        	try {
				cuttime = new java.sql.Timestamp(format.parse(cutdate).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	//实际采伐材积
			sql="SELECT SUM(inyard.tolstere) AS tolstere1 FROM inyard WHERE cutNum='"+cutNum+"'";//从inyard数据库中选取材积
			compareVolume compareVolume=trd.findVolume(sql);//inyard输入的材积库存材积
			double volumet=(float)compareVolume.getTolstere();//相同采伐证累加的材积
			double tolstereal=volumet;//进入inyard中的材积
			//System.out.print(tolstereal);
			//采伐证材积
			sql="SELECT statusVolume from cutnum_status JOIN cutnum ON cutnum.cutnumid=cutnum_status.cutnumid WHERE cutnum='"+cutNum+"'";
			compareVolume compareVolumecut=trd.findVolume(sql);//
			double volumetcut=(float)compareVolumecut.getTolstere();//从采伐证才选择的材积
			//采伐证状态
			sql="SELECT s.`status` from cutnum_status as s join cutnum as c on s.cutnumid=c.cutnumid WHERE c.cutnum='"+cutNum+"'";
			cutnumStatus stu=cnd.findCutnumStatus(sql);//查询采伐证状态
			int cutstatus=stu.getCutnumid();
			//System.out.print(cutstatus);
			sql="select count(*) from cutnum where cutnum='"+cutNum+"'";
			double f1=cnd.findcount(sql);
			if(f1>0 && cutstatus<10) 
			{
			if(tolstereal<(0.90*volumetcut))
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
		    		ws.setWorkidstatus(1);//(未审核是0，审核是成功是1)//工单状态，0：工单已申请未审核，1：信息中心第一次审核工单，2：进场之后信息中心签收照片第二次审核，3：审核成功，4：工单被退回，5：工单修改，6：工单成功
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
			else if((tolstereal>(0.90*volumetcut)) && (tolstereal<(0.95*volumetcut))) {
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
			    		ws.setWorkidstatus(1);//工单状态，0：工单已申请未审核，1：信息中心第一次审核工单，2：进场之后信息中心签收照片第二次审核，3：审核成功，4：工单被退回，5：工单修改，6：工单成功
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
//		       		sql="select * from workpage where workid="+workid+"";
//		       		workpage workpage=wpd.findCodeSingle(sql);
//	        		request.setAttribute("workpage", workpage);
//	    		    request.getRequestDispatcher("workpageUpdate.jsp").forward(request, response);
//	    		    out.print("输入材积已经超过本砍伐证的蓄留材积90%");
			    	out.print("添加成功,输入材积已经超过本采伐证的蓄留材积90%,2秒后返回录入界面<script>setTimeout(\"window.location.href ='workpageAdd.jsp';\", 2000);</script>");
			    }
			    else
	        	{
	        		request.getRequestDispatcher("workpageAdd.jsp").forward(request, response);
	        	}
			}
			else if((tolstereal>(0.95*volumetcut))) {
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
			    		ws.setWorkidstatus(1);//工单状态，0：工单已申请未审核，1：信息中心第一次审核工单，2：进场之后信息中心签收照片第二次审核，3：审核成功，4：工单被退回，5：工单修改，6：工单成功
			    	    ws.setRejectreason(rejectreason);
			    	    int flagws=wpd.addWorkpageStatus(ws);
			    	    /*if(flagws>0) {
			    	    	out.print("插入工单状况成功");
			    	    }
			    	    else {
			    	    	out.print("插入工单状况失败");
			    	    }*/
			    	}
			    }
			    if(flag==1) {
			    	DecimalFormat decimalFormat = new DecimalFormat("##.00%");
		 			double totalImplement1=(tolstereal)/(volumetcut);
		 			   //System.out.println("...."+totalImplement1+ "...");
		 			String totalImplement=decimalFormat.format(totalImplement1);
			    	out.printf("添加成功,输入材积已经超过本采伐证的蓄留材积为%s,3秒后返回录入界面<script>setTimeout(\"window.location.href ='workpageAdd.jsp';\", 3000);</script>",totalImplement);
			    }
			    else
	        	{
			    	out.print("添加失败");
	        	}
			}
			}
			else {
				out.print("没有该采伐证");
			}
        }
        //保存工单(带锁定的版本)
        else if("addWorkpage1".equals(action)) {
        	//System.out.print(request.getParameter("workid"));
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
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date cuttime=null;
        	try {
				cuttime = new java.sql.Timestamp(format.parse(cutdate).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	//实际采伐材积
			sql="SELECT SUM(inyard.tolstere) AS tolstere1 FROM inyard WHERE cutNum='"+cutNum+"'";//从inyard数据库中选取材积
			compareVolume compareVolume=trd.findVolume(sql);//inyard输入的材积库存材积
			double volumet=(float)compareVolume.getTolstere();//相同采伐证累加的材积
			double tolstereal=volumet;//进入inyard中的材积
			//System.out.print(tolstereal);
			//采伐证材积
			sql="SELECT statusVolume from cutnum_status JOIN cutnum ON cutnum.cutnumid=cutnum_status.cutnumid WHERE cutnum='"+cutNum+"'";
			compareVolume compareVolumecut=trd.findVolume(sql);//
			double volumetcut=(float)compareVolumecut.getTolstere();//从采伐证才选择的材积
			//采伐证状态
			sql="SELECT s.`status` from cutnum_status as s join cutnum as c on s.cutnumid=c.cutnumid WHERE c.cutnum='"+cutNum+"'";
			cutnumStatus stu=cnd.findCutnumStatus(sql);//查询采伐证状态
			int cutstatus=stu.getCutnumid();
			System.out.print(cutstatus);
			sql="select count(*) from cutnum where cutnum='"+cutNum+"'";
			double f1=cnd.findcount(sql);
			if(f1>0 && cutstatus<10) 
			{
//			sql="SELECT statusVolume from cutnum_status JOIN cutnum ON cutnum.cutnumid=cutnum_status.cutnumid WHERE cutnum='"+cutNum+"'";
//			compareVolume compareVolumecut=trd.findVolume(sql);//
//			double volumetcut=(float)compareVolumecut.getTolstere();//从采伐证才选择的材积
			if(tolstereal<(0.90*volumetcut))
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
		    		ws.setWorkidstatus(1);//(未审核是0，审核是成功是1)//工单状态，0：工单已申请未审核，1：信息中心第一次审核工单，2：进场之后信息中心签收照片第二次审核，3：审核成功，4：工单被退回，5：工单修改，6：工单成功
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
			else if((tolstereal>(0.90*volumetcut)) && (tolstereal<(0.95*volumetcut))) {
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
			    		ws.setWorkidstatus(1);//工单状态，0：工单已申请未审核，1：信息中心第一次审核工单，2：进场之后信息中心签收照片第二次审核，3：审核成功，4：工单被退回，5：工单修改，6：工单成功
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
//		       		sql="select * from workpage where workid="+workid+"";
//		       		workpage workpage=wpd.findCodeSingle(sql);
//	        		request.setAttribute("workpage", workpage);
//	    		    request.getRequestDispatcher("workpageUpdate.jsp").forward(request, response);
//	    		    out.print("输入材积已经超过本砍伐证的蓄留材积90%");
			    	out.print("添加成功,输入材积已经超过本采伐证的蓄留材积90%,2秒后返回录入界面<script>setTimeout(\"window.location.href ='workpageAdd.jsp';\", 2000);</script>");
			    }
			    else
	        	{
	        		request.getRequestDispatcher("workpageAdd.jsp").forward(request, response);
	        	}
			}
			else if((tolstereal>(0.95*volumetcut))) {
				sql="select cutnumid from cutnum WHERE cutnum='"+cutNum+"'";
				cutnumStatus cd=cnd.findCutnumStatus(sql);
				double cutnumid=cd.getCutnumid();
				cutnumStatus cs=new cutnumStatus();
				cs.setStatus(10);//采伐证锁定
				int flagS=cnd.updateCutnumStatus(cs,cutnumid);
				if(flagS>0) {
				//out.write("采伐证已锁定，输入材积已经超过本采伐证的蓄留材积");
			   out.print("采伐证已锁定，输入材积已经超过本采伐证的蓄留材积,2秒后返回录入界面<script>setTimeout(\"window.location.href ='workpageAdd.jsp';\", 2000);</script>");
				}
			}
			}
			else if(f1>0 && cutstatus==14) {
				if((tolstereal>0) && (tolstereal<(1.95*volumetcut))) {
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
				    		ws.setWorkidstatus(1);//工单状态，0：工单已申请未审核，1：信息中心第一次审核工单，2：进场之后信息中心签收照片第二次审核，3：审核成功，4：工单被退回，5：工单修改，6：工单成功
				    	    ws.setRejectreason(rejectreason);
				    	    int flagws=wpd.addWorkpageStatus(ws);
				    	    /*if(flagws>0) {
				    	    	out.print("插入工单状况成功");
				    	    }
				    	    else {
				    	    	out.print("插入工单状况失败");
				    	    }*/
				    	}
				    }
				    if(flag==1) {
//			       		sql="select * from workpage where workid="+workid+"";
//			       		workpage workpage=wpd.findCodeSingle(sql);
//		        		request.setAttribute("workpage", workpage);
//		    		    request.getRequestDispatcher("workpageUpdate.jsp").forward(request, response);
//		    		    out.print("输入材积已经超过本砍伐证的蓄留材积100%");
				    	DecimalFormat decimalFormat = new DecimalFormat("##.00%");
			 			double totalImplement1=(tolstereal)/(volumetcut);
			 			   //System.out.println("...."+totalImplement1+ "...");
			 			String totalImplement=decimalFormat.format(totalImplement1);
				    	out.printf("添加成功,输入材积已经超过本采伐证的蓄留材积为%s,2秒后返回录入界面<script>setTimeout(\"window.location.href ='workpageAdd.jsp';\", 2000);</script>",totalImplement);
				    }
				    else
		        	{
				    	out.print("添加失败");
		        	}
				}
				else if((tolstereal>(1.95*volumetcut))) {
					sql="select cutnumid from cutnum WHERE cutnum='"+cutNum+"'";
					cutnumStatus cd=cnd.findCutnumStatus(sql);
					double cutnumid=cd.getCutnumid();
					cutnumStatus cs=new cutnumStatus();
					cs.setStatus(13);//采伐证锁定不可解锁
					int flagS=cnd.updateCutnumStatus(cs,cutnumid);
					if(flagS>0) {
					out.write("采伐证已锁定，输入材积已经超过本采伐证的200%蓄留材积");
					}
				}
			}
			else {
				out.print("没有该采伐证");
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
        	sql="delete from workpage where workid='"+mygroup+"'";
        	int i=wpd.delWorkPage(sql);
        	/*sql="delete from inyard where workid='"+mygroup+"'";
        	int ii=wpd.delWorkPage(sql);
        	sql="delete from tree where workid='"+mygroup+"'";
        	int iii=wpd.delWorkPage(sql);
        	int iiii=0;
        	if(i>0 && ii>0 && iii>0)
        	{
        		iiii=1;
        	}*/
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
         *2：信息中心一次未通过，3：一次审核未通过伐区监管员补交材料，
         *4：货场管理员提交拍装车照片，5：信息中心审核照片通过，6：信息中心审核照片不通过
          *7：检尺员填写木材进场信息，8：信息中心审核木材数据通过，9：信息中心审核木材数据未通过，10：检尺员补交木材数据
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
        		sql="select w.workid,w.cutNum,w.cutdate,w.cutSite,w.checkSite,w.carNumber,w.forester from workpage as w JOIN workpage_status on w.workid=workpage_status.workid WHERE workpage_status.workid_status=1 OR workpage_status.workid_status>4 and workpage_status.workid_status<8";
            	List<workpage> work=wpd.findsinglework(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//通过信息中心第一次审核通过的工单
        	else if(mytype.equals("tongguo1")) {
        		sql="select w.workid,w.cutNum,w.cutdate,w.cutSite,w.checkSite,w.carNumber,w.forester from workpage as w JOIN workpage_status on w.workid=workpage_status.workid WHERE workpage_status.workid_status=1";
            	List<workpage> work=wpd.findsinglework(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//通过信息中心第二次审核的工单
        	else if(mytype.equals("tongtree")) {
        		sql="select w.workid,w.cutNum,w.cutdate,w.cutSite,w.checkSite,w.carNumber,w.forester from workpage as w JOIN workpage_status on w.workid=workpage_status.workid WHERE workpage_status.workid_status=5";
            	List<workpage> work=wpd.findsinglework(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//全部通过通过
        	else if(mytype.equals("passall"))
        	{
        		sql="select w.workid,w.cutNum,w.cutdate,w.cutSite,w.forester from workpage as w JOIN workpage_status on w.workid=workpage_status.workid WHERE workpage_status.workid_status=8 or workpage_status.workid_status>=20";
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
        //经理查看工单信息
    	else if(action.equals("faquworkpageM"))
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
		    request.getRequestDispatcher("workpageUpdateFaquManager.jsp").forward(request, response);
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
    				//out.print("更新成功");
    				String message="更新成功";
    				request.setAttribute("message", message);
    			    request.getRequestDispatcher("message.jsp").forward(request, response);
    			}
    		}
        	}
    	   else
    	   {
    	      flag=wpd.addWorkPage(cp);
    	   }
        }
      //伐区经理修改被信息中心退回的工单
    	else if("updateWorkpageFaquM".equals(action))
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
    				//out.print("更新成功");
    				String message="更新成功";
    				request.setAttribute("message", message);
    			    request.getRequestDispatcher("message.jsp").forward(request, response);
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
        		ws.setWorkidstatus(5);//信息中心审核照片通过
        		int flag=wpd.updateWorkpagestatus1(workid,ws);
        		if(flag>0){
        			out.print(flag);
        			}
        	}
        	else if(mytype.equals("not")) {
        		workpageStatus ws=new workpageStatus();       		//ws.setWorkid(workid);
        		ws.setWorkidstatus(6);//审核不通过
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
    		sql="select * from workpage where workid="+workid+"";
       		workpage workpage=wpd.findCodeSingle(sql);
        	sql="select workid,cutNum,yarddate,cutSite,carNumber,yard,surveyor,toltree,tolstere,section from inyard where workid="+workid+"";
			inyard inyard=trd.findInSingle(sql);
			sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid="+workid+"";
			List<tree> tree=trd.findTree(sql);
    		request.setAttribute("workpage", workpage);
			request.setAttribute("inyard", inyard);
			request.setAttribute("tree", tree);
    		request.setAttribute("compareTree", compareTree);
		    request.getRequestDispatcher("compareTreeUpdate.jsp").forward(request, response);
    	}
        //添加货场信息
    	else if(action.equals("yardAdd")) {
    		String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
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
        		for(int i=0;i<id;i++)
            	{
            		JSONArray s=jb.getJSONArray(String.valueOf(i));
            		yardManage y=new yardManage();
            		y.setYardname(yardname);
            		y.setSection(s.getString(0));
            		int flagy=wpd.addyardSection(y);
            		out.print(flagy);
        	}
        	}
        	else {
        		out.print(flag);
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
			Date dd=null;
			try {
				d = format.parse(signingtime);
				dd = format.parse(borndate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date signingdate = new java.sql.Date(d.getTime());
			java.sql.Date borntime = new java.sql.Date(dd.getTime());
			String cooperationyear = request.getParameter("cooperationyear");
			String team = request.getParameter("team");
			String yeji = request.getParameter("yeji");
        	managesdatecard cp=new managesdatecard();
        	cp.setOwnername(ownername);
			cp.setSex(sex);
			cp.setBorndate(borntime);
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
        //上传施工方资料
    	else if(action.equals("managesdateCard1")) {
    		managesdatecard cp = new managesdatecard();//采伐证信息
        	String ownername="";
        	double volume=0;
        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = format1.format(new Date());
			///System.out.println(createtime);
			Timestamp Time = Timestamp.valueOf(createtime);//创建时间
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        	Date d = null;
			Date dd = null;
			Date ddd=null;
        	// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
    		String savePath = this.getServletContext().getRealPath("/WEB-INF/managesCardfile");//施工方资料
    		//得到文件访问的相对路径
    		String readPath = "../WEB-INF/managesCardfile/";
    		File file = new File(savePath);
    		// 判断上传文件的保存目录是否存在
    		if (!file.exists() || !file.isDirectory()) {
    			System.out.println(savePath + "目录不存在，需要创建");
    			// 创建目录
    			file.mkdir();
    		}
    		// 消息提示
    		String message = "";
    		try {
    			// 使用Apache文件上传组件处理文件上传步骤：
    			// 1、创建一个DiskFileItemFactory工厂
    			DiskFileItemFactory factory = new DiskFileItemFactory();
    			// 2、创建一个文件上传解析器
    			ServletFileUpload upload = new ServletFileUpload(factory);
    			// 解决上传文件名的中文乱码
    			upload.setHeaderEncoding("UTF-8");
    			// 3、判断提交上来的数据是否是上传表单的数据
    			if (!ServletFileUpload.isMultipartContent(request)) {
    				// 按照传统方式获取数据
    				return;
    			}
    			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
    			List<FileItem> list = upload.parseRequest(request);
    			System.out.println(list.size());
    			for (FileItem item : list) {
    				// 如果fileitem中封装的是普通输入项的数据
    				if (item.isFormField()) {
    					String name = item.getFieldName();//普通输入项username
    					if("ownername".equals(name)) {
    						cp.setOwnername(item.getString("UTF-8"));
    						ownername=item.getString("UTF-8");
    					}
    					else if("sex".equals(name)) {
    						cp.setSex(item.getString("UTF-8"));
    					}
    					else if("borndate".equals(name)) {
    						String borndate=item.getString("UTF-8");
    						try {
    							d = format.parse(borndate);
    						} catch (ParseException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} 
    						java.sql.Date borntime = new java.sql.Date(d.getTime()); 
    						cp.setBorndate(borntime);
    					}
    					else if("idnumber".equals(name)) {
    						cp.setIdnumber(item.getString("UTF-8"));
    					}
    					else if("bornplace".equals(name)) {
    						cp.setBornplace(item.getString("UTF-8"));
    					}
    					else if("enterprisename".equals(name)) {
    						cp.setEnterprisename(item.getString("UTF-8"));
    					}
    					else if("businesslicense".equals(name)) {
    						cp.setBusinesslicense(item.getString("UTF-8"));
    					}
    					else if("address".equals(name)) {
    						cp.setAddress(item.getString("UTF-8"));
    					}
    					else if("contact".equals(name)) {
    						cp.setContact(item.getString("UTF-8"));
    					}
    					/*else if("engineeringbag".equals(name)) {
    						cp.setEngineeringbag(item.getString("UTF-8"));
    					}
    					else if("cutnum".equals(name)) {
    						cp.setCutnum(item.getString("UTF-8"));
    					}
    					else if("quartel".equals(name)) {
    						cp.setQuartel(item.getString("UTF-8"));
    					}
    					else if("largeblock".equals(name)) {
    						cp.setLargeblock(item.getString("UTF-8"));
    					}
    					else if("smallblock".equals(name)) {
    						cp.setSmallblock(item.getString("UTF-8"));
    					}*/
    					else if("allarea".equals(name)) {
    						cp.setAllarea(Double.parseDouble(item.getString("UTF-8")));
    					}
    					else if("totaloutwood".equals(name)) {
    						cp.setTotaloutwood(Double.parseDouble(item.getString("UTF-8")));
    					}
    					/*else if("areapiece".equals(name)) {
    						cp.setAreapiece(Integer.parseInt(item.getString("UTF-8")));
    					}*/
    					/*else if("signingtime".equals(name)) {
    						String signingtime=item.getString("UTF-8");
    						try {
    							d = format.parse(signingtime);
    						} catch (ParseException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} 
    						java.sql.Date signingt = new java.sql.Date(d.getTime()); 
    						cp.setSigningtime(signingt);;
    					}
    					else if("cooperationyear".equals(name)) {
    						cp.setCooperationyear(item.getString("UTF-8"));
    					}
    					else if("team".equals(name)) {
    						cp.setTeam(item.getString("UTF-8"));
    					}
    					else if("yeji".equals(name)) {
    						String yeji=item.getString("UTF-8");
    						cp.setYeji(yeji);
    					}*/
    					// 解决普通输入项的数据的中文乱码问题
    					String value = item.getString("UTF-8");
    					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
    					System.out.println(name + "=" + value);
    				} else {// 如果fileitem中封装的是上传文件
    						// 得到上传的文件名称，
    					String filename = item.getName();
    					System.out.println(filename);
    					if (filename == null || filename.trim().equals("")) {
    						continue;
    					}
    					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
    					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
    					filename = filename.substring(filename.lastIndexOf("\\") + 1);
    					//request.setAttribute("filename", filename);
    					// 获取item中的上传文件的输入流
    					InputStream in = item.getInputStream();
    					// 创建一个文件输出流
    					FileOutputStream outt = new FileOutputStream(savePath + "\\" + filename);
    					//输出文件保存路径
    					System.out.println("savePath:" + savePath + "\\" +filename);
    					//显示文件读取的相对路径
    					readPath = readPath+ filename;
    					cp.setManagepath(readPath);
    					System.out.println("readPath :"+ readPath);
    					// 创建一个缓冲区
    					byte buffer[] = new byte[1024];
    					// 判断输入流中的数据是否已经读完的标识
    					int len = 0;
    					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
    					while ((len = in.read(buffer)) > 0) {
    						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
    						outt.write(buffer, 0, len);
    					}
    					// 关闭输入流
    					in.close();
    					// 关闭输出流
    					outt.close();
    					// 删除处理文件上传时生成的临时文件
    					item.delete();
    					message = "文件上传成功！";
    				}
    			}
    		} catch (Exception e) {
    			message = "文件上传失败！";
    			e.printStackTrace();
    		}
    		cp.setSigningtime(Time);
    		int flag=wpd.addmanagesdatecard(cp);
    		out.print(flag);
        	/*if(flag>0) {
        		out.print("添加成功");
        	}
        	else {
        		out.print("添加失败");
        	}*/
    	}
        //在资料卡页面显示已添加货场管理信息
    	else if(action.equals("managesdateCardinfo")) {
    		sql="SELECT cardid,ownername,idnumber,allarea,totaloutwood from managesdatecard";
        	List<managesdatecard> mc=wpd.findmanagesdatecard(sql);
        	mapper.writeValue(response.getWriter(), mc);
    	}
        //查询施工方已经签订合同时间
    	else if(action.equals("searchMnage")) {
    		String ownername=request.getParameter("ownername");
    		ArrayList<String> Cutarea=new ArrayList<>();
    		ArrayList<Double> Volume=new ArrayList<>();
        	Map map=new HashMap();
        		sql="SELECT projpackageStarttime,proj_package_Name from proj_package  WHERE contractionSide='"+ownername+"'";
        		List<projectpackage> project=wpd.seachProject(sql);//显示工程包信息
        		int j=project.size();
        		for(int i=0;i<j;i++) {
        			sql="SELECT sum(cutarea) as cutarea,sum(volume) as volume from cutnum WHERE proj_package_Name='"+project.get(i).getProjectPackageName()+"'";
        			cutnum c=wpd.searchVolume(sql);
        			Cutarea.add(c.getCutarea());
        			Volume.add(c.getVolume());
        		}
        		map.put("projectpackage", project);
        		map.put("Cutarea", Cutarea);
        		map.put("Volume", Volume);
        		mapper.writeValue(response.getWriter(), map);
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
    		String comparefile = compareTree.getPic();
    		if(String.valueOf(comparefile)=="null")
        	{
    			comparefile="";
        	}
        	else {
        		comparefile = comparefile.substring(comparefile.lastIndexOf("/") + 1);//去掉储存路径斜杠
        	}
    		//comparefile = comparefile.substring(comparefile.lastIndexOf("/") + 1);
    		request.setAttribute("comparefile", comparefile);
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
        	
        	sql="select c.company,w.workid,w.cutNum,w.cutdate,w.cutSite,w.checkSite from workpage w join cutnum c on w.cutNum=c.cutnum  where workid='"+workid+"'";
    		workpage workpage=wpd.findCodeSingle1(sql);
    		
    		sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid='"+workid+"'";
			List<tree> tree=wpd.findTree(sql);
        	request.setAttribute("workid", workpage);
			request.setAttribute("tree", tree);       	
			request.getRequestDispatcher("workpageTreeBuyTable.jsp").forward(request, response);	
			//FileInputStream file = new FileInputStream(new File("f:\\workpageTreeBuyTable.xls"));
			FileInputStream file = new FileInputStream(new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 9.0\\webapps\\jinsenZ\\\\WEB-INF\\PrintTreeBuy\\workpageTreeBuyTable.xls"));
			HSSFWorkbook workbook =new HSSFWorkbook(file);
      //   	HSSFSheet sheet = workbook.createSheet("Sample sheet445");
 	        HSSFSheet sheet = workbook.getSheet("Sheet1");       	
 	        int a=sheet.getFirstRowNum()+80;//从80列开始
 	        int b=sheet.getLastRowNum();
 	       try {
				System.out.print("已经创建好文件，进入到sheet1");
				System.out.print(b);
               Row row = sheet.getRow(80);//获取第80行
//				// 这个判断很必要  确保下面cell操作顺利执行
		          if(row == null){
		              row = sheet.createRow(80);
		          }
				 for (int j=a;j<b;j++)
				 {
					 if(sheet.getRow(j)!=null)
					 {
					 sheet.removeRow(sheet.getRow(j));
					 //sheet.setSelected(true);
					 sheet.setActive(true);  
					 }
				 }
        				       				
        				  //Row row = sheet.createRow(0);
        	                Cell cell = row.createCell(7);
        	                cell.setCellValue(workpage.getCompany());//发货单位
        	                
							Cell cell1 = row.createCell(8);
							cell1.setCellValue(workpage.getWorkid());
							
						
							Cell cell2 = row.createCell(9);
							cell2.setCellValue(workpage.getCutNum());
							
							Cell cell3 = row.createCell(10);
							cell3.setCellValue(String.valueOf(workpage.getCutdate()));
							
							/*
							 * Cell cell4 = row.createCell(11); cell4.setCellValue(workpage.getCutSite());
							 */
							Cell cell4 = row.createCell(11);
							cell4.setCellValue(workpage.getCheckSite()); //货场
							
								/* System.out.print(tree.size()); */														
								 for (int i=0;i<tree.size();i++) {				        	            
									    Row rowx = sheet.createRow(i+81);
				        	            Cell cella = rowx.createCell(7);
				        	            cella.setCellValue((String) tree.get(i).getTreetype());
				        	            Cell cellb = rowx.createCell(8);
				        	            cellb.setCellValue((double) tree.get(i).getTlong());
				        	            Cell cellc = rowx.createCell(9);
				        	            cellc.setCellValue((double) tree.get(i).getTradius());
				        	            Cell celld = rowx.createCell(10);
				        	            celld.setCellValue((double) tree.get(i).getNum());
				        	            Cell celle = rowx.createCell(11);
				        	            celle.setCellValue((double) tree.get(i).getTvolume());								  
								 }
							
        	     file.close();
        		
        	    //FileOutputStream out1=new FileOutputStream(new File("f:\\workpageTreeBuyTable.xls"));
        	    FileOutputStream out1=new FileOutputStream(new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 9.0\\webapps\\jinsenZ\\WEB-INF\\PrintTreeBuy\\workpageTreeBuyTable.xls"));
        	    //String path = "C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 9.0\\webapps\\jinsenZ\\WEB-INF\\PrintTreeBuy" + "\\" + fileName;
        	    workbook.write(out1);
				/*
				 * sheet = workbook.getSheetAt(0); Iterator<Row> rowIte = sheet.iterator();
				 * while(rowIte.hasNext()){ rowIte.next(); rowIte.remove(); }
				 * System.out.println("已删除完毕");
				 */
        	    file.close();
        	    out1.close();
        	    System.out.println("Excel written successfully..");
        	   
        	} catch (FileNotFoundException e) {
        	    e.printStackTrace();
        	} catch (IOException e) {
        	    e.printStackTrace();
        	}
	
        }
        //先检尺元首页显示工单信息
        else if(action.equals("finishworkP")) {
        	String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select * from workpage where workid="+workid+"";
       		workpage workpage=wpd.findCodeSingle(sql);
       		request.setAttribute("workpage", workpage);
			request.getRequestDispatcher("treein.jsp").forward(request, response);
        }
      //先检尺元首页显示工单信息
        else if(action.equals("finishworkPD")) {
        	String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select * from workpage where workid="+workid+"";
       		workpage workpage=wpd.findCodeSingle(sql);
       		request.setAttribute("workpage", workpage);
			request.getRequestDispatcher("treeinDirector.jsp").forward(request, response);
        }
        //在compareTree.jsp页面显示工单和树材信息
        else if(action.equals("finishWaTree")) {
        	String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select * from workpage where workid="+workid+"";
       		workpage workpage=wpd.findCodeSingle(sql);
        	//sql="select workid,cutNum,yarddate,cutSite,carNumber,yard,surveyor,toltree,tolstere,section from inyard where workid="+workid+"";
			//inyard inyard=trd.findInSingle(sql);
			//sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid="+workid+"";
			//List<tree> tree=trd.findTree(sql);
    		request.setAttribute("workpage", workpage);
			//request.setAttribute("inyard", inyard);
			//request.setAttribute("tree", tree);
			request.getRequestDispatcher("compareTree.jsp").forward(request, response);
        }
      //在compareTreeDirector.jsp页面显示工单和树材信息
        else if(action.equals("finishWaTreeD")) {
        	String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select * from workpage where workid="+workid+"";
       		workpage workpage=wpd.findCodeSingle(sql);
        	sql="select workid,cutNum,yarddate,cutSite,carNumber,yard,surveyor,toltree,tolstere,section from inyard where workid="+workid+"";
			inyard inyard=trd.findInSingle(sql);
			sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid="+workid+"";
			List<tree> tree=trd.findTree(sql);
    		request.setAttribute("workpage", workpage);
			request.setAttribute("inyard", inyard);
			request.setAttribute("tree", tree);
			request.getRequestDispatcher("compareTreeDirector.jsp").forward(request, response);
        }
        else if("goodsYardCost".equals(action)) {
        	double gouJiZD = Double.parseDouble(request.getParameter("gouJiZD"));
        	double renGongZD = Double.parseDouble(request.getParameter("renGongZD"));
        	double zhuangCheGZ = Double.parseDouble(request.getParameter("zhuangCheGZ"));
        	double changDiWH = Double.parseDouble(request.getParameter("changDiWH"));
        	double changDiZJ = Double.parseDouble(request.getParameter("changDiZJ"));
        	double kanHuGZ = Double.parseDouble(request.getParameter("kanHuGZ"));
        	double dianFei = Double.parseDouble(request.getParameter("dianFei"));

        	double sheBeiWH = Double.parseDouble(request.getParameter("sheBeiWH"));
        	double yunFei = Double.parseDouble(request.getParameter("yunFei"));
        	double surveyorFee = Double.parseDouble(request.getParameter("surveyorFee"));
        	double totolCost = Double.parseDouble(request.getParameter("totolCost"));
        	String luRuDate = request.getParameter("LuRuDate");
        	//System.out.print(sheBeiWH);
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Date d = null;
			try {
				d = format.parse(luRuDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date luRuDatee = new java.sql.Date(d.getTime()); 
			goodsYardCost cp=new goodsYardCost();
            cp.setGouJiZD(gouJiZD);
            cp.setRenGongZD(renGongZD);
            cp.setZhuangCheGZ(zhuangCheGZ);
            cp.setChangDiWH(changDiWH);
            cp.setChangDiZJ(changDiZJ);
            cp.setKanHuGZ(kanHuGZ);
            cp.setDianFei(dianFei);
            cp.setSheBeiWH(sheBeiWH);
            cp.setYunFei(yunFei);
            cp.setSurveyorFee(surveyorFee);
			cp.setTotolCost(totolCost);
            cp.setLuRuDate(luRuDatee);

			int flag=wpd.addgoodsYardCost(cp);

			if(flag>0) {
			
				out.print("保存成功");
			}
			else {
        		out.print("保存失败");
        	}
        }
        //费用盘点
        else if("feeAdd".equals(action)) {
        	String LuRuDate = request.getParameter("LuRuDate");
        	String yard = request.getParameter("yard");
        	String luruperson = request.getParameter("luruperson");
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date luRuDatee=null;
        	try {
        		luRuDatee = new java.sql.Timestamp(format.parse(LuRuDate).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	for(int i=0;i<id;i++)
        	{
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		goodsYardCost g=new goodsYardCost();
        		g.setYard(yard);
        		g.setLuRuDate(luRuDatee);
        		g.setFeetype(s.getString(0));
        		g.setNum(Integer.parseInt(s.getString(1)));
        		g.setUnit(s.getString(2));
        		g.setUnitprice(Double.parseDouble(s.getString(3)));
        		g.setPrice(Double.parseDouble(s.getString(4)));
        		g.setRemarks(s.getString(5));
            	g.setLuruperson(luruperson);
            	int flag=wpd.addgoodsYardfee(g);
            	out.print(flag);
        	}
        }
        //货场费用台账
        else if(action.equals("goodsyardList")) {
        	sql="SELECT * FROM goodyard_cost";//货场费用
        	List<goodsYardCost> goodsYardCost=wpd.findgoodsYard(sql);
        	mapper.writeValue(response.getWriter(), goodsYardCost);
        }
        //退库单
        else if("addCancellingStockTable".equals(action)) {
        	//double workid=Double.parseDouble(request.getParameter("workid"));
        	String cancellingStockDate = request.getParameter("cancellingStockDate");
        	System.out.print(cancellingStockDate);
        	String cancellingStockSite=request.getParameter("cancellingStockSite");
        	String treetype=request.getParameter("treetype");
        	String tlong=request.getParameter("tlong");
        	String tradius=request.getParameter("tradius");
        	//String gpsinfo=request.getParameter("gpsinfo");
        	int num=Integer.parseInt(request.getParameter("num"));
        	//String loadphoto=request.getParameter("loadphoto");
        	//double batchNum=Double.parseDouble(request.getParameter("batchNum"));
        	String tvolume=request.getParameter("tvolume");
        	String cancellingStockReason=request.getParameter("cancellingStockReason");
        	String path=request.getParameter("path");
    
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d = null;
			try {
				d = format.parse(cancellingStockDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date cuttime = new java.sql.Date(d.getTime());
			sql="";//从inyard数据库中选取材积 
			CancellingStockTable cs=new CancellingStockTable();
			cs.setCancellingStockDate(cuttime);
			cs.setCancellingStockSite(cancellingStockSite);
			cs.setTreetype(treetype);
			cs.setTlong(tlong);
			cs.setTradius(tradius);
			cs.setNum(num);
			cs.setTvolume(tvolume);
			cs.setCancellingStockReason(cancellingStockReason);
			cs.setPath(path);
			int flag=wpd.addCancellingStockTable(cs);
			if(flag>0) {
			out.print("保存成功");}
			else {
				out.print("保存失败");
			}
	}
        //退库单
        else if(action.equals("addCancellingStockTable1")) {
        	CancellingStockTable cs=new CancellingStockTable();//退库单信息
        	String cutNum="";
        	double volume=0;
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        	Date d = null;
			Date dd = null;
			Date ddd=null;
			String treetype1="";
        	String tlong1="";
        	String tradius1="";
        	int num1=0;
        	String tvolume1="";
			ArrayList<String> tree=new ArrayList<>();
        	ArrayList<String> tl=new ArrayList<>();
        	ArrayList<String> tr=new ArrayList<>();
        	ArrayList<Integer> num=new ArrayList<>();
        	ArrayList<String> tv=new ArrayList<>();
        	// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
    		String savePath = this.getServletContext().getRealPath("/WEB-INF/cancelfile");
    		//得到文件访问的相对路径
    		String readPath = "../WEB-INF/cancelfile/";
    		File file = new File(savePath);
    		// 判断上传文件的保存目录是否存在
    		if (!file.exists() || !file.isDirectory()) {
    			System.out.println(savePath + "目录不存在，需要创建");
    			// 创建目录
    			file.mkdir();
    		}
    		// 消息提示
    		String message = "";
    		try {
    			// 使用Apache文件上传组件处理文件上传步骤：
    			// 1、创建一个DiskFileItemFactory工厂
    			DiskFileItemFactory factory = new DiskFileItemFactory();
    			// 2、创建一个文件上传解析器
    			ServletFileUpload upload = new ServletFileUpload(factory);
    			// 解决上传文件名的中文乱码
    			upload.setHeaderEncoding("UTF-8");
    			// 3、判断提交上来的数据是否是上传表单的数据
    			if (!ServletFileUpload.isMultipartContent(request)) {
    				// 按照传统方式获取数据
    				return;
    			}
    			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
    			List<FileItem> list = upload.parseRequest(request);
    			System.out.println(list.size());
    			for (FileItem item : list) {
    				// 如果fileitem中封装的是普通输入项的数据
    				if (item.isFormField()) {
    					String name = item.getFieldName();//普通输入项username
    					if("cancellingStockDate".equals(name)) {
    						String sttime=item.getString("UTF-8");
    						try {
    							d = format.parse(sttime);
    						} catch (ParseException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} 
    						java.sql.Date begin = new java.sql.Date(d.getTime()); 
    						cs.setCancellingStockDate(begin);
    					}
    					else if("cancellingStockSite".equals(name)) {
    						cs.setCancellingStockSite(item.getString("UTF-8"));
    					}
    					else if("cancellingStockReason".equals(name)) {
    						cs.setCancellingStockReason(item.getString("UTF-8"));
    					}
    					else if("treetype".equals(name)) {
    						treetype1=item.getString("UTF-8");
    						tree.add(treetype1);
    						//cs.setTreetype(item.getString("UTF-8"));
    					}
    					else if("tlong".equals(name)) {
    						tlong1=item.getString("UTF-8");
    						tl.add(tlong1);
    						//cs.setTlong(item.getString("UTF-8"));
    					}
    					else if("tradius".equals(name)) {
    						tradius1=item.getString("UTF-8");
    						tr.add(tradius1);
    						//cs.setTradius(item.getString("UTF-8"));
    					}
    					else if("num".equals(name)) {
    						num1=Integer.parseInt(item.getString("UTF-8"));
    						num.add(num1);
    						//cs.setNum(Integer.parseInt(item.getString("UTF-8")));
    					}
    					else if("tvolume".equals(name)) {
    						tvolume1=item.getString("UTF-8");
    						tv.add(tvolume1);
    						//cs.setTvolume(item.getString("UTF-8"));
    					}
    					// 解决普通输入项的数据的中文乱码问题
    					String value = item.getString("UTF-8");
    					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
    					System.out.println(name + "=" + value);
    				} else {// 如果fileitem中封装的是上传文件
    						// 得到上传的文件名称，
    					String filename = item.getName();
    					System.out.println(filename);
    					if (filename == null || filename.trim().equals("")) {
    						continue;
    					}
    					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
    					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
    					filename = filename.substring(filename.lastIndexOf("\\") + 1);
    					//request.setAttribute("filename", filename);
    					// 获取item中的上传文件的输入流
    					InputStream in = item.getInputStream();
    					// 创建一个文件输出流
    					FileOutputStream outt = new FileOutputStream(savePath + "\\" + filename);
    					//输出文件保存路径
    					System.out.println("savePath:" + savePath + "\\" +filename);
    					//显示文件读取的相对路径
    					readPath = readPath+ filename;
    					cs.setPath(readPath);
    					System.out.println("readPath :"+ readPath);
    					// 创建一个缓冲区
    					byte buffer[] = new byte[1024];
    					// 判断输入流中的数据是否已经读完的标识
    					int len = 0;
    					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
    					while ((len = in.read(buffer)) > 0) {
    						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
    						outt.write(buffer, 0, len);
    					}
    					// 关闭输入流
    					in.close();
    					// 关闭输出流
    					outt.close();
    					// 删除处理文件上传时生成的临时文件
    					item.delete();
    					message = "文件上传成功！";
    				}
    			}
    		} catch (Exception e) {
    			message = "文件上传失败！";
    			e.printStackTrace();
    		}
    		int lt=tree.size();
    		int flag=0;
    		for(int i=0;i<lt;i++)
    		{
    			cs.setTreetype(tree.get(i));
    			cs.setTlong(tl.get(i));
    			cs.setTradius(tr.get(i));
    			cs.setNum(num.get(i));
    			cs.setTvolume(tv.get(i));
    		flag=wpd.addCancellingStockTable(cs);
    		}
    		if(flag>0) {
				out.print("添加成功,2秒后返回录入界面<script>setTimeout(\"window.location.href ='cancellingStocksTable.jsp';\", 2000);</script>");}
    			//out.print("\"添加成功,2秒后返回录入界面<script>setTimeout(\\\"window.location.href ='cancellingStocksTable.jsp';\\\", 2000);</script>\"");}
    			else {
    				out.print("\"添加失败,2秒后返回录入界面<script>setTimeout(\"window.location.href ='cancellingStocksTable.jsp';\", 2000);</script>\"");
    			}
        }
      //添加检尺员人员名单
    	else if("surveyorAdd".equals(action)) {
    		String rebate = request.getParameter("newtree");
     	    int id=Integer.parseInt(request.getParameter("id"));
     	    JSONObject jb = JSONObject.fromObject(rebate);
     	   for(int i=0;i<id;i++)
       	  {
       		JSONArray s=jb.getJSONArray(String.valueOf(i));
       		surveyor su=new surveyor();
       		 su.setSurveyor(s.getString(0));
           	 int flagt=wpd.addSurveyor(su);
           	 out.print(flagt);
    	}
    	}
        //显示检尺员信息
    	else if("surveyorList".equals(action)) {
    		sql="SELECT * from surveyor_info";
    		List<surveyor> surv=wpd.findSurveyor(sql);
    		mapper.writeValue(response.getWriter(), surv);
    	}
        //删除检尺员信息
    	else if("deteSurv".equals(action))
        {
        	String mygroup = request.getParameter("surveyorid");
        	//workpage ac=new workpage();
        	sql="delete from surveyor_info where surveyorid="+mygroup+"";
        	int i=wpd.delWorkPage(sql);
        	//System.out.println("...." +i + "...");
        	ObjectMapper mapper1=new ObjectMapper();
    		mapper1.writeValue(response.getWriter(),i);
        }
        //检尺野账数据
    	else if(action.equals("jianchiYzhang")) {
    		String str=request.getParameter("workid");
    		str=str.replace("'", "");
    		double workid=Double.parseDouble(str);
    		sql="select * from workpage where workid="+workid+"";
       		workpage workpage=wpd.findCodeSingle(sql);
        	sql="select workid,cutNum,yarddate,cutSite,carNumber,yard,surveyor,toltree,tolstere,section from inyard where workid="+workid+"";
			inyard inyard=trd.findInSingle(sql);
			sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid="+workid+"";
			List<tree> tree=trd.findTree(sql);
			String cutnum=workpage.getCutNum();
			sql="select * FROM cutnum WHERE cutnum='"+cutnum+"'";
			cutnum cutn = cnd.findCodeSingle(sql);
			int proj_package_id=cutn.getProjectid();
			sql="select * from proj_package where proj_Package_id="+proj_package_id+"";
        	projectpackage projectpackage=cnd.findprojectpackage(sql);
        	sql="SELECT treein_file from treein_file WHERE workid='"+str+"'";
        	treefile treef=wpd.findtreefile(sql);
        	String tfile=String.valueOf(treef.getTreefile());
        	System.out.print(tfile);
        	if(!tfile.equals("null"))
        	{
        		tfile = tfile.substring(tfile.lastIndexOf("/") + 1);
        	}
        	else
        	{
        		tfile="";
        	}
        	request.setAttribute("treef", treef);
    		request.setAttribute("tfile", tfile);
        	
    		request.setAttribute("cutnum", cutn);
    		request.setAttribute("workpage", workpage);
			request.setAttribute("inyard", inyard);
			request.setAttribute("tree", tree);
			request.setAttribute("projectpackage", projectpackage);
			request.getRequestDispatcher("treeinWild.jsp").forward(request, response);
			/*FileInputStream file = new FileInputStream(new File("e:\\new5.xls"));
			XSSFWorkbook workbook =new XSSFWorkbook(file);
      //   	HSSFSheet sheet = workbook.createSheet("Sample sheet445");
 	        XSSFSheet sheet = workbook.getSheet("Sheet2");       	

        	try {
        		 /*Object[][] bookData = {
        	                {"Head First Java", "Kathy Serria", 79},
        	                {"Effective Java", "Joshua Bloch", 36},
        	                {"Clean Code", "Robert martin", 42},
        	                {"Thinking in Java", "Bruce Eckel", 35},
        	        };*/
        	 
        	            /*Row row = sheet.createRow(0);
        	                Cell cell = row.createCell(0);
        	                    cell.setCellValue(cutn.getCompany());
        	        
        	        for (int i=0;i<tree.size();i++) {
        	            row = sheet.createRow(i);
        	             
        	            for (int j=0;j<5;j++) {
        	                cell = row.createCell(j);
        	                    cell.setCellValue((String) tree.get(j).getTreetype());
        	                    cell.setCellValue((double) tree.get(j).getTlong());
        	                    cell.setCellValue((double) tree.get(j).getTradius());
        	                    cell.setCellValue((double) tree.get(j).getNum());
        	                    cell.setCellValue((double) tree.get(j).getTvolume());
        	                }
        	             
        	        } 
        	       
        	     file.close();
        		
        	    FileOutputStream out1=new FileOutputStream(new File("e:\\new5.xls"));
        	    workbook.write(out1);
        	    out1.close();
        	    System.out.println("Excel written successfully..");

        	} catch (FileNotFoundException e) {
        	    e.printStackTrace();
        	} catch (IOException e) {
        	    e.printStackTrace();
        	}*/
    	}
        //显示施工方资料
    	else if(action.equals("watchManagecard")) {
    		String str=request.getParameter("ownername");
    		str=str.replace("'", "");
    		NumberFormat nf = NumberFormat.getNumberInstance();
    		 nf.setMaximumFractionDigits(2);
    		 ArrayList<String> Cutareav=new ArrayList<>();
    		 ArrayList<Double> Volumev=new ArrayList<>();
    		 List<cutnum> cutnum = new ArrayList<cutnum>();
    		double Cutarea=0;
    		double Volume=0;
    		sql="SELECT * from managesdatecard WHERE ownername='"+str+"'";
    		managesdatecard managesdatecard=wpd.findmanagecardAll(sql);
        		sql="SELECT projpackageStarttime,proj_package_Name from proj_package  WHERE contractionSide='"+str+"'";
        		List<projectpackage> project=wpd.seachProject(sql);//显示工程包信息
        		int j=project.size();
        		for(int i=0;i<j;i++) {
        			sql="SELECT sum(cutarea) as cutarea,sum(volume) as volume from cutnum WHERE proj_package_Name='"+project.get(i).getProjectPackageName()+"'";
        			cutnum c=wpd.searchVolume(sql);
        			Cutarea+=Double.parseDouble(c.getCutarea());
        			Cutareav.add(c.getCutarea());
        			Volume+=c.getVolume();
        			Volumev.add(c.getVolume());
        		}
        		System.out.print(Cutareav);
        		String Cutarea1=nf.format(Cutarea);
        		String Volume1=nf.format(Volume);
        		request.setAttribute("projectpackage", project);
        		request.setAttribute("Cutarea", Cutarea1);
    			request.setAttribute("Volume", Volume1);
    			request.setAttribute("Cutareav", Cutareav);
    			request.setAttribute("Volumev", Volumev);
    			request.setAttribute("managesdatecard", managesdatecard);
    			request.getRequestDispatcher("managesdatecardUpdate.jsp").forward(request, response);
    	}
        //更新施工方资料
    	else if(action.equals("UpdatemanagesdateCard")) {
    		managesdatecard cp = new managesdatecard();//采伐证信息
        	String ownername="";
        	double volume=0;
        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = format1.format(new Date());
			///System.out.println(createtime);
			Timestamp Time = Timestamp.valueOf(createtime);//创建时间
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        	Date d = null;
			Date dd = null;
			Date ddd=null;
        	// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
    		String savePath = this.getServletContext().getRealPath("/WEB-INF/managesCardfile");//施工方资料
    		//得到文件访问的相对路径
    		String readPath = "../WEB-INF/managesCardfile/";
    		File file = new File(savePath);
    		// 判断上传文件的保存目录是否存在
    		if (!file.exists() || !file.isDirectory()) {
    			System.out.println(savePath + "目录不存在，需要创建");
    			// 创建目录
    			file.mkdir();
    		}
    		// 消息提示
    		String message = "";
    		try {
    			// 使用Apache文件上传组件处理文件上传步骤：
    			// 1、创建一个DiskFileItemFactory工厂
    			DiskFileItemFactory factory = new DiskFileItemFactory();
    			// 2、创建一个文件上传解析器
    			ServletFileUpload upload = new ServletFileUpload(factory);
    			// 解决上传文件名的中文乱码
    			upload.setHeaderEncoding("UTF-8");
    			// 3、判断提交上来的数据是否是上传表单的数据
    			if (!ServletFileUpload.isMultipartContent(request)) {
    				// 按照传统方式获取数据
    				return;
    			}
    			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
    			List<FileItem> list = upload.parseRequest(request);
    			System.out.println(list.size());
    			for (FileItem item : list) {
    				// 如果fileitem中封装的是普通输入项的数据
    				if (item.isFormField()) {
    					String name = item.getFieldName();//普通输入项username
    					if("ownername".equals(name)) {
    						cp.setOwnername(item.getString("UTF-8"));
    						ownername=item.getString("UTF-8");
    					}
    					else if("sex".equals(name)) {
    						cp.setSex(item.getString("UTF-8"));
    					}
    					else if("borndate".equals(name)) {
    						String borndate=item.getString("UTF-8");
    						try {
    							d = format.parse(borndate);
    						} catch (ParseException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} 
    						java.sql.Date borntime = new java.sql.Date(d.getTime()); 
    						cp.setBorndate(borntime);
    					}
    					else if("idnumber".equals(name)) {
    						cp.setIdnumber(item.getString("UTF-8"));
    					}
    					else if("bornplace".equals(name)) {
    						cp.setBornplace(item.getString("UTF-8"));
    					}
    					else if("enterprisename".equals(name)) {
    						cp.setEnterprisename(item.getString("UTF-8"));
    					}
    					else if("businesslicense".equals(name)) {
    						cp.setBusinesslicense(item.getString("UTF-8"));
    					}
    					else if("address".equals(name)) {
    						cp.setAddress(item.getString("UTF-8"));
    					}
    					else if("contact".equals(name)) {
    						cp.setContact(item.getString("UTF-8"));
    					}
    					else if("allarea".equals(name)) {
    						cp.setAllarea(Double.parseDouble(item.getString("UTF-8")));
    					}
    					else if("totaloutwood".equals(name)) {
    						cp.setTotaloutwood(Double.parseDouble(item.getString("UTF-8")));
    					}
    					// 解决普通输入项的数据的中文乱码问题
    					String value = item.getString("UTF-8");
    					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
    					System.out.println(name + "=" + value);
    				} else {// 如果fileitem中封装的是上传文件
    						// 得到上传的文件名称，
    					String filename = item.getName();
    					System.out.println(filename);
    					if (filename == null || filename.trim().equals("")) {
    						continue;
    					}
    					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
    					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
    					filename = filename.substring(filename.lastIndexOf("\\") + 1);
    					//request.setAttribute("filename", filename);
    					// 获取item中的上传文件的输入流
    					InputStream in = item.getInputStream();
    					// 创建一个文件输出流
    					FileOutputStream outt = new FileOutputStream(savePath + "\\" + filename);
    					//输出文件保存路径
    					System.out.println("savePath:" + savePath + "\\" +filename);
    					//显示文件读取的相对路径
    					readPath = readPath+ filename;
    					cp.setManagepath(readPath);
    					System.out.println("readPath :"+ readPath);
    					// 创建一个缓冲区
    					byte buffer[] = new byte[1024];
    					// 判断输入流中的数据是否已经读完的标识
    					int len = 0;
    					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
    					while ((len = in.read(buffer)) > 0) {
    						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
    						outt.write(buffer, 0, len);
    					}
    					// 关闭输入流
    					in.close();
    					// 关闭输出流
    					outt.close();
    					// 删除处理文件上传时生成的临时文件
    					item.delete();
    					message = "文件上传成功！";
    				}
    			}
    		} catch (Exception e) {
    			message = "文件上传失败！";
    			e.printStackTrace();
    		}
    		cp.setSigningtime(Time);
    		sql="SELECT COUNT(*) from managesdatecard WHERE ownername='"+ownername+"'";
    		double f=wpd.findcount(sql);
    		sql="SELECT cardid from managesdatecard WHERE ownername='"+ownername+"'";
    		managesdatecard mana=wpd.findmanagecardId(sql);
    		int Cardid=mana.getCardid();
    		int flag=0;
    		if(f>0) {
    			flag=wpd.Updatemanagesdatecard(cp, Cardid);
    		}
    		else 
    		{
    		flag=wpd.addmanagesdatecard(cp);
    		
    		}
    		out.print(flag);
        	/*if(flag>0) {
        		out.print("添加成功");
        	}
        	else {
        		out.print("添加失败");
        	}*/
    	}
        //在工单页面输如采伐证显示采伐证信息
    	else if("searchWorkpageCut".equals(action)) {
    		String cutnum=request.getParameter("cutnum");
    		sql="select company,certificatenum,cutnum,cutaddress,cutvillage,quartel,largeblock,smallblock,volume,proj_package_Name from cutnum WHERE cutnum='"+cutnum+"'";
          	//sql="select * from cutnum WHERE cutnum='"+cutnum+"'";
    		List<cutnum> cutn=cnd.findCutnumPid(sql);
    		String proj_package_Name="";
    		if(cutn.size()>0) {
    			for(int i=0;i<cutn.size();i++) {
			proj_package_Name=cutn.get(i).getProjectPackageName();
			}
			System.out.print(proj_package_Name);
			}
			sql="select * from proj_package where proj_Package_Name='"+proj_package_Name+"'";
			List<projectpackage> cw=cnd.seeprojectpackag(sql);
        	Map map=new HashMap();
        		map.put("cutnum", cutn);
        		map.put("projectpackage", cw);
        		mapper.writeValue(response.getWriter(), map);
    	}
    	else if("findyardfeed".equals(action)) {
    		String yardname=request.getParameter("yardname");
        	String yeart=request.getParameter("yeart");
        	yardname=yardname.replace("'", "");
        	yeart=yeart.replace("'", "");
        	sql="SELECT yard,year(luRuDate) as tyeart,month(luRuDate) as tmonth,feetype,sum(num) as tnum,sum(price) as tprice,luruperson \r\n" + 
        			"from goodyard_cost WHERE yard='"+yardname+"' and year(luRuDate)='"+yeart+"' GROUP BY yard,feetype,unitprice,tyeart,tmonth,luruperson";
        	List<goodsYardCost> goodsYardCost=wpd.findgoodsYardDet(sql);
        	request.setAttribute("goodsYardCost", goodsYardCost);
			request.getRequestDispatcher("goodYardCostDet.jsp").forward(request, response);
    	}
      //检尺野账打印
    	else if(action.equals("passall2"))
    	{
    		String timeStart = request.getParameter("timeStart");
         	String timeEnd = request.getParameter("timeEnd");
         	String projectPackageName = request.getParameter("projectPackageName");
         	String cutnum = request.getParameter("cutnum");
         	String yard = request.getParameter("yard");
         	String workid = request.getParameter("workid");
         	String toltree = request.getParameter("toltree");
         	String tolstere = request.getParameter("tolstere");
         	String section=request.getParameter("section");
         	//System.out.println("...." +workid + "...");
         	sql="SELECT i.workid,c.proj_package_Name,c.cutnum,c.company,i.toltree,i.tolstere,i.yard,i.section FROM\r\n" + 
         			"workpage AS w JOIN workpage_status join cutnum as c join inyard as i ON w.workid = workpage_status.workid\r\n" + 
         			"and c.cutnum=i.cutNum and i.workid=w.workid\r\n" + 
         			"WHERE 1=1 AND workpage_status.workid_status >= 8 AND workpage_status.workid_status <> 9 AND workpage_status.workid_status <>10 ";
         	
         	if(!timeStart.isEmpty() && !timeEnd.isEmpty())
         	{
         		sql=sql+"AND i.yarddate>='"+timeStart+"' AND i.yarddate<='"+timeEnd+"'";
         	}
         	if(!projectPackageName.isEmpty())
         	{
         		sql=sql+" and c.proj_package_Name='"+projectPackageName+"'";
         	}
         	if(!cutnum.isEmpty())
         	{
         		sql=sql+"c.cutnum='"+cutnum+"'";
         	}
         	if(!yard.isEmpty())
         	{
         		sql=sql+" AND i.yard='"+yard+"'";
         	}
         	if(!workid.isEmpty())
         	{
         		sql=sql+" and i.workid='"+workid+"'";
         	}
         	if(!toltree.isEmpty())
         	{
         		sql=sql+" and i.toltree='"+toltree+"'";
         	}
         	if(!tolstere.isEmpty())
         	{
         		sql=sql+" and i.tolstere='"+tolstere+"'";
         	}
         	if(!section.isEmpty()) {
         		sql=sql+" and i.section='"+section+"'";
         	}
         		List<yezhangPrint> work=wpd.findyezhangPrint(sql);
            	mapper.writeValue(response.getWriter(), work);
    	}
        //生产部门超级台账工单
    	else if("cutwworkid".equals(action))
    	{
    		String cutnum = request.getParameter("id");
    		System.out.println("...." +cutnum + "...");
    		sql="SELECT i.workid,c.proj_package_Name,c.cutnum,c.company,i.toltree,i.tolstere,i.yard,i.section FROM\r\n" + 
     				"workpage AS w JOIN workpage_status join cutnum as c join inyard as i ON w.workid = workpage_status.workid \r\n" + 
     				"and c.cutnum=i.cutNum and i.workid=w.workid \r\n" + 
     				"WHERE workpage_status.workid_status >= 8 AND workpage_status.workid_status <> 9 AND workpage_status.workid_status <>10\r\n" + 
     				" AND c.cutnum='"+cutnum+"'";
     		List<yezhangPrint> work=wpd.findyezhangPrint(sql);
        	mapper.writeValue(response.getWriter(), work);
    	}
       //详细检尺数据
    	else if("cuttreeWw".equals(action))
    	{
    		String workid = request.getParameter("id");
    		System.out.println("...." +workid + "...");
    		sql="select workid,treetype,tlong,tradius,num,tvolume from tree where workid="+workid+"";
			List<tree> tree=trd.findTree(sql);
        	mapper.writeValue(response.getWriter(), tree);
    	}
        
      //木材收购单
        else if(action.equals("printworkpage1")){
        	String timeStart = request.getParameter("timeStart");
        	String timeEnd = request.getParameter("timeEnd");
        	String yard = request.getParameter("yard");
        	String section = request.getParameter("section");
        	//System.out.println("...." +timeStart + "...");
        	//只有起止时间
        	if(!timeStart.isEmpty() && !timeEnd.isEmpty() && yard.isEmpty() && section.isEmpty()) 
        	{   sql="SELECT w.workid,w.cutNum,w.cutSite,i.yarddate,i.yard,i.section,i.toltree,i.tolstere\r\n" + 
        			"FROM workpage w JOIN inyard  i ON w.workid=i.workid\r\n" + 
        			"WHERE i.yarddate >='"+timeStart+"' AND i.yarddate <='"+timeEnd+"' ORDER BY i.yarddate DESC";
        	List<workpage> cw=wpd.findWorkpageC(sql);
        	mapper.writeValue(response.getWriter(), cw);
        	}
        	//起止时间+货场
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && section.isEmpty())
        	{
        		sql="SELECT w.workid,w.cutNum,w.cutSite,i.yarddate,i.yard,i.section,i.toltree,i.tolstere\r\n" + 
        				"FROM workpage w JOIN inyard  i ON w.workid=i.workid\r\n" + 
        				"WHERE i.yarddate >='"+timeStart+"' AND i.yarddate <='"+timeEnd+"' ORDER BY i.yarddate DESC";
            	List<workpage> cw=wpd.findWorkpageC(sql);
            	mapper.writeValue(response.getWriter(), cw);
        	}
        	//起止时间+货场+分区
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && !yard.isEmpty() && !section.isEmpty())
        	{
        		sql="SELECT w.workid,w.cutNum,w.cutSite,i.yarddate,i.yard,i.section,i.toltree,i.tolstere\r\n" + 
        				"FROM workpage w JOIN inyard  i ON w.workid=i.workid\r\n" + 
        				"WHERE i.yarddate >='"+timeStart+"' AND i.yarddate <='"+timeEnd+"' ORDER BY i.yarddate DESC";
            	List<workpage> cw=wpd.findWorkpageC(sql);
            	mapper.writeValue(response.getWriter(), cw);
        	}
        	//起止时间+分区
        	else if(!timeStart.isEmpty() && !timeEnd.isEmpty() && yard.isEmpty() && !section.isEmpty())
        	{
        		sql="SELECT w.workid,w.cutNum,w.cutSite,i.yarddate,i.yard,i.section,i.toltree,i.tolstere\r\n" + 
        				"FROM workpage w JOIN inyard  i ON w.workid=i.workid\r\n" + 
        				"WHERE i.yarddate >='"+timeStart+"' AND i.yarddate <='"+timeEnd+"' ORDER BY i.yarddate DESC";
            	List<workpage> cw=wpd.findWorkpageC(sql);
            	mapper.writeValue(response.getWriter(), cw);
        	}
        }
        
      //货场费用台账（包含筛选条件）
        else if(action.equals("goodsyardList1")) {
        	String timeStart = request.getParameter("timeStart");
        	String timeEnd = request.getParameter("timeEnd");
        	String yard = request.getParameter("yard");
        	String feetype = request.getParameter("feetype");
        	//System.out.println("...." +timeStart + "...");
        	//System.out.println("...." +yard + "...");       	
    		sql="SELECT yard,luRuDate,feetype,num,unit,unitprice,price,remarks,luruperson from goodyard_cost where 1=1";
        	if(!timeStart.isEmpty() && !timeEnd.isEmpty()) {
        		sql=sql+" and luRuDate>='"+timeStart+"' AND luRuDate<='"+timeEnd+"'";
        	}
        	if(!yard.isEmpty()) {
        		sql=sql+" and yard='"+yard+"'";
        	}
        	if(!feetype.isEmpty())
        	{
        		sql=sql+" and feetype='"+feetype+"'";
        	}
        	
        	///System.out.print(sql);
        	List<goodsYardCost> goodsYardCost=wpd.findgoodsYard(sql);
        	mapper.writeValue(response.getWriter(), goodsYardCost);
        }        


	}

}
