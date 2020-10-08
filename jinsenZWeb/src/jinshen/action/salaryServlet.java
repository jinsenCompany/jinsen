package jinshen.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
/*销售部门输入木材销售价格页面*/
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinshen.dao.salemanDao;
import jinshen.dao.treeDao;
import jinshen.dao.workpageDao;
import jinshen.daoimpl.salemanDaoImpl;
import jinshen.daoimpl.treeDaoImpl;
import jinshen.daoimpl.workpageDaoImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import jinshen.bean.*;

/**
 * Servlet implementation class salaryServlet
 */
@WebServlet("/salaryServlet")
public class salaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public salaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sql="";
        treeDao trd=new treeDaoImpl();
        salemanDao sd=new salemanDaoImpl();
        treeDao td=new treeDaoImpl();
        workpageDao wpd=new workpageDaoImpl();
        ObjectMapper mapper = new ObjectMapper();
        //通过当销售工单号和数据库中一样，查询数据库中的树材信息，然后显示再treePrice.jsp页面
        if(action.equals("mysave"))
        {
        	double workid = Double.parseDouble(request.getParameter("workid"));
        	//System.out.println("...." + workid + "...");
        	Map map=new HashMap();
        	List<salesman> list=new ArrayList<salesman>();
        	sql="select count(*) from saleman where workid="+workid+"";
        	int flag=sd.findMaxid(sql);
        	//JSONArray json=JSONArray.fromObject(workid);
        	//System.out.println("...." + flag + "...");
        	if(flag>0) {
        		sql="select s.provider,s.demander,o.contractnum,o.sale_callout_orderid,o.yard,o.yarddate,o.carNumber,o.surveyor from outyard o join sale_contract s on o.contractnum=s.contractnum where workid="+workid+"";
        		list =sd.findSaleList(sql);
        		System.out.println("...." + list + "...");
        		sql="select treetype,tlong,tradius,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from treeout a join outyard b on a.workid=b.workid where b.workid="+workid+" group by treetype,tlong,tradius,unitprice";
        		List<worktree> worktree=sd.findworktree(sql);
        		map.put("salesman", list);
        		map.put("tree", worktree);
        		mapper.writeValue(response.getWriter(), map);
        		//System.out.println("...." + map.put("salesman", list) + "...");
        	}
        	else {
        		//double each=0;
            	//int count=0;
            	//String str="(";
            	//str+=",";
            	sql = "select s.provider,s.demander,o.contractnum,o.sale_callout_orderid,o.yard,o.yarddate,o.carNumber,o.surveyor from outyard o join sale_contract s on o.contractnum=s.contractnum where workid="+workid+"";
            	salesman s=sd.findsaleSingle(sql);//擦护照sql的数据通过findsaleSingle读取显示，再赋值给s
            	list.add(s);            	
            	//str+=each;
            	//str+=")";
            	//sql="select treetype,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from treeout a join outyard b on a.workid=b.workid where b.workid="+workid+" group by treetype,unitprice";
            	sql="select treetype,tlong,tradius,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from treeout a join outyard b on a.workid=b.workid where b.workid="+workid+" group by treetype,tlong,tradius,unitprice";
            	List<worktree> worktree=sd.findworktree(sql);
            	map.put("salesman", list);
        		map.put("tree", worktree);
        		mapper.writeValue(response.getWriter(), map);
        		//System.out.println("...." + map.put("salesman", list) + "...");
        	}
        }
       /* else if(action.equals("savesaleman")) {
        	double workid = Double.parseDouble(request.getParameter("workid"));
        	String treetype = request.getParameter("treetype");
        	double unitprice =Double.parseDouble(request.getParameter("unitprice"));
        	double price = Double.parseDouble(request.getParameter("price"));
        	String salesman= request.getParameter("salesman");
        	salemansql cp = new salemansql();
        	cp.setWorkid(workid);
        	cp.setTreetype(treetype);
        	cp.setUnitprice(unitprice);
        	cp.setPrice(price);
        	cp.setSaleman(salesman);
        	int flag=sd.addWork(cp);
        		out.print("flag"); 
        }*/
        //保存销售treeSalaryYezhang.jsp页面数据到数据库saleman中
        else if(action.equals("savesaleman")) {
        	String rebate = request.getParameter("newtree");
        	//System.out.println("...." + rebate + "...");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);            	
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	String salesman=request.getParameter("salesman");
        	List<salesman> list=new ArrayList<salesman>();
        	sql="select count(*) from saleman where workid="+workid+"";
        	int flag1=sd.findMaxid(sql);
        	/*if(flag1>0)
        	{
        		for(int i=0;i<id;i++) {
            		JSONArray s=jb.getJSONArray(String.valueOf(i));
                	String treetype=s.getString(0);
                	double unitprice=Double.parseDouble(s.getString(1));
                	double price=Double.parseDouble(s.getString(2));
                	sql="update saleman set treetype="+treetype+",unitprice="+unitprice+",price="+price+" where workid="+workid+"";
                	
            	}
        		
        	}*/
        		for(int i=0;i<id;i++) {
            		JSONArray s=jb.getJSONArray(String.valueOf(i));
            		salemansql cp = new salemansql();
                	cp.setWorkid(workid);
                	cp.setTreetype(s.getString(0));
                	cp.setUnitprice(Double.parseDouble(s.getString(1)));
                	cp.setPrice(Double.parseDouble(s.getString(2)));
                	cp.setSaleman(salesman);
                	int flag=sd.addWork(cp);
                	out.print(flag);
            	}
        }
       //更新再treeprice.jsp页面对木材信息的修改，并把他们保存到数据库表treeout中
        else if("treeAdd".equals(action))
        {

        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);            	
        	double workid=Double.parseDouble(request.getParameter("workid"));
            for(int i=0;i<id;i++)
            {
            	JSONArray s=jb.getJSONArray(String.valueOf(i));
            	String type=s.getString(0);
            	double volume=Double.parseDouble(s.getString(1));
            	double unitprice=Double.parseDouble(s.getString(2));
            	double price=Double.parseDouble(s.getString(3));
            	sql="update treeout set unitprice="+unitprice+",totalnum=unitprice*tvolume where  treeout.workid in "
            			+ "(select workid from outyard) and treetype='"+type+"'";
            	int flag=td.updateTree(sql);
            }
            out.print("更新完成！");
        }
        //生产业主信息
        else if("addcustomer".equals(action)) {
        	//double workid=Double.parseDouble(request.getParameter("workid"));
        	String kname=request.getParameter("kname");
        	String contractnum=request.getParameter("contractnum");
        	String address=request.getParameter("address");
        	String company=request.getParameter("company");
        	String iDCard=request.getParameter("IDCard");
        	String telephone=request.getParameter("telephone");
        	//System.out.println("...." + treetype + "...");
        	String shipaddress=request.getParameter("shipaddress");
        	String creatdate=request.getParameter("creatdate");
        	String customertype=request.getParameter("customertype");
        	//System.out.print(customertype);
//        	ArrayList<String> customess=new ArrayList<>();//顾客类型
//        	customess.add(customertype1);
//        	String customertype=StringUtils.join(customess.toArray(), ",");
        	
        	String customernum=request.getParameter("customernum");
        	String treenumber=request.getParameter("treenumber");
        	String totaltree=request.getParameter("totaltree");
        	String breakcontract=request.getParameter("breakcontract");
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date creattime=null;
        	try {
				creattime = new java.sql.Timestamp(format.parse(creatdate).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	customer cp=new customer();
        	cp.setKname(kname);
        	cp.setContractnum(contractnum);
        	cp.setCompany(company);
        	cp.setAddress(address);
        	cp.setIDCard(iDCard);
        	cp.setTelephone(telephone);
        	cp.setShipaddress(shipaddress);
        	cp.setCreatdate(creattime);
        	cp.setCustomertype(customertype);
        	cp.setCustomernum(customernum);
        	cp.setTreenumber(treenumber);
        	cp.setTotaltree(totaltree);
        	cp.setBreakcontract(breakcontract);
        	int flag=sd.addCustomer(cp);
        	out.print(flag);
        	/*if(flag>0) {
        	    //out.print("添加信息成功");
        		out.print("保存顾客信息成功,3秒后返回界面<script>setTimeout(\"window.location.href ='productowner.jsp';\", 3000);</script>");
        	    //request.getRequestDispatcher("productowner.jsp").forward(request, response);
        	    }
        	else {
        		out.print("添加信息失败");
        	}*/
        }
        //发现生产业主信息到并发送到productoy.jsp页面中
        else if("findcustomer".equals(action)) {
        	sql="SELECT * from customer";
        	List<customer> ct=sd.findCustomer(sql);
        	mapper.writeValue(response.getWriter(),ct);
        }
        //根据采伐证号显示材积
        else if(action.equals("account")) {
    		double cutnum=Double.parseDouble(request.getParameter("cutnum"));
    		Map map=new HashMap();
    		List<Laowu> list=new ArrayList<Laowu>();
    		sql="select count(*) from inyard where cutnum="+cutnum+"";
    		int flag=sd.findMaxid(sql);
    		if(flag>0) {
    			sql="select treetype,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum="+cutnum+" group by treetype,unitprice";
        		List<worktree> worktree=sd.findworktree(sql);
        		map.put("tree", worktree);
        		mapper.writeValue(response.getWriter(), map);
    		}
    		else {
    			sql="select treetype,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum="+cutnum+" group by treetype,unitprice";
        		List<worktree> worktree=sd.findworktree(sql);
        		map.put("tree", worktree);
        		mapper.writeValue(response.getWriter(), map);
    		}
    	}
        //详细生产结算保存材积到laowu表
        else if(action.equals("saveLaowu")) {
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	System.out.println("...." + id + "...");
        	JSONObject jb = JSONObject.fromObject(rebate);            	
        	String cutnum=request.getParameter("cutnum");
        	String projectPackageName=request.getParameter("projectPackageName");
        	String person=request.getParameter("person");
        	String forperson=request.getParameter("forperson");
        	String manageUnit=request.getParameter("manageUnit");
        	double ttvolume=Double.parseDouble(request.getParameter("ttvolume"));
        	double tprice=Double.parseDouble(request.getParameter("tprice"));
        	String timeEnd1=request.getParameter("timeEnd");
        	String timeStart1=request.getParameter("timeStart");
        	String bworkid=request.getParameter("bworkid");
        	String section=request.getParameter("section");
        	String yard=request.getParameter("yard");
        	String[] sw = bworkid.split(",");
        	int wlen=sw.length;
        	System.out.print(bworkid);
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        	Date d = null;
			Date dd = null;
			try {
				d = format.parse(timeEnd1);
				dd = format.parse(timeStart1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date timeEnd = new java.sql.Date(d.getTime()); 
			java.sql.Date timeStart = new java.sql.Date(dd.getTime());
        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = format1.format(new Date());
			Timestamp Time = Timestamp.valueOf(createtime);//创建时间
			int flag=0;
        	for(int i=0;i<id;i++) {
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		Laowu cp = new Laowu();
            	cp.setCutnum(cutnum);
            	cp.setProjectPackageName(projectPackageName);
            	cp.setForperson(forperson);
            	cp.setManageUnit(manageUnit);
            	cp.setTreetype(s.getString(0));
            	cp.setTlong(s.getString(1));
            	cp.setTradius(s.getString(2));
            	cp.setTvolume(s.getString(3));
            	cp.setUnitprice(Double.parseDouble(s.getString(4)));
            	cp.setprice(Double.parseDouble(s.getString(5)));
            	cp.setPerson(person);
            	cp.setttvolume(ttvolume);
            	cp.setTprice(tprice);
            	cp.setCreatTime(Time);
            	cp.setTimeStart(timeStart);
            	cp.setTimeEnd(timeEnd);
            	cp.setTworkid(bworkid);
            	cp.setYard(yard);
            	cp.setSection(section);
            	flag=sd.addProduce(cp);
            	//out.print(flag);
        	}
        	if(flag>0) {
				workpageStatus w=new workpageStatus();
				for(int j=0;j<wlen;j++) {
					String workid1=sw[j];
					double workid=Double.parseDouble(workid1);
					w.setWorkidstatus(21);//已详细生产工资结算
					int flagw=wpd.updateWorkpagestatus(workid, w);
					out.print(flagw);
				}
			}
			else {
				out.print(flag);
			}
        }
        
      //详细径极划分生产结算保存材积到laowu表
        else if(action.equals("saveLaowuRad")) {
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	System.out.println("...." + id + "...");
        	JSONObject jb = JSONObject.fromObject(rebate);            	
        	String cutnum=request.getParameter("cutnum");
        	String projectPackageName=request.getParameter("projectPackageName");
        	String person=request.getParameter("person");
        	String forperson=request.getParameter("forperson");
        	String manageUnit=request.getParameter("manageUnit");
        	double ttvolume=Double.parseDouble(request.getParameter("ttvolume"));
        	double tprice=Double.parseDouble(request.getParameter("tprice"));
        	String timeEnd1=request.getParameter("timeEnd");
        	String timeStart1=request.getParameter("timeStart");
        	String bworkid=request.getParameter("bworkid");
        	String[] sw = bworkid.split(",");
        	int wlen=sw.length;
        	String section=request.getParameter("section");
        	String yard=request.getParameter("yard");
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        	Date d = null;
			Date dd = null;
			try {
				d = format.parse(timeEnd1);
				dd = format.parse(timeStart1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date timeEnd = new java.sql.Date(d.getTime()); 
			java.sql.Date timeStart = new java.sql.Date(dd.getTime());
        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = format1.format(new Date());
			Timestamp Time = Timestamp.valueOf(createtime);//创建时间
			int flag=0;
        	for(int i=0;i<id;i++) {
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		Laowu cp = new Laowu();
            	cp.setCutnum(cutnum);
            	cp.setProjectPackageName(projectPackageName);
            	cp.setForperson(forperson);
            	cp.setManageUnit(manageUnit);
            	cp.setTreetype(s.getString(0));
            	cp.setTlong(s.getString(1));
            	cp.setTradius(s.getString(2));
            	cp.setTvolume(s.getString(3));
            	cp.setUnitprice(Double.parseDouble(s.getString(4)));
            	cp.setprice(Double.parseDouble(s.getString(5)));
            	cp.setPerson(person);
            	cp.setttvolume(ttvolume);
            	cp.setTprice(tprice);
            	cp.setCreatTime(Time);
            	cp.setTimeStart(timeStart);
            	cp.setTimeEnd(timeEnd);
            	cp.setTworkid(bworkid);
            	cp.setYard(yard);
            	cp.setSection(section);
            	flag=sd.addProduceRad(cp);
            	//out.print(flag);
        	}
        	if(flag>0) {
				workpageStatus w=new workpageStatus();
				for(int j=0;j<wlen;j++) {
					String workid1=sw[j];
					double workid=Double.parseDouble(workid1);
					w.setWorkidstatus(22);//按照径极（检尺径）生产工资结算
					int flagw=wpd.updateWorkpagestatus(workid, w);
					out.print(flagw);
				}
			}
			else {
				out.print(flag);
			}
        }
        
        //保存生产工资及其它费用结算清单
        else if(action.equals("saveLaowuAll")) {
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	System.out.println("...." + id + "...");
        	JSONObject jb = JSONObject.fromObject(rebate);            	
        	String cutnum=request.getParameter("cutnum");
        	String projectPackageName=request.getParameter("projectPackageName");
        	String person=request.getParameter("person");
        	String forperson=request.getParameter("forperson");
        	String manageUnit=request.getParameter("manageUnit");
        	String remarks=request.getParameter("remarks");
        	String freightNum=request.getParameter("freightNum");
        	String unitfreight=request.getParameter("unitfreight");
        	String freight=request.getParameter("freight");
        	String toprice=request.getParameter("toprice");
        	String premiumNum=request.getParameter("premiumNum");
        	String unitpremium=request.getParameter("unitpremium");
        	String premium=request.getParameter("premium");
        	String taxationNum=request.getParameter("taxationNum");
        	String taxation=request.getParameter("taxation");
        	String totalAll=request.getParameter("totalAll");
        	String timeEnd1=request.getParameter("timeEnd");
        	String timeStart1=request.getParameter("timeStart");
        	String bworkid=request.getParameter("bworkid");
        	String section=request.getParameter("section");
        	String yard=request.getParameter("yard");
        	String[] sw = bworkid.split(",");
        	int wlen=sw.length;
        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = format1.format(new Date());
			Timestamp Time = Timestamp.valueOf(createtime);//创建时间
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        	Date d = null;
			Date dd = null;
			try {
				d = format.parse(timeEnd1);
				dd = format.parse(timeStart1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date timeEnd = new java.sql.Date(d.getTime()); 
			java.sql.Date timeStart = new java.sql.Date(dd.getTime());
			int flag=0;
        	for(int i=0;i<id;i++) {
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		Laowu cp = new Laowu();
            	cp.setCutnum(cutnum);
            	cp.setProjectPackageName(projectPackageName);
            	cp.setForperson(forperson);
            	cp.setManageUnit(manageUnit);
            	cp.setFreightNum(freightNum);
            	cp.setUnitfreight(unitfreight);
            	cp.setFreight(freight);
            	cp.setRemarks(remarks);
            	cp.setToprice(toprice);
            	cp.setPremiumNum(premiumNum);
            	cp.setUnitpremium(unitpremium);
            	cp.setPremium(premium);
            	cp.setTaxationNum(taxationNum);
            	cp.setTaxation(taxation);
            	cp.setTotalAll(totalAll);
            	cp.setPerson(person);
            	cp.setCreatTime(Time);
            	cp.setTreetype(s.getString(0));
            	cp.setTvolume(s.getString(1));
            	cp.setUnitprice(Double.parseDouble(s.getString(2)));
            	cp.setprice(Double.parseDouble(s.getString(3)));
            	cp.setTimeStart(timeStart);
            	cp.setTimeEnd(timeEnd);
            	cp.setYard(yard);
            	cp.setSection(section);
            	flag=sd.addProduceLaowu(cp);
            	//out.print(flag);
        	}
        	if(flag>0) {
				workpageStatus w=new workpageStatus();
				for(int j=0;j<wlen;j++) {
					String workid1=sw[j];
					double workid=Double.parseDouble(workid1);
					w.setWorkidstatus(20);//已生产工资及其它费用结算清单
					int flagw=wpd.updateWorkpagestatus(workid, w);
					out.print(flagw);
				}
			}
			else {
				out.print(flag);
			}
        }
      //根据采伐证号显示材积
        else if(action.equals("savew")) {
        	String cutnum=request.getParameter("cutnum");
        	Map map=new HashMap();
    		List<singleworkid> list=new ArrayList<singleworkid>();
    		sql="select count(*) from inyard where cutnum='"+cutnum+"'";
    		int flag=sd.findMaxid(sql);
    		String mygroup = request.getParameter("mygroup");
        	JSONArray json=JSONArray.fromObject(mygroup); 
        	if(flag>0 && json.length()==0)
        	{
        		sql="select c.proj_package_Name,m.ownername,c.company from cutnum c JOIN managesdatecard m on c.cutnum=m.cutnum WHERE c.cutnum='"+cutnum+"'";
        		List<singleworkid> project=sd.findProjectp(sql);//显示工程包信息
        		sql="select a.workid from tree a join inyard b on a.workid=b.workid where b.cutNum='"+cutnum+"' group by workid";
        		list=sd.findworkid(sql);
        		sql="select treetype,round(sum(tvolume),4) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum='"+cutnum+"' group by treetype";
        		List<worktree> worktree=sd.findworktree(sql);
        		map.put("project", project);
        		map.put("work", list);
        		map.put("tree", worktree);
        		mapper.writeValue(response.getWriter(), map);
        	}
        	else {
        		double each=0;
        		String str="(";
        		str+=")";
        		sql="select c.proj_package_id,m.ownername,c.company from cutnum c JOIN managesdatecard m on c.cutnum=m.cutnum WHERE c.cutnum='"+cutnum+"'";
        		List<singleworkid> project=sd.findProjectp(sql);//显示工程包信息
        		sql="select a.workid from tree a join inyard b on a.workid=b.workid where b.cutNum="+cutnum+" group by treetype";
        		list=sd.findworkid(sql);
        		sql="select treetype,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum="+cutnum+" group by treetype";
        		List<worktree> worktree=sd.findworktree(sql);
        		map.put("project", project);
        		map.put("work", list);
        		map.put("tree", worktree);
        		mapper.writeValue(response.getWriter(), map);
        	}
        }
        //生产工资以及其他劳务结算
        //暂时不用了
        else if(action.equals("savew13333")) {
        	String cutnum=request.getParameter("cutnum");
        	String timeStart=request.getParameter("timeStart");
        	String timeEnd=request.getParameter("timeEnd");
        	String section=request.getParameter("section");
        	String yard=request.getParameter("yard");
        	//System.out.print(timeStart);
        	Map map=new HashMap();
        	sql="SELECT company,proj_package_Name from cutnum WHERE cutnum='"+cutnum+"'";
        	List<cutnum> cutCompack=sd.findcutnumPackid(sql);
        	
        	sql="select a.workid from tree a join inyard b join workpage w join workpage_status s\r\n" + 
        			"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where b.cutNum='"+cutnum+"'and w.cutdate>='"+timeStart+"' and w.cutdate<='"+timeEnd+"' and s.workid_status<>20 group by s.workid";
        	List<singleworkid> workid=sd.findworkid(sql);//显示工单信息
        	
        	//sql="select (case treetype WHEN '杉木' THEN '生产工资(衫杂木)' WHEN '杂木' then '生产工资(衫杂木)' WHEN '松木'  THEN '生产工资(松木)' WHEN '杉薪材' then '生产工资薪材' WHEN '松薪材' then '生产工资薪材' WHEN '杂薪材' THEN  '生产工资薪材' else '其他' END) as treetype1,sum(tvolume) as tvolume,unitprice,(sum(tvolume)*unitprice) as totalprice from tree a join inyard b on a.workid=b.workid where b.cutNum='"+cutnum+"'  GROUP BY treetype1,unitprice";
        	sql="select (case treetype WHEN '杉木' THEN '生产工资(衫杂木)' WHEN '杂木' then '生产工资(衫杂木)' WHEN '松木'  THEN '生产工资(松木)' WHEN '杉薪材' then '生产工资薪材' WHEN '松薪材' then '生产工资薪材' WHEN '杂薪材' THEN  '生产工资薪材' else '其他' END) as treetype1,round(sum(DISTINCT tvolume),2) as tvolume,unitprice,round(sum(DISTINCT tvolume)*unitprice,2) as totalprice from tree a join inyard b join workpage w join workpage_status s\r\n" + 
        			" on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where b.cutNum='"+cutnum+"' and w.cutdate>='"+timeStart+"' and w.cutdate<='"+timeEnd+"' and s.workid_status<>20  GROUP BY treetype1,unitprice";
        	List<worktree> worktree=sd.findworktreeSum(sql);
        	//int projpackageid=cutCompack.get(0).getProjectid();
        	for(int i=0;i<cutCompack.size();i++)
        	{
        		String propackName=cutCompack.get(i).getProjectPackageName();
        		//int projpackageid=cutCompack.get(i).getProjectid();
        		//sql="SELECT contractionSide FROM proj_package WHERE proj_package_id='"+projpackageid+"'";
            	sql="SELECT contractionSide FROM proj_package WHERE proj_package_Name='"+propackName+"'";
        		List<projectpackage> contractionSide=sd.findContractionSide(sql);
            		map.put("cutCompack", cutCompack);
            		map.put("contractionSide", contractionSide);
            		map.put("worktree", worktree);
            		map.put("workid", workid);
            		mapper.writeValue(response.getWriter(), map);
        	}
        	
        }
      //生产工资以及其他劳务结算
        else if(action.equals("savew1")) {
        	String cutnum=request.getParameter("cutnum");
        	String timeStart=request.getParameter("timeStart");
        	String timeEnd=request.getParameter("timeEnd");
        	String section=request.getParameter("section");
        	String yard=request.getParameter("yard");
        	//System.out.print(timeStart);
        	Map map=new HashMap();
        	sql="SELECT company,proj_package_Name from cutnum WHERE cutnum='"+cutnum+"'";
        	List<cutnum> cutCompack=sd.findcutnumPackid(sql);
        	
        	String sql1="select (case treetype WHEN '杉木' THEN '生产工资(衫杂木)' WHEN '杂木' then '生产工资(衫杂木)' WHEN '松木'  THEN '生产工资(松木)' WHEN '杉薪材' then '生产工资薪材' WHEN '松薪材' then '生产工资薪材' WHEN '杂薪材' THEN  '生产工资薪材' else '其他' END) as treetype1,round(sum(DISTINCT tvolume),2) as tvolume,unitprice,round(sum(DISTINCT tvolume)*unitprice,2) as totalprice from tree a join inyard b join workpage w join workpage_status s\r\n" + 
        			" on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where 1=1 and s.workid_status<>20";
        	
        	sql="select a.workid from tree a join inyard b join workpage w join workpage_status s\r\n" + 
        			"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where 1=1 and s.workid_status<>20";
        	if(!timeStart.isEmpty() && !timeEnd.isEmpty())
        	{
        		sql=sql+" and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"'";
        		sql1=sql1+" and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"'";
        	}
        	if(!cutnum.isEmpty())
        	{
        		sql=sql+" and b.cutNum='"+cutnum+"'";
        		sql1=sql1+" and b.cutNum='"+cutnum+"'";
        	}
        	if(!yard.isEmpty())
        	{
        		sql=sql+" and b.yard='"+yard+"'";
        		sql1=sql1+" and b.yard='"+yard+"'";
        	}
        	if(!section.isEmpty())
        	{
        		sql=sql+" and b.section='"+section+"'";
        		sql1=sql1+" and b.section='"+section+"'";
        	}
        	sql=sql+" group by s.workid";
        	sql1=sql1+" GROUP BY treetype1,unitprice";
        	List<singleworkid> workid=sd.findworkid(sql);//显示工单信息
        	List<worktree> worktree=sd.findworktreeSum(sql1);
        	//int projpackageid=cutCompack.get(0).getProjectid();
        	for(int i=0;i<cutCompack.size();i++)
        	{
        		String propackName=cutCompack.get(i).getProjectPackageName();
        		//int projpackageid=cutCompack.get(i).getProjectid();
        		//sql="SELECT contractionSide FROM proj_package WHERE proj_package_id='"+projpackageid+"'";
            	sql="SELECT contractionSide FROM proj_package WHERE proj_package_Name='"+propackName+"'";
        		List<projectpackage> contractionSide=sd.findContractionSide(sql);
            		map.put("cutCompack", cutCompack);
            		map.put("contractionSide", contractionSide);
            		map.put("worktree", worktree);
            		map.put("workid", workid);
            		mapper.writeValue(response.getWriter(), map);
        	}
        	
        }
        //生产工资结算
        //暂时不用用下面的
        else if(action.equals("savew211111")) {
        	String cutnum=request.getParameter("cutnum");
        	String timeStart=request.getParameter("timeStart");
        	String timeEnd=request.getParameter("timeEnd");
        	String section=request.getParameter("section");
        	String yard=request.getParameter("yard");
        	Map map=new HashMap();
        	sql="SELECT company,proj_package_Name,cutaddress,cutvillage,quartel,largeblock,smallblock from cutnum WHERE cutnum='"+cutnum+"'";
        	List<cutnum> cutCompack=sd.findcutnumPackidLin(sql);
        	//int projpackageid=cutCompack.get(0).getProjectid();
        	
    		//sql="select a.workid from tree a join inyard b on a.workid=b.workid where b.cutNum='"+cutnum+"' group by workid";
    		sql="select a.workid from tree a join inyard b join workpage w join workpage_status s\r\n" + 
    				"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where b.cutNum='"+cutnum+"'and w.cutdate>='"+timeStart+"' and w.cutdate<='"+timeEnd+"' and s.workid_status<>21 group by workid";
        	List<singleworkid> workid=sd.findworkid(sql);//显示工程包信息
    		//sql="select treetype,tlong,tradius,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum='"+cutnum+"' group by treetype,tlong,tradius,unitprice";
    		sql="select treetype,tlong,tradius,round(sum(DISTINCT tvolume),2) as total,unitprice,round(sum(DISTINCT tvolume)*unitprice,2) as totalprice  from tree a join inyard b join workpage w join workpage_status s\r\n" + 
    				"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where b.cutNum='"+cutnum+"' and w.cutdate>='"+timeStart+"' and w.cutdate<='"+timeEnd+"' and s.workid_status<>21 group by treetype,tlong,tradius,unitprice";
        	//sql="select workid,treetype,tlong,(case  WHEN  tradius>0 and tradius<3 THEN '1-2' WHEN  tradius>=3 and tradius<8 THEN '3-7' WHEN tradius>=8 and tradius<10 THEN '8-9'  WHEN tradius>=10 and tradius<13 THEN '10-12' WHEN tradius>=14 and tradius<19 THEN '14-18' WHEN tradius>=20 and tradius<25 THEN '20-24' else '26以上' END) as tradius1,sum(num),sum(tvolume) from tree GROUP BY treetype,tradius1";//按径级分类
    		List<worktree> worktree=sd.findworktree(sql);
        	for(int i=0;i<cutCompack.size();i++)
        	{
        		String projpackageName=cutCompack.get(i).getProjectPackageName();
        		sql="SELECT contractionSide FROM proj_package WHERE proj_package_Name='"+projpackageName+"'";
            	List<projectpackage> contractionSide=sd.findContractionSide(sql);
            		map.put("cutCompack", cutCompack);
            		map.put("contractionSide", contractionSide);
            		map.put("workid", workid);
            		map.put("worktree",worktree);
            		mapper.writeValue(response.getWriter(), map);
        	}
        	
        }
      //生产工资结算
        else if(action.equals("savew2")) {
        	String cutnum=request.getParameter("cutnum");
        	String timeStart=request.getParameter("timeStart");
        	String timeEnd=request.getParameter("timeEnd");
        	String section=request.getParameter("section");
        	String yard=request.getParameter("yard");
        	Map map=new HashMap();
        	sql="SELECT company,proj_package_Name,cutaddress,cutvillage,quartel,largeblock,smallblock from cutnum WHERE cutnum='"+cutnum+"'";
        	List<cutnum> cutCompack=sd.findcutnumPackidLin(sql);
    		sql="select a.workid from tree a join inyard b join workpage w join workpage_status s\r\n" + 
    				"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where 1=1 and s.workid_status<>21";
    		
    	   String sql1="select treetype,tlong,tradius,round(sum(DISTINCT tvolume),2) as total,unitprice,round(sum(DISTINCT tvolume)*unitprice,2) as totalprice  from tree a join inyard b join workpage w join workpage_status s\r\n" + 
    				"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where 1=1 and s.workid_status<>21";
    		if(!timeStart.isEmpty() && !timeEnd.isEmpty())
        	{
        		sql=sql+" and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"'";
        		sql1=sql1+" and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"'";
        	}
        	if(!cutnum.isEmpty())
        	{
        		sql=sql+" and b.cutNum='"+cutnum+"'";
        		sql1=sql1+" and b.cutNum='"+cutnum+"'";
        	}
        	if(!yard.isEmpty())
        	{
        		sql=sql+" and b.yard='"+yard+"'";
        		sql1=sql1+" and yard='"+yard+"'";
        	}
        	if(!section.isEmpty())
        	{
        		sql=sql+" and b.section='"+section+"'";
        		sql1=sql1+" and section='"+section+"'";
        	}
        	sql=sql+" group by a.workid";
        	sql1=sql1+" group by treetype,tlong,tradius,unitprice";
    		
    		List<singleworkid> workid=sd.findworkid(sql);//显示工程包信息
    		List<worktree> worktree=sd.findworktree(sql1);
        	for(int i=0;i<cutCompack.size();i++)
        	{
        		String projpackageName=cutCompack.get(i).getProjectPackageName();
        		sql="SELECT contractionSide FROM proj_package WHERE proj_package_Name='"+projpackageName+"'";
            	List<projectpackage> contractionSide=sd.findContractionSide(sql);
            		map.put("cutCompack", cutCompack);
            		map.put("contractionSide", contractionSide);
            		map.put("workid", workid);
            		map.put("worktree",worktree);
            		mapper.writeValue(response.getWriter(), map);
        	}
        	
        }
      //生产工资按照径级结算
        else if(action.equals("savew2322233")) {
        	String cutnum=request.getParameter("cutnum");
        	String timeStart=request.getParameter("timeStart");
        	String timeEnd=request.getParameter("timeEnd");
        	Map map=new HashMap();
    		/*List<singleworkid> list=new ArrayList<singleworkid>();
    		sql="select count(*) from inyard where cutnum='"+cutnum+"'";
    		int flag=sd.findMaxid(sql);
    		String mygroup = request.getParameter("mygroup");
        	JSONArray json=JSONArray.fromObject(mygroup); */
        	sql="SELECT company,proj_package_Name,cutaddress,cutvillage,quartel,largeblock,smallblock from cutnum WHERE cutnum='"+cutnum+"'";
        	List<cutnum> cutCompack=sd.findcutnumPackidLin(sql);
        	//int projpackageid=cutCompack.get(0).getProjectid();
        	
    		//sql="select a.workid from tree a join inyard b on a.workid=b.workid where b.cutNum='"+cutnum+"' group by workid";
        	sql="select a.workid from tree a join inyard b join workpage w join workpage_status s\r\n" + 
    				"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where b.cutNum='"+cutnum+"'and w.cutdate>='"+timeStart+"' and w.cutdate<='"+timeEnd+"' and s.workid_status<>22 group by workid";
        	List<singleworkid> workid=sd.findworkid(sql);//显示工单信息
    		//sql="select treetype,tlong,tradius,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum='"+cutnum+"' group by treetype,tradius";
    		/*sql="select (case WHEN treeid='1' THEN '杉小径' WHEN treeid='2' THEN '杉原木' WHEN treeid='3' THEN '松小径' WHEN treeid='4' THEN '松原木' WHEN treeid='5' THEN '杂小径' WHEN treeid='6' THEN '杂原木' else treetype END) as treetype1,\r\n" + 
    				"tlong,(case  WHEN  tradius>0 and tradius<3 THEN '1-2' WHEN  tradius>=3 and tradius<8 THEN '3-7' WHEN tradius>=8 and tradius<10 THEN '8-9'  WHEN tradius>=10 and tradius<13 THEN '10-12' WHEN tradius>=14 and tradius<19 THEN '14-18' WHEN tradius>=20 and tradius<25 THEN '20-24' else '26以上' END) as tradius1,round(sum(DISTINCT tvolume),2) as ttvolume,"
    				+ "unitprice,round(sum(DISTINCT tvolume)*unitprice,2) as totalprice  from tree a join inyard b join workpage w join workpage_status s\r\n" + 
    				"    				on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where b.cutNum='"+cutnum+"' and w.cutdate>='"+timeStart+"' and w.cutdate<='"+timeEnd+"' and s.workid_status<>22 GROUP BY treetype1,tlong,tradius1,unitprice";//按径级分类*/
    		sql="select (case WHEN treeid='1' THEN '杉小径' WHEN treeid='2' THEN '杉原木' WHEN treeid='3' THEN '松小径' WHEN treeid='4' THEN '松原木' WHEN treeid='5' THEN '杂小径' WHEN treeid='6' THEN '杂原木' WHEN treeid='8' THEN '杉短材' else treetype END) as treetype1,\r\n" + 
    				"tlong,(case  WHEN  tradius>0 and tradius<3 THEN '1-2' WHEN  tradius>=3 and tradius<8 THEN '3-7' WHEN tradius>=8 and tradius<10 THEN '8-9'  WHEN tradius>=10 and tradius<13 THEN '10-12' WHEN tradius>=14 and tradius<19 THEN '14-18' WHEN tradius>=20 and tradius<25 THEN '20-24' else '26以上' END) as tradius1,round(sum(DISTINCT tvolume),2) as ttvolume,\r\n" + 
    				"unitprice,round(sum(DISTINCT tvolume)*unitprice,2) as totalprice  from tree a join inyard b join workpage w join workpage_status s\r\n" + 
    				"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where b.cutNum='"+cutnum+"' and w.cutdate>='"+timeStart+"' and w.cutdate<='"+timeEnd+"' and s.workid_status<>22 GROUP BY treetype1,tlong,tradius1,unitprice";
        	List<worktree> worktree=sd.findworktree(sql);
        	for(int i=0;i<cutCompack.size();i++)
        	{
        		String projpackageName=cutCompack.get(i).getProjectPackageName();
        		sql="SELECT contractionSide FROM proj_package WHERE proj_package_Name='"+projpackageName+"'";
            	List<projectpackage> contractionSide=sd.findContractionSide(sql);
            		map.put("cutCompack", cutCompack);
            		map.put("contractionSide", contractionSide);
            		map.put("workid", workid);
            		map.put("worktree",worktree);
            		mapper.writeValue(response.getWriter(), map);
        	}
        	
        }
      //生产工资按照径级结算
        else if(action.equals("savew23")) {
        	String cutnum=request.getParameter("cutnum");
        	String timeStart=request.getParameter("timeStart");
        	String timeEnd=request.getParameter("timeEnd");
        	String section=request.getParameter("section");
        	String yard=request.getParameter("yard");
        	Map map=new HashMap();
        	sql="SELECT company,proj_package_Name,cutaddress,cutvillage,quartel,largeblock,smallblock from cutnum WHERE cutnum='"+cutnum+"'";
        	List<cutnum> cutCompack=sd.findcutnumPackidLin(sql);
        	
        	sql="select a.workid from tree a join inyard b join workpage w join workpage_status s\r\n" + 
    				"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where 1=1 and s.workid_status<>22";
        	
    		String sql1="select (case WHEN treeid='1' THEN '杉小径' WHEN treeid='2' THEN '杉原木' WHEN treeid='3' THEN '松小径' WHEN treeid='4' THEN '松原木' WHEN treeid='5' THEN '杂小径' WHEN treeid='6' THEN '杂原木' WHEN treeid='8' THEN '杉短材' else treetype END) as treetype1,\r\n" + 
    				"tlong,(case  WHEN  tradius>0 and tradius<3 THEN '1-2' WHEN  tradius>=3 and tradius<8 THEN '3-7' WHEN tradius>=8 and tradius<10 THEN '8-9'  WHEN tradius>=10 and tradius<13 THEN '10-12' WHEN tradius>=14 and tradius<19 THEN '14-18' WHEN tradius>=20 and tradius<25 THEN '20-24' else '26以上' END) as tradius1,round(sum(DISTINCT tvolume),2) as ttvolume,\r\n" + 
    				"unitprice,round(sum(DISTINCT tvolume)*unitprice,2) as totalprice  from tree a join inyard b join workpage w join workpage_status s\r\n" + 
    				"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where 1=1 and s.workid_status<>22";
        	
        	if(!timeStart.isEmpty() && !timeEnd.isEmpty())
        	{
        		sql=sql+" and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"'";
        		sql1=sql1+" and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"'";
        	}
        	if(!cutnum.isEmpty())
        	{
        		sql=sql+" and b.cutNum='"+cutnum+"'";
        		sql1=sql1+" and b.cutNum='"+cutnum+"'";
        	}
        	if(!yard.isEmpty())
        	{
        		sql=sql+" and b.yard='"+yard+"'";
        		sql1=sql1+" and b.yard='"+yard+"'";
        	}
        	if(!section.isEmpty())
        	{
        		sql=sql+" and b.section='"+section+"'";
        		sql1=sql1+" and b.section='"+section+"'";
        	}
        	sql=sql+" group by a.workid";
        	sql1=sql1+" GROUP BY treetype1,tlong,tradius1,unitprice";
        	
        	List<worktree> worktree=sd.findworktree(sql1);
        	List<singleworkid> workid=sd.findworkid(sql);//显示工单信息
        	
        	for(int i=0;i<cutCompack.size();i++)
        	{
        		String projpackageName=cutCompack.get(i).getProjectPackageName();
        		sql="SELECT contractionSide FROM proj_package WHERE proj_package_Name='"+projpackageName+"'";
            	List<projectpackage> contractionSide=sd.findContractionSide(sql);
            		map.put("cutCompack", cutCompack);
            		map.put("contractionSide", contractionSide);
            		map.put("workid", workid);
            		map.put("worktree",worktree);
            		mapper.writeValue(response.getWriter(), map);
        	}
        	
        }
        
        //生产部门木材销售结算单
        else if(action.equals("fiTreeSalary111")) {
        	String cutnum=request.getParameter("cutnum");
        	String timeStart=request.getParameter("timeStart");
        	String timeEnd=request.getParameter("timeEnd");
        	Map map=new HashMap();
        	sql="SELECT company,proj_package_Name,cutaddress,cutvillage,quartel,largeblock,smallblock from cutnum WHERE cutnum='"+cutnum+"'";
        	List<cutnum> cutCompack=sd.findcutnumPackidLin(sql);
    		sql="select a.workid,b.yard from tree a join inyard b join workpage w join workpage_status s\r\n" + 
    				"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where b.cutNum='"+cutnum+"' and w.cutdate>='"+timeStart+"' and w.cutdate<='"+timeEnd+"' and s.workid_status<>23 group by workid";
    		List<singleworkid> workid=sd.findworkidYard(sql);//显示工程包信息
    		//sql="select treetype,tlong,tradius,num,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum='"+cutnum+"' group by treetype,tradius";
    		sql="select (case WHEN treeid='1' THEN '杉小径' WHEN treeid='2' THEN '杉原木' WHEN treeid='3' THEN '松小径' WHEN treeid='4' THEN '松原木' WHEN treeid='5' THEN '杂小径' WHEN treeid='6' THEN '杂原木' WHEN treeid='8' THEN '杉短材' else treetype END) as treetype1,\r\n" + 
    				"tlong,(case  WHEN  tradius>0 and tradius<3 THEN '1-2' WHEN  tradius>=3 and tradius<8 THEN '3-7' WHEN tradius>=8 and tradius<10 THEN '8-9'  WHEN tradius>=10 and tradius<13 THEN '10-12' WHEN tradius>=14 and tradius<19 THEN '14-18' WHEN tradius>=20 and tradius<25 THEN '20-24' else '26以上' END) as tradius1,sum(DISTINCT num) as tnum,round(sum(DISTINCT tvolume),2) as ttvolume,unitprice,round(sum(DISTINCT tvolume)*unitprice,2) as totalprice  from tree a join inyard b join workpage w join workpage_status s\r\n" + 
    				"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where b.cutNum='"+cutnum+"' and w.cutdate>='"+timeStart+"' and w.cutdate<='"+timeEnd+"' and s.workid_status<>23 GROUP BY treetype1,tlong,tradius1,unitprice";
    		List<worktree> worktree=sd.findworktreeSalary(sql);
            		map.put("cutCompack", cutCompack);
            		map.put("workid", workid);
            		map.put("worktree",worktree);
            		mapper.writeValue(response.getWriter(), map);
        }
        
        else if(action.equals("fiTreeSalary")) {
        	String cutnum=request.getParameter("cutnum");
        	String timeStart=request.getParameter("timeStart");
        	String timeEnd=request.getParameter("timeEnd");
        	String section=request.getParameter("section");
        	String yard=request.getParameter("yard");
        	Map map=new HashMap();
        	sql="SELECT company,proj_package_Name,cutaddress,cutvillage,quartel,largeblock,smallblock from cutnum WHERE cutnum='"+cutnum+"'";
        	List<cutnum> cutCompack=sd.findcutnumPackidLin(sql);
        	
    		sql="select a.workid,b.yard from tree a join inyard b join workpage w join workpage_status s\r\n" + 
    				"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where 1=1 and s.workid_status<>23";
    	
    		String sql1="select (case WHEN treeid='1' THEN '杉小径' WHEN treeid='2' THEN '杉原木' WHEN treeid='3' THEN '松小径' WHEN treeid='4' THEN '松原木' WHEN treeid='5' THEN '杂小径' WHEN treeid='6' THEN '杂原木' WHEN treeid='8' THEN '杉短材' else treetype END) as treetype1,\r\n" + 
    				"tlong,(case  WHEN  tradius>0 and tradius<3 THEN '1-2' WHEN  tradius>=3 and tradius<8 THEN '3-7' WHEN tradius>=8 and tradius<10 THEN '8-9'  WHEN tradius>=10 and tradius<13 THEN '10-12' WHEN tradius>=14 and tradius<19 THEN '14-18' WHEN tradius>=20 and tradius<25 THEN '20-24' else '26以上' END) as tradius1,sum(DISTINCT num) as tnum,round(sum(DISTINCT tvolume),2) as ttvolume,unitprice,round(sum(DISTINCT tvolume)*unitprice,2) as totalprice  from tree a join inyard b join workpage w join workpage_status s\r\n" + 
    				"on a.workid=b.workid and b.workid=w.workid and w.workid=s.workid where 1=1 and s.workid_status<>23";
    		if(!timeStart.isEmpty() && !timeEnd.isEmpty())
        	{
        		sql=sql+" and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"'";
        		sql1=sql1+" and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"'";
        	}
        	if(!cutnum.isEmpty())
        	{
        		sql=sql+" and b.cutNum='"+cutnum+"'";
        		sql1=sql1+" and b.cutNum='"+cutnum+"'";
        	}
        	if(!yard.isEmpty())
        	{
        		sql=sql+" and b.yard='"+yard+"'";
        		sql1=sql1+" and b.yard='"+yard+"'";
        	}
        	if(!section.isEmpty())
        	{
        		sql=sql+" and b.section='"+section+"'";
        		sql1=sql1+" and b.section='"+section+"'";
        	}
        	sql=sql+" group by a.workid,b.yard";
        	sql1=sql1+" GROUP BY treetype1,tlong,tradius1,unitprice";
        	List<singleworkid> workid=sd.findworkidYard(sql);//显示工程包信息
    		List<worktree> worktree=sd.findworktreeSalary(sql1);
            		map.put("cutCompack", cutCompack);
            		map.put("workid", workid);
            		map.put("worktree",worktree);
            		mapper.writeValue(response.getWriter(), map);
        }
        
        //生产部门=保存木材销售结算单
        else if(action.equals("saveTreePrice")) {
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate); 
        	String cutnum=request.getParameter("cutnum");
        	String consignee = request.getParameter("consignee");
        	String company = request.getParameter("company");
        	String yard = request.getParameter("yard");
        	String contractnum=request.getParameter("contractnum");
        	String saleCalloutOrder=request.getParameter("saleCalloutOrder");
        	String person=request.getParameter("person");
        	String tnum = request.getParameter("tnum");
        	double  ttvolume=Double.parseDouble(request.getParameter("ttvolume"));
        	double  tprice=Double.parseDouble(request.getParameter("tprice"));
        	String tworkid = request.getParameter("tworkid");
        	String[] sw = tworkid.split(",");
        	int wlen=sw.length;
        	String timeEnd1=request.getParameter("timeEnd");
        	String timeStart1=request.getParameter("timeStart");
        	String section=request.getParameter("section");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        	Date d = null;
			Date dd = null;
			try {
				d = format.parse(timeEnd1);
				dd = format.parse(timeStart1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date timeEnd = new java.sql.Date(d.getTime()); 
			java.sql.Date timeStart = new java.sql.Date(dd.getTime());
        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = format1.format(new Date());
			Timestamp Time = Timestamp.valueOf(createtime);//创建时间
			int flag=0;
			for(int i=0;i<id;i++) {
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		Laowu cp = new Laowu();
        		cp.setCutnum(cutnum);
        		cp.setConsignee(consignee);
        		cp.setCompany(company);
        		cp.setYard(yard);
        		cp.setContractnum(contractnum);
        		cp.setSaleCalloutOrder(saleCalloutOrder);
        		cp.setTnum(tnum);
        		cp.setttvolume(ttvolume);
        		cp.setTprice(tprice);
        		cp.setTworkid(tworkid);
        		cp.setPerson(person);
        		cp.setTreetype(s.getString(0));
        		cp.setTlong(s.getString(1));
        		cp.setTradius(s.getString(2));
        		cp.setNum(s.getString(3));
        		cp.setTvolume(s.getString(4));
        		cp.setUnitprice(s.getDouble(5));
        		cp.setprice(s.getDouble(6));
        		cp.setCreatTime(Time);
        		cp.setTimeStart(timeStart);
        		cp.setTimeEnd(timeEnd);
        		cp.setSection(section);
        		flag=sd.addProduceTreesalary(cp);
            	//out.print(flag);
			}
			if(flag>0) {
				workpageStatus w=new workpageStatus();
				for(int j=0;j<wlen;j++) {
					String workid1=sw[j];
					double workid=Double.parseDouble(workid1);
					w.setWorkidstatus(23);//生产部结算木材销售单
					int flagw=wpd.updateWorkpagestatus(workid, w);
					out.print(flagw);
				}
			}
			else {
				out.print(flag);
			}
        }
        //打印木材销售结算台账
        /*else if("printTreesalary".equals(action)) {
        	//sql="SELECT c.proj_package_Name,p.cutnum,r.contractionSide,p.company,p.yard,p.treetype,p.tlong,p.tradius,p.tnum,p.tvolume,p.unitprice,p.price,p.creatTime from produce_tree_salary as p join cutnum as c join proj_package as r on p.cutnum=c.cutnum and r.proj_package_Name=c.proj_package_Name";
        	sql="SELECT p.creatTime,c.proj_package_Name,p.cutnum,r.contractionSide,p.company,p.yard,p.tnum,p.tprice,p.tvolume from produce_tree_salary as p join cutnum as c join proj_package as r on p.cutnum=c.cutnum and r.proj_package_Name=c.proj_package_Name GROUP BY p.creatTime,c.proj_package_Name,p.cutnum,r.contractionSide,p.company,p.yard,p.tnum,p.tprice,p.tvolume ORDER BY p.creatTime DESC,c.proj_package_Name";
        	List<Laowu> ct=sd.printTreeSalary(sql);
        	mapper.writeValue(response.getWriter(),ct);
        }*/
        else if("printTreesalary".equals(action)) {
        	String timeStart = request.getParameter("timeStart");
         	String timeEnd = request.getParameter("timeEnd");
         	String projectPackageName = request.getParameter("projectPackageName");
         	String cutnum = request.getParameter("cutnum");
         	String section=request.getParameter("section");
        	String yard=request.getParameter("yard");
        	System.out.print(yard);
        	sql="SELECT p.creatTime,c.proj_package_Name,p.cutnum,r.contractionSide,p.company,p.yard,p.tnum,p.tprice,p.tvolume,p.timeStart,p.timeEnd,p.section from produce_tree_salary as p join cutnum as c join proj_package as r\r\n" + 
        			" on p.cutnum=c.cutnum and r.proj_package_Name=c.proj_package_Name WHERE 1=1";
        	if(!timeStart.isEmpty() && !timeEnd.isEmpty())
        	{
        		sql=sql+" and p.timeStart>='"+timeStart+"' and p.timeEnd<='"+timeEnd+"'";
        	}
        	if(!cutnum.isEmpty())
        	{
        		sql=sql+" and p.cutnum='"+cutnum+"'";
        	}
        	if(!yard.isEmpty())
        	{
        		sql=sql+" and p.yard='"+yard+"'";
        	}
        	if(!section.isEmpty())
        	{
        		sql=sql+" and p.section='"+section+"'";
        	}
        	if(!projectPackageName.isEmpty())
        	{
        		sql=sql+" and c.proj_package_Name='"+projectPackageName+"'";
        	}
        	sql=sql+" GROUP BY p.creatTime,c.proj_package_Name,p.cutnum,r.contractionSide,p.company,p.yard,p.tnum,p.tprice,p.tvolume,p.timeStart,p.timeEnd,p.section ORDER BY p.creatTime DESC,c.proj_package_Name";
         		List<Laowu> ct=sd.printTreeSalary(sql);
            	mapper.writeValue(response.getWriter(),ct);
        }
        
        //保存销售合同
        /*else if("addcontract".equals(action)) {
        	String contractnum=request.getParameter("contractnum");
        	String provider=request.getParameter("provider");
        	String demander=request.getParameter("demander");
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
        	//String treetype=request.getParameter("treetype");
        	//String tlong=request.getParameter("tlong");
        	//String tradius=request.getParameter("tradius");
        	//double unitprice=Double.parseDouble(request.getParameter("unitprice"));
        	//System.out.println("...." + contractnum + "...");
        	String deliveryplace=request.getParameter("deliveryplace");
        	String treenumber=request.getParameter("treenumber");
        	String cost=request.getParameter("cost");
        	String measurestard=request.getParameter("measurestard");
        	String settlemethod=request.getParameter("settlemethod");
        	String starttime=request.getParameter("starttime");
        	String endtime=request.getParameter("endtime");
        	String margin=request.getParameter("margin");
        	String contractfile=request.getParameter("contractfile");
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        	Date d = null;
			Date dd = null;
			try {
				d = format.parse(starttime);
				dd = format.parse(endtime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date begin = new java.sql.Date(d.getTime()); 
			java.sql.Date end = new java.sql.Date(dd.getTime()); 
        	salecontract cp=new salecontract();
        	cp.setContractnum(contractnum);
        	cp.setProvider(provider);
        	cp.setDemander(demander);
        	cp.setDeliveryplace(deliveryplace);
        	cp.setTreenumber(treenumber);
        	cp.setCost(cost);
        	cp.setMeasurestard(measurestard);
        	cp.setSettlemethod(settlemethod);
        	cp.setStarttime(begin);
        	cp.setEndtime(end);
        	cp.setContractfile(contractfile);
        	cp.setMargin(margin);
        	int flag=sd.addContract(cp);
        	if(flag>0) {
            sql="select sale_contract_id from sale_contract WHERE contractnum like '%"+contractnum+"%' and treenumber="+treenumber+"";
            salecontract idd=sd.findsalecontractid(sql);
            int contractid=idd.getContractid();
            int flags=0;
            for(int i=0;i<id;i++)
        	{
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		salecontract sc=new salecontract();
        		sc.setContractid(contractid);
        		sc.setTreetype(s.getString(0));
            	sc.setTlong(s.getString(1));
            	sc.setTradius(s.getString(2));
            	sc.setUnitprice(Double.parseDouble(s.getString(3)));
            	flags=sd.addContractTree(sc);
        	}
        	//int flags=sd.addContractTree(sc);
        	if(flags>0) {
        	    out.print("添加信息成功");
        	    //request.getRequestDispatcher("productowner.jsp").forward(request, response);
        	    }
        	else {
        		out.print("添加信息失败");
        	}
        	}
        	
        }*/
      //保存销售合同
        else if("addcontract1".equals(action)) {
        	salecontract cp=new salecontract();//采伐证信息
        	salecontract sc=new salecontract();//采伐证木材信息
        	String contractnum="";
        	String treenumber="";
        	String treetype="";
        	String tlong="";
        	String tradius="";
        	Double unitprice=null;
        	ArrayList<String> Tret=new ArrayList<>();
        	ArrayList<String> tl=new ArrayList<>();
        	ArrayList<String> trad=new ArrayList<>();
        	ArrayList<Double> unitp=new ArrayList<>();
        	ArrayList<String> costG=new ArrayList<>();//甲方付费
        	ArrayList<String> costBG=new ArrayList<>();//乙方付费
        	ArrayList<String> measurestardG=new ArrayList<>();//检尺标准
        	ArrayList<String> sectionG=new ArrayList<>();//供货分区
        	String costGA = "";
        	String costBGA = "";
        	String measurestardGA = "";
        	String sectionGA = "'";
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        	Date d = null;
			Date dd = null;
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = format1.format(new Date());
			//Timestamp Time = Timestamp.valueOf(createtime);//创建时间
        	// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
    		String savePath = this.getServletContext().getRealPath("/WEB-INF/saleContractfile");
    		//得到文件访问的相对路径
    		String readPath = "../WEB-INF/saleContractfile/";
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
    					if("contractnum".equals(name)) {
    						cp.setContractnum(item.getString("UTF-8"));
    						contractnum=item.getString("UTF-8");
    					}
    					else if("provider".equals(name)) {
    						cp.setProvider(item.getString("UTF-8"));
    					}
    					else if("demander".equals(name)) {
    						cp.setDemander(item.getString("UTF-8"));
    					}
    					else if("marknumber".equals(name)) {
    						cp.setMarknumber(item.getString("UTF-8"));
    					}
    					else if("deliveryplace".equals(name)) {
    						cp.setDeliveryplace(item.getString("UTF-8"));
    					}
    					else if("treenumber".equals(name)) {
    						cp.setTreenumber(item.getString("UTF-8"));
    						treenumber=item.getString("UTF-8");
    					}
    					else if("cost".equals(name)) {
    						String cost=item.getString("UTF-8");
    						costG.add(cost);
    						//cp.setCost(item.getString("UTF-8"));
    					}
    					else if("measurestard".equals(name)) {
    						//cp.setMeasurestard(item.getString("UTF-8"));
    						measurestardG.add(item.getString("UTF-8"));//检尺标准
    					}
    					else if("settlemethod".equals(name)){
    						cp.setSettlemethod(item.getString("UTF-8"));
    					}
    					else if("margin".equals(name)) {
    						cp.setMargin(item.getString("UTF-8"));
    					}
    					else if("starttime".equals(name)) {
    						String sttime=item.getString("UTF-8");
    						try {
    							d = format.parse(sttime);
    						} catch (ParseException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} 
    						java.sql.Date begin = new java.sql.Date(d.getTime()); 
    						cp.setStarttime(begin);
    					}
    					else if("endtime".equals(name)) {
    						String endtime=item.getString("UTF-8");
    						try {
    							dd = format.parse(endtime);
    						} catch (ParseException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} 
    						java.sql.Date end = new java.sql.Date(dd.getTime()); 
    						cp.setEndtime(end);
    					}
    					else if("treetype".equals(name)) {
    						treetype=item.getString("UTF-8");
    						Tret.add(treetype);
    					}
    					else if("tlong".equals(name)) {
    						tlong=item.getString("UTF-8");
    						tl.add(tlong);
    					}
    					else if("tradius".equals(name)) {
    						tradius=item.getString("UTF-8");
    						trad.add(tradius);
    					}
    					else if("unitprice".equals(name)) {
    						unitprice=Double.parseDouble(item.getString("UTF-8"));
    						unitp.add(unitprice);
    					}
    					else if("costB".equals(name)) {
    						//cp.setCostB(item.getString("UTF-8"));
    						costBG.add(item.getString("UTF-8"));
    					}
    					else if("section".equals(name)) {
    						//cp.setSection(item.getString("UTF-8"));
    						sectionG.add(item.getString("UTF-8"));
    					}
    					else if("contractsigner".equals(name)) {
    						cp.setContractsigner(item.getString("UTF-8"));//合同签订人
    					}
    					else if("cutnum".equals(name)) {
    						cp.setCutnum(item.getString("UTF-8"));//包产报销的采伐证
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
    					cp.setContractfile(readPath);
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
    		if(costG.size()>0)
    		{
    		   costGA=StringUtils.join(costG.toArray(), ",");
    		   //System.out.print("costGA:"+costGA);
    		}
    		if(costBG.size()>0)
    		{
    			costBGA=StringUtils.join(costBG.toArray(), ",");
    		}
    		if(measurestardG.size()>0)
    		{
    			measurestardGA=StringUtils.join(measurestardG.toArray(), ",");
    		}
    		if(sectionG.size()>0)
    		{
    			sectionGA=StringUtils.join(sectionG.toArray(), ",");
    		}
    		cp.setCost(costGA);
    		cp.setCostB(costBGA);
    		cp.setMeasurestard(measurestardGA);
    		cp.setSection(sectionGA);
    		cp.setCreatTime(createtime);
    		cp.setContractstatus(0);
    		int flag=sd.addContract(cp);
        	if(flag>0) {
            sql="select sale_contract_id from sale_contract WHERE contractnum='"+contractnum+"' and treenumber='"+treenumber+"'";
            salecontract idd=sd.findsalecontractid(sql);
            int contractid=idd.getContractid();
            int flags=0;
            //System.out.print(trad);
            for (int i=0;i<Tret.size();i++) {
            	 sc.setContractid(contractid);
                sc.setTreetype(Tret.get(i));
                sc.setTlong(tl.get(i));
                if(trad.get(i).isEmpty()) {
                	sc.setTradius("");
                }
                else {
                	sc.setTradius(trad.get(i));
                }
                sc.setUnitprice(unitp.get(i));
                flags=sd.addContractTree(sc);
			}
            out.print(flags);
        	}
        }
        //合同进度信息查看
       /* else if("contractProgress".equals(action)) {
        	sql="SELECT sale_contract_id,contractnum,provider,demander,treenumber,starttime,endtime,margin FROM sale_contract";
        	List<salecontract> ac=sd.findsalecontractP(sql);
        	mapper.writeValue(response.getWriter(),ac);
        }*/
        else if("contractProgress".equals(action)) {
        	List<salecontract> cnt=new ArrayList<salecontract>();
        	salecontract cn = new salecontract();
        	JSONArray list = new JSONArray();
        	String contractnum="";
        	int contractid=0;
        	String provider="";
        	String demander="";
        	String contractsigner="";
        	String treenumber="";
        	String starttime="";
        	String endtime="";
        	String margin="";
        	String creatTime="";
        	int contractstatus=0;
        	String useTreenum="";//已经使用数量
        	String surplusTreenum="";//剩余
        	String percentageTreenum="";//使用百分比
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 			
        	DecimalFormat df = new DecimalFormat("#.0000");
        	 DecimalFormat decimalFormat = new DecimalFormat("##.00%");
        	sql="SELECT sale_contract_id,contractnum,provider,demander,contractsigner,treenumber,starttime,endtime,margin,creatTime,contract_status FROM sale_contract ORDER BY creatTime DESC";
        	List<salecontract> ac=sd.findsalecontractP(sql);
        	if(ac.size()>0)
        	{
        		for(int i=0;i<ac.size();i++)
        		{
        			contractid=ac.get(i).getContractid();
        			contractnum=ac.get(i).getContractnum();
        			provider=ac.get(i).getProvider();
        			demander=ac.get(i).getDemander();
        			contractsigner=ac.get(i).getContractsigner();
        			treenumber=ac.get(i).getTreenumber();
        			starttime=String.valueOf(ac.get(i).getStarttime());
                	endtime=String.valueOf(ac.get(i).getEndtime());
                	margin=ac.get(i).getMargin();
                	creatTime=ac.get(i).getCreatTime();
                	contractstatus=ac.get(i).getContractstatus();
        			sql="SELECT ROUND(sum(tolstere),4) as tvolume FROM outyard WHERE contractnum='"+contractnum+"'";
        			 salecontract st=sd.findUseTreenum(sql);
        			 useTreenum=String.valueOf(st.getUseTreenum());
        			//System.out.print(useTreenum);
        			// String usetreenum1="";
        			 if(useTreenum.equals("null") || useTreenum.isEmpty())
        			    {
        				 useTreenum="0";
        				 surplusTreenum=treenumber;
        				 percentageTreenum="0";
        				// System.out.print(useTreenum);
        			    }
        			    else {
        			    	//System.out.print(useTreenum);
        			    	double totalSurplus=Double.parseDouble(treenumber)-Double.parseDouble(useTreenum);
        		 		    surplusTreenum=String.valueOf(df.format(totalSurplus));
        		 		   double totalImplement1=(Double.parseDouble(useTreenum)/Double.parseDouble(treenumber));
        	 			   percentageTreenum=String.valueOf(decimalFormat.format(totalImplement1));
        			    }
        			 cn.setContractid(contractid);
        			 cn.setContractnum(contractnum);
        			 cn.setProvider(provider);
        			 cn.setDemander(demander);
        			 cn.setContractsigner(contractsigner);
        			 cn.setTreenumber(treenumber);
        			 cn.setBegin(starttime);
        			 cn.setEnd(endtime);
        			 cn.setMargin(margin);
        			 cn.setCreatTime(creatTime);
        			 cn.setContractstatus(contractstatus);
        			 cn.setUseTreenum(useTreenum);
        			 cn.setSurplusTreenum(surplusTreenum);
        			 cn.setPercentageTreenum(percentageTreenum);
        			 //cnt.add(cn);
        			 list.put(cn);
        		}
        		out.print(list);
        	
        	}
        	//mapper.writeValue(response.getWriter(),ac);
        }
        //在页面显示合同详细信息
        else if("contractList".equals(action)) {
        	String str=request.getParameter("contractid");
        	str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	sql="SELECT contractnum,provider,demander,contractsigner,deliveryplace,section,\r\n" + 
        			"treenumber,cost,costB,measurestard,settlemethod,starttime,endtime,contractfile,margin,marknumber,cutnum from sale_contract WHERE sale_contract_id="+number+"";
        	salecontract salecontract=sd.findcontract(sql);
        	String contractfile=salecontract.getContractfile();
        	contractfile = contractfile.substring(contractfile.lastIndexOf("/") + 1);//去掉储存路径斜杠
        	sql="SELECT treetype,tlong,tradius,unitprice from sale_contract_tree WHERE sale_contract_id="+number+" and unitprice <> 0";
        	List<salecontract> salecontractTree=sd.fincontractTree(sql);
        	sql="SELECT sum(o.tolstere) as tolstere,s.treenumber from outyard as o join sale_contract as s on o.contractnum=s.contractnum WHERE s.sale_contract_id="+number+"";
        	contractProgress contractProgress=sd.findconProgress(sql);
        	double tolstere=contractProgress.getTotalStere();
        	double treenumber=contractProgress.getTreenumber();
        	double treebaifen=tolstere/treenumber;
        	DecimalFormat df1 = new DecimalFormat("##.00%");
        	String treebaifenb = df1.format(treebaifen);
        	request.setAttribute("treebaifenb", treebaifenb);
        	request.setAttribute("contractfile", contractfile);
        	request.setAttribute("salecontract", salecontract);
        	request.setAttribute("salecontractTree", salecontractTree);
        	request.setAttribute("contractProgress", contractProgress);
        
        	request.getRequestDispatcher("salaryContractUpdate.jsp").forward(request, response);
        }
        else if("finishContract".equals(action)) {
        	String str=request.getParameter("contractid");
        	str=str.replace("'", "");
        	int number=Integer.parseInt(str);
        	salecontract cn = new salecontract();
        	cn.setContractstatus(1);
        	int flag=sd.updateContractStatus(cn, number);
        	if (flag>0) {
        		out.print("合同完成,2秒后返回界面<script>setTimeout(\"window.location.href ='salaryContractList.jsp';\", 2000);</script>");
        	}
        	else
        		out.print("操作失败");
        }
        //合同进度
        else if("contractListProg".equals(action)) {
        	Map map=new HashMap();
        	String str=request.getParameter("contractid");
        	//str=str.replace("'", "");
        	double number=Double.parseDouble(str);
        	sql="SELECT sum(o.tolstere) as tolstere,s.treenumber from outyard as o join sale_contract as s on o.contractnum=s.contractnum WHERE s.sale_contract_id="+number+"";
        	contractProgress contractProgress=sd.findconProgress(sql);
    		//mapper.writeValue(response.getWriter(),contractProgress);
    		//out.print(contractProgress);
        	double tolstere=contractProgress.getTotalStere();
        	double treenumber=contractProgress.getTreenumber();
        	double treebaifen=tolstere/treenumber;
        	DecimalFormat df1 = new DecimalFormat("##.00%");
        	String treebaifenb = df1.format(treebaifen);
    		//map.put("contractProgress", treebaifenb);
    		//System.out.print(treebaifenb);
    		//mapper.writeValue(response.getWriter(), map);
        	request.setAttribute("contractProgress", contractProgress);
        	request.getRequestDispatcher("salaryContractUpdate.jsp").forward(request, response);
        }
        //暂时没用
        else if("treeAddcon".equals(action)) {
        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
        	String contractnum = request.getParameter("contractnum");
        	double treenumber=Double.parseDouble(request.getParameter("treenumber"));
        	sql="select sale_contract_id from sale_contract WHERE contractnum='"+contractnum+"' and treenumber='"+treenumber+"'";
            salecontract cid=sd.findsalecontractid(sql);//获取合同单号
            int contractid=cid.getContractid();
        	for(int i=0;i<id;i++)
        	{
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		salecontract sc=new salecontract();
        		sc.setContractid(contractid);
        		sc.setTreetype(s.getString(0));
            	sc.setTlong(s.getString(1));
            	sc.setTradius(s.getString(2));
            	sc.setUnitprice(Double.parseDouble(s.getString(3)));
            	int flags=sd.addContractTree(sc);
            	out.print(flags);
        	}
        }
        //销售调令
        else if("salecalloutAdd".equals(action)) {
            String rebate = request.getParameter("newtree");
    	    int id=Integer.parseInt(request.getParameter("id"));
    	    JSONObject jb = JSONObject.fromObject(rebate);
    	    String saleCalloutOrder = request.getParameter("saleCalloutOrder");
    	    String contractnum = request.getParameter("contractnum");
    	    String yardname = request.getParameter("yardname");
    	    String demander=request.getParameter("demander");
    	    String section = request.getParameter("section");//去掉票据
    	    String paymentamount = request.getParameter("Paymentamount");//货款金额
    	    String signer = request.getParameter("Signer");
    	    String totalnum = request.getParameter("totalnum");
    	    String callidtime = request.getParameter("callidtime");//调运时间
    	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    	    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	    Date date=null;
    	    String date1="";
    	    try {
				date = sdf1.parse(callidtime);
				date1=sdf2.format(date);
				System.out.print(date1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    
    	    
    	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        	Date calliddate=null;
        	try {
        		calliddate = new java.sql.Timestamp(format.parse(date1).getTime());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = format1.format(new Date());
			//System.out.println(createtime);
			Timestamp Time = Timestamp.valueOf(createtime);
    	    saleCalloutOrder sc=new saleCalloutOrder();
    	    sc.setSaleCalloutOrder(saleCalloutOrder);
    	    sc.setContractnum(contractnum);
    	    sc.setYard(yardname);
    	    sc.setDemander(demander);
    	    sc.setSection(section);
    	    sc.setPaymentamount(paymentamount);
    	    sc.setTotalnum(totalnum);
    	    sc.setSigner(signer);
    	    sc.setCallidtime(calliddate);
    	    sc.setCreattime(Time);
    	    sc.setSalestatus(0);//销售调令状态，0：表示添加未审核，1：审核通过，2审核未通过，3：审核未通过补交材料
    	    int flag=sd.addCalloutOrder(sc);
    	    if (flag>0) {
    	    	sql="SELECT  sale_order_id from sale_callout_orderid WHERE sale_callout_order_id='"+saleCalloutOrder+"'";
    	    	saleCalloutOrder fsd=sd.findsaleorderid(sql);
                int saleCallid=fsd.getSaleCallid();
    	       for(int i=0;i<id;i++)
        	  {
        		JSONArray s=jb.getJSONArray(String.valueOf(i));
        		 saleCalloutOrder sco=new saleCalloutOrder();
        		 sco.setSaleCallid(saleCallid);
        		 sco.setTreetype(s.getString(0));
            	 sco.setTlong(s.getString(1));
            	 sco.setTradius(s.getString(2));
            	int flags=sd.addCalloutOrderTree(sco);
            	out.print(flags);
        	}
    	    }
        }
        /*else if("seesalecalloutorder".equals(action)) {
        	//String saleCalloutOrder=request.getParameter("saleCalloutOrder");
        	//System.out.print(saleCalloutOrder);
        	//按时间降序
        	//sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer from sale_callout_orderid ORDER BY callidtime desc";
        	sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer,sale_status from sale_callout_orderid";
        	//List<saleCalloutOrder> cw=sd.findsaleCallid(sql);
        	List<saleCalloutOrder> cw=sd.findsaleCallout(sql);
        	mapper.writeValue(response.getWriter(), cw);
        }*/
        //显示调令台账
        else if("seesalecalloutorder".equals(action)) {
        	saleCalloutOrder cn = new saleCalloutOrder();
        	JSONArray list = new JSONArray();
        	//按时间降序
        	//sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer from sale_callout_orderid ORDER BY callidtime desc";
        	sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer,sale_status from sale_callout_orderid";
        	List<saleCalloutOrder> cw=sd.findsaleCallout(sql);
        	for(int i=1;i<cw.size();i++)
        	{
        		int saleCallid=cw.get(i).getSaleCallid();
        		String callidtime=String.valueOf(cw.get(i).getCallidtime());
        		String contractnum=cw.get(i).getContractnum();
        		String saleCalloutOrder=cw.get(i).getSaleCalloutOrder();
        		String yard=cw.get(i).getYard();
        		String section=cw.get(i).getDemander();
        		String demander=cw.get(i).getDemander();
        		String paymentamount=cw.get(i).getPaymentamount();
        		String totalnum=cw.get(i).getTotalnum();
        		String signer=cw.get(i).getSigner();
        		int salestatus=cw.get(i).getSalestatus();
        		sql="select GROUP_CONCAT(surveyor) as surveyort from sale_callout_orderid_surveyor WHERE sale_order_id='"+saleCallid+"' GROUP BY sale_order_id";
        		saleCalloutOrder survey=sd.findsaleSurveyor(sql);
        		String surveyor=survey.getSurveyor();
        		cn.setSaleCallid(saleCallid);
        		cn.setUseTime(callidtime);
        		cn.setContractnum(contractnum);
        		cn.setSaleCalloutOrder(saleCalloutOrder);
        		cn.setYard(yard);
        		cn.setSection(section);
        		cn.setDemander(demander);
        		cn.setPaymentamount(paymentamount);
        		cn.setTotalnum(totalnum);
        		cn.setSigner(signer);
        		cn.setSalestatus(salestatus);
        		cn.setSurveyor(surveyor);
        		list.put(cn);
    		}
    		out.print(list);
        }
        
        //第一次审核调令
        else if("salecallupdate".equals(action)) {
        	String str=request.getParameter("saleCalloutOrder");
        	str=str.replace("'", "");
        	sql="SELECT  sale_order_id from sale_callout_orderid WHERE sale_callout_order_id='"+str+"'";
	    	saleCalloutOrder fsd=sd.findsaleorderid(sql);//调令号
            int saleCallid=fsd.getSaleCallid();
        	sql="select sale_callout_order_id,contractnum,yard,section,demander,Paymentamount,totalnum,Signer,callidtime,creattime from sale_callout_orderid WHERE sale_callout_order_id='"+str+"'";
        	saleCalloutOrder saleCalloutOrder=sd.findsaleorder(sql);
        	Date sss=saleCalloutOrder.getCallidtime();
        	String yardname=saleCalloutOrder.getYard();
        	sql="select yardmanager from yardmanage WHERE yardname='"+yardname+"'";
        	yardManage ya=wpd.findyardmanager(sql);//管理人员姓名
        	String yardmanager=ya.getYardmanager();
        	sql="SELECT treetype,tlong,tradius from sale_callout_orderid_tree WHERE sale_order_id="+saleCallid+"";
        	List<saleCalloutOrder> saleOrdertree=sd.findsaletree(sql);
        	System.out.print(sss);
        	sql="SELECT * from surveyor_info";
    		List<surveyor> surv=wpd.findSurveyor(sql);
    		request.setAttribute("surveyor", surv);
        	request.setAttribute("saleCalloutOrder", saleCalloutOrder);
        	request.setAttribute("saleOrdertree", saleOrdertree);
        	request.setAttribute("yardmanager", yardmanager);
        	request.getRequestDispatcher("saleCalloutOrderUpdate.jsp").forward(request, response);
        }
      //已完成调令
        else if("salecallupdateM".equals(action)) {
        	String str=request.getParameter("saleCalloutOrder");
        	str=str.replace("'", "");
        	//double number=Double.parseDouble(str);
        	sql="SELECT  sale_order_id from sale_callout_orderid WHERE sale_callout_order_id='"+str+"'";
	    	saleCalloutOrder fsd=sd.findsaleorderid(sql);//调令号
            int saleCallid=fsd.getSaleCallid();
        	sql="select sale_callout_order_id,contractnum,yard,section,demander,Paymentamount,totalnum,Signer,callidtime,creattime from sale_callout_orderid WHERE sale_callout_order_id='"+str+"'";
        	saleCalloutOrder saleCalloutOrder=sd.findsaleorder(sql);
        	Date sss=saleCalloutOrder.getCallidtime();
        	String yardname=saleCalloutOrder.getYard();
        	
        	String contractnum=saleCalloutOrder.getContractnum();
        	sql="SELECT provider,marknumber from sale_contract WHERE contractnum='"+contractnum+"'";
        	salecontract contractPrivoder=sd.findcontractPrivode(sql);
        	
        	sql="select yardmanager from yardmanage WHERE yardname='"+yardname+"'";
        	yardManage ya=wpd.findyardmanager(sql);//管理人员姓名
        	String yardmanager=ya.getYardmanager();
        	sql="SELECT treetype,tlong,tradius from sale_callout_orderid_tree WHERE sale_order_id="+saleCallid+"";
        	List<saleCalloutOrder> saleOrdertree=sd.findsaletree(sql);
        	//System.out.print(sss);
        	sql="SELECT * from sale_callout_orderid_surveyor WHERE sale_order_id="+saleCallid+"";
    		List<surveyor> surv=wpd.findSurveyor(sql);
    		request.setAttribute("surveyor", surv);
        	request.setAttribute("saleCalloutOrder", saleCalloutOrder);
        	request.setAttribute("saleOrdertree", saleOrdertree);
        	request.setAttribute("yardmanager", yardmanager);
        	request.getRequestDispatcher("saleCalloutOrderUpdateFinish.jsp").forward(request, response);
        	
        	Date d1 = null;
			try {
				d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(saleCalloutOrder.getCallidtime()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//定义起始日期
        	SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");
        	SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        	SimpleDateFormat sdf2= new SimpleDateFormat("dd");
        	SimpleDateFormat sdf3= new SimpleDateFormat("HH");
        	String str0 = sdf0.format(d1);//年
        	String str1 = sdf1.format(d1);//月
        	String str2 = sdf2.format(d1);//日
        	String str3 = sdf3.format(d1);//时
        	
        	//FileInputStream file = new FileInputStream(new File("f:\\saleCallout.xls"));
        	FileInputStream file = new FileInputStream(new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 9.0\\webapps\\jinsenZ\\WEB-INF\\PrintsaleCallout\\saleCallout.xls"));
			HSSFWorkbook workbook =new HSSFWorkbook(file);
      //   	HSSFSheet sheet = workbook.createSheet("Sample sheet445");
 	        HSSFSheet sheet = workbook.getSheet("Sheet1");       	
 	        int a=sheet.getFirstRowNum()+80;//从80列开始
 	        int b=sheet.getLastRowNum();
        	try {
        				System.out.print("已经创建好文件，进入到sheet1");
        				System.out.print(b);
                        Row row = sheet.getRow(80);//获取第80行
//        				// 这个判断很必要  确保下面cell操作顺利执行
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
        				    //Row row = sheet.createRow(80);
        	                Cell cell = row.createCell(16);//第16列
        	                cell.setCellValue((String)saleCalloutOrder.getSaleCalloutOrder());//调令
        	                
							Cell cell1 = row.createCell(17);
							cell1.setCellValue((String)saleCalloutOrder.getContractnum());
							
						
							Cell cell2 = row.createCell(18);
							cell2.setCellValue((String)saleCalloutOrder.getYard());
							
							  Cell cell3 = row.createCell(19);
							  cell3.setCellValue((String)saleCalloutOrder.getSection());
							
							  
							  Cell cell4 = row.createCell(20);
							  cell4.setCellValue((String)saleCalloutOrder.getDemander());
							 
							 Cell cell5 = row.createCell(21);
							  cell5.setCellValue((String)saleCalloutOrder.getTotalnum());
							
							  Cell cell6 = row.createCell(22);
							  cell6.setCellValue(String.valueOf(saleCalloutOrder.getCallidtime()));
							  System.out.print(saleCalloutOrder.getCallidtime());
							  
							  Cell cell7 = row.createCell(23);
							  cell7.setCellValue(yardmanager);
							  
							  Cell cell8 = row.createCell(24);
							  cell8.setCellValue(saleCalloutOrder.getSigner());
						
							  Cell cell9 = row.createCell(25);
							  cell9.setCellValue(contractPrivoder.getProvider());
							  
							  Cell cell10 = row.createCell(26);
							  cell10.setCellValue(str0);
							  
							  Cell cell11 = row.createCell(27);
							  cell11.setCellValue(str1);
						
							  Cell cell12 = row.createCell(28);
							  cell12.setCellValue(str2);
							  
							  Cell cell13 = row.createCell(29);
							  cell13.setCellValue(str3);
							  
								 for (int i=0;i<saleOrdertree.size();i++) {
				        	            
									Row rowx = sheet.createRow(i+82);//从82行开始
				        	            Cell cella = rowx.createCell(16);//第16列
				        	            cella.setCellValue((String) saleOrdertree.get(i).getTreetype());
				        	            Cell cellb = rowx.createCell(17);
				        	            cellb.setCellValue((String)saleOrdertree.get(i).getTlong());
				        	            Cell cellc = rowx.createCell(18);
				        	            cellc.setCellValue((String) saleOrdertree.get(i).getTradius());
				        	            Cell   celld = rowx.createCell(19);
				        	            celld.setCellValue((double) saleOrdertree.get(i).getTnum());
								  
								 }
								 sheet.setForceFormulaRecalculation(true);
        	     file.close();
        		
        	    FileOutputStream out1=new FileOutputStream(new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 9.0\\webapps\\jinsenZ\\WEB-INF\\PrintsaleCallout\\saleCallout.xls"));
        	    //FileOutputStream out1=new FileOutputStream(new File("f:\\saleCallout.xls"));
        	    workbook.write(out1);
				/*
				 * sheet = workbook.getSheetAt(0); Iterator<Row> rowIte = sheet.iterator();
				 * while(rowIte.hasNext()){ rowIte.next(); rowIte.remove(); }
				 * System.out.println("已删除完毕");
				 */
        	    file.close();
        	    out1.close();
        	    workbook.close();
        	    System.out.println("Excel written successfully..");
        	   
        	} catch (FileNotFoundException e) {
        	    e.printStackTrace();
        	} catch (IOException e) {
        	    e.printStackTrace();
        	}
        }
      //修改调令
        else if("salecallupdateF".equals(action)) {
        	String str=request.getParameter("saleCalloutOrder");
        	str=str.replace("'", "");
        	//double number=Double.parseDouble(str);
        	sql="SELECT  sale_order_id from sale_callout_orderid WHERE sale_callout_order_id='"+str+"'";
	    	saleCalloutOrder fsd=sd.findsaleorderid(sql);//调令号
            int saleCallid=fsd.getSaleCallid();
        	sql="select sale_callout_order_id,contractnum,yard,section,demander,Paymentamount,totalnum,Signer,callidtime from sale_callout_orderid WHERE sale_order_id="+str+"";
        	saleCalloutOrder saleCalloutOrder=sd.findsaleorder(sql);
        	sql="SELECT treetype,tlong,tradius from sale_callout_orderid_tree WHERE sale_order_id="+saleCallid+"";
        	List<saleCalloutOrder> saleOrdertree=sd.findsaletree(sql);
        	//System.out.print(saleOrdertree);
        	request.setAttribute("saleCalloutOrder", saleCalloutOrder);
        	request.setAttribute("saleOrdertree", saleOrdertree);
        	request.getRequestDispatcher("saleCalloutOrderUpdateModify.jsp").forward(request, response);
        }
        //审核销售调令//销售调令状态，0：表示添加未审核，1：审核通过，2审核未通过，3：审核调令补充，4：调令成功
        else if("writesale".equals(action))
        {
        	String mytype = request.getParameter("type");
        	if(mytype.equals("all"))
        	{
        		//sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer from sale_callout_orderid ORDER BY callidtime desc";
            	//List<saleCalloutOrder> work=sd.findsaleCallid(sql);
        		sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer,sale_status from sale_callout_orderid ORDER BY callidtime desc";
            	List<saleCalloutOrder> work=sd.findsaleCallout(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//未通过
        	else if(mytype.equals("notpass"))
        	{
        		sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer,sale_status from sale_callout_orderid WHERE sale_status=2";
        		List<saleCalloutOrder> work=sd.findsaleCallout(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//通过
        	else if(mytype.equals("pass"))
        	{
        		sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer,sale_status from sale_callout_orderid WHERE sale_status=1";
        		List<saleCalloutOrder> work=sd.findsaleCallout(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//未审核
        	else if(mytype.equals("notshenhe"))
        	{
        		sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer,sale_status from sale_callout_orderid WHERE sale_status=0";
        		List<saleCalloutOrder> work=sd.findsaleCallout(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//审核后补充材料
        	else if(mytype.equals("buchong"))
        	{
        		sql="select sale_order_id,callidtime,contractnum,sale_callout_order_id,yard,section,demander,Paymentamount,totalnum,Signer,sale_status from sale_callout_orderid WHERE sale_status=3";
        		List<saleCalloutOrder> work=sd.findsaleCallout(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	//货场管理员审核通过的调令
        	else if(mytype.equals("shenhepass"))
        	{
        		sql="select sale_callout_order_id,contractnum,demander,yard,totalnum,callidtime from sale_callout_orderid WHERE sale_status=1";
        		List<saleCalloutOrder> work=sd.findsaleCallpass(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        }
        	//删除内容销售调令从数据库中
        	else if("alldelete".equals(action))
            {
            	String mygroup = request.getParameter("alldelete");
            	//workpage ac=new workpage();
            	sql="delete from sale_callout_orderid where sale_callout_order_id='"+mygroup+"'";
            	int i=sd.delWorkPage(sql);
            	sql="SELECT  sale_order_id from sale_callout_orderid WHERE sale_callout_order_id='"+mygroup+"'";
    	    	saleCalloutOrder fsd=sd.findsaleorderid(sql);//调令号
                int saleCallid=fsd.getSaleCallid();
                sql="delete from sale_callout_orderid_tree where sale_order_id='"+saleCallid+"'";
            	int i1=sd.delWorkPage(sql);
            	//System.out.println("...." +i + "...");
            	ObjectMapper mapper1=new ObjectMapper();
        		mapper1.writeValue(response.getWriter(),i1);
        		out.print(i1);	
            }
        //销售部门审核销售调令
        	else if("worksaleyesorno".equals(action)) {
            	String mytype = request.getParameter("type");
            	String rebate = request.getParameter("newtree");
        	    int id=Integer.parseInt(request.getParameter("id"));
        	    JSONObject jb = JSONObject.fromObject(rebate);
            	String saleCalloutOrder=request.getParameter("saleCalloutOrder");
            	sql="SELECT  sale_order_id from sale_callout_orderid WHERE sale_callout_order_id='"+saleCalloutOrder+"'";
    	    	saleCalloutOrder fsd=sd.findsaleorderid(sql);
                int saleCallid=fsd.getSaleCallid();
            	if(mytype.equals("yes")) {
            		saleCalloutOrder sc=new saleCalloutOrder();
            		sc.setSalestatus(1);
            		int flag=sd.updateSaleCallorder(sc,saleCalloutOrder);
            		if(flag>0){
            			 for(int i=0;i<id;i++)
                   	  {
                   		JSONArray s=jb.getJSONArray(String.valueOf(i));
                   		saleCalloutOrder sco=new saleCalloutOrder();
                   		sco.setSaleCallid(saleCallid);
                   		sco.setSurveyor(s.getString(0));
                   		int flags=sd.addCalloutSurveyor(sco);
                   		out.print(flags);
                     }
            	}
            		else
            			out.print(flag);
            	}
            	else if(mytype.equals("not")) {
            		saleCalloutOrder sc=new saleCalloutOrder();
            		sc.setSalestatus(2);//审核未通过
            		int flag=sd.updateSaleCallorder(sc,saleCalloutOrder);
            		if(flag>0){
            			out.print(flag);
            			}
            	}
            }
        //更新销售调令
        	else if("updatesaleCallout".equals(action)) {
        		 String rebate = request.getParameter("newtree");
         	    int id=Integer.parseInt(request.getParameter("id"));
         	    JSONObject jb = JSONObject.fromObject(rebate);
         	    String saleCalloutOrder = request.getParameter("saleCalloutOrder");
         	    String contractnum = request.getParameter("contractnum");
         	    String yard = request.getParameter("yardname");
         	   //System.out.println("...." +yard + "...");
         	    String demander=request.getParameter("demander");
         	    String section = request.getParameter("section");//去掉票据
         	    String paymentamount = request.getParameter("Paymentamount");//货款金额
         	    String signer = request.getParameter("Signer");
         	    String totalnum = request.getParameter("totalnum");
         	    String callidtime = request.getParameter("callidtime");//货款金额
         	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
             	Date d = null;
     			try {
     				d = format.parse(callidtime);
     			} catch (ParseException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			} 
     			java.sql.Date calliddate = new java.sql.Date(d.getTime()); 
        	    saleCalloutOrder sc=new saleCalloutOrder();
        	    sc.setSaleCalloutOrder(saleCalloutOrder);
        	    sc.setContractnum(contractnum);
        	    sc.setYard(yard);
        	    sc.setDemander(demander);
        	    sc.setSection(section);
        	    sc.setPaymentamount(paymentamount);
        	    sc.setTotalnum(totalnum);
        	    sc.setSigner(signer);
        	    sc.setCallidtime(calliddate);
        	    sc.setSalestatus(3);//销售调令状态，0：表示添加未审核，1：审核通过，2审核未通过，3：审核未通过补交材料，4：调令完成
        	    sql="SELECT  sale_order_id FROM sale_callout_orderid where sale_callout_order_id='"+saleCalloutOrder+"'";
        	    saleCalloutOrder fsd=sd.findsaleorderid(sql);
                int saleCallid=fsd.getSaleCallid();
                //System.out.print(saleCallid);
        	    sql="SELECT COUNT(*) FROM sale_callout_orderid WHERE sale_order_id="+saleCallid+"";
        	    double f=sd.findcount(sql);
        	    if(f==1) {
        	    	int flag=sd.updateSaleCallOrderid(sc, saleCallid);
        	    	if(flag>0) {
        	    		for(int i=0;i<id;i++)
                  	  {
                  		JSONArray s=jb.getJSONArray(String.valueOf(i));
                  		 saleCalloutOrder sco=new saleCalloutOrder();
                  		 sco.setSaleCallid(saleCallid);
                  		 sco.setTreetype(s.getString(0));
                      	 sco.setTlong(s.getString(1));
                      	 sco.setTradius(s.getString(2));
                      	 int flagt=sd.addCalloutOrderTree1(sco);
                      	out.print(flagt);
        	    	}
        	    }
        	}
        	    else {
        	    	out.print("不能改变销售调令");
        	    }
        	}
        //货场信息
        	else if(action.equals("loadyardinfo")) {
        		sql="SELECT yardname from yardmanage";
        		List<yardManage> yardname=sd.findyardname(sql);
        		mapper.writeValue(response.getWriter(),yardname);
        	}
      //第一次审核调令
            else if("salecallupdate1".equals(action)) {
            	String str=request.getParameter("saleCallid");
            	str=str.replace("'", "");
            	double number=Double.parseDouble(str);
            	//System.out.println("...."+number + "...");
            	sql="select sale_callout_order_id,contractnum,yard,section,demander,Paymentamount,totalnum,Signer,callidtime,creattime from sale_callout_orderid WHERE sale_order_id="+number+"";
            	saleCalloutOrder saleCalloutOrder=sd.findsaleorder(sql);
            	String yardname=saleCalloutOrder.getYard();
            	sql="select yardmanager from yardmanage WHERE yardname='"+yardname+"'";
            	yardManage ya=wpd.findyardmanager(sql);//管理人员姓名
            	String yardmanager=ya.getYardmanager();
            	String[] sourceStrArray = yardmanager.split("、");
                for (int i = 0; i < sourceStrArray.length; i++) {
                    //System.out.println(sourceStrArray[i]);
                }
                System.out.println(sourceStrArray);
            	sql="SELECT treetype,tlong,tradius from sale_callout_orderid_tree WHERE sale_order_id="+number+"";
            	List<saleCalloutOrder> saleOrdertree=sd.findsaletree(sql);
            	//System.out.print(yardmanager);
            	sql="SELECT * from surveyor_info";
        		List<surveyor> surv=wpd.findSurveyor(sql);
        		request.setAttribute("surveyor", surv);
            	request.setAttribute("saleCalloutOrder", saleCalloutOrder);
            	request.setAttribute("saleOrdertree", saleOrdertree);
            	request.setAttribute("yardmanager", yardmanager);
            	request.setAttribute("sourceStrArray", sourceStrArray);
            	request.getRequestDispatcher("saleCalloutOrderUpdate.jsp").forward(request, response);
            }
        //货场分区
        	else if(action.equals("loadyardsection")) {
        		String yardname = request.getParameter("yardname");
        		sql="SELECT section from yard_section WHERE yardname='"+yardname+"'";
        		List<yardManage> section=sd.findyardSec(sql);
        		mapper.writeValue(response.getWriter(),section);
        	}
        //录入检尺数据
        	else if("luruJianchi".equals(action)) {
        		String str=request.getParameter("saleCalloutOrder");
            	str=str.replace("'", "");
            	sql="select s.sale_callout_order_id,s.contractnum,s.yard,s.section,s.demander,s.Paymentamount,s.totalnum,s.Signer,s.callidtime,s.creattime,c.cutnum from sale_callout_orderid s JOIN sale_contract c on c.contractnum=s.contractnum WHERE sale_callout_order_id='"+str+"'";
            	//saleCalloutOrder saleCalloutOrder=sd.findsaleorder(sql);
            	saleCalloutOrder saleCalloutOrder=sd.findsaleorderCutnum(sql);
            	String contractnum=saleCalloutOrder.getContractnum();
            	sql="SELECT provider,marknumber from sale_contract WHERE contractnum='"+contractnum+"'";
            	salecontract contractPrivoder=sd.findcontractPrivode(sql);
            	sql="SELECT company,shipaddress from customer WHERE contractnum='"+contractnum+"'";
            	customer compShip=sd.findcutnumAddres(sql);
            	request.setAttribute("saleCalloutOrder", saleCalloutOrder);
            	request.setAttribute("contractPrivoder", contractPrivoder);
            	request.setAttribute("compShip", compShip);
            	request.getRequestDispatcher("treeout.jsp").forward(request, response);
        	}
        //调令完成
        	else if("finishcorder".equals(action)) {
        		String str=request.getParameter("saleCalloutOrder");
            	str=str.replace("'", "");
                saleCalloutOrder sc=new saleCalloutOrder();
        		sc.setSalestatus(4);//调令完成
        		int flag=sd.updateSaleCallorder(sc,str);
        		if(flag>0){
        			out.print("调令完成");
        			}
        	}
        //伐区管理
        	else if("findCutarea".equals(action)) {
        		String cutnum=request.getParameter("cutnum");
            	Map map=new HashMap();
            	sql="select cutnumid,company,certificatenum,forestid,cutnum,cutaddress,cutvillage,quartel,largeblock,smallblock,cuttype,cutmethod,cutqiang,cutarea,starttime,endtime from cutnum where cutnum='"+cutnum+"'";
            	List<cutnum> cutCompack=sd.findById(sql);
            	//System.out.print(cutCompack);
                map.put("cutCompack", cutCompack);
                mapper.writeValue(response.getWriter(), map);
        	}
        	else if(action.equals("findContracynum")) {
            	String contractnum=request.getParameter("contractnum");
            	Map map=new HashMap();
            	sql="SELECT t.treetype,t.tlong,t.tradius,a.demander from sale_contract as a JOIN sale_contract_tree as t ON a.sale_contract_id=t.sale_contract_id WHERE a.contractnum='"+contractnum+"' and t.unitprice<>0 ";
            	List<salecontract> tree=sd.fincontractnum(sql);
            	sql="SELECT measurestard from sale_contract WHERE contractnum='"+contractnum+"'";
            	List<salecontract> measurestard=sd.findMeasurestard(sql);
            	//System.out.print(measurestard);
                map.put("tree", tree);
                map.put("measurestard", measurestard);
                mapper.writeValue(response.getWriter(), map);
            }
        //销售部门木材结算单
        	 else if(action.equals("fiTreeoutSalary")) {
             	String saleCalloutOrder=request.getParameter("saleCalloutOrder");
             	String timeStart=request.getParameter("timeStart");
            	String timeEnd=request.getParameter("timeEnd");
            	String contractnum=request.getParameter("contractnum");
             	Map map=new HashMap();
             	if(!contractnum.isEmpty() && saleCalloutOrder.isEmpty())
             	{
             	sql="SELECT s.sale_callout_order_id,s.contractnum,s.demander,c.provider,s.yard,s.section,c.cutnum from sale_callout_orderid as s join sale_contract as c on s.contractnum=c.contractnum WHERE s.contractnum='"+contractnum+"'";
             	List<treeoutSalary> treeotS=sd.findtreesalaOrder(sql);
             	//sql="select a.workid,b.yard from treeout a join outyard b on a.workid=b.workid WHERE sale_callout_orderid='"+saleCalloutOrder+"' group by workid";
        		sql="select a.workid,b.yard from treeout a join outyard b join treeout_status s on a.workid=b.workid and b.workid=s.workid \r\n" + 
        				"WHERE contractnum='"+contractnum+"' and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"' and s.outStatus=0 group by a.workid,b.yard";
             	List<singleworkid> workid=sd.findworkidYard(sql);//显示工程包信息
         		//sql="select treetype,tlong,tradius,num,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum='"+cutnum+"' group by treetype,tradius";
         		sql="select (case WHEN treeid='1' THEN '杉小径' WHEN treeid='2' THEN '杉原木' WHEN treeid='3' THEN '松小径' WHEN treeid='4' THEN '松原木' WHEN treeid='5' THEN '杂小径' WHEN treeid='6' THEN '杂原木' else treetype END) as treetype1,\r\n" + 
         				"tlong,(case  WHEN  tradius>0 and tradius<3 THEN '1-2' WHEN  tradius>=3 and tradius<8 THEN '3-7' WHEN tradius>=8 and tradius<10 THEN '8-9'  WHEN tradius>=10 and tradius<13 THEN '10-12' WHEN tradius>=14 and tradius<19 THEN '14-18' WHEN tradius>=20 and tradius<25 THEN '20-24' else '26以上' END) as tradius1,\r\n" + 
         				"sum(DISTINCT num) as tnum,round(sum(DISTINCT tvolume),2) as ttvolume,unitprice,round(sum(DISTINCT tvolume)*unitprice,2) as totalprice  from treeout a join outyard b join treeout_status s on a.workid=b.workid and b.workid=s.workid where b.contractnum='"+contractnum+"'and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"' and s.outStatus=0 GROUP BY treetype1,tlong,tradius1,unitprice";
         		List<worktree> worktree=sd.findworktreeSalary(sql);
                 		map.put("treeotS", treeotS);
                 		map.put("workid", workid);
                 		map.put("worktree",worktree);
                 		mapper.writeValue(response.getWriter(), map);
             	}
             	else if(!contractnum.isEmpty() && !saleCalloutOrder.isEmpty()){
             		sql="SELECT s.sale_callout_order_id,s.contractnum,s.demander,c.provider,s.yard,s.section,c.cutnum from sale_callout_orderid as s join sale_contract as c on s.contractnum=c.contractnum WHERE s.sale_callout_order_id='"+saleCalloutOrder+"' and c.contractnum='"+contractnum+"'";
                 	List<treeoutSalary> treeotS=sd.findtreesalaOrder(sql);
                 	//sql="select a.workid,b.yard from treeout a join outyard b on a.workid=b.workid WHERE sale_callout_orderid='"+saleCalloutOrder+"' group by workid";
            		sql="select a.workid,b.yard from treeout a join outyard b join treeout_status s on a.workid=b.workid and b.workid=s.workid \r\n" + 
            				"WHERE sale_callout_orderid='"+saleCalloutOrder+"' and contractnum='"+contractnum+"' and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"' and s.outStatus=0 group by a.workid,b.yard";
                 	List<singleworkid> workid=sd.findworkidYard(sql);//显示工程包信息
             		//sql="select treetype,tlong,tradius,num,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum='"+cutnum+"' group by treetype,tradius";
             		sql="select (case WHEN treeid='1' THEN '杉小径' WHEN treeid='2' THEN '杉原木' WHEN treeid='3' THEN '松小径' WHEN treeid='4' THEN '松原木' WHEN treeid='5' THEN '杂小径' WHEN treeid='6' THEN '杂原木' else treetype END) as treetype1,\r\n" + 
             				"tlong,(case  WHEN  tradius>0 and tradius<3 THEN '1-2' WHEN  tradius>=3 and tradius<8 THEN '3-7' WHEN tradius>=8 and tradius<10 THEN '8-9'  WHEN tradius>=10 and tradius<13 THEN '10-12' WHEN tradius>=14 and tradius<19 THEN '14-18' WHEN tradius>=20 and tradius<25 THEN '20-24' else '26以上' END) as tradius1,\r\n" + 
             				"sum(DISTINCT num) as tnum,round(sum(DISTINCT tvolume),2) as ttvolume,unitprice,round(sum(DISTINCT tvolume)*unitprice,2) as totalprice  from treeout a join outyard b join treeout_status s on a.workid=b.workid and b.workid=s.workid where b.sale_callout_orderid='"+saleCalloutOrder+"'and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"' and b.contractnum='"+contractnum+"' and s.outStatus=0 GROUP BY treetype1,tlong,tradius1,unitprice";
             		List<worktree> worktree=sd.findworktreeSalary(sql);
                     		map.put("treeotS", treeotS);
                     		map.put("workid", workid);
                     		map.put("worktree",worktree);
                     		mapper.writeValue(response.getWriter(), map);
             	}
             	else if(contractnum.isEmpty() && !saleCalloutOrder.isEmpty()){
             		sql="SELECT s.sale_callout_order_id,s.contractnum,s.demander,c.provider,s.yard,s.section,c.cutnum from sale_callout_orderid as s join sale_contract as c on s.contractnum=c.contractnum WHERE s.sale_callout_order_id='"+saleCalloutOrder+"'";
                 	List<treeoutSalary> treeotS=sd.findtreesalaOrder(sql);
                 	//sql="select a.workid,b.yard from treeout a join outyard b on a.workid=b.workid WHERE sale_callout_orderid='"+saleCalloutOrder+"' group by workid";
            		sql="select a.workid,b.yard from treeout a join outyard b join treeout_status s on a.workid=b.workid and b.workid=s.workid \r\n" + 
            				"WHERE sale_callout_orderid='"+saleCalloutOrder+"' and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"' and s.outStatus=0 group by a.workid,b.yard";
                 	List<singleworkid> workid=sd.findworkidYard(sql);//显示工程包信息
             		//sql="select treetype,tlong,tradius,num,sum(tvolume) as total,unitprice,(sum(tvolume)*unitprice) as totalprice  from tree a join inyard b on a.workid=b.workid where b.cutNum='"+cutnum+"' group by treetype,tradius";
             		sql="select (case WHEN treeid='1' THEN '杉小径' WHEN treeid='2' THEN '杉原木' WHEN treeid='3' THEN '松小径' WHEN treeid='4' THEN '松原木' WHEN treeid='5' THEN '杂小径' WHEN treeid='6' THEN '杂原木' else treetype END) as treetype1,\r\n" + 
             				"tlong,(case  WHEN  tradius>0 and tradius<3 THEN '1-2' WHEN  tradius>=3 and tradius<8 THEN '3-7' WHEN tradius>=8 and tradius<10 THEN '8-9'  WHEN tradius>=10 and tradius<13 THEN '10-12' WHEN tradius>=14 and tradius<19 THEN '14-18' WHEN tradius>=20 and tradius<25 THEN '20-24' else '26以上' END) as tradius1,\r\n" + 
             				"sum(DISTINCT num) as tnum,round(sum(DISTINCT tvolume),2) as ttvolume,unitprice,round(sum(DISTINCT tvolume)*unitprice,2) as totalprice  from treeout a join outyard b join treeout_status s on a.workid=b.workid and b.workid=s.workid where b.sale_callout_orderid='"+saleCalloutOrder+"'and b.yarddate>='"+timeStart+"' and b.yarddate<='"+timeEnd+"' and s.outStatus=0 GROUP BY treetype1,tlong,tradius1,unitprice";
             		List<worktree> worktree=sd.findworktreeSalary(sql);
                     		map.put("treeotS", treeotS);
                     		map.put("workid", workid);
                     		map.put("worktree",worktree);
                     		mapper.writeValue(response.getWriter(), map);
             	}
             }
        //销售部门=保存木材销售结算单
             else if(action.equals("saveTreeoutPrice")) {
             	String rebate = request.getParameter("newtree");
             	int id=Integer.parseInt(request.getParameter("id"));
             	JSONObject jb = JSONObject.fromObject(rebate); 
             	String cutnum=request.getParameter("cutnum");
             	String consignee = request.getParameter("consignee");
             	String company = request.getParameter("company");
             	String yard = request.getParameter("yard");
             	String contractnum=request.getParameter("contractnum");
             	String saleCalloutOrder=request.getParameter("saleCalloutOrder");
             	String person=request.getParameter("person");
             	String tnum = request.getParameter("tnum");
             	double  ttvolume=Double.parseDouble(request.getParameter("ttvolume"));
             	double  tprice=Double.parseDouble(request.getParameter("tprice"));
             	String tworkid = request.getParameter("tworkid");
             	//System.out.print(tworkid);
             	String[] as = tworkid.split(",");
             	int ttl=as.length;
             	int flag=0;
             	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     			String createtime = format1.format(new Date());
     			Timestamp Time = Timestamp.valueOf(createtime);//创建时间
     			for(int i=0;i<id;i++) {
             		JSONArray s=jb.getJSONArray(String.valueOf(i));
             		Laowu cp = new Laowu();
             		cp.setCutnum(cutnum);
             		cp.setConsignee(consignee);
             		cp.setCompany(company);
             		cp.setYard(yard);
             		cp.setContractnum(contractnum);
             		cp.setSaleCalloutOrder(saleCalloutOrder);
             		cp.setTnum(tnum);
             		cp.setttvolume(ttvolume);
             		cp.setTprice(tprice);
             		cp.setTworkid(tworkid);
             		cp.setPerson(person);
             		cp.setTreetype(s.getString(0));
             		cp.setTlong(s.getString(1));
             		cp.setTradius(s.getString(2));
             		cp.setNum(s.getString(3));
             		cp.setTvolume(s.getString(4));
             		cp.setUnitprice(s.getDouble(5));
             		cp.setprice(s.getDouble(6));
             		cp.setCreatTime(Time);
             		flag=sd.addProduceTreeoutsalary(cp);
                 	//out.print(flag);
     			}
     			if(flag>0) {
         			outyard o = new outyard();
         			for(int j=0;j<ttl;j++) {
         				String workid1=as[j];
         				double workid=Double.parseDouble(workid1);
         				o.setOutStatus(1);//已结算
         				int flagh=trd.updateTreeoutStatus(o, workid);
         				out.print(flagh);
         			}
         		}
     			else {
     				out.print(flag);
     			}
             }
        //销售部生产结算台账
             else if("printTreeoutsalary".equals(action)) {
            	 Map map=new HashMap();
            	 sql="SELECT contractnum,saleCalloutOrder,consignee,company,yard,tnum,tvolume,tprice,creatTime FROM produce_treeout_salary GROUP BY contractnum,saleCalloutOrder,consignee,company,yard,tnum,tvolume,tprice,creatTime ORDER BY creatTime desc,contractnum";
            	 List<Laowu> tpd=sd.findtreeoutPridce(sql);
            	 map.put("tpd", tpd);
                 mapper.writeValue(response.getWriter(), tpd);
             }
        //查看销售部门生产结算结算详细
             else if("watchTreeoutPrice".equals(action))
             {
            	String contractnum1=request.getParameter("contractnum");
              	String saleCalloutOrder1 = request.getParameter("saleCalloutOrder");
              	String creatTime1 = request.getParameter("creatTime");
              	String contractnum=contractnum1.replace("'", "");
              	String saleCalloutOrder=saleCalloutOrder1.replace("'", "");
              	String creatTime=creatTime1.replace("'", "");
              	sql="SELECT * from produce_treeout_salary WHERE contractnum='"+contractnum+"' and saleCalloutOrder='"+saleCalloutOrder+"' and creatTime='"+creatTime+"'";
              	List<Laowu> treeout=sd.findtreeoutPridceDet(sql);
              	String wokleng=treeout.get(0).getTworkid();
              	String[] as = wokleng.split(",");
              	int worklength=as.length;
              	request.setAttribute("worklength", worklength);
            	request.setAttribute("treeout", treeout);
            	request.getRequestDispatcher("treeoutPriceDet.jsp").forward(request, response);
            	
            	
            	Date d1 = null;
    			try {
    				d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(creatTime));
    			} catch (ParseException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}//定义起始日期
            	SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");
            	SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
            	SimpleDateFormat sdf2= new SimpleDateFormat("dd");
            	SimpleDateFormat sdf3= new SimpleDateFormat("HH");
            	String str0 = sdf0.format(d1);//年
            	String str1 = sdf1.format(d1);//月
            	String str2 = sdf2.format(d1);//日
            	String str3 = sdf3.format(d1);//时
            	
            	//FileInputStream file = new FileInputStream(new File("f:\\outPrice.xls"));
            	FileInputStream file = new FileInputStream(new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 9.0\\webapps\\jinsenZ\\WEB-INF\\PrintoutPrice\\saleCallout.xls"));
    			HSSFWorkbook workbook =new HSSFWorkbook(file);
          //   	HSSFSheet sheet = workbook.createSheet("Sample sheet445");
     	        HSSFSheet sheet = workbook.getSheet("Sheet1");       	
     	        int a=sheet.getFirstRowNum()+80;//从80列开始
     	        int b=sheet.getLastRowNum();
            	try {
            				System.out.print("已经创建好文件，进入到sheet1");
            				System.out.print(b);
                            Row row = sheet.getRow(80);//获取第80行
//            				// 这个判断很必要  确保下面cell操作顺利执行
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
            				    //Row row = sheet.createRow(80);
            	                Cell cell = row.createCell(16);//第16列
            	                cell.setCellValue((String)treeout.get(0).getSaleCalloutOrder());//调令
            	                
    							Cell cell1 = row.createCell(17);
    							cell1.setCellValue((String)treeout.get(0).getContractnum());
    							
    						
    							Cell cell2 = row.createCell(18);
    							cell2.setCellValue((String)treeout.get(0).getCutnum());
    							
    							  Cell cell3 = row.createCell(19);
    							  cell3.setCellValue((String)treeout.get(0).getCompany());
    							
    							  
    							  Cell cell4 = row.createCell(20);
    							  cell4.setCellValue((String)treeout.get(0).getYard());
    							 
    							 Cell cell5 = row.createCell(21);
    							  cell5.setCellValue((String)treeout.get(0).getConsignee());//购货人
    							
    							  Cell cell6 = row.createCell(22);
    							  cell6.setCellValue(String.valueOf(treeout.get(0).getTnum()));
    							  //System.out.print(treeout.getCallidtime());
    							  
    							  Cell cell7 = row.createCell(23);
    							  cell7.setCellValue(treeout.get(0).getttvolume());
    							  
    							  Cell cell8 = row.createCell(24);
    							  cell8.setCellValue(treeout.get(0).getTprice());
    						
    							  Cell cell9 = row.createCell(25);
    							  cell9.setCellValue(treeout.get(0).getTworkid());
    							  
    							  Cell cell10 = row.createCell(26);
    							  cell10.setCellValue(treeout.get(0).getForperson());
    							  
    							  Cell cell11 = row.createCell(27);
    							  cell11.setCellValue(str0);
    							  
    							  Cell cell12 = row.createCell(28);
    							  cell12.setCellValue(str1);
    						
    							  Cell cell13 = row.createCell(29);
    							  cell13.setCellValue(str2);
    							  
    							  Cell cell14 = row.createCell(30);
    							  cell14.setCellValue(str3);
    							  
    							  Cell cell15 = row.createCell(31);
    							  cell15.setCellValue(String.valueOf(creatTime));
    							  
    							  Cell cell16 = row.createCell(32);
    							  cell16.setCellValue(String.valueOf(worklength));
    							  
    								 for (int i=0;i<treeout.size();i++) {
    				        	            
    									Row rowx = sheet.createRow(i+82);//从82行开始
    				        	            Cell cella = rowx.createCell(16);//第16列
    				        	            cella.setCellValue((String) treeout.get(i).getTreetype());
    				        	            Cell cellb = rowx.createCell(17);
    				        	            cellb.setCellValue((String)treeout.get(i).getTlong());
    				        	            Cell cellc = rowx.createCell(18);
    				        	            cellc.setCellValue((String) treeout.get(i).getTradius());
    				        	            Cell   celld = rowx.createCell(19);
    				        	            celld.setCellValue((String) treeout.get(i).getNum());
    				        	            Cell celle = rowx.createCell(20);
    				        	            celle.setCellValue((String) treeout.get(i).getTvolume());
    				        	            Cell   cellf = rowx.createCell(21);
    				        	            cellf.setCellValue((double) treeout.get(i).getUnitprice());
    				        	            Cell   cellg = rowx.createCell(22);
    				        	            cellg.setCellValue((double) treeout.get(i).getprice());
    								  
    								 }
    								 sheet.setForceFormulaRecalculation(true);
            	     file.close();
            		
            	    //FileOutputStream out1=new FileOutputStream(new File("f:\\outPrice.xls"));
            	    FileOutputStream out1=new FileOutputStream(new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 9.0\\webapps\\jinsenZ\\WEB-INF\\PrintoutPrice\\outPrice.xls"));
            	    workbook.write(out1);
    				/*
    				 * sheet = workbook.getSheetAt(0); Iterator<Row> rowIte = sheet.iterator();
    				 * while(rowIte.hasNext()){ rowIte.next(); rowIte.remove(); }
    				 * System.out.println("已删除完毕");
    				 */
            	    file.close();
            	    out1.close();
            	    workbook.close();
            	    System.out.println("Excel written successfully..");
            	   
            	} catch (FileNotFoundException e) {
            	    e.printStackTrace();
            	} catch (IOException e) {
            	    e.printStackTrace();
            	}
             }
      //查看生产部们生产结算结算详细
             else if("watchProducePrice".equals(action))
             {
            	String cutnum1=request.getParameter("cutnum");
              	String projectPackageName1 = request.getParameter("projectPackageName");
              	String creatTime1 = request.getParameter("creatTime");
              	String cutnum=cutnum1.replace("'", "");
              	String projectPackageName=projectPackageName1.replace("'", "");
              	String creatTime=creatTime1.replace("'", "");
              	sql="SELECT c.proj_package_Name,p.cutnum,p.consignee,p.company,p.yard,p.contractnum,p.saleCalloutOrder,p.tnum,p.tvolume,p.tprice,p.tworkid,p.person,p.treetype,p.tlong,p.tradius,p.num,p.volume,p.unitprice,p.price,p.creatTime\r\n" + 
              			" from produce_tree_salary as p JOIN cutnum as c ON p.cutnum=c.cutnum WHERE p.creatTime='"+creatTime+"' and p.cutnum='"+cutnum+"' and c.proj_package_Name='"+projectPackageName+"'";
              	List<Laowu> treein=sd.findProducePDet(sql);
              	String wokleng=treein.get(0).getTworkid();
              	String[] as = wokleng.split(",");
              	int worklength=as.length;
              	request.setAttribute("worklength", worklength);
            	request.setAttribute("treein", treein);
            	request.getRequestDispatcher("productTreePriceDet.jsp").forward(request, response);
             }
        
      //生产部门生产工资及其他结算台账
             else if("producetreetable".equals(action)) {
            	 String timeStart = request.getParameter("timeStart");
             	String timeEnd = request.getParameter("timeEnd");
             	String projectPackageName = request.getParameter("projectPackageName");
             	String cutnum = request.getParameter("cutnum");
             	String section=request.getParameter("section");
            	String yard=request.getParameter("yard");
            	//System.out.print(timeStart);
            	Map map=new HashMap();
            	sql="SELECT cutnum,proj_package_Name,forperson,manageUnit,totalAll,timeStart,timeEnd,creatTime,yard,section from Laowu_price where 1=1";
            	if(!timeStart.isEmpty() && !timeEnd.isEmpty())
            	{
            		sql=sql+" and timeStart>='"+timeStart+"' and timeEnd<='"+timeEnd+"'";
            	}
            	if(!cutnum.isEmpty())
            	{
            		sql=sql+" and cutnum='"+cutnum+"'";
            	}
            	if(!yard.isEmpty())
            	{
            		sql=sql+" and yard='"+yard+"'";
            	}
            	if(!section.isEmpty())
            	{
            		sql=sql+" and section='"+section+"'";
            	}
            	if(!projectPackageName.isEmpty())
            	{
            		sql=sql+" and proj_package_Name='"+projectPackageName+"'";
            	}
            	sql=sql+" GROUP BY cutnum,proj_package_Name,forperson,manageUnit,totalAll,timeStart,timeEnd,creatTime,yard,section ORDER BY creatTime";
             	System.out.println("...." +timeStart + "...");
                     List<Laowu> dp=sd.printProductpriceT(sql);
                 	mapper.writeValue(response.getWriter(), dp);;
             }
        //生产部门生产工资及其他结算台账
             else if("seeProductPriceT".equals(action))
             {
            	String cutnum1=request.getParameter("cutnum");
              	String projectPackageName1 = request.getParameter("projectPackageName");
              	String creatTime1 = request.getParameter("creatTime");
              	String cutnum=cutnum1.replace("'", "");
              	String projectPackageName=projectPackageName1.replace("'", "");
              	String creatTime=creatTime1.replace("'", "");
              	sql="SELECT * from laowu_price WHERE creatTime='"+creatTime+"' and cutnum='"+cutnum+"' and proj_package_Name='"+projectPackageName+"'";
              	List<Laowu> treein=sd.seeProductpriceT(sql);
            	request.setAttribute("treein", treein);
            	request.getRequestDispatcher("productPriceTableDet.jsp").forward(request, response);
             }
        //生产部门生产工资详细结算
             else if("producetree2table".equals(action)) {
            	 String timeStart = request.getParameter("timeStart");
             	String timeEnd = request.getParameter("timeEnd");
             	String projectPackageName = request.getParameter("projectPackageName");
             	String cutnum = request.getParameter("cutnum");
             	String section=request.getParameter("section");
            	String yard=request.getParameter("yard");
             	System.out.println("...." +timeStart + "...");
             	sql="SELECT cutnum,proj_package_Name,forperson,manageUnit,ttvolume,tprice,timeStart,timeEnd,creatTime,yard,section from laowu where 1=1";
             	if(!timeStart.isEmpty() && !timeEnd.isEmpty())
            	{
            		sql=sql+" and timeStart>='"+timeStart+"' and timeEnd<='"+timeEnd+"'";
            	}
            	if(!cutnum.isEmpty())
            	{
            		sql=sql+" and cutnum='"+cutnum+"'";
            	}
            	if(!yard.isEmpty())
            	{
            		sql=sql+" and yard='"+yard+"'";
            	}
            	if(!section.isEmpty())
            	{
            		sql=sql+" and section='"+section+"'";
            	}
            	if(!projectPackageName.isEmpty())
            	{
            		sql=sql+" and proj_package_Name='"+projectPackageName+"'";
            	}
            	sql=sql+" GROUP BY cutnum,proj_package_Name,forperson,manageUnit,ttvolume,tprice,timeStart,timeEnd,yard,section ORDER BY creatTime";
                     List<Laowu> dp=sd.printProduct2priceT(sql);
                 	mapper.writeValue(response.getWriter(), dp);;
             }
        //生产部门生产工按照径级细结算
             else if("producetree23table".equals(action)) {
            	 String timeStart = request.getParameter("timeStart");
             	String timeEnd = request.getParameter("timeEnd");
             	String projectPackageName = request.getParameter("projectPackageName");
             	String cutnum = request.getParameter("cutnum");
             	String section=request.getParameter("section");
            	String yard=request.getParameter("yard");
             	System.out.println("...." +timeStart + "...");
             	sql="SELECT cutnum,proj_package_Name,forperson,manageUnit,ttvolume,tprice,timeStart,timeEnd,creatTime,yard,section from laowu_class where 1=1";
             	if(!timeStart.isEmpty() && !timeEnd.isEmpty())
            	{
            		sql=sql+" and timeStart>='"+timeStart+"' and timeEnd<='"+timeEnd+"'";
            	}
            	if(!cutnum.isEmpty())
            	{
            		sql=sql+" and cutnum='"+cutnum+"'";
            	}
            	if(!yard.isEmpty())
            	{
            		sql=sql+" and yard='"+yard+"'";
            	}
            	if(!section.isEmpty())
            	{
            		sql=sql+" and section='"+section+"'";
            	}
            	if(!projectPackageName.isEmpty())
            	{
            		sql=sql+" and proj_package_Name='"+projectPackageName+"'";
            	}
            	sql=sql+" GROUP BY cutnum,proj_package_Name,forperson,manageUnit,ttvolume,tprice,timeStart,timeEnd,yard,section ORDER BY creatTime";
                     List<Laowu> dp=sd.printProduct2priceT(sql);
                 	mapper.writeValue(response.getWriter(), dp);;
             }
      //生产部门生产工资详细结算
             else if("seeProduct2PriceT".equals(action))
             {
            	String cutnum1=request.getParameter("cutnum");
              	String projectPackageName1 = request.getParameter("projectPackageName");
              	String creatTime1 = request.getParameter("creatTime");
              	String cutnum=cutnum1.replace("'", "");
              	String projectPackageName=projectPackageName1.replace("'", "");
              	String creatTime=creatTime1.replace("'", "");
              	System.out.print(cutnum);
              	System.out.print(projectPackageName);
              	System.out.print(creatTime);
              	sql="SELECT company,proj_package_Name,cutaddress,cutvillage,quartel,largeblock,smallblock from cutnum WHERE cutnum='"+cutnum+"'";
            	List<cutnum> cutCompack=sd.findcutnumPackidLin(sql);
              	sql="SELECT * from laowu WHERE creatTime='"+creatTime+"' and cutnum='"+cutnum+"' and proj_package_Name='"+projectPackageName+"'";
              	List<Laowu> treein=sd.seeProduct23priceT(sql);
//              	int worklength=0;
//              	if(treein.size()>0) {
//              		String workidt=treein.get(0).getTworkid();
//              		String[] as = workidt.split(",");
//              		worklength=as.length;
//              	}
              	
            	request.setAttribute("treein", treein);
//            	request.setAttribute("worklength", worklength);
            	request.setAttribute("cutCompack", cutCompack);
            	request.getRequestDispatcher("productPrice23TableDet.jsp").forward(request, response);
             }
      //生产部门生产工资按径级详细结算
             else if("seeProduct23PriceT".equals(action))
             {
            	String cutnum1=request.getParameter("cutnum");
              	String projectPackageName1 = request.getParameter("projectPackageName");
              	String creatTime1 = request.getParameter("creatTime");
              	String cutnum=cutnum1.replace("'", "");
              	String projectPackageName=projectPackageName1.replace("'", "");
              	String creatTime=creatTime1.replace("'", "");
              	sql="SELECT company,proj_package_Name,cutaddress,cutvillage,quartel,largeblock,smallblock from cutnum WHERE cutnum='"+cutnum+"'";
            	List<cutnum> cutCompack=sd.findcutnumPackidLin(sql);
              	sql="SELECT * from laowu_class WHERE creatTime='"+creatTime+"' and cutnum='"+cutnum+"' and proj_package_Name='"+projectPackageName+"'";
              	List<Laowu> treein=sd.seeProduct23priceT(sql);
              	int worklength=0;
              	if(treein.size()>0) {
              		String workidt=treein.get(0).getTworkid();
              		String[] as = workidt.split(",");
              		worklength=as.length;
              	}
              	System.out.print(cutnum);
              	System.out.print(projectPackageName);
              	System.out.print(creatTime);
            	request.setAttribute("treein", treein);
            	request.setAttribute("worklength", worklength);
            	request.setAttribute("cutCompack", cutCompack);
            	request.getRequestDispatcher("productPrice23TableDet.jsp").forward(request, response);
             }
             else if("seeProductDet".equals(action))
             {
            	 String creatdate1=request.getParameter("creatdate");
               	String kname1 = request.getParameter("kname");
               	String creatdate=creatdate1.replace("'", "");
               	String kname=kname1.replace("'", "");
               	sql="SELECT * FROM customer WHERE creatdate='"+creatdate+"' and kname='"+kname+"'";
               	customer ct=sd.findcutnumDet(sql);
               	sql="SELECT GROUP_CONCAT(c.contractnum),sum(c.treenumber) as treenumberr,round(sum(o.tolstere),4) from sale_contract c JOIN outyard o on c.contractnum=o.contractnum WHERE demander='"+kname+"'";
               	customer treenum=sd.findcutnumTreenum(sql);
               	request.setAttribute("cutnumDet", ct);
            	request.setAttribute("treenum", treenum);
            	request.getRequestDispatcher("productownerDet.jsp").forward(request, response);
               	
             }

	}

}
