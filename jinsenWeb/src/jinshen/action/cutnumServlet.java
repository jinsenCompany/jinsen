package jinshen.action;
/*管理部门录入采伐证servlet*/
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
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinshen.bean.cuntnumproj;
import jinshen.bean.cutnum;
import jinshen.bean.cutnumApply;
import jinshen.bean.cutnumCheck;
import jinshen.bean.cutnumProgress;
import jinshen.bean.cutnumStatus;
import jinshen.bean.cutnumWatch;
import jinshen.bean.cutnumfeedback;
import jinshen.bean.projectpackage;
import jinshen.dao.cutnumDao;
import jinshen.daoimpl.cutnumDaoImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class cutnumServlet
 */
@WebServlet("/cutnumServlet")
public class cutnumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cutnumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
        cutnumDao cnd=new cutnumDaoImpl();
        ObjectMapper mapper = new ObjectMapper();
        Map map=new HashMap();
        //将销售部录入的采伐证保存到数据库种
        if("addcutnum".equals(action)) {
        	String cutNum = request.getParameter("cutnum");
        	String certificatenum = request.getParameter("certificatenum");
        	double number = Double.parseDouble(request.getParameter("numbern"));
        	String company = request.getParameter("company");
        	String cutsite = request.getParameter("cutsite");
        	String sizhi = request.getParameter("sizhi");
        	String gpsinfo=request.getParameter("gpsinfo");
        	String treeorigin = request.getParameter("treeorigin");
        	String foresttype = request.getParameter("foresttype");
        	String treetype = request.getParameter("treetype");
        	String ownership = request.getParameter("ownership");
        	String forestid = request.getParameter("forestid");
        	String cuttype = request.getParameter("cuttype");
        	String cutmethod = request.getParameter("cutmethod");
        	String cutqiang = request.getParameter("cutqiang");
        	double cutarea = Double.parseDouble(request.getParameter("cutarea"));
        	double treenum = Double.parseDouble(request.getParameter("treenum"));
        	double cutstore = Double.parseDouble(request.getParameter("cutstore"));
        	double volume = Double.parseDouble(request.getParameter("volume"));
        	String starttime=request.getParameter("starttime");
        	String endtime=request.getParameter("endtime");
        	String certifier=request.getParameter("certifier");
        	String updatedate=request.getParameter("updatedate");
        	double updatevolume = Double.parseDouble(request.getParameter("updatevolume"));
        	double updatenum = Double.parseDouble(request.getParameter("updatenum"));
        	String cutnumfile=request.getParameter("cutnumfile");
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        	Date d = null;
			Date dd = null;
			Date ddd=null;
			try {
				d = format.parse(starttime);
				dd = format.parse(endtime);
				ddd=format.parse(updatedate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date begin = new java.sql.Date(d.getTime()); 
			java.sql.Date end = new java.sql.Date(dd.getTime()); 
			java.sql.Date updated = new java.sql.Date(ddd.getTime()); 
			cutnum cp = new cutnum();
			cp.setCutnum(cutNum);
			cp.setCertificatenum(certificatenum);
			cp.setNumber(number);
			cp.setCompany(company);
			cp.setcutsite(cutsite);
			cp.setSizhi(sizhi);
			cp.setGpsinfo(gpsinfo);
			cp.setTreeorigin(treeorigin);
			cp.setforesttype(foresttype);
			cp.setTreetype(treetype);
			cp.setOwnership(ownership);
			cp.setForestid(forestid);
			cp.setcuttype(cuttype);
			cp.setcutmethod(cutmethod);
			cp.setcutqiang(cutqiang);
			cp.setcutarea(cutarea);
			cp.settreenum(treenum);
			cp.setcutstore(cutstore);
			cp.setvolume(volume);
			cp.setStarttime(begin);
			cp.setEndtime(end);
			cp.setCertifier(certifier);
			cp.setUpdatedate(updated);
			cp.setUpdatevolume(updatevolume);
			cp.setUpdatenum(updatenum);
			cp.setProjectid(0);
			cp.setCutnumfile(cutnumfile);//采伐证文件路径
			sql="select count(*) from cutnum where cutnum='"+cutNum+"'";
			int flag=0;
			double f=cnd.findcount(sql);
			if(f==1) {
				out.print("该采伐证已经录入、采伐证号重复");
			}
			else {
				flag=cnd.addCutnum(cp);
				//0：未拨交，1：已拨交，2：已完成前中检查，3：已完成伐终检查，10：采伐证锁定
				if(flag>0) {
					sql="select cutnumid from cutnum WHERE cutnum='"+cutNum+"'";
					cutnumStatus cd=cnd.findCutnumStatus(sql);
					double cutnumid=cd.getCutnumid();
					cutnumStatus cs=new cutnumStatus();
					cs.setCutnumid(cutnumid);
					cs.setStatus(0);
					cs.setCutnumVolume(volume);
					int flagS=cnd.addCutnumStatus(cs);
					if(flagS>0) {
						out.write("采伐证状态插入成功");
					}
				}
			}
			if(flag==1) {
				sql="SELECT cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum,cutnumfile from cutnum where cutnum='"+cutNum+"'";
				cutnum cutnum=cnd.findCodeSingle(sql);
				request.setAttribute("cutnum",cutnum);
				request.getRequestDispatcher("manageCutnumUpdate.jsp").forward(request, response);//录入采伐证后跳转到更新采伐证页面
			}
        }
        //更新采伐证页面
        else if(action.equals("cutnumUpdate")) {
        	String cutNum = request.getParameter("cutnum");
        	String certificatenum = request.getParameter("certificatenum");
        	double number = Double.parseDouble(request.getParameter("numbern"));
        	String company = request.getParameter("company");
        	String cutsite = request.getParameter("cutsite");
        	String sizhi = request.getParameter("sizhi");
        	String gpsinfo=request.getParameter("gpsinfo");
        	String treeorigin = request.getParameter("treeorigin");
        	String foresttype = request.getParameter("foresttype");
        	String treetype = request.getParameter("treetype");
        	String ownership = request.getParameter("ownership");
        	String forestid =request.getParameter("forestid");
        	String cuttype = request.getParameter("cuttype");
        	String cutmethod = request.getParameter("cutmethod");
        	String cutqiang = request.getParameter("cutqiang");
        	double cutarea = Double.parseDouble(request.getParameter("cutarea"));
        	double treenum = Double.parseDouble(request.getParameter("treenum"));
        	double cutstore = Double.parseDouble(request.getParameter("cutstore"));
        	double volume = Double.parseDouble(request.getParameter("volume"));
        	String starttime=request.getParameter("starttime");
        	String endtime=request.getParameter("endtime");
        	String certifier=request.getParameter("certifier");
        	String updatedate=request.getParameter("updatedate");
        	double updatevolume = Double.parseDouble(request.getParameter("updatevolume"));
        	double updatenum = Double.parseDouble(request.getParameter("updatenum"));
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //转换时间格式
        	Date d = null;
			Date dd = null;
			Date ddd=null;
			try {
				d = format.parse(starttime);
				dd = format.parse(endtime);
				ddd=format.parse(updatedate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date begin = new java.sql.Date(d.getTime()); 
			java.sql.Date end = new java.sql.Date(dd.getTime());
			java.sql.Date updated = new java.sql.Date(ddd.getTime()); 
			cutnum cp = new cutnum();
			cp.setCutnum(cutNum);
			cp.setCertificatenum(certificatenum);
			cp.setNumber(number);
			cp.setCompany(company);
			cp.setcutsite(cutsite);
			cp.setSizhi(sizhi);
			cp.setGpsinfo(gpsinfo);
			cp.setTreeorigin(treeorigin);
			cp.setforesttype(foresttype);
			cp.setTreetype(treetype);
			cp.setOwnership(ownership);
			cp.setForestid(forestid);
			cp.setcuttype(cuttype);
			cp.setcutmethod(cutmethod);
			cp.setcutqiang(cutqiang);
			cp.setcutarea(cutarea);
			cp.settreenum(treenum);
			cp.setcutstore(cutstore);
			cp.setvolume(volume);
			cp.setStarttime(begin);
			cp.setEndtime(end);
			cp.setCertifier(certifier);
			cp.setUpdatedate(updated);
			cp.setUpdatevolume(updatevolume);
			cp.setUpdatenum(updatenum);
			sql="select count(*) from cutnum where number="+cp.getNumber()+"";
			int flag=0;
			double f=cnd.findcount(sql);//计算数据库种中有几个和输入采伐证号一样的
			if(f==1) {
				flag=cnd.updateCutnum(cp);//更新采伐证材料输入
			}
			else {
				flag=cnd.addCutnum(cp);//添加采伐证
			}
			if(flag==1) {
				sql="SELECT cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum,cutnumfile from cutnum where cutnum='"+cutNum+"'";
				cutnum cutnum=cnd.findCodeSingle(sql);
				request.setAttribute("cutnum",cutnum);
				request.getRequestDispatcher("manageCutnumUpdate.jsp").forward(request, response);
			}
			else {
				//sql="SELECT cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum where number="+number+"";
				sql="SELECT cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum,cutnumfile from cutnum where cutnum='"+cutNum+"'";
				cutnum cutnum=cnd.findCodeSingle(sql);
				request.setAttribute("cutnum",cutnum);
				request.getRequestDispatcher("manageCutnumUpdate.jsp").forward(request, response);
			}
        }
        //再manageCutnumshenhe.jsp页面显示采伐证信息
        /*else if(action.equals("printCutnum")){
        	String cutnum=request.getParameter("cutnum");
        	List<cutnum> dp=cnd.findCutnum();
        	//System.out.println("...."+cutnum+ "...");
        	List<cutnum> dplist = new ArrayList<cutnum>();
        	sql="SELECT cutnumid,cutnum,number,company,cutsite,sizhi,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum where cutnum="+cutnum+"";
        	cutnum d=cnd.printCutnum(sql);
        	if(dp != null) {
        		
        		//System.out.println("...."+String.valueOf(cutnum)+ "...");
        		for(int i = 0;i<dp.size();i ++) {
        			
        			//if ((String.valueOf(cutnum) == dp.get(i).getCutnum()))
        			//{
        			   d.setCutnumid(dp.get(i).getCutnumid());
        			   d.setCutnum(dp.get(i).getCutnum());
    				   d.setNumber(dp.get(i).getNumber());
    				   d.setCompany(dp.get(i).getCompany());
    				   d.setcutsite(dp.get(i).getcutsite());
    				   d.setSizhi(dp.get(i).getSizhi());
    				   d.setTreeorigin(dp.get(i).getTreeorigin());
    				   d.setforesttype(dp.get(i).getforesttype());
    				   d.setTreetype(dp.get(i).getTreetype());
    				   d.setOwnership(dp.get(i).getOwnership());
    				   d.setForestid(dp.get(i).getForestid());
    				   d.setcuttype(dp.get(i).getcuttype());
    				   d.setcutmethod(dp.get(i).getcutmethod());
    				   d.setcutqiang(dp.get(i).getcutqiang());
    				   d.setcutarea(dp.get(i).getcutarea());
    				   d.settreenum(dp.get(i).gettreenum());
    				   d.setcutstore(dp.get(i).getcutstore());
    				   d.setvolume(dp.get(i).getvolume());
    				   d.setStarttime(dp.get(i).getStarttime());
    				   d.setEndtime(dp.get(i).getEndtime());
    				   d.setCertifier(dp.get(i).getCertifier());
    				   d.setUpdatedate(dp.get(i).getUpdatedate());
    				   d.setUpdatevolume(dp.get(i).getUpdatevolume());
    				   d.setUpdatenum(dp.get(i).getUpdatenum());
    				   //System.out.println("...."+dp.get(i).getCutnum() + "...");
    				   dplist.add(d);
    				   //System.out.println("...."+dplist.size()+ "...");
        			//}
        		}
        	}
        	else {
        		out.print("数据为空");
        	}
        	ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), dplist);
        }*/
        //通过采伐证号搜索采伐证信息
        else if(action.equals("printCutnum")){
        	//sql="SELECT cutnumid,cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum where cutnum like '%"+cutnum+"%'";
        	sql="SELECT starttime,endtime,cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,certifier,updatedate,updatevolume,updatenum from cutnum GROUP BY starttime\r\n" + 
        			""; 
        	List<cutnum> cw=cnd.findCutnumC(sql);
        	//System.out.println("...."+cutnum+ "...");
        	mapper.writeValue(response.getWriter(), cw);
        }
        else if("seecut".equals(action)) {
        	//String str=request.getParameter("type");
        	
        	sql="select cutnumid,cutnum,number,company,cutsite from cutnum";
        	
        	List<cutnumWatch> cw=cnd.findCuteSingle(sql);
        	//System.out.println("...."+cw.size() + "...");
        	mapper.writeValue(response.getWriter(), cw);
        	
        }
        //查看更新页面也就是查看输入采伐证号详细信息
        else if(action.equals("watch")){
        	String str=request.getParameter("cutnum");
        	str=str.replace("'", "");
        	//double number=Double.parseDouble(str);
        	//System.out.println("...."+number + "...");
        	sql="SELECT cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum,cutnumfile from cutnum where cutnum='"+str+"'";
        	cutnum cutn = cnd.findCodeSingle(sql);
        	request.setAttribute("cutnum", cutn);
        	request.getRequestDispatcher("manageCutnumUpdate.jsp").forward(request, response);
        }
      //管理部主页查看输入采伐证号详细信息
        else if(action.equals("watchAssis")){
        	String str=request.getParameter("cutnum");
        	str=str.replace("'", "");
        	//double number=Double.parseDouble(str);
        	//System.out.println("...."+number + "...");
        	sql="SELECT cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum,cutnumfile from cutnum where cutnum='"+str+"'";
        	cutnum cutn = cnd.findCodeSingle(sql);
        	request.setAttribute("cutnum", cutn);
        	request.getRequestDispatcher("manageCutnumUpdateAssis.jsp").forward(request, response);
        }
        //将采伐证信息显示再创建工程包页面(未选择工程包的采伐证号)
        else if("cutnumproject".equals(action)) {
        	//String str=request.getParameter("type");
        	
        	sql="select cutnum,number,starttime,endtime,company,cutsite,cutstore from cutnum WHERE proj_package_id=0";
        	
        	List<cutnum> cw=cnd.findCutnumproject(sql);
        	//System.out.println("...."+cw.size() + "...");
        	mapper.writeValue(response.getWriter(), cw);
        }
        /*else if("addproject".equals(action)) {
        	double projectPackageid = Double.parseDouble(request.getParameter("projectPackageid"));
        	String rebate = request.getParameter("project");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
        	int ctu=0;
        	cutnum cn=new cutnum();
        	for(int i=0;i<id;i++)
        	{
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		cn.setProjectid(s.getDouble(i));
        		//sql="update cutnum set proj_package_id="+projectPackageid+" WHERE cutnum="+cn+"";
        	}
        	ctu=cnd.addprojectid(projectPackageid, cn);
			if(ctu > 0)
				out.print("录入成功！");
			else
				out.println("录入失败！");
        }*/
        //将工程包信息添加在数据库表project_package_id中暂时未用
        else if("addprojectid".equals(action)) {
        	double projectPackageid = Double.parseDouble(request.getParameter("projectPackageid"));
        	String projectPackagename = request.getParameter("projectPackagename");
        	String managerPhone = request.getParameter("managerPhone");
        	projectpackage pk=new projectpackage();
        	pk.setprojectPackageid(projectPackageid);
        	pk.setprojectPackagename(projectPackagename);
        	pk.setmanagerPhone(managerPhone);
        	sql="select count(*) from proj_package where proj_Package_id="+projectPackageid+"";
        	int flag=0;
        	double f=cnd.findcount(sql);
			if(f==1) {
				//flag=cnd.updateCutnum(cp);
			}
			else {
				flag=cnd.addprojectpackage(pk);
			}
        }
        else if("addproject".equals(action)) {
        	double projectPackageid = Double.parseDouble(request.getParameter("projectPackageid"));
        	String rebate = request.getParameter("project");
        	JSONArray jb = JSONArray.fromObject(rebate);
        	String each="";
        	//System.out.println("...."+jb + "...");
        	for(int i=0;i<jb.length();i++)
        	{
        		//each = Double.parseDouble(jb.getString(i));
        		each = String.valueOf(jb.getString(i));
        		//JSONArray s=jb.getJSONArray(String.valueOf(i));
        		//cn.setCutnum(s.getDouble(i));
        		cutnum cn=new cutnum();
            	cn.setProjectid(projectPackageid);
        	    sql="SELECT count(cutnum) from cutnum WHERE cutnum='"+each+"'";
        	    //System.out.println("...."+each+ "...");
        	    double f=cnd.findcount(sql);
        	    //System.out.println("...."+f+ "...");
        	    if (f>0) {
        	    	int ctu=cnd.addprojectid(each,cn);
        	    	out.print(ctu);
        	    }
        	    else {
        	    	out.print("录入失败！");
        	    }
        	    
        	}
        	//将工程包信息添加在数据库表project_package_id中
        	String projectPackagename = request.getParameter("projectPackagename");
        	String managerPhone = request.getParameter("managerPhone");
        	projectpackage pk=new projectpackage();
        	pk.setprojectPackageid(projectPackageid);
        	pk.setprojectPackagename(projectPackagename);
        	pk.setmanagerPhone(managerPhone);
        	sql="select count(*) from proj_package where proj_Package_id="+projectPackageid+"";
        	int flag=0;
        	double f=cnd.findcount(sql);
			if(f>=1) {
				flag=cnd.updateprojectpackage(pk);
				out.print(flag);
			}
			else {
				flag=cnd.addprojectpackage(pk);
				out.print(flag);
			}
			/*if(flag==1) {
				sql="select * from proj_package where proj_Package_id="+projectPackageid+"";
				projectpackage projectpackage=cnd.findprojectpackage(sql);
				request.setAttribute("projectpackage", projectpackage);
			    request.getRequestDispatcher("CutnumProjectpackageUpdate.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("CutnumProjectpackage.jsp").forward(request, response);
			}*/
        }
        //在更新工程包页面显示被当前工程包选取的采伐证号
        else if("cutnumprojectS".equals(action)) {
        	double projectPackageid = Double.parseDouble(request.getParameter("projectPackageid"));
            sql="select cutnum,number,starttime,endtime,company,cutsite,cutstore from cutnum WHERE proj_package_id="+projectPackageid+"";
              List<cutnum> cw=cnd.findCutnumproject(sql);
        	//System.out.println("...."+cw.size() + "...");
        	mapper.writeValue(response.getWriter(), cw);
        }
        
        else if("updateproject".equals(action)) {
        	double projectPackageid = Double.parseDouble(request.getParameter("projectPackageid"));
        	String rebate = request.getParameter("project");
        	JSONArray jb = JSONArray.fromObject(rebate);
        	String each="";
        	//System.out.println("...."+jb + "...");
        	for(int i=0;i<jb.length();i++)
        	{
        		each = jb.getString(i);
        		//JSONArray s=jb.getJSONArray(String.valueOf(i));
        		//cn.setCutnum(s.getDouble(i));
        		cutnum cn=new cutnum();
            	cn.setProjectid(projectPackageid);
        	    sql="SELECT count(cutnum) from cutnum WHERE cutnum='"+each+"'";
        	    //System.out.println("...."+each+ "...");
        	    double f=cnd.findcount(sql);
        	    if (f>0) {
        	    	int ctu=cnd.addprojectid(each,cn);
        	    	out.print(ctu);
        	    }
        	    else {
        	    	out.print("录入失败！");
        	    }
        	    
        	}
        	//将工程包信息添加在数据库表project_package_id中
        	String projectPackagename = request.getParameter("projectPackagename");
        	String managerPhone = request.getParameter("managerPhone");
        	projectpackage pk=new projectpackage();
        	pk.setprojectPackageid(projectPackageid);
        	pk.setprojectPackagename(projectPackagename);
        	pk.setmanagerPhone(managerPhone);
        	sql="select count(*) from proj_package where proj_Package_id="+projectPackageid+"";
        	int flag=0;
        	double f=cnd.findcount(sql);
			if(f==1) {
				flag=cnd.updateprojectpackage(pk);
				out.print(flag);
			}
			else {
				flag=cnd.addprojectpackage(pk);
				out.print(flag);
			}
			/*if(flag==1) {
				sql="select * from proj_package where proj_Package_id="+projectPackageid+"";
				projectpackage projectpackage=cnd.findprojectpackage(sql);
				request.setAttribute("projectpackage", projectpackage);
			    request.getRequestDispatcher("CutnumProjectpackageUpdate.jsp").forward(request, response);
			}
			else {
				sql="select * from proj_package where proj_Package_id="+projectPackageid+"";
				projectpackage projectpackage=cnd.findprojectpackage(sql);
				request.setAttribute("projectpackage", projectpackage);
			    request.getRequestDispatcher("CutnumProjectpackageUpdate.jsp").forward(request, response);
			}*/
        }
        //从工程包删除采伐证号
        else if("alldelete".equals(action)) {
        	String mygroup = request.getParameter("cutnum");
        	//JSONArray json=JSONArray.fromObject(mygroup); 
        	//double projectPackageid = Double.parseDouble(request.getParameter("projectPackageid"));
        	String each="";
        	//each=Double.parseDouble(mygroup);
        	each=mygroup;
        	System.out.println("...."+each + "...");
        	cutnum cn=new cutnum();
        	cn.setProjectid(0);
    		 int j=cnd.addprojectid(each,cn);
    		 if(j>0)
    		 {
    			 out.print("删除成功！");
    			 //break;
    		 }
    		 else {
    			 out.print("删除失败！");
    		 }
        	/*for(int i=0;i<json.length();i++)
        	{
        		each=Double.parseDouble(json.getString(i));
        		 //sql="delete from  where workid="+each+"";
        		cutnum cn=new cutnum();
            	cn.setProjectid(0);
        		 int j=cnd.addprojectid(each,cn);
        		 if(j>0)
        		 {
        			 out.print("删除成功！");
        			 //break;
        		 }
        		 else {
        			 out.print("删除失败！");
        		 }
        	}*/
    	}
        else if("watchpackage".equals(action)) {
        	String str=request.getParameter("projectPackageid");
        	str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	//System.out.println("...."+number + "...");
        	sql="select * from proj_package where proj_Package_id="+number+"";
        	projectpackage projectpackage=cnd.findprojectpackage(sql);
        	request.setAttribute("projectpackage", projectpackage);
        	request.getRequestDispatcher("CutnumProjectpackageUpdate.jsp").forward(request, response);
        }
        //查看工程包信息
        else if("seepackage".equals(action)) {
            sql="select * from proj_package";
        	List<projectpackage> cw=cnd.seeprojectpackag(sql);
        	//System.out.println("...."+cw.size() + "...");
        	mapper.writeValue(response.getWriter(), cw);
        }
        //添加采伐证申请信息
        else if("cutnumapply".equals(action)) {
        	//double applyid = Double.parseDouble(request.getParameter("applyid"));
        	String designum = request.getParameter("designum");
        	String cutreason = request.getParameter("cutreason");
        	String cutaddress = request.getParameter("cutaddress");
        	String cutvillage = request.getParameter("cutvillage");
        	String quartel = request.getParameter("quartel");
        	String largeblock = request.getParameter("largeblock");
        	String smallblock = request.getParameter("smallblock");
        	double smallblackarea = Double.parseDouble(request.getParameter("smallblackarea"));
        	String origin = request.getParameter("origin");
        	String foresttype = request.getParameter("foresttype");
        	String typeconsist = request.getParameter("typeconsist");
        	String managetype = request.getParameter("managetype");
        	double forestage = Double.parseDouble(request.getParameter("forestage"));
        	double cutarea = Double.parseDouble(request.getParameter("cutarea"));
        	String cuttype = request.getParameter("cuttype");
        	String cutway = request.getParameter("cutway");
        	String cutstrength = request.getParameter("cutstrength");
        	String treetype = request.getParameter("treetype");
        	double cutvolume = Double.parseDouble(request.getParameter("cutvolume"));
            int cutnumer=Integer.parseInt(request.getParameter("cutnumer"));
            double total = Double.parseDouble(request.getParameter("total"));
            double cutintermediate = Double.parseDouble(request.getParameter("cutintermediate"));
            double total2 = Double.parseDouble(request.getParameter("total2"));
            String sizewood = request.getParameter("sizewood");
        	String smalltimber = request.getParameter("smalltimber");
        	String shorttimber = request.getParameter("shorttimber");
        	String firewood = request.getParameter("firewood");
        	String cutpath = request.getParameter("cutpath");
        	String applaydate = request.getParameter("applaydate");
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Date d = null;
			try {
				d = format.parse(applaydate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date applydate = new java.sql.Date(d.getTime()); 
			cutnumApply cp=new cutnumApply();
			//cp.setapplyid(applyid);
			cp.setDesignum(designum);
			cp.setCutreason(cutreason);
			cp.setCutaddress(cutaddress);
			cp.setCutvillage(cutvillage);
			cp.setQuartel(quartel);
			cp.setLargeblock(largeblock);
			cp.setSmallblock(smallblock);
			cp.setSmallblackarea(smallblackarea);
			cp.setOrigin(origin);
			cp.setForesttype(foresttype);
			cp.setTypeconsist(typeconsist);
			cp.setManagetype(managetype);
			cp.setForestage(forestage);
			cp.setCutarea(cutarea);
			cp.setCuttype(cuttype);
			cp.setCutway(cutway);
			cp.setCutstrength(cutstrength);
			cp.setTreetype(treetype);
			cp.setCutvolume(cutvolume);
			cp.setCutnumer(cutnumer);
			cp.setTotal(total);
			cp.setCutintermediate(cutintermediate);
			cp.setTotal2(total2);
			cp.setSizewood(sizewood);
			cp.setSmalltimber(smalltimber);
			cp.setShorttimber(shorttimber);
			cp.setFirewood(firewood);
			cp.setCutpath(cutpath);
			cp.setApplaydate(applydate);
			int flag=cnd.addCutnumApply(cp);
			if(flag>0) {
				cutnumfeedback cf=new cutnumfeedback();
				//cf.setApplyid(applyid);
				String unablereason="";
				String replenishpath="";
				sql="select apply_id from cutnum_application where designum='"+designum+"'";
				cutnumfeedback apid=cnd.findApplyid(sql);
				double ap=apid.getApplyid();
				//System.out.println("...."+apid + "...");
				cf.setApplyid(ap);
				cf.setStatus(0);
				cf.setUnablereason(unablereason);
				cf.setReplenishpath(replenishpath);
				int flags=cnd.addcutumapplystatus(cf);
				if(flags>0) {
				out.print("插入成功");}
			}
        }
        //查看采伐证申请信息：未审核：0，已通过：1，第一次未通过或者第二次审核未通过:2或5,补充材料:3 ,第二次审核补充材料：4
        else if("seecutnumApply".equals(action)) {
        	String mytype = request.getParameter("type");
        	if(mytype.equals("all")) {
        		sql="SELECT a.apply_id,a.applay_date,a.designum,a.cut_adress,a.tree_type,a.cut_volume from cutnum_application as a join feedback_application as f on a.apply_id=f.apply_id";
        		List<cutnumApply> cw=cnd.seecutnumApply(sql);
            	System.out.println("...."+cw.size() + "...");
            	mapper.writeValue(response.getWriter(), cw);
        	}
        	//未审核
        	else if ("notshenhe".equals(mytype)) {
            sql="SELECT a.apply_id,a.applay_date,a.designum,a.cut_adress,a.tree_type,a.cut_volume from cutnum_application as a join feedback_application as f on a.apply_id=f.apply_id WHERE f.status=0";
        	List<cutnumApply> cw=cnd.seecutnumApply(sql);
        	//System.out.println("...."+cw.size() + "...");
        	mapper.writeValue(response.getWriter(), cw);
        	}
        	//一次通过
        	else if ("pass".equals(mytype)) {
        		sql="SELECT a.apply_id,a.applay_date,a.designum,a.cut_adress,a.tree_type,a.cut_volume from cutnum_application as a join feedback_application as f on a.apply_id=f.apply_id WHERE f.status=1";
            	List<cutnumApply> cw=cnd.seecutnumApply(sql);
            	//System.out.println("...."+cw.size() + "...");
            	mapper.writeValue(response.getWriter(), cw);
        	}
        	//补充材料再审核
        	else if ("notpass".equals(mytype)) {
        		sql="SELECT a.apply_id,a.applay_date,a.designum,a.cut_adress,a.tree_type,a.cut_volume from cutnum_application as a join feedback_application as f on a.apply_id=f.apply_id WHERE f.status=4";
            	List<cutnumApply> cw=cnd.seecutnumApply(sql);
            	//System.out.println("...."+cw.size() + "...");
            	mapper.writeValue(response.getWriter(), cw);
        	}
        	//一次审核未通过需补充材料
        	else if("supply".equals(mytype)) {
        		sql="SELECT a.apply_id,a.applay_date,a.designum,a.cut_adress,a.tree_type,a.cut_volume from cutnum_application as a join feedback_application as f on a.apply_id=f.apply_id WHERE f.status=3";
            	List<cutnumApply> cw=cnd.seecutnumApply(sql);
            	//System.out.println("...."+cw.size() + "...");
            	mapper.writeValue(response.getWriter(), cw);
        	}
        	//未通过（第一次未通过和第二次补充材料未通过）
        	else if("supplynot".equals(mytype)) {
        		sql="SELECT a.apply_id,a.applay_date,a.designum,a.cut_adress,a.tree_type,a.cut_volume from cutnum_application as a join feedback_application as f on a.apply_id=f.apply_id WHERE f.status=2 OR f.status=3 or f.status=5";
            	List<cutnumApply> cw=cnd.seecutnumApply(sql);
            	//System.out.println("...."+cw.size() + "...");
            	mapper.writeValue(response.getWriter(), cw);
        	}
        }
        //管理部门查看采伐申请明细
        else if("seeallapply".equals(action)) {
        	String str=request.getParameter("applyid");
        	str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	//System.out.println("...."+number + "...");
        	sql="SELECT designum,cut_reson,cut_adress,cut_village,quartel,larg_block,small_block,small_block_area,origin,forest_type,type_consist,mange_type,forest_age,cut_area,cut_type,cut_way,cut_strength,tree_type,cut_volume,cut_num,total,cut_intermediate,total2,size_wood,small_timber,short_timber,firewood,cutpath,applay_date from cutnum_application where apply_id="+number+"";
        	cutnumApply cutnumApply=cnd.findCutnumApply(sql);
        	request.setAttribute("cutnumApply", cutnumApply);
        	request.getRequestDispatcher("cutnumApplyupdate.jsp").forward(request, response);
        }
        //规划队修改采伐证申请
        else if("seeallapplyW".equals(action)) {
        	String str=request.getParameter("applyid");
        	str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	//System.out.println("...."+number + "...");
        	sql="SELECT designum,cut_reson,cut_adress,cut_village,quartel,larg_block,small_block,small_block_area,origin,forest_type,type_consist,mange_type,forest_age,cut_area,cut_type,cut_way,cut_strength,tree_type,cut_volume,cut_num,total,cut_intermediate,total2,size_wood,small_timber,short_timber,firewood,cutpath,applay_date from cutnum_application where apply_id="+number+"";
        	cutnumApply cutnumApply=cnd.findCutnumApply(sql);
        	sql="select unable_reson FROM feedback_application where apply_id="+number+"";
        	cutnumfeedback cutnumfeedback=cnd.findCutfeedback(sql);
        	request.setAttribute("cutnumApply", cutnumApply);
        	request.setAttribute("cutnumfeedback", cutnumfeedback);
        	request.getRequestDispatcher("cutnumApplyfeedbackW.jsp").forward(request, response);
        }
        //cutnumApplyfeedbackPass.jsp已完成采伐证
        else if("seeallapplyPass".equals(action)) {
        	String str=request.getParameter("applyid");
        	str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	//System.out.println("...."+number + "...");
        	sql="SELECT designum,cut_reson,cut_adress,cut_village,quartel,larg_block,small_block,small_block_area,origin,forest_type,type_consist,mange_type,forest_age,cut_area,cut_type,cut_way,cut_strength,tree_type,cut_volume,cut_num,total,cut_intermediate,total2,size_wood,small_timber,short_timber,firewood,cutpath,applay_date from cutnum_application where apply_id="+number+"";
        	cutnumApply cutnumApply=cnd.findCutnumApply(sql);
        	sql="select unable_reson FROM feedback_application where apply_id="+number+"";
        	cutnumfeedback cutnumfeedback=cnd.findCutfeedback(sql);
        	request.setAttribute("cutnumApply", cutnumApply);
        	request.setAttribute("cutnumfeedback", cutnumfeedback);
        	request.getRequestDispatcher("cutnumApplyfeedbackPass.jsp").forward(request, response);
        }
        //管理部门对采伐证进行操作，不办理not/办理yes/ status:0未审核，1可办理，2无法办理，3需补充材料，4补充可办理，5补充不可办理
        else if("applyyesorno".equals(action)) {
        	String mytype = request.getParameter("type");
        	String designum = request.getParameter("designum");
        	String unablereson = request.getParameter("unablereson");
        	System.out.println("...."+unablereson + "...");
        	if(mytype.equals("yes")) {
        		
        		//System.out.println("...."+designum + "...");
        		sql="SELECT f.apply_id from feedback_application as f JOIN cutnum_application as c on f.apply_id=c.apply_id WHERE c.designum='"+designum+"'";
        		cutnumfeedback apid=cnd.findApplyid(sql);
				double ap=apid.getApplyid();
        		cutnumfeedback cf=new cutnumfeedback();
        		cf.setStatus(1);//Status=1是采伐证可办理
        		int flags=cnd.updatecutumapplystatus(ap,cf);
        		if(flags>0){
        			out.print(flags);
        			}
        	}
        	else if(mytype.equals("not")) {
        		//String designum = request.getParameter("designum");
        		//System.out.println("...."+unablereson + "...");
        		sql="SELECT f.apply_id from feedback_application as f JOIN cutnum_application as c on f.apply_id=c.apply_id WHERE c.designum='"+designum+"'";
        		cutnumfeedback apid=cnd.findApplyid(sql);
        		double ap=apid.getApplyid();
        		cutnumfeedback cf=new cutnumfeedback();
        		if (unablereson!=null)
        		{
        			cf.setStatus(3);//需要补充采伐证材料
        			//cf.setUnablereason(unablereson);
        		}
        		else {
        			cf.setStatus(2);//不可办理
        		}
        		cf.setUnablereason(unablereson);
        		int flags=cnd.updatecutumapplystatusN(ap,cf);//将无法办理原因和状态存入数据库
        		if(flags>0){
        			out.print(flags);
        			}
        		
        	}
        }
        //管理部门对补充材料的采伐证申请书进行审核
        else if("applyfeedback".equals(action)) {
        	String mytype = request.getParameter("type");
        	String designum = request.getParameter("designum");
        	String unablereson = request.getParameter("unablereson");
        	//System.out.println("...."+mytype + "...");
        	if(mytype.equals("yes")) {
        		
        		//System.out.println("...."+designum + "...");
        		sql="SELECT f.apply_id from feedback_application as f JOIN cutnum_application as c on f.apply_id=c.apply_id WHERE c.designum='"+designum+"'";
        		cutnumfeedback apid=cnd.findApplyid(sql);
				double ap=apid.getApplyid();
        		cutnumfeedback cf=new cutnumfeedback();
        		cf.setStatus(1);
        		int flags=cnd.updatecutumapplystatus(ap,cf);
        		if(flags>0){
        			out.print(flags);
        			}
        	}
        	else if(mytype.equals("not")) {
        		//String designum = request.getParameter("designum");
        		//System.out.println("...."+unablereson + "...");
        		sql="SELECT f.apply_id from feedback_application as f JOIN cutnum_application as c on f.apply_id=c.apply_id WHERE c.designum='"+designum+"'";
        		cutnumfeedback apid=cnd.findApplyid(sql);
        		double ap=apid.getApplyid();
        		cutnumfeedback cf=new cutnumfeedback();
        		cf.setStatus(5);//补充不可办理
        		/*if (unablereson!=null)
        		{
        			cf.setStatus(3);
        		}
        		else {
        			cf.setStatus(2);
        		}
        		cf.setUnablereason(unablereson);*/
        		int flags=cnd.updatecutumapplystatus(ap,cf);
        		if(flags>0){
        			out.print(flags);
        			}
        		
        	}
        }
        	//更新规划对修改的采伐证修改信息
        else if("updateCutnumapply".equals(action)) {
            	//double applyid = Double.parseDouble(request.getParameter("applyid"));
            	String designum = request.getParameter("designum");
            	//System.out.println("...."+designum+ "...");
            	String cutreason = request.getParameter("cutreason");
            	String cutaddress = request.getParameter("cutaddress");
            	String cutvillage = request.getParameter("cutvillage");
            	String quartel = request.getParameter("quartel");
            	String largeblock = request.getParameter("largeblock");
            	String smallblock = request.getParameter("smallblock");
            	double smallblackarea = Double.parseDouble(request.getParameter("smallblackarea"));
            	String origin = request.getParameter("origin");
            	String foresttype = request.getParameter("foresttype");
            	String typeconsist = request.getParameter("typeconsist");
            	String managetype = request.getParameter("managetype");
            	double forestage = Double.parseDouble(request.getParameter("forestage"));
            	double cutarea = Double.parseDouble(request.getParameter("cutarea"));
            	String cuttype = request.getParameter("cuttype");
            	String cutway = request.getParameter("cutway");
            	String cutstrength = request.getParameter("cutstrength");
            	String treetype = request.getParameter("treetype");
            	double cutvolume = Double.parseDouble(request.getParameter("cutvolume"));
                int cutnumer=Integer.parseInt(request.getParameter("cutnumer"));
                double total = Double.parseDouble(request.getParameter("total"));
                double cutintermediate = Double.parseDouble(request.getParameter("cutintermediate"));
                double total2 = Double.parseDouble(request.getParameter("total2"));
                String sizewood = request.getParameter("sizewood");
            	String smalltimber = request.getParameter("smalltimber");
            	String shorttimber = request.getParameter("shorttimber");
            	String firewood = request.getParameter("firewood");
            	String cutpath = request.getParameter("cutpath");
            	String applaydate = request.getParameter("applaydate");
            	String replenishpath = request.getParameter("replenishpath");
            	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            	Date d = null;
    			try {
    				d = format.parse(applaydate);
    			} catch (ParseException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} 
    			java.sql.Date applydate = new java.sql.Date(d.getTime());
    			sql="select apply_id from cutnum_application where designum='"+designum+"'";
    			cutnumfeedback apid=cnd.findApplyid(sql);
				double ap=apid.getApplyid();
				System.out.println("...."+ap+ "...");
    			cutnumApply cp=new cutnumApply();
    			//cp.setapplyid(applyid);
    			cp.setDesignum(designum);
    			cp.setCutreason(cutreason);
    			cp.setCutaddress(cutaddress);
    			cp.setCutvillage(cutvillage);
    			cp.setQuartel(quartel);
    			cp.setLargeblock(largeblock);
    			cp.setSmallblock(smallblock);
    			cp.setSmallblackarea(smallblackarea);
    			cp.setOrigin(origin);
    			cp.setForesttype(foresttype);
    			cp.setTypeconsist(typeconsist);
    			cp.setManagetype(managetype);
    			cp.setForestage(forestage);
    			cp.setCutarea(cutarea);
    			cp.setCuttype(cuttype);
    			cp.setCutway(cutway);
    			cp.setCutstrength(cutstrength);
    			cp.setTreetype(treetype);
    			cp.setCutvolume(cutvolume);
    			cp.setCutnumer(cutnumer);
    			cp.setTotal(total);
    			cp.setCutintermediate(cutintermediate);
    			cp.setTotal2(total2);
    			cp.setSizewood(sizewood);
    			cp.setSmalltimber(smalltimber);
    			cp.setShorttimber(shorttimber);
    			cp.setFirewood(firewood);
    			cp.setCutpath(cutpath);
    			cp.setApplaydate(applydate);
    			int flag=cnd.updateCutnumApply(ap,cp);//更新采伐证申请表
    			//采伐证状态表
    			if(flag>0)
    			{
    				cutnumfeedback cf=new cutnumfeedback();
    			//sql="select apply_id from cutnum_application where designum like '%"+designum+"%'";
				//cutnumfeedback apid=cnd.findApplyid(sql);
				//double ap=apid.getApplyid();
				//cf.setApplyid(ap);
				cf.setStatus(4);//提交补充可办理
    			cf.setReplenishpath(replenishpath);
    			int flagf=cnd.updateCutnumApplypath(ap, cf);
    			if(flagf>0) {
    				out.print("修改成功");
    				}
    			else {
    				out.print("修改失败");
    			}
    			}
    	 }
        //管理部门审核以补充材料的申请书
        else if("seeallapplyBu".equals(action)) {
        	String str=request.getParameter("applyid");
        	str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	//System.out.println("...."+number + "...");
        	sql="SELECT designum,cut_reson,cut_adress,cut_village,quartel,larg_block,small_block,small_block_area,origin,forest_type,type_consist,mange_type,forest_age,cut_area,cut_type,cut_way,cut_strength,tree_type,cut_volume,cut_num,total,cut_intermediate,total2,size_wood,small_timber,short_timber,firewood,cutpath,applay_date from cutnum_application where apply_id="+number+"";
        	cutnumApply cutnumApply=cnd.findCutnumApply(sql);
        	sql="select unable_reson,replenish_path FROM feedback_application where apply_id="+number+"";
        	cutnumfeedback cutnumfeedback=cnd.findCutfeedbackP(sql);
        	request.setAttribute("cutnumApply", cutnumApply);
        	request.setAttribute("cutnumfeedback", cutnumfeedback);
        	request.getRequestDispatcher("cutnumApplyshenhe.jsp").forward(request, response);
        }
        else if("findCutnumF".equals(action)) {
        	String year=request.getParameter("year");
        	String month=request.getParameter("month");
        	if("all".equals(month))
        	{
        	sql="select cutnumid,cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype, treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum WHERE year(starttime)="+year+"";    	
        	}
        	else if("1-3".equals(month)) {
        	sql="select cutnumid,cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype, treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum WHERE year(starttime)="+year+" and (month(starttime)="+1+" or month(starttime)="+2+" or month(starttime)="+3+" )";
        	
        	}
        	else if("4-6".equals(month)) {
            	sql="select cutnumid,cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype, treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum WHERE year(starttime)="+year+" and (month(starttime)="+4+" or month(starttime)="+5+" or month(starttime)="+6+" )";
            	}
        	else if("7-9".equals(month)) {
            	sql="select cutnumid,cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype, treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum WHERE year(starttime)="+year+" and (month(starttime)="+7+" or month(starttime)="+8+" or month(starttime)="+9+" )";
            	}
        	else if("10-12".equals(month)) {
            	sql="select cutnumid,cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype, treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum WHERE year(starttime)="+year+" and (month(starttime)="+10+" or month(starttime)="+11+" or month(starttime)="+12+" )";
            	}
        	else
        	{
        	sql="select cutnumid,cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype, treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum WHERE year(starttime)="+year+" and month(starttime)="+month+"";        	
        	}
        	List<cutnum> cw=cnd.findCutnumF(sql);
        	mapper.writeValue(response.getWriter(), cw);
	  }
        //拨交查看采伐证材料
        else if("cutnumprojectAllot".equals(action)) {
        	double projectPackageid = Double.parseDouble(request.getParameter("projectPackageid"));
        	sql="select c.cutnum,c.number,c.starttime,c.endtime,c.company,c.cutsite,c.cutstore from cutnum as c join cutnum_status as s on c.cutnumid=s.cutnumid WHERE c.proj_package_id="+projectPackageid+" and s.`status`>=0";
            //sql="select cutnum,number,starttime,endtime,company,cutsite,cutstore from cutnum WHERE proj_package_id="+projectPackageid+"";
              List<cutnum> cw=cnd.findCutnumproject(sql);
        	//System.out.println("...."+cw.size() + "...");
        	mapper.writeValue(response.getWriter(), cw);
        }
       //根据工程包查看采伐证进行拨交
        else if(action.equals("seebojiao")) {
        	String str=request.getParameter("projectPackageid");
        	str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	//System.out.println("...."+number + "...");
        	sql="select * from proj_package where proj_Package_id="+number+"";
        	projectpackage projectpackage=cnd.findprojectpackage(sql);
        	request.setAttribute("projectpackage", projectpackage);
        	request.getRequestDispatcher("cutareaAllotSee.jsp").forward(request, response);
        }
        //查看采伐证信息
        else if(action.equals("watchcutnumAllot")){
        	String str=request.getParameter("cutnum");
        	str=str.replace("'", "");
        	//double number=Double.parseDouble(str);
        	//System.out.println("...."+str + "...");
        	sql="SELECT cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum where cutnum='"+str+"'";
        	cutnum cutnum = cnd.findCodeSingle(sql);
        	request.setAttribute("cutnum", cutnum);
        	request.getRequestDispatcher("cutareaAllotupdate.jsp").forward(request, response);	
        }
        //拨交采伐证激活采伐证
        else if(action.equals("cutareaAllot")) {
        	//double projectPackageid = Double.parseDouble(request.getParameter("projectPackageid"));
        	String rebate = request.getParameter("project");
        	JSONArray jb = JSONArray.fromObject(rebate);
        	String each="";
        	//System.out.println("...."+jb + "...");
        	for(int i=0;i<jb.length();i++)
        	{
        		each = jb.getString(i);
        		//JSONArray s=jb.getJSONArray(String.valueOf(i));
        		//cn.setCutnum(s.getDouble(i));
        		//cutnum cn=new cutnum();
            	//cn.setProjectid(projectPackageid);
        	    sql="SELECT count(cutnum) from cutnum WHERE cutnum='"+each+"'";
        	    //System.out.println("...."+each+ "...");
        	    double f=cnd.findcount(sql);
        	    if (f>0) {
        	    	sql="select cutnumid from cutnum WHERE cutnum='"+each+"'";
    				cutnumStatus cd=cnd.findCutnumStatus(sql);
    				double cutnumid=cd.getCutnumid();
    				cutnumStatus cs=new cutnumStatus();
    				cs.setStatus(1);
    				int flagS=cnd.updateCutnumStatus(cs,cutnumid);
        	    	out.print(flagS);
        	    }
        	    else {
        	    	out.print("录入失败！");
        	    }
        	    
        	}
        }
        //工程包管理台账
        else if("findCutnumproject1".equals(action)) {
        	//double projectPackageid = Double.parseDouble(request.getParameter("projectPackageid"));
        	//double projectPackagename = Double.parseDouble(request.getParameter("projectPackagename"));
        	//double managerPhone = Double.parseDouble(request.getParameter("managerPhone"));

            sql=" select c.updatedate,c.proj_package_id,c.cutnum,c.certificatenum,cutarea,p.proj_package_manager,p.manager_phone from cutnum as c JOIN proj_package as p WHERE c.proj_package_id=p.proj_package_id ";
              List<cuntnumproj> cw=cnd.findCutnumproject1(sql);
        	//System.out.println("...."+cw.size() + "...");
        	mapper.writeValue(response.getWriter(), cw);
        }
        else if("findCutnumCheck".equals(action)) {
        	//String str=request.getParameter("type");
        	
        	sql="select c.cutnum,c.starttime,c.endtime,c.cutsite,w.status from cutnum as c JOIN cutnum_status as w WHERE c.cutnumid=w.cutnumid order by starttime desc";
        	
        	List<cutnumCheck> cw=cnd.findCutnumCheck(sql);
        	//System.out.println("...."+cw.size() + "...");
        	mapper.writeValue(response.getWriter(), cw);
        
    	}
        //查看中采伐证信息
    	else if(action.equals("watchCheck")){
        	String str=request.getParameter("cutnum");
        	str=str.replace("'", "");
        	//double number=Double.parseDouble(str);
        	//System.out.println("...."+number + "...");
        	sql="SELECT cutnum,certificatenum,number,company,cutsite,sizhi,gpsinfo,treeorigin,foresttype,treetype,ownership,forestid,cuttype,cutmethod,cutqiang,cutarea,treenum,cutstore,volume,starttime,endtime,certifier,updatedate,updatevolume,updatenum from cutnum where cutnum='"+str+"'";
        	cutnum cutn = cnd.findCodeSingle(sql);
        	request.setAttribute("cutnum", cutn);
        	request.getRequestDispatcher("manageCutnumCheckUpdate.jsp").forward(request, response);
        	
        }
    	else if(action.equals("AddcutnumCheck")) {
    		String cutnum = request.getParameter("cutnum");
        	String checkdate = request.getParameter("checkdate");
        	String checkcontend = request.getParameter("checkcontend");
        	String checkresult = request.getParameter("checkrestult");
        	String checkpeople = request.getParameter("checkpeople");
        	String checkfileO = request.getParameter("checkfileO");
        	String checkfileT = request.getParameter("checkfileT");
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Date d = null;
			try {
				d = format.parse(checkdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date checktime = new java.sql.Date(d.getTime());
			cutnumCheck ck=new cutnumCheck();
			ck.setCutnum(cutnum);
			ck.setCheckdate(checktime);
			ck.setCheckcontend(checkcontend);
			ck.setCheckresult(checkresult);
			ck.setCheckpeople(checkpeople);
			ck.setCheckfileO(checkfileO);
			ck.setCheckfileT(checkfileT);
			int flag=cnd.addcutnumCheck(ck);
			if(flag>0) {
				sql="select cutnumid from cutnum WHERE cutnum='"+cutnum+"'";
				cutnumStatus cd=cnd.findCutnumStatus(sql);
				double cutnumid=cd.getCutnumid();
				cutnumStatus cs=new cutnumStatus();
				cs.setStatus(3);//0：未拨交，1：已拨交，2：已完成前中检查，3：已完成伐终检查，10：采伐证锁定
				int flagS=cnd.updateCutnumStatus(cs,cutnumid);
				if(flagS>0)
					out.print("保存成功");
			}
			else {
				out.print("保存失败");
			}
    	}
        //采伐证进程查询
    	else if("seecutP".equals(action)) {
        	//String str=request.getParameter("type");
        	
        	sql="select c.cutnumid,c.cutnum,c.number,c.company,c.cutsite from cutnum as c join cutnum_status WHERE c.cutnumid=cutnum_status.cutnumid";
        	
        	List<cutnumWatch> cw=cnd.findCuteSingle(sql);
        	//System.out.println("...."+cw.size() + "...");
        	mapper.writeValue(response.getWriter(), cw);
        	
        }
        //管理部门查看采伐证进程
    	else if(action.equals("watchProgress1")){
        	String str=request.getParameter("cutnumid");
        	str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	sql="SELECT c.cutnum,c.proj_package_id,c.cutsite,c.cutstore,c.starttime,c.endtime,s.cutnumid from cutnum as c join cutnum_status as s on c.cutnumid=s.cutnumid where c.cutnumid="+number+"";
        	cutnumProgress cutn = cnd.findProgress(sql);
        	int vstatus=cutn.getStatus();
        	System.out.println("...."+vstatus + "...");
        	String svstatus="";
        	if(vstatus==0) {
        		svstatus.equals("未拨交");
        	}
        	else if(vstatus==1)
        	{
        		svstatus.equals("已拨交");
        	}
        	else if(vstatus==2)
        	{
        		svstatus.equals("已中期检查");
        	}
        	else if(vstatus==3)
        	{
        		svstatus.equals("已终期检查");
        	}
        	else if(vstatus==10)
        	{
        		svstatus.equals("已锁定");
        	}
        	request.setAttribute("svstatus", svstatus);
        	request.setAttribute("cutnumProgress", cutn);
        	request.getRequestDispatcher("cutnumProgress.jsp").forward(request, response);
        }
    	else if(action.equals("watchProgress")){
    		String rebate = request.getParameter("project");
        	JSONArray jb = JSONArray.fromObject(rebate);
        	double each=0;
        	//System.out.println("...."+jb + "...");
        	for(int i=0;i<jb.length();i++)
        	{
        		each = jb.getDouble(i);
        	sql="SELECT c.cutnum,c.proj_package_id,c.cutsite,c.cutarea,c.cutstore,c.starttime,c.endtime,s.`status` from cutnum as c join cutnum_status as s on c.cutnumid=s.cutnumid where c.cutnumid="+each+"";
        	cutnumProgress cutn = cnd.findProgress(sql);
        	int vstatus=cutn.getStatus();
        	//System.out.println("...."+cutn.getStarttime() + "...");
        	String svstatus="";
        	if(vstatus==0) {
        		svstatus=String.valueOf("未拨交");
        	}
        	else if(vstatus==1)
        	{
        		svstatus=String.valueOf("已拨交");
        	}
        	else if(vstatus==2)
        	{
        		svstatus=String.valueOf("已中期检查");
        	}
        	else if(vstatus==3)
        	{
        		svstatus=String.valueOf("已终期检查");
        	}
        	else if(vstatus==10)
        	{
        		svstatus=String.valueOf("已锁定");
        	}
        	//System.out.println("...."+svstatus + "...");
        	map.put("cutnumProgress", cutn);
    		map.put("svstatus", svstatus);
    		mapper.writeValue(response.getWriter(), map);
        	///request.setAttribute("svstatus", svstatus);
        	//request.setAttribute("cutnumProgress", cutn);
        	//request.getRequestDispatcher("cutnumProgress.jsp").forward(request, response);
        }
    	}
	}

}
